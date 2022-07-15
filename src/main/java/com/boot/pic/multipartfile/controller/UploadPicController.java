package com.boot.pic.multipartfile.controller;

import com.boot.pic.multipartfile.custom.CustomException;
import com.boot.pic.multipartfile.utils.PicUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="UploadController",description ="测试文件上传和下载")
public class UploadPicController {

    @Autowired
    private PicUtil picUtil;

    //从yml文件中读取路径
    @Value("${pic.path}")
    private String path;

    @Operation(summary = "上传功能",description = "字符串")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String form_layouts(
            @Parameter(description="单文件流") @RequestPart("file")MultipartFile file,
            @Parameter(description = "多文件流") @RequestPart("photos")MultipartFile[] photos){
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
