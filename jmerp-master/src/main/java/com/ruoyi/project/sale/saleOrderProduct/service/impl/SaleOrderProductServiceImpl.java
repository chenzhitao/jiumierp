package com.ruoyi.project.sale.saleOrderProduct.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
import com.ruoyi.project.config.configProduct.mapper.ConfigProductMapper;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrder.mapper.SaleOrderMapper;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotation;
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleOrderProduct.mapper.SaleOrderProductMapper;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import com.ruoyi.project.sale.saleOrderProduct.service.ISaleOrderProductService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 销售产品Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleOrderProductServiceImpl implements ISaleOrderProductService 
{
    @Autowired
    private SaleOrderProductMapper saleOrderProductMapper;

    @Autowired
    private ConfigProductMapper configProductMapper;

    @Autowired
    private SaleOrderMapper saleOrderMapper;

    /**
     * 查询销售产品
     * 
     * @param id 销售产品ID
     * @return 销售产品
     */
    @Override
    public SaleOrderProduct selectSaleOrderProductById(Long id)
    {
        return saleOrderProductMapper.selectSaleOrderProductById(id);
    }

    /**
     * 查询销售产品列表
     * 
     * @param saleOrderProduct 销售产品
     * @return 销售产品
     */
    @Override
    public List<SaleOrderProduct> selectSaleOrderProductList(SaleOrderProduct saleOrderProduct)
    {
        if(null!=saleOrderProduct&&!StringUtils.isEmpty(saleOrderProduct.getRzyValue1())&&"produce".equals(saleOrderProduct.getRzyValue1())){
            return saleOrderProductMapper.selectSaleOrderProductList2(saleOrderProduct);
        }else if(null!=saleOrderProduct&&!StringUtils.isEmpty(saleOrderProduct.getRzyValue1())&&"outsource".equals(saleOrderProduct.getRzyValue1())){
            return saleOrderProductMapper.selectSaleOrderProductList2(saleOrderProduct);
        } else{
            return saleOrderProductMapper.selectSaleOrderProductList(saleOrderProduct);
        }

    }

    /**
     * 新增销售产品
     * 
     * @param saleOrderProduct 销售产品
     * @return 结果
     */
    @Override
    public int insertSaleOrderProduct(SaleOrderProduct saleOrderProduct)
    {
        return saleOrderProductMapper.insertSaleOrderProduct(saleOrderProduct);
    }

    /**
     * 修改销售产品
     * 
     * @param saleOrderProduct 销售产品
     * @return 结果
     */
    @Override
    public int updateSaleOrderProduct(SaleOrderProduct saleOrderProduct)
    {
        return saleOrderProductMapper.updateSaleOrderProduct(saleOrderProduct);
    }

    /**
     * 删除销售产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleOrderProductByIds(String ids)
    {
        return saleOrderProductMapper.deleteSaleOrderProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除销售产品信息
     * 
     * @param id 销售产品ID
     * @return 结果
     */
    @Override
    public int deleteSaleOrderProductById(Long id)
    {
        return saleOrderProductMapper.deleteSaleOrderProductById(id);
    }


    /**
     * 新建产品
     * @Author 方舟
     * @Date 2021/4/23 10:14:36
     **/
    @Override
    public SaleOrderProduct createProduct(SaleOrderProduct saleOrderProduct){
        //准备产品数据保存
        ConfigProduct configProduct = new ConfigProduct();
        BeanUtils.copyProperties(saleOrderProduct, configProduct);
        configProduct.setIsPublic("N");
        configProduct.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configProduct.setCreateTime(DateUtils.getNowDate());
        configProduct.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configProduct.setUpdateTime(DateUtils.getNowDate());
        configProductMapper.insertConfigProduct(configProduct);
        //保存完了,获取ID
        Long productId = configProduct.getId();
        configProduct = configProductMapper.selectConfigProductById(productId);
        //剩下的按选产品的进行
        SaleOrder saleOrder = saleOrderMapper.selectSaleOrderById(saleOrderProduct.getSaleOrderId());
        saveSaleOrderProductByProduct(configProduct,saleOrder,saleOrderProduct);
        return saleOrderProduct;
    }

    /**
     * 已有Product创建报价产品
     * @Author 方舟
     * @Date 2021/4/23 10:26:50
     **/
    private void saveSaleOrderProductByProduct(ConfigProduct configProduct,SaleOrder saleOrder,SaleOrderProduct saleOrderProduct){
        //添加报价产品
        saleOrderProduct.setCustomerId(saleOrder.getCustomerId());
        saleOrderProduct.setSaleOrderId(saleOrder.getId());
        saleOrderProduct.setProductId(configProduct.getId());
        insertSaleOrderProduct(saleOrderProduct);
    }
}
