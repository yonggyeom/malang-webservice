var ws;

	function wsOpen(){
	    alert("웹소켓 오픈하기 이전");
		ws = new WebSocket("ws://localhost:8082/chating");
		alert("웹소켓 오픈하기 이후" + ws);
		console.log('ws:', ws);
		wsEvt();
	}

	function wsEvt() {
		ws.onopen = function(data){
			//소켓이 열리면 초기화 세팅하기
		}

		ws.onmessage = function(data) {
			var msg = data.data;
			if(msg != null && msg.trim() != ''){
				$("#chatting").append("<p>" + msg + "</p>");
			}
		}

		document.addEventListener("keypress", function(e){
			if(e.keyCode == 13){ //enter press
				send();
			}
		});
	}

	function chatName(){
		var userName = $("#userName").val();
		if(userName == null || userName.trim() == ""){
			alert("사용자 이름을 입력해주세요.");
			$("#userName").focus();
		}else{
			wsOpen();
			$("#yourName").hide();
			$("#yourMsg").show();
		}
	}

	function send() {
		var uN = $("#userName").val();
		var msg = $("#chatting").val();
		ws.send(uN+" : "+msg);
		$('#chatting').val("");
	}

//var main = {
//    init : function (){
//        var _this = this;
//        $('#btn-save').on('click', function(){
//            _this.save();
//        });
//        $('#btn-update').on('click', function () {
//            _this.update();
//        });
//        $('#btn-delete').on('click', function () {
//            _this.delete();
//        });
//    },
//    save : function () {
//        var data = {
//            title: $('#title').val(),
//            author: $('#author').val(),
//            content: $('#content').val()
//        };
//
//        $.ajax({
//            type: 'POST',
//            url: '/api/v1/posts',
//            dataType: 'json',
//            contentType:'application/json; charset=utf-8',
//            data: JSON.stringify(data)
//        }).done(function() {
//            alert('글이 등록되었습니다.');
//            window.location.href = '/';
//        }).fail(function(error) {
//            alert(JSON.stringify(error));
//        });
//    },
//    update : function () {
//        var data = {
//            title: $('#title').val(),
//            content: $('#content').val()
//        };
//
//        var id = $('#id').val();
//
//        $.ajax({
//            type: 'PUT',
//            url: '/api/v1/posts/update/'+id,
//            dataType: 'json',
//            contentType:'application/json; charset=utf-8',
//            data: JSON.stringify(data)
//        }).done(function() {
//            alert('글이 수정되었습니다.');
//            window.location.href = '/';
//        }).fail(function (error) {
//            alert(JSON.stringify(error));
//        });
//    },
//     delete : function () {
//         var id = $('#id').val();
//
//         $.ajax({
//             type: 'DELETE',
//             url: '/api/v1/posts/delete/'+id,
//             dataType: 'json',
//             contentType:'application/json; charset=utf-8'
//         }).done(function() {
//             alert('글이 삭제되었습니다.');
//             window.location.href = '/';
//         }).fail(function (error) {
//             alert(JSON.stringify(error));
//         });
//     }
//};
//
//main.init();