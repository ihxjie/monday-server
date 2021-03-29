package com.ihxjie.mondayserver.modules.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xjie
 * @since 2021-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherClazz implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 班级id
     */
    private Integer clazzId;


}
