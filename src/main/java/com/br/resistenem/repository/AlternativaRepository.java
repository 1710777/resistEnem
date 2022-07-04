package com.br.resistenem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.Alternativa;

public interface AlternativaRepository extends MongoRepository<Alternativa, String> {
	Alternativa findAllById(String id);
	List<Alternativa> findAllByIdQuestao(String id);
	Alternativa findAllByRespostaCorretaAndIdQuestao(boolean respostaCorreta, String idQuestao);
}
