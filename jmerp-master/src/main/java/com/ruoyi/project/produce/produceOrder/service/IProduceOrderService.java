package com.ruoyi.project.produce.produceOrder.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configEquipment.domain.ConfigEquipment;
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.produce.produceOrderMaterials.domain.ProduceOrderMaterials;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 施工单Service接口
 * 
 * @author fangzhou
 * @date 2021-04-30
 */
public interface IProduceOrderService 
{
    /**
     * 查询施工单
     * 
     * @param id 施工单ID
     * @return 施工单
     */
    public ProduceOrder selectProduceOrderById(Long id);
    public ProduceOrder selectProduceOrderPartById(Long id);

    /**
     * 查询施工单列表
     * 
     * @param produceOrder 施工单
     * @return 施工单集合
     */
    public List<ProduceOrder> selectProduceOrderList(ProduceOrder produceOrder);
    public List<ProduceOrder> selectProduceOrderPartList(ProduceOrder produceOrder);

    /**
     * 新增施工单
     * 
     * @param produceOrder 施工单
     * @return 结果
     */
    public int insertProduceOrder(ProduceOrder produceOrder);

    /**
     * 修改施工单
     * 
     * @param produceOrder 施工单
     * @return 结果
     */
    public int updateProduceOrder(ProduceOrder produceOrder);
    public int updateProduceOrderPart(ProduceOrder produceOrder);

    /**
     * 批量删除施工单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceOrderByIds(String ids);

    /**
     * 删除施工单信息
     * 
     * @param id 施工单ID
     * @return 结果
     */
    public int deleteProduceOrderById(Long id);

    /**
     * 加载工艺卡里的工序和材料
     * @Author 方舟
     * @Date 2021/5/5 9:44:33
     **/
    public ProduceOrder loadProcessMaterials(ProduceOrder produceOrder);

    /**
     * 加载工艺卡里的工序和材料
     * @Author 方舟
     * @Date 2021/5/5 9:44:33
     **/
    public ProduceOrder areaCalculator(ProduceOrder produceOrder);

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    public ProduceOrder createProduceOrder(ProduceOrder produceOrder);

    /**
     * 产品入库
     * @Author 方舟
     * @Date 2021/5/18 12:13:10
     **/
    public ProduceOrder inbound(ProduceOrder produceOrder);

    /**
     * 获取印刷设备
     * @Author 方舟
     * @Date 2021/8/18 19:19:21
     **/
    public List<ConfigEquipment> getPrintEquipment(ProduceOrder produceOrder);

    /**
     * 批量加工序
     * @Author 方舟
     * @Date 2021/8/22 21:53:34
     **/
    public ProduceOrder addProcessBatch(ProduceOrder produceOrder);


    /**
     * 计算工序投入产出数
     * @Author 方舟
     * @Date 2021/5/6 10:31:30
     **/
    public void inoutCountYS(ProduceOrder produceOrder, List<ProduceOrderProcess> processList, List<ProduceOrderMaterials> materialsList);
    public void inoutCountQZ(ProduceOrder produceOrder, List<ProduceOrderProcess> processList, List<ProduceOrderMaterials> materialsList);
    public void inoutCountMQ(ProduceOrder produceOrder, List<ProduceOrderProcess> processList, List<ProduceOrderMaterials> materialsList);
    public void inoutCountIO(ProduceOrder produceOrder, List<ProduceOrderProcess> processList,Integer realQty);
    public void inoutCountCLQ(ProduceOrder produceOrder, List<ProduceOrderProcess> processList,Integer realQty);

    /**
     * 保存开料要求
     * @param produceOrder
     * @return
     */
    public int saveCutRequire(ProduceOrder produceOrder);

    /**
     * 根据销售订单翻单
     * @param orgSaleOrderId
     * @param newSaleOrderId
     * @return
     */
    public void copyProduceOrderBySaleOrder(Long orgSaleOrderId,Long newSaleOrderId);

    /**
     * 工单翻单
     * @param produceOrder
     * @return
     */
    public ProduceOrder copyProduceOrder(ProduceOrder produceOrder);


    /**
     * 拼版
     * @param produceOrder
     * @return
     */
    public ProduceOrder setMarkup(ProduceOrder produceOrder);

    /**
     * 新增部件
     * @param produceOrder
     * @return
     */
    public ProduceOrder insertProduceOrderPart(ProduceOrder produceOrder);

    /**
     * 删除部件
     * @param produceOrder
     * @return
     */
    public ProduceOrder deleteProduceOrderPartByPartIds(ProduceOrder produceOrder);

}
