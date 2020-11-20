(function ($) {

    $(document).ready(function () {
        getData();
        deleteNewLine();
    });

    let contractTypeList = new Vue({
        el : '#contractTypeList',
        data : {
            list : [],
        },methods:{
            deleteLine: function (event) {
                let target = event.target.parentNode.parentNode.firstChild;
                let idx = target.innerHTML;
                if(confirm("삭제하시겠습니까?")) {
                    $.ajax({
                        type: 'DELETE',
                        url: '/api/contractType/' + idx,
                        success: function(data) { window.location.assign("/pages/contract/contractType") },
                        contentType: "application/json",
                        dataType: 'json'
                    });
                }
            }
        }
    });
    
    //데이터 받아오기
    function getData(){
        $.get("/api/contractType", function (res) {
            contractTypeList.list = res.data;
        })
    }

    //행 삭제
    function deleteNewLine() {
        $('button[name="deleteBtn"]').on("click", function (e) {
            let tr = $(this).parent().parent();
            tr.remove();
        });
    }

    //계약서 타입 추가
    $("#addBtn").on("click", function (e) {
        let tbody = $("#contractTypeList");
        let td = $('<tr role="row" class="odd">' +
            '<td hidden></td>'+
            '<td class="text-center"></td>' +
            '<td name = "type" class="text-center"><input type="text" style="width: auto; text-align: center;"></td>' +
            '<td class="text-center"><button name = "deleteBtn" type="button" class="btn btn-danger btn-xs">삭제</button></td>' +
            '</tr>');
        tbody.append(td);
        deleteNewLine();
    });

    $("#saveBtn").on("click", function (e) {
        let typeTd = $('td[name="type"]');
        let postBody = [];
        let type;
        for(let i =0; i<typeTd.length; i++){
            // console.log(typeTd[i].childNodes[0].value)
            type = typeTd[i].childNodes[0].value;
            postBody[i] = {
                type : type
            }
        }

        console.log(postBody);

        if(confirm("수정하시겠습니까?")) {
            $.ajax({
                type: 'POST',
                url: '/api/contractType/bulkCreate',
                data: JSON.stringify({'data':postBody}),
                success: function(data) { window.location.assign("/pages/contract/contractType") },
                contentType: "application/json",
                dataType: 'json'
            });
        }
    });

    $("#cancelBtn").on("click", function (e) {
        if(confirm("수정사항을 취소하시겠습니까?")){ window.location.assign("/pages/contract/contractType") }
    });

})(jQuery)