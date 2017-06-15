<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en"
	class="app js no-touch no-android chrome no-firefox no-iemobile no-ie no-ie10 no-ie11 no-ios no-ios7 ipad">
<head>
<meta name="decorator" content="default"/>
<script src="${ctxStatic}/echarts-2.2.7/build/dist/echarts-all.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${ctxStatic}/common/systemInfo.js"></script>
	<script type="text/javascript">
	function modifySer(key){
		$.ajax({
	        async: false,
	        url: "${ctx}/monitor/modifySetting?"+key+"="+$("#"+key).val(),
	        dataType: "json",
	        success: function (data) {
	    	    if(data.success){
	    	    	alert("更新成功！");
	    	    }else{
	    	    	alert("更新失败！");
	    	    }
	        }
		});
	}
	</script>
</head>
<body class="" style="">
	<div class="wrapper wrapper-content">
			<div class="row animated fadeInRight">
				<div class="col-sm-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<i class="fa fa-rss-square"></i> 实时监控
						</div>
	
						<div class="panel-body">
							<table style="width: 100%;">
								<tr>
									<td width="33.3%"><div id="main_one" style="height: 240px;"></div></td>
									<td width="33.3%"><div id="main_two" style="height: 240px;"></div></td>
									<td width="33.3%"><div id="main_three"
											style="height: 240px;"></div></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="row animated fadeInRight">
				<div class="col-sm-12">
				   <div class="panel panel-success">
					<div class="panel-heading">
						<i class="fa fa-briefcase"></i> 警告设置
					</div>
					<table class="table table-striped table-bordered table-hover"
						width="100%" style="vertical-align: middle;">
						<thead>
							<tr style="background-color: #faebcc; text-align: center;">
								<td width="100">名称</td>
								<td width="100">参数</td>
								<td width="205">预警设置</td>
								<td width="375">邮箱设置</td>
							</tr>
						</thead>
						<tbody id="tbody">
							<tr>
								<td style='padding-left: 10px; text-align: left;vertical-align: middle;'>CPU</td>
								<td style='padding-left: 10px; text-align: left;vertical-align: middle;'>当前使用率：<span
									id="td_cpuUsage" style="color: red;">50</span> %
								</td>
								<td align="center">
									<table>
										<tr>
											<td>使用率超出</td>
											<td><input class='inputclass' name='cpu' id='cpu'
												type='text' value='${cpu}' /> %,</td>
											<td>发送邮箱提示 <a class='btn btn-info'
												href='javascript:void(0)' onclick='modifySer("cpu");'>
													修改 </a></td>
										</tr>
									</table>
								</td>
								<td rowspan='3' align="center" style="vertical-align: middle;"><input
									class='inputclass' style='width: 250px; height: 32px;'
									name='toEmail' id='toEmail' type='text'
									value='${toEmail}' /><a class='btn btn-info'
									href='javascript:void(0)' onclick='modifySer("toEmail");'>
										修改 </a></td>
							</tr>
							<tr>
								<td style='padding-left: 10px; text-align: left;vertical-align: middle;'>服务器内存</td>
								<td style='padding-left: 10px; text-align: left;vertical-align: middle;'>当前使用率：<span
									id="td_serverUsage" style="color: blue;">50</span> %
								</td>
								<td align="center">
									<table>
										<tr>
											<td>使用率超出</td>
											<td><input class='inputclass' name='ram' id='ram'
												type='text' value='${ram}' /> %,</td>
											<td>发送邮箱提示 <a class='btn btn-info'
												href='javascript:void(0)' onclick='modifySer("ram");'>
													修改 </a></td>
										</tr>
									</table>

								</td>
							</tr>
							<tr>
								<td style='padding-left: 10px; text-align: left;vertical-align: middle;'>JVM内存</td>
								<td style='padding-left: 10px; text-align: left;vertical-align: middle;'>当前使用率：<span
									id="td_jvmUsage" style="color: green;">50</span> %
								</td>
								<td align="center">
									<table>
										<tr>
											<td>使用率超出</td>
											<td><input class='inputclass' name='jvm' id='jvm'
												type='text' value='${jvm}' /> %,</td>
											<td>发送邮箱提示 <a class='btn btn-info'
												href='javascript:void(0)' onclick='modifySer("jvm");'>
													修改 </a></td>
										</tr>
									</table>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
			</div>
			<div class="row animated fadeInRight">
				<div class="col-sm-6">
					<div class="panel panel-info">
						<div class="panel-heading">
							<i class="fa fa-th-list"></i> 服务器信息
						</div>
						<div class="panel-body" style="padding: 0px">
							<div style="height: 370px;" class="embed-responsive embed-responsive-16by9">
							  <iframe class="embed-responsive-item" src="${ctx}/monitor/systemInfo"></iframe>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
				  <div class="panel panel-danger">
					<div class="panel-heading">
						<i class="fa fa-fire"></i> 实时监控
					</div>

					<div class="panel-body">
						<div id="main" style="height: 370px;"></div>
					</div>
				 </div>
				</div>
			</div>
	</div>
</body>
</html>
