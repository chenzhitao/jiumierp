package com.ruoyi.project.sale.saleQuotationMultDetail.service;

import java.util.List;

import com.ruoyi.project.sale.saleQuotationMaterials.domain.SaleQuotationMaterials;
import com.ruoyi.project.sale.saleQuotationMult.domain.SaleQuotationMult;
import com.ruoyi.project.sale.saleQuotationMultDetail.domain.SaleQuotationMultDetail;
import com.ruoyi.project.sale.saleQuotationProcess.domain.SaleQuotationProcess;
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;

/**
 * 多数量报价明细Service接口
 * 
 * @author fangzhou
 * @date 2021-04-21
 */
public interface ISaleQuotationMultDetailService 
{
    /**
     * 查询多数量报价明细
     * 
     * @param id 多数量报价明细ID
     * @return 多数量报价明细
     */
    public SaleQuotationMultDetail selectSaleQuotationMultDetailById(Long id);

    /**
     * 查询多数量报价明细列表
     * 
     * @param saleQuotationMultDetail 多数量报价明细
     * @return 多数量报价明细集合
     */
    public List<SaleQuotationMultDetail> selectSaleQuotationMultDetailList(SaleQuotationMultDetail saleQuotationMultDetail);
    public List<SaleQuotationMultDetail> selectSaleQuotationMultDetail2List(SaleQuotationMultDetail saleQuotationMultDetail);

    /**
     * 新增多数量报价明细
     * 
     * @param saleQuotationMultDetail 多数量报价明细
     * @return 结果
     */
    public int insertSaleQuotationMultDetail(SaleQuotationMultDetail saleQuotationMultDetail);

    /**
     * 修改多数量报价明细
     * 
     * @param saleQuotationMultDetail 多数量报价明细
     * @return 结果
     */
    public int updateSaleQuotationMultDetail(SaleQuotationMultDetail saleQuotationMultDetail);

    /**
     * 批量删除多数量报价明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleQuotationMultDetailByIds(String ids);

    /**
     * 删除多数量报价明细信息
     * 
     * @param id 多数量报价明细ID
     * @return 结果
     */
    public int deleteSaleQuotationMultDetailById(Long id);

    /**
     * 删除投入产出数
     * @Author 方舟
     * @Date 2021/4/21 15:46:18
     **/
    public int deleteSaleQuotationMultDetailBySaleQuotationId(Long id);

    /**
     * 计算工序投入产出数
     * @Author 方舟
     * @Date 2021/4/21 16:02:26
    **/
    public void createProcessDetail(SaleQuotationMult saleQuotationMult , SaleQuotationProduct saleQuotationProduct, List<SaleQuotationProcess> saleQuotationProcessList);

    /**
     * 计算材料投入产出数
     * @Author 方舟
     * @Date 2021/4/21 16:02:42
    **/
    public void createMaterialsDetail(SaleQuotationMult saleQuotationMult, SaleQuotationProduct saleQuotationProduct, List<SaleQuotationMaterials> saleQuotationMaterialsList);
}
