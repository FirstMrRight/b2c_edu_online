package com.online.edu.statistics.mapper;

import com.online.edu.statistics.entity.Daily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author Liutx
 * @since 2020-03-12
 */
public interface DailyMapper extends BaseMapper<Daily> {
    int cusNum(@Param("Daily") Daily day);

}
