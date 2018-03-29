package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class Aggiungi
 */
@WebServlet("/Aggiungi")
public class Aggiungi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aggiungi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FreeMarker.process("aggiungi_cliente.html", data, response, getServletContext());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		String scelta=request.getParameter("inserisci");
		
		if(scelta.equals("yes")){
		Map<String,Object> agg=new HashMap<String,Object>();
		
		agg.put("nome", request.getParameter("nome"));
		
		agg.put("comune", request.getParameter("comune"));
		agg.put("rappresentante", request.getParameter("rappresentante"));
		agg.put("sede_legale", request.getParameter("sedel"));
		agg.put("sede_operativa", request.getParameter("sedeo"));
		agg.put("codice_fiscale", request.getParameter("codfis"));
		agg.put("iva", request.getParameter("iva"));
		agg.put("email", request.getParameter("email"));
		agg.put("pec", request.getParameter("pec"));
		agg.put("cellulare", request.getParameter("cellulare"));
		agg.put("telefono", request.getParameter("telefono"));
		agg.put("ateco", request.getParameter("ateco"));
		agg.put("note", request.getParameter("note"));
		
		try {
			Database.connect();
			Database.insertRecord("azienda", agg);
			Database.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		response.sendRedirect("Home");
	}

}