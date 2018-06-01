$(function () {
    var un_left_rate = 0.403;
    var un_top_rate = 0.685;
    var ps_left_rate = 0.383;
    var ps_top_rate = 0.75;
    var input_height_rate = 0.041;
    var input_width_rate = 0.392;
    var btn_width_rate = 0.4533;
    var btn_height_rate  = 0.1052;
    var btn_top_rate = 0.8225;
    var btn_left_rate = 0.1964;
    $body = $("body");
    $username = $("#username");
    $password = $("#password");
    $submitBTN =$("#submitBTN");
    var body_width = $body.width();
    var body_height = $body.height();
    $username.offset({top:body_height*un_top_rate,left:body_width*un_left_rate}).css("height",body_height*input_height_rate).css("width",body_width*input_width_rate);
    $password.offset({top:body_height*ps_top_rate,left:body_width*ps_left_rate}).css("height",body_height*input_height_rate).css("width",body_width*input_width_rate);
    $submitBTN.css("width",body_width*btn_width_rate).css("height",body_height*btn_height_rate)
        .offset({top:body_height*btn_top_rate,left:body_height*btn_left_rate});
})