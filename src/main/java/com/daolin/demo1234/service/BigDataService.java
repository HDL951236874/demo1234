package com.daolin.demo1234.service;

import com.daolin.demo1234.model.Movie;

import java.io.IOException;
import java.util.List;

public interface BigDataService {
    List<Movie> recommend(String movieList) throws IOException;
}
