package com.ihxjie.mondayserver.api.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author xjie
 * @date 2021/6/2 13:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClazzDualAxesLine implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Double rate;
}
