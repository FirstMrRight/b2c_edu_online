package com.online.edu.eduservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

//小结的dto对象
@Data
public class EduVideoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Boolean free;
}
