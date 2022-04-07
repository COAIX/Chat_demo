package com.coaix.chatdemo.root.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiaoWei
 * @date 2022-04-06 23:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String userName;
    private String password;

}
