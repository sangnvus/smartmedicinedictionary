function loadAjax() {
	var typeOfBusinessStr = $("#typeOfBusinessStr").val();
	var cityStr = $("#cityStr").val();
	var districtStr = $("#districtStr").val();
	$.ajax({
		type : 'post',
		url : "selectBoxAjax.action",
		dataType : "json",
		data : "",
		success : function(data) {
			$(data.selectString).each(
					function(index, el) {
						var split = el.split("~");
						if (split[1] == cityStr) {
							$("#selectBoxAjax1").append(
									'<option selected="selected" value=' + split[1] + '>'
											+ split[0]
											+ '<\/option>');
						} else {
							$("#selectBoxAjax1").append(
									'<option value=' + split[1] + '>'
											+ split[0]
											+ '<\/option>');
						}
					});
			$(data.selectTypeStr).each(
					function(index, el) {
						var split = el.split("~");
						if (split[1] == typeOfBusinessStr) {
							$("#typeOfBusiness").append(
									'<option selected="selected" value=' + split[1] + '>'
											+ split[0]
											+ '<\/option>');
						} else {
							$("#typeOfBusiness").append(
									'<option value=' + split[1] + '>'
											+ split[0]
											+ '<\/option>');
						}
					});
		},
		
	});
	$.ajax({
		type : 'post',
		url : "selectBoxAjax.action?selectBoxAjax1=" + cityStr,
		dataType : "json",
		data : "",
		success : function(data) {
			$(data.selectDistrictStr).each(
					function(index, el) {
						var split = el.split("~");
						if (split[1] == districtStr) {
							$("#selectBoxAjax2").append(
									'<option selected="selected" value=' + split[1] + '>'
											+ split[0]
											+ '<\/option>');
						} else {
							$("#selectBoxAjax2").append(
									'<option value=' + split[1] + '>'
											+ split[0]
											+ '<\/option>');
						}
					});
		},
	});
}