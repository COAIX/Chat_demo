package com.coaix.chatdemo.root.controller;

import com.alibaba.fastjson.JSON;
import com.coaix.chatdemo.root.bean.FirstMsg;
import com.coaix.chatdemo.root.bean.Msg;
import com.coaix.chatdemo.root.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author LiaoWei
 * @date 2021-10-03 20:23
 */
@Slf4j
@RestController
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private WebSocketService webSocketService;


    @RequestMapping("/testRedis")
    public String test1() {
        redisTemplate.opsForValue().set("name", "jackey");
        // redisTemplate.opsForHash().put("name","tjoe","set");
        return redisTemplate.opsForValue().get("name");
    }

    @GetMapping("/testRedis2")
    public String test2(HttpSession httpSession) {
        httpSession.setAttribute("Nanni", "123465");
        return "TestSession";
    }

    /**
     * @param id
     * @param password
     * @return
     */
    @GetMapping("/login")
    public boolean login(String id, String password) {
        String o = (String) redisTemplate.opsForHash().get("chat_demo:user", id);
        return Objects.equals(password, o);
    }

    /**
     * @param userid
     * @param value
     * @return
     */
    @GetMapping("/add")
    public String add(String userid, String value) {
        Msg message = new Msg(userid, value);
        redisTemplate.opsForList().rightPush("usermsg_0102", JSON.toJSONString(new FirstMsg(userid, "info", value)));
        String senduserid = Objects.equals(userid, "01") ? "02" : "01";
        webSocketService.sendOneMessage(Long.valueOf(senduserid), JSON.toJSONString(new FirstMsg(senduserid, "info", value)));
        log.info("USR {} ADD NEW MSG", userid);
        return "addReturn";
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/getAll")
    public List<FirstMsg> getAll(String id) {
        List<String> listWithString = redisTemplate.opsForList().range("usermsg_0102", 0, -1);
        List<FirstMsg> returnList = new ArrayList<>();
        assert listWithString != null;
        if (!listWithString.isEmpty()) {
            for (String s : listWithString) {
                Msg originMsg = JSON.parseObject(s, Msg.class);
                returnList.add(new FirstMsg(originMsg.getUserid(),
                        Objects.equals(originMsg.getUserid(), id) ? "danger" : "info",
                        originMsg.getMsg()));
            }
        }
        log.info("NEW USER GET ALL MSG");
        return returnList;
    }

}
