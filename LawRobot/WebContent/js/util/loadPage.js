﻿/**loadPage*/
$(function(){
	loadPage();
});
var loadPage=function(){
	initGlobalVals();
	initGlobalHotKey();
	initSwipeQuery();
	initOther();
};
var initOther=function(){
	/**路径展示*/
	$('#photoFile').blur(function(){$("#filepath").text($(this).val());});
	/**清空表单*/
	cleanForm();
	/**分布表单完成*/
	$('.button-finish').click(finish);
	/**拍照、照片选择按钮*/
	photoBtnInit();
	/**日期控件*/
	dateTimePicker();
	/**自定义滚动条*/
    greenScroll();
    /**bootstrap mual init*/
    bootstrapInit();
    
    loadAutoCountHours();
};
var loadAutoCountHours=function(){
	if($('#payhours')&&$('#givehours')){
		$('#payhours').keyup(function(){autoCountHours();});
		$('#givehours').keyup(function(){autoCountHours();});
	}
	
};
var autoCountHours=function(){
	var payhour=isNaN(parseInt($('#payhours').val()))?0:parseInt($('#payhours').val());
	var givehour=isNaN(parseInt($('#givehours').val()))?0:parseInt($('#givehours').val());
	$('#totalhours').val(payhour+givehour);
};
var photoBtnInit = function(){
	$('#cameraPhoto').click(function(){
		savePhotoType(1);
		var peopleId=$("#peopleId").val();
		if(!peopleId){
			info('请先填写并保存基本信息');
		}
		var photoSavelUrl = $("#photoSaveUrl").val();
		autoSizeDialog('摄像头-拍照','sys/invokeComarea.do?peopleId='+peopleId+"&saveUrl="+photoSavelUrl,1200,700);
	});
	$('#choosePhoto').click(function(){
		savePhotoType(0);
	});
};
var cleanForm=function(){
	$('.cancel').click(function(){
		$("textarea,select").each(
			function(){$(this).val('');});
		$("input[name]").each(function(){
			if($(this).attr('type')=='text'||
					$(this).attr('type')=='hidden'){
				$(this).val('');
			}
			
		});
		refresh();
	});
};
var bootstrapInit=function(){
	 $('[data-toggle="popover"]').popover();
	 $('[data-toggle="tooltip"]').tooltip();
	 $('#swipeModal').on('hidden.bs.modal', function (e) {
			$btnDisable('#swipeConfirmBtn');
			cleanFormById('#swipeConfirm');
		});
	 $('#formModal').on('hidden.bs.modal', function (e) {
		 $('#formModal'+' .modal-title').text("");
		 $('#formModal'+' .modal-body').html("");
	 });
	 $('#memberModal').on('hidden.bs.modal', function (e) {
			cleanFormById('#memberModal');
		});
};
var greenScroll =function(){
	$('html,body,textare').niceScroll({
		cursorborderradius: 0,
		cursorwidth: "8px",
		cursorfixedheight: 150,
		cursorcolor: "#6CB670",
		zindex: 999,
		cursorborder: 0,
	});
	
	$('.acc,.navPage_bg,.content,.warp,.modal').niceScroll({
		cursorborderradius: 0,
		cursorwidth: "8px",
		cursorfixedheight: 150,
		cursorcolor: "#6CB670",
		zindex: 997,
		cursorborder: 0,
	});
	
}; 

var initPage=function(){
	var template = $('#template').children('td');;
	if(template!=null&&template.length>0){
		page(1);
	};
};
/*************分页封装***************/
var pageHtml='<a id="page_%page%" class="pages current" onclick="page(\'%page%\')">%page%</a>';
function getPageUrl(){
	return $('#search').attr('action');
}

function getTotal(){
	return parseInt($('#totalPages').text());
};
function setTotal(pages){
	$('#totalPages').text("");
	$('#totalPages').text(pages);
};
function getPage(){
	return parseInt($('#page').text());
};
function setPage(page){
	$('.pages').removeClass('current');
	$('#page').text("");
	$('#page').text(page);
	var pageId="#page_"+page;
	$(pageId).addClass('current');
};
var setPageItems=function(itemHtml){
	$('#pagesItems').html('');
	$('#pagesItems').html(itemHtml);
};
var setTableData=function(dataHtml){
	$('#tableData').html('');
	$('#tableData').html(dataHtml);
	
};
var page=function(num){
	if(getPageUrl()=='undefined'||getParams()=='undefined'){
		return;
	}
	var params = getParams('#search');
	params['page']=num;
	var url = getPageUrl();
	ajaxData(url,params,pageFun);
};
var pageFun=function(data){
	if(data){
		jspLoadPageDatas(data.content);
		loadPagesItem(data.totalPages);
		setTotal(data.totalPages);
		setPage(data.page);
	}else{
		loadPagesItem(0);
		setTotal(0);
		setPage(0);
	}
};
var loadPagesItem=function(total){
	total = total=='undefined'?0:total;
	if(total>0){
		var itemHtml='';
		for(var i =1;i<=total;i++){
			itemHtml = itemHtml+pageHtml.replace('%page%', i).replace('%page%', i).replace('%page%', i);
		}
		setPageItems(itemHtml);
	};
};
var jspLoadPageDatas=function(data){
	var trStr='<tr class="">%content%</tr>';
	var tdStr='<td item="%item%">%content%</td>';
	var trObj=$('#template');
	
	var tdObjs=trObj.children('td');
	var trStrs = "";
	//行列遍历生成html字符串
	for(var row=0;row<data.length;row++){
		var jsonData = data[row];
		var tempTr=trStr;
		var tdStrs="";
		for(var col=0;col<tdObjs.length;col++){
			var dataMap = $jsonObjToMap(jsonData);
			var tempTd = tdStr;
			var tdObj = $(tdObjs[col]);
			var item = tdObj.attr('item');
			var content = "";
			if('index'==item){
				content = ''+(row+1);
			}else if('oprea'==item){
				var itemFiled = tdObj.attr('itemFiled');
				var itemId = $getDataFiled(dataMap, itemFiled);
				content = genOpreaHtml($(tdObjs[col]).html(),itemId);
			}else{
				content = $getDataFiled(dataMap,item);
			}
			tempTd = tempTd.replace("%item%", item).replace("%content%", content);
			tdStrs = tdStrs+tempTd;
		}
		tempTr = tempTr.replace('%content%',tdStrs);
		trStrs=trStrs+tempTr;
	}
		
	if(trStrs.indexOf('%content%')<=-1){
		clearTable();
		setTable(trStrs);
	}
	
};

var genOpreaHtml = function(aStrs,itemId){
	var opStrs = aStrs.split('id=');
	var newOpStrs = opStrs[0];
	for(var i=1;i<opStrs.length;i++){
		newOpStrs=newOpStrs+"id="+itemId+opStrs[i].substring(opStrs[i].indexOf('\''),opStrs[i].length);
	}
	return newOpStrs;
};

var clearTable=function(){
	$("#tableData").html('');
};
var setTable=function(trStrs){
	$("#tableData").html(trStrs);
	var trs = $("#tableData").children('tr');
	for(var i =0;i<trs.length;i++){
		if(i % 2==1){
			$(trs[i]).addClass('even trodd');
		}else{
			$(trs[i]).addClass('treven');
		}
	}
};
var nextPage=function(){
	var total = getTotal();
	var pageNum = getPage()+1;
	if(pageNum>total){
		return;
	}
	page(pageNum);
};

var prePage=function(){
	var pageNum = getPage()-1;
	if(pageNum<1){
		return;
	}
	page(pageNum);
};
var fristPage=function(){
	page(1);
};
var lastPage=function(){
	page(getTotal());
};
