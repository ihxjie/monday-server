package com.ihxjie.mondayserver.api.map;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xjie
 * @date 2021/6/2 18:16
 */
@Data
public class MapInfo implements Serializable {

    private String status;
    private String info;
    private String infocode;
    private String count;
    private List<Poi> pois;

}
