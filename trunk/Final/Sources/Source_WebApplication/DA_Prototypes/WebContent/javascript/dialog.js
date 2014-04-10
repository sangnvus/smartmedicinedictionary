$(document).ready(function() {
	$("#map_container").dialog({
		autoOpen : false,
		width : 555,
		height : 400,
		title : 'Google Map',
		show : 'blind',
		hide : 'explode',
		resizeStop : function(event, ui) {
			google.maps.event.trigger(map, 'resize');
		},
		open : function(event, ui) {
			google.maps.event.trigger(map, 'resize');
		}
	});
	$("#map_container").dialog({
		position : [ 'center', '0' ]
	});
	initialize1();
});
