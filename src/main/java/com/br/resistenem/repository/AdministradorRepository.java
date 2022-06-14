package com.br.resistenem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.Administrador;


public interface AdministradorRepository extends MongoRepository<Administrador, String> {
	Administrador findAllById(String id);
	Administrador findByUsuario(String usuario);

}
