package com.example.servercommon.controler;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.servercommon.Utills.QiniuCloudUtil;
import com.example.servercommon.Utills.UploadGiteeImgBed;
import com.example.servercommon.pojo.result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @project: blog
 * @ClassName: UPloadController
 * @author: smallwei
 * @creat: 2022/8/27 17:30
 * 描述:
 */

@RestController
@Slf4j
public class UploadController {

    /**
     *  上传图片
     * @param multipartFile 文件对象
     * @return
     * @throws IOException
     */
        @PostMapping("/uploadImg")
        public void uploadImg(@RequestParam("file")MultipartFile multipartFile) throws IOException {

            log.info("uploadImg()请求已来临...");
            //根据文件名生成指定的请求url
            String originalFilename = multipartFile.getOriginalFilename();

            String targetURL = UploadGiteeImgBed.createUploadFileUrl(originalFilename);
            log.info("目标url："+targetURL);
            //请求体封装
            Map<String, Object> uploadBodyMap = UploadGiteeImgBed.getUploadBodyMap(multipartFile.getBytes());
            //借助HttpUtil工具类发送POST请求
            String JSONResult = HttpUtil.post(targetURL, uploadBodyMap);
            //解析响应JSON字符串
            cn.hutool.json.JSONObject jsonObj = JSONUtil.parseObj(JSONResult);
            //请求失败

            //请求成功：返回下载地址
            JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));

        }

    @ResponseBody
    @RequestMapping(value = "/uploadImg2", method = RequestMethod.POST)
    public Object uploadImg2(@RequestBody MultipartFile file) {
        if (file.isEmpty()) {
            return new result(null, "文件为空", 300);
        }
        String url = null;
        try {
            byte[] bytes = file.getBytes();
            String imageName = UUID.randomUUID().toString();
            try {
                //使用base64方式上传到七牛云
                url = QiniuCloudUtil.put64image(bytes, imageName);

                log.info("上传地址为----：" + url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            return new result(null, "文件异常", 301);
        }

        return new result(url, "文件上传成功", 200);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadImg3", method = RequestMethod.POST)
    public String  uploadImg3(@RequestBody byte[] bytes) {

        String url = null;

        String imageName = UUID.randomUUID().toString();
        try {
            //使用base64方式上传到七牛云
            url = QiniuCloudUtil.put64image(bytes, imageName);

            log.info("上传地址为----：" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }



}


