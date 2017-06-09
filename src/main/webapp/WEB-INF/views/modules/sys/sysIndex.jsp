<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title>jeeqd</title>

<%@ include file="/WEB-INF/views/include/head.jsp"%>
<script src="${ctxStatic}/common/inspinia.js?v=3.2.0"></script>
<script src="${ctxStatic}/common/contabs.js"></script>
<meta name="keywords" content="jeeqd快速开发平台">
<meta name="description"
	content="jeeqd快速开发平台，采用spring mvc+mybatis+shiro+bootstrap，集成代码生成器的快速开发平台">
<script type="text/javascript">
	$(document).ready(function() {
		 if('${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}' == '天蓝主题'){
			    // 蓝色主题
			        $("body").removeClass("skin-2");
			        $("body").removeClass("skin-3");
			        $("body").addClass("skin-1");
		 }else  if('${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}' == '橙色主题'){
			    // 黄色主题
			        $("body").removeClass("skin-1");
			        $("body").removeClass("skin-2");
			        $("body").addClass("skin-3");
		 }else {
			 // 默认主题
			        $("body").removeClass("skin-2");
			        $("body").removeClass("skin-3");
			        $("body").removeClass("skin-1");
		 };
	 });
			
	</script>

</head>

<body class="fixed-sidebar full-height-layout gray-bg">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<span><img alt="image" class="img-circle"
								style="height: 64px; width: 64px;" src="${fns:getUser().photo }" /></span>
							<a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
								class="clear"> <span class="block m-t-xs"><strong
										class="font-bold">${fns:getUser().name}</strong></span> <span
									class="text-muted text-xs block">${fns:getUser().roleNames}<b
										class="caret"></b></span>
							</span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li><a class="J_menuItem" href="${ctx}/sys/user/imageEdit">修改头像</a>
								</li>
								<li><a class="J_menuItem" href="${ctx }/sys/user/info">个人资料</a>
								</li>
								<li><a class="J_menuItem" href="${ctx }/iim/contact/index">我的通讯录</a>
								</li>
								<li><a class="J_menuItem" href="${ctx }/iim/mailBox/list">信箱</a>
								</li>
								<li class="divider"></li>
								<li><a onclick="changeStyle()" href="#">切换到ACE模式</a></li>
								<li class="divider"></li>
								<li><a href="${ctx}/logout">安全退出</a></li>
							</ul>
						</div>
						<div class="logo-element">JP</div>
					</li>

					<t:menu menu="${fns:getTopMenu()}"></t:menu>

				</ul>
			</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"><i class="fa fa-bars"></i> </a>
						<form role="search" class="navbar-form-custom" method="post"
							action="search_results.html">
							<div class="form-group">
								<input type="text" placeholder="请输入您需要查找的内容 …"
									class="form-control" name="top-search" id="top-search">
							</div>
						</form>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li class="dropdown"><a class="dropdown-toggle count-info"
							data-toggle="dropdown" href="#"> <i class="fa fa-envelope"></i>
								<span class="label label-warning">${noReadCount}</span>
						</a>
							<ul class="dropdown-menu dropdown-messages">
								<c:forEach items="${mailPage.list}" var="mailBox">
									<li class="m-t-xs">
										<div class="dropdown-messages-box">

											<a href="#"
												onclick='top.openTab("${ctx}/iim/contact/index?name=${mailBox.sender.name }","通讯录", false)'
												class="pull-left"> <img alt="image" class="img-circle"
												src="${mailBox.sender.photo }">
											</a>
											<div class="media-body">
												<small class="pull-right">${fns:getTime(mailBox.sendtime)}前</small>
												<strong>${mailBox.sender.name }</strong> <a
													class="J_menuItem"
													href="${ctx}/iim/mailBox/detail?id=${mailBox.id}">
													${fns:abbr(mailBox.mail.title,50)}</a> <br> <a
													class="J_menuItem"
													href="${ctx}/iim/mailBox/detail?id=${mailBox.id}">
													${mailBox.mail.overview} </a> <br> <small
													class="text-muted"> <fmt:formatDate
														value="${mailBox.sendtime}" pattern="yyyy-MM-dd HH:mm:ss" /></small>
											</div>
										</div>
									</li>
									<li class="divider"></li>
								</c:forEach>
								<li>
									<div class="text-center link-block">
										<a class="J_menuItem"
											href="${ctx}/iim/mailBox/list?orderBy=sendtime desc"> <i
											class="fa fa-envelope"></i> <strong> 查看所有邮件</strong>
										</a>
									</div>
								</li>
							</ul></li>
						<li class="dropdown"><a class="dropdown-toggle count-info"
							data-toggle="dropdown" href="#"> <i class="fa fa-bell"></i> <span
								class="label label-primary">${count }</span>
						</a>
							<ul class="dropdown-menu dropdown-alerts">
								<li><c:forEach items="${page.list}" var="oaNotify">

										<div>
											<a class="J_menuItem"
												href="${ctx}/oa/oaNotify/view?id=${oaNotify.id}&"> <i
												class="fa fa-envelope fa-fw"></i>
												${fns:abbr(oaNotify.title,50)}
											</a> <span class="pull-right text-muted small">${fns:getTime(oaNotify.updateDate)}前</span>
										</div>

									</c:forEach></li>
								<li class="divider"></li>
								<li>
									<div class="text-center link-block">
										您有${count }条未读消息 <a class="J_menuItem"
											href="${ctx }/oa/oaNotify/self "> <strong>查看所有 </strong>
											<i class="fa fa-angle-right"></i>
										</a>
									</div>
								</li>
							</ul></li>


						<!-- 国际化功能预留接口 -->
						
						<li class="dropdown"><a id="lang-switch"
							class="lang-selector dropdown-toggle" href="#"
							data-toggle="dropdown" aria-expanded="true"> <span
								class="lang-selected"> <img class="lang-flag"
									src="${ctxStatic}/common/img/china.png" alt="中国"> <span
									class="lang-id">中国</span> <span class="lang-name">中文</span>
							</span>
						</a> <!--Language selector menu-->
							<ul class="head-list dropdown-menu with-arrow">
								<li>
									<!--English--> <a class="lang-select"> <img
										class="lang-flag" src="${ctxStatic}/common/img/china.png"
										alt="中国"> <span class="lang-id">中国</span> <span class="lang-name">中文</span>
								</a>
								</li>
								<li>
									<!--English--> <a class="lang-select"> <img
										class="lang-flag"
										src="${ctxStatic}/common/img/united-kingdom.png" alt="English">
										<span class="lang-id">EN</span> <span class="lang-name">English</span>
								</a>
								</li>
								<li>
									<!--France--> <a class="lang-select"> <img
										class="lang-flag" src="${ctxStatic}/common/img/france.png"
										alt="France"> <span class="lang-id">FR</span> <span
										class="lang-name">Français</span>
								</a>
								</li>
								<li>
									<!--Germany--> <a class="lang-select"> <img
										class="lang-flag" src="${ctxStatic}/common/img/germany.png"
										alt="Germany"> <span class="lang-id">DE</span> <span
										class="lang-name">Deutsch</span>
								</a>
								</li>
								<li>
									<!--Italy--> <a class="lang-select"> <img class="lang-flag"
										src="${ctxStatic}/common/img/italy.png" alt="Italy"> <span
										class="lang-id">IT</span> <span class="lang-name">Italiano</span>
								</a>
								</li>
								<li>
									<!--Spain--> <a class="lang-select"> <img class="lang-flag"
										src="${ctxStatic}/common/img/spain.png" alt="Spain"> <span
										class="lang-id">ES</span> <span class="lang-name">Español</span>
								</a>
								</li>
							</ul></li>
					</ul>
				</nav>
			</div>
			<div class="row content-tabs">
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="javascript:;" class="active J_menuTab"	data-id="${ctx}/home">首页</a>
					</div>
				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭操作<span class="caret"></span>
					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
						<li class="divider"></li>
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>
				<a href="${ctx}/logout" class="roll-nav roll-right J_tabExit"><i
					class="fa fa fa-sign-out"></i> 退出</a>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
					src="${ctx}/home" frameborder="0" data-id="${ctx}/home" seamless></iframe>
			</div>
			<div class="footer">
				<div class="pull-left">
					<a href="http://www.tech-qd.com">http://www.tech-qd.com</a>
					&copy; 2015-2025
				</div>
			</div>
		</div>
		<!--右侧部分结束-->


	</div>
</body>

<!-- 语言切换插件，为国际化功能预留插件 -->
<script type="text/javascript">
$(document).ready(function(){

	$("a.lang-select").click(function(){
		$(".lang-selected").find(".lang-flag").attr("src",$(this).find(".lang-flag").attr("src"));
		$(".lang-selected").find(".lang-flag").attr("alt",$(this).find(".lang-flag").attr("alt"));
		$(".lang-selected").find(".lang-id").text($(this).find(".lang-id").text());
		$(".lang-selected").find(".lang-name").text($(this).find(".lang-name").text());

	});


});
//切换到ACE模式
function changeStyle(){
   $.get('${pageContext.request.contextPath}/theme/ace?url='+window.top.location.href,function(result){   window.location.reload();});
}
</script>



<!-- 即时聊天插件  开始-->
<link href="${ctxStatic}/layer-v2.3/layim/layui/css/layui.css" type="text/css" rel="stylesheet" />
<!--  
<script type="text/javascript">
	var currentId = '${fns:getUser().loginName}';
	var currentName = '${fns:getUser().name}';
	var currentFace ='${fns:getUser().photo}';
	var url="${ctx}";
	var static_url="${ctxStatic}";
	var wsServer = 'ws://'+window.document.domain+':8668'; 

</script>
-->
<!--webscoket接口  -->
<script src="${ctxStatic}/layer-v2.3/layim/layui/layui.js"></script>

<script src="${ctxStatic}/layer-v2.3/layim/layim.js"></script>
<!-- 即时聊天插件 结束 -->

<style>
/*签名样式*/
.layim-sign-box {
	width: 95%
}

.layim-sign-hide {
	border: none;
	background-color: #F5F5F5;
}
</style>

</html>