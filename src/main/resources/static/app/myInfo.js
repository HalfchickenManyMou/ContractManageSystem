(function ($) {

<<<<<<< HEAD
    $(document).ready(function () {
        deleteLine();
        getData();
    });

    let myData = new Vue({
        el : '#myData',
        data : {
            list :[],

        },methods:{
            deleteLine: function (event) {
                let target = event.target.parentNode.parentNode
                target.remove();
            }
        }
    });

    //데이터 받아오기
    function getData(){
        $.get("/api/myInfo", function (res) {
            myData.list = res.data;
        })
    }


    $("#updateBtn").on("click", function (e) {
        let myInfo={
            "user_code"    : document.getElementById("id"),
            "name": document.getElementById("inputName"),
            "email": document.getElementById("email"),
            "pwd": document.getElementById("npwd"),
            "deptartment_idx": document.getElementById("dept_id"),
            "team_idx": document.getElementById("team_id"),
            "rank": document.getElementById("rank"),
            "phone_number": document.getElementById("phone_num")
        }

        if(confirm("수정하시겠습니까?")) {
            $.ajax({
                type: 'POST',
                url: '/api/myInfo',
                data: JSON.stringify({'data':myInfo}),
                success: function(data) { window.location.assign("/pages/myInfo") },
                contentType: "application/json",
                dataType: 'json'
            });
        }

    });



})(jQuery)
=======
    var maxBtnSize = 7;              // 검색 하단 최대 범위
    var indexBtn = [];               // 인덱스 버튼

    // 페이징 처리 데이터
    var pagination = {
        total_pages: 0,            // 전체 페이지수
        total_elements: 0,         // 전체 데이터수
        current_page: 0,          // 현재 페이지수
        current_elements: 0,        // 현재 데이터수
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

    // 데이터 리스트
    var itemList = new Vue({
        el: '#itemList',
        data: {
            itemList: {}
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
                if (pagination.current_page !== 0) {
                    searchStart(pagination.current_page - 1);
                }
            },
            nextClick: function () {

                if (pagination.current_page !== pagination.total_pages - 1) {
                    searchStart(pagination.current_page + 1);
                }
            }
        },
        mounted: function () {
            // 제일 처음 랜더링 후 색상 처리
            setTimeout(function () {
                $('li[btn_id]').removeClass("active");
                $('li[btn_id=' + (pagination.current_page + 1) + ']').addClass("active");
            }, 50)
        }
    });


    $('#search').click(function () {
        searchStart(0)
    });

    $(document).ready(function () {
        searchStart(0)
    });

    function searchStart(index) {
        console.log("call index : " + index);
        $.get("/api/user?page=" + index, function (response) {

            /* 데이터 셋팅 */
            // 페이징 처리 데이터
            indexBtn = [];
            pagination = response.pagination;


            //전체 페이지
            showPage.totalElements = pagination.current_elements;
            showPage.currentPage = pagination.current_page + 1;


            // 검색 데이터
            itemList.itemList = response.data;


            // 이전버튼
            if (pagination.current_page === 0) {
                $('#previousBtn').addClass("disabled")
            } else {
                $('#previousBtn').removeClass("disabled")
            }


            // 다음버튼
            if (pagination.current_page === pagination.total_pages - 1) {
                $('#nextBtn').addClass("disabled")
            } else {
                $('#nextBtn').removeClass("disabled")
            }

            // 페이징 버튼 처리
            var temp = Math.floor(pagination.current_page / maxBtnSize);
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
                $('li[btn_id=' + (pagination.current_page + 1) + ']').addClass("active");
            }, 50)
        });
    }

})(jQuery);
>>>>>>> e96389c62b0c40b596d565947d1e477cb833bf2e
