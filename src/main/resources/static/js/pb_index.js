$(function () {
    $drawBTN = $(".drawBTN");
    $lottery = $(".lottery");
    $drawForm = $("#drawForm");
    // $lottery.css("background","url(../images/lottery.png)");
    $drawBTN.click(function () {
        $.ajax({
            type: "POST",   //提交的方法
            url:"/draw", //提交的地址
            data:$drawForm.serialize(),// 序列化表单值
            async: false,
            error: function (request) {
                alert("连接超时！");
            },
            success: function (data) {
                var jsondata=eval("("+data+")");
                alert(jsondata.rewardName);
            }
        })
        $(".lottery").animate({rotate: '360'}, 2000);
    })
})