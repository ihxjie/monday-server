package com.ihxjie.mondayserver.api.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xjie
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Push implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Integer userId;

    /**
     * regId
     */
    private String regId;


}
