package com.boot.pic.multipartfile.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author linyi
 * @date 2022/7/15
 * 1.0
 */


@Component
public  class PicUtil {

    /**
     * 通过获取原文件名重新生成新的文件名加存储路径
     * 图片上传方法
     * @param PATH   ： 图片存储的路径
     * @param pic    ： 图片流
     *
     * 其中输出的可以给他换成日志类，或者返给前端的东西
     */
    public  void  uploadPic( String PATH,MultipartFile pic) {
        if (pic.isEmpty()) {
            System.out.println("存放文件的路径" + PATH);
            //获取图片原名称,带后缀名
            String originalFilename = pic.getOriginalFilename();
            System.out.println("获取图片原名称:" + originalFilename);
            //获取图片的后缀名
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
            System.out.println("图片的后缀名:" + substring);
            //通过UUID重新生成新的文件名
            String NEW_PIC_NAME = UUID.randomUUID().toString() + substring;
            System.out.println("重新生成后的文件名：" + NEW_PIC_NAME);
            //创建图片路径
            File path = new File(PATH);
            //判断图片存放路径是否存在
            if (!path.exists()) {
                //目录不存在则创建目录
                path.mkdirs();
            }
            //加上存储路径 最终使用这个
            String LAST_FILE = PATH + NEW_PIC_NAME;
            System.out.println("最终文件名+路径：" + LAST_FILE);
            try {
                pic.transferTo(new File(LAST_FILE));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("文件为空");
        }
    }
}
