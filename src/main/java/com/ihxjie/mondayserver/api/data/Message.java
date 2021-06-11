package com.ihxjie.mondayserver.api.data;

import lombok.Data;

/**
 * @author xjie
 * @date 2021/4/12 21:43
 */
@Data
public class Message {
    Integer id;
    String avatar;
    String title;
    String description;
    String datetime;
    String type;
    String clickClose;
    Integer read;
}
