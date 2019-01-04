package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 杨郑兴
 * @Date 2019/1/2 17:23
 * @官网 www.weifuwukt.com
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String> {
    public NoFriend findByUseridAndFriendid(String userid, String friendid);
}
