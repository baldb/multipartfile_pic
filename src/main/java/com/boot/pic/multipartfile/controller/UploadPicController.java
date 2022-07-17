package com.boot.pic.multipartfile.controller;

import com.boot.pic.multipartfile.custom.CustomException;
import com.boot.pic.multipartfile.utils.PicUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    ////从yml文件中读取路径
    //@Value("${pic.path}")
    //private String path;

    @Operation(summary = "上传功能",description = "字符串")
    @RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String form_layouts(
            @Parameter(description="单文件流") @RequestPart("file") MultipartFile file,
            @Parameter(description = "多文件流") @RequestPart("photos") MultipartFile[] photos){
        //单文件上传
        picUtil.uploadPic(file);
        log.info("返回给前端新的图片名：{}",picUtil.getNEW_PIC_NAME());

        List<String> picList = new ArrayList<>();
        //多文件上传
        if(photos.length !=0) {
            for (MultipartFile photo : photos) {
                picUtil.uploadPic(photo);
                picList.add(picUtil.getNEW_PIC_NAME());
            }
        }else {
            //在此处爆出自定义异常的信息
            throw new CustomException("多文件上传为空");
        }
        log.info("返回给前端新的图片名：{}",picList);
        return "ok";
    }

    /**
     * 异常情况需要一级一级网上抛
     * @param name
     * @param response
     */
    //文件下载
    @Operation(summary = "图片回显功能",description = "图片的下载/回显")
    @GetMapping("/down")
    public void downFile(
            @Parameter(description = "要下载的图片名") String name,
            HttpServletResponse response) {
        picUtil.downPic(name,response);
    }


    @Operation(summary = "图片删除功能",description = "图片的删除")
    @GetMapping("/delete")
    public void deleteFile(
            @Parameter(description = "要删除的图片名") String name) {
        picUtil.deletePic(name);
    }

}
