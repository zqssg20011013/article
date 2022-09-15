package com.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admin.PO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @project: blog
 * @ClassName: user
 * @author: smallwei
 * @creat: 2022/8/22 15:18
 * 描述:
 */
@Mapper
public interface user extends BaseMapper<User> {
    @Select("select * from user where username=#{username}")
    User GetuserbyUsername(String username);
}
