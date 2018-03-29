package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CorsoDAO;
import util.FreeMarker;

/**
 * Servlet implementation class Formazione
 */
@WebServlet("/Formazione")
public class Formazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Formazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			data.put("corsi",CorsoDAO.corsi());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FreeMarker.process("formazione.html", data, response, getServletContext());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
