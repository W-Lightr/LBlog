package com.lightr.lblog.model.vo;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class AccountVo implements Serializable {
    @NotBlank(message = "用户名不能为空!")
    private String name;
    @NotBlank(message = "密码不能为空!")
    private String password;

}
