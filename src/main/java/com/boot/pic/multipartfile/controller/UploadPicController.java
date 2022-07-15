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
    @RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String form_layouts(
            @Parameter(description="单文件流") @RequestPart("file") MultipartFile file,
            @Parameter(description = "多文件流") @RequestPart("photos") MultipartFile[] photos){
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

    /**
     * 异常情况需要一级一级网上抛
     * @param name
     * @param response
     */
    //文件下载
    @Operation(description = "图片的下载/回显")
    @GetMapping("/down")
    public void downFile(
            @Parameter(description = "要下载的图片名") String name,
            HttpServletResponse response) {
        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream(new File(path + name));

            //
            // 输出流，通过输出流将文件写入到浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();
            //设置响应方式，图片文件
            response.setContentType("image/jpeg");

            //读取一行一行
            int len = 0;

            byte[] bytes = new byte[1024];

            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            //关闭资源
            outputStream.close();
            fileInputStream.close();
        }catch (FileNotFoundException e) {
            throw new CustomException("没有该图片："+name);
        }catch (IOException e) {
            throw new CustomException("存在异常");
        }
    }
}
