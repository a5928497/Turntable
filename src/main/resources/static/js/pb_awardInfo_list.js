$(function () {
    $cashingBTN = $(".cashingBTN");
    $cashingForm = $("#cashingForm");
    $recommender_id = $("#recommender_id");
    $cashingBTN.click(function () {
        var isOk = prompt("请输入推荐号：");
        if (isOk != null) {
            $recommender_id.val(isOk);
            $cashingForm.attr("action",$(this).attr("put_uri")).submit();
        }
        return false;
    });
})