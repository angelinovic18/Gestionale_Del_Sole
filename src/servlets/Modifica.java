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

import dao.AziendaDAO;
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class Modifica
 */
@WebServlet("/Modifica")
public class Modifica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifica() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=0;
HttpSession s = SecurityLayer.checkSession(request);
		
		id=Integer.parseInt(request.getParameter("id"));
		data.put("azienda", AziendaDAO.specifica(id));
		FreeMarker.process("modifica.html", data, response, getServletContext());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		String decisione=request.getParameter("decisione");
		if(decisione.equals("salva")){
		int id=Integer.parseInt(request.getParameter("id"));
		String numero=request.getParameter("numero");
		String nome=request.getParameter("nome");
		System.out.println(nome + "nomeeeeeeee");
		String comune=request.getParameter("comune");
		String rappresentante=request.getParameter("rappresentante");
		String sedel=request.getParameter("sedel");
		String sedeo=request.getParameter("sedeo");
		String codicef=request.getParameter("codfis");
		String iva=request.getParameter("iva");
		String email=request.getParameter("email");
		String pec=request.getParameter("pec");
		String cellulare=request.getParameter("cellulare");
		String telefono=request.getParameter("telefono");
		String ateco=request.getParameter("ateco");
		String note=request.getParameter("note");
		
		
		
		Map<String,Object> agg=new HashMap<String,Object>();
		if(numero!=""){
			agg.put("numero", numero);
		}
		
		if(nome!=""){
			agg.put("nome", nome);
		}
		
		if(comune!=""){
			agg.put("comune", comune);
		}
		
		if(rappresentante!=""){
			agg.put("rappresentante", rappresentante);
		}
		
		if(sedel!=""){
			agg.put("sede_legale", sedel);
		}
		
		if(sedeo!=""){
			agg.put("sede_operativa", sedeo);
		}
		
		if(codicef!=""){
			agg.put("codice_fiscale", codicef);
		}
		
		if(iva!=""){
			agg.put("iva", iva);
		}
		
		if(email!=""){
			agg.put("email", email);
		}
		
		if(pec!=""){
			agg.put("pec", pec);
		}
		
		if(cellulare!=""){
			agg.put("cellulare", cellulare);
		}
		
		if(telefono!=""){
			agg.put("telefono", telefono);
		}
		
		if(ateco!=""){
			agg.put("ateco", ateco);
		}
		
		if(note!=""){
			agg.put("note", note);
		}
		
		try {
			Database.connect();
			Database.updateRecord("azienda", agg, "id = " + id);
			Database.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("Home");
	} else{
		response.sendRedirect("Home");
	}
		}

}
