(function ($) {

    $(document).ready(function () {
        deleteLine();
        getData();
    });

    let rankList = new Vue({
        el : '#userData',
        data : {
            link:[],
        },methods:{
            deleteLine: function (event) {
                let target = event.target.parentNode.parentNode
                target.remove();
            }
        }
    });

    //데이터 받아오기
    function getData(){
        $.get("/api/userInfo", function (res) {
            rankList.list = res.data;
        })
    }



    $("#updateBtn").on("click", function (e) {
        let useridx = $('td[idx="id"]');
        let postBody = [];
        let rank;
        for(let i =0; i<useridx.length; i++){
            if(useridx[i].childNodes[0].type === undefined ) rank = useridx[i].childNodes[0].nodeValue;
            else rank = useridx[i].childNodes[0].value;
            postBody[i] = {
                name    : name,
            }
        }


    });


})(jQuery)