(function ($) {
    var maxBtnSize = 7;              // 검색 하단 최대 범위
    var indexBtn = [];               // 인덱스 버튼


    $(document).ready(function () {
        searchStart(0);
        getData();
    });


    // 데이터 리스트
    let itemList = new Vue({
        el: '#itemList',
        data: {
            itemList: [],
        }
    });
    debugger


    let contractTypeList = new Vue({
        el: '#contractTypeList',
        data:{
            contractTypeList:[],
        }
    });
    debugger;

    let departmentList = new Vue({
        el: '#departmentList',
        data:{
            departmentList:[],
        }
    });
    debugger

    let teamList = new Vue({
        el: '#teamList',
        data:{
            teamList:[],
        }
    });
    debugger

    function getData( ) {
        $.get("/api/contract",function(res){
            itemList.list= res.data;
        });

        $.get("/api/contractType",function (res){
            //contractTypeList.list=res.data;
           // // contractTypeList.list=console.log(res.data['type']);
             contractTypeList.list=res.data;
        });

        $.get("/api/department",function (res){
            departmentList.list=res.data;
        });

        $.get("/api/team",function (res){
            teamList.list=res.data;
        });
        debugger

    }


    // 페이징 처리 데이터
    var pagination = {
        total_pages: 0,            // 전체 페이지수
     //   total_elements: 0,         // 전체 데이터수
        currentPage: 0,          // 현재 페이지수
        currentElements: 0,        // 현재 데이터수
        amountPerPage: 10,
    };


    // 페이지 정보
    var showPage = new Vue({
        el: '#showPage',
        data: {
            totalElements: {},
            currentPage: {},

        }
    });

    // 페이지 버튼 리스트
    var pageBtnList = new Vue({
        el: '#pageBtn',
        data: {
            btnList: {}
        },
        methods: {
            indexClick: function (id) {
                searchStart(id - 1)
            },
            previousClick: function () {
                if (pagination.currentPage !== 0) {
                    searchStart(pagination.currentPage - 1);
                }
            },
            nextClick: function () {

                if (pagination.currentPage !== pagination.total_pages - 1) {
                    searchStart(pagination.currentPage + 1);
                }
            }
        },
        mounted: function () {
            // 제일 처음 랜더링 후 색상 처리
            setTimeout(function () {
                $('li[btn_id]').removeClass("active");
                $('li[btn_id=' + (pagination.currentPage + 1) + ']').addClass("active");
            }, 50)
        }
    });

    function move(index){
        System.out.println("movemove");
    };


    function searchStart(index) {
        $.get("/api/contract?id=" + index, function (response) {

            /* 데이터 셋팅 */
            // 페이징 처리 데이터
            indexBtn = [];
         //   pagination = response.pagination;


            //전체 페이지
            showPage.totalElements = pagination.currentElements;
            showPage.currentPage = pagination.currentPage + 1;
            debugger

            // 검색 데이터
            itemList.itemList = response.data;

            // 이전버튼
            if (pagination.currentPage === 0) {
                $('#previousBtn').addClass("disabled")
            } else {
                $('#previousBtn').removeClass("disabled")
            }


            // 다음버튼
            if (pagination.currentPage === pagination.total_pages - 1) {
                $('#nextBtn').addClass("disabled")
            } else {
                $('#nextBtn').removeClass("disabled")
            }

            // 페이징 버튼 처리
            var temp = Math.floor(pagination.currentPage / maxBtnSize);
            for (var i = 1; i <= maxBtnSize; i++) {
                var value = i + (temp * maxBtnSize);

                if (value <= pagination.total_pages) {
                    indexBtn.push(value)
                }
            }

            // 페이지 버튼 셋팅
            pageBtnList.btnList = indexBtn;


            // 색상처리
            setTimeout(function () {
                $('li[btn_id]').removeClass("active");
                $('li[btn_id=' + (pagination.currentPage + 1) + ']').addClass("active");
            }, 50)
        });

        debugger
    }

//조회
    $("#searchBtn").on("click", function (e) {
        var searchIndex = document.getElementById("contractNum").value;
        searchStart(searchIndex);
        // $.get("/api/contract?id=" + searchIndex, function (response){
        //     itemList.list=response.data;
        // })
    });

})(jQuery);