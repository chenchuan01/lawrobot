﻿
function loadFontPage(){
	$('#send').click(askQuestion);
	setTimeout('hello()',1000);
}
function hello(){
	thinkId=thinking();
	ajaxData('front/hello.do',{},showAnswer,sysError);
}
var thinkId =[];
var thinkIndex=0;
function thinking(){
	if($('#robotImg').popover){
		$('#robotImg').popover('destroy');
	}
	thinkId[thinkIndex++] = setInterval(function(){
		var that=$('#robotImg');
		var imgSrc = that.attr('src');
		if(imgSrc.indexOf('thinking0')<0){
			setRobotImg('thinking0');
		}else{
			setRobotImg('thinking1');
		}
	}, 200);
	return thinkId;
}
function clearThink(){
	if(thinkId!='undefined'||thinkId.length>0){
		for(var i in thinkId){
			if(thinkId[i]!='undefined'&&thinkId[i]!=null&&thinkId[i]!=''){
				clearInterval(thinkId[i]);
			}
		}
		
	}
}

function askQuestion(){
	var question = $('#question');
	var questContent = question.val();
	if(questContent!='undefined'&&questContent!=''&&questContent!=null){
		question.val('');
		var param = {'content':questContent};
		thinkId=thinking();
		ajaxData('front/ask.do',param,showAskMsg,sysError);
	}
	
}
function showAskMsg(data){
	var question = data;
	var questContent = '<p class="alert alert-info msg"><b>#time</b><br>#content</p>';
	questContent = questContent.replace('#time',question.time).replace('#content',question.content);
	$('#msgAre').append(questContent);
	focusBottom();
	queryAnswer(data);
}

function queryAnswer(question){
	var zone_index = $('#zone_index').val();
	var filed_index = $('#filed_index').val();
	var param={'time':question.time,'question':question.content,'zone':zone_index,'filed_index':filed_index};
	ajaxData('front/query.do',param,showAnswer,sysError);
}
function showAnswer(data){
	var answer = data;
	var answerContent = '<p class="alert alert-success msg"><b>#time</b><br>#content</p>';
	answerContent = answerContent.replace('#time',answer.time).replace('#content',answer.answer);
	clearThink();
	setRobotImg('answered');
	$('#msgAre').append(answerContent);
	setTimeout('space',1000);
	focusBottom();
}
function space(){
	setRobotImg('space');
}
function focusBottom(){
	var msgAre = document.getElementById('msgAre');
	msgAre.scrollTop = msgAre.scrollHeight;
}
function setRobotImg(img_subfix){
	$('#robotImg').attr('src','img/robot_'+img_subfix+".png");
}

function sysError(data){
	clearThink();
	setRobotImg('syserror');
	if(data&&data.msg){
		errorTip(data.msg);
	}
}

function errorTip(message){
$('#robotImg').attr('title','系统提示');
$('#robotImg').attr('data-toggle','popover');
$('#robotImg').attr('data-trigger','focus');
$('#robotImg').attr('data-placement','left');
$('#robotImg').attr('data-content',message);
$('#robotImg').popover('show');
$('#robotImg').focus();
}
