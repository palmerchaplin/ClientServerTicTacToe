package databases;

import java.io.*;
import java.util.ArrayList;

public class PerformanceDatabase extends CSVDatabase{
	private static String fileLocation = "C:\\TicTacToeDatabases\\PerformanceDatabase.csv";
	private static ArrayList<UserPerformance> database = new ArrayList<>();
	private static String separationCharacter = ",";
	private static UserPerformance bestUser;
	
	private static File file = new File(fileLocation);
	
	public static void initialize() {
		readIntoArrayList();
	}
	
	//reads in the first line in the file as the best user, then reads the rest in to the database
	private static void readIntoArrayList(){
		if(file.exists() && !file.isDirectory()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				boolean bestRead = false;
			    String line;
			    while ((line = br.readLine()) != null) {
			    	if(bestRead) {
			    		database.add(lineToUser(line));
			    	}
			    	else {
			    		bestUser = lineToUser(line);
			    		bestRead = true;
			    	}
			    }
			} catch (Exception e) {
				System.out.println("you messed up man (performance)");
			}
		} else {
			bestUser = new UserPerformance("Nobody Yet!");
		}
	}
	
	//writes best user to file, then adds on each user in the database
	private static void writeFromArrayList() {
		  try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
			  writer.write(userToLine(bestUser));
			  for (UserPerformance user:database) {
				  writer.append(userToLine(user));
				  writer.append("\n");
			  }
		  } catch (Exception e) {
			  System.out.println("smh (performance)");
		  }
	}
	
	// converts a user to a csv string to write to file
	private static String userToLine(UserPerformance user) {
		return user.getUsername() + separationCharacter + user.getWins()
			+ separationCharacter + user.getLosses() + separationCharacter
			+ user.getTies() + separationCharacter + user.getGamesPlayed();
	}
	
	// converts a csv line to a UserPerformance object
	private static UserPerformance lineToUser(String line) {
		 String[] splitLine = line.split(separationCharacter);
		 return new UserPerformance(splitLine[0],Integer.parseInt(splitLine[1]),Integer.parseInt(splitLine[2]),Integer.parseInt(splitLine[3]),Integer.parseInt(splitLine[4]));
	}
	
	//
	// ACCESSORS
	//
	
	public static int getSize() {
		return database.size();
	}
	
	// returns boolean true or false depending on if the user exists
	public static boolean userExists(String uname) {
		boolean exists = false;
		for (UserPerformance user:database) {
			if (user.getUsername().equals(uname)) {
				exists = true;
			}
		}
		return exists;
	}
	
	//returns user if it exists, returns an empty user if not
	public static UserPerformance getUserByUsername(String uname) {
		UserPerformance toReturn = new UserPerformance("UserDoesNotExist",0,0,0,0);
		for (UserPerformance user:database) {
			if (user.getUsername().equals(uname)) {
				toReturn = user;
			}
		}
		return toReturn;
	}
	
	public String toString() {
		String toReturn = bestUser.toString();
		for (UserPerformance user : database) {
			toReturn = toReturn + user.toString() + "\n";
		}
		return toReturn;
	}
	
	public UserPerformance getBestUser() {
		return bestUser;
	}
	
	//
	// MUTATORS
	//
	
	public static void add(String user){
		database.add(new UserPerformance(user));
		writeFromArrayList();
	}
	
	public static void incrWins(UserPerformance user) {
		user.incrWins();
		writeFromArrayList();
	}
	public static void incrLosses(UserPerformance user) {
		user.incrLosses();
		writeFromArrayList();
	}
	public static void incrTies(UserPerformance user) {
		user.incrTies();
		writeFromArrayList();
	}
	public static void incrGamesPlayed(UserPerformance user) {
		user.incrGamesPlayed();
		writeFromArrayList();
	}
	
	//
	// CONSTRUCTORS
	//
	
	public PerformanceDatabase() {
		readIntoArrayList();
	}
}
