$(function(){
console.log("???");
    $("#gnb_wrap>li").on("mouseover focusin",function(){

        $(this).children().stop().fadeIn(500);

        $(this).children("a").addClass("on");

    });

    $("#gnb_wrap>li").on("mouseout focusout",function(){

        $(this).children("div").stop().fadeOut(500);

        $(this).children("a").removeClass("on");

    });

    

    $(window).scroll(function(){

        var calcuSct=$(this).scrollTop();

        var introOffset=$("#d_intro_wrap").offset().top;

        if(calcuSct>=introOffset){

            $("#d_quickMenu_wrap").fadeIn(500);

        }else{

            $("#d_quickMenu_wrap").fadeOut(500);

        };

    });
}); //End