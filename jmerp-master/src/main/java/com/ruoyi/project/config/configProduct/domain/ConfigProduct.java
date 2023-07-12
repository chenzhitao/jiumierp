package com.ruoyi.project.config.configProduct.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 产品配置对象 config_product
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private Long parentId;
    private String productLevel;
    private String productParentName;
    private String fullName;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 彩印/纸箱 */
    @Excel(name = "产品类型", dictType = "product_type")
    private String productType;

    /** 客户 */
    @Excel(name = "客户")
    private String customerName;
    private Long customerId;

    /** 是否公共产品 */
    @Excel(name = "公共产品", dictType = "sys_yes_no")
    private String isPublic;

    /** 客户料号 */
    @Excel(name = "客户料号")
    private String customerMaterialNo;

    /** 产品单价 */
    @Excel(name = "产品单价")
    private BigDecimal price;

    /** 外发单价 */
    @Excel(name = "外发单价")
    private BigDecimal outsourcePrice;

    /** 销售单位 */
    @Excel(name = "销售单位", dictType = "common_unit")
    private String saleUnit;

    /** 生产单位 */
    @Excel(name = "生产单位", dictType = "common_unit")
    private String productionUnit;

    /** 计价方式(销售) */
    @Excel(name = "计价方式(销售)", dictType = "valuation_type")
    private String saleValuationType;

    /** 计价方式(外发) */
    @Excel(name = "计价方式(外发)", dictType = "valuation_type")
    private String outsourceValuationType;

    /** 产品规格(长) */
    @Excel(name = "产品规格(长)")
    private Integer sizeLong;

    /** 产品规格(宽) */
    @Excel(name = "产品规格(宽)")
    private Integer sizeWidth;

    /** 产品规格(高) */
    @Excel(name = "产品规格(高)")
    private Integer sizeHeight;

    /** 展长 */
    @Excel(name = "展长")
    private Integer opensizeLong;

    /** 展宽 */
    @Excel(name = "展宽")
    private Integer opensizeWidth;

    /** 单只面积(m²) */
    @Excel(name = "单只面积(m²)")
    private BigDecimal area;

    /** 手工费 */
    @Excel(name = "手工费")
    private BigDecimal handworkPrice;

    /** 面积公式 */
    @Excel(name = "面积公式")
    private String formulaName;
    private Long formulaId;

    /** 产品图片 */
    //@Excel(name = "产品图片")
    private String productImg;

    /** 印刷主材 */
    @Excel(name = "印刷主材")
    private String materialsName;
    private Long materialsId;

    /** 报价工艺卡 */
    @Excel(name = "报价工艺卡")
    private String quotationTemplateName;
    private Long quotationTemplateId;

    /** 生产工艺卡 */
    @Excel(name = "生产工艺卡")
    private String productionTemplateName;
    private Long productionTemplateId;

    /** 上机长(mm) */
    @Excel(name = "上机长(mm)")
    private Integer workLong;

    /** 上机宽(mm) */
    @Excel(name = "上机宽(mm)")
    private Integer workWidth;

    /** 大色面版 */
    @Excel(name = "大色面版", dictType = "sys_yes_no")
    private String isBigPanel;

    /** 特殊纸张 */
    @Excel(name = "特殊纸张", dictType = "sys_yes_no")
    private String isSpecialPaper;

    /** 印张正数 */
    @Excel(name = "印张正数")
    private Integer printSheetQty;

    /** 总印张数 */
    @Excel(name = "总印张数")
    private Integer printSheetSumQty;

    /** 拼版数 */
    @Excel(name = "拼版数")
    private Integer puzzleQty;

    /** 印版付数 */
    @Excel(name = "印版付数")
    private Integer formeQty;

    /** 印刷方式 */
    @Excel(name = "印刷方式", dictType = "print_type")
    private String printType;

    /** 拉规方式 */
    @Excel(name = "拉规方式", dictType = "gauge_type")
    private String gaugeType;

    /** 是否到底 */
    @Excel(name = "是否到底", dictType = "sys_yes_no")
    private String isButtom;

    /** 正面专色 */
    @Excel(name = "正面专色")
    private String frontSpot;

    /** 反面专色 */
    @Excel(name = "反面专色")
    private String backSpot;

    /** 正面颜色 */
    @Excel(name = "正面颜色")
    private String frontColor;

    /** 反面颜色 */
    @Excel(name = "反面颜色")
    private String backColor;

    /** 箱型 */
    @Excel(name = "箱型")
    private String cartonShapeName;
    private Long cartonShapeId;

    /** 楞型 */
    @Excel(name = "楞型")
    private String corrugatedName;
    private Long corrugatedId;

    /** 纸张配方 */
    @Excel(name = "纸张配方")
    private String paperFormulaName;
    private Long paperFormulaId;

    /** 生产类型 */
    @Excel(name = "生产类型", dictType = "carton_size_type")
    private String cartonSizeType;

    /** 孔型 */
    @Excel(name = "孔型", dictType = "hole_pattern")
    private String holePattern;

    /** 压线类型 */
    @Excel(name = "压线类型", dictType = "wire_type")
    private String wireType;

    /** 压线深度 */
    @Excel(name = "压线深度", dictType = "wire_deep")
    private String wireDeep;

    /** 生产横向压线 */
    @Excel(name = "生产横向压线")
    private String crossWise;

    /** 生产纵向压线 */
    @Excel(name = "生产纵向压线")
    private String verticalWise;

    /** 生产尺寸 */
    @Excel(name = "生产尺寸")
    private String productionSize;

    /** 误差范围 */
    @Excel(name = "误差范围")
    private String errorRange;

    /** 印刷工艺 */
    @Excel(name = "印刷工艺", dictType = "print_technology_type")
    private String printTechnologyType;

    /** 印刷次数 */
    @Excel(name = "印刷次数")
    private Integer printTimes;

    /** 印版数 */
    @Excel(name = "印版数")
    private Integer plateQty;

    /** 原模仓库 */
    @Excel(name = "原模仓库")
    private String dieWarehouseName;
    private Long dieWarehouseId;

    /** 原模货架号 */
    @Excel(name = "原模货架号")
    private String dieShelvesNo;

    /** 印刷方向 */
    @Excel(name = "印刷方向", dictType = "print_direction")
    private String printDirection;

    /** 是否套色 */
    @Excel(name = "是否套色", dictType = "sys_yes_no")
    private String isAquatone;

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
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
    }
    public void setProductType(String productType)
    {
        this.productType = productType;
    }

    public String getProductType()
    {
        return productType;
    }
    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }
    public void setIsPublic(String isPublic)
    {
        this.isPublic = isPublic;
    }

    public String getIsPublic()
    {
        return isPublic;
    }
    public void setCustomerMaterialNo(String customerMaterialNo)
    {
        this.customerMaterialNo = customerMaterialNo;
    }

    public String getCustomerMaterialNo()
    {
        return customerMaterialNo;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setOutsourcePrice(BigDecimal outsourcePrice)
    {
        this.outsourcePrice = outsourcePrice;
    }

    public BigDecimal getOutsourcePrice()
    {
        return outsourcePrice;
    }
    public void setSaleUnit(String saleUnit)
    {
        this.saleUnit = saleUnit;
    }

    public String getSaleUnit()
    {
        return saleUnit;
    }
    public void setProductionUnit(String productionUnit)
    {
        this.productionUnit = productionUnit;
    }

    public String getProductionUnit()
    {
        return productionUnit;
    }
    public void setSaleValuationType(String saleValuationType)
    {
        this.saleValuationType = saleValuationType;
    }

    public String getSaleValuationType()
    {
        return saleValuationType;
    }
    public void setOutsourceValuationType(String outsourceValuationType)
    {
        this.outsourceValuationType = outsourceValuationType;
    }

    public String getOutsourceValuationType()
    {
        return outsourceValuationType;
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
    public void setSizeHeight(Integer sizeHeight)
    {
        this.sizeHeight = sizeHeight;
    }

    public Integer getSizeHeight()
    {
        return sizeHeight;
    }
    public void setOpensizeLong(Integer opensizeLong)
    {
        this.opensizeLong = opensizeLong;
    }

    public Integer getOpensizeLong()
    {
        return opensizeLong;
    }
    public void setOpensizeWidth(Integer opensizeWidth)
    {
        this.opensizeWidth = opensizeWidth;
    }

    public Integer getOpensizeWidth()
    {
        return opensizeWidth;
    }
    public void setArea(BigDecimal area)
    {
        this.area = area;
    }

    public BigDecimal getArea()
    {
        return area;
    }
    public void setHandworkPrice(BigDecimal handworkPrice)
    {
        this.handworkPrice = handworkPrice;
    }

    public BigDecimal getHandworkPrice()
    {
        return handworkPrice;
    }
    public void setFormulaId(Long formulaId)
    {
        this.formulaId = formulaId;
    }

    public Long getFormulaId()
    {
        return formulaId;
    }
    public void setProductImg(String productImg)
    {
        this.productImg = productImg;
    }

    public String getProductImg()
    {
        return productImg;
    }
    public void setMaterialsId(Long materialsId)
    {
        this.materialsId = materialsId;
    }

    public Long getMaterialsId()
    {
        return materialsId;
    }
    public void setQuotationTemplateId(Long quotationTemplateId)
    {
        this.quotationTemplateId = quotationTemplateId;
    }

    public Long getQuotationTemplateId()
    {
        return quotationTemplateId;
    }
    public void setProductionTemplateId(Long productionTemplateId)
    {
        this.productionTemplateId = productionTemplateId;
    }

    public Long getProductionTemplateId()
    {
        return productionTemplateId;
    }
    public void setWorkLong(Integer workLong)
    {
        this.workLong = workLong;
    }

    public Integer getWorkLong()
    {
        return workLong;
    }
    public void setWorkWidth(Integer workWidth)
    {
        this.workWidth = workWidth;
    }

    public Integer getWorkWidth()
    {
        return workWidth;
    }
    public void setIsBigPanel(String isBigPanel)
    {
        this.isBigPanel = isBigPanel;
    }

    public String getIsBigPanel()
    {
        return isBigPanel;
    }
    public void setIsSpecialPaper(String isSpecialPaper)
    {
        this.isSpecialPaper = isSpecialPaper;
    }

    public String getIsSpecialPaper()
    {
        return isSpecialPaper;
    }
    public void setPrintSheetQty(Integer printSheetQty)
    {
        this.printSheetQty = printSheetQty;
    }

    public Integer getPrintSheetQty()
    {
        return printSheetQty;
    }
    public void setPrintSheetSumQty(Integer printSheetSumQty)
    {
        this.printSheetSumQty = printSheetSumQty;
    }

    public Integer getPrintSheetSumQty()
    {
        return printSheetSumQty;
    }
    public void setPuzzleQty(Integer puzzleQty)
    {
        this.puzzleQty = puzzleQty;
    }

    public Integer getPuzzleQty()
    {
        return puzzleQty;
    }
    public void setFormeQty(Integer formeQty)
    {
        this.formeQty = formeQty;
    }

    public Integer getFormeQty()
    {
        return formeQty;
    }
    public void setPrintType(String printType)
    {
        this.printType = printType;
    }

    public String getPrintType()
    {
        return printType;
    }
    public void setGaugeType(String gaugeType)
    {
        this.gaugeType = gaugeType;
    }

    public String getGaugeType()
    {
        return gaugeType;
    }
    public void setIsButtom(String isButtom)
    {
        this.isButtom = isButtom;
    }

    public String getIsButtom()
    {
        return isButtom;
    }
    public void setFrontSpot(String frontSpot)
    {
        this.frontSpot = frontSpot;
    }

    public String getFrontSpot()
    {
        return frontSpot;
    }
    public void setBackSpot(String backSpot)
    {
        this.backSpot = backSpot;
    }

    public String getBackSpot()
    {
        return backSpot;
    }
    public void setFrontColor(String frontColor)
    {
        this.frontColor = frontColor;
    }

    public String getFrontColor()
    {
        return frontColor;
    }
    public void setBackColor(String backColor)
    {
        this.backColor = backColor;
    }

    public String getBackColor()
    {
        return backColor;
    }
    public void setCartonShapeId(Long cartonShapeId)
    {
        this.cartonShapeId = cartonShapeId;
    }

    public Long getCartonShapeId()
    {
        return cartonShapeId;
    }
    public void setCorrugatedId(Long corrugatedId)
    {
        this.corrugatedId = corrugatedId;
    }

    public Long getCorrugatedId()
    {
        return corrugatedId;
    }
    public void setPaperFormulaId(Long paperFormulaId)
    {
        this.paperFormulaId = paperFormulaId;
    }

    public Long getPaperFormulaId()
    {
        return paperFormulaId;
    }
    public void setCartonSizeType(String cartonSizeType)
    {
        this.cartonSizeType = cartonSizeType;
    }

    public String getCartonSizeType()
    {
        return cartonSizeType;
    }
    public void setHolePattern(String holePattern)
    {
        this.holePattern = holePattern;
    }

    public String getHolePattern()
    {
        return holePattern;
    }
    public void setWireType(String wireType)
    {
        this.wireType = wireType;
    }

    public String getWireType()
    {
        return wireType;
    }
    public void setWireDeep(String wireDeep)
    {
        this.wireDeep = wireDeep;
    }

    public String getWireDeep()
    {
        return wireDeep;
    }
    public void setCrossWise(String crossWise)
    {
        this.crossWise = crossWise;
    }

    public String getCrossWise()
    {
        return crossWise;
    }
    public void setVerticalWise(String verticalWise)
    {
        this.verticalWise = verticalWise;
    }

    public String getVerticalWise()
    {
        return verticalWise;
    }
    public void setProductionSize(String productionSize)
    {
        this.productionSize = productionSize;
    }

    public String getProductionSize()
    {
        return productionSize;
    }
    public void setErrorRange(String errorRange)
    {
        this.errorRange = errorRange;
    }

    public String getErrorRange()
    {
        return errorRange;
    }
    public void setPrintTechnologyType(String printTechnologyType)
    {
        this.printTechnologyType = printTechnologyType;
    }

    public String getPrintTechnologyType()
    {
        return printTechnologyType;
    }
    public void setPrintTimes(Integer printTimes)
    {
        this.printTimes = printTimes;
    }

    public Integer getPrintTimes()
    {
        return printTimes;
    }
    public void setPlateQty(Integer plateQty)
    {
        this.plateQty = plateQty;
    }

    public Integer getPlateQty()
    {
        return plateQty;
    }
    public void setDieWarehouseId(Long dieWarehouseId)
    {
        this.dieWarehouseId = dieWarehouseId;
    }

    public Long getDieWarehouseId()
    {
        return dieWarehouseId;
    }
    public void setDieShelvesNo(String dieShelvesNo)
    {
        this.dieShelvesNo = dieShelvesNo;
    }

    public String getDieShelvesNo()
    {
        return dieShelvesNo;
    }
    public void setPrintDirection(String printDirection)
    {
        this.printDirection = printDirection;
    }

    public String getPrintDirection()
    {
        return printDirection;
    }
    public void setIsAquatone(String isAquatone)
    {
        this.isAquatone = isAquatone;
    }

    public String getIsAquatone()
    {
        return isAquatone;
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
            .append("productName", getProductName())
            .append("productType", getProductType())
            .append("customerId", getCustomerId())
            .append("isPublic", getIsPublic())
            .append("customerMaterialNo", getCustomerMaterialNo())
            .append("price", getPrice())
            .append("outsourcePrice", getOutsourcePrice())
            .append("saleUnit", getSaleUnit())
            .append("productionUnit", getProductionUnit())
            .append("saleValuationType", getSaleValuationType())
            .append("outsourceValuationType", getOutsourceValuationType())
            .append("sizeLong", getSizeLong())
            .append("sizeWidth", getSizeWidth())
            .append("sizeHeight", getSizeHeight())
            .append("opensizeLong", getOpensizeLong())
            .append("opensizeWidth", getOpensizeWidth())
            .append("area", getArea())
            .append("handworkPrice", getHandworkPrice())
            .append("formulaId", getFormulaId())
            .append("productImg", getProductImg())
            .append("materialsId", getMaterialsId())
            .append("quotationTemplateId", getQuotationTemplateId())
            .append("productionTemplateId", getProductionTemplateId())
            .append("workLong", getWorkLong())
            .append("workWidth", getWorkWidth())
            .append("isBigPanel", getIsBigPanel())
            .append("isSpecialPaper", getIsSpecialPaper())
            .append("printSheetQty", getPrintSheetQty())
            .append("printSheetSumQty", getPrintSheetSumQty())
            .append("puzzleQty", getPuzzleQty())
            .append("formeQty", getFormeQty())
            .append("printType", getPrintType())
            .append("gaugeType", getGaugeType())
            .append("isButtom", getIsButtom())
            .append("frontSpot", getFrontSpot())
            .append("backSpot", getBackSpot())
            .append("frontColor", getFrontColor())
            .append("backColor", getBackColor())
            .append("cartonShapeId", getCartonShapeId())
            .append("corrugatedId", getCorrugatedId())
            .append("paperFormulaId", getPaperFormulaId())
            .append("cartonSizeType", getCartonSizeType())
            .append("holePattern", getHolePattern())
            .append("wireType", getWireType())
            .append("wireDeep", getWireDeep())
            .append("crossWise", getCrossWise())
            .append("verticalWise", getVerticalWise())
            .append("productionSize", getProductionSize())
            .append("errorRange", getErrorRange())
            .append("printTechnologyType", getPrintTechnologyType())
            .append("printTimes", getPrintTimes())
            .append("plateQty", getPlateQty())
            .append("dieWarehouseId", getDieWarehouseId())
            .append("dieShelvesNo", getDieShelvesNo())
            .append("printDirection", getPrintDirection())
            .append("isAquatone", getIsAquatone())
            .toString();
    }

    public String getQuotationTemplateName() {
        return quotationTemplateName;
    }

    public void setQuotationTemplateName(String quotationTemplateName) {
        this.quotationTemplateName = quotationTemplateName;
    }

    public String getProductionTemplateName() {
        return productionTemplateName;
    }

    public void setProductionTemplateName(String productionTemplateName) {
        this.productionTemplateName = productionTemplateName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public String getCartonShapeName() {
        return cartonShapeName;
    }

    public void setCartonShapeName(String cartonShapeName) {
        this.cartonShapeName = cartonShapeName;
    }

    public String getCorrugatedName() {
        return corrugatedName;
    }

    public void setCorrugatedName(String corrugatedName) {
        this.corrugatedName = corrugatedName;
    }

    public String getPaperFormulaName() {
        return paperFormulaName;
    }

    public String getDieWarehouseName() {
        return dieWarehouseName;
    }

    public void setDieWarehouseName(String dieWarehouseName) {
        this.dieWarehouseName = dieWarehouseName;
    }

    public void setPaperFormulaName(String paperFormulaName) {
        this.paperFormulaName = paperFormulaName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(String productLevel) {
        this.productLevel = productLevel;
    }

    public String getProductParentName() {
        return productParentName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setProductParentName(String productParentName) {
        this.productParentName = productParentName;
    }
}
