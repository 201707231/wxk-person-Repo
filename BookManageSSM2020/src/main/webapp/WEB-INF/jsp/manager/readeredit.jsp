<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
<link rel="stylesheet" href="libs/css/edit.css">
<script type="text/javascript" src="libs/js/jquery.min.js"></script>
</head>
<body>
	
	<form action="manager/addReader" method="post">
		<c:if test='${sessionScope.info.equals("update")}'>
			<input type="hidden" name="cmd" value="update">
		</c:if>
		<c:if test='${sessionScope.info.equals("insert")}'>
			<input type="hidden" name="cmd" value="insert">
		</c:if>
<%--			<input type="hidden" name="main_id" value="${main_id}">--%>
<%--			<input type="hidden" name="vip_id" value="${vip_id}">--%>
		<div class="inputview">
		<span class="inputspan">
		<label class="inputtext">学号:</label>
		<input class="inputinput" name="reader.id" value="${reader.id}" type="text"
                <c:if test='${sessionScope.info.equals("update")}'>
                    readonly="readonly"
                </c:if>
        />
		</span>


		<span class="inputspan">
		<label class="inputtext">性名:</label>
		<input class="inputinput" name="reader.name" value="${reader.name}" type="text"/>
		</span>

		<span class="inputspan">
		<label class="inputtext">性别:</label>
		<input class="inputinput" name="reader.sex" value="${reader.sex}" type="text"/>
		</span>

		<span class="inputspan">
		<label class="inputtext">年龄:</label>
		<input class="inputinput" name="reader.age" value="${reader.age}" type="text"/>
		</span>

		<span class="inputspan">
		<label class="inputtext">电话:</label>
		<input class="inputinput" name="reader.telephone" value="${reader.telephone}" type="text"/>
		</span>

		<span class="inputspan">
		<label class="inputtext">邮箱:</label>
		<input class="inputinput" name="reader.email" value="${reader.email}" type="text"/>
<%--		<textarea rows="3" cols="20" name="introduction">--%>
<%--        </textarea>--%>
		</span>

		<span class="inputspan">
		<label class="inputtext">密码:</label>
		<input class="inputinput" name="reader.password" value="${reader.password}" type="text"/>
		</span>

<%--		<span class="inputspan">--%>
<%--		<label class="inputtext">开始时间:</label>--%>
<%--		<input class="inputinput" name="reader.start" value="${reader.start}" type="date"/>--%>
<%--		</span>--%>

<%--		<span class="inputspan">--%>
<%--		<label class="inputtext">结束时间:</label>--%>
<%--		<input class="inputinput" name="end" value="${reader.end}" type="date"/>--%>
<%--		</span>--%>

<%--		<span class="inputspan">--%>
<%--		<label class="inputtext">惩罚情况:</label>--%>
<%--		<input class="inputinput" name="punishcount" value="${reader.punishcount}" type="text"/>--%>
<%--		</span>--%>

<%--		<span class="inputspan">--%>
<%--		<label class="inputtext">借书次数:</label>--%>
<%--		<input class="inputinput" name="lendcount" value="${reader.lendcount}" type="text"/>--%>
<%--		</span>--%>
<%--		<span class="inputspan">--%>
<%--		<label class="inputtext">类别:</label>--%>
<%--		<select name="tid"class="inputselect" >--%>
<%--			<c:forEach items="${allTypes}" var="a" >--%>
<%--				<option  value="${a.tid}" <c:if test='${oneBook.type.tid}==${a.tid}'>selected</c:if>>${a.tname}</option>--%>
<%--			</c:forEach>--%>
<%--		</select>--%>
<%--		</span>--%>

		</div>
		<div class="inputbtpanel ">
		<button class="seachbt1" type="submit" >保存</button>
		</div>
	</form>
	
</body>
</html>