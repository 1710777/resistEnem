package com.br.resistenem.model;

import org.springframework.data.annotation.Id;

public class ConfiguracaoSimulado {
	@Id
	public String id;
	public Integer quantidade;
	public Dificuldades dificuldades;
	public TipoSimulado tipoSimulado;
	public Materia materia;


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

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

}
