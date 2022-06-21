package com.br.resistenem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.ConfiguracaoSimulado;


public interface ConfiguracaoSimuladoRepository extends MongoRepository<ConfiguracaoSimulado, String> {
	ConfiguracaoSimulado findAllById(String id);
	ConfiguracaoSimulado findByFkIdDificuldadeAndFkIdTipoSimulado(String fkIdDificuldade, String fkIdTipoSimulado);
}
