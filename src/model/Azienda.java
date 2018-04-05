package model;


public class Azienda {

	private int id;
	private String numero;
	private String nome;
	private String comune;
	private String rappresentante;
	private String sede_legale;
	private String sede_operativa;
	private String codice_fiscale;
	private String iva;
	private String email;
	private String pec;
	private String cellulare;
	private String telefono;
	private String ateco;
	private String auditc;
	private String auditt;
	private String note;
	private String nomecorsista;
	private String nomecorso;
	private String datascad;
	private int scaduto;
	private String cognome;
	
	public Azienda(int id,String numero, String nome, String comune, String rappresentante, String sede_legale, String sede_operativa, String codice_fiscale, String iva, String email, String pec, String cellulare, String telefono, String ateco, String auditc, String auditt, String note){
		this.id = id;
		this.numero = numero;
		this.nome = nome;
		this.comune = comune;
		this.rappresentante = rappresentante;
		this.sede_legale = sede_legale;
		this.sede_operativa = sede_operativa;
		this.codice_fiscale = codice_fiscale;
		this.iva = iva;
		this.email = email;
		this.pec = pec;
		this.cellulare = cellulare;
		this.telefono = telefono;
		this.ateco = ateco;
		this.auditc = auditc;
		this.auditt = auditt;
		this.note = note;
	}
	
	public Azienda(int id,String numero, String nome, String comune){
		this.id = id;
		this.numero = numero;
		this.nome = nome;
		this.comune = comune;
		
	}
	
	public Azienda(int id,String numero, String nome, String comune,String nomecorsista,String cognome,String nomecorso,String datascad, int scaduto){
		this.id = id;
		this.numero = numero;
		this.nome = nome;
		this.comune = comune;
		this.nomecorsista=nomecorsista;
		this.nomecorso=nomecorso;
		this.datascad=datascad;
		this.scaduto=scaduto;
		this.cognome=cognome;
		
	}
	
	public Azienda(int id,String numero, String nome, String comune, String auditc, String auditt){
		this.id = id;
		this.numero = numero;
		this.nome = nome;
		this.comune = comune;
		this.auditc = auditc;
		this.auditt = auditt;
	}
	
	
	public Azienda(int id,String numero, String nome, String comune, String auditc){
		this.id = id;
		this.numero = numero;
		this.nome = nome;
		this.comune = comune;
		this.auditc = auditc;
		
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getNumero(){
		return numero;
	}
	
	public void setNumero(String numero){
		this.numero = numero;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getComune(){
		return comune;
	}
	
	public void setComune(String comune){
		this.comune = comune;
	}
	
	public String getCognome(){
		return cognome;
	}
	
	public void setCognome(String cognome){
		this.cognome = cognome;
	}
	
	public String getRappresentante(){
		return rappresentante;
	}
	
	public void setRappresentante(String rappresentante){
		this.rappresentante = rappresentante;
	}
	
	public String getSede_legale(){
		return sede_legale;
	}
	
	public void setSede_legale(String sede_legale){
		this.sede_legale = sede_legale;
	}
	
	public String getSede_operativa(){
		return sede_operativa;
	}
	
	public void setSede_operativa(String sede_operativa){
		this.sede_operativa = sede_operativa;
	}
	
	public String getCodice_fiscale(){
		return codice_fiscale;
	}
	
	public void setCodice_fiscale(String codice_fiscale){
		this.codice_fiscale = codice_fiscale;
	}
	
	public String getIva(){
		return iva;
	}
	
	public void setIva(String iva){
		this.iva = iva;
	}
	
	public String getNomeCorsista(){
		return nomecorsista;
	}
	
	public void setNomeCorsista(String nomecorsista){
		this.nomecorsista = nomecorsista;
	}
	
	public void setNomeCorso(String nomecorso){
		this.nomecorso = nomecorso;
	}
	
	public String getNomeCorso(){
		return nomecorso;
	}
	
	public void setDataScad(String datascad){
		this.datascad = datascad;
	}
	
	public String getDataScad(){
		return datascad;
	}
	
	public void setScaduto(int scaduto){
		this.scaduto = scaduto;
	}
	
	public int getScaduto(){
		return scaduto;
	}
	
	
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getPec(){
		return pec;
	}
	
	public void setPec(String pec){
		this.pec = pec;
	}
	
	public String getCellulare(){
		return cellulare;
	}
	
	public void setCellulare(String cellulare){
		this.cellulare = cellulare;
	}
	
	public String getTelefono(){
		return telefono;
	}
	
	public void setTelefono(String telefono){
		this.telefono = telefono;
	}
	
	public String getAteco(){
		return ateco;
	}
	
	public void setAteco(String ateco){
		this.ateco = ateco;
	}
	
	public String getAuditc(){
		return auditc;
	}
	
	public void setAuditc(String auditc){
		this.auditc = auditc;
	}
	
	public String getAuditt(){
		return auditt;
	}
	
	public void setAuditt(String auditt){
		this.auditt = auditt;
	}
	
	public String getNote(){
		return note;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	
	
	
	
}
