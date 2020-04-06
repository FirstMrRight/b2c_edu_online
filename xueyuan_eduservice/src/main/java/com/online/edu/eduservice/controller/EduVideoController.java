package com.online.edu.eduservice.controller;


import com.guli.common.R;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Liutx
 * @since 2020-02-05
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
public class EduVideoController {

@Autowired
private EduVideoService eduVideoService;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){

        boolean save = eduVideoService.save(eduVideo);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }

    }

    //根据id查询
    @GetMapping("{videoId}")
    public R getVideoId(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        if (eduVideo!=null){
            return R.ok().data("eduVideo",eduVideo);
        }else {
            return R.error();
        }
    }

    //修改的方法
    @PostMapping("updateEduVideo")
    public R updateEduVideo(@RequestBody EduVideo eduVideo){
        boolean updateById = eduVideoService.updateById(eduVideo);
        if (updateById){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //删除的方法
    @DeleteMapping("{videoId}")
    public R deleteEduVideo(@PathVariable String videoId){
        boolean removeById = eduVideoService.removeById(videoId);
        if (removeById){
            return R.ok();
        }else {
            return R.error();
        }
    }

}
