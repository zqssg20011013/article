package com.example.servicees.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @project: blog
 * @ClassName: article
 * @author: smallwei
 * @creat: 2022/8/12 18:51
 * 描述:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName="article",shards=3)
public class article {
    @Id
    @Field(type = FieldType.Text)
    private String id;
    @Field(type = FieldType.Text)
    private String  userid;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String  title;
    @Field(type = FieldType.Text)
    private String  content;
}
