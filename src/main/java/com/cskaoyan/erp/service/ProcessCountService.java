package com.cskaoyan.erp.service;

import com.cskaoyan.erp.bean.ProcessCountCheck;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.ProcessCountListVO;

import java.util.List;

/**
 * Created by Administrator on 2018/12/8
 */
public interface ProcessCountService {
    List<ProcessCountListVO> searchByPageAndRows(PageModel pageModel);

    Integer count();

    List<ProcessCountListVO> searchByPCountId(String searchValue, PageModel pageModel);

    Integer searchCountByPCountId(String searchValue);

    void insert(ProcessCountCheck processCountCheck);

    void update(ProcessCountCheck processCountCheck);

    void deleteByIds(String[] ids);

}
