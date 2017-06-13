<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定时任务管理</title>
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
		});
	</script>
</head>
<body class="hideScroll">
	<form:form id="inputForm" modelAttribute="sysScheduleJob" action="${ctx}/sys/sysScheduleJob/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td  class="width-15 active">	<label class="pull-right">任务名称:</label></td>
		         <td class="width-35" ><form:input path="jobName" htmlEscape="false" maxlength="255" class="form-control required"/></td>
		         <td  class="width-15 active">	<label class="pull-right">任务分组:</label></td>
		          <td  class="width-35" ><form:input path="jobGroup" htmlEscape="false" maxlength="255" class="form-control required"/></td>
		      </tr>
		       <tr>
		         <td  class="width-15 active">	<label class="pull-right">任务状态:</label></td>
		         <td class="width-35" >
			         <form:select path="jobStatus" class="form-control m-b">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('task_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
		         <td  class="width-15 active">	<label class="pull-right">cron表达式:</label></td>
		          <td  class="width-35" ><form:input path="cronExpression" htmlEscape="false" maxlength="255" class="form-control required"/></td>
		      </tr>
		       <tr>
		         <td  class="width-15 active">	<label class="pull-right">方法名称：</label></td>
		         <td class="width-35" ><form:input path="methodName" htmlEscape="false" maxlength="255" class="form-control required "/></td>
		         <td  class="width-15 active">	<label class="pull-right">执行class类:</label></td>
		         <td  class="width-35" ><form:input path="beanClass" htmlEscape="false" maxlength="255" class="form-control required "/></td>
		      </tr>
		       <tr>
		         <td  class="width-15 active">	<label class="pull-right">是否异步执行:</label></td>
		         <td class="width-35" >
			        <form:select path="isConcurrent" class="form-control m-b">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
		         <td  class="width-15 active">	<label class="pull-right">spring_id：</label></td>
		          <td  class="width-35" ><form:input path="springId" htmlEscape="false" maxlength="255" class="form-control "/></td>
		      </tr>
		       <tr>
		           <td  class="width-15 active">	<label class="pull-right">描述:</label></td>
		         <td class="width-35" ><form:textarea path="description" htmlEscape="false" maxlength="255" class="form-control "/></td>
		    
		         <td  class="width-15 active">	<label class="pull-right">备注信息：</label></td>
		          <td  class="width-35" ><form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="255" class="form-control "/></td>
		      </tr>
		   </tbody>
	   </table>   
	</form:form>
</body>
</html>