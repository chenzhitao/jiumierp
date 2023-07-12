package com.ruoyi.project.sale.saleReturn.mapper;

import java.util.List;
import com.ruoyi.project.sale.saleReturn.domain.SaleReturn;
import com.ruoyi.project.sale.saleReturn.domain.SaleReturnExport;

/**
 * 退货单Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface SaleReturnMapper 
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
     * 删除退货单
     * 
     * @param id 退货单ID
     * @return 结果
     */
    public int deleteSaleReturnById(Long id);
    public int deleteSaleReturnProductById(Long id);

    /**
     * 批量删除退货单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleReturnByIds(String[] ids);
}
