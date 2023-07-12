package com.ruoyi.project.sale.saleOrderDelivery.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleOrderDelivery.mapper.SaleOrderDeliveryMapper;
import com.ruoyi.project.sale.saleOrderDelivery.domain.SaleOrderDelivery;
import com.ruoyi.project.sale.saleOrderDelivery.service.ISaleOrderDeliveryService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 送货排程Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleOrderDeliveryServiceImpl implements ISaleOrderDeliveryService 
{
    @Autowired
    private SaleOrderDeliveryMapper saleOrderDeliveryMapper;

    /**
     * 查询送货排程
     * 
     * @param id 送货排程ID
     * @return 送货排程
     */
    @Override
    public SaleOrderDelivery selectSaleOrderDeliveryById(Long id)
    {
        return saleOrderDeliveryMapper.selectSaleOrderDeliveryById(id);
    }

    /**
     * 查询送货排程列表
     * 
     * @param saleOrderDelivery 送货排程
     * @return 送货排程
     */
    @Override
    public List<SaleOrderDelivery> selectSaleOrderDeliveryList(SaleOrderDelivery saleOrderDelivery)
    {
        return saleOrderDeliveryMapper.selectSaleOrderDeliveryList(saleOrderDelivery);
    }

    /**
     * 新增送货排程
     * 
     * @param saleOrderDelivery 送货排程
     * @return 结果
     */
    @Override
    public int insertSaleOrderDelivery(SaleOrderDelivery saleOrderDelivery)
    {
        return saleOrderDeliveryMapper.insertSaleOrderDelivery(saleOrderDelivery);
    }

    /**
     * 修改送货排程
     * 
     * @param saleOrderDelivery 送货排程
     * @return 结果
     */
    @Override
    public int updateSaleOrderDelivery(SaleOrderDelivery saleOrderDelivery)
    {
        return saleOrderDeliveryMapper.updateSaleOrderDelivery(saleOrderDelivery);
    }

    /**
     * 删除送货排程对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleOrderDeliveryByIds(String ids)
    {
        return saleOrderDeliveryMapper.deleteSaleOrderDeliveryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除送货排程信息
     * 
     * @param id 送货排程ID
     * @return 结果
     */
    @Override
    public int deleteSaleOrderDeliveryById(Long id)
    {
        return saleOrderDeliveryMapper.deleteSaleOrderDeliveryById(id);
    }

    /**
     * 已送
     * @Author 方舟
     * @Date 2021/4/27 14:19:20
     **/
    @Override
    public Integer selectSendQty(SaleOrderDelivery saleOrderDelivery){
        return saleOrderDeliveryMapper.selectSendQty(saleOrderDelivery);
    }
}
