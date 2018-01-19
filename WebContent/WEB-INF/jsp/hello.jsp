<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head></head>
<body>
	<%-- <h1>1.使用ModelAndView传数据</h1>
	<h1>是否成功：${success }</h1>
	<h1>提示信息：${message }</h1>
	
	<h1>2.使用ModelMap/Model传数据</h1>
	<h1>姓名：${name }</h1>
	<h1>工资：${salary }</h1>
	
	<h1>3.使用@ModelAttribute传数据</h1>
	<h1>年龄：${age }</h1>
	<h1>性别：${sex }</h1>
	<h1>电话：${phone }</h1>
	
	<h1>4.使用Session</h1>
	<h1>管理员：${admin }</h1> --%>

	<h1>5.costs</h1>
	<table border="1" cellspacing="0" cellpadding="2">
		<tr>
			<td>ID</td>
			<td>资费名</td>
			<td>基本时长</td>
			<td>基本费用</td>
			<td>单位费用</td>
			<td>状态</td>
			<td>描述</td>
			<td>创建时间</td>
			<td>开通时间</td>
			<td>资费类型</td>
		</tr>
		<c:forEach var="cost" items="${costs}">
			<tr>
				<td>${cost.costId}</td>
				<td>${cost.name}</td>
				<td>${cost.baseDuration}</td>
				<td>${cost.baseCost}</td>
				<td>${cost.unitCost}</td>
				<td><c:choose>
						<c:when test="${cost.status==0 }">开通</c:when>
						<c:when test="${cost.status==1 }">暂停</c:when>
					</c:choose></td>
				<td>${cost.descr}</td>
				<td>${cost.creatime}</td>
				<td>${cost.startime}</td>
				<td><c:choose>
						<c:when test="${cost.costType==1 }">包月</c:when>
						<c:when test="${cost.costType==2 }">套餐</c:when>
						<c:when test="${cost.costType==3 }">计时</c:when>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>