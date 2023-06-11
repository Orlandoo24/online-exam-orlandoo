package com.exam.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * 登陆校验参数
 */
@Data
public class Login implements Serializable {

    private Integer username;

    private String password;

}
