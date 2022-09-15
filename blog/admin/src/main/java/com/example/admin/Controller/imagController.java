package com.example.admin.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.admin.PO.image;
import com.example.admin.Service.imageService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

/**
 * @project: blog
 * @ClassName: imagController
 * @author: smallwei
 * @creat: 2022/9/1 19:09
 * 描述:
 */
@Controller
@CrossOrigin
@RestController
@RequestMapping("/image")
public class imagController {
    @Autowired
    imageService imageservice;
    @Autowired
    RestTemplate restTemplate;;
    @ResponseBody
    @RequestMapping("/add")
    public String  add(@RequestParam String articleid,@RequestParam String username, @RequestParam("file")MultipartFile file) throws IOException {
       image image=  imageservice.getImage(articleid);
        if(image==null){
         image=new image();
        image.setUsername(username);
        image.setArticleid(articleid);
            byte[]bytes=   file.getBytes();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("file", file);
            HttpEntity< MultiValueMap<String, Object>> fromEntity = new HttpEntity(requestBody,httpHeaders);
            System.out.print( fromEntity.getBody());
            String result=  restTemplate.postForObject("http://server-common/uploadImg3",bytes,String.class);
            result="http://"+result;
            image.setUrl(result);
            imageservice.add(image);
            return result;
        }else {
            byte[] bytes = file.getBytes();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("file", file);
            HttpEntity<MultiValueMap<String, Object>> fromEntity = new HttpEntity(requestBody, httpHeaders);
            System.out.print(fromEntity.getBody());
            String result = restTemplate.postForObject("http://server-common/uploadImg3", bytes, String.class);
            result = "http://" + result;
            image.setUrl(result);
            imageservice.upser(image);
            return result;
        }



    }
    @ResponseBody
    @RequestMapping("/List")
    public JSONArray List(@RequestBody String username ){
        System.out.print(username);
        JSONObject jsonObject=JSONObject.parseObject(username);
        username= jsonObject.getString("username");
        List<String> articleList=imageservice.ImageList(username);
        JSONArray jsonarray=JSONArray.parseArray(JSON.toJSONString(articleList));
        return jsonarray;
    }
    @ResponseBody
    @RequestMapping("/get")
    public image Get(@RequestBody String articleid){

        return imageservice.getImage(articleid);

    }
}
