package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;
import model.Azienda;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		data.clear();
		data.put("lista", AziendaDAO.lista());

  		
  		
  	  FreeMarker.process("home.html", data, response, getServletContext());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String premuto=request.getParameter("tasto");
		
		
		
		if(premuto.equals("si")){
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println(id + "idddddddddddd");
			AziendaDAO.cancella(id);
			doGet(request, response);
			}
		
		if(premuto.equals("cerca")){
		
		String cercaaz=request.getParameter("nomeaz");
		String cercacom=request.getParameter("comune");
		
			try {
				data.put("lista", AziendaDAO.cerca(cercaaz, cercacom));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data.put("titolo", 1);
			 FreeMarker.process("home.html", data, response, getServletContext());
			
			}
		
		
		
		
	}

}
