package com.online.edu.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService  {
    String uploadVideoAlyun(MultipartFile file);

    void deleteAliyunVideoId(String videoId);
}
