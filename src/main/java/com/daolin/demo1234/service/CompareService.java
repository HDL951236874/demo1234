package com.daolin.demo1234.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.catalina.LifecycleState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CompareService {
    String compare(List<String> list) throws IOException;
}
