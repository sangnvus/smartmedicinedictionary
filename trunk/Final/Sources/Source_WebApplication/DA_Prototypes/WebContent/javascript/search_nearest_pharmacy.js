function updatestatus() {
	// Show number of loaded items
	var totalItems = $('#side_bar p').length;
	var countPharmacyList = $("#countPharmacyList").val();
	if (countPharmacyList == 0) {
		$('#status').text('Hiển thị ' + totalItems + '/' + 0 + ' kết quả');
	} else {
		$('#status')
				.text(
						'Hiển thị ' + totalItems + '/' + countPharmacyList
								+ ' kết quả');
	}
}
function scrollalert() {
	var scrolltop = $('#scrollbox').attr('scrollTop');
	var scrollheight = $('#scrollbox').attr('scrollHeight');
	var windowheight = $('#scrollbox').attr('clientHeight');
	var scrolloffset = 20;
	var namelist = document.getElementById('pharamcyNameList').value;
	nameListArray = namelist.split('~');
	if (scrolltop >= (scrollheight - (windowheight + scrolloffset))) {
		if (count <= markerArray.length - 2) {

			// fetch new items
			$('#status').text('Loading more items...');
			for ( var i = count; i < count + 2; i++) {
				if (i < markerArray.length - 1) {
					if (i != markerArray.length - 2) {
						$('#side_bar')
								.append(
										'<div id="pharmacyBox"><a id="banDo" href="javascript:myclick('
												+ i
												+ ')">'
												+ '<b>Bản Đồ</b>'
												+ '<\/a><br><p  id="box_content" onclick="javascript:openPopup('
												+ idListArray[i + 1]
												+ ')"><a>'
												+ markerArray[i].myname
												+ '<\/a><\/p><ul class="pharmacyImg"><li><img src=Pharmacy_Images/'
												+ ""
												+ imageListArray[i + 1]
												+ ""
												+ ' \/>'
												+ '<span><img src=Pharmacy_Images/'
												+ "" + imageListArray[i + 1]
												+ "" + ' \/>'
												+ markerArray[i].myname
												+ '<\/span>'
												+ '<\/li><\/ul><\/div>');
					}
				}
				updatestatus();
			}
			count = count + 2;
		}
	}
	setTimeout('scrollalert();', 1500);
}

function openPopup(value) {
	var url = "popupAction?pharmacyId=" + value + "&currentLat="
			+ document.getElementById("currentLat").value + "&currentLon="
			+ document.getElementById("currentLon").value;
	var w = 1000;
	var h = 500;
	var left = Number((screen.width / 2) - (w / 2));
	var tops = Number((screen.height / 2) - (h / 2));
	window
			.open(
					url,
					'jav',
					'scrollbars=no,location=no,resizable=yes,width=1000, height=500,top=0,left=0,top='
							+ tops + ',left=' + left);
}

function codeAddress() {
	var address = document.getElementById("home_text").value;
	if (address) {
		address = address + ", việt nam";
	}
	document.getElementById("addressId").value = address
	geocoder
			.geocode(
					{
						'address' : address
					},
					function(results, status) {
						if (status == google.maps.GeocoderStatus.OK) {
							document.getElementById('latId').value = results[0].geometry.location
									.lat().toFixed(15);
							document.getElementById('lonId').value = results[0].geometry.location
									.lng().toFixed(15);
						}
					});
}
function createMarker(latlng, content, label, i) {
	var contentString = content;
	marker = new google.maps.Marker({
		position : latlng,
		draggable : true,
		map : map,
		title : label
	});
	marker.myname = label;
	markerArray.push(marker);
	google.maps.event.addListener(marker, 'click', (function(marker, i) {
		return function() {
			infowindow.setContent(contentString);
			infowindow.open(map, marker);
		}
	})(marker, i));
	return marker;
}
function clearMarker() {
	// First, remove any existing markers from the map.
	for (i = 0; i < markerArray.length; i++) {
		markerArray[i].setMap(null);
	}

	// Now, clear the array itself.
	markerArray = [];
}
function myclick(i) {
	google.maps.event.trigger(markerArray[i], "click");
}
// == rebuilds the sidebar to match the markers currently displayed ==
function makeSidebar() {
	var j = markerArray.length - 2;
	$('#side_bar')
			.append(
					'<div id="pharmacyBox"><a id="banDo" href="javascript:myclick('
							+ j
							+ ')">'
							+ '<b>Bản Đồ</b>'
							+ '<\/a><br><p  id="box_content" onclick="javascript:openPopup('
							+ idListArray[0] + ')"><a>'
							+ markerArray[j].myname + '<\/a><\/p><ul class="pharmacyImg"><li><img src=Pharmacy_Images/'+ "" +imageListArray[0]+ "" + ' \/>'
							+ '<span><img src=Pharmacy_Images/'+ "" +imageListArray[0]+ "" + ' \/>' + markerArray[j].myname + '<\/span>'
							+ '<\/li><\/ul><\/div>');
	if(count < markerArray.length - 2) {
		for ( var i = 0; i < 2; i++) {
			$('#side_bar')
					.append(
							'<div id="pharmacyBox"><a id="banDo" href="javascript:myclick('
									+ i
									+ ')">'
									+ '<b>Bản Đồ</b>'
									+ '<\/a><br><p  id="box_content" onclick="javascript:openPopup('
									+ idListArray[i + 1] + ')"><a>'
									+ markerArray[i].myname + '<\/a><\/p><ul class="pharmacyImg"><li><img src=Pharmacy_Images/'+ "" +imageListArray[i + 1]+ "" + ' \/>'
									+ '<span><img src=Pharmacy_Images/'+ "" +imageListArray[i + 1]+ "" + ' \/>' + markerArray[i].myname + '<\/span>'
									+ '<\/li><\/ul><\/div>');
			count++;
		}
	}
	updatestatus();
}