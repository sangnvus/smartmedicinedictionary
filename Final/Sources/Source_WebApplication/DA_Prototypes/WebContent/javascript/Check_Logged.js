	$(document).ready(function() {
		var userGroup1 = "<s:property value='#session['userGroup1'].email'/>";
		var userGroup2 = "<s:property value='#session['userGroup2'].email'/>";
		if(!userGroup1 && !userGroup2) {
			document.getElementById("language").removeAttribute("style");
			document.getElementById("language").style.display = "none";
		} else {
			document.getElementById("language").removeAttribute("style");
			document.getElementById("language").style.visibility = "visible";
			if (userGroup1) {
				$('#userLogin').text(userGroup1);
			} else {
				$('#userLogin').text(userGroup2);
			}
		}
	});