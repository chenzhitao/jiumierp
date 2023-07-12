package com.ruoyi.project.outsource.outsourceCheckingProcess.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 外发对账工序对象 outsource_checking_process
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public class OutsourceCheckingProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 外发对账单 */
    @Excel(name = "外发对账单")
    private Long outsourceCheckingId;

    /** 外发到货 */
    @Excel(name = "外发到货")
    private Long outsourceDeliveryId;

    /** 外发到货工序 */
    @Excel(name = "外发到货工序")
    private Long outsourceDeliveryProcessId;

    /** 外发加工工序 */
    @Excel(name = "外发加工工序")
    private Long outsourceOrderProcessId;

    /** 外发加工 */
    @Excel(name = "外发加工")
    private Long outsourceOrderId;

    /** 销售产品 */
    @Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 销售订单 */
    @Excel(name = "销售订单")
    private Long saleOrderId;

    /** 施工单 */
    @Excel(name = "施工单")
    private Long produceOrderId;

    /** 施工产品 */
    @Excel(name = "施工产品")
    private Long produceOrderProductId;

    /** 产品 */
    @Excel(name = "产品")
    private Long productId;

    /** 工序 */
    @Excel(name = "工序")
    private Long processId;

    /** 生产工艺卡 */
    @Excel(name = "生产工艺卡")
    private Long productionTemplateId;

    /** 生产工艺卡工序 */
    @Excel(name = "生产工艺卡工序")
    private Long productionTemplateProcessId;

    private Long supplierId;
    private String supplierName;
    private BigDecimal amount;
    private Integer leftQty;
    private String outsourceDeliverySN;
    private String productName;



    /** 外发加工 */
    @Excel(name = "外发加工")
    private String outsourceOrderSN;


    /** 施工单 */
    @Excel(name = "施工单")
    private String produceOrderSN;

    /** 施工产品 */
    @Excel(name = "施工产品")
    private Long produceOrderProcessId;


    /** 工序 */
    @Excel(name = "工序")
    private String processName;

    /** 外发类型 */
    @Excel(name = "外发类型")
    private String outsourceType;

    /** 计价方式 */
    @Excel(name = "计价方式")
    private String outsourceValuationType;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;

    /** 退货数量 */
    @Excel(name = "退货数量")
    private Integer returnQty;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOutsourceCheckingId(Long outsourceCheckingId)
    {
        this.outsourceCheckingId = outsourceCheckingId;
    }

    public Long getOutsourceCheckingId()
    {
        return outsourceCheckingId;
    }
    public void setOutsourceDeliveryId(Long outsourceDeliveryId)
    {
        this.outsourceDeliveryId = outsourceDeliveryId;
    }

    public Long getOutsourceDeliveryId()
    {
        return outsourceDeliveryId;
    }
    public void setOutsourceDeliveryProcessId(Long outsourceDeliveryProcessId)
    {
        this.outsourceDeliveryProcessId = outsourceDeliveryProcessId;
    }

    public Long getOutsourceDeliveryProcessId()
    {
        return outsourceDeliveryProcessId;
    }
    public void setOutsourceOrderProcessId(Long outsourceOrderProcessId)
    {
        this.outsourceOrderProcessId = outsourceOrderProcessId;
    }

    public Long getOutsourceOrderProcessId()
    {
        return outsourceOrderProcessId;
    }
    public void setOutsourceOrderId(Long outsourceOrderId)
    {
        this.outsourceOrderId = outsourceOrderId;
    }

    public Long getOutsourceOrderId()
    {
        return outsourceOrderId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("outsourceCheckingId", getOutsourceCheckingId())
            .append("outsourceDeliveryId", getOutsourceDeliveryId())
            .append("outsourceDeliveryProcessId", getOutsourceDeliveryProcessId())
            .append("outsourceOrderProcessId", getOutsourceOrderProcessId())
            .append("outsourceOrderId", getOutsourceOrderId())
            .append("saleOrderProductId", getSaleOrderProductId())
            .append("saleOrderId", getSaleOrderId())
            .append("produceOrderId", getProduceOrderId())
            .append("produceOrderProductId", getProduceOrderProductId())
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("productionTemplateId", getProductionTemplateId())
            .append("productionTemplateProcessId", getProductionTemplateProcessId())
            .toString();
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getLeftQty() {
        return leftQty;
    }

    public void setLeftQty(Integer leftQty) {
        this.leftQty = leftQty;
    }

    public String getOutsourceDeliverySN() {
        return outsourceDeliverySN;
    }

    public void setOutsourceDeliverySN(String outsourceDeliverySN) {
        this.outsourceDeliverySN = outsourceDeliverySN;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOutsourceOrderSN() {
        return outsourceOrderSN;
    }

    public void setOutsourceOrderSN(String outsourceOrderSN) {
        this.outsourceOrderSN = outsourceOrderSN;
    }

    public String getProduceOrderSN() {
        return produceOrderSN;
    }

    public void setProduceOrderSN(String produceOrderSN) {
        this.produceOrderSN = produceOrderSN;
    }

    public Long getProduceOrderProcessId() {
        return produceOrderProcessId;
    }

    public void setProduceOrderProcessId(Long produceOrderProcessId) {
        this.produceOrderProcessId = produceOrderProcessId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getOutsourceType() {
        return outsourceType;
    }

    public void setOutsourceType(String outsourceType) {
        this.outsourceType = outsourceType;
    }

    public String getOutsourceValuationType() {
        return outsourceValuationType;
    }

    public void setOutsourceValuationType(String outsourceValuationType) {
        this.outsourceValuationType = outsourceValuationType;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(Integer returnQty) {
        this.returnQty = returnQty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
