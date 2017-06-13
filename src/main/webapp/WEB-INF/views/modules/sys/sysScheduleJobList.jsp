<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定时任务管理</title>
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
		function changeJobStatus(jobId, cmd) {
			showWaitMsg();
			$.ajax({
				type : "POST",
				async : false,
				dataType : "JSON",
				cache : false,
				url : "${ctx}/sys/sysScheduleJob/changeJobStatus",
				data : {
					jobId : jobId,
					cmd : cmd
				},
				success : function(data) {
					hideWaitMsg();
					if (data.flag) {

						location.reload();
					} else {
						alert(data.msg);
					}

				}//end-callback
			});//end-ajax
		}
		function showWaitMsg(msg) {
			if (msg) {

			} else {
				msg = '正在处理，请稍候...';
			}
			var panelContainer = $("body");
			$("<div id='msg-background' class='datagrid-mask' style=\"display:block;z-index:10006;\"></div>").appendTo(panelContainer);
			var msgDiv = $("<div id='msg-board' class='datagrid-mask-msg' style=\"display:block;z-index:10007;left:50%\"></div>").html(msg).appendTo(
					panelContainer);
			msgDiv.css("marginLeft", -msgDiv.outerWidth() / 2);
		}
		function hideWaitMsg() {
			$('.datagrid-mask').remove();
			$('.datagrid-mask-msg').remove();
		}
	</script>
</head>
<bodyclass="gray-bg">
<div class="wrapper wrapper-content">
<div class="ibox">
<div class="ibox-title">
		<h5>定时任务管理列表 </h5>
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
	<form:form id="searchForm" modelAttribute="sysScheduleJob" action="${ctx}/sys/sysScheduleJob/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->		
<!-- 		<ul class="ul-form"> -->
<!-- 			<li><label>任务名称：</label> -->
<%-- 				<form:input path="jobName" htmlEscape="false" maxlength="255" class="input-medium"/> --%>
<!-- 			</li> -->
<!-- 			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
<!-- 			<li class="clearfix"></li> -->
<!-- 		</ul> -->
			<div class="form-group">
				<span>任务名称：</span>
					<form:input path="jobName" htmlEscape="false" maxlength="255" class="form-control"/>
			 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sys:sysScheduleJob:edit">
				<table:addRow url="${ctx}/sys/sysScheduleJob/form" title="添加"></table:addRow><!-- 增加按钮 -->
<%-- 				<table:delRow url="${ctx}/sys/sysScheduleJob/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 --> --%>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
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
				<th>任务名称</th>
				<th>任务分组</th>
				<th>任务状态</th>
				<th>cron表达式</th>
				<th>描述</th>
				<th>是否同步</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:sysScheduleJob:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysScheduleJob">
			<tr>
				<td> <input type="checkbox" id="${sysScheduleJob.id}" class="i-checks"></td>
				<td>
					${sysScheduleJob.jobName}
				</td>
				<td>
					${sysScheduleJob.jobGroup}
				</td>
				<td>
					${fns:getDictLabel(sysScheduleJob.jobStatus, 'task_state', '')}
				</td>
				<td>
					${sysScheduleJob.cronExpression}
				</td>
				<td>
					${sysScheduleJob.description}
				</td>
				<td>
					${fns:getDictLabel(sysScheduleJob.isConcurrent, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${sysScheduleJob.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysScheduleJob.remarks}
				</td>
				<shiro:hasPermission name="sys:sysScheduleJob:edit"><td>
				<a href="#" onclick="openDialogView('查看定时任务', '${ctx}/sys/sysScheduleJob/form?id=${sysScheduleJob.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
				<a href="#" onclick="openDialog('修改定时任务', '${ctx}/sys/sysScheduleJob/form?id=${sysScheduleJob.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
					<a href="${ctx}/sys/sysScheduleJob/delete?id=${sysScheduleJob.id}" onclick="return confirmx('确认要删除该定时任务吗？', this.href)" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					<c:if test="${sysScheduleJob.jobStatus =='0' }">
						<a href="javascript:;" onclick="changeJobStatus('${sysScheduleJob.id}','start')" class="btn btn-info btn-xs"><i class="fa fa-adjust"></i>启用</a>
					</c:if>
					<c:if test="${sysScheduleJob.jobStatus =='1' }">
						<a href="javascript:;" onclick="changeJobStatus('${sysScheduleJob.id}','stop')" class="btn btn-danger btn-xs"><i class="fa fa-ban"></i>停止</a>
					</c:if>
				</td></shiro:hasPermission>
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