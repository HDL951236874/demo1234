package com.daolin.demo1234.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class CompareServiceImpl implements CompareService {
    @Override
    public String compare(List<String> list) throws IOException {
        ClassPathResource classpathResource = new ClassPathResource("data/map.json");
        InputStream inputStream = classpathResource.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = mapper.readValue(inputStream, JSONObject.class);
        HashMap<String,List<String>> map = new Gson().fromJson(String.valueOf(jsonObject),
                HashMap.class);
        Map<String, Integer> map2 = new HashMap<>();
        for (String s : list) {
            List<String> temp2 = map.get(s);
            for (int i = 0; i < temp2.size(); i++) {
                if (map2.containsKey(temp2.get(i))) {
                    map2.put(temp2.get(i), map2.get(temp2.get(i)) + 1);
                } else {
                    map2.put(temp2.get(i), 1);
                }
            }
        }
        int max_num = 0;
        String res = null;
        for (String str : map2.keySet()) {
            if (map2.get(str) > max_num) {
                max_num = map2.get(str);
                res = str;
            }
        }
        return res;
    }
}
