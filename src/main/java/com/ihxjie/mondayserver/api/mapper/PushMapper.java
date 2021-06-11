package com.ihxjie.mondayserver.api.mapper;

import com.ihxjie.mondayserver.api.entity.Push;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xjie
 * @since 2021-06-08
 */
@Mapper
public interface PushMapper extends BaseMapper<Push> {
    List<Push> queryAllByClazzId(Integer clazzId);
}
