package com.cskaoyan.erp.bean.vo;

import com.cskaoyan.erp.bean.FinalMeasureCheck;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/12/7
 */
@Component
public class FinalMeasureListVO extends FinalMeasureCheck {

    private String empName;

    public FinalMeasureListVO() {
        super();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
