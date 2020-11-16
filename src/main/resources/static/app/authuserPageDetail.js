(function ($) {

    let maxBtnSize = 7;              // 검색 하단 최대 범위
    let indexBtn = [];               // 인덱스 버튼

    // 페이징 처리 데이터
    let pagination = {
        totalPages         :  0,       // 전체 페이지수
        totalElements      :  0,       // 전체 데이터수
        currentPage        :  0,       // 현재 페이지수
        currentElements    :  0,        // 현재 데이터수
        amountPerPage      :  10,
    };

    // 페이지 정보
    let showPage = new Vue({
        el : '#showPage',
        data : {
            totalElements       : {},
            currentPage         : {},
            selectedElements    : 0,    // 현재 조건 중 선택된 값들의 수
        }
    });

    // 데이터 리스트
    let itemList = new Vue({
        el : '#itemList',
        data : {
            itemList         : {},
            selectedItemList : {},
            amountSelect     : 0    // 현재 page에서 보여지는 값들중 선택된 값의 수
        },methods:{
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
                if(pagination.currentPage !== 0){
                    search(pagination.currentPage-1, conditions.getParameter() );
                }
            },
            nextClick:function (event) {
                if(pagination.currentPage !== pagination.totalPages-1){
                    search(pagination.currentPage+1, conditions.getParameter() );
                }
            }
        },
        mounted:function () {
            // 제일 처음 랜더링 후 색상 처리
            setTimeout(function () {
                $('li[btn_id]').removeClass( "active" );
                $('li[btn_id='+(pagination.currentPage+1)+']').addClass( "active" );
            },50)
        }
    });


    $(document).ready(function () {
     //   search(0)
        getData();

    });

    function getData( ) {
        $.get("/api/authuser",function(res){
            itemList.list=res.data;
        });
    }
 //받아오기
 //    function search(index,conditions) {
 //        $.get(["/api/items?page="+index,conditions].join('&'), function (response) {
 //            /* 데이터 셋팅 */
 //            // 페이징 처리 데이터
 //            indexBtn = [];
 //            pagination = response.pagination;
 //
 //            //전체 페이지
 //            showPage.totalElements      = pagination.currentElements;
 //            showPage.currentPage        = pagination.currentPage+1;
 //            // 검색 데이터
 //            itemList.setItemList( response.data );
 //
 //            // 이전버튼
 //            if(pagination.currentPage === 0){
 //                $('#previousBtn').addClass("disabled")
 //            }else{
 //                $('#previousBtn').removeClass("disabled")
 //            }
 //            // 다음버튼
 //            if(pagination.currentPage === pagination.totalPages-1){
 //                $('#nextBtn').addClass("disabled")
 //            }else{
 //                $('#nextBtn').removeClass("disabled")
 //            }
 //
 //            // 페이징 버튼 처리
 //            var temp = Math.floor(pagination.currentPage / maxBtnSize);
 //            for(var i = 1; i <= maxBtnSize; i++){
 //                var value = i+(temp*maxBtnSize);
 //
 //                if(value <= pagination.totalPages){
 //                    indexBtn.push(value)
 //                }
 //            }
 //
 //            // 페이지 버튼 셋팅
 //            pageBtnList.btnList = indexBtn;
 //
 //            // 색상처리
 //            setTimeout(function () {
 //                $('li[btn_id]').removeClass( "active" );
 //                $('li[btn_id='+(pagination.currentPage+1)+']').addClass( "active" );
 //            },50)
 //        });
 //    }


})(jQuery);