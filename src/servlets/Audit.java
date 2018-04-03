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
 * Servlet implementation class Audit
 */
@WebServlet("/Audit")
public class Audit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Audit() {
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
		data.put("lista1", AziendaDAO.lista1());
		
		FreeMarker.process("audit.html", data, response, getServletContext());
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
		
		if(premuto.equals("confermaaudc")){
			int id=Integer.parseInt(request.getParameter("id"));
			String datascadaudc="";
			Map<String,Object> a=new HashMap<String,Object>();
			try {
				Database.connect();
				ResultSet azienda=Database.selectRecord("azienda","id=" +id);
				while(azienda.next()){
					String dataauc=azienda.getString("auditc");
					int anno=Integer.parseInt(dataauc.substring(0,4));
					anno=anno+1;
					int mese=Integer.parseInt(dataauc.substring(5,7));
					System.out.println(mese + "meseeeeeee");
					int giorno=Integer.parseInt(dataauc.substring(8,10));
					System.out.println(giorno + "giornooooo");
					
					datascadaudc=anno+"-"+mese+"-"+giorno;
					a.put("auditc", datascadaudc);
					Database.updateRecord("azienda",a,"id=" + id);
				}
				
				Database.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		if(premuto.equals("confermaaudt")){
			int id=Integer.parseInt(request.getParameter("id"));
			String datascadaudt="";
			Map<String,Object> a=new HashMap<String,Object>();
			try {
				Database.connect();
				ResultSet azienda=Database.selectRecord("azienda","id=" +id);
				while(azienda.next()){
					String dataaut=azienda.getString("auditt");
					int anno=Integer.parseInt(dataaut.substring(0,4));
					anno=anno+1;
					int mese=Integer.parseInt(dataaut.substring(5,7));
					System.out.println(mese + "meseeeeeee");
					int giorno=Integer.parseInt(dataaut.substring(8,10));
					System.out.println(giorno + "giornooooo");
					
					datascadaudt=anno+"-"+mese+"-"+giorno;
					a.put("auditt", datascadaudt);
					Database.updateRecord("azienda",a,"id=" + id);
				}
				
				Database.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
