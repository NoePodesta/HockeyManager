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
            $(".status").html("<font color=red>Debe ingresar por lo menos <b>3</b> dígitos</font>");
        }
    });
};

arg.checkTournamentName = function(){

    $("#tournamentName").change(function(){
        var tournamentName = $(this).val();
        if(tournamentName.length >= 3){
            $(".status").html("<img src=\"image/loading.gif\"  width=\"10\" height=\"10\"> Chequeando base de datos...");
            $.ajax({
                type: "POST",
                url: "TournamentNameCheck",
                data: "tournamentName="+ tournamentName,
                success: function(msg){
                    $(".status").ajaxComplete(function(event, request, settings){
                        $(".status").html(msg);
                    });
                }
            });
        }
        else{
            $(".status").html("<font color=red>Debe ingresar por lo menos <b>3</b> dígitos</font>");
        }
    });
};

arg.checkTeamName = function(){

    $("#teamName").change(function(){
        var teamName = $(this).val();
        if(teamName.length >= 3){
            $(".status").html("<img src=\"image/loading.gif\"  width=\"10\" height=\"10\"> Chequeando base de datos...");
            $.ajax({
                type: "POST",
                url: "TeamNameCheck",
                data: "teamName="+ teamName,
                success: function(msg){
                    $(".status").ajaxComplete(function(event, request, settings){
                        $(".status").html(msg);
                    });
                }
            });
        }
        else{
            $(".status").html("<font color=red>Debe ingresar por lo menos <b>3</b> dígitos</font>");
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
	$("#description").charCounter(2000);
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
		var value = $("#value").val();
		var dataString = 'value='+value +  '&comment='+ comment + '&user=' + user; 
		if(comment==' '){
			alert('Por favor, comente algo');
		}else{
			$("#flash").show();
			$("#flash").fadeIn(300).html('<img src="image/loading.gif" />Loading Comment...');
			$.ajax({
				type: "POST",
				url: "AddComment",
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

arg.pagination = function(name){
	
/* The following code is executed once the DOM is loaded */
	
	// Calling the jQuery plugin and splitting the
	// #holder UL into pages of 3 LIs each:
	
	$('#holder').sweetPages({perPage:5});
	
	// The default behaviour of the plugin is to insert the 
	// page links in the ul, but we need them in the main container:

	var controls = $('.swControls').detach();
	controls.appendTo('#'+name);
	
};

function validatePass(p1, p2) {
    if (p1.value != p2.value || p1.value == '' || p2.value == '') {
        p2.setCustomValidity('Contrase�a incorrecta');
    } else {
        p2.setCustomValidity('');
    }
}
