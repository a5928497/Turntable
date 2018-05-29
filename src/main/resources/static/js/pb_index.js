$(function () {
    $drawBTN = $(".drawBTN");
    $lottery = $(".lottery");
    $drawForm = $("#drawForm");
    $available_draw_times =$(".available_draw_times");
    $user_id = $("#user_id");
    $act_id =$("#act_id");
    // $lottery.css("background","url(../images/lottery.png)");
    $drawBTN.click(function () {
        var msg;
        var rotation;
        var user_id = $user_id.val();
        var url  = "/getVT/"+user_id;
        if (user_id != null || user_id !=0) {
            //从后台获取可用次数
            $.ajax({
                type: "GET",   //提交的方法
                url: url,//提交的地址
                async: false,
                error: function (request) {
                    alert("连接超时！");
                },
                success: function (data) {
                    $user_id.val(data.id);
                    $act_id.val(data.act_id);
                    var available_draw_times = data.available_draw_times;
                    $available_draw_times.val(available_draw_times);
                    //若可用次数大于0
                    if (available_draw_times > 0) {
                        $.ajax({
                            type: "POST",   //提交的方法
                            url:"/draw", //提交的地址

                            data:$drawForm.serialize(),// 序列化表单值
                            async: false,
                            error: function (request) {
                                alert("连接超时！");
                            },
                            success: function (data) {
                                if (data.rewardName == "thanks") {
                                    msg = "对不起，您本次没有中奖！"
                                }else {
                                    msg = "恭喜您抽到了" + data.rewardName;
                                }
                                rotation = parseInt(data.rotation)+360;
                                console.log(rotation);
                            }
                        });
                        //根据抽奖记录旋转并执行回调提示
                        $(".lottery").animate({rotate: rotation}, 2000,function () {
                            alert(msg);
                            window.location.replace("/reflash");
                        });
                    }else{
                        //若可用次数为0
                        alert("对不起，您的抽奖次数已经用完了！");
                        window.location.replace("/reflash");
                    }
                }
            });
        }

    })
})