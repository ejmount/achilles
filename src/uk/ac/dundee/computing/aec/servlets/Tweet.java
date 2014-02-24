package uk.ac.dundee.computing.aec.servlets;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;

import uk.ac.dundee.computing.aec.lib.*;
import uk.ac.dundee.computing.aec.models.*;
import uk.ac.dundee.computing.aec.stores.*;

/**
 * Servlet implementation class Tweet
 */
@WebServlet({"/", "/tweet", "/tweet/*" })
public class Tweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Cluster cluster;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tweet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		cluster = CassandraHosts.getCluster();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String args[]=Convertors.SplitRequestPath(request);
		
		TweetModel tm= new TweetModel();
		tm.setCluster(cluster);
		LinkedList<TweetStore> tweetList = tm.getTweets();
		Collections.sort(tweetList);
		Collections.reverse(tweetList);
		
		if (args.length > 2) {
			LinkedList<TweetStore> userList = new LinkedList<>();
			String user = args[2].toLowerCase();
			for(TweetStore tweet : tweetList )
			{
				if(user.equals(tweet.getUser().toLowerCase()))
				userList.add(tweet);
			}
			request.setAttribute("Tweets", userList); //Set a bean with the list in it
			
		}
		else {
			request.setAttribute("Tweets", tweetList); //Set a bean with the list in it
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/RenderTweets.jsp"); 

		rd.forward(request, response);
	}

	

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserStore u = (UserStore)request.getSession().getAttribute("user"); 
		
		if (u == null 
			|| request.getParameter("tweet") == null
			|| request.getParameter("tweet").isEmpty()) 
		{
			response.setStatus(403);
			request.setAttribute("error", "Invalid Request");
			RequestDispatcher rd = request.getRequestDispatcher("/RenderError.jsp"); 

			rd.forward(request, response);
		}
		else {
			TweetModel tm = new TweetModel();
			tm.setCluster(cluster);
			TweetStore newT = new TweetStore(); 
			newT.setUser(u.getUsername());
			newT.setTweet(request.getParameter("tweet").replace("<", "&lt;").replace(">", "&gt;"));
					
			tm.postTweet(newT);
			
			this.doGet(request, response);
		}
	}

}
