package com.example.admin.Service;

import com.example.admin.PO.Admin;
import com.example.admin.PO.Result;
import com.example.admin.PO.User;
import com.example.admin.mapper.UserMapper;
import com.example.admin.mapper.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.UUID;

/**
 * @project: blog
 * @ClassName: Userservice
 * @author: smallwei
 * @creat: 2022/8/19 13:49
 * 描述:
 */
@Service
@Transactional
public class Userservice {
    @Autowired
    UserMapper usermapper;
    @Autowired
    com.example.admin.mapper.user users;
    @Autowired
    RedisTemplate redisTemplate;
    public Admin login(Admin user){
        if(user.getUsername().equals(usermapper.getAdminByName(user.getUsername()).getUsername())&&user.getPassword()
                .equals(usermapper.getAdminByName(user.getUsername()).getPassword())){
            return user;
        }
        return null;
    }
    public User getUser(Admin user){
    return  users.GetuserbyUsername(user.getUsername());
    }
    public User  updset(User user){
        users.updateById(user);
        return users.GetuserbyUsername(user.getUsername());
    }
    public User getuser(String username){
       return  users.GetuserbyUsername(username);
    }


}
