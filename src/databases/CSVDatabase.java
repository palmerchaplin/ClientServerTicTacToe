package databases;

import java.util.ArrayList;

public abstract class CSVDatabase {
	private String fileLocation;
	private ArrayList<String> database = new ArrayList<>();
	
	private void readIntoArrayList() {};
	private void writeFromArrayList() {};
}
