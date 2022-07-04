package com.br.resistenem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.Materia;
import com.br.resistenem.model.TipoSimulado;


public interface TipoSimuladoRepository extends MongoRepository<TipoSimulado, String> {
	TipoSimulado findAllById(String id);
	TipoSimulado findTipoSimuladoById(String id);
	Materia findMateriaById(String id);
}
