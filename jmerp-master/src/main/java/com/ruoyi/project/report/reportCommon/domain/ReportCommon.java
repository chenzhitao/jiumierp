package com.ruoyi.project.report.reportCommon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 报表公共对象 report_common
 * 
 * @author fangzhou
 * @date 2021-06-04
 */
public class ReportCommon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginDeliveryDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDeliveryDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginProduceDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endProduceDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    private Long supplierId;
    private Long equipmentId;
    private String supplierName;
    private Long materialsId;
    private String materialsName;
    private Integer qty;
    private Integer deliveryQty;
    private String qtyStatus;
    private String customerName;
    private String serialNumber;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("status", getStatus())
            .toString();
    }

    public Date getBeginDeliveryDate() {
        return beginDeliveryDate;
    }

    public void setBeginDeliveryDate(Date beginDeliveryDate) {
        this.beginDeliveryDate = beginDeliveryDate;
    }

    public Date getEndDeliveryDate() {
        return endDeliveryDate;
    }

    public void setEndDeliveryDate(Date endDeliveryDate) {
        this.endDeliveryDate = endDeliveryDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getMaterialsId() {
        return materialsId;
    }

    public void setMaterialsId(Long materialsId) {
        this.materialsId = materialsId;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(Integer deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public String getQtyStatus() {
        return qtyStatus;
    }

    public void setQtyStatus(String qtyStatus) {
        this.qtyStatus = qtyStatus;
    }

    public Date getBeginProduceDate() {
        return beginProduceDate;
    }

    public void setBeginProduceDate(Date beginProduceDate) {
        this.beginProduceDate = beginProduceDate;
    }

    public Date getEndProduceDate() {
        return endProduceDate;
    }

    public void setEndProduceDate(Date endProduceDate) {
        this.endProduceDate = endProduceDate;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
