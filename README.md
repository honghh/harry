**项目说明** 

**具有如下特点** 
- 友好的代码结构及注释，便于阅读及二次开发
- 实现前后端分离，通过token进行数据交互，前端再也不用关注后端技术
- 灵活的权限控制，可控制到页面或按钮，满足绝大部分的权限需求
- 完善的代码生成机制，可在线生成entity、xml、dao、service代码，减少70%以上的开发任务
- 引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
- 引入云存储服务，已支持：七牛云、阿里云、腾讯云等
- 引入swagger文档支持，方便编写API接口文档
<br> 

**项目结构** 
```
Harry
│
├─common 公共模块
│  ├─exception 异常处理
│  └─utils 工具类
│ 
├─config 配置信息
│ 
├─modules 功能模块
│  ├─oss 文件服务模块
│  └─sys 权限模块
│ 
├─PlatformApplication 项目启动类
│  
├──resources 
│  └─mapper SQL对应的XML文件

```
<br> 


**技术选型：** 
- 核心框架：Spring Boot 2.1.7
- 安全框架：Security 
- 持久层框架：Mybatis-Plus 3.0
- 数据库连接池：Druid 1.0
- 日志管理：SLF4J 1.7、Log4j 
<br> 


 **后端部署**
- 通过git下载源码
- idea、eclipse需安装lombok插件，不然会提示找不到entity的get set方法
- 创建数据库harry，数据库编码为UTF-8
- 执行db/mysql.sql文件，初始化数据
- 修改application-dev.yml，更新MySQL账号和密码
- Eclipse、IDEA运行PlatformApplication.java，则可启动项目
- Swagger注解路径：http://localhost:9001/swagger-ui.html

<br> 

 **前端部署**
 - 本项目是前后端分离的，还需要部署前端，才能运行起来
 - 前端下载地址：https://github.com/honghh/harry-vue
 - 前端部署文档：待填充
 - 前端部署完毕，就可以访问项目了，账号：admin，密码：123456
 
 <br>

 **项目演示**
- 演示地址：http://www.honghh.top
- 账号密码：admin/admin
<br> 
