package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Azienda;

import util.Database;


public class AziendaDAO implements AziendaDAO_interface {
	
	public static List<Azienda> lista(){
		List<Azienda> aziende=new ArrayList<Azienda>();
		try {
			Database.connect();
			ResultSet listaaz=Database.selectRecord("azienda");
			while(listaaz.next()){
				int id=listaaz.getInt("id");
				String nome=listaaz.getString("nome");
				String comune=listaaz.getString("comune");
				
				Azienda c=new Azienda(id,nome,comune);
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
				
				azi=new Azienda(id,nome,comune,rappresentante,sedel,sedeo,codicef,iva,email,pec,cellulare,telefono,ateco,note);
				
			}
			Database.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return azi;
	}
	
	public static void cancella(int id, String decisione){
		if(decisione.equals("si")){
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
	}
	
	public static List<Azienda> cerca(String cercaaz, String cercacom){
		Azienda az=null;
		int c=0;
		int b=0;
		List<Azienda> cercate=new ArrayList<Azienda>();
		try {
			Database.connect();
			ResultSet a=Database.selectRecord("azienda");
			while(a.next()){
				c=0;
				b=0;
				int id2=a.getInt("id");
				String nome=a.getString("nome");
				
				String comune=a.getString("comune");
				String rappresentante=a.getString("rappresentante");
				String sedel=a.getString("sede_legale");
				String sedeo=a.getString("sede_operativa");
				String codicef=a.getString("codice_fiscale");
				String iva=a.getString("iva");
				String email=a.getString("email");
				String pec=a.getString("pec");
				String cellulare=a.getString("cellulare");
				String telefono=a.getString("telefono");
				String ateco=a.getString("ateco");
				String note=a.getString("note");
				
				
				char[] comunecar= comune.toCharArray();
				char[] nomecar= nome.toCharArray();
				if(cercaaz!="" && cercacom==""){
					char[] nomcar= cercaaz.toCharArray();
					if(nomcar.length<=nomecar.length){
					for(int i=0; i<nomcar.length; i++){
						
						if(nomcar[i]==nomecar[i]){
							c=c+1;
						}}
					
				}
					if(c==nomcar.length){
						az=new Azienda(id2,nome,comune);
						cercate.add(az);
					}
				}
				
				if(cercacom!="" && cercaaz==""){
					char[] comucar= cercacom.toCharArray();
					if(comucar.length<=comunecar.length){
					for(int i=0; i<comucar.length; i++){
						
						if(comucar[i]==comunecar[i]){
							c=c+1;
						}}
					
				}
					if(c==comucar.length){
						az=new Azienda(id2,nome,comune);
						cercate.add(az);
					}
				}
				
				
				if(cercacom!="" && cercaaz!=""){
					char[] nomcar= cercaaz.toCharArray();
					char[] comucar= cercacom.toCharArray();
					if(comucar.length<=comunecar.length){
					for(int i=0; i<comucar.length; i++){
						
						if(comucar[i]==comunecar[i]){
							c=c+1;
						}}}
						if(nomcar.length<=nomecar.length){
						for(int j=0; j<nomcar.length; j++){
							
							if(nomcar[j]==nomecar[j]){
								b=b+1;
							}
					
				}}
					if(c==comucar.length && b==nomcar.length){
						az=new Azienda(id2,nome,comune);
						cercate.add(az);
					}
				}
				
				
				
				
			
		} 
			Database.close();
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cercate;
	
}}
