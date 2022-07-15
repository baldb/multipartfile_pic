package com.boot.pic.multipartfile.controller;

import com.boot.pic.multipartfile.custom.CustomException;
import com.boot.pic.multipartfile.utils.PicUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author linyi
 * @date 2022/7/15
 * 1.0
 */

/**
 * 测试文件上传和下载
 */
@Slf4j
@RestController
public class UploadPicController {

    @Autowired
    private PicUtil picUtil;

    //从yml文件中读取路径
    @Value("${pic.path}")
    private String path;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String form_layouts(MultipartFile file,
                               MultipartFile[] photos){
        //单文件上传
        picUtil.uploadPic(path,file);

        //多文件上传
        if(photos.length !=0) {
            for (MultipartFile photo : photos) {
                picUtil.uploadPic(path,photo);
            }
        }else {
            //在此处爆出自定义异常的信息
            throw new CustomException("多文件上传为空");
        }
        return "ok";
    }
}
