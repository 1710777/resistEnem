package com.br.resistenem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.Area;


public interface AreaRepository extends MongoRepository<Area, String> {
	Area findAllById(String id);

	List<Area> findByStatus(boolean b);
}
