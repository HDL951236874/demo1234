package com.daolin.demo1234.controller;

import com.alibaba.fastjson.JSONObject;
import com.daolin.demo1234.model.Movie;
import com.daolin.demo1234.service.BigDataService;
import com.daolin.demo1234.service.MovieService;
import com.daolin.demo1234.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MovieController {
    @Autowired
    MovieService movieService;

    @Autowired
    RandomService randomService;

    @Autowired
    BigDataService bigDataService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/recommend")
    public String recommend(@RequestParam("m1") String m1,
                            @RequestParam("m2") String m2,
                            @RequestParam("m3") String m3,
                            @RequestParam("m4") String m4,
                            @RequestParam("m5") String m5, Model model) {
        List<Movie> recommend = randomService.recommend(m1 + m2 + m3 + m4 + m5);
        System.out.println(m1);
        System.out.println(recommend);
        model.addAttribute("recommends", recommend);
        return "index";
    }

    @RequestMapping("/recommend2")
    public String recommend2(@RequestParam("m1") String m1,
                             @RequestParam("m2") String m2,
                             @RequestParam("m3") String m3,
                             @RequestParam("m4") String m4,
                             @RequestParam("m5") String m5, Model model) throws IOException {
        List<Movie> recommend = new ArrayList<>();
        try {
            recommend = bigDataService.recommend(m1 + "|" + m2 + "|" + m3 + "|" + m4 + "|" + m5);
        } catch (Exception e){
            recommend = randomService.recommend(m1 + m2 + m3 + m4 + m5);
        }
        System.out.println(m1);
        System.out.println(recommend);
        model.addAttribute("recommends", recommend);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/recommendation", method = RequestMethod.GET)
    public JSONObject recommend3(@RequestParam("m1") String m1,
                             @RequestParam("m2") String m2,
                             @RequestParam("m3") String m3,
                             @RequestParam("m4") String m4,
                             @RequestParam("m5") String m5) throws IOException {
        List<Movie> recommend = new ArrayList<>();
        try {
            recommend = bigDataService.recommend(m1 + "|" + m2 + "|" + m3 + "|" + m4 + "|" + m5);
        } catch (Exception e){
            recommend = randomService.recommend(m1 + m2 + m3 + m4 + m5);
        }
        JSONObject map = new JSONObject();
        map.put("m1", recommend.get(0));
        map.put("m2",  recommend.get(1));
        map.put("m3",  recommend.get(2));
        map.put("m4",  recommend.get(3));
        map.put("m5",  recommend.get(4));
        return map;
    }

}
