package com.daolin.demo1234;

import com.daolin.demo1234.model.Movie;
import com.daolin.demo1234.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.PostConstruct;
import java.io.*;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Demo1234Application {
    @Autowired
    MovieService movieService;

    public static void main(String[] args) {
        SpringApplication.run(Demo1234Application.class, args);
    }

//    @PostConstruct
//    private void init() throws IOException {
//        System.out.println("Grpc server start ================>");
//        String pathname = "src/main/resources/data/u.item";
//        File filename = new File(pathname);
//        InputStreamReader reader = new InputStreamReader(
//                new FileInputStream(filename));
//        BufferedReader br = new BufferedReader(reader);
//        String line = "";
//        while (line != null) {
//            try {
//                line = br.readLine();
//                String[] splitLine = line.split("\\|");
//                Movie movie = Movie.builder().name(splitLine[1]).build();
//                movieService.add(movie);
//            } catch (Exception e) {
//                continue;
//            }
//
//        }
//    }

}
