package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AziendaDAO;

import util.DataUtil;
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class Log
 */
@WebServlet("/Log")
public class Log extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FreeMarker.process("login.html", data, response, getServletContext());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		{
			// TODO Auto-generated method stub
			HttpSession s = SecurityLayer.checkSession(request);
			
			  String username = request.getParameter("username");
	          String pass = request.getParameter("pass");
	          System.out.println(pass);
	          
	          int userid=0;
	          
	          try {
	        	  Database.connect();
				userid=DataUtil.checkUser(username,pass);
				
				s.setAttribute("userid", userid);
				Database.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          if(userid==0){
	        	 FreeMarker.process("login.html", data, response, getServletContext()); 
	          } else{
	        	  
	        	  
	        	  SecurityLayer.createSession(request, username , userid);
	        	  
	        	
	        	  
	        	  
	      		data.put("lista", AziendaDAO.lista());

	      		
	      		
	        	  FreeMarker.process("home.html", data, response, getServletContext());}
	          }
		}
		
	}


