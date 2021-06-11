package com.ihxjie.mondayserver.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xjie
 * @date 2021/5/17 13:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VacationVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    private String realName;

    private String userTel;

    private String userEmail;

    private String userAvatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    private Integer dayNum;

    private String reason;
}
