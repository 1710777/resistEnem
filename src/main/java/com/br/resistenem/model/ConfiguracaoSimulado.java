package com.br.resistenem.model;

import org.springframework.data.annotation.Id;

public class ConfiguracaoSimulado {
	@Id
	public String id;
	public String fkIdTipoSimulado;
	public String fkIdMateria;
	public String fkIdDificuldade;
	public Integer quantidade;
	public Dificuldades dificuldades;
	public TipoSimulado tipoSimulado;


	public ConfiguracaoSimulado() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ConfiguracaoSimulado(String id, String fkIdTipoSimulado, String fkIdDificuldade, Integer quantidade) {
		super();
		this.id = id;
		this.fkIdTipoSimulado = fkIdTipoSimulado;
		this.fkIdDificuldade = fkIdDificuldade;
		this.quantidade = quantidade;
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
	public String getFkIdDificuldade() {
		return fkIdDificuldade;
	}
	public void setFkIdDificuldade(String fkIdDificuldade) {
		this.fkIdDificuldade = fkIdDificuldade;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Dificuldades getDificuldades() {
		return dificuldades;
	}

	public void setDificuldades(Dificuldades dificuldades) {
		this.dificuldades = dificuldades;
	}

	public TipoSimulado getTipoSimulado() {
		return tipoSimulado;
	}

	public void setTipoSimulado(TipoSimulado tipoSimulado) {
		this.tipoSimulado = tipoSimulado;
	}

	public String getFkIdMateria() {
		return fkIdMateria;
	}

	public void setFkIdMateria(String fkIdMateria) {
		this.fkIdMateria = fkIdMateria;
	}

}
