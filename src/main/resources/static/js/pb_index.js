$(function () {
    $drawBTN = $(".drawBTN");
    $lottery = $(".lottery");
    $drawForm = $("#drawForm");
    $available_draw_times =$(".available_draw_times");
    // $lottery.css("background","url(../images/lottery.png)");
    $drawBTN.click(function () {
        var msg;
        var rotation;
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
                rotation = parseInt(data.rotation)+360;
                console.log(rotation);
            }
        });
        //根据抽奖记录旋转并执行回调提示
        $(".lottery").animate({rotate: rotation}, 2000,function () {
            alert(msg);
            window.location.replace("/ruser");
        });
    })
})