package com.daolin.demo1234.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BigDataServiceTest {
    @Autowired
    BigDataService bigDataService;

    @Test
    public void recommend2Test() throws IOException {
        bigDataService.recommend("100");
    }
}
