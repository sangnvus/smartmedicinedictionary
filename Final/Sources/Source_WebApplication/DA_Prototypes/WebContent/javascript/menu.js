$(document).ready(function() {
			$("#ulMenu li:has(ul)").addClass("down");
			$("#ulMenu li").hover(function(){
				$(this).find("ul:first").css({ display: "none"}).slideDown(500);
			}, function() {
				$(this).find("ul:first").fadeOut(200);
			});
		});
