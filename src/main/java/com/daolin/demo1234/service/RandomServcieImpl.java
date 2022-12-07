package com.daolin.demo1234.service;

import com.daolin.demo1234.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RandomServcieImpl implements RandomService {
    @Autowired
    MovieService movieService;

    @Override
    public List<Movie> recommend(String movieList) {
        Random ran = new Random();
        Set<Integer> hs = new HashSet<>();
        for (; ; ) {
            int tmp = ran.nextInt(1682) + 1;
            hs.add(tmp);
            if (hs.size() == 5) break;
        }
        List<Movie> list = new ArrayList<>();
        for (Integer i : hs) {
            Optional<Movie> movie = movieService.find(String.valueOf(i));
            movie.ifPresent(list::add);
        }
        return list;
    }
}
