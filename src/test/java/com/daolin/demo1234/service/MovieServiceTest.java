package com.daolin.demo1234.service;

import com.daolin.demo1234.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MovieServiceTest {
    @Autowired
    MovieService movieService;

    @Test
    public void addMovie() throws IOException {
        String pathname = "src/main/resources/data/u.item";
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        while (line != null) {
            try {
                line = br.readLine();
                String[] splitLine = line.split("\\|");
                Movie movie = Movie.builder().name(splitLine[1]).id(splitLine[0]).build();
                movieService.add(movie);
            } catch (Exception e) {
                continue;
            }

        }
    }
}
