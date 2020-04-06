package com.online.edu.ucenter.mapper;

import com.online.edu.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Liutx
 * @since 2020-03-12
 */
public interface MemberMapper extends BaseMapper<Member> {
    Integer countRegisterNum(String day);
}
