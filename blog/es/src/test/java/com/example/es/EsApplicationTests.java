package com.example.es;

import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

@SpringBootTest
class EsApplicationTests {
       @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Test
    void contextLoads() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId("2");
        bookEntity.setAuthor("老李");
        bookEntity.setName("一起学习es");

        elasticsearchRestTemplate.createIndex("book_index");
    }

}
