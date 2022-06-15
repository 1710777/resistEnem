package com.br.resistenem.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class ConfiguracaoSimulado {
	@Id
	public String id;
	public String fkIdTipoSimulado;
	public List<Dificuldades> dificuldades;
	public Boolean status;
	public ConfiguracaoSimulado() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFkIdTipoSimulado() {
		return fkIdTipoSimulado;
	}
	public void setFkIdTipoSimulado(String fkIdTipoSimulado) {
		this.fkIdTipoSimulado = fkIdTipoSimulado;
	}
	public List<Dificuldades> getDificuldades() {
		return dificuldades;
	}
	public void setDificuldades(List<Dificuldades> dificuldades) {
		this.dificuldades = dificuldades;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}	
	
	
}
