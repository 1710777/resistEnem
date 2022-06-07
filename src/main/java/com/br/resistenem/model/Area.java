package com.br.resistenem.model;

import org.springframework.data.annotation.Id;

public class Area {

	public String area;
	public Boolean status;
	
	public Area() {

	}
	
	public Area(String area, Boolean status) {
		this.area = area;
		this.status = status;
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
