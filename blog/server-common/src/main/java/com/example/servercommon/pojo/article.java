package com.example.servercommon.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @project: blog
 * @ClassName: article
 * @author: smallwei
 * @creat: 2022/8/12 7:32
 * 描述:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class article implements Serializable {
    @Id
    private String  id;
    private String  userid;
    private String  title;
    private String  content;
    private String  type;
    private Boolean recomend;


}
