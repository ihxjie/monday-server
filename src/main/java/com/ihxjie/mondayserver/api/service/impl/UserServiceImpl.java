package com.ihxjie.mondayserver.api.service.impl;

import com.ihxjie.mondayserver.api.entity.User;
import com.ihxjie.mondayserver.api.mapper.UserMapper;
import com.ihxjie.mondayserver.api.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author xjie
 * @since 2021-04-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
