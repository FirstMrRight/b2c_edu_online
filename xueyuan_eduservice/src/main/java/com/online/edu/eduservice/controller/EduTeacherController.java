package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.R;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.query.Queryteacher;
import com.online.edu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-12-11
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
public class EduTeacherController {


    @Autowired
    private EduTeacherService eduTeacherService;
    /**
     * 获取所有讲师
     * @return
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("")
    public R getAllTeacherList(){
        List <EduTeacher> list= eduTeacherService.list(null);//()里边是条件
        return R.ok().data("items",list);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID删除某位讲师")
    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        boolean remove=eduTeacherService.removeById(id);
        return remove;
    }

    /**
     * 分页查询讲师列表
     * @param page:当前页
     * @param limit 一页显示多少
     * @return
     */
    @ApiOperation(value = "分页查询讲师列表")
    @GetMapping("{page}/{limit}")
    public R getAllTeacherByList(@PathVariable long page,
                                 @PathVariable long limit){
        //mybatisplus实现分页
        Page <EduTeacher> teacherPage=new Page<>(page,limit);
        eduTeacherService.page(teacherPage,null);
        List<EduTeacher> records = teacherPage.getRecords();
        Long total=teacherPage.getTotal();

        Map res=new HashMap();
        res.put("total",total);
        res.put("records",records);
        return R.ok().data("res",res);
    }


    @ApiOperation(value = "多条件查询讲师列表")
    @PostMapping("MoreTeacherByList/{page}/{limit}")
        public R getMoreTeacherByList(@PathVariable long page,
                                      @PathVariable long limit,
                                      Queryteacher queryteacher){

        Page<EduTeacher> teacherPage=new Page<>(page,limit);
        //调用service的方法实现多条件查询
        eduTeacherService.pageListCondition(teacherPage,queryteacher);
        List<EduTeacher> records = teacherPage.getRecords();
        Long total=teacherPage.getTotal();
        return  R.ok().data("total", total).data("rows", records);
    }

    /**
     * 新增讲师
     * @param eduTeacher
     * @return
     */
    @ApiOperation(value="新增讲师功能")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 更新讲师信息
     * @param eduTeacher
     * @return
     */
    @ApiOperation(value="更新讲师信息")
    @PostMapping("updateTeacher/{id}")
    public R updateTeacher(@PathVariable String id,@RequestBody EduTeacher eduTeacher)
    {
        eduTeacher.setId(id);
        boolean update = eduTeacherService.updateById(eduTeacher);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiImplicitParam(value = "根据id查找")
    @GetMapping("getTeacherListById/{id}")
    public R getTeacherListById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        /*这样写只是去练习一下Map的用法，简单一点可以直接使用data(),data里放一个teacher对象*/
//        Map<String,Object> res=new HashMap<>();
//        String name=teacher.getName();
//        String avatar = teacher.getAvatar();
//        String career = teacher.getCareer();
//        Integer level = teacher.getLevel();
//        res.put("name",name);
//        res.put("avatar",avatar);
//        res.put("career",career);
//        res.put("level",level);
//        return R.ok().data(res);
        return R.ok().data("teacher",teacher);

    }
    //{"code":20000,"data":{"token":"admin"}}
    //模拟登陆
    @PostMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }

    //{"code":20000,"data":{"roles":["admin"],"name":"admin","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
//    @GetMapping("info")
//    public R info() {
//        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @GetMapping("getTeacherInfo/{id}")
    public R getTeacherInfo(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("eduTeacher",eduTeacher);
    }


}

