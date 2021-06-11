package com.ihxjie.mondayserver.api.mapper;

import com.ihxjie.mondayserver.api.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 *
 * @author xjie
 * @since 2021-04-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> queryStudentByClazzId(Integer clazzId);

}
