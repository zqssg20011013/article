package com.example.servicees;

import com.example.servicees.Controller.esController;
import com.example.servicees.dao.esDao;
import com.example.servicees.pojo.article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ServiceEsApplicationTests {
    @Autowired
    esController controller;
    @Autowired
    ElasticsearchRestTemplate restTemplate;
    @Autowired
    esDao esdao;
    @Test
    void contextLoads() {
  System.out.print(controller.Search(new article(null,null,"",null)));
}}
