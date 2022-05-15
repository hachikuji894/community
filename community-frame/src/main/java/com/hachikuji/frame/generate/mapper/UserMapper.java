package com.hachikuji.frame.generate.mapper;

import com.hachikuji.frame.generate.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hachikuji
 * @since 2021-11-29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
