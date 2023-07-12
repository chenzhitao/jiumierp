package com.ruoyi.project.sale.saleQuotationMult.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleQuotationMult.mapper.SaleQuotationMultMapper;
import com.ruoyi.project.sale.saleQuotationMult.domain.SaleQuotationMult;
import com.ruoyi.project.sale.saleQuotationMult.service.ISaleQuotationMultService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 多数量报价Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-21
 */
@Service
public class SaleQuotationMultServiceImpl implements ISaleQuotationMultService 
{
    @Autowired
    private SaleQuotationMultMapper saleQuotationMultMapper;

    /**
     * 查询多数量报价
     * 
     * @param id 多数量报价ID
     * @return 多数量报价
     */
    @Override
    public SaleQuotationMult selectSaleQuotationMultById(Long id)
    {
        return saleQuotationMultMapper.selectSaleQuotationMultById(id);
    }

    /**
     * 查询多数量报价列表
     * 
     * @param saleQuotationMult 多数量报价
     * @return 多数量报价
     */
    @Override
    public List<SaleQuotationMult> selectSaleQuotationMultList(SaleQuotationMult saleQuotationMult)
    {
        return saleQuotationMultMapper.selectSaleQuotationMultList(saleQuotationMult);
    }

    /**
     * 新增多数量报价
     * 
     * @param saleQuotationMult 多数量报价
     * @return 结果
     */
    @Override
    public int insertSaleQuotationMult(SaleQuotationMult saleQuotationMult)
    {
        return saleQuotationMultMapper.insertSaleQuotationMult(saleQuotationMult);
    }

    /**
     * 修改多数量报价
     * 
     * @param saleQuotationMult 多数量报价
     * @return 结果
     */
    @Override
    public int updateSaleQuotationMult(SaleQuotationMult saleQuotationMult)
    {
        return saleQuotationMultMapper.updateSaleQuotationMult(saleQuotationMult);
    }

    /**
     * 删除多数量报价对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationMultByIds(String ids)
    {
        return saleQuotationMultMapper.deleteSaleQuotationMultByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除多数量报价信息
     * 
     * @param id 多数量报价ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationMultById(Long id)
    {
        return saleQuotationMultMapper.deleteSaleQuotationMultById(id);
    }

    /**
     * 保存多数量
     * @Author 方舟
     * @Date 2021/4/21 14:28:24
     **/
    @Override
    public void saveMultByString(Long saleQuotationId,String content){
        String[] arr = content.split(",");
        for (int i=0;i<arr.length;i++){
            Long id = Long.parseLong(arr[i].split("_")[0]);
            Integer qty = Integer.parseInt(arr[i].split("_")[1]);
            BigDecimal rate = new BigDecimal(arr[i].split("_")[2]);
            SaleQuotationMult paramVO = new SaleQuotationMult();
            paramVO.setId(id);
            paramVO.setQty(qty);
            paramVO.setTaxRate(rate);
            updateSaleQuotationMult(paramVO);
        }
    }
}
