package uk.ac.dundee.computing.aec.models;

/*
 * Expects a cassandra columnfamily defined as
 * use keyspace2;
  CREATE TABLE Tweets (
  user varchar,
  interaction_time timeuuid,
   tweet varchar,
   PRIMARY KEY (user,interaction_time)
  ) WITH CLUSTERING ORDER BY (interaction_time DESC);
 * To manually generate a UUID use:
 * http://www.famkruithof.net/uuid/uuidgen
 * 
 * 
 * 
 * 
 */


import java.util.LinkedList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import uk.ac.dundee.computing.aec.lib.Convertors;
import uk.ac.dundee.computing.aec.lib.Keyspaces;
import uk.ac.dundee.computing.aec.stores.TweetStore;
public class TweetModel {
	Cluster cluster;
	public TweetModel(){
		
	}

	public void setCluster(Cluster cluster){
		this.cluster=cluster;
	}
	
	public void postTweet(TweetStore T)
	{
		Session session = cluster.connect(Keyspaces.keySpaceName);
		PreparedStatement statement = session.prepare("INSERT INTO tweets(user, tweet,interaction_time) VALUES(?, ?, now());");
		BoundStatement boundStatement = new BoundStatement(statement);
		
		boundStatement.bind(T.getUser(), T.getTweet());
		
		session.execute(boundStatement);
		session.shutdown();
	}
	
	
	
	public LinkedList<TweetStore> getTweets() {
		LinkedList<TweetStore> tweetList = new LinkedList<TweetStore>();
		Session session = cluster.connect(Keyspaces.keySpaceName);

		PreparedStatement statement = session.prepare("SELECT * from tweets");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No Tweets returned");
		} else {
			for (Row row : rs) {
				TweetStore ts = new TweetStore();
				ts.setTweet(row.getString("tweet"));
				ts.setUser(row.getString("user"));
				ts.setData(Convertors.UUIDToDate(row.getUUID("interaction_time")));
				tweetList.add(ts);
			}
		}
		session.shutdown();
		return tweetList;
	}
}
