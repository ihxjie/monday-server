package com.ihxjie.mondayserver.api.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author xjie
 * @date 2021/5/31 1:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttendancePie implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;

    private Integer value;

}
