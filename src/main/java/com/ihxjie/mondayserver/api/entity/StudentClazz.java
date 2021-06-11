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
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StudentClazz implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sc_id", type = IdType.AUTO)
    private Integer scId;

    private Integer studentId;

    private Integer clazzId;


}
