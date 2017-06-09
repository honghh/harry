<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		     WinMove();
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
   <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
            <blockquote class="text-info" style="font-size:14px">jeeqd是一款基于代码生成器的智能快速开发平台，引领新开发模式(智能开发\在线开发\插件开发)， 可以帮助解决Java项目80%的重复工作，让开发更多关注业务逻辑。既能快速提高开发效率，帮助公司节省人力成本，同时又不失灵活性。 
    jeeqd快速开发宗旨是：简单功能由代码生成器生成使用; 复杂业务采用表单自定义，业务流程使用工作流来实现、扩展出任务接口，供开发编写业务逻辑。 实现了流程任务节点和任务接口的灵活配置，既保证了公司流程的保密性，又减少了开发人员的工作量
    			<br/>
               <br>jeeqd采用了目前极为流行的扁平化响应式的设计风格，可以完美的兼容电脑，pad，手机等多个平台。前端UI采用了<a target="_blank" href="http://wrapbootstrap.com/preview/WB0R5L90S">INSPINIA</a>(点击访问）为原型开发，jeeqd v2.0发布时作者将bootstrap升级到了最新的3.3.4。
                <br>…………
            </blockquote>

            <hr>
        </div>
    </div>
      
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-4">

                <div class="ibox float-e-margins">
                     <div class="ibox-title">
                        <h5>jeeqd 技术特点</h5> 
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="index.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="index.html#">选项1</a>
                                </li>
                                <li><a href="index.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <p> jeeqd开发平台采用 SpringMVC + MyBatis + BootStrap + Apache Shiro + Ehcache 开发组件 的基础架构,采用面向声明的开发模式， 基于泛型编写极少代码即可实现复杂的数据展示、数据编辑、
表单处理等功能，再配合代码生成器的使用,将J2EE的开发效率提高5倍以上，可以将代码减少50%以上。

                        <ol>
						<li>代码生成器，支持多种数据模型,根据表生成对应的Entity,Service,Dao,Action,JSP等,增删改查/排序/导出导入Excel/权限控制/功能生成直接使用</li>
						<li>基础用户权限，强大数据权限，操作权限控制精密细致，对所有管理链接都进行权限验证，可控制到按钮，对指定数据集权限进行过滤</li>
						<li>简易Excel导入导出，支持单表导出和一对多表模式导出，生成的代码自带导入导出Excel功能</li>
						<li>查询过滤器，查询功能自动生成，后台动态拼SQL追加查询条件；支持多种匹配方式（全匹配/模糊查询/包含查询/不匹配查询） </li>
						<li>UI标签库，针对WEB UI进行标准封装，页面统一采用UI标签实现功能：封装了bootstrap风格的table，检索，部门选择 ，人员选择等常用控件。</li>
						<li>国际化，支持多语言，多语言系统切换（测试中，即将发布)</li>
						<li>集成百度Echarts，实现曲线图，柱状图，数据等报表</li>
						<li>系统日志监控，详细记录操作日志，可支持追查表修改日志</li>
						<li>WebSocket集成：集成在线聊天系统。</li>
						<li>提供常用工具类封装，日志、缓存、验证、字典、组织机构等，常用标签（taglib），获取当前组织机构、字典等数据。</li>
						<li>工作流组件（开发中）</li>
						<li>连接池监视：监视当期系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。</li>
						<li>提供APP接口，可以快速和移动APP整合。</li>
						
						<li>快速体验</li>
						<li>支持多种浏览器: IE, 火狐, Google 等浏览器访问速度都很快</li>
						<li>支持数据库: Mysql,Oracle数据库的支持，但不限于数据库，平台留有其它数据库支持接口等</li>
						
						<li>要求JDK1.6+</li>
                        </ol>
                    </div>
                </div>
              
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                     <div class="ibox-title">
                        <h5>升级日志</h5> <span class="label label-primary">K+</span>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="index.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="index.html#">选项1</a>
                                </li>
                                <li><a href="index.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="panel-body">
                            <div class="panel-group" id="version">
                            
                            	<div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v2.6">v2.6</a><code class="pull-right">2017.2.18更新</code>
                                            </h5>
                                    </div>
                                    <div id="v2.6" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>这是一个维护版本。</li>
                                            	<li>修复不能修改密码的bug</li>
                                            	<li>修复ace下不显示layim的bug。</li>
                                                <li>修复一对多生成子表时，子表不支持gridselect的bug</li>
                                                <li>修复layim发送文件在ie下的bug</li>
                                            	<li>修复ace菜单构建bug</li>
                                            	<li>优化登录，减少了关联sql查询，大幅减少了首次登录耗时</li>
                                                <li>.........</li>
                                                <li>让我们共同期待3.x系列的到来，这是一个完全风格体验完全不同的框架。</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                            
                            	<div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v2.5">v2.5</a><code class="pull-right">2016.10.8更新</code>
                                            </h5>
                                    </div>
                                    <div id="v2.5" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>升级代码生成器，生成的代码增加表单校验功能。</li>
                                            	<li>修复代码生成器v2.3版本的bug，修复乱码功能。</li>
                                            	<li>优化代码生成器体验，增加错误校验等。</li>
                                                <li>去除dialog的竖向滚动条</li>
                                                <li>修复升级layer2.3的bug。</li>
                                            	<li>升级layim1.0到layim2.0。</li>
                                            	<li>支持自定义签名。</li>
                                                <li>支持表情，文件，群聊，群聊聊天记录。</li>
                                                <li>支持自定义聊天群组，添加移除群组人员。</li>
                                                <li>支持离线消息。</li>
                                                <li>....</li>
                                                <li>layim1.0是免费插件，layim2.0是商业授权，你仍可选择免费使用layim1.0，如果需要使用layim2.0，你需要购买一个授权，价格不贵，希望大家尊重知识版权，给开源作者一点点鼓励，才能持续给大家提供优秀的开源软件,<a href="http://layim.layui.com/?from=layer" target="_blank">layIM官网</a>。</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                            
                            	<div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v2.4">v2.4</a><code class="pull-right">2016.06.27更新</code>
                                            </h5>
                                    </div>
                                    <div id="v2.4" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>升级layer到2.4版本。</li>
                                                <li>解决layer弹出窗无法自适应手机的问题，jeeqd以完美支持移动端。</li>
                                                <li>修复layer弹窗弹出时窗口闪动的bug。</li>
                                                <li>修复分页bug。</li>
                                                <li>....</li>
                                                <li>封装echarts标签，提供快捷开发报表的功能。</li>
                                                <li>感谢大家的持续关注，如果你有更好的建议，请直接联系我。</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                            	<div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v2.3">v2.3</a><code class="pull-right">2016.05.17更新</code>
                                            </h5>
                                    </div>
                                    <div id="v2.3" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>修复初次登录，菜单加载慢的问题,加载速度从10秒优化到2秒。</li>
                                                <li>修复list导入Excel文档为空的bug。</li>
                                                <li>修复360浏览器（7.1版本）提交表单失败的bug。</li>
                                                <li>修复短信发送bug。</li>
                                                <li>修复发送站内信时，无法添加图片的bug。</li>
                                                <li>增加我的日程功能。</li>
                                                <li>修复代码生成器生成的树结构，删除子节点时，会误删除父节点的bug。</li>
                                                <li>删除百度统计的代码</li>
                                                <li>修复oracle数据库脚本</li>
                                                <li>优化登录页的显示风格</li>
                                                <li>增加我的日程功能</li>
                                                <li>增加代码生成器支持生成富文本编辑框的功能</li>
                                                <li>增加代码生成器支持自定义grid选择框的功能。</li>
                                                <li>....</li>
                                                <li>下一个版本，主要关注功能是报表和activity工作流，至于数据权限，我暂时还没想到好的解决方案。</li>
                                                <li>感谢大家的持续关注，如果你有更好的建议，请直接联系我。</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                            		<div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v2.2">v2.2Beta版</a><code class="pull-right">2016.03.13更新</code>
                                            </h5>
                                    </div>
                                    <div id="v2.2" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>这是目前为止最重要的一次升级。</li>
                                                <li>新增功能：cpu，内存，jvm性能检测预警工具，短信群发功能，邮件群发功能，ace主题切换功能，表单构建器功能等功能。</li>
                                                <li>修正了大量2.1beta版本的bug，以及页面性能优化，具体参照网站的更新报告</li>
                                                <li>升级代码生成器功能，移除（可插入，可编辑）2个配置项，新增(表单）配置项，使代码生成器更加简单易懂，提高了代码生成器的实用性。</li>
                                                <li>....</li>
                                                <li>富文本编辑器，自定义java对象功能，表格的行权限和列权限，上下菜单风格，echarts图表生成器等功能预计在2.3版本推出。</li>
                                                <li>感谢大家的持续关注，如果你有更好的建议，请直接联系我。</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                                
                            	<div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v2.0">v2.1Beta版</a><code class="pull-right">2016.02.25更新</code>
                                            </h5>
                                    </div>
                                    <div id="v2.0" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>这个版本是一个bug修复版本</li>
                                                <li>修正zTree显示bug</li>
                                                <li>移除google的cdn依赖，解决加载慢的问题</li>
                                                <li>修正代码生成器拖拽bug</li>
                                                <li>修正站内信bug</li>
                                                <li>....</li>
                                                <li>仪表盘，定时任务，自定义表单，邮箱短信等等新功能，因为多个环境对应以及代码校验完善的原因将在2.2推出，所有正式用户均可免费升级。</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v2.0">v2.0</a><code class="pull-right">2016.01.15更新</code>
                                            </h5>
                                    </div>
                                    <div id="v2.0" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <ol>
                                                <li>修复IE不兼容，首页出现滚动条</li>
                                                <li>修复validate插件bug</li>
                                                <li>提供基于WebSocket的即时聊天系统</li>
                                                <li>增加注册忘记密码功能</li>
                                                <li>优化了主题的显示效果</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                                   <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.9</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                   <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.8</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                           		<div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="">********</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.1">v1.1</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.0</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                               
                </div>
            </div>
            </div>
            </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>商业授权 </h5>
                    </div>
                    <div class="ibox-content">
                        <p>jeeqd是一款收费开源的快速开发平台，购买后您将获得以下内容</p>
                        <ol>
                            <li>源码(带注释)；</li>
                            <li>代码生成器；</li>
                            <li>开发者文档；</li>
                            <li>永久的免费升级服务；</li>
                            <li>授权许可；</li>
                            <li>付费二次开发服务；</li>
                            <li>……</li>
                        </ol>
                        <hr>
                    

                    </div>
                </div>
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>联系信息</h5>

                    </div>
                    <div class="ibox-content">
                        <p><i class="fa fa-send-o"></i> 网址：<a href="http://www.tech-qd.com" target="_blank">http://www.tech-qd.com</a>
                        </p>
                        <p><i class="fa fa-qq"></i> QQ：<a href="http://wpa.qq.com/msgrd?v=3&uin=183865800&site=qq&menu=yes" target="_blank">183865800</a>
                        </p>
                        <p><i class="fa fa-weixin"></i> 微信：<a href="javascript:;">kuaiquqi</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
	</div>
</body>
</html>