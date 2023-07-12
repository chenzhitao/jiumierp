package com.ruoyi.project.sale.saleDelivery.mapper;

import java.util.List;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDeliveryExport;

/**
 * 送货单Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface SaleDeliveryMapper 
{
    /**
     * 查询送货单
     * 
     * @param id 送货单ID
     * @return 送货单
     */
    public SaleDelivery selectSaleDeliveryById(Long id);

    /**
     * 查询送货单列表
     * 
     * @param saleDelivery 送货单
     * @return 送货单集合
     */
    public List<SaleDelivery> selectSaleDeliveryList(SaleDelivery saleDelivery);
    public List<SaleDeliveryExport> selectSaleDeliveryExportList(SaleDelivery saleDelivery);

    /**
     * 新增送货单
     * 
     * @param saleDelivery 送货单
     * @return 结果
     */
    public int insertSaleDelivery(SaleDelivery saleDelivery);

    /**
     * 修改送货单
     * 
     * @param saleDelivery 送货单
     * @return 结果
     */
    public int updateSaleDelivery(SaleDelivery saleDelivery);

    /**
     * 删除送货单
     * 
     * @param id 送货单ID
     * @return 结果
     */
    public int deleteSaleDeliveryById(Long id);
    public int deleteSaleDeliveryProductById(Long id);

    /**
     * 批量删除送货单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleDeliveryByIds(String[] ids);
}
