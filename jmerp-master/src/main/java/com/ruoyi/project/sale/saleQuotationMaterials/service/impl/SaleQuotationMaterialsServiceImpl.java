package com.ruoyi.project.sale.saleQuotationMaterials.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleQuotationMaterials.mapper.SaleQuotationMaterialsMapper;
import com.ruoyi.project.sale.saleQuotationMaterials.domain.SaleQuotationMaterials;
import com.ruoyi.project.sale.saleQuotationMaterials.service.ISaleQuotationMaterialsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 报价材料Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleQuotationMaterialsServiceImpl implements ISaleQuotationMaterialsService 
{
    @Autowired
    private SaleQuotationMaterialsMapper saleQuotationMaterialsMapper;

    /**
     * 查询报价材料
     * 
     * @param id 报价材料ID
     * @return 报价材料
     */
    @Override
    public SaleQuotationMaterials selectSaleQuotationMaterialsById(Long id)
    {
        return saleQuotationMaterialsMapper.selectSaleQuotationMaterialsById(id);
    }

    /**
     * 查询报价材料列表
     * 
     * @param saleQuotationMaterials 报价材料
     * @return 报价材料
     */
    @Override
    public List<SaleQuotationMaterials> selectSaleQuotationMaterialsList(SaleQuotationMaterials saleQuotationMaterials)
    {
        return saleQuotationMaterialsMapper.selectSaleQuotationMaterialsList(saleQuotationMaterials);
    }

    /**
     * 新增报价材料
     * 
     * @param saleQuotationMaterials 报价材料
     * @return 结果
     */
    @Override
    public int insertSaleQuotationMaterials(SaleQuotationMaterials saleQuotationMaterials)
    {
        return saleQuotationMaterialsMapper.insertSaleQuotationMaterials(saleQuotationMaterials);
    }

    /**
     * 修改报价材料
     * 
     * @param saleQuotationMaterials 报价材料
     * @return 结果
     */
    @Override
    public int updateSaleQuotationMaterials(SaleQuotationMaterials saleQuotationMaterials)
    {
        return saleQuotationMaterialsMapper.updateSaleQuotationMaterials(saleQuotationMaterials);
    }

    /**
     * 删除报价材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationMaterialsByIds(String ids)
    {
        return saleQuotationMaterialsMapper.deleteSaleQuotationMaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除报价材料信息
     * 
     * @param id 报价材料ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationMaterialsById(Long id)
    {
        return saleQuotationMaterialsMapper.deleteSaleQuotationMaterialsById(id);
    }
}
