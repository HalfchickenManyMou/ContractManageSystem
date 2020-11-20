$(function ($) {
    $(document).ready(function () {

    });
    $("#btnSend").on("click", function (e) {
        let body = {
            sender : document.getElementById("sender"),
            title : document.getElementById("title"),
            content:document.getElementById("content")
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
