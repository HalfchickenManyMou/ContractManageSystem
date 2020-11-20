(function ($) {

     var sel_file;
     $(document).ready(function() {
         $("input_img").on("change",handleImgFileSelect);
    });
    function handleImgFileSelect(e){
        var files = e.target.files;
        var filesArr = Array.prototype.slice.call(files);
        filesArr.forEach(function(f){
            if(!f.type.match("image.*")){
                alert("확장자는 이미지 확장자만 가능.");
                return;
            }
            sel_file = f;
            var reader = new FileReader();
            reader.onload =function (e){
                $("#img").attr("src",e.target.result);
            }
            reader.readAsDataURL(f);
        })
    }
})




