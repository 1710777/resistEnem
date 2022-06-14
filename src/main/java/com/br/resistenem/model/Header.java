package com.br.resistenem.model;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public class Header {
	@Id
	public String id;
	@NonNull
	public String fkUsuario;
	@NonNull
	public Boolean status;
	public Header() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFkUsuario() {
		return fkUsuario;
	}
	public void setFkUsuario(String fkUsuario) {
		this.fkUsuario = fkUsuario;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	

	
}
