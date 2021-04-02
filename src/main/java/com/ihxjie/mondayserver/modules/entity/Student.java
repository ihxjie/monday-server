package com.ihxjie.mondayserver.modules.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author xjie
 * @since 2021-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer stuId;

    private String stuName;

    private String stuTel;

    private String stuEmail;

    private String stuUsername;

    private String stuPassword;


}
