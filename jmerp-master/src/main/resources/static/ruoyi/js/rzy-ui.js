/**
 * 通用js方法封装处理
 * Copyright (c) 2021 fangzhou
 */
(function ($) {
    $.extend({
        rzy: {
            // 表格显示处理
            grid: {
                exportSelect:function (columnName,url) {
                    var ids = $.table.selectColumns(columnName);
                    var dataParam = $("#export-form").serializeArray();
                    var tipMsg = "确定导出所有数据吗？";
                    if($.common.isNotEmpty(ids)){
                        tipMsg = "确定导出勾选" + ids.length + "条数据吗？";
                        dataParam.push({ "name": "ids", "value": ids });
                    }
                    $.modal.confirm(tipMsg, function() {
                        $.post(url, dataParam, function(result) {
                            if (result.code == web_status.SUCCESS) {
                                window.location.href = ctx + "common/download?fileName=" + encodeURI(result.msg) + "&delete=" + true;
                            } else {
                                $.modal.alertError(result.msg);
                            }
                        });
                    });
                },
                //找config里面的值
                getConfigSelectText:function (datas, value, showCol, objCol) {
                    if(null==objCol){
                        objCol = "id";
                    }
                    var res = "";
                    $.each(datas, function(idx, obj) {
                        if (obj[objCol] == value) {
                            res = obj[showCol];
                            return false;
                        }
                    });
                    return res;
                },
                //下拉框
                commonToSelect: function(datas, value, name, objId, objName) {
                    var actions = [];
                    actions.push($.common.sprintf("<select class='form-control' name='%s'>", name));
                    $.each(datas, function(index, obj) {
                        actions.push($.common.sprintf("<option value='%s'", obj[objId]));
                        if (obj[objId] == ('' + value)) {
                            actions.push(' selected');
                        }
                        actions.push($.common.sprintf(">%s</option>", obj[objName]));
                    });
                    actions.push('</select>');
                    return actions.join('');
                },
            },
            //部门
            dept: {
                //选择部门引用
                selectDeptTree:function () {
                    var treeId = $("#treeId").val();
                    var deptId = $.common.isEmpty(treeId) ? "100" : $("#treeId").val();
                    var url = ctx + "system/dept/selectDeptTree/" + deptId;
                    var options = {
                        title: '选择部门',
                        width: "380",
                        url: url,
                        url: url,
                        callBack: function(index, layero){
                            var tree = layero.find("iframe")[0].contentWindow.$._tree;
                            var body = layer.getChildFrame('body', index);
                            $("#treeId").val(body.find('#treeId').val());
                            $("#treeName").val(body.find('#treeName').val());
                            layer.close(index);
                        }
                    };
                    $.modal.openOptions(options);
                },
                //已经有id了,填值
                initDeptName:function () {

                }
            },
            //其他公共方法
            common: {
                // 条码
                barcode:{
                    build:function (sn,w,h) {
                        $(".barcode").JsBarcode(sn, {
                            format: "CODE128", //选择要使用的条形码类型
                            width: w,//设置条之间的宽度
                            height: h, //高度
                            textMargin: 1, //设置条形码和文本之间的间距
                            fontSize: 14,//设置文本的大小
                            lineColor: "#333",//设置条和文本的颜色。
                        });
                    }
                },
                // 二维码
                qrcode:{
                    build:function (str,w) {
                        $("#qrcode").html("");
                        var qrcode = new QRCode(document.getElementById("qrcode"), {
                            width : w,
                            height : w
                        });
                        qrcode.makeCode(str);
                    }
                },
                checkIsNull:function (obj){
                    obj = obj + "";
                    if(null==obj||obj==""||obj=="null"){
                        return true;
                    }else{
                        return false;
                    }
                },
                //null和字符串合并校验空
                clearNull:function (str) {
                    if(str==null){
                        return "";
                    }else{
                        return str;
                    }
                },
                //clearNull数字版
                clearNullInt:function(str){
                    var n = parseInt(str);
                    if(isNaN(n)){
                        return 0;
                    }else{
                        return n;
                    }
                },
                //clearNull浮点版
                clearNullFloat:function(str){
                    var n = parseFloat(str);
                    if(isNaN(n)){
                        return 0;
                    }else{
                        return n;
                    }
                },
                //数组转字符串
                arr2str: function (arr){
                    if(arr==null){
                        return "";
                    }
                    var str = "";
                    for(var i=0;i<arr.length;i++){
                        if(i!=0){
                            str += ","
                        }
                        str += arr[i];
                    }
                    return str;
                },
                //图标下拉框
                icon:function (formId,columnName) {
                    $("input[name='"+columnName+"']").focus(function() {
                        $(".icon-drop").show();
                    });
                    $("#"+formId).click(function(event) {
                        var obj = event.srcElement || event.target;
                        if (!$(obj).is("input[name='"+columnName+"']")) {
                            $(".icon-drop").hide();
                        }
                    });
                    $(".icon-drop").find(".ico-list i").on("click", function() {
                        $('#icon').val($(this).attr('class'));
                    });
                    $('input').on('ifChecked', function(event){
                        var menuType = $(event.target).val();
                        if (menuType == "M") {
                            $("#url").parents(".form-group").hide();
                            $("#perms").parents(".form-group").hide();
                            $("#icon").parents(".form-group").show();
                            $("#target").parents(".form-group").hide();
                            $("input[name='visible']").parents(".form-group").show();
                            $(".is-refresh").hide();
                        } else if (menuType == "C") {
                            $("#url").parents(".form-group").show();
                            $("#perms").parents(".form-group").show();
                            $("#icon").parents(".form-group").show();
                            $("#target").parents(".form-group").show();
                            $("input[name='visible']").parents(".form-group").show();
                            $(".is-refresh").show();
                        } else if (menuType == "F") {
                            $("#url").parents(".form-group").hide();
                            $("#perms").parents(".form-group").show();
                            $("#icon").parents(".form-group").hide();
                            $("#target").parents(".form-group").hide();
                            $("input[name='visible']").parents(".form-group").hide();
                            $(".is-refresh").hide();
                        }
                    });
                },
                //弹框列表选择后调用
                afterSelect:function () {
                    var rows = $.table.selectFirstColumns();
                    if (rows.length == 0) {
                        $.modal.alertWarning("请至少选择一条记录");
                        return;
                    }
                    $.modal.close();
                    parent.getSelectIds(rows.join());
                },
                afterSelectInSame:function (column,msg) {
                    var objArray = $.table.selectColumns(column);
                    if(objArray.length>1){
                        $.modal.alertWarning(msg);
                        return;
                    }
                    var rows = $.table.selectFirstColumns();
                    if (rows.length == 0) {
                        $.modal.alertWarning("请至少选择一条记录");
                        return;
                    }
                    $.modal.close();
                    parent.getSelectIds(rows.join());
                },
                //afterSelect的父页面操作
                afterSelectParent:function (ids,create_url,open_url,title) {
                    var params = new Object();
                    params.ids = ids;
                    //初始化数据
                    $.operate.post(create_url, params, function(result) {
                        $.table.search();
                        if(result.data.id!=-1&&null!=open_url){
                            $.modal.openTab(title, open_url + result.data.id);
                        }
                    });
                },
                //根据前缀获取单据类型
                getDocumentType:function (type) {
                    var typeArr = ["XBJ","XDD","XSH","XTH","XDZ","RZ2","SPC","SCB","SJD","CSQ","CDD","CDH","CTH","CDZ","WJG","WDH","WTH","WDZ","CRB"];
                    var textArr = ["报价单","销售订单","送货单","退货单","销售对账","施工单","生产排程","产量上报","生产进度","采购申请","采购订单","采购到货","采购退货","采购对账","外发加工","外发到货","外发退货","外发对账","生产日报"];
                    var tableArr = ["sale_quotation","sale_order","sale_delivery","sale_return","sale_checking","produce_order","produce_schedule","produce_report","produce_warehouse","purchase_request","purchase_order","purchase_delivery","purchase_return","purchase_checking","outsource_order","outsource_delivery","outsource_return","outsource_checking","finance_daily"];
                    var pageArr = ["sale/saleQuotation","sale/saleOrder","sale/saleDelivery","sale/saleReturn","sale/saleChecking","produce/produceOrder","produce/produceSchedule","produce/produceReport","produce/produceWarehouse","purchase/purchaseRequest","purchase/purchaseOrder","purchase/purchaseDelivery","purchase/purchaseReturn","purchase/purchaseChecking","outsource/outsourceOrder","outsource/outsourceDelivery","outsource/outsourceReturn","outsource/outsourceChecking","finance/financeDaily"];
                    var params = new Object();
                    for (var i=0;i<typeArr.length;i++){
                        if(typeArr[i]==type){
                            params.text = textArr[i];
                            params.table = tableArr[i];
                            params.page = pageArr[i];
                            break;
                        }
                    }
                    return params;
                },
                //文本中的中文逗号转英文逗号
                turnComma:function (obj) {
                    var str = $(obj).val();
                    var res = str.replaceAll(/，/ig,",");
                    $(obj).val(res)
                    //return res;
                },
                //金额转大写
                //把数字金额转换成中文大写数字
                changeNumMoneyToChinese: function (money) {
                    var cnNums = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); //汉字的数字
                    var cnIntRadice = new Array("", "拾", "佰", "仟"); //基本单位
                    var cnIntUnits = new Array("", "万", "亿", "兆"); //对应整数部分扩展单位
                    var cnDecUnits = new Array("角", "分", "毫", "厘"); //对应小数部分单位
                    var cnInteger = "整"; //整数金额时后面跟的字符
                    var cnIntLast = "元"; //整型完以后的单位
                    var maxNum = 999999999999999.9999; //最大处理的数字
                    var IntegerNum; //金额整数部分
                    var DecimalNum; //金额小数部分
                    var ChineseStr = ""; //输出的中文金额字符串
                    var parts; //分离金额后用的数组，预定义
                    var Symbol="";//正负值标记
                    if (money == "") {
                        return "";
                    }

                    money = parseFloat(money);
                    if (money >= maxNum) {
                        alert('超出最大处理数字');
                        return "";
                    }
                    if (money == 0) {
                        ChineseStr = cnNums[0] + cnIntLast + cnInteger;
                        return ChineseStr;
                    }
                    if(money<0)
                    {
                        money=-money;
                        Symbol="负 ";
                    }
                    money = money.toString(); //转换为字符串
                    if (money.indexOf(".") == -1) {
                        IntegerNum = money;
                        DecimalNum = '';
                    } else {
                        parts = money.split(".");
                        IntegerNum = parts[0];
                        DecimalNum = parts[1].substr(0, 4);
                    }
                    if (parseInt(IntegerNum, 10) > 0) { //获取整型部分转换
                        var zeroCount = 0;
                        var IntLen = IntegerNum.length;
                        for (var i = 0; i < IntLen; i++) {
                            var n = IntegerNum.substr(i, 1);
                            var p = IntLen - i - 1;
                            var q = p / 4;
                            var m = p % 4;
                            if (n == "0") {
                                zeroCount++;
                            }
                            else {
                                if (zeroCount > 0) {
                                    ChineseStr += cnNums[0];
                                }
                                zeroCount = 0; //归零
                                ChineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
                            }
                            if (m == 0 && zeroCount < 4) {
                                ChineseStr += cnIntUnits[q];
                            }
                        }
                        ChineseStr += cnIntLast;
                        //整型部分处理完毕
                    }
                    if (DecimalNum != '') { //小数部分
                        var decLen = DecimalNum.length;
                        for (var i = 0; i < decLen; i++) {
                            var n = DecimalNum.substr(i, 1);
                            if (n != '0') {
                                ChineseStr += cnNums[Number(n)] + cnDecUnits[i];
                            }
                        }
                    }
                    if (ChineseStr == '') {
                        ChineseStr += cnNums[0] + cnIntLast + cnInteger;
                    } else if (DecimalNum == '') {
                        ChineseStr += cnInteger;
                    }
                    ChineseStr = Symbol +ChineseStr;

                    return ChineseStr;
                }
            },
            //下拉框
            select:{
                //ajax赋值
                setValue:function(initValueColumnName,initTextColumnName){
                    //有没有初始值
                    var initVal = $("span."+initValueColumnName).eq(0).html();
                    var initText = $("span."+initTextColumnName).eq(0).html();
                    if(initVal!=null){
                        $("select[name='"+initValueColumnName+"']").html('<option value="' + initVal + '">' + initText + '</option>').trigger("change")
                    }
                },
                /**
                 * 基础设置通用下拉
                 * @param moduleName 模块名,第一个字母大写,Customer
                 * @param columnName 页面上字段名 ,customerId
                 * @param voName 对象里的显示名称 customerName
                 * @param extraName 额外参数名
                 * @param extraValue 额外参数值
                 */
                configs:function (moduleName,columnName,voName,extraName,extraValue,allowNull,nullTitle,rowcount,isupdesc) {
                    if(null==rowcount){
                        rowcount = 10;
                    }
                    if(null==allowNull){
                        allowNull = false;
                    }else if(allowNull&&null==nullTitle){
                        nullTitle = '---请选择---';
                    }
                    $("select[name='"+columnName+"']").select2({
                        ajax: {
                            url: ctx + "config/config"+moduleName+"/list",    // 接口地址
                            dataType : 'json',        // 数据类型
                            type : 'POST',            // 请求方式
                            data: function (params) {
                                var obj = new Object();
                                obj[voName] = params.term;
                                obj['pageSize'] = rowcount;
                                obj['pageNum'] = 1;
                                if(isupdesc){
                                    obj['orderByColumn'] = 'updateTime';
                                    obj['isAsc'] = 'desc';
                                }
                                if(extraName){
                                    obj[extraName] = extraValue;
                                }
                                return obj;
                            },
                            processResults: function (data, params) {
                                var datas = data.rows;
                                var options = new Array();
                                if(allowNull){
                                    options.push({
                                        id : -1,
                                        text : nullTitle
                                    });
                                }
                                $(datas).each(function(idx, obj) {
                                    options.push({
                                        id : obj.id,
                                        text : obj[voName]
                                    });
                                });
                                return {
                                    results: options
                                };
                            }
                        },
                        placeholder : '请选择',        // 提示信息
                        multiple : false,            // 允许多个选项
                        allowClear : true,            // 可清空
                        language: "zh-CN"            // 提示语言
                    });
                },
                configs:function (moduleName,columnName,voName,extraName,extraValue,allowNull,nullTitle,rowcount,isupdesc) {
                    if(null==rowcount){
                        rowcount = 10;
                    }
                    if(null==allowNull){
                        allowNull = false;
                    }else if(allowNull&&null==nullTitle){
                        nullTitle = '---请选择---';
                    }
                    $("select[name='"+columnName+"']").select2({
                        ajax: {
                            url: ctx + "config/config"+moduleName+"/list",    // 接口地址
                            dataType : 'json',        // 数据类型
                            type : 'POST',            // 请求方式
                            data: function (params) {
                                var obj = new Object();
                                obj[voName] = params.term;
                                obj['pageSize'] = rowcount;
                                obj['pageNum'] = 1;
                                if(isupdesc){
                                    obj['orderByColumn'] = 'updateTime';
                                    obj['isAsc'] = 'desc';
                                }
                                if(extraName){
                                    obj[extraName] = extraValue;
                                }
                                return obj;
                            },
                            processResults: function (data, params) {
                                var datas = data.rows;
                                var options = new Array();
                                if(allowNull){
                                    options.push({
                                        id : -1,
                                        text : nullTitle
                                    });
                                }
                                $(datas).each(function(idx, obj) {
                                    options.push({
                                        id : obj.id,
                                        text : obj[voName]
                                    });
                                });
                                return {
                                    results: options
                                };
                            }
                        },
                        placeholder : '请选择',        // 提示信息
                        multiple : false,            // 允许多个选项
                        allowClear : true,            // 可清空
                        language: "zh-CN"            // 提示语言
                    });
                },
                workflow:function (project,moduleName,columnName,voName,extraName,extraValue,allowNull) {
                    if(null==allowNull){
                        allowNull = false;
                    }
                    $("select[name='"+columnName+"']").select2({
                        ajax: {
                            url: ctx + project + "/" + project + moduleName+"/list",    // 接口地址
                            dataType : 'json',        // 数据类型
                            type : 'POST',            // 请求方式
                            data: function (params) {
                                var obj = new Object();
                                obj[voName] = params.term;
                                obj['pageSize'] = 10;
                                obj['pageNum'] = 1;
                                if(extraName){
                                    obj[extraName] = extraValue;
                                }
                                return obj;
                            },
                            processResults: function (data, params) {
                                var datas = data.rows;
                                var options = new Array();
                                if(allowNull){
                                    options.push({
                                        id : -1,
                                        text : '---请选择---'
                                    });
                                }
                                $(datas).each(function(idx, obj) {
                                    options.push({
                                        id : obj.id,
                                        text : obj[voName]
                                    });
                                });
                                return {
                                    results: options
                                };
                            }
                        },
                        placeholder : '请选择',        // 提示信息
                        multiple : false,            // 允许多个选项
                        allowClear : true,            // 可清空
                        language: "zh-CN"            // 提示语言
                    });
                },
                //销售选择
                sales:function(){

                }
            },
            //文件处理
            files:{
                //新增一个地址
                addUrl:function(columnName,newUrl){
                    var tempVal = $("input[name='" + columnName + "']").val();
                    var newVal = newUrl;
                    var resVal = "";
                    if(tempVal.length>10){
                        resVal = tempVal + "," + newVal;
                    }else{
                        resVal = newVal;
                    }
                    $("input[name='" + columnName + "']").val(resVal);
                },
                //删除一个地址
                deleteUrl:function (columnName,index) {
                    var tempVal = $("input[name='" + columnName + "']").val();
                    if(tempVal.length>10){
                        var resVal = "";
                        var idx = parseInt(index.replace("init-",""));
                        var arr = tempVal.split(",");
                        for(var i=0;i<arr.length;i++){
                            var str = arr[i];
                            if(i==idx){
                                str = "rzy_tmp_img"
                            }
                            if(resVal.length>1){
                                resVal = resVal+","+str;
                            }else{
                                resVal = str
                            }
                        }
                    }
                    $("input[name='" + columnName + "']").val(resVal);
                },
                //下载特殊模板
                importTemplate:function (excelName) {
                    window.location.href = ctx + "/file/"+excelName+".xlsx";
                    //window.location.href = ctx + "rzy/rzyCommon/rzyImportTemplate/" + excelName;
                }
            },
            //提交相关
            form:{
                //原页面更新
                saveAndFill:function(url,formId,title){
                    $.ajax({
                        url: url,
                        data: $('#'+formId).serialize(),
                        type: "post",
                        success: function(result) {
                            layer.msg("保存成功,正在刷新数据请稍后……", {
                                icon: 1,
                                time: 500,
                                shade: [0.1, '#8F8F8F']
                            },function() {
                                $.modal.parentTab(title, prefix + "/edit/" + result.data.id);
                            });
                        }
                    })
                },
                //仅保存
                saveOnly:function(url,formId){
                    $.ajax({
                        url: url,
                        data: $('#'+formId).serialize(),
                        type: "post",
                        success: function(result) {
                            layer.msg("保存成功,正在刷新数据请稍后……", {
                                icon: 1,
                                time: 500,
                                shade: [0.1, '#8F8F8F']
                            },function() {
                                $.modal.parentTab(title, prefix + "/edit/" + result.data.id);
                            });
                        }
                    })
                },
                //标题颜色formTitle
                workFlowFormTitleCss:function () {
                    var statusArr = ["draft","submit","approve","reject","normal","close","file","delete"];
                    var textArr = ["草稿","提交","待审批","驳回","正常","关闭","归档","删除"];
                    var spanArr = ["label-warning","","","","label-primary","label-info","","label-danger"];
                    var status = $("input[name='status']").val();
                    for (var i=0;i<statusArr.length;i++){
                        if(statusArr[i]==status){
                            $("#formTitleLabels").addClass(spanArr[i]);
                            $("#formTitleLabels").html(textArr[i]);
                        }
                    }
                },
                //审批
                approve:function () {
                    var param = new Object();
                    param.id = $("input[name='id']").val();
                    param.status = "normal";
                    param.rzyValue1 = "approve";
                    var url = prefix + "/edit";
                    $.modal.confirm("确认要审批吗？审批前基本信息如果有修改请先保存。", function() {
                        $.operate.save(url, param);
                        /*$.operate.post(url, param, function(result) {
                            location.reload();
                        });*/
                    });
                },
                //反审
                unapprove:function () {
                    var param = new Object();
                    param.id = $("input[name='id']").val();
                    param.status = "draft";
                    param.rzyValue1 = "unapprove";
                    var url = prefix + "/edit";
                    $.modal.confirm("确认要反审吗?", function() {
                        $.operate.save(url, param);
                        /*$.operate.post(url, param, function(result) {
                            location.reload();
                        });*/
                    });
                },
                //打印
                print:function () {
                    var id = $("input[name='id']").val();
                    var url = prefix + "/print/" + id;
                    $.modal.openTab('打印', url);
                },
                //打印2
                print2:function () {
                    var id = $("input[name='id']").val();
                    var url = prefix + "/print2/" + id;
                    $.modal.openTab('打印', url);
                },
                //按钮显示
                btnCssInit:function () {
                    var pimsArr = ["SA","","","","UP","P","P","P"];//权限
                    var status = $("input[name='status']").val();
                    var btnArr = ["btnSave","btnApprove","btnUnapprove","btnPrint"];
                    var codeArr = ["S","A","U","P"];
                    var statusArr = ["draft","submit","approve","reject","normal","close","file","delete"];
                    var respims = "";
                    $("#operateBar a").hide();
                    $("#btnClose").show();
                    for (var i=0;i<statusArr.length;i++){
                        if(statusArr[i]==status){
                            respims = pimsArr[i];
                            break;
                        }
                    }
                    if(""!=respims){
                        for (var i=0;i<codeArr.length;i++){
                            if(respims.indexOf(codeArr[i])>-1){
                                $("#"+btnArr[i]).show();
                            }
                        }
                    }
                    if(status!="draft"){
                        $(".btn-group-sm").hide();
                        $("#operateBar").show();
                    }
                    //end
                },
                //提示已审批不可修改
                approveAlert:function () {
                    $.modal.alertWarning("当前状态不可编辑");
                },
                //是否可以编辑
                disabledFlag:function () {
                    var status = $("input[name='status']").val();
                    if(status!="draft"){
                        return "disabled";
                    }else{
                        return "";
                    }
                }
            },
            //打印
            print:{
                //二维码
                qrcode:function(){
                    var id = $("input[name='id']").val();
                    var serialNumber = $("input[name='serialNumber']").val();
                    var sncode = serialNumber.substring(0,3);
                    //var url = window.location.origin + prefix + "/edit/" + id;
                    var url = "rzy://" + sncode + "/" + id;
                    $.rzy.common.qrcode.build(url,150);
                },
                //条形码
                barcode:function (h) {
                    var hi = 60;
                    if(h!=null){
                        hi = h;
                    }
                    var serialNumber = $("input[name='serialNumber']").val();
                    $.rzy.common.barcode.build(serialNumber,2,hi);
                },
                //金额
                amount:function () {
                    var taxRate = $("#tax").html();
                    var taxName = $.rzy.grid.getConfigSelectText(tax_datas,taxRate,"taxName","taxRate");
                    $("#tax").html(taxName);
                    var taxRate = $.rzy.common.clearNullFloat($("input[name='taxRate']").val());
                    var amount = $.rzy.common.clearNullFloat($("input[name='amount']").val());
                    var notax = ((1 - taxRate) * amount).toFixed(2);
                    $("#notax").html(notax);
                },
                //行数据
                lines:function (titleArr,columnNameArr,list) {
                    //标题
                    for (var i=0;i<titleArr.length;i++){
                        $("#tableHeader").append('<th style="text-align: left;">'+titleArr[i]+'</th>');
                    }
                    //填数据
                    for (var i=0;i<list.length;i++){
                        var obj = list[i];
                        var line = '<tr>';
                        for (var j=0;j<columnNameArr.length;j++){
                            var val = $.rzy.common.clearNull(obj[columnNameArr[j]]);
                            if(columnNameArr[j]=="taxRate"){
                                val = $.rzy.grid.getConfigSelectText(tax_datas,val,"taxName","taxRate");
                            }
                            if(j==0){
                                line += '<td style="text-align: left;"><div><strong class="'+columnNameArr[j]+'">'+val+'</strong></div></td>';
                            }else{
                                line += '<td style="text-align: left;" class="'+columnNameArr[j]+'">'+val+'</td>';
                            }
                        }
                        line += '</tr>';
                        $("#tableLine").append(line);
                    }
                    //end
                },
                //code转text
                toDictText:function (name,datas) {
                    var code = $("#"+name+"Div").html();
                    var text = $.table.selectDictLabel(datas, code);
                    if(text==""){
                        text = "~";
                    }
                    $("#"+name+"Div").html(text);
                },
                //code转text2
                lineDictText:function (name,datas) {
                    var codeArr = $("."+name);
                    for (var i=0;i<codeArr.length;i++){
                        var code = $(codeArr[i]).html();
                        var text = $.table.selectDictLabel(datas, code);
                        if(text!=""){
                            $(codeArr[i]).html(text);
                        }
                    }
                },
                // 打印方法
                execPrint:function (zoom) {
                    if(null==zoom){
                        zoom = 1;
                    }
                    document.getElementsByTagName('body')[0].style.zoom=zoom;
                    window.print();
                },
                // 打印刷新
                reloadPrint:function () {
                    var id = $("input[name='id']").val();
                    var url = prefix + "/print/" + id;
                    $.modal.close();
                    $.modal.openTab('打印', url);
                },
                // 打印头表
                buildMainTable:function (tableId,cols,valueArr,textArr) {
                    //组装arr
                    var tbodyStr = "";
                    for(var i=0;i<textArr.length;i++){
                        var seq = i+1;
                        if(seq%cols==1){
                            tbodyStr += "<tr>";
                        }
                        tbodyStr += "<td>";
                        tbodyStr += textArr[i];
                        tbodyStr += "</td>";
                        tbodyStr += "<td class='"+valueArr[i]+"'>";
                        tbodyStr += $.rzy.common.clearNull($("input[name='"+valueArr[i]+"']").val());
                        tbodyStr += "</td>";
                        if(seq%cols==0){
                            tbodyStr += "</tr>";
                        }
                    }
                    $("#"+tableId).html(tbodyStr);
                },
                // 行1
                buildRowTable:function (tableId,valueArr,textArr,list,widthArr) {
                    var tableHtml = '';
                    //表头
                    tableHtml += '<thead>';
                    for (var i=0;i<textArr.length;i++){

                        var w = "";
                        if(widthArr[i]!=0){
                            w = widthArr[i] + "%";
                        }
                        tableHtml += '<th style="text-align: left;width: '+w+'">'+textArr[i]+'</th>';
                    }
                    tableHtml += '</thead>';
                    //表内容
                    tableHtml += '<tbody>';
                    for (var i=0;i<list.length;i++){
                        tableHtml += '<tr>';
                        for (var j=0;j<valueArr.length;j++){
                            if(valueArr[j].split(",").length>1){
                                var strk = "";
                                for(var k=0;k<valueArr[j].split(",").length;k++){
                                    if(k!=0){
                                        strk += "*";
                                    }
                                    strk += list[i][valueArr[j].split(",")[k]];
                                }
                                tableHtml += '<td class="'+valueArr[j]+'" style="word-wrap: break-all;">'+strk+'　</td>';
                            }else{
                                tableHtml += '<td class="'+valueArr[j]+'" style="word-break:break-all;">'+list[i][valueArr[j]]+'　</td>';
                            }


                        }
                        tableHtml += '</tr>';
                    }
                    tableHtml += '</tbody>';

                    $("#"+tableId).append(tableHtml);
                },
                // 行2 css
                buildRowTable2:function (tableId,valueArr,textArr,extendArr,list,widthArr) {
                    var rowsNo = valueArr.length - textArr.length + 1;
                    var closNo = textArr.length - 1;
                    var tableHtml = '';
                    //表头
                    tableHtml += '<thead>';
                    for (var i=0;i<textArr.length;i++){
                        var w = "";
                        if(widthArr[i]!=0){
                            w = widthArr[i] + "%";
                        }
                        tableHtml += '<th style="text-align: center;width: '+w+'">'+textArr[i]+'</th>';
                    }
                    tableHtml += '</thead>';
                    //表内容
                    tableHtml += '<tbody>';
                    for (var i=0;i<list.length;i++){
                        tableHtml += '<tr>';
                        for (var j=0;j<valueArr.length;j++){
                            var text = $.rzy.common.clearNull(list[i][valueArr[j]]);
                            if(j==0){
                                tableHtml += '<td rowspan="'+rowsNo+'" class="'+valueArr[j]+'">'+text+'</td>';
                            }else if(j<textArr.length){
                                tableHtml += '<td class="'+valueArr[j]+'">'+text+'　</td>';
                            }else{
                                tableHtml += '</tr><tr>';
                                tableHtml += '<td colspan="'+closNo+'" class="'+valueArr[j]+'">'+extendArr[j]+'：'+text+'　</td>';
                            }
                        }
                        tableHtml += '</tr>';
                    }
                    tableHtml += '</tbody>';
                    $("#"+tableId).append(tableHtml);
                },
                //值转换
                translationValue2Text:function (val,list,isdict,colValue,colName) {
                    var vals = $("."+val);
                    for(var i=0;i<vals.length;i++){
                        var vls = $.trim(vals.eq(i).html());
                        var txt = "";
                        if(isdict){
                            txt = $.table.selectDictLabel(list, vls);
                        }else{
                            $.each(list, function(idx, obj) {
                                if (obj[colValue] == vls) {
                                    txt = obj[colName];
                                }
                            });
                        }
                        vals.eq(i).html(txt)
                    }
                }
            }

        }
    });
})(jQuery);

