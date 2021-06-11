package com.ihxjie.mondayserver.api.data;

import lombok.Data;

/**
 * @author xjie
 * @date 2021/4/12 20:23
 */
@Data
public class CurrentUser {
    String name;
    String avatar;
    String userid;
    // String notice: NoticeType[];
    String email;
    String signature;
    String title;
    String group;
    // String tags: TagType[];
    String notifyCount;
    String unreadCount;
    // String country;
    // String geographic: GeographicType;
    ///String address;
    String phone;
}
