<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://www.tech-qd.com/"/>
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<!-- 引入jquery插件 -->
<script src="${ctxStatic}/jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery/jquery-migrate-1.1.1.min.js" type="text/javascript"></script>



<!-- 引入依赖的第三方插件 -->
	<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${ctxStatic}/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${ctxStatic}/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="${ctxStatic}/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="${ctxStatic}/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<script src="${ctxStatic}/ace/assets/js/ace-extra.js"></script>

		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${ctxStatic}/ace/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<script src="${ctxStatic}/ace/assets/js/bootstrap.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="${ctxStatic}/ace/assets/js/excanvas.js"></script>
		<![endif]-->
		<script src="${ctxStatic}/ace/assets/js/jquery-ui.custom.js"></script>
		<script src="${ctxStatic}/ace/assets/js/jquery.ui.touch-punch.js"></script>
		<script src="${ctxStatic}/ace/assets/js/jquery.easypiechart.js"></script>
		<script src="${ctxStatic}/ace/assets/js/jquery.sparkline.js"></script>

		<!-- ace scripts -->
		<script src="${ctxStatic}/ace/assets/js/ace/elements.scroller.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/elements.colorpicker.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/elements.fileinput.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/elements.typeahead.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/elements.wysiwyg.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/elements.spinner.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/elements.treeview.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/elements.wizard.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/elements.aside.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.ajax-content.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.touch-drag.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.sidebar.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.sidebar-scroll-1.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.submenu-hover.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.widget-box.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.settings.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.settings-rtl.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.settings-skin.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.widget-on-reload.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.searchbox-autocomplete.js"></script>
		
		
		
		<!-- the following scripts are used in demo only for onpage help and you don't need them -->
		<link rel="stylesheet" href="${ctxStatic}/ace/assets/css/ace.onpage-help.css" />
		<link rel="stylesheet" href="${ctxStatic}/ace/docs/assets/js/themes/sunburst.css" />

		<script type="text/javascript"> ace.vars['base'] = '..'; </script>
		<script src="${ctxStatic}/ace/assets/js/ace/elements.onpage-help.js"></script>
		<script src="${ctxStatic}/ace/assets/js/ace/ace.onpage-help.js"></script>
		<script src="${ctxStatic}/ace/docs/assets/js/rainbow.js"></script>
		<script src="${ctxStatic}/ace/docs/assets/js/language/generic.js"></script>
		<script src="${ctxStatic}/ace/docs/assets/js/language/html.js"></script>
		<script src="${ctxStatic}/ace/docs/assets/js/language/css.js"></script>
		<script src="${ctxStatic}/ace/docs/assets/js/language/javascript.js"></script>





<!-- 引入依赖的第三方插件 -->
<script src="${ctxStatic}/slimscroll/jquery.slimscroll.min.js"></script>


<!-- <link href="${ctxStatic}/jquery-select2/3.5.4/select2.css" rel="stylesheet" />
<link href="${ctxStatic}/jquery-select2/3.5.4/select2-bootstrap.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-select2/3.5.4/select2.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-select2/3.5.4/select2_locale_zh-CN.js" type="text/javascript"></script>-->
<script src="${ctxStatic}/jquery-validation/1.14.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-validation/1.14.0/localization/messages_zh.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/pace/pace.min.js"></script>
<script src="${ctxStatic}/metisMenu/jquery.metisMenu.js"></script>
<link href="${ctxStatic}/iCheck/custom.css" rel="stylesheet" />
<script src="${ctxStatic}/iCheck/icheck.min.js"></script>
<script src="${ctxStatic}/iCheck/icheck-init.js"></script>
<link href="${ctxStatic}/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
<script src="${ctxStatic}/dataTables/jquery.dataTables.js"></script>
<script src="${ctxStatic}/dataTables/dataTables.bootstrap.js"></script>

<!-- 引入自定义文件 -->
<link href="${ctxStatic}/ace/tab.css" type="text/css" rel="stylesheet" />

<!-- 引入layer插件 -->
<script src="${ctxStatic}/layer-v2.3/layer/layer.js"></script>
<script src="${ctxStatic}/layer-v2.3/layer/laydate/laydate.js"></script>

<!--引入webuploader-->
<link rel="stylesheet" type="text/css" href="${ctxStatic}/webuploader-0.1.5/webuploader.css">
<script type="text/javascript" src="${ctxStatic}/webuploader-0.1.5/webuploader.js"></script>


<!-- 引入自定义文件 -->
<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/content.js" type="text/javascript"></script>
<script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}';</script>








