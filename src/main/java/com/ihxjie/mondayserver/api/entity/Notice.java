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
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;

    private LocalDateTime noticeTime;

    private String noticeTitle;

    private String noticeContent;

    private Integer senderId;

    private Integer receiverId;

    private Integer isRead;

}
