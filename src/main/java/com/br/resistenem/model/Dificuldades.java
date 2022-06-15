package com.br.resistenem.model;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public class Dificuldades {
	@Id
	public String id;
	@NonNull
	public String dificuldade;
	@NonNull
	public Integer quatidade;
	@NonNull
	public Boolean status;
	public Dificuldades() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dificuldades(String id, String dificuldade, Boolean status) {
		super();
		this.id = id;
		this.dificuldade = dificuldade;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}
	public Integer getQuatidade() {
		return quatidade;
	}
	public void setQuatidade(Integer quatidade) {
		this.quatidade = quatidade;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
