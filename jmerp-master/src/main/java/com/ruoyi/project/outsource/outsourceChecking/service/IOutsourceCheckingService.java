package com.ruoyi.project.outsource.outsourceChecking.service;

import java.util.List;
import com.ruoyi.project.outsource.outsourceChecking.domain.OutsourceChecking;
import com.ruoyi.project.purchase.purchaseChecking.domain.PurchaseChecking;

/**
 * 外发对账Service接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface IOutsourceCheckingService 
{
    /**
     * 查询外发对账
     * 
     * @param id 外发对账ID
     * @return 外发对账
     */
    public OutsourceChecking selectOutsourceCheckingById(Long id);

    /**
     * 查询外发对账列表
     * 
     * @param outsourceChecking 外发对账
     * @return 外发对账集合
     */
    public List<OutsourceChecking> selectOutsourceCheckingList(OutsourceChecking outsourceChecking);

    /**
     * 新增外发对账
     * 
     * @param outsourceChecking 外发对账
     * @return 结果
     */
    public int insertOutsourceChecking(OutsourceChecking outsourceChecking);

    /**
     * 修改外发对账
     * 
     * @param outsourceChecking 外发对账
     * @return 结果
     */
    public int updateOutsourceChecking(OutsourceChecking outsourceChecking);

    /**
     * 批量删除外发对账
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceCheckingByIds(String ids);

    /**
     * 删除外发对账信息
     * 
     * @param id 外发对账ID
     * @return 结果
     */
    public int deleteOutsourceCheckingById(Long id);

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    public OutsourceChecking createOutsourceChecking(OutsourceChecking outsourceChecking);

    /**
     * 计算金额
     * @Author 方舟
     * @Date 2021/5/27 14:45:12
     **/
    public OutsourceChecking calculateAmount(OutsourceChecking outsourceChecking);
}
