<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主子表管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body class="hideScroll">
	<form:form id="inputForm" modelAttribute="testDataMain" action="${ctx}/test/testDataMain/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">归属用户：</label>
					</td>
					<td class="width-35">
							<sys:treeselect id="user" name="user.id" value="${testDataMain.user.id}" labelName="user.name" labelValue="${testDataMain.user.name}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="form-control input-sm " allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">归属部门：</label>
					</td>
					<td class="width-35">
							<sys:treeselect id="office" name="office.id" value="${testDataMain.office.id}" labelName="office.name" labelValue="${testDataMain.office.name}"
								title="部门" url="/sys/office/treeData?type=2" cssClass="form-control input-sm " allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">归属区域：</label>
					</td>
					<td class="width-35">
							<sys:treeselect id="area" name="area.id" value="${testDataMain.area.id}" labelName="area.name" labelValue="${testDataMain.area.name}"
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
							<form:select path="sex" class="form-control max-width-250 ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="col-sm-3 control-label">加入日期：</label>
					</td>
					<td class="width-35">
							<input id="inDate" name="inDate" type="text" readonly="readonly" maxlength="20" class="laydate-icon form-control layer-date input-sm"
								value="<fmt:formatDate value="${testDataMain.inDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
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
		<div class="tabs-container">
			  <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">业务数据子表：</a> </li>
        	  </ul>
			<div class="tab-content">
				<div id="tab-1" class="tab-pane active">
					<a class="btn btn-white btn-sm" onclick="addRow('#testDataChildList', testDataChildRowIdx, testDataChildTpl);testDataChildRowIdx = testDataChildRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th class="hide"></th>
							<th>名称</th>
							<th>备注信息</th>
							<th width="10">&nbsp;</th>
						</tr>
					</thead>
					<tbody id="testDataChildList">
					</tbody>
				</table>
				<script type="text/template" id="testDataChildTpl">//<!--
					<tr id="testDataChildList{{idx}}">
						<td class="hide">
							<input id="testDataChildList{{idx}}_id" name="testDataChildList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
							<input id="testDataChildList{{idx}}_delFlag" name="testDataChildList[{{idx}}].delFlag" type="hidden" value="0"/>
						</td>
						<td>
							<input id="testDataChildList{{idx}}_name" name="testDataChildList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="100" class="input-small "/>
						</td>
						<td>
							<input id="testDataChildList{{idx}}_remarks" name="testDataChildList[{{idx}}].remarks" type="text" value="{{row.remarks}}" maxlength="255" class="input-small "/>
						</td>
						<shiro:hasPermission name="test:testDataMain:edit"><td class="text-center" width="10">
							{{#delBtn}}<span class="close" onclick="delRow(this, '#testDataChildList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
						</td></shiro:hasPermission>
					</tr>//-->
				</script>
				<script type="text/javascript">
					var testDataChildRowIdx = 0, testDataChildTpl = $("#testDataChildTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
					$(document).ready(function() {
						var data = ${fns:toJson(testDataMain.testDataChildList)};
						for (var i=0; i<data.length; i++){
							addRow('#testDataChildList', testDataChildRowIdx, testDataChildTpl, data[i]);
							testDataChildRowIdx = testDataChildRowIdx + 1;
						}
					});
				</script>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>