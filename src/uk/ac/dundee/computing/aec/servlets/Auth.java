package uk.ac.dundee.computing.aec.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.dundee.computing.aec.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.lib.Convertors;
import uk.ac.dundee.computing.aec.models.AuthModel;
import uk.ac.dundee.computing.aec.stores.UserStore;

/**
 * Servlet implementation class Auth
 */
@WebServlet({"/login", "/logout", "/register"})
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String args[]=Convertors.SplitRequestPath(request);
		if (args[args.length-1].equals("logout"))
		{
			handleLogout(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/RenderLogin.jsp"); 
			rd.forward(request, response);
		}
	}

	
	protected void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usr = request.getParameter("username");
		String pass = request.getParameter("password");
		
		if (usr != null && pass != null && !usr.isEmpty() && !pass.isEmpty()  ) {
			AuthModel auth = new AuthModel(CassandraHosts.getCluster());
						
			UserStore user = auth.VerifyPassword(usr, pass);
			
			if (user != null){
				request.getSession().setAttribute("user", user);
				request.setAttribute("message", "Login sucessful");
			}
			else {
				request.setAttribute("message", "Login failed. Please check username and password.");
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/RenderLogin.jsp"); 
		rd.forward(request, response);
	}
	
	protected void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.err.print("Enter logout");
		
		request.setAttribute("message", "");
		if (request.getSession().getAttribute("user") != null) 
		{
			request.setAttribute("message", "If you were logged in, you are now logged out.");
			request.getSession().setAttribute("user", null);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/RenderLogin.jsp"); 
		rd.forward(request, response);
	}
	
	protected void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String usr = request.getParameter("username");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		
		if (usr != null && pass != null && !usr.isEmpty() && !pass.isEmpty()  ) {
	
			AuthModel auth = new AuthModel(CassandraHosts.getCluster());
		
			String sanUser = usr.replace("<", "&lt;").replace(">", "&gt;");
			
			
			UserStore U = new UserStore();
			U.setUsername(sanUser);
			U.setPassword(pass);
			U.setEmail(email);
			
			auth.RegisterUser(U);
		}
		else {
			request.setAttribute("error", "Invalid register request");
			RequestDispatcher rd = request.getRequestDispatcher("/RenderError.jsp"); 
			rd.forward(request, response);
		}
		
		handleLogin(request, response); // Logging in with the username/pass we just registered *should* work correctly 
										// and leave the user logged in.
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String args[]=Convertors.SplitRequestPath(request);
		
		if (args[args.length-1].equals("login"))
		{
			handleLogin(request, response);
		}
		else if (args[args.length-1].equals("register"))
		{
			handleRegister(request, response);
		}
		else {
			request.setAttribute("error", "You triggered the auth handler without actually... authing.");
			for(int i = 0; i < args.length; i++)
			{
				System.err.print(args[i]);
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/RenderError.jsp"); 
			rd.forward(request, response);
		}
	}

}
