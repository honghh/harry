<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			 //外部js调用
	        laydate({
	            elem: '#beginInDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
	        laydate({
	            elem: '#endInDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
		});
	</script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
<div class="ibox">
<div class="ibox-title">
		<h5>单表</h5>
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
	<form:form id="searchForm" modelAttribute="testData" action="${ctx}/test/testData/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>归属用户：</span>
				<sys:treeselect id="user" name="user.id" value="${testData.user.id}" labelName="user.name" labelValue="${testData.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="form-control input-sm" allowClear="true" notAllowSelectParent="true"/>
			<span>归属部门：</span>
				<sys:treeselect id="office" name="office.id" value="${testData.office.id}" labelName="office.name" labelValue="${testData.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="form-control input-sm" allowClear="true" notAllowSelectParent="true"/>
			<span>归属区域：</span>
				<sys:treeselect id="area" name="area.id" value="${testData.area.id}" labelName="area.name" labelValue="${testData.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="form-control input-sm" allowClear="true" notAllowSelectParent="true"/>
			<span>名称：</span>
				<form:input path="name" htmlEscape="false" maxlength="100" class="form-control"/>
			<span>性别：</span>
				<form:radiobuttons path="sex" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks"/>
			<span>加入日期：</span>
				<input id="beginInDate" name="beginInDate" type="text" readonly="readonly" maxlength="20" class="laydate-icon form-control layer-date input-sm" 
					value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd"/>" />
				<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
				<input id="endInDate" name="endInDate" type="text" readonly="readonly" maxlength="20" class=" laydate-icon form-control layer-date input-sm" 
					value="<fmt:formatDate value="${testData.endInDate}" pattern="yyyy-MM-dd"/>" />
		 </div>	
		 </form:form>
		<br/>
		</div>
		</div>
		 <!-- 工具栏 -->
		 <div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
					<shiro:hasPermission name="test:testData:add">
						<table:addRow url="${ctx}/test/testData/form" title="单表"></table:addRow> <!-- 添加按钮 -->
					</shiro:hasPermission>
					<shiro:hasPermission name="test:testData:del">
						<table:delRow url="${ctx}/test/testData/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 --> 
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
				<th> <input type="checkbox" class="i-checks"></th>
				<th>归属用户</th>
				<th>归属部门</th>
				<th>归属区域</th>
				<th>名称</th>
				<th>性别</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testData">
			<tr>
				<td> 
				<input type="checkbox" id="${testData.id}" class="i-checks"></td>
				<td><a href="#" onclick="openDialogView('查看', '${ctx}/test/testData/form?id=${testData.id}','800px', '500px')">
					${testData.user.name}
				</a></td>
				<td>
					${testData.office.name}
				</td>
				<td>
					${testData.area.name}
				</td>
				<td>
					${testData.name}
				</td>
				<td>
					${fns:getDictLabel(testData.sex, 'sex', '')}
				</td>
				<td>
					<fmt:formatDate value="${testData.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${testData.remarks}
				</td>
				<td>
					<shiro:hasPermission name="test:testData:view">
						<a href="#" onclick="openDialogView('查看', '${ctx}/test/testData/form?id=${testData.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="test:testData:edit">
    					<a href="#" onclick="openDialog('修改', '${ctx}/test/testData/form?id=${testData.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="test:testData:del">
						<a href="${ctx}/test/testData/delete?id=${testData.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- 分页代码 -->
	<table:page page='${page}'></table:page>
	<br/> <br/>
		</div>
	</div>
</div>
</body>
</html>