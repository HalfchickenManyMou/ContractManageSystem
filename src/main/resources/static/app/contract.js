(function ($) {

    let maxBtnSize = 7;              // 검색 하단 최대 범위
    let indexBtn = [];               // 인덱스 버튼

    $(document).ready(function () {
        search(0)

        // table에 모두 선택 처리
        $('#selectAll').click(function(e){
            let table= $(e.target).closest('table');
            $('td input:checkbox',table).prop('checked',e.target.checked);

            if(e.target.checked){
                contractList.contractList.map( (element) =>{
                    Object.defineProperty( contractList.selectedcontractList, element.id, { value: element, configurable:true, enumerable:true } );
                })
            }else{
                contractList.contractList.map( (element) =>{
                    delete contractList.selectedcontractList[element.id]
                })
            }

            showPage.selectedElements = Object.entries( contractList.selectedcontractList ).length

            contractList.amountSelect = 10;
        });

        // 등록일 datepicker 처리
        $('#start_date').datepicker({
            format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
            autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
            startDate: '-10d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
            language : "ko"	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
        }).on('changeDate', function (event) {
            conditions.setCreateDate( dateString(event.date) )
        });
        // 등록일 datepicker 처리
        $('#start_date_to').datepicker({
            format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
            autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
            startDate: '-10d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
            language : "ko"	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
        }).on('changeDate', function (event) {
            conditions.setCreateDateto( dateString(event.date) )
        });

        // 등록일 datepicker 처리
        $('#end_date').datepicker({
            format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
            autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
            startDate: '-10d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
            language : "ko"	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
        }).on('changeDate', function (event) {
            conditions.setEndDate( dateString(event.date) )
        });
        // 등록일 datepicker 처리
        $('#end_date_to').datepicker({
            format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
            autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
            startDate: '-10d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
            language : "ko"	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
        }).on('changeDate', function (event) {
            conditions.setEndDateTo( dateString(event.date) )
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
        let URL = "/api/contract"

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
            contractList.contractList = response.data;

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
            item : {
                code                   :   "",
                name                   :   "",
                registerUser           :   "",
                contractTypeIdx        :   "",

                ownerName              :   "",
                ownerBusinessNumber    :   "",
                ownerAddress           :   "",

                otherName              :   "",
                otherBusinessNumber    :   "",
                otherAddress           :   "",

                startDate              :   "",
                startDateTo            :   "",

                endDate                :   "",
                endDateTo              :   "",
            }

        },methods: {
            searchContract  : function () {
                search(0,
                Object.entries( this.item ).filter( item=>item[1]!='').reduce( (acc,cur)=> { return acc.concat('&',cur.join('=') );} ,"" )
                );
            },
            createContract  : function () {
                contractModal.modalMode = 0;
                contractModal.item      = {};
                $('#contractModal').modal();
            },
            setCreateDate:function ( date ) {
                this.startDate = date;
            },
            setCreateDateTo:function ( date ) {
                this.startDateTo = date;
            },
            setEndDate:function ( date ) {
                this.endDate = date;
            },
            setEndDateTo:function ( date ) {
                this.endDateTo = date;
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
    let contractList = new Vue({
        el : '#contracts_table',
        data : {
            contractList         : {},
            selectedontractList : {},
            amountSelect     : 0    // 현재 page에서 보여지는 값들중 선택된 값의 수
        },methods:{
            updateHandler : function( event, contract ){
                contractModal.modalMode             = 1;
                contractModal.item                  = $.extend(true, {}, contract );

                $('#contractModal').modal().off()
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



    let contractModal = new Vue({
        el: '#contractModal',
        data: {
            modalMode   : 0,    // modal type 지정  0:create / 1:update
            item        : {},

        },methods: {
            saveHandler: function ( event ){
                let postBody = Object.entries( this.item ).filter( item=>item[1]!='')
                    .reduce( (acc,cur)=> { acc[cur[0]] = cur[1]; return acc; } ,{ } )

                console.log("call saveHandler!!")
                console.log("call postBody!!",postBody)

                $.ajax({
                    type: 'POST',
                    url: '/api/contract',
                    data: JSON.stringify({'data':postBody}), // or JSON.stringify ({name: 'jonas'}),
                    success: function(data) {

                        contractModal.initHandler();

                        search(0);

                        alert("create done");

                        $('#contractModal').modal("hide");
                    },
                    contentType: "application/json",
                    dataType: 'json'
                });



            },
            updateHandler: function ( event ){
                let postBody = Object.entries( this.item ).filter( item=>item[1]!='')
                    .reduce( (acc,cur)=> { acc[cur[0]] = cur[1]; return acc; } ,{ } )

                $.ajax({
                    type: 'PUT',
                    url: '/api/contract',
                    data: JSON.stringify({'data':postBody}), // or JSON.stringify ({name: 'jonas'}),
                    success: function(data) {

                        contractModal.initHandler();

                        search(0);

                        alert("update done");

                        $('#contractModal').modal("hide");
                    },
                    contentType: "application/json",
                    dataType: 'json'
                });



            },
            deleteHandler: function ( idx ){

                $.ajax({
                    type: 'DELETE',
                    url: '/api/contract/'+idx,
                    success: function(data) {

                        contractModal.initHandler();

                        search(0);

                        alert("delete done");

                        $('#contractModal').modal("hide");
                    },
                    contentType: "application/json",
                    dataType: 'json'
                });



            },
            setStartDate:function ( date ) {
                this.item.start_date = date;
            },
            initHandler:function ( date ) {
                this.item = {
                    code                   :   "",
                    name                   :   "",
                    register_user           :   "",
                    contract_type_idx        :   "",

                    owner_name              :   "",
                    owner_business_number    :   "",
                    owner_address           :   "",

                    other_name              :   "",
                    other_business_number    :   "",
                    other_address           :   "",

                    start_date              :   "",
                    start_date_to            :   "",

                    end_date                :   "",
                    end_date_to              :   "",
                }
            },
            setEndDate:function ( date ) {
                this.item.end_date = date;
            },
            closeHandler: function ( event ){
                this.item = {};
                $('#contractModal').modal("hide");
            }
        },mounted: function( ) {
            this.initHandler();

            // 등록일 datepicker 처리
            $('#start_date_modal').datepicker({
                format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
                autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
                startDate: '-10d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
                language : "ko"	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
            }).on('changeDate', function (event) {
                contractModal.setStartDate( dateString(event.date) )
            });

            // 등록일 datepicker 처리
            $('#end_date_modal').datepicker({
                format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
                autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
                startDate: '-10d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
                language : "ko"	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
            }).on('changeDate', function (event) {
                contractModal.setEndDate( dateString(event.date) )
            });
        }
    })

    // for test
    window.updateUser       = 1;
    window.contractList     = contractList;
    window.contractModal    = contractModal;

})(jQuery);