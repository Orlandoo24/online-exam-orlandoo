package com.exam.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageDO implements Serializable {

    private Integer id;

    private String title;

    private String content;

}
