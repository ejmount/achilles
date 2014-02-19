package uk.ac.dundee.computing.aec.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.dundee.computing.aec.lib.Convertors;
import uk.ac.dundee.computing.aec.models.AuthModel;
import uk.ac.dundee.computing.aec.stores.UserStore;

/**
 * Servlet implementation class Auth
 */
@WebServlet({"/login", "/logout"})
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
		
		request.setAttribute("error", "Do not attempt to GET " + request.getRequestURI());
		RequestDispatcher rd = request.getRequestDispatcher("/RenderError.jsp"); 
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String args[]=Convertors.SplitRequestPath(request);
		
		if (args[args.length-1] == "login")
		{
			String usr = request.getParameter("username");
			String pass = request.getParameter("password");
			
			UserStore user = AuthModel.VerifyPassword(usr, pass);
			
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp"); 
			response.sendRedirect("/login.jsp");
			
			if (user != null){
				request.getSession().setAttribute("user", user);
			}
			else {
				
			}
		}
		else if (args[args.length-1] == "logout")
		{
			
		}
		else {
			request.setAttribute("error", "You triggered the auth handler without actually... authing.");
			RequestDispatcher rd = request.getRequestDispatcher("/RenderError.jsp"); 
			rd.forward(request, response);
		}
	}

}
