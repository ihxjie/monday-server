package com.ihxjie.mondayserver.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ihxjie.mondayserver.api.common.QRCodeService;
import com.ihxjie.mondayserver.api.data.AttendanceWithName;
import com.ihxjie.mondayserver.api.entity.*;
import com.ihxjie.mondayserver.api.mapper.*;
import com.ihxjie.mondayserver.api.util.GouldUtil;
import com.ihxjie.mondayserver.api.vo.AttendanceVo;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 签到表 前端控制器
 * </p>
 *
 * @author xjie
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    StudentClazzMapper studentClazzMapper;
    @Autowired
    ClazzMapper clazzMapper;
    @Autowired
    PushMapper pushMapper;
    @Autowired
    QRCodeService qrCodeService;
    @Autowired
    NoticeMapper noticeMapper;

    @GetMapping("/queryAttendanceByClazzId")
    public List<Attendance> queryAttendanceByClazzId(Integer clazzId){
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>();

        // 设置签到状态
        LocalDateTime now = LocalDateTime.now();

        queryWrapper
                .eq("clazz_id", clazzId)
                .eq("status", 1);
        List<Attendance> attendanceList = attendanceMapper.selectList(queryWrapper);
        for (Attendance attendance : attendanceList){
            if (now.isAfter(attendance.getStartTime())){
                attendance.setStatus(2);
                attendanceMapper.updateById(attendance);
            }
        }
        queryWrapper.clear();

        queryWrapper
                .eq("clazz_id", clazzId)
                .eq("status", 2);
        attendanceList = attendanceMapper.selectList(queryWrapper);
        for (Attendance attendance : attendanceList){
            if (now.isAfter(attendance.getEndTime())){
                attendance.setStatus(3);
                attendanceMapper.updateById(attendance);
            }
        }
        queryWrapper.clear();

        queryWrapper
                .eq("clazz_id", clazzId)
                .orderByDesc("start_time");
        return attendanceMapper.selectList(queryWrapper);
    }

    @PostMapping("/add")
    public String addAttendance(@RequestBody AttendanceVo attendanceVo){
        Attendance attendance = new Attendance();
        Integer clazzId = attendanceVo.getClazzId();
        Clazz clazz = clazzMapper.selectById(clazzId);
        if (clazz.getIsFinish() == 1){
            return "班级已经解散，仅支持查看历史签到信息";
        }
        BeanUtils.copyProperties(attendanceVo, attendance);
        if (attendanceVo.getSubscribe() == null){
            attendanceVo.setSubscribe(false);
        }
        // 设置签到状态 true表示预约签到 false表示立即签到
        if (attendanceVo.getSubscribe()){
            attendance.setStatus(1);
        }else {
            attendance.setStatus(2);
        }
        if (attendance.getAttendanceType() == 3 || attendance.getAttendanceType() == 5
            || attendance.getAttendanceType() == 6)
        {
            GouldUtil gouldUtil = new GouldUtil();
            String location = gouldUtil.getAddressById(attendanceVo.getAttPosition());
            String[] position = location.split(",");
            attendance.setAttLongitude(position[0]);
            attendance.setAttLatitude(position[1]);
        }


        int res = attendanceMapper.insert(attendance);

        if (res != 0){
            List<Push> pushList = pushMapper.queryAllByClazzId(clazzId);
            for (Push push : pushList){
                String title = "签到通知";
                String description = clazz.getClazzName() + "课程发布了一个新的签到("
                        + getAttendanceType(attendance.getAttendanceType())
                        + ")，请及时签到！";
                String content = clazz.getClazzName()
                        + "^" + getAttendanceType(attendance.getAttendanceType())
                        + "^" + attendance.getStartTime();
                Notice notice = new Notice();
                notice.setNoticeTitle(title);
                notice.setNoticeContent(content);
                notice.setReceiverId(push.getUserId());
                notice.setIsRead(0);
                notice.setSenderId(1);
                noticeMapper.insert(notice);

                String regId = push.getRegId();
                Constants.useOfficial();
                Sender sender = new Sender("fAA2s6YCfoAXdsgY3l97kg==");

                Message message = new Message.Builder()
                        .title(title)
                        .description(description)
                        .restrictedPackageName("com.ihxjie.monday")
                        .notifyType(1)     // 使用默认提示音提示
                        .extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_LAUNCHER_ACTIVITY)
                        .build();
                try {
                    Result result = sender.send(message, regId, 3);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            return "签到发起成功";
        }
        return "签到发起失败";
    }

    private String getAttendanceType(Integer type){
        if (type == 1){
            return "点击签到";
        }else if (type == 2){
            return "二维码签到";
        }else if (type == 3){
            return "地理位置签到";
        }else if (type == 4){
            return "人脸识别签到";
        }else if (type == 5){
            return "人脸+地理位置签到";
        }else if (type == 6){
            return "地理位置+二维码签到";
        }else {
            return "未定义签到";
        }
    }

    @PostMapping("/update")
    public Boolean updateAttendance(@RequestBody AttendanceVo attendanceVo){
        Attendance attendance = new Attendance();
        BeanUtils.copyProperties(attendanceVo, attendance);
        if (attendanceVo.getSubscribe() == null){
            attendanceVo.setSubscribe(false);
        }
        // 设置签到状态 true表示预约签到 false表示立即签到
        if (attendanceVo.getSubscribe()){
            attendance.setStatus(1);
        }else {
            attendance.setStatus(2);
        }
        int res = attendanceMapper.updateById(attendance);
        return res != 0;
    }

    @GetMapping("/queryProcess/{attendanceId}")
    public Double queryProcess(@PathVariable("attendanceId") Integer attendanceId){
        Attendance attendance = attendanceMapper.selectById(attendanceId);
        QueryWrapper<StudentClazz> sc = new QueryWrapper<>();
        sc.eq("clazz_id", attendance.getClazzId());
        double cnt = studentClazzMapper.selectCount(sc);
        QueryWrapper<Record> qw = new QueryWrapper<>();
        qw.eq("attendance_id", attendanceId);
        double res = recordMapper.selectCount(qw);
        return res / cnt;
    }

    @GetMapping("/queryAttendanceInfo")
    public AttendanceWithName queryAttendanceInfo(Integer attendanceId){
        AttendanceWithName attendanceWithName = new AttendanceWithName();
        Attendance attendance = attendanceMapper.selectById(attendanceId);
        BeanUtils.copyProperties(attendance, attendanceWithName);
        Clazz clazz = clazzMapper.selectById(attendance.getClazzId());
        attendanceWithName.setClazzName(clazz.getClazzName());
        return attendanceWithName;
    }

    @GetMapping("/finishAttendanceStep2")
    public String finishAttendanceStep2(Integer attendanceId){
        Attendance attendance = attendanceMapper.selectById(attendanceId);
        LocalDateTime ldt = LocalDateTime.now();
        if (ldt.isBefore(attendance.getEndTime())){
            attendance.setEndTime(ldt);
        }
        attendance.setStatus(3);
        int res = attendanceMapper.updateById(attendance);
        if (res == 0){
            return "结束签到请求失败";
        }
        return "已结束签到";
    }

    @GetMapping("/finishAttendanceStep3")
    public String finishAttendanceStep3(Integer attendanceId){
        Attendance attendance = attendanceMapper.selectById(attendanceId);
        attendance.setStatus(4);
        int res = attendanceMapper.updateById(attendance);
        if (res == 0){
            return "结束签到请求失败";
        }
        return "已结束补签签到";
    }

    @PostMapping("/removeAttendance")
    public String removeAttendance(@RequestBody Attendance attendance){
        int res = attendanceMapper.deleteById(attendance.getAttendanceId());
        if (res == 0){
            return "移除签到失败";
        }
        return "已移除该签到";
    }

    @GetMapping("/getDynamicQrcode/{attendanceId}")
    public String getDynamicQrcode(@PathVariable("attendanceId") Integer attendanceId) throws Exception {
        LocalDateTime ldt = LocalDateTime.now();
        ldt = ldt.plusSeconds(2);
        LocalDateTime ldt2 = ldt.plusSeconds(7);
        long start = ldt.toEpochSecond(ZoneOffset.ofHours(8));
        long end = ldt2.toEpochSecond(ZoneOffset.ofHours(8));
        StringBuilder sb = new StringBuilder();
        sb.append(com.ihxjie.mondayserver.api.common.Constants.host)
                .append("api/record/attQrcode?attendanceId=").append(attendanceId)
                .append("&startTime=").append(start)
                .append("&endTime=").append(end);

        return qrCodeService.crateQRCode(sb.toString(), 1000, 1000);
    }

}
