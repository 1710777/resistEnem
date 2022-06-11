package com.br.resistenem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.Dificuldades;


public interface DificuldadesRepository extends MongoRepository<Dificuldades, String> {
	Dificuldades findAllById(String id);
}
