package com.example.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: blog
 * @ClassName: BookEntity
 * @author: smallwei
 * @creat: 2022/8/13 11:47
 * 描述:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    private String author;
    private String name;
    private String id;
}
