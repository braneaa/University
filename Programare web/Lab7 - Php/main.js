$(document).ready(function() {

    $("#filter-gbentry-button").click(function() {                
        var chk1 = $('#typeInput').val();
            
    $.ajax({   
        type:"GET",
        url: "filter.php",       
        data: { check : chk1 },           
        success: function(response){                    
            $("#table1").html(response); 
        }
    
    });
});
});