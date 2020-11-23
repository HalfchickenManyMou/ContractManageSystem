(function ($) {

    // 권한 수정할 계약서 정보
    let itemList = new Vue({
        el : '#itemList',
        data : {
            list         : [],
        },methods:{
        }
    });

    //  전체 user 정보
    let userList = new Vue({
        el: '.userList',
        data : {
            list:[],
        },methods:{

        }
    })

    // 전체 dept 종류
    let deptList= new Vue({
        el : '.deptList',
        data : {
            list : [],
        },methods:{

        }
    });

    // 전체 team 종류
    let teamList= new Vue({
        el : '.teamList',
        data : {
            list : [],

        },methods:{

        }
    });



    //  전체 user 정보
    let userMList = new Vue({
        el: '.userMList',
        data : {
            list:[],
        },methods:{

        }
    })

    // 전체 dept 종류
    let deptMList= new Vue({
        el : '.deptMList',
        data : {
            list : [],
        },methods:{

        }
    });

    // 전체 team 종류
    let teamMList= new Vue({
        el : '.teamMList',
        data : {
            list : [],

        },methods:{

        }
    });




    //해당 계약서의 권한을 가진 부서 정보
    let authDeptList= new Vue({
        el : '#authDeptList',
        data : {
            list : [],
        },methods:{

        }
    });

    //해당 계약서의 권한을 가진 팀 정보
    let authTeamList = new Vue({
        el:'#authTeamList',
        data:{
            list:[],
        },methods:{

        }
    });

    //해당 계약서의 권한을 가진 유저 정보
    let authUserList = new Vue({
        el:'#authUserList',
        data:{
            list:[],
        },methods:{

        }
    });


    //해당 계약서의 권한을 가진 부서 정보
    let authDeptListM= new Vue({
        el : '#authDeptListM',
        data : {
            list : [],
        },methods:{

        }
    });

    //해당 계약서의 권한을 가진 팀 정보
    let authTeamListM = new Vue({
        el:'#authTeamListM',
        data:{
            list:[],
        },methods:{

        }
    });

    //해당 계약서의 권한을 가진 유저 정보
    let authUserListM = new Vue({
        el:'#authUserListM',
        data:{
            list:[],
        },methods:{

        }
    });
//테스트용
    let testList = new Vue({
        el:'#testList',
        data:{
            list:[],
        },methods:{

        }
    });

    $(document).ready(function () {
        getData();
        debugger
    });

    // function getParameterByName(name) {
    //     name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    //     var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    //         results = regex.exec(location.search);
    //     return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    // }

    function getData( ) {

        itemList.list.idx=2;
        itemList.list.name="name";
        itemList.list.contractTypeIdx="계약서 타입 3";
        itemList.list.usercode="test1";
        debugger
        $.get("/api/department", function (res) {
            deptList.list = res.data;
            deptMList.list=res.data;

        });
        debugger

        $.get("/api/team", function (res) {
            teamList.list = res.data;
            teamMList.list=res.data;
        });

        $.get("/api/authuser", function(res){
            userList.list=res.data;
            userMList.list=res.data;
        })

        $.get("/api/authdept", function(res){
            authDeptList.list=res.data;
            authDeptListM.list=res.data;
        })
        $.get("/api/authteam", function(res){
            authTeamList.list=res.data;
            authTeamListM.list=res.data;
        })
        $.get("/api/authuser", function(res){
            authUserList.list = res.data;
            authUserListM.list=res.data;
        })
    }



//계약서 읽기 권한 추가
    $("#addDept").on("click", function (e) {
        let tbody = $("#authDeptList");
        tbody.append($(".deptList option:selected").val()+',  ');

    });
    $("#addTeam").on("click", function (e) {
        let tbody = $("#authTeamList");
        tbody.append($(".teamList option:selected").val()+', ');

    });
    $("#addPerson").on("click", function (e) {
        let tbody = $("#authUserList");
        tbody.append($(".userList option:selected").val()+', ');

    });


    //계약서 수정 권한 추가
    $("#addMDept").on("click", function (e) {
        let tbody = $("#authDeptListM");
        tbody.append($(".deptMList option:selected").val()+',  ');

    });
    $("#addMTeam").on("click", function (e) {
        let tbody = $("#authTeamListM");
        tbody.append($(".teamMList option:selected").val()+', ');

    });
    $("#addMPerson").on("click", function (e) {
        let tbody = $("#authUserListM");
        tbody.append($(".userMList option:selected").val()+', ');

    });




//save button

})(jQuery);