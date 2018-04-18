package databases;

public class UserAccount {
	private String username;
	private String password;
	private String email;
	private int lockout;
	
	public UserAccount(String uname, String pass, String mail, int lock) {
		this.username = uname;
		this.password = pass;
		this.email = mail;
		this.lockout = lock;
	}
	public UserAccount(String uname, String pass, String mail) {
		this.username = uname;
		this.password = pass;
		this.email = mail;
		this.lockout = 0;
	}
	
	public String toString() {
		return "Username: "+username+"\n password: " + password + "\n email: " + email + "\n lockout: "+ lockout;
	}
	
	//accessors
	public String getUsername() {
		return this.username;
	}
	public boolean verifyPassword(String passAttempt) {
		return passAttempt.equals(this.password);
	}
	public String getPassword() {
		return this.password;
	}
	public String getEmail() {
		return this.email;
	}
	public int getLockout() {
		return this.lockout;
	}
	
	//mutators
	protected void changePassword(String newPass) {
		this.password = newPass;
	}
	protected void incrLockout() {
		this.lockout++;
	}
	protected void resetLockout() {
		this.lockout = 0;
	}
	
	
}
