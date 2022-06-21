package com.br.resistenem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.Dificuldades;


public interface DificuldadesRepository extends MongoRepository<Dificuldades, String> {
	Dificuldades findAllById(String id);
	Dificuldades findDificuldadesById(String fkIdDificuldade);
}
