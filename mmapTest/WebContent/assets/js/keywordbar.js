/**
 * 
 */

$(document).ready(function() {
    $('.open').on('click', function(event){
        $(this).addClass('oppenned');
        event.stopPropagation();
    })
    /*$('body').on('click', function(event) {
        $('.open').removeClass('oppenned');
    })*/
    $('.cls').on('click', function(event){
        $('.open').removeClass('oppenned');
        event.stopPropagation();
    });
});