<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../bak/common/taglib.jspf" %>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
	response.addHeader("pragma", "no-cache");
	response.addHeader("cache-control", "no-cache");
	response.addHeader("expires", "0");
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>LawHelper</title>
<link rel="shortcut icon" href="${ctx }/img/robot_space.png">
<!-- vendor -->
<link rel="stylesheet" href="${ctx }/assets/vendor/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${ctx }/css/style.css"/>
</head>
<body style="height: 100%;overflow:auto;">
<div class="container-fluid" style="height: 100%">
	<div class="row" style="height: 100%">
		<div class="col-md-12" style="height: 100%">
				<div class="panel panel-info"style="height: 100%;padding: 5px;background: #d9edf7">
				  <div class="panel-heading" style="height: 10%;padding:0px;">
				  	
				  	<h3 style="display: inline-block;"><i class="glyphicon glyphicon-book"></i>&nbsp;&nbsp;LawHelper</h3>
				  	<!-- <h1 style="display: inline-block;">法律小助手</h1> -->
				  </div>
				  <div class="panel-body" style="height: 85%;">
				    <div class="col-md-9" style="height: 100%;">
				    	<!-- 消息区 -->
				    	<div class="panel"style="height: 78%;">
				    		 <div class="panel-body" style="height:100%">
				    		 	<div style="width:100%">
								    <select  id="zone_index" class="form-control" style="width: 49.8%;display: inline-block;" >
								    	<c:forEach items="${sys:getListVal('ZONE_INDEX') }" var="zoneItem">
									  		<c:set var="zone_index" value="${fn:split(zoneItem,':')[0] }"/>
									  		<c:set var="zone_content" value="${fn:split(zoneItem,':')[1] }"/>
									  		<option value="${zone_index }">${zone_content }</option>
									  	</c:forEach>
								    </select>
								    <select  id="filed_index" class="form-control" style="width: 49.8%;display: inline-block;" >
								    	<c:forEach items="${sys:getListVal('FILED_INDEX') }" var="filedItem">
									  		<c:set var="filed_index" value="${fn:split(filedItem,':')[0] }"/>
									  		<c:set var="filed_content" value="${fn:split(filedItem,':')[1]}"/>
									  		<option value="${filed_index }">${filed_content }</option>
									  	</c:forEach>
								    </select>
				    		 	</div>
				    		 	<div id="msgAre" class="form-control"  style="height:90%;overflow-y:auto;word-break:break-all;font-size: 12px;">
				    		 	</div>
				    		 </div>
				    	</div>
				    	<!-- 编辑区 -->
				    	<div class="panel"style="height: 18%;">
				    		 <div class="panel-body" style="height: 100%;">
				    		 	<div class="col-md-10"  style="height: 100%;">
				    		 		<textarea id="question" style="height: 100%;width: 100%;overflow-y:auto;word-break:break-all; resize:none;padding: 4px;" 
				    		 			placeholder="请输入咨询问题" class="form-control"></textarea>
				    		 	</div>
				    		 	<div class="col-md-2"  style="height: 100%;">
				    		 		<button id="send" class="btn btn-primary btn-block" style="height:100%;">Send</button>
				    		 	</div>
				    		 </div>
				    	</div>
				    	
				    </div>
				    <!-- 介绍区 -->
				    <div class="col-md-3" style="height: 100%;">
				    	<div class="panel"style="height: 100%;">
				    		 <div class="panel-body" style="height: 100%;text-align: center;">
				    		 	<!-- 机器人图片 -->
				    		 	<div id="robotImgArea" style="height: 50%;padding-top: 25%">
				    		 		<img id="robotImg" style="display: inline-block;" alt="LawRobot" src="${ctx }/img/robot_space.png">
				    		 	</div>
				    		 	<!-- 机器人介绍 -->
				    		 	<div style="height: 50%;">
				    		 		<div style="height: 80%;">
				    		 			 <label>
										  	<i class="glyphicon glyphicon-info-sign"></i>&nbsp;&nbsp;About Me
										 </label>
						    		 	<div id="robotIntrduce" class="form-control" style="height:100%;padding-top:20%;overflow-y:auto;word-break:break-all;">
						    		 		${sys:getStrVal('LAWROBOT_DES') }
						    		 	</div>
							    	</div>
				    		 	</div>
				    		 </div>
				    	</div>
				    </div>
				  </div>
				  
				    <div class="panel-footer" style="height: 5%;text-align: center;background-color: #d9edf7;color: #31708f;">
						  <p>Copyright&copy;2016 法律小助手 -xxxx专业-xxx级-xxx同学</p>
				    </div>
				</div>
		</div>
	</div>
</div>
<!-- vendor -->
<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx }/js/bootstrap-typeahead.js"></script>
<script type="text/javascript" src="${ctx }/js/util/ajax.js"></script>
<script type="text/javascript" src="${ctx }/js/front/front.js"></script>
<script type="text/javascript">
$(function(){
	loadFontPage();
});
</script>
</body>
</html>