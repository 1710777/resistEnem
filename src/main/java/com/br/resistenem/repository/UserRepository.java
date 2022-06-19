package com.br.resistenem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.User;

public interface UserRepository extends MongoRepository<User, String> {
   User findAllById(String id);

User findByEmail(Object user);
}
