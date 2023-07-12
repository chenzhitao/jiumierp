package com.ruoyi.project.produce.produceReport.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 产量上报对象 produce_report
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public class ProduceReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 排程工序 */
    @Excel(name = "排程工序")
    private Long produceScheduleProcessId;

    /** 生产排程 */
    @Excel(name = "生产排程")
    private Long produceScheduleId;

    /** 施工工序 */
    @Excel(name = "施工工序")
    private Long produceOrderProcessId;

    /** 施工产品 */
    @Excel(name = "施工产品")
    private Long produceOrderProductId;

    /** 施工单 */
    @Excel(name = "施工单")
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

    /** 工序 */
    @Excel(name = "工序")
    private Long processId;

    /** 报产数量 */
    @Excel(name = "报产数量")
    private Integer qty;

    /** 报产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportDate;

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
    public void setProduceScheduleProcessId(Long produceScheduleProcessId)
    {
        this.produceScheduleProcessId = produceScheduleProcessId;
    }

    public Long getProduceScheduleProcessId()
    {
        return produceScheduleProcessId;
    }
    public void setProduceScheduleId(Long produceScheduleId)
    {
        this.produceScheduleId = produceScheduleId;
    }

    public Long getProduceScheduleId()
    {
        return produceScheduleId;
    }
    public void setProduceOrderProcessId(Long produceOrderProcessId)
    {
        this.produceOrderProcessId = produceOrderProcessId;
    }

    public Long getProduceOrderProcessId()
    {
        return produceOrderProcessId;
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
    public void setQty(Integer qty)
    {
        this.qty = qty;
    }

    public Integer getQty()
    {
        return qty;
    }
    public void setReportDate(Date reportDate)
    {
        this.reportDate = reportDate;
    }

    public Date getReportDate()
    {
        return reportDate;
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
            .append("produceScheduleProcessId", getProduceScheduleProcessId())
            .append("produceScheduleId", getProduceScheduleId())
            .append("produceOrderProcessId", getProduceOrderProcessId())
            .append("produceOrderProductId", getProduceOrderProductId())
            .append("produceOrderId", getProduceOrderId())
            .append("saleOrderProductId", getSaleOrderProductId())
            .append("saleOrderId", getSaleOrderId())
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("qty", getQty())
            .append("reportDate", getReportDate())
            .toString();
    }
}
