package com.cskaoyan.erp.service;

import com.cskaoyan.erp.bean.UnqualifyApply;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.UnqualifyListVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/6
 */
public interface UnqualifyApplyService {
    ArrayList<UnqualifyListVO> findByPageAndRows(PageModel pageModel);

    Integer count();

    void insert(UnqualifyApply unqualifyApply);

    void update(UnqualifyApply unqualifyApply);

    void deleteByIds(String[] ids);

    Integer searchCountByUnqualifyId(String searchValue);

    List<UnqualifyListVO> searchByUnqualifyId(String searchValue, PageModel pageModel);

    Integer searchCountByProductName(String searchValue);

    List<UnqualifyListVO> searchByProductName(String searchValue, PageModel pageModel);
}
