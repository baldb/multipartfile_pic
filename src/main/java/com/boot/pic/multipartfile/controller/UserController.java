package com.boot.pic.multipartfile.controller;

import com.boot.pic.multipartfile.pojo.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyi
 * @date 2022/7/15
 * 1.0
 */


@RestController
@Tag(name="UserController",description = "测试返回JSON值注释和默认值")
public class UserController {

    @GetMapping("/user")
    @Operation(summary = "测试1",description = "返回默认User")
    public User test1(){
        return new User();
    }
}
