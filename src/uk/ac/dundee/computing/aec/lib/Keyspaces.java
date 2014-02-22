package uk.ac.dundee.computing.aec.lib;


import com.datastax.driver.core.*;

public final class Keyspaces {

	static public final String keySpaceName = "achilles_db";
	
	public Keyspaces(){
		
		
	}
	
	public static void SetUpKeySpaces(Cluster c){
		try{
		
			String ks = "CREATE KEYSPACE " + keySpaceName + " WITH replication = {'class': 'SimpleStrategy','replication_factor': '1'};";
			String tweets = "CREATE COLUMNFAMILY tweets (user text, interaction_time timeuuid, tweet text, PRIMARY KEY (interaction_time));";
			String users = "CREATE COLUMNFAMILY users (username text, admin boolean, email text, pass text, following set<text>, PRIMARY KEY (username));";
			String buildAdmin = "INSERT INTO users (username, admin, email, pass) VALUES('admin', true, '','qwe1');";
			
			Session s = c.connect();
			
			Metadata M = c.getMetadata();
			
			if (M.getKeyspace(keySpaceName) == null)
			{
				s.execute(ks);
				System.err.print("Recreated keyspace");
				s = c.connect(keySpaceName);
				s.execute(tweets);
				s.execute(users);
				s.execute(buildAdmin);
				System.err.print("Recreated tables");
			}
			
			
			
		}catch(Exception et){
			System.out.println("Other keyspace or coulm definition error" +et);
		}
		
	}
}
