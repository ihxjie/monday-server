package com.ihxjie.mondayserver.modules.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xjie
 * @since 2021-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 签到记录id
     */
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
    private LocalDateTime attendanceTime;

    private Integer attClick;

    private Integer attQrcode;

    private Integer attGps;

    private Integer attFace;


}
