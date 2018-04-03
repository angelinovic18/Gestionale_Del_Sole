package model;

public class Corsista {
	private int id;
	private String nome;
	private String cognome;
	private int idcorso;
	private String data_inizio;
	
	public Corsista(int id, String nome, String cognome){
		this.id=id;
		this.nome=nome;
		this.cognome=cognome;
	}
	
	public Corsista(int id, String nome, String cognome, int idcorso){
		this.id=id;
		this.nome=nome;
		this.cognome=cognome;
		this.idcorso=idcorso;
	}
	
	public Corsista(int id, String nome, String cognome, int idcorso, String datainizio){
		this.id=id;
		this.nome=nome;
		this.cognome=cognome;
		this.idcorso=idcorso;
		this.data_inizio=datainizio;
	}
	
	

	public void setId (int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}
	
	public void setNome (String nome) {
		this.nome=nome;
	}

	public String getNome() {
		return nome;
	}
	
	public void setCognome (String cognome) {
		this.cognome=cognome;
	}

	public String getCognome() {
		return cognome;
	}
	
	public void setDataIni (String datainizio) {
		this.data_inizio=datainizio;
	}

	public String getDataIni() {
		return data_inizio;
	}
	
	public void setIdCorso (int idcorso) {
		this.idcorso=idcorso;
	}

	public int getIdCorso() {
		return idcorso;
	}
}
