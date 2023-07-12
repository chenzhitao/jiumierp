package com.ruoyi.project.purchase.purchaseCheckingMaterials.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购对账材料对象 purchase_checking_materials
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public class PurchaseCheckingMaterials extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 销售对账 */
    @Excel(name = "销售对账")
    private Long purchaseCheckingId;

    /** 采购订单 */
    @Excel(name = "采购订单")
    private Long purchaseOrderId;

    /** 采购订单材料 */
    @Excel(name = "采购订单材料")
    private Long purchaseOrderMaterialsId;

    /** 采购到货 */
    @Excel(name = "采购到货")
    private Long purchaseDeliveryId;

    /** 采购到货材料 */
    @Excel(name = "采购到货材料")
    private Long purchaseDeliveryMaterialsId;

    /** 采购申请单 */
    @Excel(name = "采购申请单")
    private Long purchaseRequestId;

    /** 施工单 */
    @Excel(name = "施工单")
    private Long produceOrderId;

    /** 施工材料 */
    @Excel(name = "施工材料")
    private Long produceOrderMaterialsId;

    /** 销售订单 */
    @Excel(name = "销售订单")
    private Long saleOrderId;

    /** 销售产品 */
    @Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 关联产品 */
    @Excel(name = "关联产品")
    private Long productId;

    /** 关联工序 */
    @Excel(name = "关联工序")
    private Long processId;

    /** 材料 */
    @Excel(name = "材料")
    private Long materialsId;

    /** 供应商 */
    @Excel(name = "供应商")
    private Long supplierId;

    /** ID */
    private Long purchaseReturnId;

    /** 采购到货 */
    @Excel(name = "采购到货")
    private String purchaseDeliverySN;

    /** 采购订单 */
    @Excel(name = "采购订单")
    private String purchaseOrderSN;

    /** 施工单 */
    //@Excel(name = "施工单")
    private String produceOrderSN;

    /** 施工材料 */
    //@Excel(name = "施工材料")

    /** 销售订单 */
    //@Excel(name = "销售订单")

    /** 销售产品 */
    //@Excel(name = "销售产品")

    /** 采购申请单 */
    @Excel(name = "采购申请单")
    private String purchaseRequestSN;

    /** 关联产品 */
    //@Excel(name = "关联产品")

    /** 关联工序 */
    //@Excel(name = "关联工序")

    /** 材料 */
    @Excel(name = "材料")
    private String materialsName;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplierName;

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
    public void setPurchaseCheckingId(Long purchaseCheckingId)
    {
        this.purchaseCheckingId = purchaseCheckingId;
    }

    public Long getPurchaseCheckingId()
    {
        return purchaseCheckingId;
    }
    public void setPurchaseOrderId(Long purchaseOrderId)
    {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getPurchaseOrderId()
    {
        return purchaseOrderId;
    }
    public void setPurchaseOrderMaterialsId(Long purchaseOrderMaterialsId)
    {
        this.purchaseOrderMaterialsId = purchaseOrderMaterialsId;
    }

    public Long getPurchaseOrderMaterialsId()
    {
        return purchaseOrderMaterialsId;
    }
    public void setPurchaseDeliveryId(Long purchaseDeliveryId)
    {
        this.purchaseDeliveryId = purchaseDeliveryId;
    }

    public Long getPurchaseDeliveryId()
    {
        return purchaseDeliveryId;
    }
    public void setPurchaseDeliveryMaterialsId(Long purchaseDeliveryMaterialsId)
    {
        this.purchaseDeliveryMaterialsId = purchaseDeliveryMaterialsId;
    }

    public Long getPurchaseDeliveryMaterialsId()
    {
        return purchaseDeliveryMaterialsId;
    }
    public void setPurchaseRequestId(Long purchaseRequestId)
    {
        this.purchaseRequestId = purchaseRequestId;
    }

    public Long getPurchaseRequestId()
    {
        return purchaseRequestId;
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
    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("purchaseCheckingId", getPurchaseCheckingId())
            .append("purchaseOrderId", getPurchaseOrderId())
            .append("purchaseOrderMaterialsId", getPurchaseOrderMaterialsId())
            .append("purchaseDeliveryId", getPurchaseDeliveryId())
            .append("purchaseDeliveryMaterialsId", getPurchaseDeliveryMaterialsId())
            .append("purchaseRequestId", getPurchaseRequestId())
            .append("produceOrderId", getProduceOrderId())
            .append("produceOrderMaterialsId", getProduceOrderMaterialsId())
            .append("saleOrderId", getSaleOrderId())
            .append("saleOrderProductId", getSaleOrderProductId())
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("materialsId", getMaterialsId())
            .append("supplierId", getSupplierId())
            .toString();
    }

    public Long getPurchaseReturnId() {
        return purchaseReturnId;
    }

    public void setPurchaseReturnId(Long purchaseReturnId) {
        this.purchaseReturnId = purchaseReturnId;
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

    public String getProduceOrderSN() {
        return produceOrderSN;
    }

    public void setProduceOrderSN(String produceOrderSN) {
        this.produceOrderSN = produceOrderSN;
    }

    public String getPurchaseRequestSN() {
        return purchaseRequestSN;
    }

    public void setPurchaseRequestSN(String purchaseRequestSN) {
        this.purchaseRequestSN = purchaseRequestSN;
    }

    public String getMaterialsName() {
        return materialsName;
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

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public BigDecimal getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(BigDecimal orgPrice) {
        this.orgPrice = orgPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
