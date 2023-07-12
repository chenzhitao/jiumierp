package com.ruoyi.project.sale.saleOrderMaterials.mapper;

import java.util.List;
import com.ruoyi.project.sale.saleOrderMaterials.domain.SaleOrderMaterials;

/**
 * 客户带料Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface SaleOrderMaterialsMapper 
{
    /**
     * 查询客户带料
     * 
     * @param id 客户带料ID
     * @return 客户带料
     */
    public SaleOrderMaterials selectSaleOrderMaterialsById(Long id);

    /**
     * 查询客户带料列表
     * 
     * @param saleOrderMaterials 客户带料
     * @return 客户带料集合
     */
    public List<SaleOrderMaterials> selectSaleOrderMaterialsList(SaleOrderMaterials saleOrderMaterials);

    /**
     * 新增客户带料
     * 
     * @param saleOrderMaterials 客户带料
     * @return 结果
     */
    public int insertSaleOrderMaterials(SaleOrderMaterials saleOrderMaterials);

    /**
     * 修改客户带料
     * 
     * @param saleOrderMaterials 客户带料
     * @return 结果
     */
    public int updateSaleOrderMaterials(SaleOrderMaterials saleOrderMaterials);

    /**
     * 删除客户带料
     * 
     * @param id 客户带料ID
     * @return 结果
     */
    public int deleteSaleOrderMaterialsById(Long id);

    /**
     * 批量删除客户带料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleOrderMaterialsByIds(String[] ids);
}
