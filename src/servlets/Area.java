package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class Area
 */
@WebServlet("/Area")
public class Area extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Area() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FreeMarker.process("area.html", data, response, getServletContext());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		int areascelta = Integer.parseInt(request.getParameter("areascelta"));
		if(areascelta==1) {
			response.sendRedirect("Home");
		}
		if(areascelta==2) {
			FreeMarker.process("haccp.html", data, response, getServletContext());
		}
		if(areascelta==3) {
			FreeMarker.process("privacy.html", data, response, getServletContext());
		}
		if(areascelta==4) {
		FreeMarker.process("antincendio.html", data, response, getServletContext());
		}
	}

}
