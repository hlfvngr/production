package com.cskaoyan.erp.bean.vo;

import com.cskaoyan.erp.bean.ProcessMeasureCheck;

/**
 * Created by Administrator on 2018/12/8
 */
public class ProcessMeasureListVO extends ProcessMeasureCheck {

    String empName;

    public ProcessMeasureListVO() {
        super();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
