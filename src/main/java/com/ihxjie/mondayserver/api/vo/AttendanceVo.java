package com.ihxjie.mondayserver.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xjie
 * @date 2021/5/15 22:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttendanceVo implements Serializable {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /**
     * 签到结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    /**
     * 签到方式（普通签到：1；二维码签到：2；地理位置签到：3；人脸识别签到：4；人脸+地理位置签到：5；地理位置+二维码签到：6）
     */
    private Integer attendanceType;

    /**
     * Poi ID
     */
    private String attPosition;

    /**
     * 签到精度
     */
    private String attAccuracy;

    /**
     * 预约签到：开启：true 关闭：false
     */
    private Boolean subscribe;


}
