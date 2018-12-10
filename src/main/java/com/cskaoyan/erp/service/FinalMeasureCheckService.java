package com.cskaoyan.erp.service;

import com.cskaoyan.erp.bean.FinalMeasureCheck;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.FinalMeasureListVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/6
 */
public interface FinalMeasureCheckService {
    ArrayList<FinalMeasureListVO> findByPageAndRows(PageModel pageModel);

    Integer count();

    void insert(FinalMeasureCheck finalMeasureCheck);

    void update(FinalMeasureCheck finalMeasureCheck);

    void deleteByIds(String[] ids);

    Integer searchCountByMeasureId(String searchValue);

    List<FinalMeasureListVO> searchByMeasureId(String searchValue, PageModel pageModel);

    Integer searchCountByOrderId(String searchValue);

    List<FinalMeasureListVO> searchByOrderId(String searchValue, PageModel pageModel);
}
