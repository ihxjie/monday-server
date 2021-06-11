package com.ihxjie.mondayserver.api.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ihxjie.mondayserver.api.entity.Attendance;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xjie
 * @date 2021/4/20 15:20
 */
@Data
public class AttendanceInfo {
    /**
     * 签到id
     */
    private Long attendanceId;

    /**
     * 签到班级
     */
    private Integer clazzId;

    /**
     * 签到开始时间
     */
    private LocalDateTime startTime;

    /**
     * 签到结束时间
     */
    private LocalDateTime endTime;

    /**
     * 签到方式（普通签到：1；二维码签到：2；地理位置签到：3；人脸识别签到：4；人脸+地理位置签到：5；地理位置+二维码签到：6）
     */
    private Integer attendanceType;
}
