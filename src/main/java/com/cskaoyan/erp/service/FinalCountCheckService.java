package com.cskaoyan.erp.service;

import com.cskaoyan.erp.bean.FinalCountCheck;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.FinalCountListVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/6
 */
public interface FinalCountCheckService {
    ArrayList<FinalCountListVO> findByPageAndRows(PageModel pageModel);

    Integer count();

    void insert(FinalCountCheck finalCountCheck);

    void update(FinalCountCheck finalCountCheck);

    void deleteByIds(String[] ids);

    Integer searchCountByCountId(String searchValue);

    List<FinalCountListVO> searchByCountId(String searchValue, PageModel pageModel);

    Integer searchCountByOrderId(String searchValue);

    List<FinalCountListVO> searchByOrderId(String searchValue, PageModel pageModel);
}
