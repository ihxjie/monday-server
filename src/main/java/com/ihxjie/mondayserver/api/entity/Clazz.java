package com.ihxjie.mondayserver.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 班级
 * </p>
 *
 * @author xjie
 * @since 2021-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Clazz implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "clazz_id", type = IdType.AUTO)
    private Integer clazzId;

    /**
     * 班级名称
     */
    private String clazzName;

    private String clazzLogo;

    private String clazzDescription;

    private LocalDateTime createdAt;

    /**
     * 班级解散
     */
    private Integer isFinish;

    /**
     * 活跃度
     */
    private Integer activeLevel;


}
