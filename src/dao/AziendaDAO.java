package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Azienda;
import util.DataUtil;
import util.Database;


public class AziendaDAO implements AziendaDAO_interface {
	
	public static List<Azienda> lista(){
		List<Azienda> aziende=new ArrayList<Azienda>();
		try {
			Database.connect();
			ResultSet listaaz=Database.selectRecord("azienda");
			while(listaaz.next()){
				int id=listaaz.getInt("id");
				String numero=listaaz.getString("numero");
				String nome=listaaz.getString("nome");
				String comune=listaaz.getString("comune");
				
				Azienda c=new Azienda(id,numero, nome,comune);
				aziende.add(c);
			}
			Database.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aziende;
	}
	
	public static List<Azienda> lista1(){
		List<Azienda> aziende1=new ArrayList<Azienda>();
		try {
			Database.connect();
			ResultSet listaaz1=Database.selectRecordCond("azienda", "`azienda`.`auditc` ASC");
			while(listaaz1.next()){
				int id=listaaz1.getInt("id");
				String numero=listaaz1.getString("numero");
				String nome=listaaz1.getString("nome");
				String comune=listaaz1.getString("comune");
				String auditc=listaaz1.getString("auditc");
				System.out.println(auditc + " dataaaaaaa");
				String auditt=listaaz1.getString("auditt");

				System.out.println(auditt + " dataaaaaaa");
		
				if(auditc!="" && auditt!="") {
				

				Azienda z=new Azienda(id,numero, nome,comune,auditc,auditt);
				aziende1.add(z);
			} }
			Database.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aziende1;
	}
	
	public static List<Azienda> lista2(){
		List<Azienda> aziende2=new ArrayList<Azienda>();
		try {
			Database.connect();
			ResultSet listaaz2=Database.selectRecordCond("azienda", "`azienda`.`auditt` ASC");
			while(listaaz2.next()){
				int id=listaaz2.getInt("id");
				String numero=listaaz2.getString("numero");
				String nome=listaaz2.getString("nome");
				String comune=listaaz2.getString("comune");
				String auditc=listaaz2.getString("auditc");
				System.out.println(auditc + " dataaaaaaa");
				String auditt=listaaz2.getString("auditt");

				System.out.println(auditt + " dataaaaaaa");
		
				if(auditc!="" && auditt!="") {
				

				Azienda x=new Azienda(id,numero, nome,comune,auditc,auditt);
				aziende2.add(x);
			} }
			Database.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aziende2;
	}
	
	public static Azienda specifica(int id){
		Azienda azi=null;
		try {
			Database.connect();
			ResultSet az=Database.selectRecord("azienda","id=" + id);
			while(az.next()){
				
				String numero=az.getString("numero");
				String nome=az.getString("nome");
				System.out.println(nome + "nomeeeeeeee");
				String comune=az.getString("comune");
				String rappresentante=az.getString("rappresentante");
				String sedel=az.getString("sede_legale");
				String sedeo=az.getString("sede_operativa");
				String codicef=az.getString("codice_fiscale");
				String iva=az.getString("iva");
				String email=az.getString("email");
				String pec=az.getString("pec");
				String cellulare=az.getString("cellulare");
				String telefono=az.getString("telefono");
				String ateco=az.getString("ateco");
				String auditc=az.getString("auditc");
			
				String auditt=az.getString("auditt");
			
				String note=az.getString("note");
				
				azi=new Azienda(id,numero,nome,comune,rappresentante,sedel,sedeo,codicef,iva,email,pec,cellulare,telefono,ateco,auditc,auditt,note);
				
			}
			Database.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return azi;
	}
	
	public static void cancella(int id){
		
		try {
			Database.connect();
			Database.deleteRecord("azienda", "id = " + id);
			Database.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static List<Azienda> cerca(String cercaaz, String cercacom) throws Exception{
		
		List<Azienda> cercate=new ArrayList<Azienda>();
		
				
			
				if(cercaaz!="" && cercacom==""){
				return DataUtil.searchaz(cercaaz);
				}
				
				if(cercacom!="" && cercaaz==""){
					return DataUtil.searchcom(cercacom);
				}
				
				
				if(cercacom!="" && cercaaz!=""){
					return DataUtil.search(cercaaz,cercacom);
				}
				
				
				
				
			
		 
			
		
	
		return cercate;}
	
	
	
	public static List<Azienda> cercaaz(String cercaaz) throws Exception{
		
		
		
				return DataUtil.searchaz(cercaaz);
				
				
			}
	
	public static List<Azienda> cercam(String mese) throws Exception{
		List<Azienda> lis=new ArrayList<Azienda>();
		Database.connect();
		ResultSet listaz=Database.selectRecord("azienda");
		while(listaz.next()){
			String auditc=listaz.getString("auditc");
			String meseaudc=auditc.substring(5,7);
			if(meseaudc.equals(mese)){
				int id=listaz.getInt("id");
				
            	String auditt=listaz.getString("auditt");
                String nome = listaz.getString("nome");
                String comune = listaz.getString("comune");
                String numero = listaz.getString("numero");
                
                Azienda n=new Azienda(id,numero,nome,comune,auditc,auditt);
              lis.add(n);
			}
			
		}
		Database.close();
		return lis;
		
		
	}
	
	public static List<Azienda> cercam1(String mese) throws Exception{
		List<Azienda> lis=new ArrayList<Azienda>();
		Database.connect();
		ResultSet listaz=Database.selectRecord("azienda");
		while(listaz.next()){
			String auditt=listaz.getString("auditt");
			String meseaudc=auditt.substring(5,7);
			if(meseaudc.equals(mese)){
				int id=listaz.getInt("id");
				
            	String auditc=listaz.getString("auditc");
                String nome = listaz.getString("nome");
                String comune = listaz.getString("comune");
                String numero = listaz.getString("numero");
                
                Azienda n=new Azienda(id,numero,nome,comune,auditc,auditt);
              lis.add(n);
			}
			
		}
		Database.close();
		return lis;
		
		
	}
}
