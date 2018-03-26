package model;

public class Admin {
	
	private int id;
	private String nome;
	private String username;
	private String password;
	
	public Admin(int id, String nome, String cognome){
		this.id = id;
		this.nome = nome;
		this.username = username;
		this.password=password;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setCognome(String password){
		this.password = password;
	}

}
