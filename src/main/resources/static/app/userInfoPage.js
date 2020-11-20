(function ($) {

    $(document).ready(function () {
        getData();
    });

    let userInfo = new Vue({
        el : '#userInfo',
        data : {
            info : {},
        }
    });

    $("#modifyBtn").on("click", function (e) {
        window.location.assign("/pages/user/myInfo/edit")
    });

    $("#deleteBtn").on("click", function (e) {
        //TODO 삭제
    });

    //데이터 받아오기
    function getData(){
        //TODO : 로그인한 유저로 변경
        $.get("/api/user/test1", function (res) {
            console.log(res);
            userInfo.info = res.data;
        })
    }




})(jQuery)