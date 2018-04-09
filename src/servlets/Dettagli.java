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

import dao.AziendaDAO;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class Dettagli
 */
@WebServlet("/Dettagli")
public class Dettagli extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dettagli() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		if(s!=null){
		int id=Integer.parseInt(request.getParameter("id"));
		s.setAttribute("id", id);
		data.put("azienda", AziendaDAO.specifica(id));
		FreeMarker.process("dettagli_azienda.html", data, response, getServletContext());
		}else{
			response.sendRedirect("Log");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		if(s!=null){
		doGet(request, response);
		}else{
			response.sendRedirect("Log");
		}
	}

}
