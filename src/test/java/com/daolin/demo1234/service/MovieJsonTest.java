package com.daolin.demo1234.service;

import com.alibaba.fastjson.JSONObject;
import com.daolin.demo1234.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MovieJsonTest {
    @Test
    public void movieJsonTest() throws IOException {
        String pathname = "src/main/resources/data/u.item";
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        Map<String, String> map = new HashMap<>();
        while (line != null) {
            try {
                line = br.readLine();
                String[] splitLine = line.split("\\|");
                map.put(splitLine[1],splitLine[0]);
            } catch (Exception e) {
                continue;
            }

        }
        String filePath ="src/main/resources/data/movieMapper.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filePath), map);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        File filename2 = new File(filePath);
        File filename2 = new File(filePath);

        JSONObject jsonObject = mapper.readValue(filename2, JSONObject.class);
        HashMap<String, String> map2 = new Gson().fromJson(String.valueOf(jsonObject), HashMap.class);
        System.out.println(1);

    }
}
