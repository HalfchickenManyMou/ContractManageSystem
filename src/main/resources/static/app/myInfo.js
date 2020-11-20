(function ($) {

    $(document).ready(function () {
        deleteLine();
        getData();
    });

    let myData = new Vue({
        el : '#myData',
        data : {
            list :[],

        },methods:{
            deleteLine: function (event) {
                let target = event.target.parentNode.parentNode
                target.remove();
            }
        }
    });

    //데이터 받아오기
    function getData(){
        $.get("/api/myInfo", function (res) {
            myData.list = res.data;
        })
    }


    $("#updateBtn").on("click", function (e) {
        let myInfo={
            "user_code"    : document.getElementById("id"),
            "name": document.getElementById("inputName"),
            "email": document.getElementById("email"),
            "pwd": document.getElementById("npwd"),
            "deptartment_idx": document.getElementById("dept_id"),
            "team_idx": document.getElementById("team_id"),
            "rank": document.getElementById("rank"),
            "phone_number": document.getElementById("phone_num")
        }

        if(confirm("수정하시겠습니까?")) {
            $.ajax({
                type: 'POST',
                url: '/api/myInfo',
                data: JSON.stringify({'data':myInfo}),
                success: function(data) { window.location.assign("/pages/myInfo") },
                contentType: "application/json",
                dataType: 'json'
            });
        }

    });



})(jQuery)