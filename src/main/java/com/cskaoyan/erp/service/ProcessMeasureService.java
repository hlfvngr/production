package com.cskaoyan.erp.service;

import com.cskaoyan.erp.bean.ProcessMeasureCheck;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.ProcessMeasureListVO;

import java.util.List;

/**
 * Created by Administrator on 2018/12/8
 */
public interface ProcessMeasureService {

    List<ProcessMeasureListVO> searchByPageAndRows(PageModel pageModel);

    Integer count();

    List<ProcessMeasureListVO> searchByPMeasureId(String searchValue, PageModel pageModel);

    Integer searchCountByPMeasureId(String searchValue);

    void insert(ProcessMeasureCheck processMeasureCheck);

    void update(ProcessMeasureCheck processMeasureCheck);

    void deleteByIds(String[] ids);

}
