package com.ihxjie.mondayserver.api.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xjie
 * @date 2021/6/2 16:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClazzInfo implements Serializable {

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

    private String clazzTeacher;

    /**
     * 班级解散
     */
    private Integer isFinish;

    /**
     * 活跃度
     */
    private Integer activeLevel;


}
