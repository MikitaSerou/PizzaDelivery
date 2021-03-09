$(document).ready(function () {
    var basePrice = $("#base option:selected").data('price');
    var sauce = $("#sauce option:selected").data('price');
    var ingredientsSum = 0;
    var total = Number(sauce) + Number(ingredientsSum) + Number(basePrice);
    $("#result").text(total);

    $('select#base').change(function () {
        basePrice = $("#base option:selected").data('price');
        var total = Number(sauce) + Number(ingredientsSum) + Number(basePrice);
        $("#result").text(total);
    });

    $('select#sauce').change(function () {
        sauce = $("#sauce option:selected").data('price');
        var total = Number(sauce) + Number(ingredientsSum) + Number(basePrice);
        $("#result").text(total);
    });

    $("#boxes input[type='checkbox']").click(function () {
        ingredientsSum = 0;
        $("#boxes input[type='checkbox']:checked").each(function () {
            ingredientsSum += parseFloat($(this).data("price"));
        });
        var total = Number(sauce) + Number(ingredientsSum) + Number(basePrice);
        $("#result").text(total);
    });
});
