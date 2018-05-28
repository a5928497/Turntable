$(function () {
    $drawBTN = $(".drawBTN");
    $lottery = $(".lottery");
    $drawForm = $("#drawForm");
    $available_draw_times =$(".available_draw_times");
    // $lottery.css("background","url(../images/lottery.png)");
    $drawBTN.click(function () {
        var msg;
        $.ajax({
            type: "POST",   //提交的方法
            url:"/draw", //提交的地址

            data:$drawForm.serialize(),// 序列化表单值
            async: false,
            error: function (request) {
                alert("连接超时！");
            },
            success: function (data) {
                msg = "恭喜您抽到了" + data.rewardName;

            }
        });
        $(".lottery").animate({rotate: '360'+180}, 2000);
    })
})