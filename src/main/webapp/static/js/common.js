window.onload = call111;
function call111(){
	$("#gnb_wrap>li").on("mouseover focusin", function() {
		$(this).children().stop().fadeIn(500);
		$(this).children("a").addClass("on");
	});
	$("#gnb_wrap>li").on("mouseout focusout", function() {
		$(this).children("div").stop().fadeOut(500);
		$(this).children("a").removeClass("on");
	});
	
	call();
}