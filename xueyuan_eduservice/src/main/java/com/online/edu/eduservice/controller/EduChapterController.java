package com.online.edu.eduservice.controller;


import com.guli.common.R;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.service.EduChapterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Liutx
 * @since 2020-02-05
 */
@RestController
@CrossOrigin//跨域
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    //根据id查询章节和小节
    @GetMapping("getChapterVideoList/{courseId}")
    public R getChapterVideoListCourseId(@PathVariable String courseId){
        List<EduChapterDto> list= eduChapterService.getChapterVideoListCourseId(courseId);
        return R.ok().data("items",list);
    }
    @ApiOperation("添加章节")
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        boolean flag=eduChapterService.save(eduChapter);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("根据章节id查询")
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfoById(@PathVariable String chapterId){
       EduChapter eduChapter = eduChapterService.getById(chapterId);
       return R.ok().data("eduChapter",eduChapter);
    }

    @ApiOperation("修改章节的方法")
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        boolean update = eduChapterService.updateById(eduChapter);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("删除章节")
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean result=eduChapterService.removeChapterId(chapterId);
        if(result){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

