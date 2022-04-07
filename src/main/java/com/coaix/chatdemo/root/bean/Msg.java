package com.coaix.chatdemo.root.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiaoWei
 * @date 2022-04-06 23:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Msg {
    private String userid;
    private String msg;
}
