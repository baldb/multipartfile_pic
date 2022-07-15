package com.boot.pic.multipartfile.custom;

/**
 * @author linyi
 * @date 2022/7/15
 * 1.0
 */

/**
 * 自定义异常类
 */
public class CustomException extends RuntimeException{

    //定义一个有参构造器，用于向上抛自定义异常信息
    public CustomException(String message){
        super(message);
    }
}
