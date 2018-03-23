package model;

public class AzCo {
	private int id;
	private int idazienda;
	private int idcorso;
	
	public AzCo(int id, int idazienda, int idcorso){
		this.id=id;
		this.idazienda=idazienda;
		this.idcorso=idcorso;
	}
	
	public void setId (int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}
	
	public void setIdAzienda (int idazienda) {
		this.idazienda=idazienda;
	}

	public int getIdAzienda() {
		return idazienda;
	}
	
	public void setIdCorso (int idcorso) {
		this.idcorso=idcorso;
	}

	public int getIdCorso() {
		return idcorso;
	}
}
