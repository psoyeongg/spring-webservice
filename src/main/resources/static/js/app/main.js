var main = {
    init : function() {
        var _this = this;
        $("#btn-save").on("click", function() {
            _this.save();
        });
    },
    save : function() {
        var data = {
            title : $("#title").val(),
            author : $("#author").val(),
            content : $("#content").val()
        };

        $.ajax({
            type : "POST",
            url : "/posts",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            data : JSON.stringify(data)
        }).done(function() {
            alert("글이 등록되었습니다");
            location.reload();
        }).fail(function(error) {
            alert(error);
        });
    }
}
<!-- init(), save()를 바로 함수로 안 잡고 객체의 메소드로 잡는 이유? a.js에 또 다른 init()가 존재할 경우 문제발생 -->
main.init();