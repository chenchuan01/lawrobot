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
<link rel="stylesheet" href="${ctx }/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx }/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${ctx }/css/matrix-style.css" />
<link href="${ctx }/font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>
<body>

<!--Header-part-->
<div id="header">
  <h3 >
  <a href="admin.html" style="color:#fff;">LawHelper<br/>
  Admin</a>
  </h3>
</div>
<!--close-Header-part--> 

<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text">欢迎，xxx</span><b class="caret"></b></a>
      <ul class="dropdown-menu">
       <li class="divider"></li>
        <li>
        	<a href="#"><i class="icon-check"></i> 修改密码</a>
        </li>
      </ul>
    </li>
    <li class="">
    	<a title="" href="login.html"><i class="icon icon-share-alt"></i> <span class="text">退出登录</span></a>
    </li>
  </ul>
</div>
<!--close-top-serch--> 

<!--sidebar-menu-->

<div id="sidebar">
<a href="#" class="visible-phone"><i class="icon icon-home"></i></a>
  <ul>
    <li ><a href="admin.html"><i class="icon icon-home"></i> <span>问题列表</span></a> </li>
    <li class="submenu"> <a href="#"><i class="icon icon-list"></i> <span>系统功能</span></a>
      <ul>
        <li><a href="form-common.html">系统用户</a></li>
        <li><a href="form-validation.html">系统配置</a></li>
      </ul>
    </li>
  </ul>
</div>
<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="admin.html" title="问题列表" class="tip-bottom"><i class="icon-home"></i>问题列表</a></div>
  </div>
  <div  class="quick-actions_homepage">
    <ul class="quick-actions">
      <li class="bg_lb"> <a href="#"> <i class="icon-list"></i>问题列表</a> </li>
      <li class="bg_lg"> <a href="#"> <i class="icon-user"></i> 系统用户</a> </li>
      <li class="bg_ly"> <a href="#"> <i class="icon-cog"></i> 系统配置</a> </li>
    </ul>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
      	<table class="table table-bordered" style="text-align: center; background: #fff;">
      		<thead>
      			<tr>
      				<th>NO.</th><th>关键字</th><th>回答</th><th>操作</th>
      			</tr>
      		</thead>
      		<tbody>
      			<tr>
      				<td>1</td><td>你/是/谁</td><td>我是法律小助手</td><td><a class="btn btn-warning btn-sm"><i class="icon icon-edit"></i></a></td>
      			</tr>
      			<tr>
      				<td>2</td><td>你/是/谁</td><td>我是法律小助手</td><td><a class="btn btn-warning btn-sm"><i class="icon icon-edit"></i></a></td>
      			</tr>
      			<tr>
      				<td>3</td><td>你/是/谁</td><td>我是法律小助手</td><td><a class="btn btn-warning btn-sm"><i class="icon icon-edit"></i></a></td>
      			</tr>
      			<tr>
      				<td>4</td><td>你/是/谁</td><td>我是法律小助手</td><td><a class="btn btn-warning btn-sm"><i class="icon icon-edit"></i></a></td>
      			</tr>
      			<tr>
      				<td>5</td><td>你/是/谁</td><td>我是法律小助手</td><td><a class="btn btn-warning btn-sm"><i class="icon icon-edit"></i></a></td>
      			</tr>
      			<tr>
      				<td>6</td><td>你/是/谁</td><td>我是法律小助手</td><td><a class="btn btn-warning btn-sm"><i class="icon icon-edit"></i></a></td>
      			</tr>
      		</tbody>
      		<tbody>
      			<tr>
      				<td colspan="4">
      					<div class="pagination">
						  <ul>
						    <li><a href="#">Prev</a></li>
						    <li><a href="#">1</a></li>
						    <li><a href="#">2</a></li>
						    <li><a href="#">3</a></li>
						    <li><a href="#">4</a></li>
						    <li><a href="#">5</a></li>
						    <li><a href="#">Next</a></li>
						  </ul>
						</div>
      				</td>
      			</tr>
      		</tbody>
      	</table>
      </div>
    </div>
  </div>
</div>
<!--Footer-part-->
<div class="row-fluid">
  <div id="footer" class="span12"> <p>Copyright&copy;2016 法律小助手 -xxxx专业-xxx级-xxx同学</p></div>
</div>
<!--end-Footer-part-->
<script src="${ctx }/js/jquery.min.js"></script> 
<script src="${ctx }/js/jquery.ui.custom.js"></script> 
<script src="${ctx }/js/bootstrap.min.js"></script> 
<script src="${ctx }/js/jquery.flot.min.js"></script> 
<script src="${ctx }/js/jquery.flot.resize.min.js"></script> 
<script src="${ctx }/js/jquery.peity.min.js"></script> 
<script src="${ctx }/js/matrix.js"></script> 
<script type="text/javascript">
  function goPage (newURL) {
      // if url is empty, skip the menu dividers and reset the menu selection to default
      if (newURL != "") {
          // if url is "-", it is this page -- reset the menu:
          if (newURL == "-" ) {
              resetMenu();            
          } 
          // else, send page to designated URL            
          else {  
            document.location.href = newURL;
          }
      }
  }

// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
}
</script>
</body>
</html>
