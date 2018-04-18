package protocol;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import databases.AccountDatabase;
import databases.UserAccount;



public class Connection extends Thread
{
	private boolean go;
	private String name;
	private int id;
	private String username = "";
	
	// -- the main server (port listener) will supply the socket
	//    the thread (this class) will provide the I/O streams
	//    BufferedReader is used because it handles String objects
	//    whereas DataInputStream does not (primitive types only)
	private BufferedReader datain;
	private DataOutputStream dataout;
	
	// -- this is a reference to the "parent" Server object
	//    it will be set at time of construction
	private Server server;
	



	public Connection (int id, Socket socket, Server server) 
	{
		this.server = server;
		this.id = id;
		this.name = Integer.toString(id);
		go = true;
		
		// -- create the stream I/O objects on top of the socket
		try {
			datain = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			dataout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public String toString ()
	{
		return name;
	}
	
	public String getname ()
	{
		return name;
	}
	
	public void Stop () throws IOException
	{
		datain.close();
		server.removeID(id);
		go = false;
	}
	
	//Runs a selected operation based on the string provided
	public void runInstruction(String txt) throws IOException
	{
		String[] instruction = txt.split("/,");
		
		// -- if the special "termination" string is received then 
		//    close the socket, remove this thread object from the
		//    server's active client thread list, and terminate the thread
		if (instruction[0].equals("DiScOnNeCt"))
		{
			this.Stop();
		}
		//Register the user.  Return true or false
		//System.out.println(instruction[0]);
		else if (instruction[0].equals("register"))
		{
			String result;
			
			AccountDatabase db = new AccountDatabase();
			if(AccountDatabase.userExists(instruction[1]))
			{
				AccountDatabase.add(instruction[1], instruction[2], instruction[3]);
				result = "register/,true";
			}
			else
			{
				result = "register/,error/,notExist";
			}
			
			dataout.writeBytes(result + "\n");
			dataout.flush();
		}
		//Login the user, return true, false, or lockedOut
		else if (instruction[0].equals("login"))
		{
			String result;
			
			AccountDatabase db = new AccountDatabase();
			UserAccount user = AccountDatabase.getUserByUsername(instruction[1]);
			
			if(user != null)
			{
				if(user.verifyPassword(instruction[2]))
				{
					//TODO Check if the user is already logged in.  If not, log the user in
					if(true)
					{
						result = "login/,true";
					}
					else
					{
						result = "login/,error/,alreadyLogged";
					}
				}
				else
				{
					//If user lockout is about to become 3
					if(user.getLockout() >= 2)
					{
						AccountDatabase.resetLockout(user);
						result = "login/,error/,lockedOut";
					}
					else
					{
						AccountDatabase.incrLockout(user);
						result = "login/,error/,invalidP";
					}
				}
			}
			else
			{
				result = "login/,error/,invalidU";
			}
			
			dataout.writeBytes(result + "\n");
			dataout.flush();
		}
		/*		NEED TO ADD CODE TO CALL METHOIDS OF OTHER CLASSES
		
		//Logout the user, always return true
		else if (instruction[0].equals("logout"))
		{
			aClass.logout(username);
			
			dataout.writeBytes("true" + "\n");
			dataout.flush();
		}
		//Change the password, return true
		else if (instruction[0].equals("changePassword"))
		{
			aClass.changePassword(instruction[1]);
			
			dataout.writeBytes("true" + "\n");
			dataout.flush();
		}
		//Recover password, return true
		else if (instruction[0].equals("passwordRecovery"))
		{
			aClass.recoverPassword(instruction[1]);
			
			dataout.writeBytes("true" + "\n");
			dataout.flush();
		}
		//Query System data, return data
		else if (instruction[0].equals("querySystemData"))
		{
			String data = aClass.querySData();
					
			dataout.writeBytes(data + "\n");
			dataout.flush();
		}
		//Query Performance data, return data
		else if (instruction[0].equals("queryPerformanceData"))
		{
			String data = aClass.queryPData();
			
			dataout.writeBytes(data + "\n");
			dataout.flush();
		}
		else if (instruction[0].equals("ticTacToe"))
		{
			//Check if this is a new game, if so radomize a move
			if(aClass.boardEmpty(instruction) && NaughtsAndCrossesGame.playerGoesFrist() == true)
			{
				String playerGamePiece = aClass.pickPlayerGamePiece();
				dataout.writeBytes("firstGame|" + playerGamePiece + "\n");
				dataout.flush();
			}
			else
			{
				//Check if player won (1 is player, 0 is tie, -1 is computer)
				if(aClass.whoWon() == 1)
				{
					dataout.writeBytes("winner|player" + "\n");
					dataout.flush();
				}
				else
				{
					int spot = aClass.makeMove();//TODO
					
					if(aClass.whoWon() == -1)
					{
						dataout.writeBytes("winner|computer|" + spot + "\n");
						dataout.flush();
					}
					else
					{
						dataout.writeBytes("myMove|" + spot + "\n");
						dataout.flush();
					}
				}
			}
		}*/
		else
		{
			System.out.println("unrecognized command >>" + txt + "<<");
			dataout.writeBytes("UnknownCommand");
			dataout.flush();
		}
	}


	public void run ()
	{
		// -- server thread runs until the client terminates the connection
		while (go) {
			try {
				// -- always receives a String object with a newline (\n)
				//    on the end due to how BufferedReader readLine() works.
				//    The client adds it to the user's string but the BufferedReader
				//    readLine() call strips it off
				String txt = datain.readLine();
				System.out.println("SERVER receive: " + txt);
				
				//Run an operation based on the string
				runInstruction(txt);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
