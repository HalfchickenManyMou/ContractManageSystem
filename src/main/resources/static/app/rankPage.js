(function ($) {

    $(document).ready(function () {
        deleteLine();
        getData();
    });

    let rankList = new Vue({
        el : '#rankList',
        data : {
            list : [],
        },methods:{
            deleteLine: function (event) {
                let target = event.target.parentNode.parentNode
                target.remove();
            }
        }
    });

    //데이터 받아오기
    function getData(){
        $.get("/api/rank", function (res) {
            rankList.list = res.data;
        })
    }

    //계약서 타입 삭제
    function deleteLine() {
        $('button[name="deleteBtn"]').on("click", function (e) {
            let tr = $(this).parent().parent();
            tr.remove();
        });
    }

    //계약서 타입 추가
    $("#addBtn").on("click", function (e) {
        let tbody = $("#rankList");
        td = $('<tr role="row" class="odd">' +
            '<td class="text-center"></td>' +
            '<td name = "rank" class="text-center"><input type="text" style="width: auto; text-align: center;"></td>' +
            '<td class="text-center"><button name = "deleteBtn" type="button" class="btn btn-danger btn-xs">삭제</button></td>' +
            '</tr>');
        tbody.append(td);
        deleteLine();
    });

    $("#saveBtn").on("click", function (e) {
        let typeTd = $('td[name="rank"]');
        let postBody = [];
        let rank;
        for(let i =0; i<typeTd.length; i++){
            if(typeTd[i].childNodes[0].type === undefined ) rank = typeTd[i].childNodes[0].nodeValue;
            else rank = typeTd[i].childNodes[0].value;
            postBody[i] = {
                rank_name : rank
            }
        }

        if(confirm("수정하시겠습니까?")) {
            $.ajax({
                type: 'POST',
                url: '/api/rank/all',
                data: JSON.stringify({'data':postBody}),
                success: function(data) { window.location.assign("/pages/rank") },
                contentType: "application/json",
                dataType: 'json'
            });
        }
    });

    $("#cancelBtn").on("click", function (e) {
        if(confirm("수정사항을 취소하시겠습니까?")){ window.location.assign("/pages/rank") }
    });

})(jQuery)