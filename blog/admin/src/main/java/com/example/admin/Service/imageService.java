package com.example.admin.Service;

import com.example.admin.PO.image;
import com.example.admin.mapper.imagemapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @project: blog
 * @ClassName: imageService
 * @author: smallwei
 * @creat: 2022/9/1 18:15
 * 描述:
 */
@Service
public class imageService {
    @Autowired
    com.example.admin.mapper.imagemapper imagemapper;
     public void  add(image image){
         imagemapper.insert(image);
     }
     public void upser(image image){
         imagemapper.updateById(image);
     }
     public image getImage(String articleid){
         return imagemapper.GetImageByarticleid(articleid);
     }
     public List<String> ImageList(String username){
         return  imagemapper.GetImageByUsername(username);
     }

}
