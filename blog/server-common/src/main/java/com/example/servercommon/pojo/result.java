package com.example.servercommon.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: blog
 * @ClassName: result
 * @author: smallwei
 * @creat: 2022/8/23 22:27
 * 描述:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class result {
    private    Object data;
    private String  message;
    private  int  code;
}
