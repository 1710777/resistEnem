package com.br.resistenem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.Alternativa;
import com.br.resistenem.model.Questao;


public interface QuestaoRepository extends MongoRepository<Questao, String> {
	Questao findAllById(String id);
}
