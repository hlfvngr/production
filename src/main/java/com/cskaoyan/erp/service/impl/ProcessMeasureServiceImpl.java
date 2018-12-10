package com.cskaoyan.erp.service.impl;

import com.cskaoyan.erp.bean.ProcessMeasureCheck;
import com.cskaoyan.erp.mapper.ProcessMeasureCheckMapper;
import com.cskaoyan.erp.service.ProcessMeasureService;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.ProcessMeasureListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/12/8
 */
@Service
public class ProcessMeasureServiceImpl implements ProcessMeasureService {

    @Autowired
    ProcessMeasureCheckMapper processMeasureCheckMapper;


    public List<ProcessMeasureListVO> searchByPageAndRows(PageModel pageModel) {
        return processMeasureCheckMapper.selectByPageAndRows(pageModel);
    }

    public Integer count() {
        return processMeasureCheckMapper.count();
    }

    public List<ProcessMeasureListVO> searchByPMeasureId(String searchValue, PageModel pageModel) {
        return processMeasureCheckMapper.selectByPMeasureId(searchValue, pageModel);
    }

    public Integer searchCountByPMeasureId(String searchValue) {
        return processMeasureCheckMapper.selectCountByPMeasureId(searchValue);
    }

    public void insert(ProcessMeasureCheck processMeasureCheck) {
        processMeasureCheckMapper.insert(processMeasureCheck);
    }

    public void update(ProcessMeasureCheck processMeasureCheck) {
        processMeasureCheckMapper.updateByPrimaryKey(processMeasureCheck);
    }

    @Transactional
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            processMeasureCheckMapper.deleteByPrimaryKey(id);
        }
    }
}
