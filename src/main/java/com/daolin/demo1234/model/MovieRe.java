package com.daolin.demo1234.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRe extends MongoRepository<Movie, String> {

}
