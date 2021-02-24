 $(document).ready(function () {
    var basePrice = $("#base option:selected").val();
    var sauce = $("#sauce option:selected").val();
    var ingredientsSum = 0;
     var total = Number(sauce) + Number(ingredientsSum)+Number(basePrice);
     $("#result").text(total);
    $('#base').change(function () {
    basePrice = $(this).val();
        var total = Number(sauce) + Number(ingredientsSum)+Number(basePrice);
        $("#result").text(total);
});
    $('#sauce').change(function () {
    sauce = $(this).val();
        var total = Number(sauce) + Number(ingredientsSum)+Number(basePrice);
    $("#result").text(total);
});
    $("#boxes input[type='checkbox']").click(function () {
    ingredientsSum = 0;
    //sauce = $("#sauce option:selected").val();
    $("#boxes input[type='checkbox']:checked").each(function () {
    ingredientsSum += parseFloat($(this).data("exval"));
});
        var total = Number(sauce) + Number(ingredientsSum)+Number(basePrice);
        $("#result").text(total);
});
});
