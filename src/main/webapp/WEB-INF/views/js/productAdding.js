$(document).ready(function () {

    var buttonAdd = $("section.creditorAdd button")

    buttonAdd.on("click", function () {
        var newInput = $("<input type='text' name='creditors'/><br>")

        $("section.creditorAdd").prepend(newInput)
    })

})