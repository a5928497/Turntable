$(function () {
    $cashingBTN = $(".cashingBTN");
    $cashingForm = $("#cashingForm");
    $recommender_id = $("#recommender_id");
    $wrong_recommender =$("#wrong_recommender");

    // var body_width = $("body").css("width");
    // var info_area_rate = 0.55
    // //设置奖品信息尺寸
    // $info_area.css("width",body_width*info_area_rate);
    $cashingBTN.click(function () {
        var url = $(this).attr("get_uri");
        $info_area = $(this).prev(".info_area");
       $.get(url,function (data) {
           $info_area.text("兑换码:"+ data);
       })
        return false;
    });
})