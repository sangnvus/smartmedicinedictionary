<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Đăng ký nhà thuốc</title>
</head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/pharmacy.css">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/login.js"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/validate.file.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
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
													'<li><a href="aboutUs">Giới thiệu</a></li>'
													+ '<li><a href="news">Tin tức </a></li>'
													+ '<li><a href="Login">Đăng nhập</a></li>');
						} else {
							document.getElementById("language")
									.removeAttribute("style");
							document.getElementById("language").style.visibility = "visible";
							if (userGroup1) {
								$('.inner')
										.after(
												'<li><a href="admin_medicineManagement" class="admin">Quản lý thuốc</a>'
														+ '<ul>'
														+ '<li><a href="admin_registerNewMedicine"> Thêm mới'
														+ ' thuốc</a></li>'
														+ '<li><a href="admin_medicineManagement"> Tìm kiếm'
														+ ' thuốc</a></li>'
														+ '</ul></li>'
														+ '<li><a href="admin_pharmacyManagement" class="admin"> Quản lý hiệu thuốc</a></li>'
														+ '<li><a href="admin_news" >Quản lý tin tức</a></li>');

							} else {
								$(".inner")
										.after(			'<li><a href="aboutUs">Giới thiệu</a></li>'
														+ '<li><a href="news">Tin tức </a></li>'
														+ '<li><a href="RepresentativeInfor" class="admin">Nhà thuốc của bạn</a></li>');

							}
						}
					});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#buttonOne").click(function () {
			document.getElementById("errorEmail").style.display = "none";
		});
		$("#email").keyup(function () {
			document.getElementById("errorEmail").style.display = "none";
		});
		$('#password').bind('cut copy paste', function(event) {
	        event.preventDefault();
	    });
		$('#retypePassword').bind('cut copy paste', function(event) {
	        event.preventDefault();
	    });
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
			var submitStepOne = $("#submitStepOne").val();
			if(submitStepOne == 'true') {
				initialize();
			}
	});
</script>
<script type="text/javascript">
	/**
	 * Basic jQuery Validation Form Demo Code
	 * Copyright Sam Deering 2012
	 * Licence: http://www.jquery4u.com/license/
	 */
	(function($, W, D) {
		var JQUERY4U = {};

		JQUERY4U.UTIL = {
			setupFormValidation : function() {
					$("#registerForm")
							.validate(
									{
										rules : {
											representativeName : {
												required : true
											},
											email : {
												required : true,
												email : true,
												emailAlready : true,
												notSpecialCharacter: true
											},
											password : {
												required : true,
												minlength : 8
											},
											mobilePhone : {
												required : true,
												number : true,
												minlength : 9
											},
											licensureNo : {
												required : true
											},
											retypePassword : {
												required : true,
												equalTo : "#password"
											},
											pharmacyName : {
												required : true
											},
											businesslicenseNo : {
												required : true
											},
											homePhone : {
												required : true,
												number : true,
												minlength : 7
											},
											street : {
												required : true
											},
											houseNo : {
												required : true
											},
											city : {
												valueNotEquals : "-1"
											},
											district : {
												valueNotEquals : "-1"
											}
										},
										messages : {
											representativeName : { 
												required : "Mục này bắt buộc phải nhập",
											},
											password : {
												required : "Mục này bắt buộc phải nhập",
												minlength : "Mật khẩu của bạn phải có ít nhất 8 ký tự"
											},
											email : {
												required : "Mục này bắt buộc phải nhập",
												email : "Xin vui lòng nhập đúng định dạng email",
											},
											retypePassword : {
												required : "Mục này bắt buộc phải nhập",
												equalTo : "Mật khẩu xác nhận không khớp"
											},
											mobilePhone : {
												required : "Mục này bắt buộc phải nhập",
												number : "Sai định dạng. Vui lòng nhập số",
												minlength : "Số điện thoại phải có ít nhất 9 số"
											},
											licensureNo : {
												required : "Mục này bắt buộc phải nhập"
											},
											pharmacyName : {
												required : "Mục này bắt buộc phải nhập"
											},
											businesslicenseNo : {
												required : "Mục này bắt buộc phải nhập"
											},
											homePhone : {
												required : "Mục này bắt buộc phải nhập",
												number : "Sai định dạng. Vui lòng nhập số",
												minlength : "Số điện thoại phải có ít nhất 7 số"
											},
											street : {
												required : "Mục này bắt buộc phải nhập"
											},
											houseNo : {
												required : "Mục này bắt buộc phải nhập"
											}
										},
										submitHandler : function(form) {
											form.submit();
										}
									});
					$.validator.addMethod("customvalidation", function(value,
							element) {
						return /^[A-Za-z\d=#$%@_ -]+$/.test(value);
					}, "Mật khẩu không được chứa ký tự đặc biệt");
					$.validator.addMethod("valueNotEquals", function(value,
							element, arg) {
						return arg != value;
					}, "Mục này không được bỏ trống");
					$.validator.addMethod("notSpecialCharacter", function(value,
							element, arg) {
						return /^[A-Za-z\d@_ ]+/.test(value);
					}, "Email không được chưa ký tự đặc biệt");
					$.ajax({
								type : 'post',
								url : "checkAlreadyEmail.action",
								dataType : "json",
								data : "",
								success : function(data) {
									$.validator
											.addMethod("emailAlready", function(
													value, element) {
												var check = false;
												$(data.email).each(
														function(index, el) {
															if (el == value) {
																check = true;
															}
														});
												return check == false;
											},
													$("#msgEmailError").val());
								}
							});
			}
		}

		//when the dom has loaded setup form validation rules
		$(D).ready(function($) {
				JQUERY4U.UTIL.setupFormValidation();
		});

	})(jQuery, window, document);
</script>
<script type="text/javascript">
	function resetConfirmPassword() {
		document.getElementById("retypePassword").value = "";
	}
</script>
</script>
<script type="text/javascript">
	var myCenter = new google.maps.LatLng(21.033333, 105.850000);
	var marker;
	var map;
	var mapProp;
	var geocoder;
	var markerArray = [];
	var minZoomLevel = 13;
	var strictBounds;
	var northLatBound;
	var northLongBound;
	var sounthLatBound;
	var sounthLongBound;
	function initialize() {
		geocoder = new google.maps.Geocoder();
		var cityStr = $("#cityStr").val();
		var districtStr = $("#districtStr").val();
		var mlat = $("#mLatId").val();
		var mlon = $("#mLonId").val();
		var mLatBase = $("#mLatIdBase").val();
		var mLonBase = $("#mLonIdBase").val();
		var lat = 21.033333;
		var lng = 105.850000;
		if((!cityStr && !districtStr) || (cityStr == -1)) {
			myCenter = new google.maps.LatLng(21.033333, 105.850000);
		} else {
			if(districtStr != -1) {
				document.myForm.street.readOnly = false;
				document.myForm.houseNo.readOnly = false;
				lat = districtStr.split('~')[1];
				lng = districtStr.split('~')[2];
			} else {
				lat = cityStr.split('~')[1];
				lng = cityStr.split('~')[2];
			}
			document.getElementById("telAreaCode").innerHTML = "+"
				+ cityStr.split('~')[4];
			myCenter = new google.maps.LatLng(parseFloat(lat), parseFloat(lng));
		}
		var mapProp = {
				center : myCenter,
				zoom : 14,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
		map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
		if(mlat && mlat != '0.0') {
			clearMarker();
			myCenter = new google.maps.LatLng(mlat, mlon);
			marker = new google.maps.Marker({
				position : myCenter,
				map : map
			});
			markerArray.push(marker);
			
		} else if(mLatBase && mLatBase != '0.0') {
			clearMarker();
			myCenter = new google.maps.LatLng(mLatBase, mLonBase);
			marker = new google.maps.Marker({
				position : myCenter,
				map : map
			});
			markerArray.push(marker);
		}
		map.streetViewControl = false;
		google.maps.event.addListener(map, 'click', function(event) {
			clearMarker();
			marker = new google.maps.Marker({
				position : event.latLng,
				map : map
			});
			markerArray.push(marker);
			var yeri = event.latLng;
			document.getElementById('mLatIdBase').value = yeri.lat().toFixed(15);
			document.getElementById('mLonIdBase').value = yeri.lng().toFixed(15);
		});
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
		var hasbound = true;
		clearMarker();
		document.getElementById("street").value = "";
		document.getElementById("houseNo").value = "";
		document.getElementById('mLatId').value = '0.0';
		document.getElementById('mLonId').value = '0.0';
		if (!select1Value || select1Value == -1) {
			hasbound = false;
		}
		if (!select2Value || select2Value == -1) {
			document.myForm.street.readOnly = true;
			document.myForm.houseNo.readOnly = true;
			document.getElementById("telAreaCode").innerHTML = "";
			hasbound = false;
		}
		if (select2Value && select2Value != -1) {
			hasbound = true;
			lat = select2Value.split('~')[1];
			lng = select2Value.split('~')[2];
			document.getElementById("cityId").value = select1Value.split('~')[0];
			document.getElementById("districtId").value = select2Value
					.split('~')[0];
			document.myForm.street.readOnly = false;
			document.myForm.houseNo.readOnly = false;
			northLatBound = select2Value.split('~')[5];
			northLongBound = select2Value.split('~')[6];
			sounthLatBound = select2Value.split('~')[7];
			sounthLongBound = select2Value.split('~')[8];
		} else if (select1Value && select1Value != -1) {
			hasbound = true;
			lat = select1Value.split('~')[1];
			lng = select1Value.split('~')[2];
			document.getElementById("telAreaCode").innerHTML = select1Value.split('~')[4];
			northLatBound = select1Value.split('~')[5];
			northLongBound = select1Value.split('~')[6];
			sounthLatBound = select1Value.split('~')[7];
			sounthLongBound = select1Value.split('~')[8];
		}
		if(hasbound) {
			strictBounds = new google.maps.LatLngBounds(
				     /* new google.maps.LatLng(northLatBound, northLongBound), 
				     new google.maps.LatLng(sounthLatBound, sounthLongBound) */
				    new google.maps.LatLng(sounthLatBound, sounthLongBound) ,
					new google.maps.LatLng(northLatBound, northLongBound) 
				   );
			
				// Listen for the dragend event
			   google.maps.event.addListener(map, 'dragend', function() {
			     if (strictBounds.contains(map.getCenter())) return;

			     // We're out of bounds - Move the map back within the bounds

			     var c = map.getCenter(),
			         x = c.lng(),
			         y = c.lat(),
			         maxX = strictBounds.getNorthEast().lng(),
			         maxY = strictBounds.getNorthEast().lat(),
			         minX = strictBounds.getSouthWest().lng(),
			         minY = strictBounds.getSouthWest().lat();

			     if (x < minX) x = minX;
			     if (x > maxX) x = maxX;
			     if (y < minY) y = minY;
			     if (y > maxY) y = maxY;

			     map.setCenter(new google.maps.LatLng(lat, lng));
			   }); 
			   google.maps.event.addListener(map, 'zoom_changed', function() {
		    	     if (map.getZoom() < minZoomLevel) map.setZoom(minZoomLevel);
		    	   });
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
</script>
<script type="text/javascript">
	function validate(divId, fieldId) {
		var stepOne = document.myForm.stepOne.value;
		var fieldValue = document.getElementById(fieldId).value;
		if (stepOne) {
			if (fieldValue) {
				document.getElementById(fieldId).cssErrorClass = "";
				document.getElementById(divId).removeAttribute("style");
				document.getElementById(divId).style.display = "none";
			} else {
				document.getElementById(divId).removeAttribute("style");
				document.getElementById(divId).style.visibility = "visible";
			}
		}
	}
	function deleteOverlays() {
		clearOverlays();
		markerArray = [];
	}
	function clearOverlays() {
		setAllMap(map);
		document.getElementById('mLatIdBase').value = "0.0";
		document.getElementById('mLonIdBase').value = "0.0";
		document.getElementById('mLatId').value = '0.0';
		document.getElementById('mLonId').value = '0.0';
	}
	function setAllMap(map) {
		  for (var i = 0; i < markerArray.length; i++) {
			  markerArray[i].setMap(null);
		  }
}
</script>
<sj:head />
<body>
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
	<div id="login-box" class="login-popup">
		<input type="button" class="close" value="X" id="close" />
		<div id="login_header">Đăng Nhập</div>
	</div>
	<div id="menu_container">
		<div id="menu">
			<ul id="ulMenu">
				<li><a href="home">Trang chủ</a></li>
				<li><a href="search_pharmacyInfo">Tìm nhà thuốc</a></li>
				<li><a href="search_nearestPharmacy">Nhà
						thuốc gần nhất</a></li>
				<li class="inner"><a href="register" id="current">Đăng ký</a></li>
			</ul>
		</div>
	</div>
	<div id="container">
		<!-- <div id="page_title">
			<ul>
				<li><a href="register.jsp">Đăng ký</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div> -->
		<s:form id="registerForm" theme="simple" acceptcharset="utf-8"
			action="completeStepOne" name="myForm" enctype="multipart/form-data">
			<s:url var="remoteurl" action="relatedSelectAction" />
			<s:url var="degreeUrl" action="degreeAction" />
			<s:url var="typeOfBusinessUrl" action="typeOfBusinessAction" />
			<s:hidden name="cityStr" id="cityStr"/>
			<s:hidden name="districtStr" id="districtStr" />
			<s:hidden name="cityId" id="cityId" />
			<s:hidden name="districtId" id="districtId" />
			<s:hidden name="streetStr" />
			<s:hidden name="houseNoStr" />
			<s:hidden name="homePhoneStr"/>
			<s:hidden name="pharCompanyStr" />
			<s:hidden name="pharmacyNameStr" />
			<s:hidden name="businesslicenseNoStr" />
			<s:hidden name="typeOfBusinessStr" />
			<s:hidden name="gppNoStr" />
			<s:hidden name="notesStr" />
			<s:hidden name="latitude" id="mLatId" />
			<s:hidden name="longtitude" id="mLonId" />
			<s:hidden name="latitudebase" id="mLatIdBase" />
			<s:hidden name="longtitudebase" id="mLonIdBase" />
			<s:hidden name="smdEmail" value="%{#session['smdEmail']}" id="smdEmail" />
			<s:if test="logged == true">
				<s:hidden name="msgEmailError" value="Email đã được sử dụng, vui lòng sử dụng email khác, hoặc bỏ qua bước này." id="msgEmailError"/>
			</s:if>
			<s:else>
				<s:hidden name="msgEmailError" value="Email này đã được sử dụng. Thử email khác hoặc đăng nhập để đăng ký mới." id="msgEmailError"/>
			</s:else>
			<div id=searchMedicines>
				<!-- <div id="header_big">Đăng Ký</div> -->
				<br/>
				<s:hidden name="submitStepOne" id="submitStepOne" />
				<s:hidden name="stepOne" />
				<s:hidden name="completeAll" />
				<s:hidden name="morToHour" id="morToHour"/>
				<s:hidden name="morToMinute" id="morToMinute"/>
				<s:hidden name="afToHour" id="afToHour"/>
				<s:hidden name="afToMinute" id="afToMinute"/>
				<s:hidden name="logged" id="logged" />
				<s:hidden name="ignoreStepOne" />
				<s:if test="!completeAll">
				<div class="register_explain">Bạn cần phải điền đầy đủ thông
					tin vào các phần bắt buộc (Có đánh dấu (*) ).</div>
					<s:if test="!submitStepOne">
						<div id="rootElement" class="step0">
							<div id="register_head">Bước 1: Thông Tin Người Đại Diện
								(dược sỹ chính)</div>
								<s:if test="logged == true">
									<div class="phar-check">Bạn có muốn sử dụng thông tin người dùng hiện tại làm thông tin người đại diện</div>
									<s:submit value="Có" cssClass="cancel phar-check-bt" action="ignoreStepOneAction" />
									<hr class="r-line"/>
								</s:if>
							<div id="nodeElement1">
								<div id="nodeElement2">
									<div id="register_title" class="required header_small1">Email</div>
									<div class="explain">Đây sẽ là tên đăng nhập của bạn.</div>
									<div class="fieldgroup">
										<s:textfield id="email" name="email"
											cssErrorClass="errorStyle"
											onkeyup="validate('errorEmail','email');" cssClass="textbox short" maxlength="150" />
									</div>
									<div class="error" id="errorEmail">
										<s:fielderror fieldName="emailError" theme="simple" />
									</div>
								</div>
								<div id="nodeElement2">
									<div id="register_title" class="required header_small1">Mật
										Khẩu</div>
									<div class="explain">Mật khẩu từ 8 ký tự trở lên.</div>
									<div class="fieldgroup">
										<s:password id="password" name="password"
											cssErrorClass="errorStyle" cssClass="textbox short"
											onkeypress="resetConfirmPassword();" maxlength="24" />
									</div>
									<!-- <div class="error">
										<s:fielderror fieldName="password" theme="simple" />
									</div> -->
								</div>
								<div id="nodeElement2">
									<div id="register_title" class="required header_small1">Xác
										Nhận Mật Khẩu</div>
									<div class="explain">Nhập lại mật khẩu.</div>
									<div class="fieldgroup">
										<s:password id="retypePassword" name="retypePassword"
											cssErrorClass="errorStyle" cssClass="textbox short" maxlength="24" />
									</div>
									<!-- <div class="error">
										<s:fielderror fieldName="retypePassword" theme="simple" />
									</div>-->
								</div>
							</div>
							<div id="nodeElement1">
								<div id="nodeElement2">
									<div id="register_title" class="required header_small1">Họ
										và tên</div>
									<div class="fieldgroup">
										<s:textfield id="representativeName" name="representativeName"
											cssErrorClass="errorStyle" cssClass="textbox short" maxlength="150"/>
									</div>
									<!--<div class="error">
										<s:fielderror fieldName="representativeName" theme="simple" />
									</div>-->
								</div>
								<div id="nodeElement2">
									<div id="register_title" class="required header_small1">Số
										Điện Thoại Di Động</div>
									<div class="fieldgroup">
										<s:textfield id="mobilePhone" name="mobilePhone"
											cssErrorClass="errorStyle" cssClass="textbox short" maxlength="10"/>
									</div>
									<!--<div class="error">
										<s:fielderror fieldName="mobilePhone" theme="simple" />
									</div>-->
								</div>
								<div id="nodeElement2">
									<div id="register_title" class="required header_small1">Trình
										độ</div>
									<sj:select href="%{degreeUrl}" id="degree" name="degree"
												list="degreeList" listKey="degreeId" listValue="degreeName"
												cssClass="select" />
								</div>
							</div>
							<div id="nodeElement1">
								<div id="nodeElement2">
									<div id="register_title" class="required header_small1">Giấy
										Phép Hành Nghề Số</div>
									<div></div>
									<div class="fieldgroup">
										<s:textfield id="licensureNo" name="licensureNo"
											cssErrorClass="errorStyle" cssClass="textbox short" maxlength="100" />
									</div>
									<!--<div class="error">
										<s:fielderror fieldName="mobilePhone" theme="simple" />
									</div>-->
								</div>
								<div id="nodeElement2">
									<div id="register_title" class="header_small1">Mã tài khoản</div>
									<div></div>
									<div class="fieldgroup">
										<s:textfield id="cardNumber" name="cardNumber"
											cssErrorClass="errorStyle" cssClass="textbox short" maxlength="45" />
									</div>
									<!--<div class="error">
										<s:fielderror fieldName="mobilePhone" theme="simple" />
									</div>-->
								</div>
							</div>
							<div id="stepOneButton">
								<div id="nodeElement21">
									<input type="submit" value="Hoàn tất và chuyển sang bước 2"
										class="r-button" action="completeStepOne" />
								</div>
							</div>
						</div>
					</s:if>
					<s:if test="submitStepOne">
						<s:hidden name="email" />
						<s:hidden name="password" />
						<s:hidden name="retypePassword" />
						<s:hidden name="representativeName" />
						<s:hidden name="mobilePhone" />
						<s:hidden name="degree" />
						<s:hidden name="licensureNo" />
						<s:hidden name="cardNumber" id="cardNumber" />
						<div id="rootElement" class="step1">
							<div id="register_head">Bước 2: Thông Tin Hiệu Thuốc</div>
							<s:if test="failed == true">
								<div class="notice searchInvalid">
											<s:fielderror fieldName="latLngError" />
								</div>
							</s:if>
							<div id="nodeElement1">
								<div id="nodeElement21">
									<div id="register_title" class="required header_small1">Tên</div>
									<div class="fieldgroup">
										<s:textfield id="pharmacyName" name="pharmacyName"
											cssErrorClass="errorStyle" cssClass="textbox short" maxlength="200"/>
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
											cssErrorClass="errorStyle" cssClass="textbox short" maxlength="100" />
									</div>
									<!-- <div class="error">
										<s:fielderror fieldName="businesslicenseNo" theme="simple" />
									</div>-->
								</div>
								<div id="nodeElement21">
									<div id="register_title" class="header_small1">Trực thuộc
										công ty</div>
									<s:textfield id="pharCompany" name="pharCompany"
										cssErrorClass="errorStyle" cssClass="textbox short" maxlength="200"/>
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
										<div class="spanHomePhone"><label id="telAreaCode"></label>
										</div>
										<s:textfield id="homePhone" name="homePhone"
											cssErrorClass="errorStyle" cssClass="homePhone" maxlength="8" />
									</div>
									<!-- <div class="error">
										<s:fielderror fieldName="homePhone" theme="simple" />
									</div>-->
								</div>
								<div id="nodeElement21">
									<div id="register_title" class="header_small1">GPP số
										(nếu có)</div>
									<s:textfield id="gppNo" name="gppNo" cssErrorClass="errorStyle"
										cssClass="textbox short" maxlength="45"/>
									<!--<div class="error">
										<s:fielderror fieldName="gppNo" theme="simple" />
									</div>-->
								</div>
								<div id="nodeElement21">
									<div id="register_title" class="required header_small1">Loại
										hình</div>
									<%-- <select style="width: 258px; height: 30px;"
										name="typeOfBusiness">
										<option value="1">Doanh nghiệp</option>
										<option value="2">Nhà thuốc</option>
										<option value="3">Hiệu thuốc</option>
									</select> --%>
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
										<sj:select href="%{remoteurl}" id="city" name="city"
												list="cityList" listKey="idLatLongName" listValue="cityName"
												onChangeTopics="reloadDistrictList" headerKey="-1"
												headerValue="Tỉnh/ Thành Phố" onchange="cityChange();"
												cssClass="select" cssErrorClass="errorStyle" />
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
												formIds="registerForm" reloadTopics="reloadDistrictList"
												name="district" list="districtList" listKey="idLatlngName"
												listValue="districtName" headerKey="-1"
												headerValue="Quận/ Huyện"
												onchange="selectChange();" cssClass="select" cssErrorClass="errorStyle"/>
										</div>
									</div>
								</div>
								<div id="nodeElement1">
									<div id="nodeElement21">
										<div id="register_title" class="required header_small1">Đường/thôn,
											xóm</div>
										<div class="fieldgroup">
											<%-- <s:textfield id="street" name="street"
												cssErrorClass="errorStyle" cssClass="textbox short"
												disabled="true" maxlength="100"/> --%>
											<input type="text" readonly="readonly" id="street" name="street"
												cssErrorClass="errorStyle" class="textbox short"
												maxlength="100"/>
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
											<%-- <s:textfield id="houseNo" name="houseNo"
												cssErrorClass="errorStyle" cssClass="textbox short"
												disabled="true" maxlength="50" /> --%>
											<input id="houseNo" name="houseNo"
												cssErrorClass="errorStyle" class="textbox short"
												readonly="readonly" maxlength="50" />
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
										<s:textarea rows="5" cols="30" name="notes"/>
										<!-- <textarea rows="5" cols="30" name="notes"></textarea> -->
									</div>
								</div>
								<div id="nodeElement1">
									<div id="register_title" class="header_small1">Chọn ảnh
										đại diện</div>
								</div>
								<div id="nodeElement1">
									<s:file name="avatarImage" id="avaterImageId"
										accept=".JPG,.PNG,.BMP,.JPEG"/>
									<!--<input id="the_real_file_input" name="foobar" type="file"
									size="100">-->
								</div>
								<div id="nodeElement1">
									<div id="register_title" class="header_small1">Tải video
										quảng cáo</div>
								</div>
								<div id="nodeElement1">
									<s:file name="video" id="videoId" accept=".MP4,.ogv,.webm" value="%{video}" />
								</div>

							</div>
							<div id="tu">
								<div id="nodeElementRight">
									<div id="nodeElement1">
										<div id="register_title" class="header_small1">Chọn vị trí trên bản đồ <input onclick="deleteOverlays();" type=button value="Xóa đánh dấu"> </div>
										<div class="register_explain explain_normal">hệ thống sẽ tự động xác
											định địa chỉ bạn cung cấp hoặc bạn có thể chọn một cách chính xác
											vị trí bạn mong muốn trên bản đồ dưới đây:</div>
										<div class="clear"></div>
										<div id="googleMap" style="width: 545px; height: 380px;" />
									</div>
								</div>
							</div>
							<div id="r_footer">
									<!-- <form action="backToStepOneAction" id="back"> -->
									<s:submit value="Quay lại bước 1" cssClass="cancel r-button"
										action="backToStepOneAction" />
										<!-- </form> -->
									<s:submit id="completeButton" value="Hoàn tất đăng ký"
										cssClass="r-button" action="completeRegisterAction" />
							</div>
						</div>
					</s:if>
				</s:if>
				<s:if test="completeAll">
					<%-- <div class="notice searchValid">
					<s:label>Bạn đã đăng ký nhà thuốc thành công, thông tin của bạn sẽ được kiểm duyệt trước khi được sử dụng. Cám ơn bạn!</s:label>
					</div> --%>
					<div id="r_success">
						<div id="success-header">Đăng ký thành công! </div>
						<div id="success-content">Chúc mừng bạn đã đăng ký tài khoản thành công!</br> Tài khoản của bạn sẽ được kiểm duyệt bởi người quản trị trước khi đi vào sử dụng.</br></br><u>Lưu ý:</u> Trong thời gian tài khoản chờ được kiểm duyệt thì tài khoản sẽ không thể đăng nhập được vào hệ thống</div>
					</div>
				</s:if>
			</div>
		</s:form>
	</div>
</body>
</html>

