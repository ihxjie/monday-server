package com.ihxjie.mondayserver.api.map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author xjie
 * @date 2021/6/2 19:53
 */
@Data
public class MapVo implements Serializable {
    private String label;
    private String value;
}
