package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Corsista;
import util.Database;

public class CorsistiDAO {
	public static List<Corsista> corsisti(int ida) throws Exception{
		Corsista b=null;
				
				List<Corsista> listaco=new ArrayList<Corsista>();

				ResultSet corsistic;
				try {
					Database.connect();
					corsistic = Database.selectRecord("corsista,azienda,acc,corso","azienda.id=" + ida + " AND corsista.idazienda=azienda.id AND acc.id_corsista=corsista.id AND acc.idcorso=corso.id");
					while(corsistic.next()){
						int idcc=corsistic.getInt("corsista.id");
						System.out.println(idcc + "idddddddd");
						String nomec=corsistic.getString("corsista.nome");
						System.out.println(nomec + "nomeeeeeeee");
						String cognome=corsistic.getString("corsista.cognome");
						int idcorso=corsistic.getInt("idcorso");
						System.out.println(idcorso + "idcorsoooo");
						b=new Corsista(idcc,nomec,cognome,idcorso);
						listaco.add(b);
								
					
				}
				Database.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
				return listaco;
			}
}
