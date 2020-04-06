package com.online.edu.ossservice.controller;

import com.guli.common.R;
import com.online.edu.ossservice.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Hashtable;

@RestController
@RequestMapping("/ossservice/vod")
@CrossOrigin
public class OSSController {
    @Autowired
    private OssService ossService;

    //实现上传视频到阿里云服务器的方法
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public R uploadAliyunVideo(@RequestParam("file") MultipartFile file) {
        //调用方法实现视频上传，返回上传之后视频id
         String videoId = ossService.uploadVideoAlyun(file);
        Hashtable hashtable=new Hashtable();
        return R.ok().data("videoId",videoId);
    }

    //实现删除云端视频的方法
    @DeleteMapping("{videoId}")
    public R deleteAlitynVideo(@PathVariable String videoId){
        ossService.deleteAliyunVideoId(videoId);
        return R.ok();
    }

}
