package com.ruoyi.project.sale.saleDelivery.service;

import java.util.List;

import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDelivery;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDeliveryExport;

/**
 * 送货单Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface ISaleDeliveryService 
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
     * 批量删除送货单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleDeliveryByIds(String ids);

    /**
     * 删除送货单信息
     * 
     * @param id 送货单ID
     * @return 结果
     */
    public int deleteSaleDeliveryById(Long id);


    /**
     * 创建外发到货
     */
    public SaleDelivery createSaleDelivery(SaleDelivery saleDelivery);

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    public SaleDelivery countAmount(SaleDelivery saleDelivery);
}
