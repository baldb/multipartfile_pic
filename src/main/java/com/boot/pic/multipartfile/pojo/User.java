package com.boot.pic.multipartfile.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author linyi
 * @date 2022/7/15
 * 1.0
 */


@Data
public class User {

    @Schema(name = "姓名",defaultValue = "张三")
    private String name;
    @Schema(name = "性别")
    private String sex;
    @Schema(name = "年龄")
    private Integer age;
}
