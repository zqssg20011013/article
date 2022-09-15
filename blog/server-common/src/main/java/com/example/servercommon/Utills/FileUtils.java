package com.example.servercommon.Utills;

/**
 * @project: blog
 * @ClassName: Fileutills
 * @author: smallwei
 * @creat: 2022/8/27 17:25
 * 描述:
 */
public class FileUtils {

    /**
     * 获取文件名的后缀，如：changlu.jpg => .jpg
     * @return 文件后缀名
     */
    public static String getFileSuffix(String fileName) {
        return fileName.contains(".") ? fileName.substring(fileName.indexOf('.')) : null;
    }
}
