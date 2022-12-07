package com.daolin.demo1234.service;

import com.alibaba.fastjson.JSONObject;
import com.daolin.demo1234.model.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CompareServiceTest {
    @Autowired
    CompareService compareService;

    @Test
    public void getATest() throws IOException {
        String filePath = "src/main/resources/data/map.json";
        String filePathOrginal = "src/main/resources/data/train.txt";
        Map<String, List<String>> map = new HashMap<>();
        File filename2 = new File(filePath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filePathOrginal));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        while (line != null) {
            try {
                line = br.readLine();
                String[] splitLine = line.split(" ");
                for (int i = 1; i < splitLine.length; i++) {
                    List<String> orDefault = map.getOrDefault(splitLine[i], new ArrayList<String>());
                    orDefault.add(splitLine[0]);
                    map.put(splitLine[i], orDefault);
                }
            } catch (Exception e) {
                continue;
            }

        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filePath), map);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = mapper.readValue(filename2, JSONObject.class);
        HashMap<String, List<String>> map2 = new Gson().fromJson(String.valueOf(jsonObject), HashMap.class);

        System.out.println(1);
    }
}
