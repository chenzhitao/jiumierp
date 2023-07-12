package com.ruoyi.project.sale.saleQuotationMult.service;

import java.util.List;
import com.ruoyi.project.sale.saleQuotationMult.domain.SaleQuotationMult;

/**
 * 多数量报价Service接口
 * 
 * @author fangzhou
 * @date 2021-04-21
 */
public interface ISaleQuotationMultService 
{
    /**
     * 查询多数量报价
     * 
     * @param id 多数量报价ID
     * @return 多数量报价
     */
    public SaleQuotationMult selectSaleQuotationMultById(Long id);

    /**
     * 查询多数量报价列表
     * 
     * @param saleQuotationMult 多数量报价
     * @return 多数量报价集合
     */
    public List<SaleQuotationMult> selectSaleQuotationMultList(SaleQuotationMult saleQuotationMult);

    /**
     * 新增多数量报价
     * 
     * @param saleQuotationMult 多数量报价
     * @return 结果
     */
    public int insertSaleQuotationMult(SaleQuotationMult saleQuotationMult);

    /**
     * 修改多数量报价
     * 
     * @param saleQuotationMult 多数量报价
     * @return 结果
     */
    public int updateSaleQuotationMult(SaleQuotationMult saleQuotationMult);

    /**
     * 批量删除多数量报价
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleQuotationMultByIds(String ids);

    /**
     * 删除多数量报价信息
     * 
     * @param id 多数量报价ID
     * @return 结果
     */
    public int deleteSaleQuotationMultById(Long id);

    /**
     * 保存多数量
     * @Author 方舟
     * @Date 2021/4/21 14:28:24
    **/
    public void saveMultByString(Long saleQuotationId,String content);

}
