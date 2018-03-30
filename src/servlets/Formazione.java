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

import dao.CorsoDAO;
import model.Corsista;
import model.Corso;
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

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
		HttpSession s = SecurityLayer.checkSession(request);
		int ida=(int) s.getAttribute("id");
		System.out.println(ida + "id azienda");
		Corsista b=null;
		Corso c=null;
		List<Corso> listac=new ArrayList<Corso>();
		List<Corsista> listaco=new ArrayList<Corsista>();
		try {
			Database.connect();
			ResultSet corsi=Database.selectRecord("corso");
			ResultSet corsisti=Database.selectRecord("corsista,azienda","corsista.idazienda=" + ida);
			while(corsi.next()){
				
				int id=corsi.getInt("id");
				String nome=corsi.getString("nome");
				System.out.println(nome +"seeeeeee");
				c=new Corso(id,nome);
				listac.add(c);
				
				while(corsisti.next()){
					int idd=corsisti.getInt("id");
					ResultSet corsistic=Database.selectRecord("corsista,acc,corso","corsista.id=" + idd + " AND acc.id_corsista=corsista.id AND acc.idcorso=" + id);
					while(corsistic.next()){
						int idcc=corsistic.getInt("corsista.id");
						String nomec=corsistic.getString("corsista.nome");
						String cognome=corsistic.getString("corsista.cognome");
						int idcorso=corsistic.getInt("idcorso");
						b=new Corsista(idcc,nomec,cognome,idcorso);
						listaco.add(b);
								
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.put("corsi", listac);
		data.put("corsisti", listaco);
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
