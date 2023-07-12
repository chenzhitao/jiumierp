package com.ruoyi.project.sale.saleQuotationMaterials.service;

import java.util.List;
import com.ruoyi.project.sale.saleQuotationMaterials.domain.SaleQuotationMaterials;

/**
 * 报价材料Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface ISaleQuotationMaterialsService 
{
    /**
     * 查询报价材料
     * 
     * @param id 报价材料ID
     * @return 报价材料
     */
    public SaleQuotationMaterials selectSaleQuotationMaterialsById(Long id);

    /**
     * 查询报价材料列表
     * 
     * @param saleQuotationMaterials 报价材料
     * @return 报价材料集合
     */
    public List<SaleQuotationMaterials> selectSaleQuotationMaterialsList(SaleQuotationMaterials saleQuotationMaterials);

    /**
     * 新增报价材料
     * 
     * @param saleQuotationMaterials 报价材料
     * @return 结果
     */
    public int insertSaleQuotationMaterials(SaleQuotationMaterials saleQuotationMaterials);

    /**
     * 修改报价材料
     * 
     * @param saleQuotationMaterials 报价材料
     * @return 结果
     */
    public int updateSaleQuotationMaterials(SaleQuotationMaterials saleQuotationMaterials);

    /**
     * 批量删除报价材料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleQuotationMaterialsByIds(String ids);

    /**
     * 删除报价材料信息
     * 
     * @param id 报价材料ID
     * @return 结果
     */
    public int deleteSaleQuotationMaterialsById(Long id);
}
