package com.ruoyi.project.config.configFormula.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 公式配置对象 config_formula
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigFormula extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private BigDecimal amount;
    private String calculateLog;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 公式名称 */
    @Excel(name = "公式名称")
    private String formulaName;

    /** 公式功能 */
    @Excel(name = "公式功能", dictType = "formula_type")
    private String formulaType;

    /** 产品类型 */
    @Excel(name = "产品类型", dictType = "product_type")
    private String productType;

    /** 是否定制 */
    @Excel(name = "是否定制", dictType = "sys_yes_no")
    private String isCustom;

    /** 定制公式编码 */
    @Excel(name = "定制公式编码")
    private String customFormulaCode;

    /**************************************************/

    /** 整体价格倍率 */
    @Excel(name = "整体价格倍率")
    private BigDecimal baseTimes;

    /** 基础单价 */
    @Excel(name = "基础单价")
    private BigDecimal basePrice;

    /** 最低单价 */
    @Excel(name = "最低单价")
    private BigDecimal minPrice;

    /** 最高单价 */
    @Excel(name = "最高单价")
    private BigDecimal maxPrice;

    /** 价格下限 */
    @Excel(name = "价格下限")
    private BigDecimal priceBottom;

    /** 价格上限 */
    @Excel(name = "价格上限")
    private BigDecimal priceCeiling;

    /** 最小单位数量 */
    @Excel(name = "最小单位数量")
    private Integer minUnitQty;

    /** 基数值 */
    @Excel(name = "基数值")
    private Integer baseMinQty;

    /** 系数 */
    @Excel(name = "系数")
    private BigDecimal coefficientQty;

    /** 整体倍率 */
    @Excel(name = "折扣率")
    private BigDecimal discountTimes;

    /** 整体倍率基数 */
    @Excel(name = "折扣线")
    private Integer discountQty;

    /** 额外价格 */
    @Excel(name = "额外价格")
    private BigDecimal extraPrice;

    /** 额外价格倍率 */
    @Excel(name = "额外价格倍率")
    private BigDecimal extraTimes;

    /**************************************************/

    /** 长放数 */
    @Excel(name = "放数(长)")
    private Integer extraLong;

    /** 宽放数 */
    @Excel(name = "放数(宽)")
    private Integer extraWidth;

    /** 高放数 */
    @Excel(name = "放数(高)")
    private Integer extraHeight;

    /** 展长放数 */
    @Excel(name = "展长放数")
    private Integer extraOpenLong;

    /** 展宽放数 */
    @Excel(name = "展宽放数")
    private Integer extraOpenWidth;

    /** 边 */
    @Excel(name = "边")
    private Integer sideSize;

    /** 挂头 */
    @Excel(name = "挂头")
    private Integer hangingSize;

    /** 免口 */
    @Excel(name = "免口")
    private Integer dispenseSize;

    /** 粘口 */
    @Excel(name = "粘口")
    private Integer stickSize;

    /** 面积公式 */
    @Excel(name = "展长公式")
    private String openLongFormula;

    /** 面积公式 */
    @Excel(name = "展宽公式")
    private String openWidthFormula;

    /**************************************************/

    /** 基数比例 */
    @Excel(name = "基数比例")
    private BigDecimal baseRate;

    /** 放数比例 */
    @Excel(name = "放数比例")
    private BigDecimal lossRate;

    /** 基数组 */
    @Excel(name = "基数组")
    private String baseQtyArr;

    /** 放数组 */
    @Excel(name = "放数组")
    private String lossQtyArr;

    /**************************************************/

    /** 公式说明 */
    @Excel(name = "公式说明")
    private String formulaDesc;

    /** 公式图标 */
    //@Excel(name = "公式图标")
    private String formulaIcon;

    /** 面积公式 */
    //@Excel(name = "面积公式")
    private String areaFormula;

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
    public void setFormulaName(String formulaName)
    {
        this.formulaName = formulaName;
    }

    public String getFormulaName()
    {
        return formulaName;
    }
    public void setProductType(String productType)
    {
        this.productType = productType;
    }

    public String getProductType()
    {
        return productType;
    }
    public void setFormulaDesc(String formulaDesc)
    {
        this.formulaDesc = formulaDesc;
    }

    public String getFormulaDesc()
    {
        return formulaDesc;
    }
    public void setFormulaType(String formulaType)
    {
        this.formulaType = formulaType;
    }

    public String getFormulaType()
    {
        return formulaType;
    }
    public void setIsCustom(String isCustom)
    {
        this.isCustom = isCustom;
    }

    public String getIsCustom()
    {
        return isCustom;
    }
    public void setFormulaIcon(String formulaIcon)
    {
        this.formulaIcon = formulaIcon;
    }

    public String getFormulaIcon()
    {
        return formulaIcon;
    }
    public void setCustomFormulaCode(String customFormulaCode)
    {
        this.customFormulaCode = customFormulaCode;
    }

    public String getCustomFormulaCode()
    {
        return customFormulaCode;
    }
    public void setExtraLong(Integer extraLong)
    {
        this.extraLong = extraLong;
    }

    public Integer getExtraLong()
    {
        return extraLong;
    }
    public void setExtraWidth(Integer extraWidth)
    {
        this.extraWidth = extraWidth;
    }

    public Integer getExtraWidth()
    {
        return extraWidth;
    }
    public void setExtraHeight(Integer extraHeight)
    {
        this.extraHeight = extraHeight;
    }

    public Integer getExtraHeight()
    {
        return extraHeight;
    }
    public void setExtraOpenLong(Integer extraOpenLong)
    {
        this.extraOpenLong = extraOpenLong;
    }

    public Integer getExtraOpenLong()
    {
        return extraOpenLong;
    }
    public void setExtraOpenWidth(Integer extraOpenWidth)
    {
        this.extraOpenWidth = extraOpenWidth;
    }

    public Integer getExtraOpenWidth()
    {
        return extraOpenWidth;
    }
    public void setSideSize(Integer sideSize)
    {
        this.sideSize = sideSize;
    }

    public Integer getSideSize()
    {
        return sideSize;
    }
    public void setHangingSize(Integer hangingSize)
    {
        this.hangingSize = hangingSize;
    }

    public Integer getHangingSize()
    {
        return hangingSize;
    }
    public void setDispenseSize(Integer dispenseSize)
    {
        this.dispenseSize = dispenseSize;
    }

    public Integer getDispenseSize()
    {
        return dispenseSize;
    }
    public void setStickSize(Integer stickSize)
    {
        this.stickSize = stickSize;
    }

    public Integer getStickSize()
    {
        return stickSize;
    }
    public void setAreaFormula(String areaFormula)
    {
        this.areaFormula = areaFormula;
    }

    public String getAreaFormula()
    {
        return areaFormula;
    }
    public void setBaseTimes(BigDecimal baseTimes)
    {
        this.baseTimes = baseTimes;
    }

    public BigDecimal getBaseTimes()
    {
        return baseTimes;
    }
    public void setBasePrice(BigDecimal basePrice)
    {
        this.basePrice = basePrice;
    }

    public BigDecimal getBasePrice()
    {
        return basePrice;
    }
    public void setMinPrice(BigDecimal minPrice)
    {
        this.minPrice = minPrice;
    }

    public BigDecimal getMinPrice()
    {
        return minPrice;
    }
    public void setMaxPrice(BigDecimal maxPrice)
    {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMaxPrice()
    {
        return maxPrice;
    }
    public void setPriceBottom(BigDecimal priceBottom)
    {
        this.priceBottom = priceBottom;
    }

    public BigDecimal getPriceBottom()
    {
        return priceBottom;
    }
    public void setPriceCeiling(BigDecimal priceCeiling)
    {
        this.priceCeiling = priceCeiling;
    }

    public BigDecimal getPriceCeiling()
    {
        return priceCeiling;
    }
    public void setMinUnitQty(Integer minUnitQty)
    {
        this.minUnitQty = minUnitQty;
    }

    public Integer getMinUnitQty()
    {
        return minUnitQty;
    }
    public void setBaseMinQty(Integer baseMinQty)
    {
        this.baseMinQty = baseMinQty;
    }

    public Integer getBaseMinQty()
    {
        return baseMinQty;
    }
    public void setCoefficientQty(BigDecimal coefficientQty)
    {
        this.coefficientQty = coefficientQty;
    }

    public BigDecimal getCoefficientQty()
    {
        return coefficientQty;
    }
    public void setDiscountTimes(BigDecimal discountTimes)
    {
        this.discountTimes = discountTimes;
    }

    public BigDecimal getDiscountTimes()
    {
        return discountTimes;
    }
    public void setDiscountQty(Integer discountQty)
    {
        this.discountQty = discountQty;
    }

    public Integer getDiscountQty()
    {
        return discountQty;
    }
    public void setExtraPrice(BigDecimal extraPrice)
    {
        this.extraPrice = extraPrice;
    }

    public BigDecimal getExtraPrice()
    {
        return extraPrice;
    }
    public void setExtraTimes(BigDecimal extraTimes)
    {
        this.extraTimes = extraTimes;
    }

    public BigDecimal getExtraTimes()
    {
        return extraTimes;
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
    public void setBaseQtyArr(String baseQtyArr)
    {
        this.baseQtyArr = baseQtyArr;
    }

    public String getBaseQtyArr()
    {
        return baseQtyArr;
    }
    public void setLossQtyArr(String lossQtyArr)
    {
        this.lossQtyArr = lossQtyArr;
    }

    public String getLossQtyArr()
    {
        return lossQtyArr;
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
            .append("formulaName", getFormulaName())
            .append("productType", getProductType())
            .append("formulaDesc", getFormulaDesc())
            .append("formulaType", getFormulaType())
            .append("isCustom", getIsCustom())
            .append("formulaIcon", getFormulaIcon())
            .append("customFormulaCode", getCustomFormulaCode())
            .append("extraLong", getExtraLong())
            .append("extraWidth", getExtraWidth())
            .append("extraHeight", getExtraHeight())
            .append("extraOpenLong", getExtraOpenLong())
            .append("extraOpenWidth", getExtraOpenWidth())
            .append("sideSize", getSideSize())
            .append("hangingSize", getHangingSize())
            .append("dispenseSize", getDispenseSize())
            .append("stickSize", getStickSize())
            .append("areaFormula", getAreaFormula())
            .append("baseTimes", getBaseTimes())
            .append("basePrice", getBasePrice())
            .append("minPrice", getMinPrice())
            .append("maxPrice", getMaxPrice())
            .append("priceBottom", getPriceBottom())
            .append("priceCeiling", getPriceCeiling())
            .append("minUnitQty", getMinUnitQty())
            .append("baseMinQty", getBaseMinQty())
            .append("coefficientQty", getCoefficientQty())
            .append("discountTimes", getDiscountTimes())
            .append("discountQty", getDiscountQty())
            .append("extraPrice", getExtraPrice())
            .append("extraTimes", getExtraTimes())
            .append("baseRate", getBaseRate())
            .append("lossRate", getLossRate())
            .append("baseQtyArr", getBaseQtyArr())
            .append("lossQtyArr", getLossQtyArr())
            .toString();
    }

    public String getOpenLongFormula() {
        return openLongFormula;
    }

    public void setOpenLongFormula(String openLongFormula) {
        this.openLongFormula = openLongFormula;
    }

    public String getOpenWidthFormula() {
        return openWidthFormula;
    }

    public void setOpenWidthFormula(String openWidthFormula) {
        this.openWidthFormula = openWidthFormula;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCalculateLog() {
        return calculateLog;
    }

    public void setCalculateLog(String calculateLog) {
        this.calculateLog = calculateLog;
    }
}
