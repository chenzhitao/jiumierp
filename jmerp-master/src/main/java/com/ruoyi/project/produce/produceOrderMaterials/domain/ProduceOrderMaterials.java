package com.ruoyi.project.produce.produceOrderMaterials.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 施工材料对象 produce_order_materials
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public class ProduceOrderMaterials extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private String supplierName;
    private Integer sizeLong;
    private Integer sizeWidth;
    private Float sizeLongIn;
    private Float sizeWidthIn;
    private BigDecimal purchasePrice;
    private Integer warehouseQty;
    private Integer requestCount;
    private Integer cuttingSizeLong;
    private Integer cuttingSizeWidth;
    private Integer processSizeLong;
    private Integer processSizeWidth;
    private String longSystem;
    private String materialsType;
    private String alias;
    private Integer inQty;
    private String requirements;

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

    /** 产品 */
    @Excel(name = "产品")
    private Long productId;

    /** 关联工序 */
    @Excel(name = "关联工序")
    private String processName;
    private Long processId;

    /** 生产工艺卡 */
    @Excel(name = "生产工艺卡")
    private Long productionTemplateId;

    /** 生产工艺卡工序 */
    @Excel(name = "生产工艺卡工序")
    private Long productionTemplateProcessId;

    /** 材料 */
    @Excel(name = "材料")
    private String materialsName;
    private String materialsName2;
    private Long materialsId;

    /** 刀模 */
    @Excel(name = "刀模")
    private String cutterDieName;
    private Long cutterDieId;

    /** 板材 */
    @Excel(name = "板材")
    private String boardName;
    private Long boardId;

    /** 取工序数量 */
    @Excel(name = "取工序数量")
    private String isGetProcessQty;

    /** 比例 */
    @Excel(name = "比例")
    private BigDecimal baseRate;

    /** 损耗率(%) */
    @Excel(name = "损耗率(%)")
    private BigDecimal lossRate;

    /** 固定损耗数 */
    @Excel(name = "固定损耗数")
    private Integer lossQty;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;
    /** 数量 */
    @Excel(name = "退料数量")
    private Integer returnQty;

    /** 仓库 */
    @Excel(name = "仓库")
    private String warehouseName;
    private Long warehouseId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
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
    public void setMaterialsId(Long materialsId)
    {
        this.materialsId = materialsId;
    }

    public Long getMaterialsId()
    {
        return materialsId;
    }
    public void setCutterDieId(Long cutterDieId)
    {
        this.cutterDieId = cutterDieId;
    }

    public Long getCutterDieId()
    {
        return cutterDieId;
    }
    public void setBoardId(Long boardId)
    {
        this.boardId = boardId;
    }

    public Long getBoardId()
    {
        return boardId;
    }
    public void setIsGetProcessQty(String isGetProcessQty)
    {
        this.isGetProcessQty = isGetProcessQty;
    }

    public String getIsGetProcessQty()
    {
        return isGetProcessQty;
    }
    public void setBaseRate(BigDecimal baseRate)
    {
        this.baseRate = baseRate;
    }

    public BigDecimal getBaseRate()
    {
        return baseRate;
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
            .append("produceOrderProductId", getProduceOrderProductId())
            .append("produceOrderId", getProduceOrderId())
            .append("saleOrderProductId", getSaleOrderProductId())
            .append("saleOrderId", getSaleOrderId())
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("productionTemplateId", getProductionTemplateId())
            .append("productionTemplateProcessId", getProductionTemplateProcessId())
            .append("materialsId", getMaterialsId())
            .append("cutterDieId", getCutterDieId())
            .append("boardId", getBoardId())
            .append("isGetProcessQty", getIsGetProcessQty())
            .append("baseRate", getBaseRate())
            .append("lossRate", getLossRate())
            .append("lossQty", getLossQty())
            .append("qty", getQty())
            .toString();
    }

    public Integer getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(Integer returnQty) {
        this.returnQty = returnQty;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public String getCutterDieName() {
        return cutterDieName;
    }

    public void setCutterDieName(String cutterDieName) {
        this.cutterDieName = cutterDieName;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getProduceOrderSN() {
        return produceOrderSN;
    }

    public Integer getWarehouseQty() {
        return warehouseQty;
    }

    public void setWarehouseQty(Integer warehouseQty) {
        this.warehouseQty = warehouseQty;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    public void setProduceOrderSN(String produceOrderSN) {
        this.produceOrderSN = produceOrderSN;
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

    public String getMaterialsName2() {
        return materialsName2;
    }

    public void setMaterialsName2(String materialsName2) {
        this.materialsName2 = materialsName2;
    }

    public Integer getProcessSizeLong() {
        return processSizeLong;
    }

    public void setProcessSizeLong(Integer processSizeLong) {
        this.processSizeLong = processSizeLong;
    }

    public Integer getProcessSizeWidth() {
        return processSizeWidth;
    }

    public void setProcessSizeWidth(Integer processSizeWidth) {
        this.processSizeWidth = processSizeWidth;
    }

    public String getLongSystem() {
        return longSystem;
    }

    public void setLongSystem(String longSystem) {
        this.longSystem = longSystem;
    }

    public Float getSizeLongIn() {
        return sizeLongIn;
    }

    public void setSizeLongIn(Float sizeLongIn) {
        this.sizeLongIn = sizeLongIn;
    }

    public Float getSizeWidthIn() {
        return sizeWidthIn;
    }

    public void setSizeWidthIn(Float sizeWidthIn) {
        this.sizeWidthIn = sizeWidthIn;
    }

    public String getMaterialsType() {
        return materialsType;
    }

    public void setMaterialsType(String materialsType) {
        this.materialsType = materialsType;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getInQty() {
        return inQty;
    }

    public void setInQty(Integer inQty) {
        this.inQty = inQty;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
