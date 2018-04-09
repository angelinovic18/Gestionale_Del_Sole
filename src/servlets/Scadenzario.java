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
import model.Corso;
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
		if(s!=null){
		int scaduto=0;
		List<Azienda> g=new ArrayList<Azienda>();
		List<Corso> as=new ArrayList<Corso>();
		try {
			Database.connect();
			ResultSet corsilist=Database.selectRecord("corso");
			while(corsilist.next()){
				int idcors=corsilist.getInt("id");
				String nom=corsilist.getString("nome");
				
				Corso k=new Corso(idcors,nom);
				as.add(k);
			}
			ResultSet azi=Database.selectRecord("azienda,corsista,acc,corso","corsista.idazienda=azienda.id AND acc.id_corsista=corsista.id AND acc.idcorso=corso.id", "datascad ASC");
			while(azi.next()){
				int id=azi.getInt("azienda.id");
				String numero=azi.getString("azienda.numero");
				String nome=azi.getString("azienda.nome");
				String cognome=azi.getString("corsista.cognome");
				int idcorsista=azi.getInt("corsista.id");
				int idcorso=azi.getInt("corso.id");
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
               Azienda l=new Azienda(id,idcorsista,idcorso,numero,nome,comune,ncors,cognome,nomcorso,datascad,scaduto); 
               g.add(l);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.put("lista", g);
		data.put("listac", as);
		FreeMarker.process("scadenzario.html", data, response, getServletContext());
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
		String premuto=request.getParameter("tasto");
		Map<String,Object> n=new HashMap<String,Object>();
		if(premuto.equals("aggiorna")){
			
			int idcorso=Integer.parseInt(request.getParameter("idcorso"));
			System.out.println(idcorso + "id corso");
			int idcorsista=Integer.parseInt(request.getParameter("idcorsista"));
			System.out.println(idcorsista + "id corsista");
			try {
				Database.connect();
				ResultSet corsis=Database.selectRecord("corso,acc,corsista","acc.idcorso=" + idcorso + " AND acc.id_corsista=" + idcorsista);
				while(corsis.next()){
					String datascad=corsis.getString("datascad");
					int durata=corsis.getInt("durata");
					int anno=Integer.parseInt(datascad.substring(6,10));
					System.out.println(anno + "annooooooo");
					int mese=Integer.parseInt(datascad.substring(3,5));
					System.out.println(mese + "meseeeeeee");
					int giorno=Integer.parseInt(datascad.substring(0,2));
					System.out.println(giorno + "giornooooo");
					anno=anno+durata;
					if(mese<10 && giorno>9){
						datascad=giorno+"/0"+mese+"/"+anno;
						}
						if(giorno<10 && mese>9){
							datascad="0"+giorno+"/"+mese+"/"+anno;
							}
						if(giorno<10 && mese<10){
							datascad="0"+giorno+"/0"+mese+"/"+anno;
							}
						n.put("datascad", datascad);
						Database.updateRecord("acc", n, "acc.id_corsista=" + idcorsista + " AND acc.idcorso=" +idcorso);
						
				}
				Database.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("Scadenzario");
		}
		if(premuto.equals("elimina")){
			int idcorso=Integer.parseInt(request.getParameter("idcorso"));
			System.out.println(idcorso + "id corso");
			int idcorsista=Integer.parseInt(request.getParameter("idcorsista"));
			System.out.println(idcorsista + "id corsista");
			try {
				Database.connect();
				Database.deleteRecord("acc","id_corsista=" + idcorsista + " AND idcorso=" +idcorso);
				Database.deleteRecord("corsista","id=" + idcorsista);
				Database.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("Scadenzario");
		}
		
		if(premuto.equals("cerca")){
			int corso=Integer.parseInt(request.getParameter("corso"));
			System.out.println(corso + " dijfdinfdsj");
			String meses=request.getParameter("mese");
			List<Corso> as=new ArrayList<Corso>();
			List<Azienda> q=new ArrayList<Azienda>();
			int scaduto=0;
			if(corso!=0 && meses==""){
				try {
					Database.connect();
					ResultSet azi=Database.selectRecord("corso,acc,corsista,azienda","corso.id=" + corso +" AND acc.idcorso=corso.id AND acc.id_corsista=corsista.id AND corsista.idazienda=azienda.id");
					while(azi.next()){
						int id=azi.getInt("azienda.id");
						String numero=azi.getString("azienda.numero");
						String nome=azi.getString("azienda.nome");
						String cognome=azi.getString("corsista.cognome");
						int idcorsista=azi.getInt("corsista.id");
						int idcorso=azi.getInt("corso.id");
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
		               Azienda l=new Azienda(id,idcorsista,idcorso,numero,nome,comune,ncors,cognome,nomcorso,datascad,scaduto); 
						q.add(l);
					}
					
					ResultSet corsilist=Database.selectRecord("corso");
					while(corsilist.next()){
						int idcors=corsilist.getInt("id");
						String nom=corsilist.getString("nome");
						
						Corso k=new Corso(idcors,nom);
						as.add(k);
					}
					data.put("lista", q);
					data.put("listac", as);
					data.put("lista", 1);
					FreeMarker.process("scadenzario.html", data, response, getServletContext());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(meses!="" && corso==0){
				
				
				List<Corso> ast=new ArrayList<Corso>();
				List<Azienda> qt=new ArrayList<Azienda>();
				int scadutot=0;
				
					try {
						Database.connect();
						ResultSet azi=Database.selectRecord("corso,acc,corsista,azienda","corsista.idazienda=azienda.id AND acc.id_corsista=corsista.id AND acc.idcorso=corso.id");
						while(azi.next()){
							int id=azi.getInt("azienda.id");
							String numero=azi.getString("azienda.numero");
							String nome=azi.getString("azienda.nome");
							String cognome=azi.getString("corsista.cognome");
							int idcorsista=azi.getInt("corsista.id");
							int idcorso=azi.getInt("corso.id");
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
			                String mese3=datascad.substring(3,5);
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
			                	scadutot=1;
			                }  else if((anno<year) || (anno==year && mese<month) || (anno==year && giorno<day && mese==month )){
			                	scadutot=2;
			                } else  {
			                	scadutot=0;
			                }
			                System.out.println(scaduto+" sei scaduto?");
			                if(mese3.equals(meses)){
			               Azienda l=new Azienda(id,idcorsista,idcorso,numero,nome,comune,ncors,cognome,nomcorso,datascad,scadutot); 
							qt.add(l);}
						}
						
						ResultSet corsilist=Database.selectRecord("corso");
						while(corsilist.next()){
							int idcors=corsilist.getInt("id");
							String nom=corsilist.getString("nome");
							
							Corso k=new Corso(idcors,nom);
							ast.add(k);
						}
						data.put("lista", qt);
						data.put("listac", ast);
						data.put("titolo", 1);
						FreeMarker.process("scadenzario.html", data, response, getServletContext());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			if(meses!="" && corso!=0){
				try {
					Database.connect();
					ResultSet azi=Database.selectRecord("corso,acc,corsista,azienda","corso.id=" + corso +" AND acc.idcorso=corso.id AND acc.id_corsista=corsista.id AND corsista.idazienda=azienda.id");
					while(azi.next()){
						int id=azi.getInt("azienda.id");
						String numero=azi.getString("azienda.numero");
						String nome=azi.getString("azienda.nome");
						String cognome=azi.getString("corsista.cognome");
						int idcorsista=azi.getInt("corsista.id");
						int idcorso=azi.getInt("corso.id");
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
		                String mese4=datascad.substring(3,5);
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
		                if(mese4.equals(meses)){
		               Azienda l=new Azienda(id,idcorsista,idcorso,numero,nome,comune,ncors,cognome,nomcorso,datascad,scaduto); 
						q.add(l);}
					}
					
					ResultSet corsilist=Database.selectRecord("corso");
					while(corsilist.next()){
						int idcors=corsilist.getInt("id");
						String nom=corsilist.getString("nome");
						
						Corso k=new Corso(idcors,nom);
						as.add(k);
					}
					data.put("lista", q);
					data.put("listac", as);
					data.put("titolo", 1);
					FreeMarker.process("scadenzario.html", data, response, getServletContext());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
				
				
				
			}
		
		
		
		
		
	}else{
		response.sendRedirect("Log");
	}
	}

}
