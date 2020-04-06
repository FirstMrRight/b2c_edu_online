package com.online.edu.eduservice.controller;


import com.guli.common.R;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.handler.dto.OneSubjectDto;
import com.online.edu.eduservice.service.EduSubjectService;
import lombok.val;
import org.apache.ibatis.annotations.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Liutx
 * @since 2019-12-26
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * excel导入导出
     * 通过上传excel获取文件内容
     * @param file
     * @return
     */
    @PostMapping("import")
    public R importExcelSubject (@RequestParam("file") MultipartFile file){

            //1.获取上传excel文件
            List<String> msg=eduSubjectService.importSubject(file);
            //判断list是否为空,判断list集合是否为空，一般判断size
            if (msg.size()==0){
                return R.ok();
            }else {
                return R.error().message("部分数据导入失败").data("msgList",msg);
            }
        }


    /**
     * 返回所有数据
     * @return
     */
    @GetMapping
        public R getSubjectList(){
        List<OneSubjectDto> list= eduSubjectService.getSubjectList();
        return R.ok().data("OneSubjectDto",list);
        }


    /**
     *删除分类
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public R deleteSubjectById (@PathVariable String id){
            Boolean flag = eduSubjectService.deleteSubjectById(id);
            if (flag){
                return R.ok();
            }else {
                return R.error();
            }
        }

    //添加一级分类
    @PostMapping("addOneLevel")
        public R addOneLevel(@RequestBody EduSubject eduSubject){
       Boolean flag = eduSubjectService.saveOneLevel(eduSubject);
        return R.ok().data("data",flag);
    }


    //添加二级分类
    @PostMapping("addTwoLevel")
    public R addTwoLevel(@RequestBody EduSubject eduSubject){
        Boolean flag = eduSubjectService.saveTwoLevel(eduSubject);
        return R.ok().data("data",flag);
    }
}

