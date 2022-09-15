package com.example.servercommon.dao;

import com.example.servercommon.pojo.article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @project: blog
 * @ClassName: mongodbDAO
 * @author: smallwei
 * @creat: 2022/8/12 7:44
 * 描述:
 */

public interface mongodbDAO extends MongoRepository<article, String> {
    article getAllById(String id);
    List<article> getAllByTitleAndContent(String title,String content);
    List<article> getAllByTitle(String title);
    List<article> getAllByUserid(String Userid);
    article getById(String id);
    void deleteById(String id);

}
