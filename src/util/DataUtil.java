/**
 * 
 */
package util;


import model.Utente;
import model.Azienda;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import javax.naming.NamingException;
/**
 * @author claudio
 * @author Claudio
 *
 */
public class DataUtil {
	
	
	
	 
	public static String getUsername(String email) throws Exception {
		
		String nome = null;
		
		try{

			 Database.connect();
		        
		        
		           
		         String condition="email='"+email+"'";
		         
		         ResultSet rs =Database.selectRecord("users",condition);
		       
		         while(rs.next()){ 
		          nome= rs.getString("nome");
		        	 
		       }
		                   
		      }catch(NamingException e)
		      {     
		      } catch (SQLException e) {
		      }
		        return nome;  
	}

    public static int checkUser(String username,String pass) throws Exception 
     {
      int st = 0;
      String passw="";
      try{

	 Database.connect();
        if(!isNull(pass)){
            passw=pass;
        }
        
         System.out.println(username);
         System.out.println(st);
         System.out.println(pass);
           
         String condition="username='"+username+"' AND password='"+passw+"'";
         
          System.out.println(condition);
         ResultSet rs =Database.selectRecord("admin",condition);
       while(rs.next()){ 
           st=rs.getInt("id");
       }
        
       

                  
      }catch(NamingException e)
      {     
      } catch (SQLException e) {
      }
        return st;    
    }   
  
  public static Utente getUser(String email) throws Exception{
      Utente utente= new Utente();
      
      try{

            
         Database.connect();
         ResultSet rs= Database.selectRecord("utente", "email="+email);
   
         
         while (rs.next()) {
              
                
                String usermail = rs.getString("email");
                String nome =rs.getString("nome");
                String cognome =rs.getString("cognome");
        
                int gruppi =rs.getInt("idgruppo");
                
                utente= new Utente(usermail,nome,cognome,gruppi);
          }
         
            
          /*     Iterator<Pub> it = lista.iterator();
                while( it.hasNext()){
                    Pub temp= it.next();
                    pubblicazioni.put(temp.getNome(), temp.getDescrizione());
                } */
         
         try {
                Database.close();
            } catch (SQLException ex) {
            }
         
         
      } catch (NamingException ex) {
            System.out.println("ciao1");
        }catch (SQLException ex) {
            System.out.println("ciao1.2");
        }
  
      return utente;
}
  
  
  /**
     * Controllo su String. Contiene solo caratteri alfanumerici?
     * @param toCheck   stringa sul quale effettuare il controllo
     * @param space     se true accetta anche gli spazi.
     * @return          true se la stringa è alfanumerica, false altrimenti.
     */
    public static boolean isAlphanumeric(String toCheck, boolean space){
        if(toCheck.equals("")) return true;
        
        if(space){
            return toCheck.matches("[a-zA-Z' ]+");
        }else{
            return toCheck.matches("[a-zA-Z']+");
        }
        
    }
    
    /**
     * Eliminazione degli spazi esterni e dei doppi spazi interni
     * @param toTrim    stringa da elaborare
     * @return          stringa "pulita"
     */
    public static String spaceTrim(String toTrim){
        return toTrim.trim().replaceAll("\\s+", " ");
    }

    
    /**
     * Cripta una stringa
     * @param string    stringa da criptare
     * @return          stringa criptata
     */
    public static String crypt(String string){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] passBytes = string.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }

    }
    
    /**
     * Verifica se una stringa criptata è stata generata da un'altra stringa
     * @param string_crypted    stringa criptata
     * @param to_check          stringa da verificare
     * @return                  true se la password è stata verificata, false altrimenti
     */
    public static boolean decrypt(String string_crypted, String to_check){
        if(to_check == null || string_crypted == null) return false;
        return string_crypted.equals(crypt(to_check));
    }
    
    public static List<Azienda> search(String inputa, String inputc) throws Exception {

        List<Azienda> result = new ArrayList<Azienda>();

        Database.connect();
        //System.out.println(input);

        try {
            String condition = " nome LIKE '%" + inputa + "%' OR comune LIKE '%" + inputc + "%'";
            //System.out.println(condition);

            ResultSet record = Database.selectRecord("azienda", condition);
            while (record.next()) {

                String nome = record.getString("nome");
                String comune = record.getString("comune");
                String numero = record.getString("numero");
                int id = record.getInt("id");
             

                Azienda k = new Azienda(id,numero,nome,comune);
                result.add(k);
            }
            Database.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return result;
    }
    
    
    
    public static List<Azienda> searchaz(String input) throws Exception {

        List<Azienda> result = new ArrayList<Azienda>();

        Database.connect();
        //System.out.println(input);

        try {
            String condition = " nome LIKE '%" + input + "%'";
            //System.out.println(condition);

            ResultSet record = Database.selectRecord("azienda", condition);
            while (record.next()) {
            	
            	String auditc=record.getString("auditc");
            	String auditt=record.getString("auditt");
                String nome = record.getString("nome");
                String comune = record.getString("comune");
                String numero = record.getString("numero");
                int id = record.getInt("id");
               

                Azienda k = new Azienda(id,numero,nome,comune,auditc,auditt);
                result.add(k);
            }
            Database.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return result;
    }
    
    
    public static List<Azienda> searchcom(String input) throws Exception {

        List<Azienda> result = new ArrayList<Azienda>();

        Database.connect();
        //System.out.println(input);

        try {
            String condition = " comune LIKE '%" + input + "%'";
            //System.out.println(condition);

            ResultSet record = Database.selectRecord("azienda", condition);
            while (record.next()) {

                String nome = record.getString("nome");
                String comune = record.getString("comune");
                String numero = record.getString("numero");
                int id = record.getInt("id");
               

                Azienda k = new Azienda(id,numero,nome,comune);
                result.add(k);
            }
            Database.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return result;
    }
    
}
