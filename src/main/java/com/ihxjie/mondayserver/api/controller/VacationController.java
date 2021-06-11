package com.ihxjie.mondayserver.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ihxjie.mondayserver.api.entity.Record;
import com.ihxjie.mondayserver.api.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xjie
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/api/vacation")
public class VacationController {
    @Autowired
    RecordMapper recordMapper;

    @GetMapping("/agreeVacation")
    public String agreeVacation(Integer userId, Integer attendanceId){
//        Record record = new Record();
//        record.setAttendanceId(attendanceId.longValue());
//        record.setStudentId(userId);
//        record.setAttVacation(1);

        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("attendance_id", attendanceId)
                .eq("student_id", userId)
                .eq("att_vacation", 1);
        Record record = recordMapper.selectOne(queryWrapper);

        if (record == null){
            record = new Record();
            record.setAttendanceId(attendanceId.longValue());
            record.setStudentId(userId);
            record.setAttVacation(1);

            int res = recordMapper.insert(record);
            if (res == 0){
                return "请求失败";
            }
            return "已同意该请假请求";
        }
        return "改请假请求已同意，请勿重复提交";

    }


}
