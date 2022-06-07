package com.br.resistenem.model;

import org.springframework.data.annotation.Id;

public class Area {

	@Id
	public String id;
	public String area;
	public Boolean status;
	
	public Area() {

	}
	
	public Area(String id, String area, Boolean status) {
		this.id = id;
		this.area = area;
		this.status = status;
	}
	public Area(String area, Boolean status) {
		this.area = area;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
