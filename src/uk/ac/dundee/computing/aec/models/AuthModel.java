package uk.ac.dundee.computing.aec.models;


import uk.ac.dundee.computing.aec.stores.UserStore;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

public class AuthModel {

	Cluster cluster;
	String ks;
	
	public AuthModel(Cluster C, String keyspace)
	{
		cluster = C;
		ks = keyspace;
	}

	
	public void RegisterUser(UserStore U)
	{
		Session session = cluster.connect(ks);
		PreparedStatement statement = session.prepare("INSERT INTO users(username, pass,admin,emails) VALUES(?,?,false,NULL) IF NOT EXISTS;");
		BoundStatement boundStatement = new BoundStatement(statement);
		
		boundStatement.bind(U.getUsername(), U.getPassword()); 
		
		session.execute(boundStatement);
	}
	
	public UserStore VerifyPassword(String username, String password)
	{
		if (username == null || password == null)
			return null;
		
		Session session = cluster.connect("twitter");		
		
		PreparedStatement statement = session.prepare("SELECT * from users WHERE username=? LIMIT 1"); 
		// Assume there's only one user by any given name
		BoundStatement boundStatement = new BoundStatement(statement);
		
		boundStatement.bind(username); 
		
		ResultSet res = session.execute(boundStatement);
		Row r = res.one(); 
		if(r != null)
		{
			String p = r.getString("pass");
			if (password.equals(p))
			{
				UserStore U = new UserStore();
				U.setUsername(username);
				U.setPassword(password);
				U.setAdmin(r.getBool("admin"));
				return U;
			}
		}
		else
		{
			return null;
		}
		
		return null;
	}
	
}
