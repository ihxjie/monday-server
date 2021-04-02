package com.ihxjie.mondayserver.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 签到表
 * </p>
 *
 * @author xjie
 * @since 2021-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 签到id
     */
    @TableId(value = "attendance_id", type = IdType.AUTO)
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
