package com.example.servicees.dao;

import com.example.servicees.pojo.article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @project: blog
 * @ClassName: esDao
 * @author: smallwei
 * @creat: 2022/8/12 18:54
 * 描述:
 */
@Repository
public interface esDao extends ElasticsearchRepository<article, String> {

       Optional<article> findById(String id);
    List<article> getAllByTitleAndContent(String title, String content);
    List<article>getAllByTitle(String title);

}
