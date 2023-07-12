package com.ruoyi.project.outsource.outsourceDelivery.mapper;

import java.util.List;
import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDelivery;
import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDeliveryExport;

/**
 * 外发到货Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface OutsourceDeliveryMapper 
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
     * 删除外发到货
     * 
     * @param id 外发到货ID
     * @return 结果
     */
    public int deleteOutsourceDeliveryById(Long id);
    public int deleteOutsourceDeliveryProcessById(Long id);

    /**
     * 批量删除外发到货
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceDeliveryByIds(String[] ids);
}
