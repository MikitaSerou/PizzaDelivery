$(document).ready(function(){
    $(".menu a").hover(function() {
        $(this).next(".hover").animate({opacity: "show", top: "-75"}, "fast");
    }, function() {
        $(this).next(".hover").animate({opacity: "hide", top: "-85"}, "slow");
    });
});