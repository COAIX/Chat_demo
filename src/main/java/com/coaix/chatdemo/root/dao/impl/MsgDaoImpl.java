package com.coaix.chatdemo.root.dao.impl;

import com.coaix.chatdemo.root.dao.MsgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author LiaoWei
 * @date 2022-04-06 23:13
 */
public class MsgDaoImpl implements MsgDao {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void addinList(String key, String value) {

    }

    @Override
    public void getwithList(String key) {

    }
}
