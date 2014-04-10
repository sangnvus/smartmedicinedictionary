$(document).ready(function() {
	 $(".advanced").hide();
	  $("#tab1").click(function(){
		    $(".advanced").hide();
		    $(".not_advanced").show();
		  });
	  
	  $("#tab2").click(function(){
		    $(".advanced").show();
		    $(".not_advanced").hide();
		  });
});