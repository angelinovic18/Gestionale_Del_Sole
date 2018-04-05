package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Azienda;
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class Scadenzario
 */
@WebServlet("/Scadenzario")
public class Scadenzario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Scadenzario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		int scaduto=0;
		List<Azienda> g=new ArrayList<Azienda>();
		try {
			Database.connect();
			ResultSet azi=Database.selectRecord("azienda,corsista,acc,corso","corsista.idazienda=azienda.id AND acc.id_corsista=corsista.id AND acc.idcorso=corso.id", "datascad ASC");
			while(azi.next()){
				int id=azi.getInt("azienda.id");
				String numero=azi.getString("azienda.numero");
				String nome=azi.getString("azienda.nome");
				String cognome=azi.getString("corsista.cognome");
				String comune=azi.getString("azienda.comune");
				String ncors=azi.getString("corsista.nome");
				String nomcorso=azi.getString("corso.nome");
				String datascad=azi.getString("acc.datascad");
				Calendar c = Calendar.getInstance();
				int year=c.get(Calendar.YEAR);
                int month= c.get(Calendar.MONTH)+1;
                /*if(month<10){
                	String meseco="0" + month;
                }*/
                System.out.println(month + " mese corrente");
                int day=c.get(Calendar.DAY_OF_MONTH);
                int mese=Integer.parseInt(datascad.substring(5,7));
                System.out.println(mese + " mese scadenza");
                if(mese-1==month){
                	scaduto=1;
                } else {
                	scaduto=0;
                }
               Azienda l=new Azienda(id,numero,nome,comune,ncors,cognome,nomcorso,datascad,scaduto); 
               g.add(l);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.put("lista", g);
		FreeMarker.process("scadenzario.html", data, response, getServletContext());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
