(function ($) {
    var maxBtnSize = 7;              // 검색 하단 최대 범위
    var indexBtn = [];               // 인덱스 버튼

    $(document).ready(function () {
//        searchStart(0);
        getData();
    });

    // 데이터 리스트
    let itemList = new Vue({
        el: '#itemList',
        data: {
            list: [],
        }, methods: {
            btn: function (item) {
                console.log("abc");
                console.log(typeof(item.idx));
                // var id = item.idx;
                // window.location.assign("/pages/authuser/" + id);

                $.ajax({
                    type: 'GET',
                    url: '/api/authuser',
                    data: item,
                    success: function () {
                        window.location.assign("/pages/authuser/"+ item.idx)
                    },
                    contentType: "application/json",
                    dataType: 'json'
                });

            },
            setItemList: function( itemList ){
                this.list = itemList;

                console.log(this.list.idx);
                debugger
            }
        }
    });


    let contractTypeList = new Vue({
        el: '#contractTypeList',
        data: {
            list: [],
        }, method: {}
    });

    let departmentList = new Vue({
        el: '#departmentList',
        data: {
            list: [],
        }, method: {}
    });

    let teamList = new Vue({
        el: '#teamList',
        data: {
            list: [],
        }, method: {}
    });


    function getData() {
        $.get("/api/contract", function (res) {
            itemList.list = res.data;
        });

        $.get("/api/contractType", function (res) {
            contractTypeList.list = res.data;
        });

        $.get("/api/department", function (res) {
            departmentList.list = res.data;
        });

        $.get("/api/team", function (res) {
            teamList.list = res.data;
        });

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


    function searchStart(index) {
        //$.get("/api/contract?id=" + index, function (response) {
        $.get("/api/contract/"+index,function(response){
            /* 데이터 셋팅 */
            // 페이징 처리 데이터
            indexBtn = [];
            //   pagination = response.pagination;

            console.log(index);
            //전체 페이지
            showPage.totalElements = pagination.currentElements;
            showPage.currentPage = pagination.currentPage + 1;


            // 검색 데이터
            itemList.setItemList(response.data);
            // itemList.list = response.data;
            // console.log(itemList.list.idx);
            // debugger

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

    }

//조회
    $("#searchBtn").on("click", function (e) {
        console.log("search btn");
        var searchIndex = document.getElementById("contractNum").value;
        searchStart(searchIndex);

        debugger
        console.log(itemList.list.idx);
    });


    // $('button[name="abc"]').on("click",function(e){
    //     console.log("btn click!");
    //     // var authuserBtn = $(this);
    //     // var tr = authuserBtn.parent().parent();
    //     // console.log(tr);
    //     // var td = tr.children();
    //     // var id = td.eq(0).text();
    //     //
    //     //
    //     // $.ajax({
    //     //     type: 'GET',
    //     //     url: '/api/authuser',
    //     //     data: id,
    //     //     success: function(data) { window.location.assign("/pages/authuser") },
    //     //     contentType: "application/json",
    //     //     dataType: 'json'
    //     // });
    // });
})(jQuery);