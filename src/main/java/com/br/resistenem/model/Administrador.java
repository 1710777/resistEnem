package com.br.resistenem.model;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public class Administrador {
	@Id
	public String id;
	@NonNull
	public String nome;
	@NonNull
	public String email;
	@NonNull
	public String usuario;
	@NonNull
	public String senha;
	@NonNull
	public Boolean status;
	
	public Administrador() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
