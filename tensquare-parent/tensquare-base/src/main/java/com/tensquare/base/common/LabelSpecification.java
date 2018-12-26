package com.tensquare.base.common;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨郑兴
 * @Date 2018/12/26 16:09
 * @官网 www.weifuwukt.com
 */
public class LabelSpecification implements Specification {
    private Label label;

    public LabelSpecification(Label label) {
        this.label = label;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> list = new ArrayList<>();
        if(!StringUtils.isEmpty(label.getLabelname())) {
                Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//where labelname like "%weifuwukt.com%"
                list.add(predicate);
            }
            if(!StringUtils.isEmpty(label.getState())) {
                Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class), "%" + label.getState() + "%");//state = 1
                list.add(predicate);
            }
            //new一个数组作为最终返回值的条件
            Predicate[] parr = new Predicate[list.size()];
            //把list直接转成数组
            parr= list.toArray(parr);
            return criteriaBuilder.and(parr);
    }
}
