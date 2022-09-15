package com.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admin.PO.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @project: blog
 * @ClassName: UserMapper
 * @author: smallwei
 * @creat: 2022/8/19 13:45
 * 描述:
 */
@Mapper
public interface UserMapper extends BaseMapper<Admin> {
    @Select("select * from Admin where username=#{stuname}")
    Admin getAdminByName(String username);
}
