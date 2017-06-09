<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
			$("#value").focus();
			 validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			 //外部js调用
	        laydate({
	            elem: '#inDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
		});
	</script>
</head>
<body class="hideScroll">
	<form:form id="inputForm" modelAttribute="testData" action="${ctx}/test/testData/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
			
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">归属用户：</label>
					</td>
					<td class="width-35">
							<sys:treeselect id="user" name="user.id" value="${testData.user.id}" labelName="user.name" labelValue="${testData.user.name}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="form-control input-sm " allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">归属部门：</label>
					</td>
					<td class="width-35">
							<sys:treeselect id="office" name="office.id" value="${testData.office.id}" labelName="office.name" labelValue="${testData.office.name}"
								title="部门" url="/sys/office/treeData?type=2" cssClass="form-control input-sm " allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">归属区域：</label>
					</td>
					<td class="width-35">
							<sys:treeselect id="area" name="area.id" value="${testData.area.id}" labelName="area.name" labelValue="${testData.area.name}"
								title="区域" url="/sys/area/treeData" cssClass="form-control input-sm " allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">名称：</label>
					</td>
					<td class="width-35">
							<form:input path="name" htmlEscape="false" maxlength="100" class="form-control  max-width-250 "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">性别：</label>
					</td>
					<td class="width-35">
							<form:radiobuttons path="sex" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">加入日期：</label>
					</td>
					<td class="width-35">
							<input id="inDate" name="inDate" type="text" readonly="readonly" maxlength="20" class="laydate-icon form-control layer-date input-sm"
								value="<fmt:formatDate value="${testData.inDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">备注信息：</label>
					</td>
					<td class="width-35">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control  max-width-250 "/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>