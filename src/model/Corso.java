package model;

public class Corso {
	private int id;
	private String nome;
	private int durata;
	
	public Corso(int id, String nome, int durata){
		this.id=id;
		this.nome=nome;
		this.durata=durata;
	}
	
	public Corso(int id, String nome){
		this.id=id;
		this.nome=nome;
		
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
	
	public void setDurata (int durata) {
		this.durata=durata;
	}

	public int getDurata() {
		return durata;
	}
}
