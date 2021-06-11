package com.ihxjie.mondayserver.api.controller;


import com.ihxjie.mondayserver.api.entity.Push;
import com.ihxjie.mondayserver.api.mapper.PushMapper;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xjie
 * @since 2021-06-08
 */
@RestController
@RequestMapping("/api/push")
public class PushController {
    @Autowired
    PushMapper pushMapper;

    @PostMapping("/updateRegId")
    public String updateRegId(@RequestBody Push push){
        Push p = pushMapper.selectById(push.getUserId());
        int res;
        if (p == null){
            res = pushMapper.insert(push);
        }else {
            res = pushMapper.updateById(push);
        }
        if (res != 0){
            return "regId更新成功";
        }
        return "regId更新失败";
    }
}
