package com.example.admin.PO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @project: blog
 * @ClassName: image
 * @author: smallwei
 * @creat: 2022/9/1 18:09
 * 描述:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("image")
public class image implements Serializable {
    @TableField("id")
    private int id;
    @TableField("username")
    private String username;
    @TableField("articleid")
    private String articleid;
    @TableField("url")
    private String url;
}
