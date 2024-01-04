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
	
	<form action="manager/addBook" method="post">
		<c:if test='${sessionScope.info.equals("update")}'>
			<input type="hidden" name="cmd" value="update">
		</c:if>
		<c:if test='${sessionScope.info.equals("insert")}'>
			<input type="hidden" name="cmd" value="insert">
		</c:if>
<%--			<input type="hidden" name="main_id" value="${main_id}">--%>
<%--			<input type="hidden" name="vip_id" value="${vip_id}">--%>
		<input type="hidden" name="id" value="${oneBook.id}">
		<div class="inputview">
		<span class="inputspan">
		<label class="inputtext">ISBN:</label>
		<input class="inputinput" name="book.isbn" value="${oneBook.isbn}" type="text"
               <c:if test='${sessionScope.info.equals("update")}'>
				   readonly="readonly"
               </c:if>
        />
		</span>

		<span class="inputspan">
		<label class="inputtext">书名:</label>
		<input class="inputinput" name="book.name" value="${oneBook.name}" type="text"/>
		</span>

		<span class="inputspan">
		<label class="inputtext">出版社:</label>
		<input class="inputinput" name="book.press" value="${oneBook.press}" type="text"/>
		</span>

		<span class="inputspan">
		<label class="inputtext">作者:</label>
		<input class="inputinput" name="book.author" value="${oneBook.author}" type="text"/>
		</span>

			<span class="inputspan">
		<label class="inputtext">数量:</label>
		<input class="inputinput" name="book.num" value="${oneBook.num}" type="text"/>
		</span>

			<span class="inputspan">
		<label class="inputtext">介绍:</label>
		<input class="inputinput" name="book.introduction" value="${oneBook.introduction}" type="text"/>
<%--		<textarea rows="3" cols="20" name="introduction">--%>
<%--        </textarea>--%>
		</span>

		<span class="inputspan">
		<label class="inputtext">出版时间:</label>
		<input class="inputinput" name="book.ctime" value="${oneBook.ctime}" type="date"/>
		</span>

		<span class="inputspan">
		<label class="inputtext">类别:</label>
		<select name="tid"class="inputselect" >
			<c:forEach items="${allTypes}" var="a" >
				<option  value="${a.tid}" <c:if test='${oneBook.type.tid}==${a.tid}'>selected</c:if>>${a.tname}</option>
			</c:forEach>
		</select>
		</span>

		</div>
		<div class="inputbtpanel ">
		<button class="seachbt1" type="submit" >保存</button>
		</div>
	</form>
	
</body>
</html>