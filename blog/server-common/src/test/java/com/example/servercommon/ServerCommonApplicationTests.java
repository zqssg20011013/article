package com.example.servercommon;

import com.example.servercommon.Utills.QiniuCloudUtil;
import com.example.servercommon.controler.commonController;
import com.example.servercommon.dao.mongodbDAO;
import com.example.servercommon.pojo.article;
import com.example.servercommon.service.dbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
class ServerCommonApplicationTests {
    @Autowired
    commonController controller;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {


    }
}