package com.ihxjie.mondayserver.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xjie
 * @date 2021/5/17 1:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RecordVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String username;

    private String password;

    private String realName;

    private String userTel;

    private String userEmail;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String userAvatar;

    private String roles;

    private String userSignature;

    private String userTitle;

    /**
     * 签到时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime attendanceTime;

    private String attendanceType;

}
