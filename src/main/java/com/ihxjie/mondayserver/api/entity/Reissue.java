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
 * @since 2021-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Reissue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 补签id
     */
    @TableId(value = "reissue_id", type = IdType.AUTO)
    private Integer reissueId;

    /**
     * 签到id
     */
    private Integer attendanceId;

    /**
     * 补签理由
     */
    private String reason;

    /**
     * 申请时间
     */
    private LocalDateTime createTime;

    /**
     * 申请用户
     */
    private Integer userId;

    /**
     * 同意：1 拒绝：2
     */
    private Integer isAgree;


}
