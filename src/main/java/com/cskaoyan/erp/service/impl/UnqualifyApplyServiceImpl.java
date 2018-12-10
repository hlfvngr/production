package com.cskaoyan.erp.service.impl;

import com.cskaoyan.erp.bean.UnqualifyApply;
import com.cskaoyan.erp.mapper.UnqualifyApplyMapper;
import com.cskaoyan.erp.service.UnqualifyApplyService;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.UnqualifyListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/6
 */
@Service
public class UnqualifyApplyServiceImpl implements UnqualifyApplyService {

    @Autowired
    UnqualifyApplyMapper unqualifyApplyMapper;

    public ArrayList<UnqualifyListVO> findByPageAndRows(PageModel pageModel) {
        return unqualifyApplyMapper.selectByPageAndRows(pageModel);
    }

    public Integer count() {
        return unqualifyApplyMapper.count();
    }

    public void insert(UnqualifyApply unqualifyApply) {
        unqualifyApplyMapper.insert(unqualifyApply);
    }

    public void update(UnqualifyApply unqualifyApply) {
        unqualifyApplyMapper.updateByPrimaryKey(unqualifyApply);
    }

    @Transactional
    public void deleteByIds(String[] ids) {

        for (String id : ids) {
            unqualifyApplyMapper.deleteByPrimaryKey(id);
        }

    }

    public Integer searchCountByUnqualifyId(String searchValue) {
        return unqualifyApplyMapper.searchCountByUnqualifyId(searchValue);
    }

    public List<UnqualifyListVO> searchByUnqualifyId(String searchValue, PageModel pageModel) {
        return unqualifyApplyMapper.searchByUnqualifyId(searchValue, pageModel);
    }

    public Integer searchCountByProductName(String searchValue) {
        return unqualifyApplyMapper.searchCountByProductName(searchValue);
    }

    public List<UnqualifyListVO> searchByProductName(String searchValue, PageModel pageModel) {
        return unqualifyApplyMapper.searchByProductName(searchValue, pageModel);
    }
}
