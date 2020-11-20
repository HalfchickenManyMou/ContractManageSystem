(function ($) {

    let maxBtnSize = 7;              // 검색 하단 최대 범위
    let indexBtn = [];               // 인덱스 버튼

    $(document).ready(function () {
        search(0)

        $.get( '/api/departments' , function (response) {
            conditions.departments  = response.data;
            userModal.departments   = response.data;
        });

        $.get( '/api/teams' , function (response) {
            conditions.teams = response.data;
            userModal.teams  = response.data;
        });

        $.get( '/api/ranks' , function (response) {
            conditions.ranks = response.data;
            userModal.ranks  = response.data;
        });

        // table에 모두 선택 처리
        $('#selectAll').click(function(e){
            let table= $(e.target).closest('table');
            $('td input:checkbox',table).prop('checked',e.target.checked);

            if(e.target.checked){
                userList.userList.map( (element) =>{
                    Object.defineProperty( userList.selecteduserList, element.id, { value: element, configurable:true, enumerable:true } );
                })
            }else{
                userList.userList.map( (element) =>{
                    delete userList.selecteduserList[element.id]
                })
            }

            showPage.selectedElements = Object.entries( userList.selecteduserList ).length

            userList.amountSelect = 10;
        });

    });

    // Date 객체를 format에 맞는 string으로 변환
    function dateString( date ){
        date =  [date.getFullYear(),date.getMonth().toString().padStart(2,'0'),date.getDate().toString().padStart(2,'0')].join("-")
        date = /(?<DateFormat>[0-9]{4}-[0-9]{2}-[0-9]{2})/.exec(date);
        return ( date )? date.groups['DateFormat'] : null ;
    }

    // 데이터 받아오기
    function search(index,conditions) {
        let URL = "/api/users"

        if( index!=null) URL = URL.concat( "?page="+index );

        if( conditions!=null) URL = URL.concat( conditions );

        console.log("URL : ", URL );

        $.get( URL, function (response) {
            /* 데이터 셋팅 */
            // 페이징 처리 데이터
            indexBtn = [];
            pagination = response.pagination;

            //전체 페이지
            showPage.total_elements      = pagination.current_elements;
            showPage.current_page        = pagination.current_page+1;
            // 검색 데이터
            userList.userList = response.data;

            window.testtest = response.data

            // 이전버튼
            if(pagination.current_page === 0){
                $('#previousBtn').addClass("disabled")
            }else{
                $('#previousBtn').removeClass("disabled")
            }
            // 다음버튼
            if(pagination.current_page === pagination.total_pages-1){
                $('#nextBtn').addClass("disabled")
            }else{
                $('#nextBtn').removeClass("disabled")
            }

            // 페이징 버튼 처리
            var temp = Math.floor(pagination.current_page / maxBtnSize);
            for(var i = 1; i <= maxBtnSize; i++){
                var value = i+(temp*maxBtnSize);

                if(value <= pagination.total_pages){
                    indexBtn.push(value)
                }
            }

            // 페이지 버튼 셋팅
            pageBtnList.btnList = indexBtn;

            // 색상처리
            setTimeout(function () {
                $('li[btn_id]').removeClass( "active" );
                $('li[btn_id='+(pagination.current_page+1)+']').addClass( "active" );
            },50)
        });
    }


    // 상세 조회 처리 데이터
    let conditions = new Vue({
        el : '#queryConditions',
        data : {
            item            : {
                name        :   "",
                user_code    :   "",
                department  :   "",
                team        :   "",
                rank        :   "",
            },
            departments     : [],
            teams           : [],
            ranks           : [],


        },methods: {
            searchUser  : function () {
                let param = {}

                param.name          = (this.item.name=="")? undefined : this.item.name
                param.user_code      = (this.item.user_code=="")? undefined : this.item.user_code
                param.departmentIdx = this.departments.filter( item=> item[1] == this.item.department  )?.[0]?.[0]
                param.teamIdx       = this.teams.filter( item=> item[1] == this.item.team  )?.[0]?.[0]
                param.rankIdx       = this.ranks.filter( item=> item[1] == this.item.rank )?.[0]?.[0]

                search(0,
                    Object.entries( param ).filter( item=>item[1]!=undefined ).reduce( (acc,cur)=> { return acc.concat('&',cur.join('=') );} ,"" )
                );
            },
            createUser  : function () {
                userModal.modalMode = 0;
                userModal.item      = {};
                $('#userModal').modal();
            },
        }
    });



    // 페이징 처리 데이터
    let pagination = {
        total_pages         :  0,       // 전체 페이지수
        total_elements      :  0,       // 전체 데이터수
        current_page        :  0,       // 현재 페이지수
        currentElements     :  0,        // 현재 데이터수
        amount_per_Page     :  10,
    };
    // 페이지 정보
    let showPage = new Vue({
        el : '#showPage',
        data : {
            total_elements       : {},
            current_page         : {},
            selectedElements    : 0,    // 현재 조건 중 선택된 값들의 수
        }
    });
    // 데이터 리스트
    let userList = new Vue({
        el : '#users_table',
        data : {
            userList            : 0,
            selectedontractList : 0,
            amountSelect        : 0    // 현재 page에서 보여지는 값들중 선택된 값의 수
        },methods:{
            updateHandler : function( event, user ){
                userModal.modalMode             = 1;
                userModal.item                  = $.extend(true, {}, user );

                $('#userModal').modal().off()
            },
        }
    });
    // 페이지 버튼 리스트
    let pageBtnList = new Vue({
        el : '#pageBtn',
        data : {
            btnList : {}
        },
        methods: {
            indexClick: function (event) {
                let id = parseInt( event.target.getAttribute("btn_id") );
                search(id-1, conditions.getParameter());
            },
            previousClick:function (event) {
                if(pagination.current_page !== 0){
                    search(pagination.current_page-1, conditions.getParameter() );
                }
            },
            nextClick:function (event) {
                if(pagination.current_page !== pagination.total_pages-1){
                    search(pagination.current_page+1, conditions.getParameter() );
                }
            }
        },
        mounted:function () {
            // 제일 처음 랜더링 후 색상 처리
            setTimeout(function () {
                $('li[btn_id]').removeClass( "active" );
                $('li[btn_id='+(pagination.current_page+1)+']').addClass( "active" );
            },50)
        }
    });



    let userModal = new Vue({
        el: '#userModal',
        data: {
            modalMode       : 0,    // modal type 지정  0:create / 1:update
            item            : {
                user_code                :   "",
                name                    :   "",
                email                   :   "",

                department_idx          :   "",
                team_idx                :   "",
                rank_idx                :   "",

                phone_number            :   "",
            },
            departments     : [],
            teams           : [],
            ranks           : [],

        },methods: {
            saveHandler: function ( event ){
                let postBody = Object.entries( this.item ).filter( item=>item[1]!='')
                    .reduce( (acc,cur)=> { acc[cur[0]] = cur[1]; return acc; } ,{ } )


                postBody.department_idx = this.departments.filter( item=> item[1] == this.item.department_idx  )?.[0]?.[0]
                postBody.team_idx       = this.teams.filter( item=> item[1] == this.item.team_idx  )?.[0]?.[0]
                postBody.rank_idx       = this.ranks.filter( item=> item[1] == this.item.rank_idx )?.[0]?.[0]

                console.log("call saveHandler!!")
                console.log("call postBody!!",postBody)

                search( 0 )

                $.ajax({
                    type: 'POST',
                    url: '/api/user',
                    data: JSON.stringify({'data':postBody}), // or JSON.stringify ({name: 'jonas'}),
                    success: function(data) {

                        userModal.initHandler();

                        search(0);

                        alert("create done");

                        $('#userModal').modal("hide");
                    },
                    error: function ( ) {
                        alert("create failed");
                    },
                    contentType: "application/json",
                    dataType: 'json'
                });



            },
            updateHandler: function ( event ){
                let postBody = Object.entries( this.item ).filter( item=>item[1]!='')
                    .reduce( (acc,cur)=> { acc[cur[0]] = cur[1]; return acc; } ,{ } )


                postBody.department_idx = this.departments.filter( item=> item[1] == this.item.department_idx  )?.[0]?.[0]
                postBody.team_idx       = this.teams.filter( item=> item[1] == this.item.team_idx  )?.[0]?.[0]
                postBody.rank_idx       = this.ranks.filter( item=> item[1] == this.item.rank_idx )?.[0]?.[0]

                console.log("call updateHandler!!")
                console.log("call postBody!!",postBody)

                search( 0 )

                $.ajax({
                    type: 'PUT',
                    url: '/api/user',
                    data: JSON.stringify({'data':postBody}), // or JSON.stringify ({name: 'jonas'}),
                    success: function(data) {

                        userModal.initHandler();

                        // search(0);

                        alert("update done");

                        $('#userModal').modal("hide");
                    },
                    error: function ( ) {
                        alert("create failed");
                    },
                    contentType: "application/json",
                    dataType: 'json'
                });



            },
            deleteHandler: function ( idx ){

                $.ajax({
                    type: 'DELETE',
                    url: '/api/user/'+idx,
                    success: function(data) {

                        userModal.initHandler();

                        search(0);

                        alert("delete done");

                        $('#userModal').modal("hide");
                    },
                    error: function ( ) {
                        alert("create failed");
                    },
                    contentType: "application/json",
                    dataType: 'json'
                });

            },
            initHandler:function ( date ) {
                this.item = {
                    user_code                :   "",
                    name                    :   "",
                    email                   :   "",

                    department_idx          :   "",
                    team_idx                :   "",
                    rank_idx                :   "",

                    phone_number            :   "",
                }
            },
            closeHandler: function ( event ){
                this.item = {
                    user_code                :   "",
                    name                    :   "",
                    email                   :   "",

                    department_idx          :   "",
                    team_idx                :   "",
                    rank_idx                :   "",

                    phone_number            :   "",
                }
                $('#userModal').modal("hide");
            }
        },mounted: function( ) {
            this.initHandler();
        }
    })


})(jQuery);