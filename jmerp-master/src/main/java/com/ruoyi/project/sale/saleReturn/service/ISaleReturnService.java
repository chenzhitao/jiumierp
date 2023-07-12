package com.ruoyi.project.sale.saleReturn.service;

import java.util.List;

import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleReturn.domain.SaleReturn;
import com.ruoyi.project.sale.saleReturn.domain.SaleReturnExport;

/**
 * 退货单Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface ISaleReturnService 
{
    /**
     * 查询退货单
     * 
     * @param id 退货单ID
     * @return 退货单
     */
    public SaleReturn selectSaleReturnById(Long id);

    /**
     * 查询退货单列表
     * 
     * @param saleReturn 退货单
     * @return 退货单集合
     */
    public List<SaleReturn> selectSaleReturnList(SaleReturn saleReturn);
    public List<SaleReturnExport> selectSaleReturnExportList(SaleReturn saleReturn);

    /**
     * 新增退货单
     * 
     * @param saleReturn 退货单
     * @return 结果
     */
    public int insertSaleReturn(SaleReturn saleReturn);

    /**
     * 修改退货单
     * 
     * @param saleReturn 退货单
     * @return 结果
     */
    public int updateSaleReturn(SaleReturn saleReturn);

    /**
     * 批量删除退货单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleReturnByIds(String ids);

    /**
     * 删除退货单信息
     * 
     * @param id 退货单ID
     * @return 结果
     */
    public int deleteSaleReturnById(Long id);

    /**
     * 创建外发到货
     */
    public SaleReturn createSaleReturn(SaleReturn saleReturn);

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    public SaleReturn countAmount(SaleReturn saleReturn);
}
