<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<title>Insert title here</title>
</head>
<body>
<script>
function callback(result)
{
	alert(result.length);
	var resultStr = "<table border='1'>";
	resultStr += "<tr align='center'>";
	resultStr += "<td>VINUM</td>";
	resultStr += "<td>VINAME</td>";
	resultStr += "<td>VIDESC</td>";
	resultStr += "<td>VIADDRESS</td>";
	resultStr += "<td>VIPHONE</td>";
	resultStr += "<td>VICREDAT</td>";
	resultStr += "<td>VICRETIM</td>";
	resultStr += "</tr>";
	for(var i = 0, max = result.length; i < max; i++)
	{
		var vendor = result[i];
		resultStr += "<tr align='center'>";
		resultStr += "<td>" + vendor.viNum + "</td>";
		resultStr += "<td>" + vendor.viName + "</td>";
		resultStr += "<td>" + vendor.viDesc + "</td>";
		resultStr += "<td>" + vendor.viAddress + "</td>";
		resultStr += "<td>" + vendor.viPhone + "</td>";
		resultStr += "<td>" + vendor.viCredat + "</td>";
		resultStr += "<td>" + vendor.viCretim + "</td>";
		resultStr += "</tr>";
	}
	resultStr += "</table>";
	$("#v_div").html(resultStr);
}
$(document).ready(function()
{
	$("input[type='button']").click(function()
	{
		var au = new AjaxUtil("vendor/list");
		au.setCallbackSuccess(callback);
		au.send();
	})	
})
</script>
<br><p/><br><p/><br><p/>
<form action="${rootPath}/vendor/list" method="post">
<input type="button" value="전송"/>
<div id="v_div"></div>
</form>
</body>
</html>