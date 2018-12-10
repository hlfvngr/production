package com.cskaoyan.erp.service.impl;

import com.cskaoyan.erp.bean.FinalMeasureCheck;
import com.cskaoyan.erp.mapper.FinalMeasureCheckMapper;
import com.cskaoyan.erp.service.FinalMeasureCheckService;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.FinalMeasureListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/6
 */
@Service
public class FinalMeasureCheckServiceImpl implements FinalMeasureCheckService {

    @Autowired
    FinalMeasureCheckMapper finalMeasureCheckMapper;

    public ArrayList<FinalMeasureListVO> findByPageAndRows(PageModel pageModel) {
        return finalMeasureCheckMapper.selectByPageAndRows(pageModel);
    }

    public Integer count() {
        return finalMeasureCheckMapper.count();
    }

    public void insert(FinalMeasureCheck unqualifyApply) {
        finalMeasureCheckMapper.insert(unqualifyApply);
    }

    public void update(FinalMeasureCheck unqualifyApply) {
        finalMeasureCheckMapper.updateByPrimaryKey(unqualifyApply);
    }

    @Transactional
    public void deleteByIds(String[] ids) {

        for (String id : ids) {
            finalMeasureCheckMapper.deleteByPrimaryKey(id);
        }

    }

    public Integer searchCountByMeasureId(String searchValue) {
        return finalMeasureCheckMapper.searchCountByMeasureId(searchValue);
    }

    public List<FinalMeasureListVO> searchByMeasureId(String searchValue, PageModel pageModel) {
        return finalMeasureCheckMapper.searchByMeasureId(searchValue, pageModel);
    }

    public Integer searchCountByOrderId(String searchValue) {
        return finalMeasureCheckMapper.searchCountByOrderId(searchValue);
    }

    public List<FinalMeasureListVO> searchByOrderId(String searchValue, PageModel pageModel) {
        return finalMeasureCheckMapper.searchByOrderId(searchValue, pageModel);
    }
}
