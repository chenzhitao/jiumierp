package com.ruoyi.project.outsource.outsourceDelivery.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDelivery;
import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDeliveryExport;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 外发到货Service接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface IOutsourceDeliveryService 
{
    /**
     * 查询外发到货
     * 
     * @param id 外发到货ID
     * @return 外发到货
     */
    public OutsourceDelivery selectOutsourceDeliveryById(Long id);

    /**
     * 查询外发到货列表
     * 
     * @param outsourceDelivery 外发到货
     * @return 外发到货集合
     */
    public List<OutsourceDelivery> selectOutsourceDeliveryList(OutsourceDelivery outsourceDelivery);
    public List<OutsourceDeliveryExport> selectOutsourceDeliveryExportList(OutsourceDelivery outsourceDelivery);

    /**
     * 新增外发到货
     * 
     * @param outsourceDelivery 外发到货
     * @return 结果
     */
    public int insertOutsourceDelivery(OutsourceDelivery outsourceDelivery);

    /**
     * 修改外发到货
     * 
     * @param outsourceDelivery 外发到货
     * @return 结果
     */
    public int updateOutsourceDelivery(OutsourceDelivery outsourceDelivery);

    /**
     * 批量删除外发到货
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceDeliveryByIds(String ids);

    /**
     * 删除外发到货信息
     * 
     * @param id 外发到货ID
     * @return 结果
     */
    public int deleteOutsourceDeliveryById(Long id);

    /**
     * 创建外发到货
     */
    public OutsourceDelivery createOutsourceDelivery(OutsourceDelivery outsourceDelivery);

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    public OutsourceDelivery countAmount(OutsourceDelivery outsourceDelivery);
}
