package com.tensquare.search.qa.dao;

import com.tensquare.search.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    //最新回复
	@Query(value = "select * from tb_problem tb INNER JOIN tb_pl tl WHERE tb.id = tl.problemid AND labelid =? ORDER BY tb.replytime DESC",nativeQuery = true)
    public Page<Problem> newlist(String labelid, Pageable pageable);
	//最热回复
    @Query(value = "select * from tb_problem tb INNER JOIN tb_pl tl WHERE tb.id = tl.problemid AND labelid =? ORDER BY tb.reply DESC",nativeQuery = true)
    public Page<Problem> hotlist(String labelid, Pageable pageable);
    //等待回复
    @Query(value = "select * from tb_problem tb INNER JOIN tb_pl tl WHERE tb.id = tl.problemid AND labelid =? AND reply = 0 ORDER BY tb.createtime DESC",nativeQuery = true)
    public Page<Problem> waitlist(String labelid, Pageable pageable);
}
