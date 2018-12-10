package com.cskaoyan.erp.bean.vo;

import com.cskaoyan.erp.bean.UnqualifyApply;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2018/12/7
 */
@Component
public class UnqualifyListVO extends UnqualifyApply {

    private String productName;

    private String empName;

    public UnqualifyListVO() {
        super();
    }

    public UnqualifyListVO(String unqualifyApplyId, String productId, String unqualifyItem, Integer unqualifyCount, Date assemblyDate, String empId, Date applyDate, String note, String productName) {
        super(unqualifyApplyId, productId, unqualifyItem, unqualifyCount, assemblyDate, empId, applyDate, note);
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
