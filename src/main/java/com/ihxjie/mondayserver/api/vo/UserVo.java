package com.ihxjie.mondayserver.api.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author xjie
 * @date 2021/6/8 13:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean autoLogin;

    private String userName;

    private String account;

    private String password;

}
