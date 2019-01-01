package com.tensquare.search.spit.dao;

import com.tensquare.search.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 杨郑兴
 * @Date 2018/12/27 21:14
 * @官网 www.weifuwukt.com
 */
public interface SpitDao extends MongoRepository<Spit, String> {

    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
