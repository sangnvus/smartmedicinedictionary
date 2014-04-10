<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Thông tin nhà thuốc</title>
<link rel="stylesheet" type="text/css" href="css/home.css">
<!-- <link rel="stylesheet" type="text/css" href="css/map.css"> -->
<link rel="stylesheet" type="text/css" href="css/popup.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?key=AIzaSyCt_RpfrVUrVs74uD1VwcXgR3xK64S56DM&sensor=false"></script>
<script type="text/javascript" src="javascript/changes-tab.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$(".p_map").show();
						$(".map").css("text-decoration", "underline");
						$(".p_video").hide();
						$(".p_anh").hide();
						$(".video")
								.click(
										function() {
											$("#videoContainer")[0].pause();
											$(".p_video").show();
											$(".p_anh").hide();
											$(".p_map").hide();
											$(".video").css("text-decoration",
													"underline");
											$(".anh").css("text-decoration",
													"none");
											$(".map").css("text-decoration",
													"none");
										});
						$(".anh").click(function() {
							$("#videoContainer")[0].pause();
							$(".p_anh").show();
							$(".p_map").hide();
							$(".p_video").hide();
							$(".anh").css("text-decoration", "underline");
							$(".video").css("text-decoration", "none");
							$(".map").css("text-decoration", "none");
						});
						$(".map").click(function() {
							$("#videoContainer")[0].pause();
							$(".p_map").show();
							$(".p_anh").hide();
							$(".p_video").hide();
							$(".map").css("text-decoration", "underline");
							$(".anh").css("text-decoration", "none");
							$(".video").css("text-decoration", "none");
						});
						$("#direct").click(function() {
							$("#videoContainer")[0].pause();
							$(".p_map").show();
							$(".p_anh").hide();
							$(".p_video").hide();
							$(".map").css("text-decoration", "underline");
							$(".anh").css("text-decoration", "none");
							$(".video").css("text-decoration", "none");
						});
					});
</script>
<script type="text/javascript">
	var myCenter;
	var marker;
	var markerArray = [];
	var map;
	var lat;
	var lon;
	var count = 0;
	var mapProp;
	var directionsService = new google.maps.DirectionsService();
	var directionsDisplay = new google.maps.DirectionsRenderer();
	function initialize() {
		lat = "<s:property value='%{pharmacyPopup.lat}'/>";
		lon = "<s:property value='%{pharmacyPopup.lon}'/>";
		myCenter = new google.maps.LatLng(
				"<s:property value='%{pharmacyPopup.lat}'/>",
				"<s:property value='%{pharmacyPopup.lon}'/>");
		mapProp = {
			center : myCenter,
			zoom : 15,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		clearMarker();
		map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
		directionsDisplay.setPanel(document.getElementById('directions-panel'));
		marker = new google.maps.Marker({
			position : myCenter,
			animation : google.maps.Animation.DROP,
			icon: "image/your_location.png",
			map : map
		});
		markerArray.push(marker);
	}
	function yourLocation() {
		if(count == 2) {
			mapProp = {
				center : myCenter,
				zoom : 15,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			clearMarker();
			map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
			marker = new google.maps.Marker({
				position : myCenter,
				animation : google.maps.Animation.DROP,
				icon: "image/your_location.png",
				map : map
			});
			markerArray.push(marker);
			count = 1;
		}
	}
	function direction() {
		if(count == 0 || count == 1) {
		count = 2;
		var polyline = new google.maps.Polyline({
			path : [],
			strokeColor : '#FF0000',
			strokeWeight : 3
		});
		directionsDisplay.setMap(map);
		clearMarker();
		var currentLat = "<s:property value='currentLat'/>";
		var currentLon = "<s:property value='currentLon'/>";
		var startLocation = new google.maps.LatLng(lat, lon);
		var endLocation = new google.maps.LatLng(currentLat, currentLon);
		var request = {
			origin : startLocation,
			destination : endLocation,
			travelMode : google.maps.DirectionsTravelMode.WALKING
		};
		directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				var bounds = new google.maps.LatLngBounds();
				var startObj = new Object();
				var endObj = new Object();
				var legs = response.routes[0].legs;
				for (i = 0; i < legs.length; i++) {
					if (i == 0) {
						startObj.latlng = legs[i].start_location;
						startObj.address = legs[i].start_address;
						startObj.marker = createMarker(legs[i].start_location, 'image/your_location.png');
					}
					endObj.latlng = legs[i].end_location;
					endObj.address = legs[i].end_address;
					var steps = legs[i].steps;
					for (j = 0; j < steps.length; j++) {
						var nextSegment = steps[j].path;
						for (k = 0; k < nextSegment.length; k++) {
							polyline.getPath().push(nextSegment[k]);
							bounds.extend(nextSegment[k]);
						}
					}
				}
				endObj.marker = createMarker(endObj.latlng, 'image/nearest_location.png');
				polyline.setMap(map);
				map.fitBounds(bounds);
			}
		});
		}
	}
	function createMarker(latlng, img) {
		marker = new google.maps.Marker({
			position : latlng,
			draggable : true,
			map : map,
			icon : img
		});
		markerArray.push(marker);
	}
	function clearMarker() {
		// First, remove any existing markers from the map.
		for (i = 0; i < markerArray.length; i++) {
			markerArray[i].setMap(null);
		}

		// Now, clear the array itself.
		markerArray = [];
	}
</script>
<!-- <tupm> -->

<!-- </tupm> -->

</head>
<body onload="initialize()">
	<div id="map_container">
		<div id="topPopup">
			<div>
				<s:property value="%{pharmacyPopup.pharmacyName}" />
			</div>
		</div>
		<s:hidden name="hasvideo" id="hasvideo" />
		<div id="middlePopup">
			<div id="midLeftPopup">
				<div id="top">
					<li class="header ttcb">Thông tin người đại diện</li>
				</div>
				<ul>
					<li class="detail house"><s:label value="Tên:" id="hder" /> <s:property
							value="%{rep.name}" /></li>
					<li class="detail house"><s:label value="Email:" id="hder" />
						<s:property value="%{rep.email}" /></li>
					<li class="detail house"><s:label value="Điện thoại:"
							id="hder" /> <s:property value="%{rep.phone}" /></li>
					<li class="detail house"><s:label value="Trình độ:" id="hder" />
						<s:property value="%{rep.degreeName}" /></li>
					<li class="detail house"><s:label value="Giấy phép hành nghề:"
							id="hder" /> <s:property value="%{rep.licensureNo}" /></li>
				</ul>
				<div></div>
				<br /> <br />
				<div id="top">
					<li class="header ttcb">Thông tin nhà thuốc</li>
				</div>
				<ul>
					<li class="detail house"><s:label value="Tên:" id="hder" /> <s:property
							value="%{pharmacyPopup.pharmacyName}" /></li>
					<li class="detail house"><s:label value="Loại hình:" id="hder" />
						<s:property value="%{pharmacyPopup.typeOfBusinessName}" /></li>
					<li class="detail house"><s:label value="Địa chỉ:" id="hder" />
						<s:property value="%{pharmacyPopup.address}" /></li>
					<li class="detail house"><s:label value="Số điện thoại:"
							id="hder" /><s:property value="%{pharmacyPopup.telAreaCode}"/> <s:property value="%{pharmacyPopup.homePhone}" /></li>
					<li class="detail house"><s:label
							value="Giấy phép kinh doanh:" id="hder" /> <s:property
							value="%{pharmacyPopup.businessLicenseNo}" /></li>
					<li class="detail house"><s:label value="Trực thuộc công ty:"
							id="hder" /> <s:property value="%{pharmacyPopup.pharCompany}" /></li>
					<li class="detail house"><s:label value="Số GPP:" id="hder" />
						<s:property value="%{pharmacyPopup.gppNo}" /></li>
					<li class="detail house"><s:label
							value="Bằng khen, chứng nhận:" id="hder" /> <s:property
							value="%{pharmacyPopup.notes}" /></li>
				</ul>
				<div></div>
				<br /> <br />
			</div>
			<div id="midRightPopup">
				<div id="top">
					<li class="header map bs1">Bản đồ</li>
					<li class="header anh bs1">Ảnh</li>
					<li class="header video bs1">Video</li>
				</div>
				<div class="p_anh">
					<img
						src="Pharmacy_Images/<s:property value='%{pharmacyPopup.imgPath}' />"
						style="width: 100%; height: 400px;" />
				</div>
				<div id="nodeElement1" class="p_video">
					<s:if test="hasvideo == true">
						<object id="videoPath">
							<video id="videoContainer" width="100%" height="400" controls> <source
								src="Pharmacy_Medias/<s:property value="%{pharmacyPopup.videoPath}" />"
								type="video/mp4" /> </video>
						</object>
					</s:if>
					<s:else>
						<img
							src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacyPopup.videoPath}" />"
							style="width: 100%; height: 400px;" />
					</s:else>
				</div>
				<div class="p_map">
					<div id="googleMap" style="width: 100%; height: 400px;" />
					<div id="directions-panel"></div>
				</div>
				
				<div id="tu">
					<s:submit value="Vẽ đường đi" name="Direction" onclick="direction();"
						id="direct" />
					<s:submit value="Nhà thuốc" name="Direction" onclick="yourLocation();"
						id="direct" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>