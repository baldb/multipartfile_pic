package com.boot.pic.multipartfile.utils;

import com.boot.pic.multipartfile.custom.CustomException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @author linyi
 * @date 2022/7/15
 * 1.0
 */


@Component
@Data
@Slf4j
public  class PicUtil {



    @Value("${pic.path}")
    private String PATH;
    //文件新名称
    private String NEW_PIC_NAME;

    /**
     * 通过获取原文件名重新生成新的文件名加存储路径
     * 图片上传方法
     * @param pic    ： 图片流
     *
     * 其中输出的可以给他换成日志类，或者返给前端的东西
     */
    public  void  uploadPic(MultipartFile pic) {
        if (!pic.isEmpty()) {
            System.out.println("存放文件的路径" + PATH);
            //获取图片原名称,带后缀名
            String originalFilename = pic.getOriginalFilename();
            log.info("获取图片原名称:{}",originalFilename);
            //获取图片的后缀名
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
            log.info("图片的后缀名: {}", substring);
            //通过UUID重新生成新的文件名
             NEW_PIC_NAME = UUID.randomUUID().toString() + substring;
            log.info("重新生成后的文件名：{}", NEW_PIC_NAME);
            //创建图片路径
            File path = new File(PATH);
            //判断图片存放路径是否存在
            if (!path.exists()) {
                //目录不存在则创建目录
                path.mkdirs();
            }
            //加上存储路径 最终使用这个
            String LAST_FILE = PATH + NEW_PIC_NAME;
            log.info("最终文件名+路径：{}", LAST_FILE);
            try {
                pic.transferTo(new File(LAST_FILE));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("文件为空");
        }
    }


    /**
     * 通过图片名下载图片或者回显图片
     * @param name
     */
    public void downPic(String name, HttpServletResponse response){
        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream(new File(PATH + name));
            log.info("要下载的图片是：{}",PATH + name);
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



    /**
     * 通过获取图片名称去删除包内对应的图片
     * @param picName
     */
    public void deletePic(String picName){
        //根据路径创建文件对象
        File file = new File(PATH+picName);
        log.info("要删除的图片是：{}",PATH+picName);
        //判断删除是否成功
        if(!file.delete()){
                throw new CustomException("没有该图片");
        }

        //别人写的逻辑
        ////路径是个文件且不为空时删除文件
        //if(file.isFile()&&file.exists()){
        //    flag = file.delete();
        //}
    }
}
