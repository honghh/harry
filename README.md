**项目说明** 

**具有如下特点** 
- 友好的代码结构及注释，便于阅读及二次开发
- 实现**前后端分离**，通过token进行数据交互，前端再也不用关注后端技术
- 灵活的**权限控制**，可控制到页面或按钮，满足绝大部分的权限需求
- 完善的代码生成机制，可在线生成entity、xml、dao、service代码，减少70%以上的开发任务
- 引入swagger接口文档服务的工具（Knife4j），方便编写API接口文档
<br> 

**项目结构** 
```
Harry
|____Dockerfile
|____harry-biz
| |____pom.xml
| |____src
| | |____main
| | | |____resources
| | | | |____mapper  #SQL对应的XML文件
| | | | | |____sys #权限模块
| | | | |____logback-spring.xml
| | | | |____application-prod.yml
| | | | |____application-test.yml
| | | | |____application-dev.yml
| | | |____java
| | | | |____cn
| | | | | |____harry
| | | | | | |____config #配置信息
| | | | | | |____common #公共模块
| | | | | | | |____exption #异常处理
| | | | | | | |____enums #枚举
| | | | | | | |____annotation
| | | | | | | |____utils
| | | | | | | |____constant
| | | | | | | |____api
| | | | | | | |____exception
| | | | | | | |____interceptor
| | | | | | |____sys #权限模块
| | | | | | |____oss #文件服务模块
|____pom.xml
|____README.md
|____.gitignore
|____doc
| |____sql
| | |____harry_20200225.sql #对应SQL文本
|____harry-platform
| |____pom.xml
| |____src
| | |____main
| | | |____resources
| | | | |____banner.txt
| | | | |____application.yml
| | | |____java
| | | | |____cn
| | | | | |____harry
| | | | | | |____config
| | | | | | |____component
| | | | | | | |____aspect
| | | | | | |____controller
| | | | | | | |____sys
| | | | | | | |____oss
| | | | | | |____PlatformApplication.java #项目启动类
```
<br> 


**技术选型：** 
- 核心框架：Spring Boot 2.1.7
- 安全框架：Security 2.1.7
- 持久层框架：Mybatis-Plus 3.0
- 数据库连接池：Druid 1.1.13
- 日志管理：SLF4J 1.7、Log4j 
<br> 


 **后端部署**
- 通过git下载源码

> - **GitHub:** https://github.com/honghh/harry.git
> - **Gitee:** https://gitee.com/honghh/harry.git

- idea、eclipse需安装lombok插件，不然会提示找不到entity的get set方法
- 创建数据库harry，数据库编码为UTF-8
- 执行doc/sql/harry.sql文件，初始化数据
- 修改application-dev.yml，更新MySQL账号和密码
- Eclipse、IDEA运行PlatformApplication.java，则可启动项目
- Swagger注解路径：http://localhost:9001/doc.html

<br> 

 **前端部署**
 - 本项目是前后端分离的，还需要部署前端，才能运行起来
 - 前端下载地址：
> - **GitHub:** https://github.com/honghh/harry-vue.git
> - **Gitee:** https://gitee.com/honghh/harry-vue.git
 - 前端部署文档：-
 - 前端部署完毕，就可以访问项目了，账号：admin，密码：123456
 
 <br>

 **配套代码生成器**
> - **GitHub:** https://github.com/honghh/harry-generator.git
> - **Gitee:** https://gitee.com/honghh/harry-generator.git
 <br>
 **项目演示**
- 演示地址：http://www.honghh.top
- 账号密码：admin/123456

<br> 

 **联系作者**
 
- 微信公众号：  ![image](https://img-blog.csdnimg.cn/20200225085656400.png)
- 通过微信公众号联系我呀！！！

<br> 

 **项目截图**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200429141727289.png)
![image](https://img-blog.csdnimg.cn/20200429141843953.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200429142640352.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200429142734107.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200429143051475.png)

 **项目接口截图 地址：http://localhost:9001/doc.html**

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020030308402777.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200303084054976.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200303084139434.png)