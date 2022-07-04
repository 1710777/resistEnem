package com.br.resistenem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.Materia;


public interface MateriaRepository extends MongoRepository<Materia, String> {
	Materia findAllById(String id);

	List<Materia> findByStatus(boolean b);

	Iterable<Materia> findByAreaStatus(boolean b);

	Materia findMateriaById(String id);
}
