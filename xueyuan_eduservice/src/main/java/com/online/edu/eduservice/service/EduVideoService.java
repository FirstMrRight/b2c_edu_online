package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Liutx
 * @since 2020-02-05
 */
public interface EduVideoService extends IService<EduVideo> {

    void deleteVideoByCourseId(String id);
}
