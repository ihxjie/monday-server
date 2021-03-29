package com.ihxjie.mondayserver.modules.service.impl;

import com.ihxjie.mondayserver.modules.entity.Student;
import com.ihxjie.mondayserver.modules.mapper.StudentMapper;
import com.ihxjie.mondayserver.modules.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author xjie
 * @since 2021-03-28
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
