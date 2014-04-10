$(document).ready(function() {
	$("#indications").keyup(function(e) {
		if ((e.keyCode || e.which) == 13) {
			$("#indications").append("\n");
		}
	});
	$("#contraindications").keyup(function(e) {
		if ((e.keyCode || e.which) == 13) {
			$("#contraindications").append("\n");
		}
	});
	$("#dosingAndUse").keyup(function(e) {
		if ((e.keyCode || e.which) == 13) {
			$("#dosingAndUse").append("\n");
		}
	});
	$("#ingredients").keyup(function(e) {
		if ((e.keyCode || e.which) == 13) {
			$("#ingredients").append("\n");
		}
	});
	$("#storage").keyup(function(e) {
		if ((e.keyCode || e.which) == 13) {
			$("#storage").append("\n");
		}
	});
	$("#warning").keyup(function(e) {
		if ((e.keyCode || e.which) == 13) {
			$("#warning").append("\n");
		}
	});
	$("#similarMedicine").keyup(function(e) {
		if ((e.keyCode || e.which) == 13) {
			$("#similarMedicine").append("\n");
		}
	});
	$("#interaction").keyup(function(e) {
		if ((e.keyCode || e.which) == 13) {
			$("#interaction").append("\n");
		}
	});
});
