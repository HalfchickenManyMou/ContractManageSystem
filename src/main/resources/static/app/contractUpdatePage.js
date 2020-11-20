(function ($) {

    $('#IMG_FILE').change(function(e){
        var reader = new FileReader();
        reader.readAsDataURL(e.target.files[0]);

        reader.onload = function  () {
            var tempImage = new Image();
            tempImage.src = reader.result;
            tempImage.onload = function () {
                var canvas = document.createElement('canvas');
                var canvasContext = canvas.getContext("2d");

                canvas.width = 100;
                canvas.height = 100;

                canvasContext.drawImage(this, 0, 0, 100, 100);

                var dataURI = canvas.toDataURL("image/jpeg");

                var imgTag = "<img id='PREVIEW_IMG' style='width: 35%;' src='"+dataURI+"'/>";
                $("#PREVIEW_IMG_DIV").append(imgTag);
            };
        };
    });


})




