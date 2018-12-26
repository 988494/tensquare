package com.tensquare.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 杨郑兴
 * @Date 2018/12/26 0:22
 * @官网 www.weifuwukt.com
 */
public interface LabelDao extends JpaRepository<com.tensquare.base.pojo.Label,String>, JpaSpecificationExecutor<com.tensquare.base.pojo.Label> {

}
