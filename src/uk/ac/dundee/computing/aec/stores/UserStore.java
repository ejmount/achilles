package uk.ac.dundee.computing.aec.stores;

import java.util.List;

public class UserStore {
	
	String Username;
	String Password; 
	boolean Admin;
	String Email;
	List<String> Followers;
	
	public String getUsername() { return Username; }
	public void setUsername(String u) { Username = u; }
	
	public String getPassword() { return Password; }
	public void setPassword(String p) { Password = p; }
	
	public boolean isAdmin() { return Admin; }
	public void setAdmin(boolean b) { Admin = b; }
	
	public List<String> getFollowers() { return Followers; }
	public void setFollowers(List<String> L) { Followers = L; }
	
	public String getEmail() { return Email; }
	public void setEmail(String S) { Email = S; }
	
}
