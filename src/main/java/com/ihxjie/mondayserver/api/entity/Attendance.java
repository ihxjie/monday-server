package com.ihxjie.mondayserver.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 签到表
 * </p>
 *
 * @author xjie
 * @since 2021-05-17
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
     * 签到经度
     */
    private String attLongitude;

    /**
     * 签到纬度
     */
    private String attLatitude;

    /**
     * 签到精度
     */
    private String attAccuracy;

    /**
     * 签到状态：1：发起签到，2：接收学生签到，3：接收补签请求，4：签到结束
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;


}
