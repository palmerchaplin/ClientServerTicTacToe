package databases;

import java.io.*;
import java.util.ArrayList;

public class AccountDatabase extends CSVDatabase{
	private static String fileLocation = "C:\\TicTacToeDatabases\\AccountDatabase.csv";
	private static ArrayList<UserAccount> database = new ArrayList<>();
	private static String separationCharacter = ",";
	
	private static File file = new File(fileLocation);
	
	//reads file into the arraylist database
	private static void readIntoArrayList(){
		if(file.exists() && !file.isDirectory()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			    	database.add(lineToUser(line));
			    }
			} catch (Exception e) {
				System.out.println("you messed up man");
			}
		}
	}
	
	//writes best user to file, then adds on each user in the database
	private static void writeFromArrayList() {
		  try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
			  for (UserAccount user:database) {
				  writer.append(userToLine(user));
				  writer.append("\n");
			  }
		  } catch (Exception e) {
			  System.out.println("smh");
		  }
	}
	
	// converts UserAccount object to csv line string
	private static String userToLine(UserAccount user) {
		return user.getUsername() + separationCharacter
				+ user.getPassword() + separationCharacter
				+ user.getEmail() + separationCharacter + user.getLockout();
	}
	
	// converts csv line to UserAccount object
	private static UserAccount lineToUser(String line) {
		String[] splitLine = line.split((separationCharacter));
		 
		return new UserAccount(splitLine[0],splitLine[1],splitLine[2],Integer.parseInt(splitLine[3]));
	}
	
	public String toString() {
		String toReturn = "";
		for (UserAccount user : database) {
			toReturn = toReturn + user.toString() + "\n";
		}
		return toReturn;
	}
	
	//
	// ACCESSORS
	//
	
	public static boolean authenticateLogin(String uname, String pass) {
		boolean toReturn = false;
		UserAccount user = getUserByUsername(uname);
		if (user.getUsername().equals(null)) {
			toReturn = false;
		}else {
			toReturn = user.verifyPassword(pass);
		}
		return toReturn;
	}
	
	public static int getSize() {
		return database.size();
	}
	
	// returns boolean true or false depending on if the user exists
	public static boolean userExists(String uname) {
		boolean exists = false;
		for (UserAccount user:database) {
			if (user.getUsername().equals(uname)) {
				exists = true;
			}
		}
		return exists;
	}
	
	//returns user if it exists, returns an empty user if not
	public static UserAccount getUserByUsername(String uname) {
		UserAccount toReturn = null;
		for (UserAccount user:database) {
			if (user.getUsername().equals(uname)) {
				toReturn = user;
			}
		}
		return toReturn;
	}
	
	//
	// MUTATORS
	//
	
	public static void add(String user, String pass, String email) {
		database.add(new UserAccount(user, pass, email));
		writeFromArrayList();
	}
	
	public static void changePassword(UserAccount user, String newPass) {
		user.changePassword(newPass);
		writeFromArrayList();
	}
	public static void incrLockout(UserAccount user) {
		user.incrLockout();
		writeFromArrayList();
	}
	public static void resetLockout(UserAccount user) {
		user.incrLockout();
		writeFromArrayList();
	}
		
	//
	// CONSTRUCTORS
	//
	
	public AccountDatabase() {
		readIntoArrayList();
	}
}
