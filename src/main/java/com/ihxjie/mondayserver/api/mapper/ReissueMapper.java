package com.ihxjie.mondayserver.api.mapper;

import com.ihxjie.mondayserver.api.entity.Reissue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ihxjie.mondayserver.api.vo.ReissueVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xjie
 * @since 2021-05-21
 */
@Mapper
public interface ReissueMapper extends BaseMapper<Reissue> {
    List<ReissueVo> getReissueByAttendanceId(Integer attendanceId);
}
