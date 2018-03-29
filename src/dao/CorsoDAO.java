package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Corso;
import util.Database;

public class CorsoDAO {

	public static List<Corso> corsi() throws Exception{
		Corso c=null;
		List<Corso> listac=new ArrayList<Corso>();
		try {
			Database.connect();
			ResultSet corsi=Database.selectRecord("corso");
			while(corsi.next()){
				int id=corsi.getInt("id");
				String nome=corsi.getString("nome");
				System.out.println(nome +"seeeeeee");
				c=new Corso(id,nome);
				listac.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listac;
	}
	
}
