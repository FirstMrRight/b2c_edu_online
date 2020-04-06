package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.query.Queryteacher;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduTeacherMapper;
import com.online.edu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-12-11
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    @Override
    public void pageListCondition(Page<EduTeacher> teacherPage, Queryteacher queryteacher) {
//        try {
//            int i = 9/0;
//        }catch(Exception e) {
//            //抛出自定义异常
//            throw new EduException(20001,"执行自定义异常");
//        }

            //多条件分页查询实现、queryWrapper条件构建
            QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();
            queryWrapper.orderByAsc("sort");

            if (queryteacher==null){
                baseMapper.selectPage(teacherPage,null);
                return;
            }
            String name = queryteacher.getName();
            String level=queryteacher.getLevel();
            String begin= queryteacher.getBegin();
            String end=queryteacher.getEnd();
            if (!StringUtils.isEmpty(name)){
                queryWrapper.like("name",name);
            }
            if (!StringUtils.isEmpty(level)){
                queryWrapper.eq("level",level);
            }
            if (!StringUtils.isEmpty(begin)){
                queryWrapper.ge("gmt_create",begin);
            }
            if (!StringUtils.isEmpty(end)){
                queryWrapper.le("gmt_create",end);
            }

            //条件查询带分页
            baseMapper.selectPage(teacherPage,queryWrapper);
        }


    }


