package com.online.edu.eduservice.mapper;

import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Liutx
 * @since 2020-01-10
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    //根据课程id查询课程详情
    CourseInfoDto getCourseInfoAll(String courseId);
    //测试多个参数,注解方式
//    CourseInfoDto getCourseInfoAll1(@RequestParam("a") String courseId, String b);
}
