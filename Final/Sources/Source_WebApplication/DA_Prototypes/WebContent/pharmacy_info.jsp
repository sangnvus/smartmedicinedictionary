<%--
    Document   : search_Medicines
    Created on : Apr 27, 2013, 10:59:49 PM
    Author     : W7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/map.css">
<script type="text/javascript" src="javascript/footer.js"></script>
<script type="text/javascript" src="javascript/show_hide.js"></script>
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/login.js"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/validate.file.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
<script src="javascript/js-image-slider.js" type="text/javascript"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#completeButton").mouseover(function () {
			codeAddress();
		});
		$("#street").keyup(function () {
			codeAddress();
		});
		$("#houseNo").keyup(function () {
			codeAddress();
		});
	});
</script>
<script type="text/javascript">
	var userGroup1;
	var userGroup2;
	var user;
	$(document).ready(function() {
		userGroup1 = "<s:property value="#session['userGroup1'].email"/>";
		userGroup2 = "<s:property value="#session['userGroup2'].email"/>";
		if (userGroup1) {
			user = userGroup1;
		} else if (userGroup2){
			user = userGroup2;
		}
		document.getElementById("userGroup").value = user;
	});
</script>
<script type="text/javascript">
	var myCenter = new google.maps.LatLng(21.033333, 105.850000);
	var marker;
	var map;
	var latlng;
	var mapProp;
	var geocoder;
	var markerArray = [];
	var cityStrList;
	var districtStrList;
	var changeInfo;
	function initialize() {
		var lon = document.getElementById("longtitude").value;
		var lat = document.getElementById("latitude").value;
		changeInfo = document.getElementById("changeInfo").value;
		if(lon && lat) {
			latlng = new google.maps.LatLng(lat, lon);
			myCenter = new google.maps.LatLng(lat, lon);
		}
		geocoder = new google.maps.Geocoder();
		//new google.maps.LatLng(21.033333, 105.850000);
		var mapProp = {
			center : myCenter,
			zoom : 15,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
		marker = new google.maps.Marker({
			position: latlng,
			map : map
		});
		markerArray.push(marker);
		map.streetViewControl = false;
		
		if(changeInfo == 'true') {
			google.maps.event.addListener(map, 'click', function(event) {
				clearMarker();
				marker = new google.maps.Marker({
					position : event.latLng,
					map : map
				});
				markerArray.push(marker);
				var yeri = event.latLng;
				document.getElementById('latitude').value = yeri.lat().toFixed(15);
				document.getElementById('longtitude').value = yeri.lng().toFixed(15);
			});
		}
	}
	function cityChange() {
		document.getElementById("district").value = 1;
		selectChange();
	}
	function selectChange() {
		var select1 = document.getElementById("city");
		var select2 = document.getElementById("district");
		var select1Value = select1.value;
		var select2Value = select2.value;
		var lat = 21.033333;
		var lng = 105.850000;
		clearMarker();
		document.getElementById("street").value = "";
		document.getElementById("houseNo").value = "";
		document.getElementById('latitude').value = '0.0';
		document.getElementById('longtitude').value = '0.0';
		if (!select2Value || select2Value == -1) {
			document.myForm.street.disabled = true;
			document.myForm.houseNo.disabled = true;
		}
		if (select2Value && select2Value != -1) {
			lat = select2Value.split('~')[1];
			lng = select2Value.split('~')[2];
			document.getElementById("cityId").value = select1Value.split('~')[0];
			document.getElementById("districtId").value = select2Value
					.split('~')[0];
			document.myForm.street.disabled = false;
			document.myForm.houseNo.disabled = false;
		} else if (select1Value && select1Value != -1) {
			lat = select1Value.split('~')[1];
			lng = select1Value.split('~')[2];
			document.getElementById("telAreaCode").innerHTML = "+"
					+ select1Value.split('~')[4];
		}
		myCenter = new google.maps.LatLng(parseFloat(lat), parseFloat(lng));
		map.setOptions({
			center : myCenter,
			zoom : 15
		});
	}
	function clearMarker() {
		// First, remove any existing markers from the map.
		for (i = 0; i < markerArray.length; i++) {
			markerArray[i].setMap(null);
		}

		// Now, clear the array itself.
		markerArray = [];
	}

	function codeAddress() {
		var lat = $("#latitude").val();
		var lon = $("#longtitude").val();
		if ((!lat) || (lon == '0.0')) {
			if (($("#houseNo").val() != '') && ($("#street").val() != '') && ($("#district").val() != '-1') && ($("#city").val() != '-1')){
				var address = $("#houseNo").val() + "," + $("#street").val() + "," + $("#district").val().split("~")[3] + "," + $("#city").val().split("~")[3];
				alert(address);
				geocoder.geocode({"address": address}, function(results, status) { 
				if (status == google.maps.GeocoderStatus.OK) { 
					document.getElementById('mLatIdBase').value = results[0].geometry.location
							.lat().toFixed(15);
					document.getElementById('mLonIdBase').value = results[0].geometry.location
							.lng().toFixed(15);
					} else { 
					   	document.getElementById('mLatIdBase').value = "0.0";
					    document.getElementById('mLonIdBase').value = "0.0";
					} 
				});
			}
		}
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".p_map").show();
		$(".map").css("text-decoration", "underline");
		$(".p_video").hide();
		$(".p_anh").hide();
		$(".video").click(function() {
			/* $(".p_video").append('<object id="videoPath"> <video width="100%" height="400" controls> <source src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacyPopup.videoPath}" />" type="video/mp4"></source> </video> </object>'); */
			$(".p_video").show();
			$(".p_anh").hide();
			$(".p_map").hide();
			$(".video").css("text-decoration", "underline");
			$(".anh").css("text-decoration", "none");
			$(".map").css("text-decoration", "none");
		});
		$(".anh").click(function() {
			/* $("#videoPath").remove();
			$(".p_video").append('<object id="videoPath"> <video width="100%" height="400" controls> <source src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacyPopup.videoPath}" />" type="video/mp4"></source> </video> </object>'); */
			$(".p_anh").show();
			$(".p_map").hide();
			$(".p_video").hide();
			$(".anh").css("text-decoration", "underline");
			$(".video").css("text-decoration", "none");
			$(".map").css("text-decoration", "none");
		});
		$(".map").click(function() {
			/* $("#videoPath").remove();
			$(".p_video").append('<object id="videoPath"> <video width="100%" height="400" controls> <source src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacyPopup.videoPath}" />" type="video/mp4"></source> </video> </object>'); */
			$(".p_map").show();
			$(".p_anh").hide();
			$(".p_video").hide();
			$(".map").css("text-decoration", "underline");
			$(".anh").css("text-decoration", "none");
			$(".video").css("text-decoration", "none");
		});
		$("#direct").click(function() {
			/* $("#videoPath").remove();
			$(".p_video").append('<object id="videoPath"> <video width="100%" height="400" controls> <source src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacyPopup.videoPath}" />" type="video/mp4"></source> </video> </object>'); */
			$(".p_map").show();
			$(".p_anh").hide();
			$(".p_video").hide();
			$(".map").css("text-decoration", "underline");
			$(".anh").css("text-decoration", "none");
			$(".video").css("text-decoration", "none");
		});
	});
</script>
<style>
	#tabmenu {
		margin: 60px 0px 0px 0px;
		padding: 0px;
		z-index: 1;
		padding-left: 30px;
	}
	
	#tabmenu li {
		display: inline;
		overflow: hidden;
		list-style-type: none;
		min-width: 100px;
	}
	
	#tabmenu .tab,#tabmenu .active {
		color: white;
		background: rgb(47, 100, 168);
		padding: 10px 15px 0px 15px;
		margin: -6px;
		text-decoration: none;
		cursor: hand;
		border-top-left-radius: 25px;
		border-top-right-radius: 70px;
		text-align: center;
		display: inline;
		position: relative;
		border: 1px solid #dcdcdc;
		font-size: 16px;
	}
	
	#tabmenu .active {
		color: black;
		background: rgb(221, 228, 238);
		border-bottom: 0px;
		z-index: 1;
		height: 50px;
	}
	
	#content {
		background: rgb(221, 228, 238);
		padding: 10px 10px 10px 10px;
		border-top: none;
		z-index: 2;
		border-radius: 15px 0;
		min-height: 100px;
		font-size: 17px;
		border: 2px solid #dcdcdc;
		border-top: 0px !important;
		width: 1063px;
	}
	
	#n_table {
		text-align: left;
		width: 900px;
		margin: 30px auto;
		padding: 5px;
	}
	
	#n_table #s_text {
		text-align: right;
		margin-right: 28px;
	}
	
	#n_table a {
		color: #B80000;
		text-decoration: none;
	}
	
	#n_table a:hover {
		color: orange;
		text-decoration: underline;
	}
	</style>
<title>Medicine Management</title>
<sj:head/>
</head>
<body onload="initialize();">
	<div id="language">
		<div id="notice">
			<span>Xin chào </span> <a href="pharmacy_info.jsp"><s:property
					value="#session['userGroup2'].email" /></a> | <a href="logout">Đăng
				xuất</a> | <a href="home.jsp">Đổi mật khẩu</a>
		</div>
	</div>
	<div id="banner">
		<div id="banner_container">
			<img id="logo-text" src="css/images/my-banner-and-slogan-demo1.png" />
		</div>
	</div>
	<div id="menu_container">
		<div id="menu">
			<ul>
				<li><a href="home">Trang chủ</a></li>
				<li><a href="search_medicines">Tìm kiếm thuốc</a></li>
				<li><a href="search_pharmacyInfo">Tìm nhà thuốc</a></li>
				<li><a href="search_nearestPharmacy">Nhà thuốc gần nhất</a></li>
				<li><a href="register">Đăng ký</a></li>
				<li><a href="aboutUs">Giới thiệu</a></li>
				<li><a href="RepresentativeInfor" id="current">Hiệu thuốc
						của bạn</a>
			</ul>

		</div>
	</div>
	<div id="container">
		<div id="page_title">
			<ul>
				<li><a href="admin_medicineManagement.jsp" id="current">
						Thông tin hiệu thuốc</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div>
		<div id=register>
			<ul id="tabmenu">
				<li onclick="makeactive(1)"><div class="tab active" id="tab1">
						<a id>Thông tin</a>
					</div></li>
				<li onclick="makeactive(2)"><div class="tab" id="tab2">Thuốc</div></li>
			</ul>
			<s:form theme="simple" acceptcharset="utf-8" action="chinh-sua-thong-tin-hieu-thuoc" method="post" id="myForm" enctype="multipart/form-data">
			<s:url var="remoteurl" action="relatedSelectAction" >
				<%-- <s:param name="cityStr" value="%{cityStr}" /> --%>
			</s:url>
			<s:url var="typeOfBusinessUrl" value="typeOfBusinessAction" />
			<s:url var="workingUrl" action="workingHouse" />
			<s:hidden name="userGroup" id="userGroup"/>
			<s:hidden name="hasvideo" id="hasvideo" />
			<s:hidden name="changeInfo" id="changeInfo" />
			<s:hidden name="pharmacyId" id="pharmacyId"/>
			<s:hidden name="cityStr" id="cityStr" />
			<s:hidden name="districtStr" id="districtStr" />
			<s:hidden name="longtitude" id="longtitude" />
			<s:hidden name="latitude" id="latitude" />
			<s:hidden name="mLat" id="mLatId" />
			<s:hidden name="mLon" id="mLonId" />
			<s:hidden name="latitudebase" id="mLatIdBase" />
			<s:hidden name="longtitudebase" id="mLonIdBase" />
			<s:hidden name="cityId" id="cityId" />
			<s:hidden name="districtId" id="districtId" />
			<s:if test="!changeInfo">
				<div id="register">
					<div id="rootElement">
						<div id="top_info">
							<span class="title"><!--[<s:property value="%{pharmacy.typeOfBusiness}"/>]--></span> <span class="name"><s:property value="%{pharmacy.pharmacyName}"/> </span>
						</div>
						<div class="clear"></div>
						<div id="component_left">
							<li class="info" id="address"><b>Tên:</b> <s:property value="%{pharmacy.pharmacyName}"/></li>
							<li class="info" id="address"><b>Địa chỉ:</b> <s:property value="%{pharmacy.address}"/> </li>
							<li class="info" id="phoneNumber"><b>Số điện thoại:</b> <s:property value="%{pharmacy.homePhone}"/> </li>
							<li class="info" id="phoneNumber"><b>Giấy phép kinh doanh
									số:</b> <s:property value="%{pharmacy.businessLicenseNo}" /></li>
							<li class="info" id="address"><b>Loại hình:</b> <s:property value="%{pharmacy.typeOfBusinessName}"/> </li>
							<li class="info" id="phoneNumber"><b>GPP số:</b> <s:property value="%{pharmacy.gppNo}"/> </li>
							<li class="info" id="phoneNumber"><b>Bằng khen, chứng
									nhận:</b> <s:property value="%{pharmacy.notes}"/> </li>
						</div>
						<s:submit name="search" value="Chỉnh sửa thông tin" id="home_submit"
										action="chinh-sua-thong-tin-hieu-thuoc-action" theme="simple" cssClass="smButton"/>
						<s:submit name="Danh Mục Thuốc" value="Danh Mục Thuốc" id="home_submit" action="danh-muc-thuoc" theme="simple" cssClass="smButton" />
						<div id="midRightPopup">
							<div id="top">
								<li class="header map ">Google Map</li>
								<li class="header anh ">Ảnh</li>
								<li class="header video ">Video</li>
							</div>
							<div class="p_anh">
								<img
									src="Pharmacy_Images/<s:property value='%{pharmacy.imgPath}' />"
									style="width: 100%; height: 400px;" />
							</div>
							<div id="nodeElement1" class="p_video">
								<s:if test="hasvideo">
									<object>
										<source src="movie.mp4" type="video/mp4">
										<source src="movie.ogg" type="video/ogg">
										<source src="movie.webm" type="video/webm">
										<video width="276px" height="286px" controls> <source
											src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
											type="video/mp4"></source> </video>
										<%-- <embed src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />" type="application/x-shockwave-flash"> --%>
									</object>
									<%-- <iframe width="100%" height="400" id="videoPath"
								src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />?autoplay=false"> </iframe> --%>
								</s:if>
								<s:else>
									<img
										src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
										style="width: 100%; height: 400px;" />
								</s:else>
								<%-- <iframe width="100%" height="400" id="videoPath"
						src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacyPopup.videoPath}" />?autoplay=false"> </iframe> --%>
								<%-- <object id="videoPath">
									<video width="100%" height="400" controls> <source
										src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
										type="video/mp4" /> </video>
								</object> --%>
							</div>
							<div class="p_map">
								<div id="googleMap" style="width: 100%; height: 400px;" />
							</div>

						</div>
						<%-- <img src="Pharmacy_Images/<s:property value="%{pharmacy.imgPath}" />"
							style="position: absolute; top: 74px; left: 680px; width: 276px; height: 286px;" /> --%>
						<!--<s:if test="hasvideo">
							<object>
								<video width="276px" height="286px" controls>
									<source src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
										type="video/mp4"></source>
								</video>
							</object>
						</s:if>
						<s:else>
							<img src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
							style="position: absolute; left: 802px; width: 200px; height: 200px;" />
						</s:else>-->
						<%--
					<div id="component_right">
						<li class="info" id="phoneNumber"> <b>Ảnh đại diện</b>
						</li>
					</div>
	--%>
					</div>
				</div>
			</s:if>
			<s:else>
				<div id=searchMedicines>
					<div id="rootElement" class="step1">
							<div id="register_head">Thông Tin Hiệu Thuốc</div>
							<div id="nodeElement1">
								<div id="nodeElement21">
									<div id="register_title" class="required header_small1">Tên</div>
									<div class="fieldgroup">
										<s:textfield id="pharmacyName" name="pharmacyName"
											cssErrorClass="errorStyle" cssClass="textbox short" value="%{pharmacy.pharmacyName}" />
									</div>
									<!-- <div class="error">
										<s:fielderror fieldName="pharmacyName" theme="simple" />
									</div> -->
								</div>
								<div id="nodeElement21">
									<div id="register_title" class="required header_small1">Giấy
										phép kinh doanh số</div>
									<div class="fieldgroup">
										<s:textfield id="businesslicenseNo" name="businesslicenseNo"
											cssErrorClass="errorStyle" cssClass="textbox short" value="%{pharmacy.businessLicenseNo}" />
									</div>
									<!-- <div class="error">
										<s:fielderror fieldName="businesslicenseNo" theme="simple" />
									</div>-->
								</div>
								<div id="nodeElement21">
									<div id="register_title" class="header_small1">Trực thuộc
										công ty</div>
									<s:textfield id="pharCompany" name="pharCompany"
										cssErrorClass="errorStyle" cssClass="textbox short" value="%{pharmacy.pharCompany}"/>
									<!--<div class="error">
										<s:fielderror fieldName="pharCompany" theme="simple" />
									</div>-->
								</div>
							</div>
							<div id="nodeElement1">
								<div id="nodeElement21">
									<div id="register_title" class="required header_small1">Số
										điện thoại</div>
									<div class="fieldgroup">
										<span class="spanHomePhone"> <label id="telAreaCode">+4</label>
										</span>
										<s:textfield id="homePhone" name="homePhone"
											cssErrorClass="errorStyle" cssClass="homePhone" value="%{pharmacy.homePhone}"/>
									</div>
									<!-- <div class="error">
										<s:fielderror fieldName="homePhone" theme="simple" />
									</div>-->
								</div>
								<div id="nodeElement21">
									<div id="register_title" class="header_small1">GPP số
										(nếu có)</div>
									<s:textfield id="gppNo" name="gppNo" cssErrorClass="errorStyle"
										cssClass="textbox short" value="%{pharmacy.gppNo}" />
									<!--<div class="error">
										<s:fielderror fieldName="gppNo" theme="simple" />
									</div>-->
								</div>
								<div id="nodeElement21">
									<div id="register_title" class="required header_small1">Loại
										hình</div>
									<sj:select href="%{typeOfBusinessUrl}" id="typeOfBusiness" name="typeOfBusiness"
												list="typeOfBusinessList" listKey="typeBusinessId" listValue="typeBusinessName"
												cssClass="select" />
								</div>
							</div>
							<div id="nodeElementLeft">
								<div id="nodeElement1">
									<div id="nodeElement21">
										<div id="register_title" class="required header_small1">Tỉnh,
											thành phố</div>
										<div></div>
										<div class="selectDiv">
										<!--<select id="city" name="city" class="select">
											<option value="-1">Tỉnh/Thành Phố</option>
										</select>-->
										<sj:select href="%{remoteurl}" id="city" name="city"
												list="cityList" listKey="idLatLongName" listValue="cityName"
												onChangeTopics="reloadDistrictList" headerKey="-1"
												headerValue="Tỉnh/ Thành Phố" onchange="cityChange();"
												cssClass="select" />
										</div>
									</div>
								</div>
								<div id="nodeElement1">
									<div id="nodeElement21">
										<div id="register_title" class="required header_small1">Quận,
											huyện</div>
										<div></div>
										<div class="selectDiv">
											<sj:select href="%{remoteurl}" id="district"
												formIds="myForm" reloadTopics="reloadDistrictList"
												name="district" list="districtList" listKey="idLatlngName"
												listValue="districtName" headerKey="-1"
												headerValue="Quận/ Huyện" onchange="selectChange();"
												 cssClass="select" />
										</div>
									</div>
								</div>
								<div id="nodeElement1">
									<div id="nodeElement21">
										<div id="register_title" class="required header_small1">Đường/thôn,
											xóm</div>
										<div class="fieldgroup">
											<s:textfield id="street" name="street"
												cssErrorClass="errorStyle" cssClass="textbox short"
												value="%{pharmacy.street}" />
										</div>
										<!--<div class="error">
											<s:fielderror fieldName="street" theme="simple" />
										</div>-->
									</div>
								</div>
								<div id="nodeElement1">
									<div id="nodeElement21">
										<div id="register_title" class="required header_small1">Số
											nhà, ngõ</div>
										<div class="fieldgroup">
											<s:textfield id="houseNo" name="houseNo"
												cssErrorClass="errorStyle" cssClass="textbox short"
												value="%{pharmacy.houseNo}" />
										</div>
										<!--<div class="error">
											<s:fielderror fieldName="houseNo" theme="simple" />
										</div>-->
									</div>
								</div>
								<div id="nodeElement1">
									<div id="nodeElement21">
										<div id="register_title" class="header_small1">Bằng
											khen, chứng nhận (nếu có)</div>
										<s:textarea rows="5" cols="30" name="notes" value="%{pharmacy.notes}"/>
									</div>
								</div>
								<%-- <div id="nodeElement1">
									<div id="nodeElement21">
										<div id="register_title" class="header_small1">Giờ làm việc</div>
										<br/>
										<div id="morning">
											<label>Sáng :</label>
												<sj:select href="%{workingUrl}" id="morningFromHour" name="morningFromHour"
												list="morningFromHourList" listKey="hourId" listValue="hourValue" onChangeTopics="reloadMorningToHour" cssClass="smallSelect" />
												
												<sj:select href="%{workingUrl}" id="morningFromMinute" name="morningFromMinute"
												list="morningFromMinuteList" listKey="minuteId" listValue="minuteValue"
												onChangeTopics="reloadMorningToMinute" cssClass="smallSelect" />
											<label> - </label>
												<sj:select href="%{workingUrl}" id="morningToHour" name="morningToHour"
												list="morningToHourList" listKey="hourId" listValue="hourValue" reloadTopics="reloadMorningToHour" cssClass="smallSelect" />
												
												<sj:select href="%{workingUrl}" id="morningToMinute" name="morningToMinute"
												list="morningToMinuteList" listKey="minuteId" listValue="minuteValue"
												reloadTopics="reloadMorningToMinute" cssClass="smallSelect" />
										</div>
										<br/>
										<br/>
										<div id="after">
											<label>Chiều:</label>
												<sj:select href="%{workingUrl}" id="afterFromHour" name="afterFromHour"
												list="afterFromHourList" listKey="hourId" listValue="hourValue" onChangeTopics="reloadAfterToHour" cssClass="smallSelect"  />
												
												<sj:select href="%{workingUrl}" id="afterFromMinute" name="afterFromMinute"
												list="afterFromMinuteList" listKey="minuteId" listValue="minuteValue"
												onChangeTopics="reloadAfterToMinute" cssClass="smallSelect" />
											<label> - </label>
												<sj:select href="%{workingUrl}" id="afterToHour" name="afterToHour"
												list="afterToHourList" listKey="hourId" listValue="hourValue" reloadTopics="reloadAfterToHour" cssClass="smallSelect" />
												
												<sj:select href="%{workingUrl}" id="afterToMinute" name="afterToMinute"
												list="afterToMinuteList" listKey="minuteId" listValue="minuteValue"
												reloadTopics="reloadAfterToMinute" cssClass="smallSelect" />
										</div>
									</div>
								</div> --%>
								<div id="nodeElement1">
									<div id="register_title" class="header_small1">Chọn ảnh
										đại diện</div>
								</div>
								<div id="nodeElement1">
									<s:file name="avatarImage" id="avaterImageId"
										accept=".JPG,.PNG,.BMP,.JPEG" value="%{pharmacy.imgPath}"/>
									<!--<input id="the_real_file_input" name="foobar" type="file"
									size="100">-->
								</div>
								<div id="nodeElement1">
									<div id="register_title" class="header_small1">Tải video
										quảng cáo</div>
								</div>
								<div id="nodeElement1">
									<s:file name="video" id="videoId" accept=".MP4,.FLV" />
								</div>

							</div>
							<div id="tu">
								<div id="nodeElementRight">
									<div id="nodeElement1">
										<div id="register_title" class="header_small1">Chọn vị trí trên bản đồ</div>
										<div class="register_explain explain_normal">hệ thống sẽ tự động xác
											định địa chỉ bạn cung cấp hoặc bạn có thể chọn một cách chính xác
											vị trí bạn mong muốn trên bản đồ dưới đây:</div>
										<div class="clear"></div>
										<div id="googleMap" style="width: 545px; height: 380px;" />
									</div>
								</div>
							</div>
							<div id="r_footer">
									<s:submit value="Lưu lại" cssClass="r_button space30"
										action="chinh-sua-thong-tin-hieu-thuoc" id="completeButton" />
									<s:submit value="Hủy bỏ" cssClass="cancel"
										action="huy-bo-chinh-sua" id="r_button" />
							</div>
						</div>
				</div>
			</s:else>
		</s:form>
		</div>
	</div>
</body>
</html>
