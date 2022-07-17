package com.boot.pic.multipartfile.custom;

/**
 * @author linyi
 * @date 2022/7/15
 * 1.0
 */

/**
 * 自定义异常类
 */

/**
 * 步骤：
 * 1.先自定义一个自定义异常类继承与运行异常类（ RuntimeException）
 * 2.编写一个全局异常类（GlobalExceptionHandler），用于返回异常时返给前端的信息
 *
 */
public class CustomException extends RuntimeException{

    //定义一个有参构造器，用于向上抛自定义异常信息
    public CustomException(String message){
        super(message);
    }
}
