package com.ruoyi.project.produce.produceOrderProcess.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 施工工序对象 produce_order_process
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public class ProduceOrderProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private Long supplierId;
    private Long supplierTempId;
    private String produceOrderSN;
    private String customerName;
    private String productName;
    private Integer sizeLong;
    private Integer sizeWidth;
    private Integer sizeHeight;
    private Long customerId;
    private Integer leftQty;
    private String isPrint;
    private String equipmentSize;
    private String processType;
    private Integer cuttingSizeLong;
    private Integer cuttingSizeWidth;
    private String serialNumber;
    private String alias;
    private BigDecimal chargeAmount;
    private Long produceOrderPartId;
    private String printType;
    private String produceOrderPartName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date produceDate;

    /** 施工单 */
    @Excel(name = "施工单")
    private Long produceOrderId;

    /** 施工产品 */
    @Excel(name = "施工产品")
    private Long produceOrderProductId;

    /** 销售产品 */
    @Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 销售订单 */
    @Excel(name = "销售订单")
    private Long saleOrderId;

    /** 产品 */
    @Excel(name = "产品")
    private Long productId;

    /** 工序 */
    @Excel(name = "工序")
    private String processName;
    private Long processId;

    /** 生产工艺卡 */
    @Excel(name = "生产工艺卡")
    private Long productionTemplateId;

    /** 生产工艺卡工序 */
    @Excel(name = "生产工艺卡工序")
    private Long productionTemplateProcessId;

    /** 生产状态 */
    @Excel(name = "生产状态")
    private String produceStatus;

    /** 顺序 */
    @Excel(name = "顺序")
    private Integer processOrder;

    /** 计价方式 */
    @Excel(name = "计价方式")
    private String valuationType;

    /** 是否计算 */
    @Excel(name = "是否计算")
    private String isIncount;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private Integer qty;

    /** 外发 */
    @Excel(name = "外发")
    private String isOutsource;

    /** 外发单价 */
    @Excel(name = "外发单价")
    private BigDecimal outsourcePrice;

    /** 外发带料 */
    @Excel(name = "外发带料")
    private String isWithMaterials;

    /** 倍率 */
    @Excel(name = "倍率")
    private BigDecimal times;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 设备 */
    @Excel(name = "设备")
    private String equipmentName;
    private Long equipmentId;

    /** 损耗率(%) */
    @Excel(name = "损耗率(%)")
    private BigDecimal lossRate;

    /** 固定损耗数 */
    @Excel(name = "固定损耗数")
    private Integer lossQty;

    /** 工序要求 */
    @Excel(name = "工序要求")
    private String requirements;

    /** 投入数 */
    @Excel(name = "投入数")
    private Integer inQty;

    /** 产出数 */
    @Excel(name = "产出数")
    private Integer outQty;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProduceOrderId(Long produceOrderId)
    {
        this.produceOrderId = produceOrderId;
    }

    public Long getProduceOrderId()
    {
        return produceOrderId;
    }
    public void setProduceOrderProductId(Long produceOrderProductId)
    {
        this.produceOrderProductId = produceOrderProductId;
    }

    public Long getProduceOrderProductId()
    {
        return produceOrderProductId;
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
    public void setProduceStatus(String produceStatus)
    {
        this.produceStatus = produceStatus;
    }

    public String getProduceStatus()
    {
        return produceStatus;
    }
    public void setProcessOrder(Integer processOrder)
    {
        this.processOrder = processOrder;
    }

    public Integer getProcessOrder()
    {
        return processOrder;
    }
    public void setValuationType(String valuationType)
    {
        this.valuationType = valuationType;
    }

    public String getValuationType()
    {
        return valuationType;
    }
    public void setIsIncount(String isIncount)
    {
        this.isIncount = isIncount;
    }

    public String getIsIncount()
    {
        return isIncount;
    }
    public void setQty(Integer qty)
    {
        this.qty = qty;
    }

    public Integer getQty()
    {
        return qty;
    }
    public void setIsOutsource(String isOutsource)
    {
        this.isOutsource = isOutsource;
    }

    public String getIsOutsource()
    {
        return isOutsource;
    }
    public void setOutsourcePrice(BigDecimal outsourcePrice)
    {
        this.outsourcePrice = outsourcePrice;
    }

    public BigDecimal getOutsourcePrice()
    {
        return outsourcePrice;
    }
    public void setIsWithMaterials(String isWithMaterials)
    {
        this.isWithMaterials = isWithMaterials;
    }

    public String getIsWithMaterials()
    {
        return isWithMaterials;
    }
    public void setTimes(BigDecimal times)
    {
        this.times = times;
    }

    public BigDecimal getTimes()
    {
        return times;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }
    public void setLossRate(BigDecimal lossRate)
    {
        this.lossRate = lossRate;
    }

    public BigDecimal getLossRate()
    {
        return lossRate;
    }
    public void setLossQty(Integer lossQty)
    {
        this.lossQty = lossQty;
    }

    public Integer getLossQty()
    {
        return lossQty;
    }
    public void setRequirements(String requirements)
    {
        this.requirements = requirements;
    }

    public String getRequirements()
    {
        return requirements;
    }
    public void setInQty(Integer inQty)
    {
        this.inQty = inQty;
    }

    public Integer getInQty()
    {
        return inQty;
    }
    public void setOutQty(Integer outQty)
    {
        this.outQty = outQty;
    }

    public Integer getOutQty()
    {
        return outQty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("produceOrderId", getProduceOrderId())
            .append("produceOrderProductId", getProduceOrderProductId())
            .append("saleOrderProductId", getSaleOrderProductId())
            .append("saleOrderId", getSaleOrderId())
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("productionTemplateId", getProductionTemplateId())
            .append("productionTemplateProcessId", getProductionTemplateProcessId())
            .append("produceStatus", getProduceStatus())
            .append("processOrder", getProcessOrder())
            .append("valuationType", getValuationType())
            .append("isIncount", getIsIncount())
            .append("qty", getQty())
            .append("isOutsource", getIsOutsource())
            .append("outsourcePrice", getOutsourcePrice())
            .append("isWithMaterials", getIsWithMaterials())
            .append("times", getTimes())
            .append("price", getPrice())
            .append("equipmentId", getEquipmentId())
            .append("lossRate", getLossRate())
            .append("lossQty", getLossQty())
            .append("requirements", getRequirements())
            .append("inQty", getInQty())
            .append("outQty", getOutQty())
            .toString();
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getProduceOrderSN() {
        return produceOrderSN;
    }

    public void setProduceOrderSN(String produceOrderSN) {
        this.produceOrderSN = produceOrderSN;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getProduceDate() {
        return produceDate;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getSupplierTempId() {
        return supplierTempId;
    }

    public void setSupplierTempId(Long supplierTempId) {
        this.supplierTempId = supplierTempId;
    }

    public Integer getLeftQty() {
        return leftQty;
    }

    public void setLeftQty(Integer leftQty) {
        this.leftQty = leftQty;
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public String getEquipmentSize() {
        return equipmentSize;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public void setEquipmentSize(String equipmentSize) {
        this.equipmentSize = equipmentSize;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Long getProduceOrderPartId() {
        return produceOrderPartId;
    }

    public void setProduceOrderPartId(Long produceOrderPartId) {
        this.produceOrderPartId = produceOrderPartId;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getProduceOrderPartName() {
        return produceOrderPartName;
    }

    public void setProduceOrderPartName(String produceOrderPartName) {
        this.produceOrderPartName = produceOrderPartName;
    }
}
