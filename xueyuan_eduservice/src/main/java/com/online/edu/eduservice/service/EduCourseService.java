package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.entity.query.Querycourse;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Liutx
 * @since 2020-01-10
 */
public interface EduCourseService extends IService<EduCourse> {

    String insertCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getIdCourse(String id);

    Boolean updateCourse(CourseInfoForm courseInfoForm);

    void pageListCondition(Page<EduCourse> coursePage, Querycourse querycourse);

    boolean removeCourseId(String id);

    CourseInfoDto getCourseInfoAll(String courseId);
}
