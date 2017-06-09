<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<table class="table table-striped table-bordered ">
	<c:forEach items="${histoicFlowList}" var="act">
		<c:if test="${act.histIns.endTime !=null}">
			<td id="start" style="width:80px;text-align:center;background-color:#5BA276;"><span style="color:#FFFFFF;">${act.histIns.activityName}>></span></td>
		</c:if>
		<c:if test="${act.histIns.endTime ==null}">
			<td id="start" style="width:80px;text-align:center;background-color:red;"><span style="color:#FFFFFF;">${act.histIns.activityName}</span></td>
		</c:if>
	</c:forEach>
</table>