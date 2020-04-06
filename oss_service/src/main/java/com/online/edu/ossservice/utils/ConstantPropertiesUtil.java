package com.online.edu.ossservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


// TODO: 2020/2/29 更改utils类，实现数据传递 
/**
 * 本类的作用是用于传递阿里云的keyid 和 keysecret信息，但是并没有实现数据传递功能，
 * 所以在实现类中使用字符串将个人阿里云密钥写死，功能实现，后期需要更改完善
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${aliyun.vod.file.keyid}")
    private String keyid;

    @Value("${aliyun.vod.file.keysecret}")
    private String keysecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
