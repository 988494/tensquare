package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 杨郑兴
 * @Date 2019/1/2 16:53
 * @官网 www.weifuwukt.com
 */
@Entity
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class)//表示联合主键
@Data
public class NoFriend implements Serializable {
    @Id
    private String userid;
    @Id
    private String friendid;
}
