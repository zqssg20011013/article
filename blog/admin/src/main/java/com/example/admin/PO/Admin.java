package com.example.admin.PO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @project: blog
 * @ClassName: Admin
 * @author: smallwei
 * @creat: 2022/8/19 13:18
 * 描述:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("person")
public class Admin implements Serializable {
    private String username;
    private String password;
}
