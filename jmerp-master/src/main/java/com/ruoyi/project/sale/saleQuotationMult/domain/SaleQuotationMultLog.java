package com.ruoyi.project.sale.saleQuotationMult.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 多数量报价对象 sale_quotation_mult
 * 
 * @author fangzhou
 * @date 2021-04-21
 */
public class SaleQuotationMultLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private Long saleQuotationMultId;
    /**
     * 投入产出_inout,工序计算_process,材料计算_materials,其他_other
     * navy-bg(绿),blue-bg(蓝),lazur-bg(亮绿info),yellow-bg(黄)
     * fa-exchange,fa-sitemap,fa-sticky-note-o,fa-user-o
     * @Author 方舟
     * @Date 2021/4/23 15:33:11
    **/
    private String logType;//类型,(投入产出_inout,工序计算_process,材料计算_materials,其他_other)颜色:navy-bg(绿),blue-bg(蓝),lazur-bg(亮绿info),yellow-bg(黄)
    private String logSubject;//主题:测试产品A-模切
    private String logContent;//内容:单价是1,数量是20,所以计算得到金额是20
    private Integer logQty;//数量:3000
    BigDecimal logAmount;//金额:20.21

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaleQuotationMultId() {
        return saleQuotationMultId;
    }

    public void setSaleQuotationMultId(Long saleQuotationMultId) {
        this.saleQuotationMultId = saleQuotationMultId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogSubject() {
        return logSubject;
    }

    public void setLogSubject(String logSubject) {
        this.logSubject = logSubject;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public Integer getLogQty() {
        return logQty;
    }

    public void setLogQty(Integer logQty) {
        this.logQty = logQty;
    }

    public BigDecimal getLogAmount() {
        return logAmount;
    }

    public void setLogAmount(BigDecimal logAmount) {
        this.logAmount = logAmount;
    }
}
