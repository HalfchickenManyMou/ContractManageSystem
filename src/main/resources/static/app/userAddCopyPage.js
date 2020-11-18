(function ($) {

    $(document).ready(function () {
        getData();
    });

    let registerUser = new Vue({
        el : '#registerUser',
        data : {
            user : {
               userCode : "",
               name : "",
               email : "",
               phoneNumber : "",
            },
            departmentCategory : [],
            rankCategory : [],
            selectedDepartment : {
            },
            selectedTeam : {
            },
            selectedRank : {
            }
        }, methods : {
            departmentHandle: function () {
                let tmp = JSON.parse(JSON.stringify(registerUser.departmentCategory)); //깊은복사>
                registerUser.selectedDepartment = tmp.find((v) => (v.department === registerUser.selectedDepartment.department));
            },
            teamHandle: function () {
                let tmp = registerUser.selectedDepartment.team_list;
                for(let i =0; i < tmp.length; i++){
                    if(tmp[i].team === registerUser.selectedTeam.team) registerUser.selectedTeam = tmp[i];
                }
            },
            rankHandle:function () {
                let tmp = JSON.parse(JSON.stringify(registerUser.rankCategory)); //깊은복사
                registerUser.selectedRank = tmp.find((v) => (v.rank_name === registerUser.selectedRank.rank_name));
            }
    }
    });

    //데이터 받아오기
    function getData(){

        $.get("/api/department", function (res) {
            registerUser.departmentCategory = res.data;
            //editUserInfo.departmentCategory = editUserInfo.departmentCategory.filter((v) => (v.idx !== editUserInfo.selectedDepartment.idx));
        })

        $.get("/api/rank", function (res) {
            registerUser.rankCategory = res.data;
        })
    }

    $("#saveBtn").on("click", function (e) {
        let body = {
            user_code : registerUser.user.userCode,
            name : registerUser.user.name,
            email : registerUser.user.email,
            phone_number :  registerUser.user.phoneNumber,
            department: registerUser.selectedDepartment,
            team : registerUser.selectedTeam,
            rank : registerUser.selectedRank
        }
        console.log(body);

        if(confirm("등록하시겠습니까?")) {
            $.ajax({
                type: 'POST',
                url: '/api/user',
                data: JSON.stringify({'data':body}),
                success: function(data) { window.location.assign("/pages/user/admin/add") },
                error: function (err){
                    if(err.responseJSON.description === "This user_code is a duplicate"){
                        alert("중복된 사원번호입니다");
                    }else alert("중복된 이메일입니다");
                  console.log(err);
                },
                contentType: "application/json",
                dataType: 'json'
            });
        }
    });

    $("#cancelBtn").on("click", function (e) {
        if(confirm("등록을 취소하시겠습니까?")) {
              window.location.assign("/pages/user/admin/add")
        }
    });

})(jQuery)