package com.ihxjie.mondayserver.api.service.impl;

import com.ihxjie.mondayserver.api.entity.Attendance;
import com.ihxjie.mondayserver.api.mapper.AttendanceMapper;
import com.ihxjie.mondayserver.api.service.IAttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 签到表 服务实现类
 * </p>
 *
 * @author xjie
 * @since 2021-04-08
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements IAttendanceService {

}
