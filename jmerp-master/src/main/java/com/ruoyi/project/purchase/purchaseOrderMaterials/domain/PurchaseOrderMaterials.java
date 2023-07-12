package com.ruoyi.project.purchase.purchaseOrderMaterials.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 采购订单材料对象 purchase_order_materials
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public class PurchaseOrderMaterials extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private Long purchaseDeliveryId;

    /** 采购订单 */
    @Excel(name = "采购订单")
    private String purchaseOrderSN;
    private Long purchaseOrderId;

    /** 采购申请单 */
    @Excel(name = "采购申请单")
    private String purchaseRequestSN;
    private Long purchaseRequestId;

    /** 销售订单 */
    //@Excel(name = "销售订单")
    private Long saleOrderId;

    /** 销售产品 */
    //@Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 施工单 */
    @Excel(name = "施工单")
    private String produceOrderSN;
    private Long produceOrderId;

    /** 施工材料 */
    //@Excel(name = "施工材料")
    private Long produceOrderMaterialsId;

    /** 关联产品 */
    //@Excel(name = "关联产品")
    private Long productId;

    /** 关联工序 */
    //@Excel(name = "关联工序")
    private Long processId;

    /** 材料 */
    @Excel(name = "材料")
    private String materialsName;
    private Long materialsId;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplierName;
    private Long supplierId;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    @Excel(name = "规格")
    private String materialsSize;

    /** 规格长 */
    //@Excel(name = "规格长")
    private Integer sizeLong;

    /** 规格宽 */
    //@Excel(name = "规格宽")
    private Integer sizeWidth;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;
    private Integer unDeliveryQty;

    /** 税率 */
    @Excel(name = "税率", taxRate = "tax")
    private BigDecimal taxRate;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    /** 到货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    /** 材料要求 */
    @Excel(name = "材料要求")
    private String requirements;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPurchaseOrderId(Long purchaseOrderId)
    {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getPurchaseOrderId()
    {
        return purchaseOrderId;
    }
    public void setPurchaseRequestId(Long purchaseRequestId)
    {
        this.purchaseRequestId = purchaseRequestId;
    }

    public Long getPurchaseRequestId()
    {
        return purchaseRequestId;
    }
    public void setSaleOrderId(Long saleOrderId)
    {
        this.saleOrderId = saleOrderId;
    }

    public Long getSaleOrderId()
    {
        return saleOrderId;
    }
    public void setSaleOrderProductId(Long saleOrderProductId)
    {
        this.saleOrderProductId = saleOrderProductId;
    }

    public Long getSaleOrderProductId()
    {
        return saleOrderProductId;
    }
    public void setProduceOrderId(Long produceOrderId)
    {
        this.produceOrderId = produceOrderId;
    }

    public Long getProduceOrderId()
    {
        return produceOrderId;
    }
    public void setProduceOrderMaterialsId(Long produceOrderMaterialsId)
    {
        this.produceOrderMaterialsId = produceOrderMaterialsId;
    }

    public Long getProduceOrderMaterialsId()
    {
        return produceOrderMaterialsId;
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
    public void setMaterialsId(Long materialsId)
    {
        this.materialsId = materialsId;
    }

    public Long getMaterialsId()
    {
        return materialsId;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setSizeLong(Integer sizeLong)
    {
        this.sizeLong = sizeLong;
    }

    public Integer getSizeLong()
    {
        return sizeLong;
    }
    public void setSizeWidth(Integer sizeWidth)
    {
        this.sizeWidth = sizeWidth;
    }

    public Integer getSizeWidth()
    {
        return sizeWidth;
    }
    public void setQty(Integer qty)
    {
        this.qty = qty;
    }

    public Integer getQty()
    {
        return qty;
    }
    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate()
    {
        return taxRate;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }
    public void setDeliveryDate(Date deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate()
    {
        return deliveryDate;
    }
    public void setRequirements(String requirements)
    {
        this.requirements = requirements;
    }

    public String getRequirements()
    {
        return requirements;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("purchaseOrderId", getPurchaseOrderId())
            .append("purchaseRequestId", getPurchaseRequestId())
            .append("saleOrderId", getSaleOrderId())
            .append("saleOrderProductId", getSaleOrderProductId())
            .append("produceOrderId", getProduceOrderId())
            .append("produceOrderMaterialsId", getProduceOrderMaterialsId())
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("materialsId", getMaterialsId())
            .append("price", getPrice())
            .append("sizeLong", getSizeLong())
            .append("sizeWidth", getSizeWidth())
            .append("qty", getQty())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("deliveryDate", getDeliveryDate())
            .append("requirements", getRequirements())
            .toString();
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public String getPurchaseOrderSN() {
        return purchaseOrderSN;
    }

    public void setPurchaseOrderSN(String purchaseOrderSN) {
        this.purchaseOrderSN = purchaseOrderSN;
    }

    public String getPurchaseRequestSN() {
        return purchaseRequestSN;
    }

    public void setPurchaseRequestSN(String purchaseRequestSN) {
        this.purchaseRequestSN = purchaseRequestSN;
    }

    public String getProduceOrderSN() {
        return produceOrderSN;
    }

    public void setProduceOrderSN(String produceOrderSN) {
        this.produceOrderSN = produceOrderSN;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getPurchaseDeliveryId() {
        return purchaseDeliveryId;
    }

    public void setPurchaseDeliveryId(Long purchaseDeliveryId) {
        this.purchaseDeliveryId = purchaseDeliveryId;
    }

    public Integer getUnDeliveryQty() {
        return unDeliveryQty;
    }

    public void setUnDeliveryQty(Integer unDeliveryQty) {
        this.unDeliveryQty = unDeliveryQty;
    }

    public String getMaterialsSize() {
        return materialsSize;
    }

    public void setMaterialsSize(String materialsSize) {
        this.materialsSize = materialsSize;
    }
}
