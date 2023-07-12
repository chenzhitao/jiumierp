package com.ruoyi.project.sale.saleOrder.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrderExport;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 销售订单Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface ISaleOrderService 
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
     * 批量删除销售订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleOrderByIds(String ids);

    /**
     * 删除销售订单信息
     * 
     * @param id 销售订单ID
     * @return 结果
     */
    public int deleteSaleOrderById(Long id);

    /**
     * 选择客户创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    public SaleOrder createSaleOrder(SaleOrder saleOrder);

    /**
     * 导入数据
     *
     * @param saleOrderProductList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importSaleOrder(List<SaleOrderProduct> saleOrderProductList, Boolean isUpdateSupport);

    /**
     * 汇总金额
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    public SaleOrder countAmount(SaleOrder saleOrder);

    /**
     * 复制
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    public SaleOrder copySaleOrder(SaleOrder saleOrder);
}
