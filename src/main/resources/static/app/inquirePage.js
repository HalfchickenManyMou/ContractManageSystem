$(function ($) {
    $(document).ready(function () {

    });
    $("#btnSend").on("click", function (e) {
        let body = {
            sender : $("#sender").val(),
            title : $("#title").val(),
            content: $("#content").val()
        }
        console.log(body);
        if(confirm("메일을 전송합니다.")) {
            $.ajax({
                type: 'POST',
                url: '/api/inquire',
                data: JSON.stringify({'data':body}),
                success: function(data) { window.location.assign("/pages/Dashboard") },
                contentType: "application/json",
                dataType: 'json'
            });
        }
    });
})(JQuery)
