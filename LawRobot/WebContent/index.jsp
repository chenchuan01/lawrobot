<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html style="height: 100%">
<header>
<title>welcome</title>
<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" href="css/style.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/front/front.js"></script>
<script type="text/javascript">
$(function(){
	setTimeout('indexInit()',1000);
});
function indexInit(){
	thinkId = thinking();
	var temp = 0;
	var tempId="";
	tempId=setInterval(function(){
			temp++;
			if(temp>=3){
				clearInterval(tempId);
				clearThink();
				setRobotImg('answered');
				setTimeout('window.location.href = "${pageContext.request.contextPath}/front.do";',800);
			}
		},800);
}
</script>
</header>
<body style="height: 100%;background: #d9edf7; overflow: hidden;">
<div style="height: 100%;text-align: center;">
	<div style="margin-top: 15%"><img id="robotImg" alt="loading" src="img/robot_disconnect.png"/></div>	
</div>
</body>
</html>