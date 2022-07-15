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

/**
 * 定义一个全局异常
 */
@Slf4j
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {

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
