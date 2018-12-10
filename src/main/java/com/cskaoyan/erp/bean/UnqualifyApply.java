package com.cskaoyan.erp.bean;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UnqualifyApply {

    @NotEmpty(message = "表单还未填写完成")
    private String unqualifyApplyId;

    @NotNull(message = "表单还未填写完成")
    private String productId;

    private String unqualifyItem;

    private Integer unqualifyCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assemblyDate;

    @NotNull(message = "表单还未填写完成")
    private String empId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;

    private String note;

    public String getUnqualifyApplyId() {
        return unqualifyApplyId;
    }

    public UnqualifyApply() {
    }

    public UnqualifyApply(String unqualifyApplyId, String productId, String unqualifyItem, Integer unqualifyCount, Date assemblyDate, String empId, Date applyDate, String note) {
        this.unqualifyApplyId = unqualifyApplyId;
        this.productId = productId;
        this.unqualifyItem = unqualifyItem;
        this.unqualifyCount = unqualifyCount;
        this.assemblyDate = assemblyDate;
        this.empId = empId;
        this.applyDate = applyDate;
        this.note = note;
    }

    @Override
    public String toString() {
        return "UnqualifyApply{" +
                "unqualifyApplyId='" + unqualifyApplyId + '\'' +
                ", productId='" + productId + '\'' +
                ", unqualifyItem='" + unqualifyItem + '\'' +
                ", unqualifyCount=" + unqualifyCount +
                ", assemblyDate=" + assemblyDate +
                ", empId='" + empId + '\'' +
                ", applyDate=" + applyDate +
                ", note='" + note + '\'' +
                '}';
    }

    public void setUnqualifyApplyId(String unqualifyApplyId) {
        this.unqualifyApplyId = unqualifyApplyId == null ? null : unqualifyApplyId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUnqualifyItem() {
        return unqualifyItem;
    }

    public void setUnqualifyItem(String unqualifyItem) {
        this.unqualifyItem = unqualifyItem == null ? null : unqualifyItem.trim();
    }

    public Integer getUnqualifyCount() {
        return unqualifyCount;
    }

    public void setUnqualifyCount(Integer unqualifyCount) {
        this.unqualifyCount = unqualifyCount;
    }

    public Date getAssemblyDate() {
        return assemblyDate;
    }

    public void setAssemblyDate(Date assemblyDate) {
        this.assemblyDate = assemblyDate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}