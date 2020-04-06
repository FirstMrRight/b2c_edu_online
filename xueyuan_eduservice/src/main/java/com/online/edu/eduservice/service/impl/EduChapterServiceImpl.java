package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.entity.dto.EduVideoDto;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduChapterMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Liutx
 * @since 2020-02-05
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    //根据课程id删除章节（course_id）
    //delete from edu_chapter c where c.course_id=?
    @Override
    public void deleteChapterByCourseId(String id) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<EduChapterDto> getChapterVideoListCourseId(String courseId) {

        //1.根据课程id查询章节,(一级分类)
        QueryWrapper<EduChapter> wrapper =new QueryWrapper<>();
        wrapper.eq("course_Id",courseId);
        List<EduChapter> eduChapters= baseMapper.selectList(wrapper);//所有的章节信息

        //2.根据课程id获取小节信息
        QueryWrapper<EduVideo> wrapper1=new QueryWrapper<>();
        wrapper1.eq("course_Id",courseId);
        List<EduVideo> eduVideos=eduVideoService.list(wrapper1);//所有的小结信息

        //用于存储章节和小结数据,章节、小结数据对应
        List<EduChapterDto> chapterDtoList = new ArrayList<>();
        //3.遍历所有章节，赋值到dto对象，页面所需数据
        for (int i=0;i<eduChapters.size();i++){
            //获取每个章节
            EduChapter chapter = eduChapters.get(i);
            EduChapterDto eduChapterDto = new EduChapterDto();
            BeanUtils.copyProperties(chapter,eduChapterDto);
            //dto对象存放到List集合
            chapterDtoList.add(eduChapterDto);

            //创建集合用于存储所有小节数据
            List<EduVideoDto> eduVideoDtoList = new ArrayList<>();
            for (int j=0;j<eduVideos.size();j++){
                EduVideo video = eduVideos.get(j);
                //判断章节id与小结id是否一致
                if (video.getChapterId().equals(chapter.getId())){
                    EduVideoDto eduVideoDto = new EduVideoDto();
                    BeanUtils.copyProperties(video,eduVideoDto);
                    eduVideoDtoList.add(eduVideoDto);
                }
            }
            //把小结数据最终放到每个章节里面
            eduChapterDto.setChildren(eduVideoDtoList);
        }
        return chapterDtoList;
    }

    //还有其他的判断方式吧
    @Override
    public boolean removeChapterId(String chapterId) {
        //判断章节里边是否有小节

        QueryWrapper<EduVideo> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(deleteWrapper);
        if (count>0){
            //有小节数据，不删
            throw new EduException(20001,"先删除小节");
        }else {
            int result=baseMapper.deleteById(chapterId);
            return result>0;
        }
    }
}
