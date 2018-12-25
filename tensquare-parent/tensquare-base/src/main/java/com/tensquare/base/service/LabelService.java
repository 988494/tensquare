package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

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
}
