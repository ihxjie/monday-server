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
 * 
 * </p>
 *
 * @author xjie
 * @since 2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 签到记录id
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;

    /**
     * 签到id
     */
    private Long attendanceId;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 签到时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime attendanceTime;

    /**
     * 点击签到
     */
    private Integer attClick;

    /**
     * 二维码签到
     */
    private Integer attQrcode;

    /**
     * 地理位置签到
     */
    private Integer attGps;

    /**
     * 人脸识别签到
     */
    private Integer attFace;

    /**
     * 补签签到
     */
    private Integer attReissue;

    /**
     * 请假
     */
    private Integer attVacation;


}
