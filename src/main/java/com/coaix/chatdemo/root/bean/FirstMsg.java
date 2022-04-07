package com.coaix.chatdemo.root.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiaoWei
 * @date 2022-04-06 23:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstMsg {
    private String userid;
    private String type;
    private String msg;
}
// <el-timeline-item timestamp="123213" placement="bottom" type="success">
// <el-card>
// <h4>更新 Github 模板</h4>
// <p>王小虎 提交于 2018/4/12 20:46</p>
// </el-card>
// </el-timeline-item>