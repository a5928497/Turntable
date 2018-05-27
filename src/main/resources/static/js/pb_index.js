$(function () {
    $drawBTN = $(".drawBTN");
    $lottery = $(".lottery");
    // $lottery.css("background","url(../images/lottery.png)");
    $drawBTN.click(function () {
        $(".lottery").animate({rotate: '360'}, 2000);
    })
})