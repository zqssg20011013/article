package com.example.servercommon;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @project: blog
 * @ClassName: result
 * @author: smallwei
 * @creat: 2022/8/15 20:30
 * 描述:
 */
@Data
@AllArgsConstructor
public class result {
    private Integer state;
    private String Exception;
}
