package com.br.resistenem.model;

import org.springframework.data.annotation.Id;

public class TipoSimulado {
	@Id
	public String id;
	public String tipoSimulado;
	public Boolean status;
	public TipoSimulado() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTipoSimulado() {
		return tipoSimulado;
	}
	public void setTipoSimulado(String tipoSimulado) {
		this.tipoSimulado = tipoSimulado;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
