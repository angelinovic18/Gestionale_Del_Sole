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
				String note=az.getString("note");
				
				azi=new Azienda(id,numero,nome,comune,rappresentante,sedel,sedeo,codicef,iva,email,pec,cellulare,telefono,ateco,note);
				
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
	
}
