package com.ihxjie.mondayserver.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xjie
 * @since 2021-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PositionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "position_id", type = IdType.AUTO)
    private Integer positionId;

    private Long attendanceId;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 精度
     */
    private String accuracy;

    /**
     * 提供者
     */
    private String provider;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域
     */
    private String district;

    /**
     * 具体地址
     */
    private String address;

    /**
     * 兴趣点
     */
    private String poi;

    /**
     * 定位类型
     */
    private String locationType;

    /**
     * GPS星数
     */
    private String gpsSatellites;

    /**
     * 距离
     */
    private String distance;


}
