package com.br.resistenem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.Header;


public interface HeaderRepository extends MongoRepository<Header, String> {
	Header findAllById(String id);
}
