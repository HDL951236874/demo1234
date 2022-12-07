package com.daolin.demo1234.service;

import com.daolin.demo1234.model.Movie;
import com.daolin.demo1234.model.MovieRe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    MovieRe movieRe;

    @Override
    public void add(Movie movie) {
        movieRe.save(movie);
    }

    @Override
    public void delete(String id) {
        movieRe.deleteById(id);
    }

    @Override
    public Optional<Movie> find(String id) {
        return movieRe.findById(id);
    }
}
