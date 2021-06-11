package com.ihxjie.mondayserver.api.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author xjie
 * @date 2021/6/8 18:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MobileUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;

    private String password;
}
