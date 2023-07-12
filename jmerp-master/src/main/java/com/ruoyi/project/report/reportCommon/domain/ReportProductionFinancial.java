package com.ruoyi.project.report.reportCommon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 报表公共对象 report_common
 * 
 * @author fangzhou
 * @date 2021-06-04
 */
public class ReportProductionFinancial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date produceDate;

    @Excel(name = "工单号")
    private String serialNumber;

    @Excel(name = "客户单号")
    private String customerOrder;
    private String customerNo;

    @Excel(name = "客户名称")
    private String customerName;

    // @Excel(name = "客户料号")
    private String customerMaterialNo;

    @Excel(name = "产品")
    private String productName;

    @Excel(name = "印刷别名")
    private String alias;

    @Excel(name = "材料")
    private String materialsName;

    /** 反面颜色 */
    @Excel(name = "开料尺寸(mm)")
    private String cuttingSize;
    private Integer cuttingSizeLong;
    private Integer cuttingSizeWidth;

    /** 印刷方式 */
    @Excel(name = "印刷方式", dictType = "print_type")
    private String printType;

    /** 正面颜色 */
    @Excel(name = "正面颜色")
    private String frontColor;

    /** 正面专色 */
    @Excel(name = "正面专色")
    private String frontSpot;

    /** 反面颜色 */
    @Excel(name = "反面颜色")
    private String backColor;

    /** 反面专色 */
    @Excel(name = "反面专色")
    private String backSpot;

    /** 反面专色 */
    @Excel(name = "颜色要求", dictType = "color_require")
    private String colorRequire;



    @Excel(name = "印刷数量", cellType = Excel.ColumnType.NUMERIC)
    private Integer qty;

    @Excel(name = "收费金额", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal chargeAmount;

    @Excel(name = "额外金额", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal extraAmount;

    @Excel(name = "原因")
    private String extraCostInfo;

    @Excel(name = "生产机台")
    private String equipmentName;

    @Excel(name = "生产班组")
    private String workTeam;

    @Excel(name = "备注")
    private String remark;

    private Long produceOrderId;

    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFrontSpot() {
        return frontSpot;
    }

    public void setFrontSpot(String frontSpot) {
        this.frontSpot = frontSpot;
    }

    public String getBackSpot() {
        return backSpot;
    }

    public void setBackSpot(String backSpot) {
        this.backSpot = backSpot;
    }

    public String getFrontColor() {
        return frontColor;
    }

    public void setFrontColor(String frontColor) {
        this.frontColor = frontColor;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerMaterialNo() {
        return customerMaterialNo;
    }

    public void setCustomerMaterialNo(String customerMaterialNo) {
        this.customerMaterialNo = customerMaterialNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCuttingSize() {
        return cuttingSize;
    }

    public void setCuttingSize(String cuttingSize) {
        this.cuttingSize = cuttingSize;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public Integer getCuttingSizeLong() {
        return cuttingSizeLong;
    }

    public void setCuttingSizeLong(Integer cuttingSizeLong) {
        this.cuttingSizeLong = cuttingSizeLong;
    }

    public Integer getCuttingSizeWidth() {
        return cuttingSizeWidth;
    }

    public void setCuttingSizeWidth(Integer cuttingSizeWidth) {
        this.cuttingSizeWidth = cuttingSizeWidth;
    }

    public String getWorkTeam() {
        return workTeam;
    }

    public void setWorkTeam(String workTeam) {
        this.workTeam = workTeam;
    }

    public String getColorRequire() {
        return colorRequire;
    }

    public void setColorRequire(String colorRequire) {
        this.colorRequire = colorRequire;
    }

    public BigDecimal getExtraAmount() {
        return extraAmount;
    }

    public void setExtraAmount(BigDecimal extraAmount) {
        this.extraAmount = extraAmount;
    }

    public Long getProduceOrderId() {
        return produceOrderId;
    }

    public void setProduceOrderId(Long produceOrderId) {
        this.produceOrderId = produceOrderId;
    }

    public String getExtraCostInfo() {
        return extraCostInfo;
    }

    public void setExtraCostInfo(String extraCostInfo) {
        this.extraCostInfo = extraCostInfo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(String customerOrder) {
        this.customerOrder = customerOrder;
    }
}
