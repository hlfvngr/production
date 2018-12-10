package com.cskaoyan.erp.bean.vo;

import com.cskaoyan.erp.bean.ProcessCountCheck;

/**
 * Created by Administrator on 2018/12/8
 */
public class ProcessCountListVO extends ProcessCountCheck {
    String empName;

    public ProcessCountListVO() {
        super();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

}
