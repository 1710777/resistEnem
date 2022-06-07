package com.br.resistenem.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.Area;


public interface AreaRepository extends MongoRepository<Area, String> {
	Area findByArea(String area);
}
