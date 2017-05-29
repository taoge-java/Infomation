Dialog = {
	version : "1.0.1",
	width : 450,
	height : 150,
	title : "提示",
	move : false,
	x : 0,
	y : 0,


	join : function() {
		if ($('.zy-ui-dialog')[0])
			$('.zy-ui-dialog').remove();
		if (!$('.zy-ui-overlay')[0])
			$('<div></div>').attr('class', 'zy-ui-overlay').appendTo('body');
		$("<div class='zy-ui-dialog'></div>").appendTo('body');
	},

	body : function(option) {
		$("<div class='dialog_head'></div>").html(option == undefined || option.title == undefined ? this.title : option.title)
			.append($("<a class='ico_close'></a>")).appendTo($(".zy-ui-dialog"));
		$("<div class='dialog_content'></div>").appendTo($(".zy-ui-dialog"));
	},
	
	resize : function(option) {
		var pwidth = option == undefined || option.width == undefined ? Dialog.width : option.width;
		var pheight = option == undefined || option.height == undefined ? Dialog.height : option.height;
		
		var left = ($(window).width() -pwidth) / 2;
		if(option != undefined){
			if(option.x != undefined || option.x != 0){
				left = option.x;
			}
		}
		
		var top = ($(window).height() - pheight) / 2;
		if(option != undefined){
			if(option.y != undefined || option.y != 0){
				top = option.y;
			}
		}
		
		$('.zy-ui-overlay').css({"width" : $(window).width(), "height" : $(document).height()});
		$(".zy-ui-overlay").fadeTo(0, 0.5);
		$(".dialog_content").css({
			"height" : (pheight - 25)
		});
		$(".zy-ui-dialog").css({
			"left" : left,
			"top" : top,
			"width" : pwidth,
			"height" : pheight
		});
		$('.zy-ui-dialog').fadeIn(1000);
	},
	
	
	init : function(option){
		this.join();this.body(option);
	},

	alert : function(message, option) {
		this.init(option);
		$(".dialog_content").append($("<i class='alert'></i>")).append($("<div class='content_message'></div>").html(message))
			 .append($("<div class='tip_button'></div>").append($("<a id='opn_ok' class='a_btn'>确定</a>")));
		this.resize(option);
		$('#opn_ok').click(function(){
			if(option && option.call)
				option.call();
			Dialog.close();
		});
		$('.ico_close').click(function(){
			if(option && option.close)
				option.close();
			Dialog.close();
		});
		if(option && option.allowDrag)
			this.allowDrag();
	},
	
	confirm : function(message, option) {
		this.init(option);
		$(".dialog_content").append($("<i class='confirm'></i>")).append($("<div class='content_message'></div>").html(message))
			.append($("<div class='tip_button'></div>").append($("<a id='opn_ok' class='a_btn'>确定</a>")).append($("<a id='opn_cancel' class='a_btn'>取消</a>")));		
		$('#opn_ok').click(function(){
			if(option.call)
				option.call();
			Dialog.close();
		});
		$('#opn_cancel').click(function(){
			if(option.cancel)
				option.cancel();
			Dialog.close();
		});
		$('.ico_close').click(function(){
			if(option && option.close)
				option.close();
			Dialog.close();
		});
		this.resize(option);
		if(option && option.allowDrag)
			this.allowDrag();
	},
	
	load : function(option) {
		if(option == undefined || option.url == undefined){
			console.info("未输入加载的url参数");
			return;
		}			
		this.init(option);
		var url = option.url;
		if (option.url.indexOf("?") == -1)
			url += "?s=" + Math.random();
		else
			url += "&s=" + Math.random();
//		$(".dialog_content").append($("<iframe class='ui-web-frame' src='" + url + "'></iframe>"));
		$(".dialog_content").load(url,function(){
			if(option && option.onSuccess){
				option.onSuccess();
			}
		});
		
		this.resize(option);
		$('.ico_close').click(function(){
			if(option && option.close)
				option.close();
			Dialog.close();
		});
		if(option && option.allowDrag)
			this.allowDrag();
	},
	
	custom : function(option) {
		this.init(option);
		$(".dialog_content").append($("<i class='confirm'></i>")).append($("<div class='content_message'></div>").html(option.message)).append($("<div class='tip_button'></div>"));
		if(option.buttons==null || option.buttons==undefined){
			$(".tip_button").append("<a id='opn_cancel' class='a_btn'>关闭</a>");
			$('#opn_cancel').click(function(){
				Dialog.close();
			});
		}else{
			$.each(option.buttons,function(i,d){
				$(".tip_button").append($("<a class='a_btn'></a>").html(d.text).attr("id", "dialog_custom_shared_"+i));
				if(d.call){
					$('#dialog_custom_shared_'+i).click(function(){
						d.call();
						Dialog.close();
					});
				}
			});
			
		}		
		this.resize(option);
		$('.ico_close').click(function(){
			if(option && option.close)
				option.close();
			Dialog.close();
		});
		if(option && option.allowDrag)
			this.allowDrag();
	},

	close : function() {
		if ($('.zy-ui-dialog')[0])
			$('.zy-ui-dialog').remove();
		if ($('.zy-ui-overlay')[0])
			$('.zy-ui-overlay').remove();
	},
	
	allowDrag : function(){
		$(".dialog_head").mouseover(function() {
			$(this).css("cursor", "move");
		}).mousedown(function(e) {
			Dialog.move = true;
			x = e.pageX - parseInt($(".zy-ui-dialog").css("left"));
			y = e.pageY - parseInt($(".zy-ui-dialog").css("top"));
			$(this).css("cursor", "move");
		});

		$(document).mousemove(function(e) {
			if (Dialog.move) {
				var _x = e.pageX - x;
				var _y = e.pageY - y;
				$(".zy-ui-dialog").css({
					top : _y,
					left : _x
				});
			}
		}).mouseup(function() {
			Dialog.move = false;
			$(".dialog_head").css("cursor", "default");
		});
	}
};