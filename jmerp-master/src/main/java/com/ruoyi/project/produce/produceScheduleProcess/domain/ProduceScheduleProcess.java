package com.ruoyi.project.produce.produceScheduleProcess.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 生产排程工序对象 produce_schedule_process
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public class ProduceScheduleProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    @Excel(name = "产品规格(长)")
    private Integer sizeLong;

    @Excel(name = "产品规格(宽)")
    private Integer sizeWidth;

    @Excel(name = "产品规格(高)")
    private Integer sizeHeight;

    /** 生产排程 */
    @Excel(name = "生产排程")
    private String produceScheduleSN;
    private Long produceScheduleId;

    /** 施工工序 */
    @Excel(name = "施工工序")
    private Long produceOrderProcessId;

    /** 施工产品 */
    @Excel(name = "施工产品")
    private Long produceOrderProductId;

    /** 施工单 */
    @Excel(name = "施工单")
    private String produceOrderSN;
    private Long produceOrderId;

    /** 销售产品 */
    @Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 销售订单 */
    @Excel(name = "销售订单")
    private Long saleOrderId;

    /** 生产工艺卡 */
    @Excel(name = "生产工艺卡")
    private Long productionTemplateId;

    /** 生产工艺卡工序 */
    @Excel(name = "生产工艺卡工序")
    private Long productionTemplateProcessId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    /** 工序 */
    @Excel(name = "工序")
    private String processName;
    private Long processId;

    /** 生产状态 */
    @Excel(name = "生产状态")
    private String produceStatus;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private Integer produceQty;

    /** 报产数量 */
    @Excel(name = "报产数量")
    private Integer qty;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date produceDate;
    private String workCenter;
    private String teamName;

    private String saleOrderSN;
    private Long customerId;
    private String customerName;
    private String frontSpot;
    private String backSpot;
    private String frontColor;
    private String backColor;
    private String colorRequire;
    private String materialsInfo;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProduceScheduleId(Long produceScheduleId)
    {
        this.produceScheduleId = produceScheduleId;
    }

    public Long getProduceScheduleId()
    {
        return produceScheduleId;
    }
    public void setProduceOrderProcessId(Long produceOrderProcessId)
    {
        this.produceOrderProcessId = produceOrderProcessId;
    }

    public Long getProduceOrderProcessId()
    {
        return produceOrderProcessId;
    }
    public void setProduceOrderProductId(Long produceOrderProductId)
    {
        this.produceOrderProductId = produceOrderProductId;
    }

    public Long getProduceOrderProductId()
    {
        return produceOrderProductId;
    }
    public void setProduceOrderId(Long produceOrderId)
    {
        this.produceOrderId = produceOrderId;
    }

    public Long getProduceOrderId()
    {
        return produceOrderId;
    }
    public void setSaleOrderProductId(Long saleOrderProductId)
    {
        this.saleOrderProductId = saleOrderProductId;
    }

    public Long getSaleOrderProductId()
    {
        return saleOrderProductId;
    }
    public void setSaleOrderId(Long saleOrderId)
    {
        this.saleOrderId = saleOrderId;
    }

    public Long getSaleOrderId()
    {
        return saleOrderId;
    }
    public void setProductionTemplateId(Long productionTemplateId)
    {
        this.productionTemplateId = productionTemplateId;
    }

    public Long getProductionTemplateId()
    {
        return productionTemplateId;
    }
    public void setProductionTemplateProcessId(Long productionTemplateProcessId)
    {
        this.productionTemplateProcessId = productionTemplateProcessId;
    }

    public Long getProductionTemplateProcessId()
    {
        return productionTemplateProcessId;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setProcessId(Long processId)
    {
        this.processId = processId;
    }

    public Long getProcessId()
    {
        return processId;
    }
    public void setProduceStatus(String produceStatus)
    {
        this.produceStatus = produceStatus;
    }

    public String getProduceStatus()
    {
        return produceStatus;
    }
    public void setProduceQty(Integer produceQty)
    {
        this.produceQty = produceQty;
    }

    public Integer getProduceQty()
    {
        return produceQty;
    }
    public void setQty(Integer qty)
    {
        this.qty = qty;
    }

    public Integer getQty()
    {
        return qty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("produceScheduleId", getProduceScheduleId())
            .append("produceOrderProcessId", getProduceOrderProcessId())
            .append("produceOrderProductId", getProduceOrderProductId())
            .append("produceOrderId", getProduceOrderId())
            .append("saleOrderProductId", getSaleOrderProductId())
            .append("saleOrderId", getSaleOrderId())
            .append("productionTemplateId", getProductionTemplateId())
            .append("productionTemplateProcessId", getProductionTemplateProcessId())
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("produceStatus", getProduceStatus())
            .append("produceQty", getProduceQty())
            .append("qty", getQty())
            .toString();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProduceOrderSN() {
        return produceOrderSN;
    }

    public void setProduceOrderSN(String produceOrderSN) {
        this.produceOrderSN = produceOrderSN;
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

    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public void setProduceScheduleSN(String produceScheduleSN) {
        this.produceScheduleSN = produceScheduleSN;
    }

    public String getWorkCenter() {
        return workCenter;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setWorkCenter(String workCenter) {
        this.workCenter = workCenter;
    }

    public String getSaleOrderSN() {
        return saleOrderSN;
    }

    public void setSaleOrderSN(String saleOrderSN) {
        this.saleOrderSN = saleOrderSN;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getColorRequire() {
        return colorRequire;
    }

    public void setColorRequire(String colorRequire) {
        this.colorRequire = colorRequire;
    }

    public String getMaterialsInfo() {
        return materialsInfo;
    }

    public void setMaterialsInfo(String materialsInfo) {
        this.materialsInfo = materialsInfo;
    }
}
