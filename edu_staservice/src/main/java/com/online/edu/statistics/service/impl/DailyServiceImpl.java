package com.online.edu.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.common.R;
import com.online.edu.statistics.UcenterClient;
import com.online.edu.statistics.entity.Daily;
import com.online.edu.statistics.mapper.DailyMapper;
import com.online.edu.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Liutx
 * @since 2020-03-12
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    UcenterClient ucenterClient;
    @Autowired
    private DailyService dailyService;
    @Override
    //在发现端调用其他模块封装好的接口
    public void getCountRegisterNum(String day) {
        //判断再进行添加统计分析表里面是否存在添加的天数的记录，如果存在删除，
        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        R r = ucenterClient.countRegisterNum(day);
        Integer registerCount = (Integer)r.getData().get("registerCount");
        //把获取数据添加到统计分析表里面
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);
        Integer loginNum = RandomUtils.nextInt(100, 200);

        Daily daily=new Daily();
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);
//baseMapper.insert(daily)
        baseMapper.cusNum(daily);

    }

}
