package uk.ac.dundee.computing.aec.stores;

import java.util.Date;
//import java.lang

public class TweetStore implements Comparable<TweetStore> {
     String Tweet;
     String User;
     Date timestamp; 
     
     public String getTweet(){
    	 return Tweet;
     }
     public String getUser(){
    	 return User;
     }
     
     public Date getDate() {
    	 return timestamp;
     }
     
     public void setTweet(String Tweet){
    	 this.Tweet=Tweet;
     }
     public void setUser(String User){
    	 this.User=User;
     }
     public void setData(Date d){
    	 this.timestamp = d;
     }
     
     public int compareTo(TweetStore T)
     {
    	 return this.timestamp.compareTo(T.timestamp);
     }
     
}
