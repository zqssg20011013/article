package com.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admin.PO.User;
import com.example.admin.PO.image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @project: blog
 * @ClassName: imagemapper
 * @author: smallwei
 * @creat: 2022/9/1 18:11
 * 描述:
 */
@Mapper
public interface imagemapper extends BaseMapper<image> {
    @Select("select * from image where articleid=#{articleid}")
    image GetImageByarticleid(String  articleid);
    @Select("select url from image where username=#{username}")
    List<String> GetImageByUsername(String username);
}
