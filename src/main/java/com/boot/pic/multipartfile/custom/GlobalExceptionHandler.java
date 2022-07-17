package com.boot.pic.multipartfile.custom;

/**
 * @author linyi
 * @date 2022/7/15
 * 1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 定义一个全局异常
 */

/**
 * 全局异常：可以通过异常名具体捕捉到异常，并自定义返回异常信息
 */
@Slf4j
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 举例：捕捉SQL异常（SQLIntegrityConstraintViolationException）
     */
    //@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    //public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
    //    log.error(ex.getMessage());
    //    //判断异常信息是否含有此字段
    //    if (ex.getMessage().contains("Duplicate entry")) {
    //        //设置输出的异常信息
    //        //Duplicate entry 'zhangsan' for key 'employee.idx_username'
    //        String[] split = ex.getMessage().split(" ");
    //        String msg = split[2] + "已存在";
    //        return R.error(msg);
    //    }
    //    //否则报未知错误
    //    return R.error("未知错误");
    //}

    /**
     * 异常处理方法
     * 可以用来将异常信息返回给前端
     * @return
     */
    //@ExceptionHandler(CustomException.class)
    //public R<String> exceptionHandler(CustomException ex) {
    //    log.error(ex.getMessage());
    //    //将自定义异常信息返给前端
    //    return R.error(ex.getMessage());
    //}

    @ExceptionHandler(CustomException.class)
    public void exceptionHandler(CustomException ex) {
        log.error(ex.getMessage());
    }
}
