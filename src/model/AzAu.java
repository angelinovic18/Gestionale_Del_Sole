package model;

public class AzAu {
	private int id;
	private int idazienda;
	private int idaudit;
	private int datascad;
	
	public AzAu (int id, int idazienda, int idaudit, int datascad){
		this.id=id;
		this.idazienda=idazienda;
		this.idaudit=idaudit;
		this.datascad=datascad;
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
	
	public void setIdAudit (int idaudit) {
		this.idaudit=idaudit;
	}

	public int getIdAudit() {
		return idaudit;
	}
	
	public void setDataScad (int datascad) {
		this.datascad=datascad;
	}

	public int getDataScad() {
		return datascad;
	}
}
