// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt) { //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
};

function getCurrentDateTime(){
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var day=date.getDate();
	var hours=date.getHours();
	var minutes=date.getMinutes();
	var seconds=date.getSeconds();
	return year+"-"+formatZero(month)+"-"+formatZero(day)+" "+formatZero(hours)+":"+formatZero(minutes)+":"+formatZero(seconds);
}

function getCurrentDate(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	return year + "-" + formatZero(month) + "-" + formatZero(day);
}

function formatZero(n){
	if(n >= 0 && n <= 9){
		return "0" + n;
	}else{
		return n;
	}
}

function isNull (targetObj) {
	if (targetObj == null || targetObj == 'null'
		|| typeof targetObj == 'undefined' || targetObj == 'undefined') {
		return true;
	} else {
		return false;
	}
}

function isEmpty(targetStr) {
	if (isNull(targetStr) || targetStr.length < 1) {
		return true;
	} else {
		return false;
	}
}

String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 *
 * @requires jQuery
 * 扩展datagrid的editor的datetimebox类型
 */
/*
$.extend($.fn.datagrid.defaults.editors, {
    datetimebox: { //datetimebox就是你要自定义editor的名称
        init: function(container, options) {
            var editor = $('<input />').appendTo(container);
            editor.enableEdit = false;
            editor.datetimebox(options);
            return editor;
        },
        getValue: function(target) {
            var new_str = $(target).datetimebox('getValue');
            return new_str;
        },
        setValue: function(target, value) {
        	var datetime;
        	if (value == null) {
        		datetime  = new Date();
        	} else {
        		datetime = new Date(value);
        	}
            $(target).datetimebox('setValue', datetime.format("yyyy-MM-dd hh:mm:ss"));
        },
        resize: function(target, width){
            $(target).datetimebox('resize', width);
        },
        destroy: function(target){
            $(target).datetimebox('destroy');
        }
    }
});*/