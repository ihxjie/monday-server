package com.ihxjie.mondayserver.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.ihxjie.mondayserver.api.entity.Record;
import com.ihxjie.mondayserver.api.entity.Reissue;
import com.ihxjie.mondayserver.api.mapper.RecordMapper;
import com.ihxjie.mondayserver.api.mapper.ReissueMapper;
import com.ihxjie.mondayserver.api.vo.ReissueVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xjie
 * @since 2021-05-21
 */
@RestController
@RequestMapping("/api/reissue")
public class ReissueController {

    @Autowired
    ReissueMapper reissueMapper;
    @Autowired
    RecordMapper recordMapper;

    @GetMapping("/queryReissueByAttendanceId")
    public List<ReissueVo> queryReissueByAttendanceId(Integer attendanceId){
        return reissueMapper.getReissueByAttendanceId(attendanceId);
    }

    @GetMapping("/agreeReissue")
    public String agreeReissue(Integer reissueId){
        Reissue reissue = reissueMapper.selectById(reissueId);
        reissue.setIsAgree(1);
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attendance_id", reissue.getAttendanceId());
        Record record = recordMapper.selectOne(queryWrapper);
        int res = reissueMapper.updateById(reissue);
        if (record == null){
            record = new Record();
            record.setStudentId(reissue.getUserId());
            record.setAttendanceId(reissue.getAttendanceId().longValue());
            record.setAttReissue(1);
            recordMapper.insert(record);
        }else {
            return "已补签成功";
        }

        if (res == 0){
            return "请求失败";
        }
        return "已同意该补签请求";
    }

    @GetMapping("/rejectReissue")
    public String rejectReissue(Integer reissueId){
        Reissue reissue = reissueMapper.selectById(reissueId);
        reissue.setIsAgree(2);
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("attendance_id", reissue.getAttendanceId())
                .eq("student_id", reissue.getUserId())
                .ne("att_reissue", 0);
        recordMapper.delete(queryWrapper);
        int res = reissueMapper.updateById(reissue);
        if (res == 0){
            return "请求失败";
        }
        return "已拒绝该补签请求";
    }

    @GetMapping("/manualReissue")
    public String manualReissue(Integer studentId, Integer attendanceId){

        QueryWrapper<Reissue> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("attendance_id", attendanceId)
                .eq("user_id", studentId);
        Reissue reissue = reissueMapper.selectOne(queryWrapper);
        if (reissue == null){
            reissue = new Reissue();
            reissue.setAttendanceId(attendanceId);
            reissue.setUserId(studentId);
            reissue.setReason("教师手动补签");
            reissue.setIsAgree(1);
            reissueMapper.insert(reissue);
        }else {
            reissue.setReason("教师手动补签");
            reissue.setIsAgree(1);
            reissueMapper.updateById(reissue);
        }

        Record record = new Record();
        record.setAttendanceId(attendanceId.longValue());
        record.setAttReissue(reissue.getReissueId());
        record.setStudentId(studentId);
        int res = recordMapper.insert(record);
        if (res == 0) {
            return "请求失败";
        }
        return "已手动补签";
    }

}
