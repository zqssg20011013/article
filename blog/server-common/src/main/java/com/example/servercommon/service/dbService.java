package com.example.servercommon.service;

import cn.hutool.db.sql.Query;
import com.example.servercommon.dao.mongodbDAO;
import com.example.servercommon.pojo.article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @project: blog
 * @ClassName: dbService
 * @author: smallwei
 * @creat: 2022/8/12 7:51
 * 描述:
 */
@Service
public class dbService {
    @Autowired
    com.example.servercommon.dao.mongodbDAO mongodbDAO;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;
    public void add(article article){
        String lockKey = "lockkey";
        try {
            boolean result=redisTemplate.opsForValue().setIfAbsent(lockKey,"db-lock");
            mongodbDAO.save(article);
        }finally {
            redisTemplate.delete(lockKey);
        }
    }
    public void delete(List<article> article){
        String lockKey = "lockkey";
        try {
            boolean result=redisTemplate.opsForValue().setIfAbsent(lockKey,"db-lock");
            mongodbDAO.deleteAll(article);

        }finally {
            redisTemplate.delete(lockKey);
        }
    }
    public List<article > GetByUserId(String Userid){
        return  mongodbDAO.getAllByUserid(Userid);
    }
    public List<article> GetByTitle(String Title){
        return mongodbDAO.getAllByTitle(Title);
    }
    public void DeleteById(String id){
         mongodbDAO.deleteById(id);
    }
    public article getById(String id){
        return mongodbDAO.getById(id);
    }
   public void Update(article article){
   }

}
