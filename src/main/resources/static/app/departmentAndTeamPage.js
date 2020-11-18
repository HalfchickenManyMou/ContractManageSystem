(function ($) {

    $(document).ready(function () {
        getData();
    });

    let departmentList = new Vue({
        el : '#departmentList',
        data : {
            itemList : [],
        },methods:{}
    });

    //데이터 받아오기
    function getData(){
        $.get("/api/department", function (res) {
            console.log(res);
            departmentList.itemList = res.data;
        })
    }
})(jQuery)