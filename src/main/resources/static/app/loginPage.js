(function ($) {

    $("#loginBtn").on("click", function (e) {
        let id = $("#ID").val();
        let password = $("#PASSWORD").val();

        let data = {}
        data.email = id;
        data.password = password;

        //console.log(data);
        $.ajax({
            type: 'POST',
            url: '/login',
            data: JSON.stringify(data),
            success: function(res, status, xhr) {
                console.log("success")
                window.location.assign("/pages/dashboard")
            },
            error: function (){
              alert("아이디와 비밀번호를 확인해주세요")
            },
            contentType: "application/json",
            dataType: 'text'
        });
    });

})(jQuery)