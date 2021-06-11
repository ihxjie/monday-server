package com.ihxjie.mondayserver.api.mapper;

import com.ihxjie.mondayserver.api.data.AttendanceInfo;
import com.ihxjie.mondayserver.api.entity.Attendance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ihxjie.mondayserver.api.entity.User;
import com.ihxjie.mondayserver.api.entity.Vacation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 签到表 Mapper 接口
 * </p>
 *
 * @author xjie
 * @since 2021-04-08
 */
@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance> {

    List<Vacation> getVacationStudentId(Integer attendanceId);

}
