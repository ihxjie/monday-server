package com.ihxjie.mondayserver.api.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author xjie
 * @date 2021/6/2 13:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClazzDualAxesColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer count;

    private String type;
}
