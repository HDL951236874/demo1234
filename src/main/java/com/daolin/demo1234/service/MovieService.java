package com.daolin.demo1234.service;

import com.daolin.demo1234.model.Movie;

import java.util.Optional;

public interface MovieService {
    void add(Movie movie);

    void delete(String id);

    Optional<Movie> find(String id);
}
