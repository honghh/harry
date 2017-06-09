<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>树结构管理</title>
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
	<form:form id="inputForm" modelAttribute="testTree" action="${ctx}/test/testTree/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
			
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">上级父级编号:</label> </td>
					<td class="width-35">
						<sys:treeselect id="parent" name="parent.id" value="${testTree.parent.id}" labelName="parent.name" labelValue="${testTree.parent.name}"
							title="父级编号" url="/test/testTree/treeData" extId="${testTree.id}" cssClass="form-control m-s" allowClear="true" notAllowSelectParent="true"/>
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
					<td class="width-15 active"><label class="col-sm-3 control-label">排序：</label>
					</td>
					<td class="width-35">
							<form:input path="sort" htmlEscape="false" maxlength="10" class="form-control  max-width-250  digits"/>
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