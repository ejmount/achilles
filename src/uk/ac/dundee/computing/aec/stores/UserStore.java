package uk.ac.dundee.computing.aec.stores;

public class UserStore {
	
	String Username;
	String Password; 
	boolean Admin;
	
	public String getUsername() { return Username; }
	public void setUsername(String u) { Username = u; }
	
	public String getPassword() { return Password; }
	public void setPassword(String p) { Password = p; }
	
	public boolean isAdmin() { return Admin; }
	public void setAdmin(boolean b) { Admin = b; }
	
	
}
