package com.br.resistenem.model;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id
	private String id;
	private String nome;
	private String email;
	private String senha;
	
	public User() {
		super();
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Object getUser() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setStatus(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Object getLogin() {
			return getLogin();
	}

	public void setLogin(String login) {
	}

	

}
