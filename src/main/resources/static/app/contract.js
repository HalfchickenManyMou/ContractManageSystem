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
        $('#createDate').datepicker({
            format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
            autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
            startDate: '-10d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
            language : "ko"	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
        }).on('changeDate', function (event) {
            conditions.setCreateDate( dateString(event.date) )
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
        $.get(["/api/contract?page="+index,conditions].join('&'), function (response) {
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
            idx             :   "",
            idx             :   "",
            idx             :   "",
            idx             :   "",
            idx             :   "",
            idx             :   "",
            idx             :   "",
            idx             :   "",
            idx             :   "",
            idx             :   "",
            idx             :   "",



            selectcontract  :   [],

        },methods: {
            searchcontracts : function () {
                search(0, this.getParameter() );

                contractList.amountSelect       = 0;
                contractList.selectedcontractList   = {};

            },
            createcontract  : function () {
                location.href = "/pages/contract/enroll"
            },
            setCreateDate:function ( date ) {
                this.createDate = date;
            },
            setExpireDate:function ( date ) {
                this.expireDate = date;
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
            handlerCheckBox: function(event){
                event.stopImmediatePropagation();

                let seletedcontract = this.contractList[ parseInt( event.target.getAttribute("index") ) ];

                if(event.target.checked){
                    Object.defineProperty( this.selectedcontractList, seletedcontract.id, { value: seletedcontract, configurable:true, enumerable:true } );
                    this.amountSelect += 1;
                }else{
                    delete this.selectedcontractList[seletedcontract.id];
                    this.amountSelect -= 1;
                }

                showPage.selectedElements = Object.entries( this.selectedcontractList ).length

                $('#selectAll input').prop('checked',(this.amountSelect==10)? true : false );
            },
            denoteCheckBox: function( ){
                let contracts = $("#contracts_table").find( "td input:checkbox" ).toArray()
                    .filter(element=>( this.selectedcontractList.hasOwnProperty( element.getAttribute("contractId"))) )
                    .map( (element)=>{
                        element.checked = true;
                    })

                this.amountSelect = contracts.length;

                $('#selectAll input').prop('checked',(contracts.length==10)? true : false );

            },
            disableAllCheckBox: function( ){
                $("#contracts_table").find( "td input:checkbox" ).prop('checked',false );
            },
            setcontractList: function( contractList ){
                this.disableAllCheckBox( );
                this.contractList = contractList;
                setTimeout( ()=>{
                    this.denoteCheckBox( )
                },50);
            },
            contractRowHandler : function( event, contract ){
                contractModal.pageMode              = 1;
                contractModal.selectedcontract      = $.extend(true, {}, contract );
                contractModal.modalSelectcontract   = conditions.selectcontract;
                contractModal.modalSelectRental     = conditions.selectRental;

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
        el: '#contractModalForm',
        data: {
            pageMode            : 0,    // modal type 지정  0:create / 1:update
            selectedContract    : {},

        },methods: {
            initCategory: function( ){
                this.selectCate01  = Object.keys( this.categories );
                this.selectCate02  = Object.keys( this.categories[this.selectedcontract.superCate] )
                this.selectCate03  = this.categories[this.selectedcontract.superCate][this.selectedcontract.subCateFirst];

                this.isChange  =  false;
            },
            handleCate01: function () {
                if( this.categories.hasOwnProperty( this.selectedcontract.superCate) ) {
                    this.selectCate02 = Object.keys(this.categories[this.selectedcontract.superCate]);
                    this.selectedcontract.subCateFirst = ""
                    this.selectedcontract.subCateSecond = ""
                }
            },
            handleCate02: function () {
                if (this.categories[this.selectedcontract.superCate].hasOwnProperty(this.selectedcontract.subCateFirst)) {
                    this.selectCate03 = this.categories[this.selectedcontract.superCate][this.selectedcontract.subCateFirst];
                    this.selectedcontract.subCateSecond = ""
                }
            },
            updatecontract  : function ( updateUser ) {
                Object.entries(this._data.selectedcontract).map((t)=>{console.log("T : ",t)});

                console.log( "validation : "+this.validation() )

                let postBody = Object.entries(this._data.selectedcontract)
                    .filter( (v)=>( (v[1]!=null)&&(v[1].constructor!=Object)&&(v[1].constructor!=Array) ))
                    .reduce( (acc,cur)=>{ acc[cur[0]] = cur[1]; return acc;  }, {} );

                Object.defineProperty(postBody, 'updateUser', { value : updateUser})

                $.ajax({
                    type: 'PUT',
                    url: '/api/contract',
                    data: JSON.stringify({'data':postBody}), // or JSON.stringify ({name: 'jonas'}),
                    success: function(data) { alert('data: ' + data); },function(response){
                        console.log( "response : ",response)
                    },
                    contentType: "application/json",
                    dataType: 'json'
                });
            },
            closeHandler: function ( event ){
                if( !this.validation() ){
                    console.log( "not changed ")
                    $('#contractModal').modal("hide");
                }else{
                    console.log( "changed ");

                }


            },validation: function(){
                let originData = contractList.contractList.filter((contract)=>(contract.id==contractModal.selectedcontract.id))[0];
                return Object.entries( contractModal.selectedcontract ).reduce( ( acc, cur )=>{ return acc || (originData[cur[0]]!=cur[1]) }, false )
            }
        },mounted: function( ) {
            // 등록일 datepicker 처리
            $('#modalRegisterDate').datepicker({
                format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
                autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
                startDate: '-10d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
                language : "ko"	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
            }).on('changeDate', function (event) {
                console.log("changeDate : ")
                contractModal.selectedcontract.createDate = dateString(event.date);
            }).unbind('change');

            // 만료일 datepicker 처리
            $('#modalExpireDate').datepicker({
                format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
                autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
                startDate: '-10d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
                language : "ko"	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
            }).on('changeDate', function (event) {
                contractModal.selectedcontract.exprieDate =  dateString(event.date);
            }).unbind('change');
        }
    })

    // for test
    window.updateUser       = 1;
    window.contractList     = contractList;
    window.contractModal    = contractModal;

})(jQuery);