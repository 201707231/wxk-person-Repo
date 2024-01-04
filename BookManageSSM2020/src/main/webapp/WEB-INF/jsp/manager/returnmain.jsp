<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
<link href="libs/layui/css/layui.css" rel="stylesheet">
<script type="text/javascript" src="libs/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="libs/layui/layui.all.js"></script>
<script type="text/javascript" src="libs/js/util.js"></script>
<script type="text/javascript" src="libs/js/mylayer.js"></script>
<link href="css/css.css" rel="stylesheet">
<style type="text/css">
.layui-table{
margin-left: 30px !important;
}
.wrap{
	width: 150px; //设置需要固定的宽度
white-space: nowrap; //不换行
text-overflow: ellipsis; //超出部分用....代替
overflow: hidden; //超出隐藏
}

</style>
   </head>
<body>
	<div class="demoTable">
<script type="text/javascript">
function returnBook(id){
	if(confirm("确认归还图书?")){
		location.href='manager/returnBook?id='+id;
	}
}
function renewBook(id){
	if(confirm("确认续借图书?")){
		location.href='manager/renewBook?id='+id;
	}
}
</script>
<form action="manager/findBorrowByTag" method="post">
<input name="rid">
<button type="submit"  class="layui-btn" >查询</button>
</form>
</div>

<div class="layui-form layui-border-box layui-table-view" >
	<div class="layui-table-box">
		<div class="layui-table-body layui-table-main" >
			<table  class="layui-table ">
			<thead>
					<tr>
	<td style="width: 100px;">编号</td>
	<td style="width: 100px;">图书编号</td>
	<td style="width: 100px;">读者编号</td>
    <td style="width: 100px;">借阅时间</td>
	<td style="width: 100px;">应归还时间</td>
	<td style="width: 100px;">续借次数</td>
	<td style="width: 150px;">状态</td>
	<td style="width: 100px;">实际归还时间</td>
	<td style="width: 100px;">管理员编号</td>
	<td>操作</td></tr>
				</thead>
				<tbody>

	<c:forEach items="${allBorrow}" var="row" varStatus="v">
	<tr>
	<td>${row.id}</td>
	<td>${row.bid}</td>
	<td>${row.rid}</td>
    <td>${row.borrowtime}</td>
	<td>${row.returntime}</td>
	<td>${row.renewtimes}</td>
	<td>${row.state}</td>
	<td>${row.practicaltime}</td>
	<td>${row.mid}</td>
	<td>
	<a href="javascript:;" onclick="returnBook(${row.id});">归还</a>
	<a href="javascript:;" onclick="renewBook(${row.id});">续借</a>
	</td></tr>
	
	</c:forEach>
	<c:if test="${sessionScope.s.equals('all')}">
	<tr>
		<td>
			&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;共${totalCount}条记录&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;
			第${pageCur}页&nbsp;&nbsp;
			<c:url var="url_pre" value="manager/toReturnBook">
				<c:param name="pageCur" value="${pageCur - 1 }"/>
			</c:url>
			<c:url var="url_next" value="manager/toReturnBook">
				<c:param name="pageCur" value="${pageCur + 1 }"/>
			</c:url>
			<!-- 第一页没有上一页 -->
			<c:if test="${pageCur != 1 }">
				<a href="${url_pre}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:if>
			<!-- 最后一页，没有下一页 -->
			<c:if test="${pageCur != totalPage && totalPage != 0}">
				<a href="${url_next}">下一页</a>
			</c:if>
		</td>
	</tr>
	</c:if>
	</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>