<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生成方案管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="ibox">
			<ul class="nav nav-tabs">
				<li class="active"><a href="${ctx}/gen/genScheme/">生成方案列表</a></li>
				<shiro:hasPermission name="gen:genScheme:edit"><li><a href="${ctx}/gen/genScheme/form">生成方案添加</a></li></shiro:hasPermission>
			</ul>
			<div class="ibox-content">
				<sys:message content="${message}" />
				<!-- 查询条件 -->
				<div class="row">
					<div class="col-sm-12">
						<form:form id="searchForm" modelAttribute="genScheme"
							action="${ctx}/gen/genScheme/" method="post" class="form-inline">
							<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
							<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
							<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();" />
							<!-- 支持排序 -->
							<div class="form-group">
								<span>方案名称 ：</span>
								<form:input path="name" htmlEscape="false" maxlength="50" class="form-control" />
							</div>
						</form:form>
						<br />
					</div>
				</div>
				<!-- 工具栏 -->
				<div class="row">
					<div class="col-sm-12">
						<div class="pull-left">
							<shiro:hasPermission name="gen:genScheme:del">
								<table:delRow url="${ctx}/gen/genScheme/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
							</shiro:hasPermission>
							<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新">
								<i class="glyphicon glyphicon-repeat"></i> 刷新
							</button>
						</div>
						<div class="pull-right">
							<button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()">
								<i class="fa fa-search"></i> 查询
							</button>
							<button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"> 
								<i class="fa fa-refresh"></i> 重置
							</button>
						</div>
					</div>
				</div>
				<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
					<thead>
						<tr>
							<th><input type="checkbox" class="i-checks"></th>
							<th>方案名称</th>
							<th>生成模块</th>
							<th>模块名</th>
							<th>功能名</th>
							<th>功能作者</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.list}" var="genScheme">
						<tr>
							<td><input type="checkbox" id="${genScheme.id}"	class="i-checks"></td>
							<td><a href="${ctx}/gen/genScheme/form?id=${genScheme.id}">${genScheme.name}</a></td>
							<td>${genScheme.packageName}</td>
							<td>${genScheme.moduleName}${not empty genScheme.subModuleName?'.':''}${genScheme.subModuleName}</td>
							<td>${genScheme.functionName}</td>
							<td>${genScheme.functionAuthor}</td>
							<td>
								<shiro:hasPermission name="gen:genScheme:edit">
				    				<a href="${ctx}/gen/genScheme/form?id=${genScheme.id}" class="btn btn-success btn-xs"><i class="fa fa-edit"></i>修改</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="gen:genScheme:del">
									<a href="${ctx}/gen/genScheme/delete?id=${genScheme.id}" onclick="return confirmx('确认要删除该生成方案吗？', this.href)" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i>删除</a>
								</shiro:hasPermission>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<!-- 分页代码 -->
			<table:page page="${page}"></table:page>
			<br /> <br />
		</div>
	</div>
</div>
</body>
</html>
