$(function(){
 	$(".nav-con").eq(0).show();
 	$(".nav-ul li").mouseover(function(){
 		var i;
 		i=$(this).index();
 		$(".nav-ul li").eq(i).addClass("act").siblings().removeClass("act");
 		$(".nav-con").eq(i).show().siblings(".nav-con").hide(); 
 	});
 }); 