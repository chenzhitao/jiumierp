# 小象智慧印刷厂ERP

#### 介绍

小象智慧ERP管理系统是由多个印刷厂ERP项目成功上线实施打磨而成的产品，适合ERP细分领域印刷厂采购、生产、销售、库存、财务等管理，功能齐全，产品成熟，
可以直接部署实施上线使用，代码工整简洁，非常适合个人或者企业二次开发。

#### 具体功能就不一一介绍了，都在体验环境里面，登录查看即可；
地址： http://erp.weixinai.cn/jmerp/login
账户：admin / admin123

#### 技术选型
1. 系统环境
Java EE 8、
Servlet 3.0、
Apache Maven 3

2. 主框架
Spring Boot 2.2.x、
Spring Framework 5.2.x、
Apache Shiro 1.7

3. 持久层
Apache MyBatis 3.5.x、
Hibernate Validation 6.0.x、
Alibaba Druid 1.2.x

4. 视图层
Bootstrap 3.3.7、
Thymeleaf 3.0.x

#### 使用说明
1.  需要使用请联系管理员微信： yubang1010

![输入图片说明](jmerp-master/src/200.png)

#### 内置功能

1.  销售管理：包含报价、销售订单、送货、退货等功能的记录管理。

2.  生产管理：本系统核心功能，包含根据销售订单生产、报产、入库等一系列生产环节的操作。

3.  采购管理：记录管理采购原材料。

4.  外发管理：生产过程中需要外包的工序进行管理。

5.  库存管理：对采购原材料、生产的成品，送货退货等进出仓库数据进行管理。

6.  财务管理：对当前生产过程中的成本进行记录和计件计算，其他功能可定制。

7.  报表分析：为当前系统中的零散数据进行直观的可视化展示，本模块提供部分功能，可根据用户需求定制化报表。 

8.  基础配置：业务管理员操作，对业务功能的常量，基础数据进行维护。

9.  系统管理：系统管理员操作，对系统的人员、权限、菜单等各项功能进行配置。


#### 应用环境安装

打包工程文件

在ruoyi项目的bin目录下执行package.bat打包Web工程，生成war/jar包文件。

然后会在项目下生成target文件夹包含war或jar

部署工程文件

1、jar部署方式

使用命令行执行：java –jar erp.jar 

2、war部署方式

ruoyi/pom.xml中的packaging修改为war，放入tomcat服务器webapps

<packaging>war</packaging>
		
<!-- 单应用排除内置tomcat -->	

<exclusions>

<exclusion>

<artifactId>spring-boot-starter-tomcat</artifactId>

<groupId>org.springframework.boot</groupId>

</exclusion>

</exclusions>


