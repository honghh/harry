<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通知管理</title>
	<meta name="decorator" content="default"/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
<div class="ibox">
<div class="ibox-title">
		<h5>通知列表 </h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
				<i class="fa fa-wrench"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#">选项1</a>
				</li>
				<li><a href="#">选项2</a>
				</li>
			</ul>
			<a class="close-link">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
		<!-- 查询条件 -->
	<div class="row">
	<div class="col-sm-12">
		<form:form id="searchForm" modelAttribute="oaNotify" action="${ctx}/oa/oaNotify/${oaNotify.self?'self':''}" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>标题：</span>
				<form:input path="title" htmlEscape="false" maxlength="200"  class=" form-control input-sm"/>
			
			<span>类型：</span>
				<form:select path="type"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('oa_notify_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			<c:if test="${!requestScope.oaNotify.self}"><span>状态：</span>
				<form:radiobuttons path="status" class="i-checks" items="${fns:getDictList('oa_notify_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</c:if>
		
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<c:if test="${!requestScope.oaNotify.self}">
			<shiro:hasPermission name="oa:oaNotify:add">
				<table:addRow url="${ctx}/oa/oaNotify/form" title="通知"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="oa:oaNotify:edit">
			    <table:editRow url="${ctx}/oa/oaNotify/form" id="contentTable"  title="通知" width="800px" height="700px"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="oa:oaNotify:del">
				<table:delRow url="${ctx}/oa/oaNotify/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="oa:oaNotify:import">
				<table:importExcel url="${ctx}/oa/oaNotify/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="oa:oaNotify:export">
	       		<table:exportExcel url="${ctx}/oa/oaNotify/export"></table:exportExcel><!-- 导出按钮 -->
	       </shiro:hasPermission>
	      
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		 </c:if>
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
		</div>
	</div>
	</div>
	
	
	
	
	<table id="contentTable" class="table table-striped table-bordered  table-hover table-condensed  dataTables-example dataTable no-footer">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th>标题</th>
				<th>类型</th>
				<th>状态</th>
				<th>查阅状态</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaNotify">
			<tr>
				<td> <input type="checkbox" id="${oaNotify.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看通知', '${ctx}/oa/oaNotify/${requestScope.oaNotify.self?'view':'form'}?id=${oaNotify.id}','800px', '700px')">
					${fns:abbr(oaNotify.title,50)}
				</a></td>
				<td>
					${fns:getDictLabel(oaNotify.type, 'oa_notify_type', '')}
				</td>
				<td>
					${fns:getDictLabel(oaNotify.status, 'oa_notify_status', '')}
				</td>
				<td>
					<c:if test="${requestScope.oaNotify.self}">
						${fns:getDictLabel(oaNotify.readFlag, 'oa_notify_read', '')}
					</c:if>
					<c:if test="${!requestScope.oaNotify.self}">
						${oaNotify.readNum} / ${oaNotify.readNum + oaNotify.unReadNum}
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${oaNotify.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<c:if test="${!requestScope.oaNotify.self}">
					<shiro:hasPermission name="oa:oaNotify:view">
						<a href="#" onclick="openDialogView('查看通知', '${ctx}/oa/oaNotify/form?id=${oaNotify.id}','800px', '700px')" class="btn btn-info btn-xs btn-circle" ><i class="fa fa-search-plus"></i></a>
					</shiro:hasPermission>
					<shiro:hasPermission name="oa:oaNotify:edit">
    					<a href="#" onclick="openDialog('修改通知', '${ctx}/oa/oaNotify/form?id=${oaNotify.id}','800px', '700px')" class="btn btn-success btn-xs btn-circle" ><i class="fa fa-edit"></i></a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="oa:oaNotify:del">
						<a href="${ctx}/oa/oaNotify/delete?id=${oaNotify.id}" onclick="return confirmx('确认要删除该通知吗？', this.href)"   class="btn btn-danger btn-xs btn-circle"><i class="fa fa-trash"></i></a>
					</shiro:hasPermission>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>