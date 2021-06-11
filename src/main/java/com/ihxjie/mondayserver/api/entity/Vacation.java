package com.ihxjie.mondayserver.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xjie
 * @since 2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Vacation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 假期id
     */
    @TableId(value = "vacation_id", type = IdType.AUTO)
    private Integer vacationId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 请假天数
     */
    private Integer dayNum;

    /**
     * 请假理由
     */
    private String reason;

    /**
     * 请假申请人
     */
    private Integer userId;

    /**
     * 是否结束：1：结束
     */
    private Integer isFinish;


}
