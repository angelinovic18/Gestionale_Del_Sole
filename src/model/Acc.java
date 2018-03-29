package model;

public class Acc {

	private int id;
	private int data_inizio;
	private int id_corsista;
	private int id_corso;
	
	public Acc(int id, int data_inizio, int id_corsista, int id_corso){
		this.id = id;
		this.data_inizio = data_inizio;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getData_inizio(){
		return data_inizio;
	}
	
	public void setData_inizio(int data_inizio){
		this.data_inizio = data_inizio;
	}
	
	public int getId_corsista(){
		return id_corsista;
	}
	
	public void setId_corsista(int id_corsista){
		this.id_corsista = id_corsista;
	}
	
	public int getId_azienda_corso(){
		return id_corso;
	}
	
	public void setId_azienda_corso(int id_corso){
		this.id_corso = id_corso;
	}
}
