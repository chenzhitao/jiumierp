package com.ruoyi.project.purchase.purchaseDeliveryMaterials.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 采购到货材料对象 purchase_delivery_materials
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public class PurchaseDeliveryMaterials extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private Long purchaseOrderMaterialsId;
    private Long purchaseReturnId;
    private Long purchaseCheckingId;

    /** 采购到货 */
    @Excel(name = "采购到货")
    private String purchaseDeliverySN;
    private Long purchaseDeliveryId;

    /** 采购订单 */
    @Excel(name = "采购订单")
    private String purchaseOrderSN;
    private Long purchaseOrderId;

    /** 施工单 */
    //@Excel(name = "施工单")
    private String produceOrderSN;
    private Long produceOrderId;

    /** 施工材料 */
    //@Excel(name = "施工材料")
    private Long produceOrderMaterialsId;

    /** 销售订单 */
    //@Excel(name = "销售订单")
    private Long saleOrderId;

    /** 销售产品 */
    //@Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 采购申请单 */
    @Excel(name = "采购申请单")
    private String purchaseRequestSN;
    private Long purchaseRequestId;

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

    /** 仓库 */
    @Excel(name = "仓库")
    private String warehouseName;
    private Long warehouseId;

    /** 订单单价 */
    //@Excel(name = "订单单价")
    private BigDecimal orgPrice;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;
    private String materialsSize;
    /** 规格长 */
    @Excel(name = "规格长")
    private Integer sizeLong;

    /** 规格宽 */
    @Excel(name = "规格宽")
    private Integer sizeWidth;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;

    /** 退货数量 */
    @Excel(name = "退货数量")
    private Integer returnQty;

    /** 税率 */
    @Excel(name = "税率")
    private String taxName;
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

    /** 送货地址 */
    @Excel(name = "送货地址")
    private String address;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPurchaseDeliveryId(Long purchaseDeliveryId)
    {
        this.purchaseDeliveryId = purchaseDeliveryId;
    }

    public Long getPurchaseDeliveryId()
    {
        return purchaseDeliveryId;
    }
    public void setPurchaseOrderId(Long purchaseOrderId)
    {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getPurchaseOrderId()
    {
        return purchaseOrderId;
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
    public void setPurchaseRequestId(Long purchaseRequestId)
    {
        this.purchaseRequestId = purchaseRequestId;
    }

    public Long getPurchaseRequestId()
    {
        return purchaseRequestId;
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
    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }
    public void setOrgPrice(BigDecimal orgPrice)
    {
        this.orgPrice = orgPrice;
    }

    public BigDecimal getOrgPrice()
    {
        return orgPrice;
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
    public void setReturnQty(Integer returnQty)
    {
        this.returnQty = returnQty;
    }

    public Integer getReturnQty()
    {
        return returnQty;
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
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("purchaseDeliveryId", getPurchaseDeliveryId())
            .append("purchaseOrderId", getPurchaseOrderId())
            .append("produceOrderId", getProduceOrderId())
            .append("produceOrderMaterialsId", getProduceOrderMaterialsId())
            .append("saleOrderId", getSaleOrderId())
            .append("saleOrderProductId", getSaleOrderProductId())
            .append("purchaseRequestId", getPurchaseRequestId())
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("materialsId", getMaterialsId())
            .append("warehouseId", getWarehouseId())
            .append("orgPrice", getOrgPrice())
            .append("price", getPrice())
            .append("sizeLong", getSizeLong())
            .append("sizeWidth", getSizeWidth())
            .append("qty", getQty())
            .append("returnQty", getReturnQty())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("deliveryDate", getDeliveryDate())
            .append("requirements", getRequirements())
            .append("address", getAddress())
            .toString();
    }

    public String getPurchaseDeliverySN() {
        return purchaseDeliverySN;
    }

    public void setPurchaseDeliverySN(String purchaseDeliverySN) {
        this.purchaseDeliverySN = purchaseDeliverySN;
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

    public String getTaxName() {
        if(null==this.taxRate){
            return null;
        }else{
            return this.taxRate.multiply(new BigDecimal(100)).toString() + "%";
        }
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public Long getPurchaseOrderMaterialsId() {
        return purchaseOrderMaterialsId;
    }

    public void setPurchaseOrderMaterialsId(Long purchaseOrderMaterialsId) {
        this.purchaseOrderMaterialsId = purchaseOrderMaterialsId;
    }

    public String getProduceOrderSN() {
        return produceOrderSN;
    }

    public void setProduceOrderSN(String produceOrderSN) {
        this.produceOrderSN = produceOrderSN;
    }

    public Long getPurchaseReturnId() {
        return purchaseReturnId;
    }

    public void setPurchaseReturnId(Long purchaseReturnId) {
        this.purchaseReturnId = purchaseReturnId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getPurchaseCheckingId() {
        return purchaseCheckingId;
    }

    public void setPurchaseCheckingId(Long purchaseCheckingId) {
        this.purchaseCheckingId = purchaseCheckingId;
    }

    public String getMaterialsSize() {
        return materialsSize;
    }

    public void setMaterialsSize(String materialsSize) {
        this.materialsSize = materialsSize;
    }
}
