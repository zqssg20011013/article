package com.example.servercommon.controler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.shaded.com.google.gson.JsonArray;
import com.example.servercommon.pojo.article;
import com.example.servercommon.pojo.result;
import com.example.servercommon.service.dbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @project: blog
 * @ClassName: commonController
 * @author: smallwei
 * @creat: 2022/8/12 7:32
 * 描述:
 */
@CrossOrigin
@Controller
@RequestMapping("/common")
public class commonController {
    @Autowired
    dbService dbservice;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @ResponseBody
    @RequestMapping("/save")
    public result save(@RequestBody article article) {
        dbservice.add(article);
        String entity = restTemplate.postForObject("http://service-es/es/save", article, String.class);
        return new result(entity,"添加成功",200);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public void delete(@RequestBody List<article> articleList) {
        dbservice.delete(articleList);
        restTemplate.postForObject("http://service-es/es/delete", articleList, String.class);
    }
    @ResponseBody
    @RequestMapping("/deleteById")
    public void DeleteById(@RequestBody String id) {
        JSONObject jsonObject= JSONObject.parseObject(id);
        id= jsonObject.getString("id");
        System.out.print(id);
        dbservice.DeleteById(id);
        restTemplate.postForObject("http://service-es/es/deleteById", id, String.class);
    }
    @ResponseBody
    @RequestMapping("/search")
    public List<article> search(@RequestBody article article) {
        List<article>articleList= (List<com.example.servercommon.pojo.article>) restTemplate.postForObject("http://service-es/es/search", article,Object.class);
        return articleList;
}
    @ResponseBody
    @RequestMapping("/searchbytitileandcontent")
    public List<article>searchbytitileandcontent(@RequestBody article article){
        List<article>articleList= (List<com.example.servercommon.pojo.article>) restTemplate.postForObject("http://service-es/es/searchbytitleandcontent", article,Object.class);
        return articleList;
    }
    @ResponseBody
    @RequestMapping("/ListById")
    public JSONArray List(@RequestBody String id ){
        JSONObject jsonObject=JSONObject.parseObject(id);
        id= jsonObject.getString("id");
        List<article>articleList=dbservice.GetByUserId(id);
        JSONArray jsonarray=JSONArray.parseArray(JSON.toJSONString(articleList));
        return jsonarray;
    }
    @ResponseBody
    @RequestMapping("/recommend")
    public result Recommend(@RequestBody String id){
        JSONObject jsonObject=JSON.parseObject(id);
        id= jsonObject.getString("id");
        redisTemplate.opsForList().rightPush("recommend",dbservice.getById(id));
      Object object=  restTemplate.postForObject("http://admin/image/get",id,Object.class);
      System.out.print(object);
        redisTemplate.opsForList().rightPush("recommendImage",restTemplate.postForObject("http://admin/image/get",id,Object.class));

        return new result(null,"推荐成功",200);
    }
    @RequestMapping("/concelrecommend")
   public result Concelrecommend(@RequestBody String id){
        JSONObject jsonObject=JSON.parseObject(id);
        id= jsonObject.getString("id");
        dbservice.getById(id).setRecomend(false);
        redisTemplate.opsForList().remove("recommend",0,dbservice.getById(id));

        redisTemplate.opsForList().remove("recommendImage",0,restTemplate.postForObject("http://admin/image/get",id,Object.class));

        return new result(null,"取消推荐成功",200);
    }
    @ResponseBody
    @RequestMapping("/ListAll")
    public JSONArray ListRecommend(){
        List list=  redisTemplate.opsForList().range("recommend",0,-1);
        JSONArray jsonarray=JSONArray.parseArray(JSON.toJSONString(list));
        return jsonarray;
    }
    //image
    @ResponseBody
    @RequestMapping("/ListAllimage")
    public JSONArray ListRecommendImage(){
        List list=  redisTemplate.opsForList().range("recommendImage",0,-1);
        JSONArray jsonarray=JSONArray.parseArray(JSON.toJSONString(list));
        return jsonarray;
    }
    @ResponseBody
    @RequestMapping("/Search")
    public JSONArray search(@RequestBody String title){
        JSONObject jsonObject=JSON.parseObject(title);
        title= jsonObject.getString("title");
        System.out.print(title);
      article article= new article();
      article.setTitle(title);
        List<article>articleList= (List<com.example.servercommon.pojo.article>) restTemplate.postForObject("http://service-es/es/search", article,Object.class);
        JSONArray jsonarray=JSONArray.parseArray(JSON.toJSONString(articleList));
        return jsonarray;
    }

}