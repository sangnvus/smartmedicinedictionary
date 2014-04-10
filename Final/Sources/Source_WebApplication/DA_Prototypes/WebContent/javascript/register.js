$(document).ready(function() {
	var submitStepOne = $('#submitStepOne').val();
	 // $(".r_button").click(function(){
		  	//var check = $(this).attr("type");
		  	//if(check!="submit"){
	if (submitStepOne == false) {
		$(".step1").slideToggle("slow");
	} else {
		$(".step0").slideToggle("slow");
	}
		    //$(".step0").slideToggle("slow");
		    //$(".step1").slideToggle("slow");
		  	//}
		 // });
});
