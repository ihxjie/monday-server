package com.ihxjie.mondayserver.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ihxjie.mondayserver.api.data.AttendanceDualAxes;
import com.ihxjie.mondayserver.api.data.AttendancePie;
import com.ihxjie.mondayserver.api.data.ClazzDualAxesColumn;
import com.ihxjie.mondayserver.api.data.ClazzDualAxesLine;
import com.ihxjie.mondayserver.api.entity.*;
import com.ihxjie.mondayserver.api.mapper.*;
import com.ihxjie.mondayserver.api.vo.RecordVo;
import com.ihxjie.mondayserver.api.vo.VacationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xjie
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/api/record")
public class RecordController {

    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    PositionInfoMapper positionInfoMapper;
    @Autowired
    ReissueMapper reissueMapper;
    @Autowired
    StudentClazzMapper studentClazzMapper;
    @Autowired
    UserMapper userMapper;

    private String getUserId(HttpSession session){
        // return session.getAttribute("userId").toString();
        return "6";
    }
    private boolean notRecord(Integer userId, Long attendanceId){
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("attendance_id", attendanceId)
                .eq("student_id", userId);
        Record record = recordMapper.selectOne(queryWrapper);
        return record == null;
    }

    @PostMapping("/attClick")
    public String attClick(Integer userId, Long attendanceId){
        Attendance attendance = attendanceMapper.selectById(attendanceId);

        if (notRecord(userId, attendanceId)){
            if (attendance.getStatus() == 1){
                return "未到签到时间";
            }else if (attendance.getStatus() == 3){
                return "签到时间已截止，请发起补签请求";
            }else if (attendance.getStatus() == 4){
                return "签到已结束";
            }
            Record record = new Record();
            record.setAttendanceId(attendanceId);
            record.setStudentId(userId);
            record.setAttClick(1);
            int res = recordMapper.insert(record);
            if (res == 0){
                return "签到失败";
            }
            return "签到成功";
        }
        return "已签到，请勿重复签到";
    }

    @GetMapping("/attQrcode")
    public String attQrcode(Long attendanceId, Integer userId,
                            String startTime, String endTime){
        Attendance attendance = attendanceMapper.selectById(attendanceId);
        long startSecond = Long.parseLong(startTime);
        long endSecond = Long.parseLong(endTime);
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.ofEpochSecond(startSecond, 0, ZoneOffset.of("+8"));
        LocalDateTime end = LocalDateTime.ofEpochSecond(endSecond, 0, ZoneOffset.of("+8"));

        if (notRecord(userId, attendanceId)){
            if (attendance.getStatus() == 1){
                return "未到签到时间";
            }else if (attendance.getStatus() == 2){
                if (ldt.isAfter(end)){
                    return "签到二维码已过期，请重新扫描";
                }
            }else if (attendance.getStatus() == 3){
                return "签到时间已截止，请发起补签请求";
            }else if (attendance.getStatus() == 4){
                return "签到已结束";
            }
            Record record = new Record();
            record.setAttendanceId(attendanceId);
            record.setStudentId(userId);
            record.setAttQrcode(1);
            if (attendance.getAttendanceType() == 6){
                record.setAttGps(1);
            }
            int res = recordMapper.insert(record);
            if (res == 0){
                return "签到失败";
            }
            return "签到成功";
        }
        return "已签到，请勿重复签到";
    }

    @PostMapping("/attGps")
    public String attGps(Integer userId, @RequestBody PositionInfo positionInfo){
        Attendance attendance = attendanceMapper.selectById(positionInfo.getAttendanceId());

        if (notRecord(userId, positionInfo.getAttendanceId())){
            if (attendance.getStatus() == 1){
                return "未到签到时间";
            }else if (attendance.getStatus() == 3){
                return "签到时间已截止，请发起补签请求";
            }else if (attendance.getStatus() == 4){
                return "签到已结束";
            }
            positionInfoMapper.insert(positionInfo);
            Integer positionId = positionInfo.getPositionId();
            Record record = new Record();
            record.setAttendanceId(positionInfo.getAttendanceId());
            record.setStudentId(userId);
            record.setAttGps(positionId);
            int res = recordMapper.insert(record);
            if (res == 0){
                return "签到失败";
            }
            return "签到成功";
        }
        return "已签到，请勿重复签到";
    }

    @PostMapping("/attFace")
    public String attFace(Integer userId, Long attendanceId){
        Attendance attendance = attendanceMapper.selectById(attendanceId);

        if (notRecord(userId, attendanceId)){
            if (attendance.getStatus() == 1){
                return "未到签到时间";
            }else if (attendance.getStatus() == 3){
                return "签到时间已截止，请发起补签请求";
            }else if (attendance.getStatus() == 4){
                return "签到已结束";
            }

            Record record = new Record();
            record.setAttendanceId(attendanceId);
            record.setStudentId(userId);
            record.setAttFace(1);
            int res = recordMapper.insert(record);
            if (res == 0){
                return "签到失败";
            }
            return "签到成功";
        }
        return "已签到，请勿重复签到";
    }
    @PostMapping("/faceGps")
    public String faceGps(Integer userId, @RequestBody PositionInfo positionInfo){
        Attendance attendance = attendanceMapper.selectById(positionInfo.getAttendanceId());

        if (notRecord(userId, positionInfo.getAttendanceId())){
            if (attendance.getStatus() == 1){
                return "未到签到时间";
            }else if (attendance.getStatus() == 3){
                return "签到时间已截止，请发起补签请求";
            }else if (attendance.getStatus() == 4){
                return "签到已结束";
            }
            positionInfoMapper.insert(positionInfo);
            Integer positionId = positionInfo.getPositionId();
            Record record = new Record();
            record.setAttendanceId(positionInfo.getAttendanceId());
            record.setStudentId(userId);
            record.setAttGps(positionId);
            record.setAttFace(1);
            int res = recordMapper.insert(record);
            if (res == 0){
                return "签到失败";
            }
            return "签到成功";
        }
        return "已签到，请勿重复签到";
    }

    @GetMapping("/queryStudentByAttendanceId")
    public List<RecordVo> queryStudentByAttendanceId(Integer attendanceId){
        return recordMapper.queryStudentByAttendanceId(attendanceId);
    }
    @GetMapping("/queryVacationStudentByAttendanceId")
    public List<VacationVo> queryVacationStudentByAttendanceId(Integer attendanceId){
        Attendance attendance = attendanceMapper.selectById(attendanceId);
        return recordMapper.queryVacationStudentByClazzId(attendance.getClazzId());
    }

    @GetMapping("/queryVacationStudent")
    public List<VacationVo> queryVacationStudent(Integer attendanceId){
        return recordMapper.queryVacationStudent(attendanceId);
    }
    @GetMapping("/queryNotYetRecordStudentByAttendanceId")
    public List<User> queryNotYetRecordStudentByAttendanceId(Integer attendanceId, Integer userId, String realName){
        Attendance attendance = attendanceMapper.selectById(attendanceId);
        if (userId != null){
            return recordMapper.queryNotYetRecordStudentByAttendanceIdAndStudentId(attendanceId, attendance.getClazzId(), userId);
        }else if (realName != null){
            return recordMapper.queryNotYetRecordStudentByRealName(attendanceId, attendance.getClazzId(), realName);
        }else {
            return recordMapper.queryNotYetRecordStudentByAttendanceId(attendanceId, attendance.getClazzId());
        }

    }

    @GetMapping("/queryAttendanceDualAxes/{attendanceId}")
    public List<AttendanceDualAxes> queryAttendanceDualAxes(@PathVariable("attendanceId") Integer attendanceId){
        Attendance attendance = attendanceMapper.selectById(attendanceId);
        Duration duration = Duration.between(attendance.getStartTime(), attendance.getEndTime());

        List<LocalDateTime> list = new ArrayList<>();
        for (long i = 0; i <= duration.toMinutes(); i ++){
            list.add(attendance.getStartTime().plusMinutes(i));
        }
        List<AttendanceDualAxes> res = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        for (int i = 0; i < list.size() - 1; i++) {
            queryWrapper
                    .ge("attendance_time", list.get(i))
                    .lt("attendance_time", list.get(i+1));
            AttendanceDualAxes data = new AttendanceDualAxes();
            data.setTime(dtf.format(list.get(i+1)));
            data.setCount(recordMapper.selectCount(queryWrapper));
            data.setValue(recordMapper.selectCount(queryWrapper));
            res.add(data);
            queryWrapper.clear();
        }
        return res;
    }
    @GetMapping("/queryAttendancePie/{attendanceId}")
    public List<AttendancePie> queryAttendancePie(@PathVariable("attendanceId") Integer attendanceId){
        List<AttendancePie> list = new ArrayList<>();
        Attendance attendance = attendanceMapper.selectById(attendanceId);
        QueryWrapper<Record> qw1 = new QueryWrapper<>();
        qw1
                .eq("attendance_id", attendanceId)
                .ne("att_reissue", "0")
                .isNull("att_vacation");
        int reissueCnt = recordMapper.selectCount(qw1);
        AttendancePie ap1 = new AttendancePie();
        ap1.setType("补签人数");
        ap1.setValue(reissueCnt);
        list.add(ap1);

        QueryWrapper<Record> qw2 = new QueryWrapper<>();
        qw2
                .eq("attendance_id", attendanceId)
                .eq("att_reissue", 0)
                .isNull("att_vacation");
        int recordCnt = recordMapper.selectCount(qw2);
        AttendancePie ap2 = new AttendancePie();
        ap2.setType("已签到人数");
        ap2.setValue(recordCnt);
        list.add(ap2);

        QueryWrapper<Record> qw3 = new QueryWrapper<>();
        qw3
                .eq("attendance_id", attendanceId)
                .eq("att_vacation", 1);
        int vacationCnt = recordMapper.selectCount(qw3);
        AttendancePie ap3 = new AttendancePie();
        ap3.setType("请假人数");
        ap3.setValue(vacationCnt);
        list.add(ap3);

        QueryWrapper<StudentClazz> qw4 = new QueryWrapper<>();
        qw4.eq("clazz_id", attendance.getClazzId());
        int allStudentCount = studentClazzMapper.selectCount(qw4);

        int notRecordCnt = allStudentCount - recordCnt - reissueCnt - vacationCnt;
        AttendancePie ap4 = new AttendancePie();
        ap4.setType("未签到人数");
        ap4.setValue(notRecordCnt);
        list.add(ap4);

        return list;
    }

    @GetMapping("/queryClazzColumn/{clazzId}")
    public List<ClazzDualAxesColumn> queryClazzColumn(@PathVariable("clazzId") Integer clazzId){
        List<ClazzDualAxesColumn> res = new ArrayList<>();

        QueryWrapper<StudentClazz> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Attendance> attendanceQueryWrapper = new QueryWrapper<>();
        attendanceQueryWrapper.eq("clazz_id", clazzId);
        Integer total = attendanceMapper.selectCount(attendanceQueryWrapper);

        queryWrapper.eq("clazz_id", clazzId);
        List<StudentClazz> list = studentClazzMapper.selectList(queryWrapper);

        for (StudentClazz sc : list){
            User user = userMapper.selectById(sc.getStudentId());
            Integer count = recordMapper.queryStudentSignInCount(sc.getStudentId(), sc.getClazzId());

            ClazzDualAxesColumn c = new ClazzDualAxesColumn();
            c.setName(user.getRealName());
            c.setCount(count);
            c.setType("签到次数(含批准的补签和请假)");
            res.add(c);

            ClazzDualAxesColumn column = new ClazzDualAxesColumn();
            column.setName(user.getRealName());
            column.setCount(total - count);
            column.setType("未签到次数");
            res.add(column);
        }
        return res;
    }

    @GetMapping("/queryClazzLine/{clazzId}")
    public List<ClazzDualAxesLine> queryClazzLine(@PathVariable("clazzId") Integer clazzId){
        List<ClazzDualAxesLine> res = new ArrayList<>();

        QueryWrapper<StudentClazz> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Attendance> attendanceQueryWrapper = new QueryWrapper<>();
        attendanceQueryWrapper.eq("clazz_id", clazzId);
        Integer total = attendanceMapper.selectCount(attendanceQueryWrapper);

        queryWrapper.eq("clazz_id", clazzId);
        List<StudentClazz> list = studentClazzMapper.selectList(queryWrapper);

        for (StudentClazz sc : list){
            User user = userMapper.selectById(sc.getStudentId());
            Integer count = recordMapper.queryStudentSignInCount(sc.getStudentId(), sc.getClazzId());

            ClazzDualAxesLine c = new ClazzDualAxesLine();
            c.setName(user.getRealName());
            c.setRate(count.doubleValue() / total.doubleValue());
            res.add(c);
        }
        return res;
    }

}
