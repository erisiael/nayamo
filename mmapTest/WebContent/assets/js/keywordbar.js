/**
 * 
 */

$(document).ready(function() {
    $('.open').on('click', function(event){
        //$(this).addClass('oppenned');
        $(this).toggleClass('oppenned');
        event.stopPropagation();
    })
    /*$('.cls').on('click', function(event){
        $('.open').removeClass('oppenned');
        event.stopPropagation();
    });*/
});