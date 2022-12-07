package com.daolin.demo1234.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daolin.demo1234.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class BigDataServiceImpl implements BigDataService {
    @Autowired
    CompareService compareService;
    @Autowired
    MovieService movieService;

    @Override
    public List<Movie> recommend(String movieList) throws IOException {
        ClassPathResource classpathResource = new ClassPathResource("data/movieMapper.json");
        InputStream inputStream = classpathResource.getInputStream();

//        String filePath ="classes/data/movieMapper.json";
        ObjectMapper mapper = new ObjectMapper();
//        File file = new File(filePath);

        JSONObject jsonObject_ = mapper.readValue(inputStream, JSONObject.class);
        HashMap<String, String> map = new Gson().fromJson(String.valueOf(jsonObject_), HashMap.class);

        String[] split1 = movieList.split("\\|");
        List<String> list = new ArrayList<>();

        for (int i = 0; i < split1.length; i++) {
            list.add(map.get(split1[i]));
        }

        String user = compareService.compare(list);
//        String url = "http://54.189.27.197:3344/{user}";
        String url = "http://54.189.27.197:3344?user={user}&m1={m1}&m2={m2}&m3={m3}&m4={m4}&m5={m5}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user", user);
        RestTemplate restTemplate = new RestTemplate();
        List<Movie> movieList2 = new ArrayList<>();

        try {
            ResponseEntity res = restTemplate.getForEntity(url, String.class, requestBody);
            System.out.println(res.getBody());
            JSONObject jsonObject = JSON.parseObject((String) res.getBody());
            String ans = (String) jsonObject.get("ans");
            String substring = ans.substring(2, ans.length() - 2);
            String[] split = substring.split(" ");
            for (String str : split) {
                System.out.println(str);
            }
            for (String i : split) {
                Optional<Movie> movie = movieService.find(String.valueOf(i));
                movie.ifPresent(movieList2::add);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieList2;
    }
}
