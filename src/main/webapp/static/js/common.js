window.onload = call;
function call(){
	$("#gnb_wrap>li").on("mouseover focusin", function() {
		$(this).children().stop().fadeIn(500);
		$(this).children("a").addClass("on");
	});
	$("#gnb_wrap>li").on("mouseout focusout", function() {
		$(this).children("div").stop().fadeOut(500);
		$(this).children("a").removeClass("on");
	});
}