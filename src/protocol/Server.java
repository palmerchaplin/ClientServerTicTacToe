package protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server
{
	// -- assign each client connection an ID. Just increment for now
	private int nextId = 0;
	
	// -- the port number used for client communication
	private final int PORT = 8000;
			
	// -- list of active client threads by ID number
	private Vector<Connection> clientconnections;
	
	// -- the socket that waits for client connections
	private ServerSocket serversocket;
	
	public int getNumOfConnections()
	{
		return clientconnections.size();
	}
	
	
	public int getPort()
	{
		return PORT;
	}

	public Server ()
	{
		
		// -- construct the list of active client threads
		clientconnections = new Vector<Connection>();

		// -- create an anonymous Connect object
		//    the ServerSocket listener will run in its own thread
		//    so an object reference is unnecessary
		try {
			// -- open the server socket
			serversocket = new ServerSocket(this.getPort());
			
			// -- listen for a connection request on the server socket
			listen();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}	

	}

	private void listen ()
	{
		// -- server runs until we manually shut it down
		while (true) {
			try {
				// -- block until a client comes along
				Socket socket = serversocket.accept();
				
				this.peerconnection(socket);
								
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void peerconnection (Socket socket)
	{		
		// -- when a client arrives, create a thread for their communication
		Connection connection = new Connection(nextId, socket, this);

		// -- add the thread to the active client threads list
		clientconnections.add(connection);
		
		// -- start the thread
		connection.start();

		// -- place some text in the area to let the server operator know
		//    what is going on
		System.out.println("SERVER: connection received for id " + nextId + "\n");
		++nextId;
	}
	

		
	// -- called by a ServerThread when a client is terminated
	public void removeID(int id)
	{
		// -- find the object belonging to the client thread being terminated
		for (int i = 0; i < clientconnections.size(); ++i) {
			Connection cc = clientconnections.get(i);
			long x = cc.getId();
			if (x == id) {
				// -- remove it from the active threads list
				//    the thread will terminate itself
				clientconnections.remove(i);
				
				// -- place some text in the area to let the server operator know
				//    what is going on
				System.out.println("SERVER: connection closed for client id " + id + "\n");
				break;
			}
		}


	}
		
	public static void main (String args[])
	{
		// -- instantiate the server anonymously
		//    no need to keep a reference to the object since it will run in its own thread
		new Server();
	}



}
