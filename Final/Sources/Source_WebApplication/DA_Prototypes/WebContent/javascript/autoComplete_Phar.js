$(document).ready(function() {
	var arr = [ "" ];
	$.ajax({
		type : 'post',
		url : "autoCompletePharmacyAction.action",
		dataType : "json",
		data : "",
		success : function(data) {
			$(data.locationString).each(function(index, el) {
				arr.push(el);
			});
		},
	});
	/*arr.sort();*/
	//autoComplte();
	$("#home_text").autocomplete({
		width : 300,
		max : 10,
		delay : 100,
		minLength : 1,
		autoFocus : true,
		cacheLength : 1,
		scroll : true,
		highlight : true,
		source : function(req, responseFn) {
			if(req.term.trim() != "") {
				var re = $.ui.autocomplete.escapeRegex(req.term.trim());
				var matcher = new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + re.replace("/([\^\$\(\)\[\]\{\}\*\.\+\?\|\\])/gi", "\\$1") + ")(?![^<>]*>)(?![^&;]+;)", "i");
				var a = $.grep(arr, function(item, index) {
					return matcher.test(item.trim());
				});
				autoComplte();
				responseFn(a.slice(0, 20));
			}
		}
	});
});
function autoComplte() {
	var oldFn = $.ui.autocomplete.prototype._renderItem;
	$.ui.autocomplete.prototype._renderItem = function(ul, item) {
		var re = new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + this.term.trim().replace("/([\^\$\(\)\[\]\{\}\*\.\+\?\|\\])/gi", "\\$1") + ")(?![^<>]*>)(?![^&;]+;)", "i");
		var t = item.label.replace(re,
				"<span style='font-weight:Bold;color:black;'>" + this.term.trim()
						+ "</span>");
		return $("<li></li>").data("item.autocomplete", item).append(
				"<a>" + t + "</a>").appendTo(ul);
	};
}
