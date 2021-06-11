package com.ihxjie.mondayserver.api.controller;


import com.ihxjie.mondayserver.api.entity.StudentClazz;
import com.ihxjie.mondayserver.api.mapper.StudentClazzMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
@RequestMapping("/mobile")
public class StudentClazzController {

    @Autowired
    StudentClazzMapper studentClazzMapper;

    private String getUserId(HttpSession session){
        // return session.getAttribute("userId").toString();
        return "6";
    }

    @GetMapping("/go-class/{clazzId}/{userId}")
    public Map<String, Object> goClass(@PathVariable("clazzId") String clazzId,
                                       @PathVariable("userId") String userId){
        Map<String, Object> map = new HashMap<>();
        
        StudentClazz sc = new StudentClazz();
        sc.setStudentId(Integer.parseInt(userId));
        sc.setClazzId(Integer.parseInt(clazzId));

        int res = studentClazzMapper.insert(sc);
        if (res == 1){
            map.put("message", "加入班级成功");
        }else {
            map.put("message", "请求失败");
        }
        return map;
    }

}
