package com.ruoyi.project.sale.saleChecking.service;

import java.util.List;

import com.ruoyi.project.sale.saleChecking.domain.SaleChecking;

/**
 * 销售对账Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface ISaleCheckingService 
{
    /**
     * 查询销售对账
     * 
     * @param id 销售对账ID
     * @return 销售对账
     */
    public SaleChecking selectSaleCheckingById(Long id);

    /**
     * 查询销售对账列表
     * 
     * @param saleChecking 销售对账
     * @return 销售对账集合
     */
    public List<SaleChecking> selectSaleCheckingList(SaleChecking saleChecking);

    /**
     * 新增销售对账
     * 
     * @param saleChecking 销售对账
     * @return 结果
     */
    public int insertSaleChecking(SaleChecking saleChecking);

    /**
     * 修改销售对账
     * 
     * @param saleChecking 销售对账
     * @return 结果
     */
    public int updateSaleChecking(SaleChecking saleChecking);

    /**
     * 批量删除销售对账
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleCheckingByIds(String ids);

    /**
     * 删除销售对账信息
     * 
     * @param id 销售对账ID
     * @return 结果
     */
    public int deleteSaleCheckingById(Long id);

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    public SaleChecking createSaleChecking(SaleChecking saleChecking);

    /**
     * 计算金额
     * @Author 方舟
     * @Date 2021/5/27 14:45:12
     **/
    public SaleChecking calculateAmount(SaleChecking saleChecking);
}
