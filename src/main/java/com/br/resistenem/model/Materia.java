package com.br.resistenem.model;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public class Materia {
	@Id
	public String id;
	public String fkIdArea;
	public String Materia;
	public Boolean status;
	
	public Materia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFkIdArea() {
		return fkIdArea;
	}

	public void setFkIdArea(String fkIdArea) {
		this.fkIdArea = fkIdArea;
	}

	public String getMateria() {
		return Materia;
	}

	public void setMateria(String materia) {
		Materia = materia;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
