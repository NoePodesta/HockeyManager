var arg = new Object();

arg.signin = function() {
	$(".signin").click(function(e) {
		e.preventDefault();
		$("fieldset#signin_menu").toggle();
		$(".signin").toggleClass("menu-open");
	});

	$("fieldset#signin_menu").mouseup(function() {
		return false;
	});
	$(document).mouseup(function(e) {
		if ($(e.target).parent("a.signin").length == 0) {
			$(".signin").removeClass("menu-open");
			$("fieldset#signin_menu").hide();
		}
	});

};

arg.signup = function() {
	$(".signup").click(function(e) {
		e.preventDefault();
		$("fieldset#signup_menu").toggle();
		$(".signup").toggleClass("menu-open");
	});

	$("fieldset#signup_menu").mouseup(function() {
		return false;
	});
	$(document).mouseup(function(e) {
		if ($(e.target).parent("a.signup").length == 0) {
			$(".signup").removeClass("menu-open");
			$("fieldset#signup_menu").hide();
		}
	});

};


arg.checkUserName = function(){

    $("#userId").change(function(){
        var userId = $(this).val();
        if(userId.length >= 3){
            $(".status").html("<img src=\"image/loading.gif\"  width=\"10\" height=\"10\"> Chequeando base de datos...");
             $.ajax({
                type: "POST",
                url: "UsernameCheck",
                data: "userId="+ userId,
                success: function(msg){
                    $(".status").ajaxComplete(function(event, request, settings){                
                    $(".status").html(msg);
                    });
                }
            }); 
        }
        else{       
            $(".status").html("<font color=red>Debe ingresar por lo menos <b>3</b> d�gitos</font>");
        }
    });
};

arg.autocomplete = function(){
	
	$("#search").autocomplete({ source : getFilteredNames, select: setHiddenField});	
	function getFilteredNames(request, response){
       $.ajax({
              type: "POST",
              url: "Autocomplete",
              dataType:"json",
              data: {
                     term: request.term
              },
              success: function(data) {                
                     response(data);
              }
       });
	};
 
	function setHiddenField(event, ui){
        $("#hiddenField").val(ui.item.value[0]);
       }
}; 

arg.thumbnailImage = function(){

    document.getElementById('file').addEventListener('change', handleFileSelect, false);

    if (window.FileReader) {

        function handleFileSelect(evt) {
            var files = evt.target.files;
            var f = files[0];
            var reader = new FileReader();

            reader.onload = (function(theFile) {
                return function(e) {
                    document.getElementById('list').innerHTML = ['<img src="', e.target.result,'" title="', theFile.name, '" width="150" />'].join('');
                };
            })(f);

            reader.readAsDataURL(f);
        }
    } else {
        alert('This browser does not support FileReader');
    }


}

arg.fileStyle = function(){

    $("input[type=file]").filestyle({
        image: 'image/chooser.gif',
        imageheight : 22,
        imagewidth : 82,
        width : 250
    });
}


arg.countCharacters = function() {
	$("#description").charCounter(300);
};

arg.scrollPane = function() {
	$('.scroll-pane').jScrollPane();
};

$(function() {
	 
    // if the function argument is given to overlay,
    // it is assumed to be the onBeforeLoad event listener
    $("a[rel]").overlay({
    	
        mask: 'grey',
        effect: 'apple',
 
        onBeforeLoad: function() {
 
            // grab wrapper element inside content
            var wrap = this.getOverlay().find(".contentWrap");
 
            // load the page specified in the trigger
            wrap.load(this.getTrigger().attr("href"));
        }
 
    });
});

arg.modifyProfile = function(){ 
	$("a.modifyProfile[rel]").overlay({
    top: 'center',
    api: true,
    mask: {
        color: "#000",
        loadSpeed: 0,
        closeSpeed: 0, //this is REQUIRED for the next overlay to work
        opacity: 0.9
    }
});
};





arg.date = function() {
	$.tools.dateinput
			.localize(
					"es",
					{
						months : 'Enero,Febrero,Marzo,Abril,Mayo,Junio,Julio,Agosto,Septiembre,Octubre,Noviembre,Diciembre',
						shortMonths : 'Ene,Feb,Mar,Abr,May,Jun,Jul,Ago,Sep,Oct,Nov,Dic',
						days : 'Domingo,Lunes,Martes,Miercoles,Jueves,Viernes,Sabado',
						shortDays : 'Dom,Lun,Mar,Mie,Jue,Vie,Sab'
					});
	$(":date").dateinput({
		lang : 'es',
		format : 'dd-mm-yyyy', // the format displayed for the user
		speed : 'fast', // calendar reveal speed
		firstDay : 1
	// which day starts a week. 0 = sunday, 1 = monday etc..
	});
};


arg.comment = function() {
	$(".submitComment").click(function() 
	{
		var comment = $("#comment").val();
		var user = $("#user").val(); 
		var dataString = 'comment='+ comment + '&user=' + user; 
		if(comment==' '){
			alert('Por favor, comente algo');
		}else{
			$("#flash").show();
			$("#flash").fadeIn(300).html('<img src="image/loading.gif" />Loading Comment...');
			$.ajax({
				type: "POST",
				url: "Comment?action=ADDCOMMENT",
				data: dataString,
				cache: false,
				success: function(html){
					$("#flash").hide();
					$('#addCommentContainer').fadeIn(300).html('<div class="comment"><div class="date">' + date + '</div><p>' + comment + '</p></div>');
					}
			});
		}return false;
	}); 
};


