package com.ruoyi.common.utils.rzy;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.config.configFormula.domain.ConfigFormula;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
import com.ruoyi.project.rzy.rzyCommon.domain.RzyCommon;
import com.ruoyi.project.sale.saleQuotationMultDetail.domain.SaleQuotationMultDetail;

import java.math.BigDecimal;
import java.util.*;

/**
 * 计算工具
 * @Author 方舟
 * @Date 2021/4/15 9:05:59
**/
public class FormulaUtils {

    /**
     * 根据长宽高公式计算
     * @Author 方舟
     * @Date 2021/4/15 13:21:24
    **/
    public static Integer calculateForOpenSize(String type, String formula, ConfigFormula configFormula, ConfigProduct configProduct){
        //String formula = configFormula.getOpenLongFormula();
        if(StringUtils.isEmpty(formula)){
            return 0;
        }
        Double d = 0.0;
        Integer n = 0;
        String result = null;
        try {
            formula = replaceVariable(formula,configProduct,configFormula);
            result = CalUtil.getResultByStrCal(formula);
            d = Double.parseDouble(result);
            n = (int) Math.round(d);
            if(type.equals("L")){
                n += configFormula.getExtraOpenLong();
            }
            if(type.equals("W")){
                n += configFormula.getExtraOpenWidth();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * 替换变量
     * @Author 方舟
     * @Date 2021/4/15 13:33:50
    **/
    private static String replaceVariable(String formula, ConfigProduct configProduct, ConfigFormula configFormula){
        String str = formula;
        //长、宽、高、放数长、放数宽、放数高、展开放数长、展开放数宽、边、挂头、免口、粘口
        String[] vars = {"边","挂头","免口","粘口","长","宽","高"};
        Integer[] vals = {
                /*configFormula.getExtraLong(),
                configFormula.getExtraWidth(),
                configFormula.getExtraHeight(),
                configFormula.getExtraOpenLong(),
                configFormula.getExtraOpenWidth(),*/
                configFormula.getSideSize(),
                configFormula.getHangingSize(),
                configFormula.getDispenseSize(),
                configFormula.getStickSize(),
                configProduct.getSizeLong()+configFormula.getExtraLong(),
                configProduct.getSizeWidth()+configFormula.getExtraWidth(),
                configProduct.getSizeHeight()+configFormula.getExtraHeight()
        };
        for (int i=0;i<vars.length;i++){
            str = str.replaceAll(vars[i], vals[i].toString());
        }
        return str;
    }

    /**
     * 根据展开尺寸计算面积m²
     * @Author 方舟
     * @Date 2021/4/15 13:22:36
    **/
    public static Double calculateAreaWithOpen(Integer openLong,Integer openWidth){
        Double area = (Double.valueOf(openLong) * Double.valueOf(openWidth))/(1000 * 1000);
        return area;
    }

    /**
     * 计算工序价格
     * @Author 方舟
     * @Date 2021/4/15 14:26:19
    **/
    public static ConfigFormula calculateProcessPrice(Integer qty, ConfigFormula configFormula){
        StringBuffer sb = new StringBuffer();
        sb.append("数量"+qty+",使用公式【"+configFormula.getFormulaName()+"】计算价格;");
        BigDecimal result = new BigDecimal(0);
        //0.前置条件
        if(qty.equals(0)){
            sb.append("数量是"+qty+",无法计算价格;");
        }else{
            //1.看系数是不是0
            if(configFormula.getCoefficientQty().compareTo(new BigDecimal(0))==0){
                sb.append("系数是【0】,所以用简单计算公式:价格*数量;");
                //2.如果系数0,按简单计算:价格*数量,单价不能超过最大或最小
                if(configFormula.getBasePrice().compareTo(configFormula.getMinPrice())==-1) {
                    sb.append("基础单价【"+configFormula.getBasePrice()+"】小于最小单价【"+configFormula.getMinPrice()+"】,用最小单价【"+configFormula.getMinPrice()+"】计算;");
                    //如果小于最低单价
                    result = configFormula.getMinPrice().multiply(new BigDecimal(qty));
                }else if(configFormula.getBasePrice().compareTo(configFormula.getMaxPrice())==1&&new BigDecimal(0).compareTo(configFormula.getMaxPrice())!=0){
                    sb.append("基础单价【"+configFormula.getBasePrice()+"】大于最大单价【"+configFormula.getMaxPrice()+"】,用最大单价【"+configFormula.getMaxPrice()+"】计算;");
                    //如果最大单价不是0,并且超过最大单价
                    result = configFormula.getMaxPrice().multiply(new BigDecimal(qty));
                }else{
                    result = configFormula.getBasePrice().multiply(new BigDecimal(qty));
                }
                sb.append("计算结果是【"+result.setScale(2, BigDecimal.ROUND_HALF_UP)+"】;");
            }else{
                sb.append("系数是【"+configFormula.getCoefficientQty()+"】,所以用系数计算公式:基数+系数*数量;");
                sb.append("基数是【"+configFormula.getBaseMinQty()+"】,最小批量是【"+configFormula.getMinUnitQty()+"】;");
                //3.如果是按系数计算
                BigDecimal coefficient = new BigDecimal(qty/configFormula.getMinUnitQty()).multiply(configFormula.getCoefficientQty());
                result = new BigDecimal(configFormula.getBaseMinQty()).add(coefficient);
                sb.append("计算结果是【"+result.setScale(2, BigDecimal.ROUND_HALF_UP)+"】;");
            }
            //4.看数量有没有超过打折线
            if(qty>configFormula.getDiscountQty()){
                sb.append("数量超过了折扣线【"+configFormula.getDiscountQty()+"】,就按【"+configFormula.getDiscountTimes()+"】进行倍率或者折扣计算;");
                //该打折打折,该加倍加倍
                result = result.multiply(configFormula.getDiscountTimes());
                sb.append("计算结果是【"+result.setScale(2, BigDecimal.ROUND_HALF_UP)+"】;");
            }
            //5.加上额外部分
            if(new BigDecimal(1).compareTo(configFormula.getBaseTimes())!=0){
                sb.append("整体倍率是【"+configFormula.getBaseTimes()+"】;");
                result = result.multiply(configFormula.getBaseTimes());//总体倍数,一般是1
                sb.append("计算结果是【"+result.setScale(2, BigDecimal.ROUND_HALF_UP)+"】;");
            }
            BigDecimal extra = configFormula.getExtraTimes().multiply(configFormula.getExtraPrice());
            sb.append("额外价格是【"+configFormula.getExtraPrice()+"】,倍率是【"+configFormula.getExtraTimes()+"】,额外总价是【"+extra+"】;");
            result = result.add(extra);
            sb.append("计算结果是【"+result.setScale(2, BigDecimal.ROUND_HALF_UP)+"】;");
            //6.看评价单价,不能超过上限和下限
            BigDecimal per = result.divide(new BigDecimal(qty),2, BigDecimal.ROUND_HALF_UP);
            if(per.compareTo(configFormula.getMaxPrice())==1&&new BigDecimal(0).compareTo(configFormula.getMaxPrice())!=0){
                sb.append("当前的平均单价是【"+per+"】,大于最大单价【"+configFormula.getMaxPrice()+"】,单价按最大单价重新计算;");
                //超过上限
                result = configFormula.getMaxPrice().multiply(new BigDecimal(qty));
                sb.append("计算结果是【"+result.setScale(2, BigDecimal.ROUND_HALF_UP)+"】;");
            }else if(per.compareTo(configFormula.getMinPrice())==-1){
                sb.append("当前的平均单价是【"+per+"】,小于最小单价【"+configFormula.getMinPrice()+"】,单价按最小单价重新计算;");
                //低于下限
                result = configFormula.getMinPrice().multiply(new BigDecimal(qty));
                sb.append("计算结果是【"+result.setScale(2, BigDecimal.ROUND_HALF_UP)+"】;");
            }else{
                sb.append("当前的平均单价是【"+per+"】,没有超过最大单价和低于最小单价;");
            }
            //7.看总价,不能超过上限和下限
            if(result.compareTo(configFormula.getPriceCeiling())==1&&new BigDecimal(0).compareTo(configFormula.getPriceCeiling())!=0){
                sb.append("当前的总价超过价格上限【"+configFormula.getPriceCeiling()+"】,按价格上限算;");
                //超过上限
                result = configFormula.getPriceCeiling();
            }else if(result.compareTo(configFormula.getPriceBottom())==-1){
                sb.append("当前的总价低于价格下限【"+configFormula.getPriceBottom()+"】,按价格下限算;");
                //低于下限
                result = configFormula.getPriceBottom();
            }else{
                sb.append("当前的总价在价格上限和下限范围内;");
            }
        }
        sb.append("最后计算结果是【"+result.setScale(2, BigDecimal.ROUND_HALF_UP)+"】;");
        ConfigFormula returnVO = new ConfigFormula();
        returnVO.setCalculateLog(sb.toString());
        returnVO.setAmount(result);
        return returnVO;
    }

    /**
     * 计算材料放数
     * @Author 方舟
     * @Date 2021/4/15 16:45:52
    **/
    public static SaleQuotationMultDetail calculateMaterialsLossQty(Integer qty, ConfigFormula configFormula){
        Integer resultQty = 0;
        StringBuffer sb1 = new StringBuffer();
        sb1.append("原数量【"+qty+"】,使用公式【"+configFormula.getFormulaName()+"】计算;");
        //不含放数的数量
        BigDecimal orgQty = new BigDecimal(qty).multiply(configFormula.getBaseRate()).setScale(0,BigDecimal.ROUND_UP);
        sb1.append("基数比例是【"+configFormula.getBaseRate()+"】,所以不含放数的数量是【"+orgQty+"】");
        //算放数
        if(configFormula.getLossRate().compareTo(new BigDecimal(0))!=0){
            sb1.append("放数比例是【"+configFormula.getBaseRate()+"】,不为0,所以放数按比例计算;");
            BigDecimal lossQty = (orgQty.multiply(configFormula.getLossRate())).setScale(0,BigDecimal.ROUND_UP);
            resultQty = (orgQty.add(lossQty)).intValue();
            sb1.append("放数是【"+lossQty+"】,最后计算得到的是数量是【"+resultQty+"】;");
        }else{
            sb1.append("放数比例是0,所以放数按放数组计算;");
            String baseQtyStr = "0";
            String lossQtyStr = "0";
            if(null!=configFormula.getBaseQtyArr()){
                baseQtyStr = configFormula.getBaseQtyArr();
            }
            if(null!=configFormula.getLossQtyArr()){
                lossQtyStr = configFormula.getLossQtyArr();
            }
            String[] baseQty = baseQtyStr.split(",");
            String[] lossQty = lossQtyStr.split(",");
            for(int i=0;i<baseQty.length;i++){
                try{
                    Integer baseQ = Integer.parseInt(baseQty[i]);
                    Integer loss1Qty = 0;
                    if(qty<baseQ){
                        break;
                    }else{
                        loss1Qty = Integer.parseInt(lossQty[i]);
                    }
                    resultQty = loss1Qty + orgQty.intValue();
                    sb1.append("原数量【"+qty+"】用到的方式档是【"+loss1Qty+"】,最后计算得到的是数量是【"+resultQty+"】;");
                }catch (Exception e){
                    resultQty = orgQty.intValue();
                    sb1.append("放数组格式不正确,跳过;");
                }
            }
        }
        SaleQuotationMultDetail saleQuotationMultDetail = new SaleQuotationMultDetail();
        saleQuotationMultDetail.setOutQty(resultQty);
        saleQuotationMultDetail.setCalculateLog(sb1.toString());
        return saleQuotationMultDetail;
    }

    /**
     * 算投入数
     * @Author 方舟
     * @Date 2021/4/22 9:30:01
     **/
    public static SaleQuotationMultDetail getInqtyByOutqty(Integer outqty,Integer lossQty,BigDecimal lossRate,BigDecimal times){
        StringBuffer sb1 = new StringBuffer();
        Integer inqty = 0;
        BigDecimal realLossRate = lossRate.add(new BigDecimal(1));
        //realLossRate = realLossRate.divide(BigDecimal.valueOf(100),4,BigDecimal.ROUND_HALF_UP);
        BigDecimal tempInQty = new BigDecimal(outqty);//赋值
        tempInQty = tempInQty.add(new BigDecimal(lossQty));//算损耗数
        tempInQty = tempInQty.multiply(realLossRate);//损耗率
        tempInQty = tempInQty.divide(times,0,BigDecimal.ROUND_UP);//倍率
        inqty = tempInQty.intValue();
        sb1.append("倍率:"+times+",损耗率:"+(lossRate.multiply(new BigDecimal(100))).setScale(2,BigDecimal.ROUND_HALF_UP)+"%,固定损耗:"+lossQty+";");
        SaleQuotationMultDetail saleQuotationMultDetail = new SaleQuotationMultDetail();
        saleQuotationMultDetail.setInQty(inqty);
        saleQuotationMultDetail.setCalculateLog(sb1.toString());
        return saleQuotationMultDetail;
    }

    /**
     * 计算拼版数
     * @Author 方舟
     * @Date 2021/5/5 11:31:41
    **/
    public static List<RzyCommon> puzzleCount(Integer workLong, Integer workWidth, Integer opensizeLong, Integer opensizeWidth){
        List<RzyCommon> maps = new ArrayList<RzyCommon>();
        Integer puzzleQty = 0;
        Integer longTimes = 0;
        Integer widthTimes = 0;
        Integer diffLong = 0;
        Integer diffWidth = 0;
        Integer longTimes2 = 0;
        Integer widthTimes2 = 0;
        //全部横向排列
        RzyCommon s1 = new RzyCommon();
        longTimes = (int) Math.floor(workLong/opensizeLong);
        widthTimes = (int) Math.floor(workWidth/opensizeWidth);
        puzzleQty = longTimes * widthTimes;
        s1.setIntValue1(puzzleQty);
        s1.setStrValue1("全部横向排列");
        maps.add(s1);
        //全部纵向排列
        RzyCommon s2 = new RzyCommon();
        longTimes = (int) Math.floor(workWidth/opensizeLong);
        widthTimes = (int) Math.floor(workLong/opensizeWidth);
        puzzleQty = longTimes * widthTimes;
        s2.setIntValue1(puzzleQty);
        s2.setStrValue1("全部纵向排列");
        maps.add(s2);
        //横向排列为主
        RzyCommon s3 = new RzyCommon();
        longTimes = (int) Math.floor(workLong/opensizeLong);
        widthTimes = (int) Math.floor(workWidth/opensizeWidth);
        puzzleQty = longTimes * widthTimes;
        diffLong = workLong - (opensizeLong * longTimes);
        if(diffLong<0){
            diffLong = 0;
        }
        longTimes2 = (int) Math.floor(diffLong/opensizeWidth);
        widthTimes2 = (int) Math.floor(workWidth/opensizeLong);
        puzzleQty += (longTimes2 * widthTimes2);
        s3.setIntValue1(puzzleQty);
        s3.setStrValue1("横向排列为主，剩余部分纵向排列");
        maps.add(s3);
        //纵向排列为主
        RzyCommon s4 = new RzyCommon();
        longTimes = (int) Math.floor(workWidth/opensizeLong);
        widthTimes = (int) Math.floor(workLong/opensizeWidth);
        puzzleQty = longTimes * widthTimes;
        diffWidth = workWidth - (widthTimes * opensizeLong);
        if(diffWidth<0){
            diffWidth = 0;
        }
        longTimes2 = (int) Math.floor(workLong/opensizeLong);
        widthTimes2 = (int) Math.floor(diffWidth/opensizeWidth);
        puzzleQty += (longTimes2 * widthTimes2);
        s4.setIntValue1(puzzleQty);
        s4.setStrValue1("纵向排列为主，剩余部分横向排列");
        maps.add(s4);
        return maps;
    }

    /**
     * 计算开料尺寸
     * @Author 方舟
     * @Date 2021/8/21 20:20:42
    **/
    public static Map openMaterialsSizeCalculate(Integer partsLong,Integer partsWidth,Integer platLong,Integer platWidth){
        Map map = new HashMap();
        Integer openLong = 0;
        Integer openWidth = 0;
        if(null==partsLong||partsLong.equals(0)){
            partsLong = 99999999;
        }
        if(null==partsWidth||partsWidth.equals(0)){
            partsWidth = 99999999;
        }
        //先全部横着,算下能开多少
        Integer rowNo1 = (int) Math.floor(platLong/partsLong);
        Integer colNo1 = (int) Math.floor(platWidth/partsWidth);
        Integer max1 = rowNo1 * colNo1;
        //然后竖着再算一次
        Integer rowNo2 = (int) Math.floor(platLong/partsWidth);
        Integer colNo2 = (int) Math.floor(platWidth/partsLong);
        Integer max2 = rowNo2 * colNo2;
        //看看那个划算用哪个
        if(max1>=max2){
            openLong = rowNo1 * partsLong;
            openWidth = colNo1 * partsWidth;
            map.put("openLong",openLong);
            map.put("openWidth",openWidth);
            map.put("rows",rowNo1);
            map.put("cols",colNo1);
            map.put("times",max1);
            map.put("type","row");
        }else{
            openLong = rowNo2 * partsWidth;
            openWidth = colNo2 * partsLong;
            map.put("openLong",openLong);
            map.put("openWidth",openWidth);
            map.put("rows",rowNo2);
            map.put("cols",colNo2);
            map.put("times",max2);
            map.put("type","col");
        }
        return map;
    }

    private static Integer minNum(Integer n){
        if(n.equals(0)){
            return 1;
        }else{
            return n;
        }
    }

    /**
     * 计算退货金额
     * @Author 方舟
     * @Date 2021/5/13 11:11:54
    **/
    public static BigDecimal calculatorReturnAmount(String returnType, Integer returnQty, BigDecimal returnRate, BigDecimal price){
        BigDecimal amount = new BigDecimal(0);
        if("THKK".equals(returnType)){
            amount = price.multiply(new BigDecimal(returnQty));
        }
        if("ZKCL".equals(returnType)){
            amount = price.multiply(new BigDecimal(returnQty));
            BigDecimal rate = new BigDecimal(1).subtract(returnRate.multiply(new BigDecimal(0.01)));
            amount = amount.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return amount;
    }

}
