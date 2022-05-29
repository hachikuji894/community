package com.hachikuji.server.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hachikuji.core.domain.model.DataTable;
import com.hachikuji.core.service.BaseService;
import com.hachikuji.frame.generate.entity.Message;
import com.hachikuji.frame.generate.entity.User;
import com.hachikuji.frame.generate.service.MessageService;
import com.hachikuji.frame.generate.service.UserService;
import com.hachikuji.frame.security.utils.SecurityUtils;
import com.hachikuji.server.constant.NormalConstants;
import com.hachikuji.server.domain.UserInfoBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysMessageService extends BaseService {


    @Autowired
    MessageService messageService;

    @Autowired
    SysUserService userService;

    /**
     * 分页查询当前用户的会话列表，针对每个会话放回最新一条私信
     * total 所有会话数量
     *
     * @return 未读数和 chat 数
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> pageChat() {

        startPage();

        Integer userId = SecurityUtils.getUserId();
//        int userId = 111;
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("conversation_id", userId.toString())
                .groupBy("conversation_id")
                .orderByDesc("create_time");

        List<Message> messages = messageService.list(queryWrapper);
        long total = getDataTablePage(messages).getTotal();

        List<Object> chats = new ArrayList<>();
        int totalUnreadCount = 0;

        for (Message m : messages) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("info", m);
            map.put("targetUser", userService.getUserInfo(m.getToId()));

            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("conversation_id", m.getConversationId())
                    .eq("status", NormalConstants.MESSAGE_UNREAD);


            int count = messageService.count(queryWrapper);
            totalUnreadCount += count;

            map.put("unreadCount", count);
            chats.add(map);
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("totalUnreadCount", totalUnreadCount);
        result.put("conversation", getDataTablePage(chats, total));
        return result;
    }

    /**
     * 分页查询某个会话的所有私信
     * total 所有私信数量
     *
     * @return list
     */
    public DataTable pageMessageByToId(Integer toId) {

        startPage();

        Integer userId = SecurityUtils.getUserId();
//        int userId = 111;
        String conversationId = switchConversationId(userId, toId);
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("conversation_id", conversationId)
                .orderByDesc("create_time");

        List<Message> messages = messageService.list(queryWrapper);
        long total = getDataTablePage(messages).getTotal();


        return getDataTablePage(messages, total);
    }

    public boolean addMassage(Integer toId, String content) {

        Integer userId = userService.getSystemUserId();

        Message message = new Message();
        message.setFromId(userId);
        message.setToId(toId);
        message.setStatus(NormalConstants.MESSAGE_UNREAD);
        message.setConversationId(switchConversationId(userId, toId));
        message.setContent(content);
        message.setCreateTime(LocalDateTime.now());

        return messageService.save(message);

    }

    public boolean updateMassageStateByToId(Integer toId) {

        Integer userId = userService.getSystemUserId();

        UpdateWrapper<Message> wrapper = new UpdateWrapper<>();
        wrapper.eq("conversation_id", switchConversationId(userId, toId))
                .set("state", NormalConstants.MESSAGE_READ);

        return messageService.update(wrapper);


    }

    public String switchConversationId(Integer id1, Integer id2) {
        if (id1 < id2)
            return id1 + "_" + id2;
        else
            return id2 + "_" + id1;
    }


}
