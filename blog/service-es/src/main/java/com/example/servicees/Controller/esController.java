package com.example.servicees.Controller;

import com.example.servicees.pojo.article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @project: blog
 * @ClassName: esController
 * @author: smallwei
 * @creat: 2022/8/6 16:45
 * 描述:
 */
@RefreshScope
@CrossOrigin
@RestController
@RequestMapping("/es")
public class esController {
    @Autowired
    com.example.servicees.service.esService esService;
    @RequestMapping("/get")
     public String get(){
        return "HelloWallet";
     }
    @RequestMapping("/save")
     public  String save(@RequestBody article article){
        esService.save(article);
        System.out.print(article);
        return "ok";
     }
     @RequestMapping("/search")
     public List<article>  Search(@RequestBody article article){
     List<article> articleList= esService.Search(article.getTitle());
      return articleList;
     }
     @RequestMapping("/searchbytitleandcontent")
     public List<article> searchbytitleandcontent(@RequestBody article article){
         List<article> articleList=   esService.searchbytitleandcontent(article.getTitle(),article.getContent());
        return articleList;
     }
     @RequestMapping ("/delete")
    public void delete(@RequestBody  List<article> articleList){
        esService.delete(articleList);
     }
    @RequestMapping ("/deleteById")
    public void delete(@RequestBody  String id){
        esService.deleteById(id);
    }
    @RequestMapping ("/savaall")
    public void saveall(@RequestBody  List<article> articleList){
        esService.saveall(articleList);
    }

}
