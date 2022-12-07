package com.daolin.demo1234.service;

import com.daolin.demo1234.model.Movie;

import java.util.List;

public interface RandomService {
    List<Movie> recommend(String movieList);
}
