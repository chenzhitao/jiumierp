package com.ruoyi.project.config.configProductionTemplateMaterials.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 生产工艺卡材料对象 config_production_template_materials
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigProductionTemplateMaterials extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 报价工艺卡 */
    @Excel(name = "报价工艺卡")
    private String productionTemplateName;
    private Long productionTemplateId;

    /** 材料 */
    @Excel(name = "材料")
    private String materialsName;
    private Long materialsId;

    /** 关联工序 */
    @Excel(name = "关联工序")
    private String processName;
    private Long processId;

    /** 刀模 */
    @Excel(name = "刀模")
    private String cutterDieName;
    private Long cutterDieId;

    /** 板材 */
    @Excel(name = "板材")
    private String boardName;
    private Long boardId;

    /** 取工序数量 */
    @Excel(name = "取工序数量", dictType = "sys_yes_no")
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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProductionTemplateId(Long productionTemplateId)
    {
        this.productionTemplateId = productionTemplateId;
    }

    public Long getProductionTemplateId()
    {
        return productionTemplateId;
    }
    public void setMaterialsId(Long materialsId)
    {
        this.materialsId = materialsId;
    }

    public Long getMaterialsId()
    {
        return materialsId;
    }
    public void setProcessId(Long processId)
    {
        this.processId = processId;
    }

    public Long getProcessId()
    {
        return processId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("productionTemplateId", getProductionTemplateId())
            .append("materialsId", getMaterialsId())
            .append("processId", getProcessId())
            .append("cutterDieId", getCutterDieId())
            .append("boardId", getBoardId())
            .append("isGetProcessQty", getIsGetProcessQty())
            .append("baseRate", getBaseRate())
            .append("lossRate", getLossRate())
            .append("lossQty", getLossQty())
            .toString();
    }

    public String getProductionTemplateName() {
        return productionTemplateName;
    }

    public void setProductionTemplateName(String productionTemplateName) {
        this.productionTemplateName = productionTemplateName;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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
}
