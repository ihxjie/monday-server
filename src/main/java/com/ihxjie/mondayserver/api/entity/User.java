package com.ihxjie.mondayserver.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xjie
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
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


}
