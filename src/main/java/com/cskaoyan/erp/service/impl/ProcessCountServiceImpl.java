package com.cskaoyan.erp.service.impl;

import com.cskaoyan.erp.bean.ProcessCountCheck;
import com.cskaoyan.erp.mapper.ProcessCountCheckMapper;
import com.cskaoyan.erp.service.ProcessCountService;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.ProcessCountListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/12/8
 */
@Service
public class ProcessCountServiceImpl implements ProcessCountService {

    @Autowired
    ProcessCountCheckMapper processCountCheckMapper;


    public List<ProcessCountListVO> searchByPageAndRows(PageModel pageModel) {
        return processCountCheckMapper.selectByPageAndRows(pageModel);
    }

    public Integer count() {
        return processCountCheckMapper.count();
    }

    public List<ProcessCountListVO> searchByPCountId(String searchValue, PageModel pageModel) {
        return processCountCheckMapper.selectByPMeasureId(searchValue, pageModel);
    }

    public Integer searchCountByPCountId(String searchValue) {
        return processCountCheckMapper.selectCountByPCountId(searchValue);
    }

    public void insert(ProcessCountCheck processCountCheck) {
        processCountCheckMapper.insert(processCountCheck);
    }

    public void update(ProcessCountCheck processCountCheck) {
        processCountCheckMapper.updateByPrimaryKey(processCountCheck);
    }

    @Transactional
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            processCountCheckMapper.deleteByPrimaryKey(id);
        }
    }
}
