package com.ihxjie.mondayserver.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ihxjie.mondayserver.api.data.Classes;
import com.ihxjie.mondayserver.api.entity.Attendance;
import com.ihxjie.mondayserver.api.entity.Clazz;
import com.ihxjie.mondayserver.api.entity.Notice;
import com.ihxjie.mondayserver.api.entity.StudentClazz;
import com.ihxjie.mondayserver.api.mapper.AttendanceMapper;
import com.ihxjie.mondayserver.api.mapper.ClazzMapper;
import com.ihxjie.mondayserver.api.mapper.NoticeMapper;
import com.ihxjie.mondayserver.api.mapper.StudentClazzMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xjie
 * @date 2021/4/20 15:32
 */
@RestController
@RequestMapping("/api/mobile")
public class MobileController {

    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    ClazzMapper clazzMapper;
    @Autowired
    StudentClazzMapper studentClazzMapper;
    @Autowired
    NoticeMapper noticeMapper;

    private String getUserId(HttpSession session){
        // return session.getAttribute("userId").toString();
        return "6";
    }

    @GetMapping("/getClazzInfo")
    public Clazz getClazzInfo(String clazzId){
        return clazzMapper.selectById(Integer.parseInt(clazzId));
    }

    @GetMapping("/getAttendanceByClazzId")
    public List<Attendance> getAttendanceByClazzId(String clazzId){
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("clazz_id", clazzId);
        return attendanceMapper.selectList(queryWrapper);
    }

    @GetMapping("/queryStuClazz")
    public List<Classes> queryStuClazz(String userId){
        return clazzMapper.queryStuClazz(userId);
    }

    @GetMapping("/quitClazz")
    public String quitClazz(String clazzId, Integer userId){
        QueryWrapper<StudentClazz> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("clazz_id", clazzId)
                .eq("student_id", userId);
        int res = studentClazzMapper.delete(queryWrapper);
        if (res > 0){
            return "success";
        }
        return "failed";
    }

    @GetMapping("/getStuNotice")
    public List<Notice> getStuNotice(Integer userId){

        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("receiver_id", userId)
                .orderByDesc("notice_time");
        return noticeMapper.selectList(queryWrapper);
    }

}
