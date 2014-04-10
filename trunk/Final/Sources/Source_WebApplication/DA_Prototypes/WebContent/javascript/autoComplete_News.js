$(document).ready(function() {
		var arr = [ "" ];
		$.ajax({
			type : 'post',
			url : "autoCompleteNewsAction.action",
			dataType : "json",
			data : "",
			success : function(data) {
				$(data.titleOfNews).each(function(index, el) {
					arr.push(el);
				});
			},
		});
		arr.sort();
		autoComplte();
		$("#home_text").autocomplete({
			width : 300,
			max : 10,
			delay : 100,
			minLength : 1,
			autoFocus : false,
			cacheLength : 1,
			scroll : true,
			highlight : false,
			source : arr
				/*function(req, responseFn) {
				var re = $.ui.autocomplete.escapeRegex(req.term);
				var matcher = new RegExp("^" + re, "i");
				var a = $.grep(arr, function(item, index) {
					alert(item + "," + index);
					return matcher.test(item);
				});
				responseFn(a.slice(0, 20));
			}*/
		});
	});
	function autoComplte() {
		var oldFn = $.ui.autocomplete.prototype._renderItem;
		$.ui.autocomplete.prototype._renderItem = function(ul, item) {
			var re = new RegExp("^" + this.term, "i");
			var t = item.label.replace(re,
					"<span style='font-weight:Bold;color:Blue;'>" + this.term
							+ "</span>");
			return $("<li></li>").data("item.autocomplete", item).append(
					"<a>" + t + "</a>").appendTo(ul);
		};
	}