package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.entity.query.Querycourse;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.online.edu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.service.EduVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Liutx
 * @since 2020-01-10
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduVideoService eduVideoService;

    //添加课程信息
    @Override
    public String insertCourseInfo(CourseInfoForm courseInfoForm) {
        //1.添加基本课程信息到课程表
        //使用dto方式获取了该页面所有的数据，将数据复制到EduCourse对象中，进行添加
        EduCourse eduCourse = new EduCourse();
        //对象属性值之间的copy
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int result = baseMapper.insert(eduCourse);

        //如果添加成功,添加描述
        if (result == 0){//失败返回0
            throw new EduException(20001,"添加课程信息失败");
        }
            //添加描述
            EduCourseDescription eduCourseDescription =new EduCourseDescription();
            //从dto中获取描述信息
            String description=courseInfoForm.getDescription();
            eduCourseDescription.setDescription(description);
            String courseId=eduCourse.getId();
            eduCourseDescription.setId(courseId);
            boolean flag=eduCourseDescriptionService.save(eduCourseDescription);
            if (flag){
                return courseId;
            }else {
                return null;
            }

    }

    //根据id查询课程信息
    @Override
    public CourseInfoForm getIdCourse(String id) {

        //1.根据id查询课程基本信息表
        EduCourse eduCourse=baseMapper.selectById(id);
        if (eduCourse==null){
            throw  new EduException(20001,"没有课程信息");
        }
        //把EduCourse对象复制到CourseInfoForm对象中
        CourseInfoForm courseInfoForm=new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);

        //courseInfoForm中已经有了课程基本信息，还需要课程描述信息
        EduCourseDescription eduCourseDescription=eduCourseDescriptionService.getById(id);
        String description = eduCourseDescription.getDescription();
        courseInfoForm.setDescription(description);
        return courseInfoForm;
    }

    //修改课程信息：涉及到两张表，基础信息表、描述表
    @Override
    public Boolean updateCourse(CourseInfoForm courseInfoForm) {

        //修改课程基本信息
        EduCourse eduCourse=new EduCourse();
        //使用方法：第一个参数赋值给第二个参数
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int result=baseMapper.updateById(eduCourse);
        if (result == 0){
            throw new EduException(20001,"修改基本课程信息失败");
        }

        //修改描述表 基本信息修改不成功，不需要执行后边的代码
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        //通过get/set方式给courseInfoForm对象赋值
            String id=courseInfoForm.getId();
            String description = courseInfoForm.getDescription();
            eduCourseDescription.setId(id);
            eduCourseDescription.setDescription(description);
        boolean update = eduCourseDescriptionService.updateById(eduCourseDescription);
        return update;
    }


    /**
     * 多条件查询接口实现
     * @param coursePage
     * @param querycourse
     */
    @Override
    public void pageListCondition(Page<EduCourse> coursePage, Querycourse querycourse) {
        //多条件查询把可能的查询条件放到实体中

        QueryWrapper<EduCourse> queryWrapper =new QueryWrapper<>();
        queryWrapper.orderByAsc("gmt_create");

        if (querycourse==null){
            baseMapper.selectPage(coursePage,null);
            return;
        }
        String title=querycourse.getTitle();
        String price = querycourse.getPrice();
        String begin = querycourse.getBegin();
        String end = querycourse.getEnd();
        if (!StringUtils.isEmpty(title)){
            queryWrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(price)){
            queryWrapper.like("price",price);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.like("begin",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.like("end",end);
        }

        baseMapper.selectPage(coursePage,queryWrapper);
    }

    //删除课程
    @Override
    public boolean removeCourseId(String id) {

        //1.根据课程id删除章节
        eduChapterService.deleteChapterByCourseId(id);
        //2.根据课程id删除小节
        eduVideoService.deleteVideoByCourseId(id);
        //3.根据课程id删除课程描述
        eduCourseDescriptionService.deleteCourseDescriptionByCourseId(id);
        //4.删除课程本身
        int result=baseMapper.deleteById(id);
        if (result>0){
            return result>0;
        }else {
            return false;
        }
    }

    @Override
    public CourseInfoDto getCourseInfoAll(String courseId) {
        CourseInfoDto courseInfoAll = baseMapper.getCourseInfoAll(courseId);
        return courseInfoAll;
    }


}
