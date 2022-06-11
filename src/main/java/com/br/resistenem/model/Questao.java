package com.br.resistenem.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Questao {
	@Id
	public String id;
	public String idArea;
	public List<Area> areas;
	public Area area;
	public String idDificuldade;
	public List<Dificuldades> listaDificuldades;
	public Dificuldades dificuldade;
	public String pergunta;
	public String explicacao;
	public String dtCriacao;
	public Boolean status;
	
	public Questao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getIdArea() {
		return idArea;
	}

	public void setIdArea(String idArea) {
		this.idArea = idArea;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	public List<Area> getAreas() {
		return areas;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public String getExplicacao() {
		return explicacao;
	}
	public void setExplicacao(String explicacao) {
		this.explicacao = explicacao;
	}
	public String getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(String dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}	
	public String getIdDificuldade() {
		return idDificuldade;
	}
	public void setIdDificuldade(String idDificuldade) {
		this.idDificuldade = idDificuldade;
	}
	public Dificuldades getDificuldades() {
		return dificuldade;
	}

	public void setDificuldade(Dificuldades dificuldade) {
		this.dificuldade = dificuldade;
	}
	public List<Dificuldades> getListaDificuldades() {
		return listaDificuldades;
	}

	public void setListaDificuldades(List<Dificuldades> lstDificuldades) {
		this.listaDificuldades = lstDificuldades;
	}
	
}
