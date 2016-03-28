<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/taglib.jspf" %>
<div id="questFormContent" class="row-fluid">
<div class="span10">
<form id="questForm" class="form-horizontal" action="admin/questModify.do" method="post">
  <div class="control-group">
    <label class="control-label" for="questZone">地区选择</label>
    <div class="controls">
       <input type="hidden" name="id" value="${answer.id }">
       <select id="questZone" name="zone" class="input-middle">
	  	<c:forEach items="${sys:getListVal('ZONE_INDEX') }" var="zoneItem">
	  		<c:set var="zone_index" value="${fn:split(zoneItem,':')[0] }"/>
	  		<c:set var="zone_content" value="${fn:split(zoneItem,':')[1] }"/>
	  		<option value="${zone_index }" ${answer.zone eq zone_index?'selected':'' }>${zone_content }</option>
	  	</c:forEach>
	  </select>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="questFiled">领域选择</label>
    <div class="controls">
       <select id="questFiled" name="filed" class="input-middle">
		  	<c:forEach items="${sys:getListVal('FILED_INDEX') }" var="filedItem">
		  		<c:set var="filed_index" value="${fn:split(filedItem,':')[0] }"/>
		  		<c:set var="filed_content" value="${fn:split(filedItem,':')[1]}"/>
		  		<option value="${filed_index }" ${answer.filed eq filed_index?'selected':'' }>${filed_content }</option>
		  	</c:forEach>
		  </select>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="questContent">问题描述</label>
    <div class="controls">
      <textarea  id="questContent" rows="4" name="question" placeholder="问题描述">${answer.question }</textarea>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="questKeyWords">关键字分词</label>
    <div class="controls">
      <input id="questKeyWords" type="text" name="keywords" class="input-middle" value="${answer.keywords }" placeholder="关键字词以'/'分隔，关键字顺序确定句意"/>
    </div>
  </div>
   <div class="control-group">
    <label class="control-label" for="questAnswer">问题回答</label>
    <div class="controls">
      <textarea  id="questAnswer" rows="4" name="answer" class="input-middle"  placeholder="问题回答">${answer.answer }</textarea>
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <button type="button" class="btn btn-primary" onclick="save('#questForm');">保存修改</button>
    </div>
  </div>
</form>	
</div>
</div>