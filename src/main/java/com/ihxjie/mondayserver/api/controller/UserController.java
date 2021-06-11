package com.ihxjie.mondayserver.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ihxjie.mondayserver.api.common.AliyunOss;
import com.ihxjie.mondayserver.api.common.Constants;
import com.ihxjie.mondayserver.api.data.Classes;
import com.ihxjie.mondayserver.api.data.CurrentUser;
import com.ihxjie.mondayserver.api.data.Message;
import com.ihxjie.mondayserver.api.entity.Clazz;
import com.ihxjie.mondayserver.api.entity.Notice;
import com.ihxjie.mondayserver.api.entity.TeacherClazz;
import com.ihxjie.mondayserver.api.entity.User;
import com.ihxjie.mondayserver.api.mapper.ClazzMapper;
import com.ihxjie.mondayserver.api.mapper.NoticeMapper;
import com.ihxjie.mondayserver.api.mapper.TeacherClazzMapper;
import com.ihxjie.mondayserver.api.mapper.UserMapper;
import com.ihxjie.mondayserver.api.service.IClazzService;
import com.ihxjie.mondayserver.api.vo.MobileUser;
import com.ihxjie.mondayserver.api.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author xjie
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    ClazzMapper clazzMapper;
    @Autowired
    IClazzService clazzService;
    @Autowired
    TeacherClazzMapper teacherClazzMapper;

    private String getUserId(HttpSession session){
        return session.getAttribute("userId").toString();
    }

    @PostMapping("/login/account")
    public Map<String, Object> getAccount(@RequestBody UserVo user, HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        List<String> auth = new ArrayList<>();

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("username", user.getUserName())
                .eq("password", user.getPassword());

        User u = userMapper.selectOne(wrapper);
        if (u != null){
            auth.add(u.getRoles());
            data.put("status", "ok");
            data.put("type", "account");
            data.put("currentAuthority", auth);
            session.setAttribute("userId", u.getUserId());
            return data;

        }
        auth.add("guest");
        data.put("status", "error");
        data.put("type", "account");
        data.put("currentAuthority", auth);
        return data;
    }

    @PostMapping("/login/mobile")
    public String loginMobile(@RequestBody MobileUser mobileUser, HttpSession session){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("username", mobileUser.getUserName())
                .eq("password", mobileUser.getPassword());

        User user = userMapper.selectOne(wrapper);
        session.setAttribute("userId", user.getUserId());
        return user.getUserId().toString();
    }

    @GetMapping("/currentUser")
    public Map<String, Object> getCurrentUser(HttpSession session){
        Map<String, Object> data = new HashMap<>();

        // String userId = session.getAttribute("userId").toString();
        String userId = getUserId(session);

        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("receiver_id", userId);
        Integer notifyCount = noticeMapper.selectCount(queryWrapper);
        queryWrapper.clear();

        queryWrapper
                .eq("receiver_id", userId)
                .eq("is_read", 0);
        Integer unreadCount = noticeMapper.selectCount(queryWrapper);

        User u = userMapper.selectById(userId);
        data.put("userid", u.getUserId());
        data.put("name", u.getRealName());
        data.put("avatar", u.getUserAvatar());
        data.put("email", u.getUserEmail());
        data.put("phone", u.getUserTel());
        data.put("signature", u.getUserSignature());
        data.put("title", u.getUserTitle());
        data.put("group", "福建工程学院－计算机科学与数学学院－计算机科学与技术-教研室");
        data.put("notifyCount", notifyCount);
        data.put("unreadCount", unreadCount);

        return data;
    }

    @GetMapping("/mobile/currentUser")
    public Map<String, Object> getMobileCurrentUser(String userId, HttpSession session){
        Map<String, Object> data = new HashMap<>();
        if (userId == null){
            userId = getUserId(session);
        }
        // String userId = session.getAttribute("userId").toString();

        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("receiver_id", userId);
        Integer notifyCount = noticeMapper.selectCount(queryWrapper);
        queryWrapper.clear();

        queryWrapper
                .eq("receiver_id", userId)
                .eq("is_read", 0);
        Integer unreadCount = noticeMapper.selectCount(queryWrapper);

        User u = userMapper.selectById(userId);
        data.put("userid", u.getUserId());
        data.put("name", u.getRealName());
        data.put("avatar", u.getUserAvatar());
        data.put("email", u.getUserEmail());
        data.put("phone", u.getUserTel());
        data.put("signature", u.getUserSignature());
        data.put("title", u.getUserTitle());
        data.put("group", "福建工程学院－计算机科学与数学学院－计算机科学与技术-1702");
        data.put("notifyCount", notifyCount);
        data.put("unreadCount", unreadCount);

        return data;
    }

    @GetMapping("/notices")
    public List<Message> getNotices(HttpSession session){

        String userId = getUserId(session);

        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("receiver_id", userId);
        List<Notice> notices = noticeMapper.selectList(queryWrapper);
        List<Message> messages = new ArrayList<>();

        for (Notice notice : notices){
            Message cn = new Message();
            cn.setId(notice.getNoticeId());
            cn.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/GvqBnKhFgObvnSGkDsje.png");
            cn.setTitle(notice.getNoticeTitle());
            cn.setDescription(notice.getNoticeContent());
            cn.setDatetime(notice.getNoticeTime().toString());
            cn.setType("notification");
            cn.setClickClose("true");
            cn.setRead(notice.getIsRead());
            messages.add(cn);
        }
        return messages;
    }

    @GetMapping("/classes")
    public List<Classes> getClasses(HttpSession session){
        String userId = getUserId(session);
        return clazzMapper.queryClasses(userId);
    }

    @PostMapping("/addClazz")
    public Map<String, Object> addClass(@RequestBody Clazz clazz, HttpSession session){
        String userId = getUserId(session);
        Map<String, Object> map = new HashMap<>();
        int res = clazzMapper.insert(clazz);

        // 添加老师与班级之间的关联
        TeacherClazz tc = new TeacherClazz();
        tc.setTeacherId(Integer.parseInt(userId));
        tc.setClazzId(clazz.getClazzId());
        teacherClazzMapper.insert(tc);

        if (res == 1){
            map.put("message", "Ok");
            map.put("qrcodePath", Constants.host + "mobile/go-class/" + clazz.getClazzId().toString());
        }else {
            map.put("message", "Failed");
        }
        return map;
    }

    @Autowired
    AliyunOss aliyunOss;

    @PostMapping("/testFile")
    public Map<String, String> testFile(@RequestBody MultipartFile file){
        Map<String, String> res = new HashMap<>();
        try {
            res = aliyunOss.upload(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @PostMapping("/updateAvatar")
    public String updateAvatar(String userId, String avatar){
        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setUserAvatar(avatar);
        int res = userMapper.updateById(user);
        if (res != 0){
            return "头像修改成功";
        }
        return "头像修改失败";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody CurrentUser currentUser){
        User user = new User();
        user.setUserId(Integer.parseInt(currentUser.getUserid()));
        user.setRealName(currentUser.getName());
        user.setUserTel(currentUser.getPhone());
        user.setUserEmail(currentUser.getEmail());
        user.setUserSignature(currentUser.getSignature());
        int res = userMapper.updateById(user);
        if (res != 0){
            return "个人信息修改成功";
        }
        return "修改失败";
    }

    @GetMapping("/user/queryStudentByClazzId")
    public List<User> queryStudentByClazzId(Integer clazzId){
        return userMapper.queryStudentByClazzId(clazzId);
    }

}
