<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域管理</title>
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
			$("#name").focus();
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
	<form:form id="inputForm" modelAttribute="area" action="${ctx}/sys/area/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td  class="width-15 active"><label class="pull-right">上级区域:</label></td>
		         <td class="width-35" ><sys:treeselect id="area" name="parent.id" value="${area.parent.id}" labelName="parent.name" labelValue="${area.parent.name}"
					title="区域" url="/sys/area/treeData" extId="${area.id}" cssClass="form-control m-s" allowClear="true"/></td>
		         <td  class="width-15 active"><label class="pull-right">区域名称:</label></td>
		         <td  class="width-35" ><form:input path="name" htmlEscape="false" maxlength="50" class="form-control required"/></td>
		      </tr>
		      <tr>
		         <td  class="width-15 active"><label class="pull-right"><font color="red">*</font>区域编码:</label></td>
		         <td class="width-35" ><form:input path="code" htmlEscape="false" maxlength="50" class="form-control"/></td>
		         <td  class="width-15 active"><label class="pull-right">区域类型:</label></td>
		         <td  class="width-35" ><form:select path="type" class="form-control">
					<form:options items="${fns:getDictList('sys_area_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select></td>
		      </tr>
			  <tr>
		         <td  class="width-15 active"><label class="pull-right">备注:</label></td>
		         <td class="width-35" ><form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control"/></td>
		         <td  class="width-15 active"><label class="pull-right"></label></td>
		         <td  class="width-35" ></td>
		      </tr>
		</tbody>
		</table>
	</form:form>
</body>
</html>