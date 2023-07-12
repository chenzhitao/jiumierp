package com.ruoyi.project.sale.saleOrder.mapper;

import java.util.List;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrderExport;

/**
 * 销售订单Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface SaleOrderMapper 
{
    /**
     * 查询销售订单
     * 
     * @param id 销售订单ID
     * @return 销售订单
     */
    public SaleOrder selectSaleOrderById(Long id);

    /**
     * 查询销售订单列表
     * 
     * @param saleOrder 销售订单
     * @return 销售订单集合
     */
    public List<SaleOrder> selectSaleOrderList(SaleOrder saleOrder);
    public List<SaleOrderExport> selectSaleOrderExportList(SaleOrder saleOrder);

    /**
     * 新增销售订单
     * 
     * @param saleOrder 销售订单
     * @return 结果
     */
    public int insertSaleOrder(SaleOrder saleOrder);

    /**
     * 修改销售订单
     * 
     * @param saleOrder 销售订单
     * @return 结果
     */
    public int updateSaleOrder(SaleOrder saleOrder);

    /**
     * 删除销售订单
     * 
     * @param id 销售订单ID
     * @return 结果
     */
    public int deleteSaleOrderById(Long id);
    public int deleteProduceOrderDeliveryById(Long id);
    public int deleteProduceOrderMaterialsById(Long id);

    /**
     * 批量删除销售订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleOrderByIds(String[] ids);
}
