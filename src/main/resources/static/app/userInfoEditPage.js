(function ($) {

    $(document).ready(function () {
        getData();
    });

    //비밀번호 변경 modal
    let userPasswordEditModal = new Vue({
        el : "#userPasswordEditModal",
        data : {
            password : {
                originPassword : "",
                newPassword : "",
                reNewPassword : ""
            }
        }, methods:{
            closeHandler : function () {
                $('#userPasswordEditModal').modal("hide");
                resetUserPasswordEditModal();
            },
            updatePasswordHandler : function (){
                let pwd = userPasswordEditModal.password;
                if(pwd.newPassword === pwd.reNewPassword){
                    //console.log("same");
                    let body = {
                        user_code : editUserInfo.user.user_code,
                        origin_password : pwd.originPassword,
                        new_password : pwd.newPassword
                    }

                    if(confirm("수정하시겠습니까?")) {
                        $.ajax({
                            type: 'PUT',
                            url: '/api/user/password',
                            data: JSON.stringify({'data':body}),
                            success: function(data) { window.location.assign("/pages/user/myInfo") },
                            error: function(err) {
                                if(err.status === 400) alert("기존 비밀번호를 확인해주세요.");
                            },
                            contentType: "application/json",
                            dataType: 'json'
                        });
                    }
                }else{
                    //console.log("diff");
                    alert("비밀번호를 확인해주세요.")
                }
            }
        }
    })

    let editUserInfo = new Vue({
        el : "#editUserInfo",
        data : {
            user : {},
            //category
            departmentCategory : [],
            rankCategory : [],
            //selected
            selectedDepartment : {},
            selectedTeam : {},
            selectedRank : {}
        },
        methods: {
            departmentHandle: function () {
                let tmp = JSON.parse(JSON.stringify(editUserInfo.departmentCategory)); //깊은복사
                editUserInfo.selectedDepartment = tmp.find((v) => (v.department === editUserInfo.selectedDepartment.department));
                editUserInfo.selectedTeam = { team : ""};
            },
            teamHandle: function () {
                let tmp = editUserInfo.selectedDepartment.team_list;
                for(let i =0; i < tmp.length; i++){
                    if(tmp[i].team === editUserInfo.selectedTeam.team) editUserInfo.selectedTeam = tmp[i];
                }
            },
            rankHandle: function () {
                let tmp = JSON.parse(JSON.stringify(editUserInfo.rankCategory)); //깊은복사
                editUserInfo.selectedRank = tmp.find((v) => (v.rank_name === editUserInfo.selectedRank.rank_name));
            }
        }
    })

    //데이터 받아오기
    function getData(){
        $.get("/api/user/test1", function (res) {
            console.log(res.data);
            editUserInfo.user = res.data;
            editUserInfo.selectedDepartment = res.data.department;
            editUserInfo.selectedTeam = res.data.team;
            editUserInfo.selectedRank = res.data.rank;
        })

        $.get("/api/department", function (res) {
            editUserInfo.departmentCategory = res.data;
            //editUserInfo.departmentCategory = editUserInfo.departmentCategory.filter((v) => (v.idx !== editUserInfo.selectedDepartment.idx));
        })

        $.get("/api/rank", function (res) {
            editUserInfo.rankCategory = res.data;
        })
    }

    //modal input값 초기화
    function resetUserPasswordEditModal(){
        userPasswordEditModal.password.originPassword="";
        userPasswordEditModal.password.newPassword="";
        userPasswordEditModal.password.reNewPassword="";
    }

    //비밀번호 modal 표시
    $("#editPasswordBtn").on("click", function (e) {
        $('#userPasswordEditModal').modal()
    });

    //취소
    $("#cancelBtn").on("click", function (e) {
        if(confirm("수정을 취소하시겠습니까?")){
            window.location.assign("/pages/user/myInfo/edit");
        }
    });


    $("#updateBtn").on("click", function (e) {
        let user = editUserInfo.user;
        let body = {
            user_code : user.user_code,
            name : user.name,
            email : user.email,
            phone_number : user.phone_number,
            department : editUserInfo.selectedDepartment,
            team : editUserInfo.selectedTeam,
            rank : editUserInfo.selectedRank
        };
        //console.log(data);
        if(confirm("수정하시겠습니까?")) {
            $.ajax({
                type: 'PUT',
                url: '/api/user',
                data: JSON.stringify({'data':body}),
                success: function(data) { window.location.assign("/pages/user/myInfo") },
                contentType: "application/json",
                dataType: 'json'
            });
        }
    });
})(jQuery)