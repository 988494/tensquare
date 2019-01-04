package com.tensquare.friend.service;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.dao.FriendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author 杨郑兴
 * @Date 2019/1/2 17:10
 * @官网 www.weifuwukt.com
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriendDao;

    public int addFirend(String userid, String friendid) {
        //先判断userid到friend是否有数据，有就是重复添加好友
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if(friend!=null){
            return 0;
        }
        //直接添加好友，让好友表中userid到friend方向的type为0
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断从friend到userid是否有数据，如果有，把双方的状态都改为1
        if(friendDao.findByUseridAndFriendid(friendid,userid)!=null){
            //存在表示都喜欢，把双方的isLike都改为1
            friendDao.updateIsLike("1",userid,friendid);
            friendDao.updateIsLike("1",friendid,userid);
        }
        return 1;
    }

    public int noFriend(String userid, String friendid) {
        //先判断是否已经是非好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid, friendid);
        if(StringUtils.isEmpty(noFriend)){
            //是好友，则不能重复添加
            return 0;
        }
        //不是好友，则添加
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userid, String friendid) {
        //删除好友表中userid到friendid这条数据
        friendDao.deletefriend(userid,friendid);
        //更新friendid到userid的islike为0
        friendDao.updateIsLike("0",friendid,userid);
        //非好友表中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setFriendid(friendid);
        noFriend.setUserid(userid);
        noFriendDao.save(noFriend);
    }
}
