package com.cskaoyan.erp.service.impl;

import com.cskaoyan.erp.bean.FinalCountCheck;
import com.cskaoyan.erp.mapper.FinalCountCheckMapper;
import com.cskaoyan.erp.service.FinalCountCheckService;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.FinalCountListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/6
 */
@Service
public class FinalCountCheckServiceImpl implements FinalCountCheckService {

    @Autowired
    FinalCountCheckMapper finalCountCheckMapper;

    public ArrayList<FinalCountListVO> findByPageAndRows(PageModel pageModel) {
        return finalCountCheckMapper.selectByPageAndRows(pageModel);
    }

    public Integer count() {
        return finalCountCheckMapper.count();
    }

    public void insert(FinalCountCheck finalCountCheck) {
        finalCountCheckMapper.insert(finalCountCheck);
    }

    public void update(FinalCountCheck finalCountCheck) {
        finalCountCheckMapper.updateByPrimaryKey(finalCountCheck);
    }

    @Transactional
    public void deleteByIds(String[] ids) {

        for (String id : ids) {
            finalCountCheckMapper.deleteByPrimaryKey(id);
        }

    }

    public Integer searchCountByCountId(String searchValue) {
        return finalCountCheckMapper.searchCountByCountId(searchValue);
    }

    public List<FinalCountListVO> searchByCountId(String searchValue, PageModel pageModel) {
        return finalCountCheckMapper.searchByCountId(searchValue, pageModel);
    }

    public Integer searchCountByOrderId(String searchValue) {
        return finalCountCheckMapper.searchCountByOrderId(searchValue);
    }

    public List<FinalCountListVO> searchByOrderId(String searchValue, PageModel pageModel) {
        return finalCountCheckMapper.searchByOrderId(searchValue, pageModel);
    }
}
