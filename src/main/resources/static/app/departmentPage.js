(function ($) {

    $(document).ready(function () {
        deleteLine();
        getData();
    });

    let departmentDetailModal = new Vue({
        el : "#departmentDetailModal",
        data : {
            departmentInfo : {
                idx : 0,
                department : "",
                team_list : []
            }
        }, methods:{
            closeHandler : function () {

                $('#departmentDetailModal').modal("hide");
                this.modalReset();
            },
            modalReset : function (){
                this.departmentInfo.department = "";
                //TODO td지우기
            },
            addBtn : function (event){
                let tbody = $("#teamList");
                td = $('<tr role="row" class="odd">' +
                    '<td class="text-center"></td>' +
                    '<td name = "team" class="text-center"><input type="text" style="width: auto; text-align: center;"></td>' +
                    '<td class="text-center"><button name = "deleteBtn" type="button" class="btn btn-danger btn-xs">삭제</button></td>' +
                    '</tr>');
                tbody.append(td);
               deleteLine();
            },
            deleteLine: function (event) {
                let target = event.target.parentNode.parentNode
                target.remove();
            },
            modifyBtn: function () {
                let idx = departmentDetailModal.departmentInfo.idx;
                let teamTd = $('td[name="team"]');
                let team;
                let body = {
                    "department" : departmentDetailModal.departmentInfo.department,
                    "team_list" : []
                };

                for(let i =0; i<teamTd.length; i++){
                    if(teamTd[i].childNodes[0].type === undefined ) team = teamTd[i].childNodes[0].nodeValue;
                    else team = teamTd[i].childNodes[0].value;
                    body.team_list[i] = {
                        team : team
                    }
                }
                //console.log(body);
                if(confirm("수정하시겠습니까?")) {
                    $.ajax({
                        type: 'PUT',
                        url: '/api/department/team/' + idx,
                        data: JSON.stringify({'data':body}),
                        success: function(data) { window.location.assign("/pages/department") },
                        contentType: "application/json",
                        dataType: 'json'
                    });
                }
            },
            totalDeleteBtn: function () {
                let idx = departmentDetailModal.departmentInfo.idx;
                if(confirm("삭제하시겠습니까?")) {
                    $.ajax({
                        type: 'DELETE',
                        url: '/api/department/' + idx,
                        success: function() { window.location.assign("/pages/department") },
                        contentType: "application/json",
                        dataType: 'json'
                    });
                }
            },

        }
    })


    let departmentEnrollModal = new Vue({
        el : "#departmentEnrollModal",
        data : {
            departmentInfo : {
                department : "",
                team_list : []
            }
        }, methods:{
            closeHandler : function () {
                $('#departmentEnrollModal').modal("hide");
                this.modalReset();
            },
            modalReset : function (){
                this.departmentInfo.department = "";
                //TODO td지우기
            },
            addBtn : function (event){
                let tbody = $("#postTeamList");
                td = $('<tr role="row" class="odd">' +
                    '<td class="text-center"></td>' +
                    '<td name = "enrollTeam" class="text-center"><input type="text" style="width: auto; text-align: center;"></td>' +
                    '<td class="text-center"><button name = "deleteBtn" type="button" class="btn btn-danger btn-xs">삭제</button></td>' +
                    '</tr>');
                tbody.append(td);
                deleteLine();
            },
            enrollBtn : function () {
                let teamTd = $('td[name="enrollTeam"]');
                let enrollTeam;
                let body = {
                    "department" : departmentEnrollModal.departmentInfo.department,
                    "team_list" : []
                };

                for(let i =0; i<teamTd.length; i++){
                    enrollTeam = teamTd[i].childNodes[0].value;
                    body.team_list[i] = {
                        team : enrollTeam
                    }
                }

                if(confirm("등록하시겠습니까?")) {
                    $.ajax({
                        type: 'POST',
                        url: '/api/department/team',
                        data: JSON.stringify({'data':body}),
                        success: function(data) { window.location.assign("/pages/department") },
                        contentType: "application/json",
                        dataType: 'json'
                    });
                }
            },
        }
    })


    let departmentList = new Vue({
        el : '#departmentList',
        data : {
            itemList : [],
        },methods:{
            itemRowHandler : function (event, row) {
                let data = $.extend(true, {} ,row);
                departmentDetailModal.departmentInfo = data;
                $('#departmentDetailModal').modal()
            }
    }
    });

    //데이터 받아오기
    function getData(){
        $.get("/api/department", function (res) {
            //console.log(res);
            departmentList.itemList = res.data;
        })
    }

    //행 삭제
    function deleteLine() {
        $('button[name="deleteBtn"]').on("click", function (e) {
            let tr = $(this).parent().parent();
            tr.remove();
        });
    }

    $("#enrollBtn").on("click", function (e) {
        $('#departmentEnrollModal').modal()
    });

})(jQuery)