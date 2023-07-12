package com.ruoyi.project.sale.saleQuotationMultDetail.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.FormulaUtils;
import com.ruoyi.project.config.configFormula.domain.ConfigFormula;
import com.ruoyi.project.config.configFormula.mapper.ConfigFormulaMapper;
import com.ruoyi.project.sale.saleQuotationMaterials.domain.SaleQuotationMaterials;
import com.ruoyi.project.sale.saleQuotationMult.domain.SaleQuotationMult;
import com.ruoyi.project.sale.saleQuotationProcess.domain.SaleQuotationProcess;
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleQuotationMultDetail.mapper.SaleQuotationMultDetailMapper;
import com.ruoyi.project.sale.saleQuotationMultDetail.domain.SaleQuotationMultDetail;
import com.ruoyi.project.sale.saleQuotationMultDetail.service.ISaleQuotationMultDetailService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 多数量报价明细Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-21
 */
@Service
public class SaleQuotationMultDetailServiceImpl implements ISaleQuotationMultDetailService 
{
    @Autowired
    private SaleQuotationMultDetailMapper saleQuotationMultDetailMapper;
    @Autowired
    private ConfigFormulaMapper configFormulaMapper;

    /**
     * 查询多数量报价明细
     * 
     * @param id 多数量报价明细ID
     * @return 多数量报价明细
     */
    @Override
    public SaleQuotationMultDetail selectSaleQuotationMultDetailById(Long id)
    {
        return saleQuotationMultDetailMapper.selectSaleQuotationMultDetailById(id);
    }

    /**
     * 查询多数量报价明细列表
     * 
     * @param saleQuotationMultDetail 多数量报价明细
     * @return 多数量报价明细
     */
    @Override
    public List<SaleQuotationMultDetail> selectSaleQuotationMultDetailList(SaleQuotationMultDetail saleQuotationMultDetail)
    {
        return saleQuotationMultDetailMapper.selectSaleQuotationMultDetailList(saleQuotationMultDetail);
    }
    @Override
    public List<SaleQuotationMultDetail> selectSaleQuotationMultDetail2List(SaleQuotationMultDetail saleQuotationMultDetail)
    {
        return saleQuotationMultDetailMapper.selectSaleQuotationMultDetail2List(saleQuotationMultDetail);
    }

    /**
     * 新增多数量报价明细
     * 
     * @param saleQuotationMultDetail 多数量报价明细
     * @return 结果
     */
    @Override
    public int insertSaleQuotationMultDetail(SaleQuotationMultDetail saleQuotationMultDetail)
    {
        return saleQuotationMultDetailMapper.insertSaleQuotationMultDetail(saleQuotationMultDetail);
    }

    /**
     * 修改多数量报价明细
     * 
     * @param saleQuotationMultDetail 多数量报价明细
     * @return 结果
     */
    @Override
    public int updateSaleQuotationMultDetail(SaleQuotationMultDetail saleQuotationMultDetail)
    {
        return saleQuotationMultDetailMapper.updateSaleQuotationMultDetail(saleQuotationMultDetail);
    }

    /**
     * 删除多数量报价明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationMultDetailByIds(String ids)
    {
        return saleQuotationMultDetailMapper.deleteSaleQuotationMultDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除多数量报价明细信息
     * 
     * @param id 多数量报价明细ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationMultDetailById(Long id)
    {
        return saleQuotationMultDetailMapper.deleteSaleQuotationMultDetailById(id);
    }

    /**
     * 删除投入产出数
     * @Author 方舟
     * @Date 2021/4/21 15:46:18
     **/
    @Override
    public int deleteSaleQuotationMultDetailBySaleQuotationId(Long id){
        return saleQuotationMultDetailMapper.deleteSaleQuotationMultDetailBySaleQuotationId(id);
    }

    /**
     * 计算工序投入产出数
     * @Author 方舟
     * @Date 2021/4/21 16:02:26
     **/
    @Override
    public void createProcessDetail(SaleQuotationMult saleQuotationMult, SaleQuotationProduct saleQuotationProduct, List<SaleQuotationProcess> saleQuotationProcessList){
        Integer inqty = 0;
        Integer outqty = 0;
        boolean hasPer = false;
        //正常计算
        for (int i=saleQuotationProcessList.size()-1;i>=0;i--){
            SaleQuotationProcess processVO = saleQuotationProcessList.get(i);
            String isTimeCount = processVO.getIsTimeCount();
            BigDecimal times = processVO.getTimes();
            Integer lossQty = processVO.getLossQty();
            BigDecimal lossRate = (processVO.getLossRate().multiply(new BigDecimal(0.01)).add(new BigDecimal(1)));
            //
            SaleQuotationMultDetail saveVO = new SaleQuotationMultDetail();
            BeanUtils.copyProperties(processVO, saveVO);
            saveVO.setSaleQuotationProcessId(processVO.getId());
            saveVO.setSaleQuotationMultId(saleQuotationMult.getId());
            //
            if(!StringUtils.isEmpty(isTimeCount)&&"Y".equals(isTimeCount)){
                hasPer = true;
                //计算倍率的开始计算
                if(i==saleQuotationProcessList.size()-1){
                    outqty = saleQuotationMult.getQty();
                }else{
                    outqty = inqty;
                }
                SaleQuotationMultDetail calculateVO = FormulaUtils.getInqtyByOutqty(outqty,lossQty,lossRate,times);
                inqty = calculateVO.getInQty();
                //保存
                saveVO.setInQty(calculateVO.getInQty());
                saveVO.setOutQty(outqty);
                saveVO.setCalculateLog(calculateVO.getCalculateLog());
            }else{
                //非倍率计算 如果继承的
                if(hasPer){
                    saveVO.setOutQty(inqty);
                }else{
                    saveVO.setOutQty(saleQuotationMult.getQty());
                }
                SaleQuotationMultDetail calculateVO = FormulaUtils.getInqtyByOutqty(inqty,lossQty,lossRate,times);
                saveVO.setInQty(calculateVO.getInQty());
                saveVO.setCalculateLog(calculateVO.getCalculateLog());
            }
            saleQuotationMultDetailMapper.insertSaleQuotationMultDetail(saveVO);
        }
    }

    /**
     * 计算材料产出数
     * @Author 方舟
     * @Date 2021/4/21 16:02:42
     **/
    @Override
    public void createMaterialsDetail(SaleQuotationMult saleQuotationMult, SaleQuotationProduct saleQuotationProduct, List<SaleQuotationMaterials> saleQuotationMaterialsList){
        //看看是不是取工序数量
        for (int i=0;i<saleQuotationMaterialsList.size();i++){
            StringBuffer sb1 = new StringBuffer();
            SaleQuotationMaterials materialsVO = saleQuotationMaterialsList.get(i);
            Integer outQty = 0;
            if(!StringUtils.isEmpty(materialsVO.getIsGetProcessQty())&&"Y".equals(materialsVO.getIsGetProcessQty())){
                //如果是工序数量
                outQty = saleQuotationMultDetailMapper.getLinkProcessQty(materialsVO.getId(),saleQuotationMult.getId());
                //如果没有设置工序,那就还是按报价数量来
                if(null==outQty){
                    outQty = saleQuotationMult.getQty();
                }
            }else{
                outQty = saleQuotationMult.getQty();
            }
            //看看有没有公式,有公式加上公式
            if(null!=materialsVO.getFormulaId()){
                ConfigFormula configFormula = configFormulaMapper.selectConfigFormulaById(materialsVO.getFormulaId());
                if(null!=configFormula){
                    SaleQuotationMultDetail calculateVO = FormulaUtils.calculateMaterialsLossQty(outQty,configFormula);
                    outQty = calculateVO.getOutQty();
                    sb1.append(calculateVO.getCalculateLog());
                }
            }else{
                sb1.append("原数量【"+outQty+"】未设置计算材料数量公式,按数量乘以单价计算;");
            }
            //倍率,损耗率计算
            SaleQuotationMultDetail calculateVO2 = FormulaUtils.getInqtyByOutqty(outQty,materialsVO.getLossQty(),materialsVO.getLossRate(),materialsVO.getTimes());
            Integer inQty = calculateVO2.getInQty();
            sb1.append(calculateVO2.getCalculateLog());
            //保存
            SaleQuotationMultDetail saveVO = new SaleQuotationMultDetail();
            BeanUtils.copyProperties(materialsVO, saveVO);
            saveVO.setSaleQuotationMaterialsId(materialsVO.getId());
            saveVO.setSaleQuotationMultId(saleQuotationMult.getId());
            saveVO.setInQty(inQty);
            saveVO.setOutQty(outQty);
            saveVO.setCalculateLog(sb1.toString());
            saleQuotationMultDetailMapper.insertSaleQuotationMultDetail(saveVO);
        }
    }


}
