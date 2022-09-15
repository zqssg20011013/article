package com.example.admin.PO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

/**
 * @project: blog
 * @ClassName: User
 * @author: smallwei
 * @creat: 2022/8/22 15:03
 * 描述:
 */
@Data
@AllArgsConstructor
@TableName("user")
@NoArgsConstructor
public class User implements Serializable{
           @TableField("username")
           @TableId
           private String username;
           @TableField("name")
           private String name;
           @TableField("date")
           @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
           @DateTimeFormat(pattern = "yyyy-MM-dd ")
           private Date date;
           @TableField("sex")
          private String sex;
           @TableField("article")
           private String article;
            @TableField("imgeUrl")
            private String imgeUrl;
}
