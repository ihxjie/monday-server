package com.ihxjie.mondayserver.modules.service.impl;

import com.ihxjie.mondayserver.modules.entity.Attendance;
import com.ihxjie.mondayserver.modules.mapper.AttendanceMapper;
import com.ihxjie.mondayserver.modules.service.IAttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 签到表 服务实现类
 * </p>
 *
 * @author xjie
 * @since 2021-03-28
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements IAttendanceService {

}
