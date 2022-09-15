package com.example.admin.PO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: blog
 * @ClassName: Result
 * @author: smallwei
 * @creat: 2022/8/19 15:31
 * 描述:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Object data;
    private String message;
    private int  code;
}
