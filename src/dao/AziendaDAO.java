package dao;

import java.sql.ResultSet;
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
}
