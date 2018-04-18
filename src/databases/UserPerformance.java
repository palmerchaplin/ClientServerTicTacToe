package databases;

public class UserPerformance {
	private String username;
	private int wins;
	private int losses;
	private int ties;
	private int gamesPlayed;
	
	public UserPerformance(String user, int win, int loss, int tie, int played) {
		this.username = user;
		this.wins = win;
		this.losses = loss;
		this.ties = tie;
		this.gamesPlayed = played;
	}
	public UserPerformance(String user) {
		this.username = user;
		this.wins = 0;
		this.losses = 0;
		this.ties = 0;
		this.gamesPlayed = 0;
	}
	public String toString() {
		return "Username: "+this.username+"\n wins: " + this.wins +"\n losses: " + this.losses +"\n ties: " + this.ties +"\n gamesPlayed: " + this.gamesPlayed;
	}
	
	//accessors
	public String getUsername() {
		return this.username;
	}
	public int getWins() {
		return this.wins;
	}
	public int getLosses() {
		return this.losses;
	}
	public int getTies() {
		return this.ties;
	}
	public int getGamesPlayed() {
		return this.gamesPlayed;
	}
	
	//mutators
	protected void incrWins() {
		this.wins++;
	}
	protected void incrLosses() {
		this.losses++;
	}
	protected void incrTies() {
		this.ties++;
	}
	protected void incrGamesPlayed() {
		this.gamesPlayed++;
	}
}
