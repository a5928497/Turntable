$(function () {
    $cashingBTN = $(".cashingBTN");
    $cashingForm = $("#cashingForm");
    $recommender_id = $("#recommender_id");
    $wrong_recommender =$("#wrong_recommender");
    if ($wrong_recommender.length !=0) {
        alert("推荐号错误，请重新输入！");
    }
    $cashingBTN.click(function () {
        var isOk = prompt("请输入推荐号：");
        if (isOk != null) {
            $recommender_id.val(isOk);
            $cashingForm.attr("action",$(this).attr("put_uri")).submit();
        }
        return false;
    });
})