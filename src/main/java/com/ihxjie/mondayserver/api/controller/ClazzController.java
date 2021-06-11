package com.ihxjie.mondayserver.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ihxjie.mondayserver.api.data.ClazzInfo;
import com.ihxjie.mondayserver.api.entity.Clazz;
import com.ihxjie.mondayserver.api.entity.StudentClazz;
import com.ihxjie.mondayserver.api.entity.TeacherClazz;
import com.ihxjie.mondayserver.api.entity.User;
import com.ihxjie.mondayserver.api.mapper.ClazzMapper;
import com.ihxjie.mondayserver.api.mapper.StudentClazzMapper;
import com.ihxjie.mondayserver.api.mapper.TeacherClazzMapper;
import com.ihxjie.mondayserver.api.mapper.UserMapper;
import jdk.nashorn.internal.ir.LiteralNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 班级 前端控制器
 * </p>
 *
 * @author xjie
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/api/clazz")
public class ClazzController {

    @Autowired
    ClazzMapper clazzMapper;
    @Autowired
    StudentClazzMapper studentClazzMapper;
    @Autowired
    TeacherClazzMapper teacherClazzMapper;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/clazzInfo")
    public ClazzInfo queryClazzInfo(Integer clazzId){
        ClazzInfo clazzInfo = new ClazzInfo();
        Clazz clazz = clazzMapper.selectById(clazzId);
        BeanUtils.copyProperties(clazz, clazzInfo);

        QueryWrapper<TeacherClazz> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("clazz_id", clazzId);
        List<TeacherClazz> list = teacherClazzMapper.selectList(queryWrapper);
        StringBuilder sb = new StringBuilder();
        for (TeacherClazz tc : list){
            User u = userMapper.selectById(tc.getTeacherId());
            sb.append(u.getRealName()).append("；");
        }
        clazzInfo.setClazzTeacher(sb.toString());
        return clazzInfo;
    }

    @PostMapping("/removeStudent")
    public String removeStuFromClazzByClazzId(@RequestBody StudentClazz studentClazz){
        QueryWrapper<StudentClazz> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("student_id", studentClazz.getStudentId())
                .eq("clazz_id", studentClazz.getClazzId());
        int res = studentClazzMapper.delete(queryWrapper);
        if (res == 0){
            return "移除失败";
        }
        return "移除成功";
    }
    @GetMapping("/finishClazz")
    public String finishClazz(Integer clazzId){
        Clazz clazz = new Clazz();
        clazz.setClazzId(clazzId);
        clazz.setIsFinish(1);
        int res = clazzMapper.updateById(clazz);
        if (res != 0){
            return "班级已结束";
        }
        return "发生错误";
    }

}
