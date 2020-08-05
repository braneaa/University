var imageCanMove = true;

$(document).ready(function(){
    var canSlide = true;
    
    function putElements() {
        let str = "#im";
        let pictureWidth = $(str+1).width();
        let elementPosition = 0;
        let index = 1;
        $(".imgContainer").each(function () {
            elementPosition += pictureWidth;
            index += 1;
            pictureWidth = $(str + index.toString()).width();
            $(this).css("left", elementPosition);
        });
    }

    putElements();
    
    function firstClick() {
        $("li").clearQueue();
        $("li").stop();
        $(".popUpImage").empty();
        canSlide = false;
        let img = document.createElement("img");
        img.setAttribute("id","popIm");
        img.setAttribute("src", $(this).attr("src"));
        document.getElementById("pop1").appendChild(img);
        $("#popIm").click(function () {
            $(".popUpImage").empty();
            slide();
        });
    }
        
    $(".imageClass").click(firstClick);
    
    function slide() {
            $("li")
                .animate({"left": "+=10px"}, 100, again);
    }

    function again() {
        console.log($(this));
        var left = $(this).parent().offset().left + $(this).offset().left;
        let pictureWidth = $("#im1").width();
        let numberPictures = $(".imgContainer").length;
        if (left >= pictureWidth * numberPictures) {
            $(this).css("left", left - pictureWidth * numberPictures);
        }
        
        slide();
    }

    slide();
});