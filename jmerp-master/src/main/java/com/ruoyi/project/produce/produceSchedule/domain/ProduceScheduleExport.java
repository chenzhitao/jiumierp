package com.ruoyi.project.produce.produceSchedule.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 生产排程对象 produce_schedule
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public class ProduceScheduleExport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private String printType;

    /** 单号 */

    private String serialNumber;
    /** 生产排程 */
    //@Excel(name = "排程单号")
    private String produceScheduleSN;
    private Long produceScheduleId;

    /** 生产状态 */
    //@Excel(name = "生产状态", dictType = "produce_status")
    private String produceStatus;

    /** 工作中心 */
    //@Excel(name = "工作中心", dictType = "work_center")
    private String workCenter;

    /** 设备 */
    //@Excel(name = "设备")
    private String equipmentName;
    private Long equipmentId;

    /** 班组 */
    //@Excel(name = "班组")
    private String teamName;
    private Long teamId;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date produceDate;

    /** 施工单 */
    @Excel(name = "工单号")
    private String produceOrderSN;
    private Long produceOrderId;

    @Excel(name = "客户名称")
    private String customerName;
    private Long customerId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    @Excel(name = "材料")
    private String materialsName;
    private Long materialsId;

    @Excel(name = "开纸尺寸（mm）")
    private String materialsSize;

    //@Excel(name = "材料数量")
    private Integer materialsQty;

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

    /** 整单外发 */
    @Excel(name = "颜色要求", dictType = "color_require")
    private String colorRequire;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private Integer produceQty;

    @Excel(name = "备注")
    private String remark;

    /** 状态 */
    //@Excel(name = "状态", dictType = "workflow_status")
    private String status;

    /** 审批人 */
    //@Excel(name = "审批人")
    private String approver;

    /** 审批时间 */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;

    /** 审批类型 */
    //@Excel(name = "审批类型")
    private String approvalType;



    //@Excel(name = "产品规格")
    private String productSize;

    /** ID */
    //@Excel(name = "产品规格(长)")
    private Integer sizeLong;

    //@Excel(name = "产品规格(宽)")
    private Integer sizeWidth;

    //@Excel(name = "产品规格(高)")
    private Integer sizeHeight;



    /** 施工工序 */
    //@Excel(name = "施工工序")
    private Long produceOrderProcessId;

    /** 施工产品 */
    //@Excel(name = "施工产品")
    private Long produceOrderProductId;



    /** 销售产品 */
    //@Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 销售订单 */
    //@Excel(name = "销售订单")
    private Long saleOrderId;

    /** 生产工艺卡 */
    //@Excel(name = "生产工艺卡")
    private Long productionTemplateId;

    /** 生产工艺卡工序 */
    //@Excel(name = "生产工艺卡工序")
    private Long productionTemplateProcessId;

    //@Excel(name = "销售订单")
    private String saleOrderSN;

    /** 工序 */
    //@Excel(name = "工序")
    private String processName;
    private Long processId;

    /** 报产数量 */
    //@Excel(name = "报产数量")
    private Integer qty;

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
    public void setApprover(String approver)
    {
        this.approver = approver;
    }

    public String getApprover()
    {
        return approver;
    }
    public void setApprovalTime(Date approvalTime)
    {
        this.approvalTime = approvalTime;
    }

    public Date getApprovalTime()
    {
        return approvalTime;
    }
    public void setApprovalType(String approvalType)
    {
        this.approvalType = approvalType;
    }

    public String getApprovalType()
    {
        return approvalType;
    }
    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }
    public void setProduceStatus(String produceStatus)
    {
        this.produceStatus = produceStatus;
    }

    public String getProduceStatus()
    {
        return produceStatus;
    }
    public void setWorkCenter(String workCenter)
    {
        this.workCenter = workCenter;
    }

    public String getWorkCenter()
    {
        return workCenter;
    }
    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }
    public void setTeamId(Long teamId)
    {
        this.teamId = teamId;
    }

    public Long getTeamId()
    {
        return teamId;
    }
    public void setProduceDate(Date produceDate)
    {
        this.produceDate = produceDate;
    }

    public Date getProduceDate()
    {
        return produceDate;
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
            .append("approver", getApprover())
            .append("approvalTime", getApprovalTime())
            .append("approvalType", getApprovalType())
            .append("serialNumber", getSerialNumber())
            .append("produceStatus", getProduceStatus())
            .append("workCenter", getWorkCenter())
            .append("equipmentId", getEquipmentId())
            .append("teamId", getTeamId())
            .append("produceDate", getProduceDate())
            .toString();
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Integer getSizeLong() {
        return sizeLong;
    }

    public void setSizeLong(Integer sizeLong) {
        this.sizeLong = sizeLong;
    }

    public Integer getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(Integer sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public Integer getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(Integer sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public String getProduceScheduleSN() {
        return produceScheduleSN;
    }

    public void setProduceScheduleSN(String produceScheduleSN) {
        this.produceScheduleSN = produceScheduleSN;
    }

    public Long getProduceScheduleId() {
        return produceScheduleId;
    }

    public void setProduceScheduleId(Long produceScheduleId) {
        this.produceScheduleId = produceScheduleId;
    }

    public Long getProduceOrderProcessId() {
        return produceOrderProcessId;
    }

    public void setProduceOrderProcessId(Long produceOrderProcessId) {
        this.produceOrderProcessId = produceOrderProcessId;
    }

    public Long getProduceOrderProductId() {
        return produceOrderProductId;
    }

    public void setProduceOrderProductId(Long produceOrderProductId) {
        this.produceOrderProductId = produceOrderProductId;
    }

    public String getProduceOrderSN() {
        return produceOrderSN;
    }

    public void setProduceOrderSN(String produceOrderSN) {
        this.produceOrderSN = produceOrderSN;
    }

    public Long getProduceOrderId() {
        return produceOrderId;
    }

    public void setProduceOrderId(Long produceOrderId) {
        this.produceOrderId = produceOrderId;
    }

    public Long getSaleOrderProductId() {
        return saleOrderProductId;
    }

    public void setSaleOrderProductId(Long saleOrderProductId) {
        this.saleOrderProductId = saleOrderProductId;
    }

    public Long getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(Long saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public Long getProductionTemplateId() {
        return productionTemplateId;
    }

    public void setProductionTemplateId(Long productionTemplateId) {
        this.productionTemplateId = productionTemplateId;
    }

    public Long getProductionTemplateProcessId() {
        return productionTemplateProcessId;
    }

    public void setProductionTemplateProcessId(Long productionTemplateProcessId) {
        this.productionTemplateProcessId = productionTemplateProcessId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Integer getProduceQty() {
        return produceQty;
    }

    public void setProduceQty(Integer produceQty) {
        this.produceQty = produceQty;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getSaleOrderSN() {
        return saleOrderSN;
    }

    public void setSaleOrderSN(String saleOrderSN) {
        this.saleOrderSN = saleOrderSN;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public Long getMaterialsId() {
        return materialsId;
    }

    public void setMaterialsId(Long materialsId) {
        this.materialsId = materialsId;
    }

    public String getMaterialsSize() {
        return materialsSize;
    }

    public void setMaterialsSize(String materialsSize) {
        this.materialsSize = materialsSize;
    }

    public String getColorRequire() {
        return colorRequire;
    }

    public void setColorRequire(String colorRequire) {
        this.colorRequire = colorRequire;
    }

    public Integer getMaterialsQty() {
        return materialsQty;
    }

    public void setMaterialsQty(Integer materialsQty) {
        this.materialsQty = materialsQty;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFrontColor() {
        return frontColor;
    }

    public void setFrontColor(String frontColor) {
        this.frontColor = frontColor;
    }

    public String getFrontSpot() {
        return frontSpot;
    }

    public void setFrontSpot(String frontSpot) {
        this.frontSpot = frontSpot;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public String getBackSpot() {
        return backSpot;
    }

    public void setBackSpot(String backSpot) {
        this.backSpot = backSpot;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }
}
