package uk.ac.dundee.computing.aec.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

abstract public class AuthModel {

	static Cluster cluster;
	
	static void setCluster(Cluster C)
	{
		cluster = C;
	}
	
	static void RegisterUser(String username, String password, boolean admin)
	{
		Session session = cluster.connect("twitter");
		PreparedStatement statement = session.prepare("INSERT INTO users(username, passwordhash,admin,emails) VALUES(?,?,?,NULL);");
		BoundStatement boundStatement = new BoundStatement(statement);
		
		boundStatement.bind(username, password, admin ? "True" : "False"); // I hate primitive/object distinctions. 
		
		session.execute(boundStatement);
	}
	
	static boolean VerifyPassword(String username, String password)
	{
		cluster.connect();		
		
		throw new UnsupportedOperationException();
		
		return false;
	}
	
}
