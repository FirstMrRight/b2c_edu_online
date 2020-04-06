package com.online.edu.statistics.controller;


import com.guli.common.R;
import com.online.edu.statistics.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Liutx
 * @since 2020-03-12
 */
@RestController
@CrossOrigin
@RequestMapping("/statistics/daily")
public class DailyController {
    @Autowired
    private DailyService dailyService;
    //获取日注册人数
    @GetMapping("getStatisticsDay/{day}")
    public R getCountRegisterNum(@PathVariable String day){
        dailyService.getCountRegisterNum(day);
        return R.ok();
    }

}

