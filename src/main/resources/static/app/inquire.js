(function ($) {

    $("#sendEmail").on("click", function (e) {
        let userEmail = $("#inputEmail");
        let userTitle = $("#inputTitle");
        let sendText = $("#inputText");
        $.ajax({
            type:"POST",
            url:"/api/inquire",
            dataType : "json",
            data:({
                "userEmail":userEmail,
                "userTitle":userTitle,
                "userText": sendText
            })
        }
        )
    })
})


