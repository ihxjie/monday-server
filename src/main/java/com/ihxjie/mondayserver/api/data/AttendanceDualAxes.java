package com.ihxjie.mondayserver.api.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author xjie
 * @date 2021/5/30 22:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttendanceDualAxes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String time;

    private Integer value;

    private Integer count;
}
