$(function() {
    $(".hamburger").on("click", function() {
        $(this).toggleClass("is-active");
    });
    $(".dropdown").on("hidden.bs.dropdown", function() {
        $(this).children("button.hamburger").removeClass("is-active");
    });
});