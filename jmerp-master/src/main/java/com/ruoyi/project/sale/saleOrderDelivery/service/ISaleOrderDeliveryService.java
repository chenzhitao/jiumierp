package com.ruoyi.project.sale.saleOrderDelivery.service;

import java.util.List;
import com.ruoyi.project.sale.saleOrderDelivery.domain.SaleOrderDelivery;

/**
 * 送货排程Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface ISaleOrderDeliveryService 
{
    /**
     * 查询送货排程
     * 
     * @param id 送货排程ID
     * @return 送货排程
     */
    public SaleOrderDelivery selectSaleOrderDeliveryById(Long id);

    /**
     * 查询送货排程列表
     * 
     * @param saleOrderDelivery 送货排程
     * @return 送货排程集合
     */
    public List<SaleOrderDelivery> selectSaleOrderDeliveryList(SaleOrderDelivery saleOrderDelivery);

    /**
     * 新增送货排程
     * 
     * @param saleOrderDelivery 送货排程
     * @return 结果
     */
    public int insertSaleOrderDelivery(SaleOrderDelivery saleOrderDelivery);

    /**
     * 修改送货排程
     * 
     * @param saleOrderDelivery 送货排程
     * @return 结果
     */
    public int updateSaleOrderDelivery(SaleOrderDelivery saleOrderDelivery);

    /**
     * 批量删除送货排程
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleOrderDeliveryByIds(String ids);

    /**
     * 删除送货排程信息
     * 
     * @param id 送货排程ID
     * @return 结果
     */
    public int deleteSaleOrderDeliveryById(Long id);

    /**
     * 已送
     * @Author 方舟
     * @Date 2021/4/27 14:19:20
     **/
    public Integer selectSendQty(SaleOrderDelivery saleOrderDelivery);
}
