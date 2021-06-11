package com.ihxjie.mondayserver.api.mapper;

import com.ihxjie.mondayserver.api.vo.RecordVo;
import com.ihxjie.mondayserver.api.vo.VacationVo;
import com.ihxjie.mondayserver.api.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ihxjie.mondayserver.api.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xjie
 * @since 2021-04-08
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    List<RecordVo> queryStudentByAttendanceId(Integer attendanceId);

    List<User> queryNotYetRecordStudentByAttendanceId(Integer attendanceId, Integer clazzId);

    List<User> queryNotYetRecordStudentByRealName(Integer attendanceId, Integer clazzId, String realName);

    List<User> queryNotYetRecordStudentByAttendanceIdAndStudentId(Integer attendanceId, Integer clazzId, Integer studentId);

    List<VacationVo> queryVacationStudentByClazzId(Integer clazzId);

    List<VacationVo> queryVacationStudent(Integer attendanceId);

    Integer queryVacationStudentCount(Integer attendanceId);

    Integer queryStudentSignInCount(Integer studentId, Integer clazzId);

}
