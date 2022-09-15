package com.example.admin.Controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.example.admin.PO.Admin;
import com.example.admin.PO.Result;
import com.example.admin.PO.User;
import com.example.admin.Service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @project: blog
 * @ClassName: AdminController
 * @author: smallwei
 * @creat: 2022/8/6 1:58
 * 描述:
 */

@RefreshScope
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    Userservice userservice;
    
    @RequestMapping("/post")
    @SentinelResource(value = "sayHello")
    public String  get2(){
   //  String defult=    restTemplate.getForObject("http://service-es/",String.class);
        String defult=    restTemplate.getForObject("http://service-es/es/get",String.class);
         return defult;
    }
    @RequestMapping("/login")
     @SentinelResource(value="login")
    @ResponseBody
    public Result  login(@RequestBody Admin  user){

        user = userservice.login(user);
        if(user!=null){
        String token= UUID.randomUUID()+"";
        redisTemplate.opsForValue().setIfAbsent(token,user, Duration.ofDays(7));
         return new Result(token,"登录成功",100);
        }
        return new Result(null,"登录失败",104);
    }
    @RequestMapping("/token")
    @ResponseBody
    public  Result loginByToken(@RequestBody String token){

         JSONObject jsonObject=JSONObject.parseObject(token);
        token= jsonObject.getString("token");

        Admin user= (Admin) redisTemplate.opsForValue().get(token);
        System.out.print(user);
        User User = userservice.getUser(user);
         user=  userservice.login(user);
        if(user!=null){
            return new Result(User,"登录成功",100);
        }
        return new Result(null,"登录失败",104);
    }
    @RequestMapping("/getbytoken")
    @ResponseBody
    public User getUserByToken(@RequestBody String token){
        JSONObject jsonObject=JSONObject.parseObject(token);
        token= jsonObject.getString("token");

        Admin user= (Admin) redisTemplate.opsForValue().get(token);
        User User = userservice.getUser(user);
        if(user!=null){
            System.out.print(User);
            return User;
        }
        return new User();
    }
    // User信息
    @RequestMapping("/UpdateUseer")
    @ResponseBody
    public User Update(@RequestBody User user){
        System.out.print(   user);

        if(user!=null){
          user=  userservice.updset(user);
            return user;
        }
        return new User();
    }
    //image
    @RequestMapping("/UpdateImage")
    @ResponseBody
    public String UpdateImage(@RequestParam String username,@RequestParam("file") MultipartFile file) throws IOException {

        User user=userservice.getuser(username);
        if(user==null){
            return null;
        }else{
         byte[]bytes=   file.getBytes();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("file", file);
            HttpEntity< MultiValueMap<String, Object>> fromEntity = new HttpEntity(requestBody,httpHeaders);
           System.out.print( fromEntity.getBody());
            String result=  restTemplate.postForObject("http://server-common/uploadImg3",bytes,String.class);
            result="http://"+result;
             user.setImgeUrl(result);
                userservice.updset(user);
           return result;
        }

    }



}

