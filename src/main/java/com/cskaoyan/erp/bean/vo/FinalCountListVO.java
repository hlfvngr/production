package com.cskaoyan.erp.bean.vo;

import com.cskaoyan.erp.bean.FinalCountCheck;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/12/7
 */
@Component
public class FinalCountListVO extends FinalCountCheck {

    private String empName;

    public FinalCountListVO() {
        super();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
