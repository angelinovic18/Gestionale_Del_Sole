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
				System.out.println(year + "anno correnteeeee");
                int month= c.get(Calendar.MONTH)+1;
                /*if(month<10){
                	String meseco="0" + month;
                }*/
                System.out.println(month + " mese corrente");
                int day=c.get(Calendar.DAY_OF_MONTH);
                int mese=Integer.parseInt(datascad.substring(3,5));
                int anno=Integer.parseInt(datascad.substring(6,10));
                String anno3=datascad.substring(6,10);
                System.out.println(anno3 + "anno di scadenzaaaaa");
                System.out.println(anno + "anno di scadenzaaaaa");
                int giorno=Integer.parseInt(datascad.substring(0,2));
                System.out.println(mese + " mese scadenza");
               
                int mese2=mese;
                int anno2=anno-year;
                if(mese==1){
                	mese2=12;
                }
                if((mese2==month && anno2==1) || (anno==year && (mese-1==month || mese==month))){
                	scaduto=1;
                }  else if((anno<year) || (anno==year && mese<month) || (anno==year && giorno<day && mese==month )){
                	scaduto=2;
                } else  {
                	scaduto=0;
                }
                System.out.println(scaduto+" sei scaduto?");
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
