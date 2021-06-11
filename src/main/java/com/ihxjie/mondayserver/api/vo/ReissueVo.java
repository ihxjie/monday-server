package com.ihxjie.mondayserver.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xjie
 * @date 2021/5/21 22:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReissueVo implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 补签id
     */
    private Integer reissueId;

    /**
     * 补签理由
     */
    private String reason;

    /**
     * 申请时间
     */
    private LocalDateTime createTime;

    /**
     * 申请用户
     */
    private Integer userId;

    /**
     * 同意：1 拒绝：2
     */
    private Integer isAgree;


}
