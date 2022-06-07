package com.br.resistenem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.resistenem.model.Area;
import com.br.resistenem.repository.AreaRepository;



@Service
public class AreaService {
	
	@Autowired
	private AreaRepository araRepository;
	
	public void save(Area Area) {
		araRepository.save(Area);
	}

}
