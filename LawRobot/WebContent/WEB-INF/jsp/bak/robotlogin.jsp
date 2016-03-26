<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
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
<title>LawRobot-Admin</title>
<link rel="shortcut icon" href="${ctx }/img/robot_space.png">
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${ctx}/css/matrix-login.css" />
<link href="${ctx}/font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>
    <body>
        <div id="loginbox">            
            <form id="loginform" class="form-vertical" action="http://themedesigner.in/demo/matrix-admin/index.html">
				 <div class="control-group normal_text"> <h3>LawRobot-Admin</h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" placeholder="Username" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" placeholder="Password" />
                        </div>
                    </div>
                </div>
                <div class="control-group" >
                	 <div class="controls" >
                        <div class="main_input_box" >
                    		<a type="submit" href="index.html" style="width: 85%;margin: 0 auto;" class="btn btn-success btn-block" > Login</a>
                    	</div>
                    </div>
                </div>
                <div class="form-actions"></div>
            </form>
        </div>
        
        <script src="${ctx}/js/jquery.min.js"></script>  
        <script src="${ctx}/js/matrix.login.js"></script> 
    </body>

</html>