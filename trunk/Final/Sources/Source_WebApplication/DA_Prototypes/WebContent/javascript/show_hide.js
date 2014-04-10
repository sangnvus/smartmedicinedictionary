$(document).ready(function() {
	$(".advanced").hide();
	  $("#tab1").click(function(){
		  	document.myForm.action = "pharmacyDetail";
			document.forms["myForm"].submit();
		    $(".advanced").hide();
		    $(".not_advanced").show();
		  });
	  
	  $("#tab2").click(function(){
		  	document.paginationForm.action = "danh-muc-thuoc-action";
			document.forms["paginationForm"].submit();
		    $(".advanced").show();
		    $(".not_advanced").hide();
		  });
});