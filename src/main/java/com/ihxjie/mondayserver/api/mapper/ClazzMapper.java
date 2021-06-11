package com.ihxjie.mondayserver.api.mapper;

import com.ihxjie.mondayserver.api.data.Classes;
import com.ihxjie.mondayserver.api.entity.Clazz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 班级 Mapper 接口
 * </p>
 *
 * @author xjie
 * @since 2021-04-08
 */
@Mapper
public interface ClazzMapper extends BaseMapper<Clazz> {

    List<Classes> queryClasses(String teacherId);

    List<Classes> queryStuClazz(String studentId);

}
