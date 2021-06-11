package com.ihxjie.mondayserver.api.map;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xjie
 * @date 2021/6/2 18:16
 */
@Data
public class Poi implements Serializable {
    private String name;
    private String id;
    private String location;

    private String type;
    private String typecode;
    private String pname;
    private String cityname;
    private String adname;
    private String address;
    private String pcode;
    private String adcode;
    private String citycode;



}
