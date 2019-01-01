package com.tensquare.search.base.dao;

import com.tensquare.search.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 杨郑兴
 * @Date 2018/12/26 0:22
 * @官网 www.weifuwukt.com
 */
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {

}
