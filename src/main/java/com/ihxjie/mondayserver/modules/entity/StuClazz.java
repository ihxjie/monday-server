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
 * @since 2021-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StuClazz implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer studentId;

    private Integer clazzId;


}
