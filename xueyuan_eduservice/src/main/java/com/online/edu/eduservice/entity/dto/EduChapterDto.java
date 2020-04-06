package com.online.edu.eduservice.entity.dto;

import com.online.edu.eduservice.entity.EduVideo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class EduChapterDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;

    //一个章节有很多小节
    private List<EduVideoDto> children = new ArrayList<>();
}
