package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.R;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.entity.query.Querycourse;
import com.online.edu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Liutx
 * @since 2020-01-10
 */
@RestController
@Api(description="课程管理")
@CrossOrigin //跨域
@RequestMapping("/eduservice/course")
public class EduCourseController {
    @Autowired private EduCourseService eduCourseService;


    @GetMapping("listCourse")
    public R getCourseList(){
        List<EduCourse> list =eduCourseService.list(null);
        return R.ok().data("items",list);
    }

    /**
     * 分页查询课程列表
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "课程列表带分页")
    @GetMapping("{page}/{limit}")
    public R getAllCourseListByList(@PathVariable long page,
                                    @PathVariable long limit){
        Page<EduCourse> coursePage =new Page<>(page,limit);
        eduCourseService.page(coursePage,null);
        List<EduCourse> records = coursePage.getRecords();
        Long total =coursePage.getTotal();

        //数据存入集合
        Map res= new HashMap();
        res.put("total",total);
        res.put("records",records);
        return R.ok().data("res",res);
    }
    @ApiOperation(value = "带条件的查询")
    @PostMapping("MoreCourseByList/{page}/{limit}")
    public R getMoreCourseByList(@PathVariable long page,
                                 @PathVariable long limit,
                                 Querycourse querycourse){
        //调用实现层
        Page<EduCourse> coursePage =new Page<>(page,limit);
        eduCourseService.pageListCondition(coursePage,querycourse);
        List<EduCourse> records = coursePage.getRecords();
        Long total = coursePage.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }


    /**
     * 添加课程信息
     * @param courseInfoForm
     * @return
     */
    @ApiOperation(value = "添加课程信息")
        /**
         * 操作两张表
         * courseInfoForm中存放了所有的信息，两张表中的信息
         * impl层需要两个service对象
         */
        @PostMapping
        public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
            String courseId = eduCourseService.insertCourseInfo(courseInfoForm);
            return R.ok().data("courseId",courseId);
        }


    //修改课程的方法
    @PostMapping("updateCourseInfo/{id}")
    public R updateCourseInfo(@PathVariable String id,
                              @RequestBody CourseInfoForm courseInfoForm) {
        Boolean flag = eduCourseService.updateCourse(courseInfoForm);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //根据Id插叙课程信息,查询两张表的信息，课程表、课程信息描述表,做数据回显使用
    @GetMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id) {
        CourseInfoForm courseInfoForm = eduCourseService.getIdCourse(id);
        return R.ok().data("courseInfoForm",courseInfoForm);
    }

    //删除课程的方法
    @DeleteMapping("deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id){
            boolean flag=eduCourseService.removeCourseId(id);
            if (flag){
                return R.ok();
            }else {
                return R.error();
            }
    }

    //发布页数据查询，根据id查询课程详情信息
    @GetMapping("getAllCourseInfo/{courseId}")
    public R getAllCourseInfo(@PathVariable String courseId){
            //不能返回Course对象，数据不全，建立dto
            CourseInfoDto courseInfoDto=eduCourseService.getCourseInfoAll(courseId);
            return R.ok().data("courseInfo",courseInfoDto);
    }
//
//    //最终发布课程，修改课程状态
//    @GetMapping("publishCourse/{courseId}")
//    public R publishCourse(@PathVariable String courseId){
//        EduCourse eduCourse = new EduCourse();
//        eduCourse.setId(courseId);
//        eduCourse.setStatus("Normal");
//        boolean result = eduCourseService.updateById(eduCourse);
//        if (result){
//            return R.ok();
//        }else {
//            return R.error();
//        }
//    }


    //最终发布课程的方法，修改课程状态
    @GetMapping("publishCourse/{courseId}")
    public  R publishCourse(@PathVariable String courseId) {
        EduCourse eduCourse = new EduCourse();
        //where entry条件
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        boolean result = eduCourseService.updateById(eduCourse);
        if(result) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

