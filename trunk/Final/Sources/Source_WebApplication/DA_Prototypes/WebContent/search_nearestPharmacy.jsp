<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
	<title>Tìm kiếm nhà thuốc gần nhất</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/login.js"></script>
<script type="text/javascript" src="javascript/check_browser_close.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/ui-darkness/jquery-ui.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<style type="text/css">
#box_content {
	cursor: pointer;
	cursor: hand;
}
</style>
<script type="text/javascript">
	var userGroup1;
	var userGroup2;
	$(document)
			.ready(
					function() {
						userGroup1 = "<s:property value="#session['userGroup1'].email"/>";
						userGroup2 = "<s:property value="#session['userGroup2'].email"/>";
						if (!userGroup1 && !userGroup2) {
							document.getElementById("language")
									.removeAttribute("style");
							document.getElementById("language").style.display = "none";
							$(".inner")
									.after(
											'<li ><a href="register">Đăng ký</a></li>'
													+ '<li><a href="aboutUs">Giới thiệu</a></li>'
													+ '<li><a href="news">Tin tức </a></li>'
													+ '<li><a href="Login">Đăng nhập</a></li>');
						} else {
							document.getElementById("language")
									.removeAttribute("style");
							document.getElementById("language").style.visibility = "visible";
							if (userGroup1) {
								$('.inner')
								.after(	'<li><a href="news">Tin tức </a></li>'
										+ '<li><a href="admin_medicineManagement" class="admin">Quản lý thuốc</a>'
												+ '<ul>'
												+ '<li><a href="admin_registerNewMedicine"> Thêm mới'
												+ ' thuốc</a></li>'
												+ '<li><a href="admin_medicineManagement"> Tìm kiếm'
												+ ' thuốc</a></li>'
												+ '<li><a href="admin_acceptMedicine"> Kiểm duyệt'
												+ ' thuốc</a></li>'
												+ '</ul></li>'
												+ '<li><a href="admin_pharmacyManagement" class="admin"> Quản lý nhà thuốc</a></li>'
												+ '<li><a href="admin_news"> Quản lý tin '
												+ 'tức</a>'
												+ '<ul>'
												+ '<li><a href="admin_registerNews"> Thêm mới'
												+ ' tin tức</a></li>'
												+ '<li><a href="admin_news"> Tìm kiếm'
												+ ' tin tức</a></li>'
												+ '</ul></li>');

							} else {
								$(".inner")
										.after(
												'<li><a href="register">Đăng ký</a></li>'
														+ '<li><a href="aboutUs">Giới thiệu</a></li>'
														+ '<li><a href="news">Tin tức </a></li>'
														+ '<li><a href="RepresentativeInfor" class="admin">Nhà thuốc của bạn</a></li>');

							}
						}
					});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		updatestatus();
		scrollalert();
	});
</script>
<script type="text/javascript">
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
</script>
<script
	src="http://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyCt_RpfrVUrVs74uD1VwcXgR3xK64S56DM&sensor=false&libraries=places"></script>
<script type="text/javascript">
	var myCenter = new google.maps.LatLng(21.033333, 105.850000);
	var geocoder = new google.maps.Geocoder();
	var marker;
	var map;
	var count = 0;
	var searched;
	var noResult;
	var markerArray = [];
	var infowindow = new google.maps.InfoWindow();
	var duplicate;
	var directionsService = new google.maps.DirectionsService();
	var directionsDisplay = new google.maps.DirectionsRenderer();
	var polyline = new google.maps.Polyline({
		path : [],
		strokeColor : '#FF0000',
		strokeWeight : 3
	});
	var lonListArray;
	var latListArray;
	var addressListArray;
	var nameListArray;
	var idListArray;
	var imageListArray;
	var cityCircle;
	var citymap = {};
	var cityCircle;
	function initialize() {
		var mapProp = {
			center : myCenter,
			zoom : 8,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
		var input = document.getElementById("home_text");
		var autocomplete = new google.maps.places.Autocomplete(input);
		autocomplete.bindTo('bounds', map);
		duplicate = document.getElementById('duplicate').value;
		searched = document.getElementById("searched").value;
		noResult = document.getElementById("noResult").value;
		map.streetViewControl = false;
		if (searched && noResult == 'false') {
			/* marker.setMap(null); */
			clearMarker();
			var hasNextLocation = document.getElementById("hasNextLocation").value;
			var namelist = document.getElementById('pharamcyNameList').value;
			nameListArray = namelist.split('~');
			var addresslist = document.getElementById('addressList').value;
			addressListArray = addresslist.split('~');
			var latlist = document.getElementById('latList').value;
			latListArray = latlist.split('~');
			var lonlist = document.getElementById('lonList').value;
			lonListArray = lonlist.split('~');
			var locationidList = document.getElementById('pharmacyIdList').value;
			idListArray = locationidList.split("~");
			var imdList = document.getElementById('imgPathList').value;
			imageListArray = imdList.split("~");
			if(hasNextLocation == 'true') {
				for ( var i = 0; i < nameListArray.length; i++) {
					if (parseInt(latListArray[i]) == 0) {
						geocoder.geocode({
							'address' : address
						}, function(results, status) {
							if (status == google.maps.GeocoderStatus.OK) {
								marker = new google.maps.Marker({
									map : map,
									position : results[0].geometry.location,
									title : name,
									zoom : 8
								});
								var title = "<b>"
									+ nameListArray[i]
								+ "</b><br/>"
								+ addressListArray[i]
								+ "<br/>";
								google.maps.event.addListener(marker, 'click',
										function() {
											infowindow
													.setContent("<b>"
															+ nameListArray[i]
															+ "</b><br/>"
															+ addressListArray[i]
															+ "<br/>");
											infowindow.open(map, marker);
										});
							}
						});
					} else {
						var position = new google.maps.LatLng(
								parseFloat(latListArray[i]),
								parseFloat(lonListArray[i]));
						var infor = nameListArray[i];
						createMarker(position, '<a onclick="javascript:openPopup('
								+ idListArray[i] + ')">' + "<b>" + nameListArray[i] 
								+ "</b><br/>" + addressListArray[i] + '<\/a>',
								infor, i, 'image/normal_location.png');
					}
				}
			}
			if (duplicate) { 
				directionsDisplay.setMap(map);
				var latId = document.getElementById("latId").value;
				var lonId = document.getElementById("lonId").value;
				var keywords = document.getElementById("home_text").value;
				var nearestLatId = "<s:property value='%{pharmacyTop.lat}' />";
				var nearestLonId = "<s:property value='%{pharmacyTop.lon}' />";
				var nearestId = "<s:property value='%{pharmacyTop.pharmacyId}' />";
				var nearestName = $("#nearestName").val();
				var nearestAddress = "<s:property value='%{pharmacyTop.address}' />";
				var startLocation = new google.maps.LatLng(
						parseFloat(nearestLatId),
						parseFloat(nearestLonId));
				var endLocation = new google.maps.LatLng(parseFloat(latId),
						parseFloat(lonId));
				var request = {
					origin : startLocation,
					destination : endLocation,
					travelMode : google.maps.DirectionsTravelMode.WALKING
				};
				directionsDisplay.setPanel(document
						.getElementById('directions_panel'));
				directionsService
						.route(
								request,
								function(response, status) {
									if (status == google.maps.DirectionsStatus.OK) {
										var length = nameListArray.length;
										var bounds = new google.maps.LatLngBounds();
										var startObj = new Object();
										var endObj = new Object();	
										var legs = response.routes[0].legs;
										for (i = 0; i < legs.length; i++) {
											if (i == 0) {
												startObj.latlng = legs[i].start_location;
												startObj.address = legs[i].start_address;
												startObj.marker = createMarker(
														legs[i].start_location,
														'<a onclick="javascript:openPopup('
																+ nearestId
																+ ')">'
																+ "<b>"
																+ nearestName
																+ "</b><br/>"
																+ nearestAddress
																+ '<\/a>',
																nearestName,
														0, 'image/your_location.png');
											}
											endObj.latlng = legs[i].end_location;
											endObj.address = legs[i].end_address;
											var steps = legs[i].steps;
											for (j = 0; j < steps.length; j++) {
												var nextSegment = steps[j].path;
												for (k = 0; k < nextSegment.length; k++) {
													polyline.getPath().push(
															nextSegment[k]);
													bounds
															.extend(nextSegment[k]);
												}
											}
										}
										endObj.marker = createMarker(
												endObj.latlng,
												"<b>"
														+ 'Địa điểm bạn đang đứng'
														+ "</b><br/>"
														+ keywords,
														'Địa điểm bạn đang đứng'
														, length, 'image/nearest_location.png');
										polyline.setMap(map);
										map.fitBounds(bounds);
										makeSidebar();
										updatestatus();
										/* citymap['Địa điểm của bạn'] = {
												  center: new google.maps.LatLng(latId, lonId),
												  population: 5000
												}; */
										/* circleMarker  = new google.maps.Marker({
											map: map,
											position: new google.maps.LatLng(latId, lonId),
											draggable: true,
											title: 'Địa điểm bạn đang đứng',
										}); */
										var length = markerArray.length - 1;
										cityCircle = new google.maps.Circle({
											map: map,
											fillColor: '#A8DFF4',
											strokeColor: 'blue',
											strokeWeight: 1,
											radius: 5000
										
										});
										cityCircle.bindTo("center", markerArray[length], "position");
										/* for (var city in citymap) {
										    // Construct the circle for each value in citymap. We scale population by 20.
										    var populationOptions = {
										      strokeColor: '#FF0000',
										      strokeOpacity: 0.8,
										      strokeWeight: 2,
										      fillColor: '#FF0000',
										      fillOpacity: 0.35,
										      map: map,
										      center: citymap[city].center,
										      radius: citymap[city].population
										    };
										    cityCircle = new google.maps.Circle(populationOptions);
										  } */
									}
								});
			 } 
		}
		

	}
	function createMarker(latlng, content, label, i, imgIcon) {
		var contentString = content;
		marker = new google.maps.Marker({
			position : latlng,
			draggable : true,
			map : map,
			title : label,
			icon: imgIcon
		});
		marker.myname = contentString;
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
	// Rebuilds the sidebar to match the markers currently displayed ==
	function makeSidebar() {
		var j = markerArray.length - 2;
		var nearestId = "<s:property value='%{pharmacyTop.pharmacyId}' />";
		var img = "<s:property value='%{pharmacyTop.imgPath}' />";
		$('#side_bar')
				.append(
						'<div id="pharmacyBox"><div id="banDo"><a href="javascript:myclick('
								+ j
								+ ')">'
								+ '<b>Bản Đồ</b>'
								+ '<\/a></div><p  id="box_content" onclick="javascript:openPopup('
								+ nearestId + ')"><a>'
								+ markerArray[j].myname + '<\/a><\/p><img src=Pharmacy_Images/'+ "" + img + "" + ' \/>'
								+ '<\/div>');
		if(count < markerArray.length - 2) {
			var markerLength = markerArray.length;
			var x;
			if(markerLength == 3) {
				x = 1;
			} else {
				x = 2;
			}
			for ( var i = 0; i < x; i++) {
				$('#side_bar')
						.append(
								'<div id="pharmacyBox"><div id="banDo"><a href="javascript:myclick('
										+ i
										+ ')">'
										+ '<b>Bản Đồ</b>'
										+ '<\/a></div><p  id="box_content" onclick="javascript:openPopup('
										+ idListArray[i] + ')"><a>'
										+ markerArray[i].myname + '<\/a><\/p><img src=Pharmacy_Images/'+ "" +imageListArray[i]+ "" + ' \/>'
										+ '<\/div>');
				count++;
			}
		}
		updatestatus();
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
						'',
						'scrollbars=yes,location=no,resizable=no,width=1000, height=500,top=0,left=0,top='
								+ tops + ',left=' + left);
	}
</script>
<script type="text/javascript">
	function updatestatus() {
		//Show number of loaded items
		var totalItems = $('#side_bar p').length;
		var countPharmacyList = $("#countPharmacyList").val();
		if (countPharmacyList == 0) {
			$('#status').text('Hiển thị ' + totalItems + '/' + 0 + ' kết quả');
		} else {
			$('#status').text(
					'Hiển thị ' + totalItems + '/' + countPharmacyList
							+ ' kết quả (Bán kính 5km)' );
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

				//fetch new items
				$('#status').text('Loading more items...');
				for ( var i = count; i < count + 2; i++) {
					if (i < markerArray.length - 1) {
						if (i != markerArray.length - 2) {
							$('#side_bar')
									.append(
											'<div id="pharmacyBox"><div id="banDo"><a href="javascript:myclick('
													+ i
													+ ')">'
													+ '<b>Bản Đồ</b>'
													+ '<\/a></div><p  id="box_content" onclick="javascript:openPopup('
													+ idListArray[i]
													+ ')"><a>'
													+ markerArray[i].myname + '<\/a><\/p><img src=Pharmacy_Images/'+ "" +imageListArray[i]+ "" + ' \/>'
													+ '<\/div>');
						}
					}
					updatestatus();
				}
				count = count + 2;
			}
		}
		setTimeout('scrollalert();', 1500);
	}
</script>
<style type="text/css">
#containerScroll {
	width: 400px;
	margin: 0px auto;
}

#containerScroll>a {
	background: #eee;
	color: #666;
	font-family: Arial, sans-serif;
	font-size: 0.75em;
	padding: 5px;
	margin: 0;
	text-align: right;
}

#containerScroll>p {
	background: #eee;
	color: #666;
	font-family: Arial, sans-serif;
	font-size: 0.75em;
	padding: 5px;
	margin: 0;
	text-align: right;
}

#scrollbox {
	overflow: scroll;
	width: 338px;
	height: 455px;
	border-top: 0px;
	border-left: 2px solid #dcdcdc;
	border-right: 2px solid #dcdcdc;
	border-bottom: 2px solid #dcdcdc;
	background-color: #f1f0f1;
}

#googleMap {
	width: 635px;
	height: 500px;
}

.status span {
	color: #3a3a3d;
	font-style: italic;
	font-weight: bold;
}

.status {
	position: relative;
	margin: 0px;
	padding: 10px 0 0 0;
	height: 30px;
	border-top: 2px solid #dcdcdc;
	border-left: 2px solid #dcdcdc;
	border-right: 2px solid #dcdcdc;
	border-bottom: 0px;
	background-color: #f1f0f1;
	text-align: center;
}

#pharmacyBox {
	margin: 5px 5px;
	border: 1px solid #dcdcdc;
	background-color: white;
	width: 300px;
	height: 140px;
	padding-left:9px;
}

#pharmacyBox #banDo a {
	color: #B80000;
	text-decoration: none !important;
}

#pharmacyBox #banDo{
	background-image: url("css/images/map-icon.png");
	background-position: 100% 0%;
	background-repeat:no-repeat;
	width:80px;
	padding-top:10px;
	height: 25px;
	position: relative;
	margin-left: 2px;
	bottom: -12px;
}

#pharmacyBox #banDo:hover {
	text-decoration: underline !important;
}

#pharmacyBox:hover {
	background-color: rgb(221, 228, 238);
}

#box_content {
	margin: 30px 0 0 0;
	height: 55px;
	width: 235px;
	padding:0px;
	position: relative;
	display: inline-block;
}
#pharmacyBox img {
	width:56px;
	height: 54px;
	position: relative;
	display: inline-block;
	z-index: 0; 
	box-shadow: 0 0 6px rgba(132, 132, 132, .75);
	border-radius: 4px; 
}
</style>
</head>
<sj:head />
<script type="text/javascript" src="javascript/footer.js"></script>
<body onload="initialize()">
	<div id="language">
		<div id="notice">
			<span>Xin chào </span> <s:property
					value="#session['userName']" /> | <a href="logout">Đăng
				xuất</a> | <a href="ChangePassword">Đổi mật khẩu</a>
		</div>
	</div>
	<div id="banner">
		<div id="banner_container">
			<img id="logo-text" src="css/images/my-banner-and-slogan-demo1.png" />	
		</div>
	</div>
	<div id="menu_container">
		<div id="menu">
			<ul id="ulMenu">
				<li><a href="home">Trang chủ</a></li>
				<li><a href="search_pharmacyInfo">Tìm nhà thuốc</a></li>
				<li class="inner"><a href="search_nearestPharmacy" id="current">Nhà
						thuốc gần nhất</a></li>
			</ul>
		</div>
	</div>
	<div id="container">
		<!-- <div id="page_title">
			<ul>
				<li><a href="search_nearestPharmacy.jsp">Tìm nhà thuốc gần
						nhất</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div> -->
		<s:form action="nha-thuoc-gan-nhat" method="post" theme="simple">
			<div id="searchMedicines">
					<s:hidden name="pharamcyNameList" id="pharamcyNameList" />
					<s:hidden name="addressList" id="addressList" />
					<s:hidden name="latList" id="latList" />
					<s:hidden name="lonList" id="lonList" />
					<s:hidden name="pharmacyIdList" id="pharmacyIdList" />
					<s:hidden name="imgPathList" id="imgPathList" />
					<s:hidden name="duplicate" id="duplicate" />
					<s:hidden name="searched" id="searched" />
					<s:hidden name="mLat" id="latId" />
					<s:hidden name="mLon" id="lonId" />
					<s:hidden name="currentLat" id="currentLat" />
					<s:hidden name="currentLon" id="currentLon" />
					<s:hidden name="address" id="addressId" />
					<s:hidden name="countPharmacyList" id="countPharmacyList" />
					<s:hidden name="popup" id="popup" />
					<s:hidden name="refresh" value="true"/>
					<s:hidden name="nearestName" id="nearestName" />
					<s:hidden name="hasNextLocation" id="hasNextLocation" />
					<s:hidden name="noResult" id="noResult" />
					<br/>
					<div style="margin: 5px auto 15px auto;width:90%">
						<s:textfield name="keyword" placeholder="Tìm theo địa chỉ"
							style="width: 87%; height: 25px; padding-left: 5px; margin-left:0px; "
							id="home_text" maxlength="200"/>
						<s:submit name="search_bt" value="Tìm Kiếm" id="home_submit"
							cssClass="smButton" style="margin-right:0px;"action="nha-thuoc-gan-nhat" />
					</div>
					<s:if test="noResult == true">
						<div class="notice searchInvalid">
							<s:property value="message"/>
						</div>
					</s:if>
				<table style="margin: 5px auto 50px auto;">
					<tr>
						<td style="padding: 0px;">
							<p class="status">
								<span id="status"></span>
							</p>
							<div id="scrollbox">
	
								<div id="side_bar"></div>
							</div>
	
						</td>
						<td><s:div id="googleMap" /></td>
					</tr>
				</table>
				<div id="directions_panel" />
			</div>
		</s:form>
	</div>
</body>
</html>