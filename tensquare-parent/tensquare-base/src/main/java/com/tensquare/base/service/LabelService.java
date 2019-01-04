package com.tensquare.base.service;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.common.LabelSpecification;
import com.tensquare.base.dao.LabelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tensquare.common.util.IdWorker;

import java.util.List;
import java.util.Optional;

/**
 * @author 杨郑兴
 * @Date 2018/12/26 0:27
 * @官网 www.weifuwukt.com
 */
@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String lableId){
        Optional<Label> labelOptional = labelDao.findById(lableId);
        return labelOptional.get();
    }

    public void save(Label label){
        //生成id
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String labelId){
        labelDao.deleteById(labelId);
    }

    public List<Label> findSearch(Label label) {
        /**
         * root 跟对象，也就是要把条件封装到那个对象中，where 类名=label.getid
         * criteriaQuery 封装查询的关键字，基本不用
         * criteriaBuilder 用来封装条件对象的
         * 如果直接返回null,则表示不需要任何条件
         */
       /* List<Label> list = labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //new一个list集合，来存放所以的条件
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
                return criteriaBuilder.and(parr); //where labelname like "%weifuwukt.com%" and state = 1
            }
        });*/
        return (List<Label>)labelDao.findAll(new LabelSpecification(label));
    }

    public Page<Label> pageQuery(Label label, Integer page, Integer size) {
        //这个分页插件默认从0开始，但是传过来的参数是从1开始的，所以要page-1
        Pageable pageable = PageRequest.of(page-1,size);
       /* return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //new一个list集合，来存放所以的条件
                List<Predicate> list = new ArrayList<>();
                if(!StringUtils.isEmpty(label.getLabelname())) {
                    Predicate  predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//where labelname like "%weifuwukt.com%"
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
                return criteriaBuilder.and(parr); //where labelname like "%weifuwukt.com%" and state = 1
            }
        },pageable);*/
        return (Page<Label>)labelDao.findAll(new LabelSpecification(label), pageable);
    }
}
