package com.ruoyi.project.config.configFormula.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 公式配置对象 config_formula
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigFormulaSimulation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String calculateLog;

    /** ID */
    private Long id;
    private Long configFormulaId;

    private Integer productLong;
    private Integer productWidth;
    private Integer productHeight;
    private Integer processQty;
    private Integer resultQty;

    private BigDecimal processPrice;
    private Integer openLong;
    private Integer openWidth;
    private Double area;

    private String formulaType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConfigFormulaId() {
        return configFormulaId;
    }

    public void setConfigFormulaId(Long configFormulaId) {
        this.configFormulaId = configFormulaId;
    }

    public Integer getProductLong() {
        return productLong;
    }

    public void setProductLong(Integer productLong) {
        this.productLong = productLong;
    }

    public Integer getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(Integer productWidth) {
        this.productWidth = productWidth;
    }

    public Integer getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(Integer productHeight) {
        this.productHeight = productHeight;
    }

    public Integer getProcessQty() {
        return processQty;
    }

    public void setProcessQty(Integer processQty) {
        this.processQty = processQty;
    }

    public BigDecimal getProcessPrice() {
        return processPrice;
    }

    public void setProcessPrice(BigDecimal processPrice) {
        this.processPrice = processPrice;
    }

    public Integer getOpenLong() {
        return openLong;
    }

    public void setOpenLong(Integer openLong) {
        this.openLong = openLong;
    }

    public Integer getOpenWidth() {
        return openWidth;
    }

    public void setOpenWidth(Integer openWidth) {
        this.openWidth = openWidth;
    }

    public Double getArea() {
        return area;
    }

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getCalculateLog() {
        return calculateLog;
    }

    public void setCalculateLog(String calculateLog) {
        this.calculateLog = calculateLog;
    }

    public Integer getResultQty() {
        return resultQty;
    }

    public void setResultQty(Integer resultQty) {
        this.resultQty = resultQty;
    }
}
