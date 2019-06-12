package br.com.botanica.object;

public class Usuario {
	private int id;
	private String login;
	private String nome;
	private String role;
	
	public int getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public String getNome() {
		return nome;
	}
	public String getRole() {
		return role;
	}

	public Usuario(int id, String login, String nome, String role) {
		super();
		this.id = id;
		this.login = login;
		this.nome = nome;
		this.role = role;
	}

	@Override
	public String toString() {
		return "[" + login + "] (" + role + ")" + nome;
	}
}
