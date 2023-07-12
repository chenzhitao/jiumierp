package com.ruoyi.project.sale.saleOrderMaterials.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleOrderMaterials.mapper.SaleOrderMaterialsMapper;
import com.ruoyi.project.sale.saleOrderMaterials.domain.SaleOrderMaterials;
import com.ruoyi.project.sale.saleOrderMaterials.service.ISaleOrderMaterialsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 客户带料Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleOrderMaterialsServiceImpl implements ISaleOrderMaterialsService 
{
    @Autowired
    private SaleOrderMaterialsMapper saleOrderMaterialsMapper;

    /**
     * 查询客户带料
     * 
     * @param id 客户带料ID
     * @return 客户带料
     */
    @Override
    public SaleOrderMaterials selectSaleOrderMaterialsById(Long id)
    {
        return saleOrderMaterialsMapper.selectSaleOrderMaterialsById(id);
    }

    /**
     * 查询客户带料列表
     * 
     * @param saleOrderMaterials 客户带料
     * @return 客户带料
     */
    @Override
    public List<SaleOrderMaterials> selectSaleOrderMaterialsList(SaleOrderMaterials saleOrderMaterials)
    {
        return saleOrderMaterialsMapper.selectSaleOrderMaterialsList(saleOrderMaterials);
    }

    /**
     * 新增客户带料
     * 
     * @param saleOrderMaterials 客户带料
     * @return 结果
     */
    @Override
    public int insertSaleOrderMaterials(SaleOrderMaterials saleOrderMaterials)
    {
        return saleOrderMaterialsMapper.insertSaleOrderMaterials(saleOrderMaterials);
    }

    /**
     * 修改客户带料
     * 
     * @param saleOrderMaterials 客户带料
     * @return 结果
     */
    @Override
    public int updateSaleOrderMaterials(SaleOrderMaterials saleOrderMaterials)
    {
        return saleOrderMaterialsMapper.updateSaleOrderMaterials(saleOrderMaterials);
    }

    /**
     * 删除客户带料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleOrderMaterialsByIds(String ids)
    {
        return saleOrderMaterialsMapper.deleteSaleOrderMaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户带料信息
     * 
     * @param id 客户带料ID
     * @return 结果
     */
    @Override
    public int deleteSaleOrderMaterialsById(Long id)
    {
        return saleOrderMaterialsMapper.deleteSaleOrderMaterialsById(id);
    }
}
