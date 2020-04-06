package com.online.edu.statistics;


import com.guli.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//发现端调用服务端接口
@FeignClient("xueyuan-ucservice")
@Component
public interface UcenterClient {

    //得到某一天注册人数的方法定义,接口封装，使用本微服务中的数据能被其他模块调用到
    @GetMapping("/ucenter/member/countRegisterNum/{day}")
    public R countRegisterNum(@PathVariable("day") String day);
}
