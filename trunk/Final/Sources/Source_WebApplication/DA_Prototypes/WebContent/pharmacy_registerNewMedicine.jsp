<%--
    Document   : index
    Created on : Apr 26, 2013, 1:43:50 AM
    Author     : W7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/pagination.js"></script>
<script type="text/javascript" src="javascript/NextPagination.js"></script>
<script type="text/javascript" src="javascript/changes-tab.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel="stylesheet" type="text/css" href="css/pharmacy.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/AdministrationPharmacy.css">
<link rel="stylesheet" type="text/css" href="css/tab-menu.css">
<link rel="stylesheet" type="text/css" href="css/popup.css">
<script type="text/javascript" src="javascript/register.js"></script>
<script type="text/javascript" src="javascript/dynamic-list.js"></script>
<script type="text/javascript" src="javascript/append_n.js"></script>
<script type="text/javascript" src="javascript/checkbox.js"></script>
<script type="text/javascript" src="javascript/validate.file.js"></script>
<script type="text/javascript" src="javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
	<script type="text/javascript" src="javascript/footer.js"></script>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
<script src="javascript/js-image-slider.js" type="text/javascript"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<style>
.ui-autocomplete {
	max-height: 200px;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: hidden;
}
/* IE 6 doesn't support max-height
   * we use height instead, but this forces the menu to always be this tall
   */
* html .ui-autocomplete {
	height: 200px;
}
#tab1{
	cursor: pointer; cursor: hand;
}
#tab2{
	cursor: pointer; cursor: hand;
}
</style>
<style type="text/css">
	.tooltip {
	display:none;
	position:absolute;
	border:1px solid #333;
	background-color:#161616;
	border-radius:5px;
	padding:10px;
	color:#fff;
	font-size:12px Arial;
}
.editPharButton {
	width: 35px;
	height: 31px;
	border: none;
	margin-left: 6px;
}
</style>
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
					$("#myForm")
							.validate(
									{
										rules : {
											nameOfPharmacy : {
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
											nameOfPharmacy : {
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
					$.validator.addMethod("valueNotEquals", function(value,
							element, arg) {
						return arg != value;
					}, "Mục này không được bỏ trống");
					$.validator.addMethod("valueNotEquals", function(value,
							element, arg) {
						return arg != value;
					}, "Mục này không được bỏ trống");
			}
		}

		//when the dom has loaded setup form validation rules
		$(D).ready(function($) {
				JQUERY4U.UTIL.setupFormValidation();
		});

	})(jQuery, window, document);
</script>
<script type="text/javascript">
	$(document).ready(function() {
		// Tooltip only Text
		$('.editPharButton').hover(function(){
		        // Hover over code
		        var title = $(this).attr('title');
		        $(this).data('tipText', title).removeAttr('title');
		        $('<p class="tooltip"></p>')
		        .text(title)
		        .appendTo('body')
		        .fadeIn('slow');
		}, function() {
		        // Hover out code
		        $(this).attr('title', $(this).data('tipText'));
		        $('.tooltip').remove();
		}).mousemove(function(e) {
		        var mousex = e.pageX + 20; //Get X coordinates
		        var mousey = e.pageY + 10; //Get Y coordinates
		        $('.tooltip')
		        .css({ top: mousey, left: mousex })
		});
		$('.editRepButton').hover(function(){
	        // Hover over code
	        var title = $(this).attr('title');
	        $(this).data('tipText', title).removeAttr('title');
	        $('<p class="tooltip"></p>')
	        .text(title)
	        .appendTo('body')
	        .fadeIn('slow');
		}, function() {
		        // Hover out code
		        $(this).attr('title', $(this).data('tipText'));
		        $('.tooltip').remove();
		}).mousemove(function(e) {
		        var mousex = e.pageX + 20; //Get X coordinates
		        var mousey = e.pageY + 10; //Get Y coordinates
		        $('.tooltip')
		        .css({ top: mousey, left: mousex })
		});
	});
</script>
<script type="text/javascript">
$(document).ready(function() {
	$(".p_map").show();
	$(".map").css("text-decoration", "underline");
	$(".p_video").hide();
	$(".p_anh").hide();
	$(".video").click(function() {
		$(".p_video").show();
		$(".p_anh").hide();
		$(".p_map").hide();
		$(".video").css("text-decoration", "underline");
		$(".anh").css("text-decoration", "none");
		$(".map").css("text-decoration", "none");
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
});

</script>
<script type="text/javascript">
$(document).ready(function() {
	$(".advanced").hide();
	  $("#tab1").click(function(){
		    $(".advanced").hide();
		    $(".not_advanced").show();
		  });
	  
	  $("#tab2").click(function(){
		    $(".advanced").show();
		    $(".not_advanced").hide();
		  });
});
</script>
<script type="text/javascript">
	function onclickTab1() {
		document.myForm.action = "pharmacyDetail";
		document.forms["myForm"].submit();
	}
	function onclickTab2() {
		document.paginationForm.action = "danh-muc-thuoc-action";
	  	document.paginationForm.typeOfPackage.value = "-1";
		document.forms["paginationForm"].submit();
	}
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
	$(document).ready(function() {
		/* $("#completeButton").mouseover(function () {
			codeAddress();
		});
		$("#street").keyup(function () {
			codeAddress();
		});
		$("#houseNo").keyup(function () {
			codeAddress();
		}); */
		if($("#medChoosen").val() == 'true') {
			/*$(".itemsPhar").append("Nhà thuốc");*/
			$(".itemsMed").append("Thuốc");
			$(".of").append("của");
			$(".show").append("Hiển thị");
			$(".advanced").show();
		    $(".not_advanced").hide();
		    makeactive(2);
		} else {
			$(".advanced").hide();
		    $(".not_advanced").show();
		    initialize();
		    makeactive(1);
		}
		  
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
	var northLatBound;
	var northLongBound;
	var sounthLatBound;
	var sounthLongBound;
	var minZoomLevel = 13;
	var strictBounds;
	function initialize() {
			var lon = document.getElementById("longtitude").value;
			var lat = document.getElementById("latitude").value;
			var districtStr = document.getElementById("districtStr").value;
			northLatBound = districtStr.split("~")[4];
			northLongBound = districtStr.split("~")[5];
			sounthLatBound = districtStr.split("~")[6];
			sounthLongBound = districtStr.split("~")[7];
			changeInfo = document.getElementById("changeInfo").value;
			if(lon && lat) {
				latlng = new google.maps.LatLng(lat, lon);
				myCenter = new google.maps.LatLng(lat, lon);
			}
			geocoder = new google.maps.Geocoder();
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
			strictBounds = new google.maps.LatLngBounds(
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

			     map.setCenter(new google.maps.LatLng(lat, lon));
			   }); 
			   google.maps.event.addListener(map, 'zoom_changed', function() {
		    	     if (map.getZoom() < minZoomLevel) map.setZoom(minZoomLevel);
		    	   });
			if(changeInfo == 'true') {
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
		document.getElementById('latitude').value = '0.0';
		document.getElementById('longtitude').value = '0.0';
		if (!select1Value || select1Value == -1) {
			hasbound = false;
		}
		if (!select2Value || select2Value == -1) {
			document.myForm.street.disabled = true;
			document.myForm.houseNo.disabled = true;
			hasbound = false;
		}
		if (select2Value && select2Value != -1) {
			hasbound = true;
			lat = select2Value.split('~')[1];
			lng = select2Value.split('~')[2];
			document.getElementById("cityId").value = select1Value.split('~')[0];
			document.getElementById("districtId").value = select2Value
					.split('~')[0];
			document.myForm.street.disabled = false;
			document.myForm.houseNo.disabled = false;
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
		myCenter = new google.maps.LatLng(parseFloat(lat), parseFloat(lng));
		map.setOptions({
			center : myCenter,
			zoom : 15
		});
		if(hasbound) {
			strictBounds = new google.maps.LatLngBounds(
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
	function submitAction() {
		document.paginationForm.action = "danh-muc-thuoc-action";
		document.paginationForm.typeOfPackage.value = "0";
		document.forms["paginationForm"].submit();
	}
	function pharmacyDetail() {
		document.myForm.action = "pharmacyDetail";
		document.forms["myForm"].submit();
	}
	function medDetailInfor(medId) {
		document.paginationForm.action = "thong-tin-thuoc";
		//document.paginationForm.medIdDetail.value = medId;
		document.paginationForm.medId.value = medId;
		document.paginationForm.page.value = "pharmacy_registerNewMedicine.jsp";
		document.forms["paginationForm"].submit();
	}
	function deleteMed(medId) {
		var result = confirm("Bạn có muốn xóa thuốc này khỏi danh mục?");
		if(result == true) {
			document.paginationForm.action = "xoa-khoi-danh-muc";
			document.paginationForm.medIdDetail.value = medId;
			document.paginationForm.medId.value = medId;
			document.forms["paginationForm"].submit();
		} else {
			return false;
		}
	}
	function changePharInfor() {
		document.myForm.action = "chinh-sua-thong-tin-hieu-thuoc-action";
		document.forms["myForm"].submit();
	}
	function addMedToPhar() {
		document.paginationForm.action = "them-thuoc-vao-nha-thuoc";
		document.forms["paginationForm"].submit();
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
<title>Thông tin hiệu thuốc</title>
</head>
<sj:head />
<body>
	<div id="language">
		<div id="notice">
			<span>Xin chào </span> <s:property
					value="#session['userName']" /> | <a href="logout">Đăng xuất</a> |
			<a href="ChangePassword">Đổi mật khẩu</a>
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
				<li><a href="search_pharmacyInfo">Tìm nhà thuốc</a></li>
				<li><a href="search_nearestPharmacy">Nhà thuốc gần nhất</a></li>
				<li><a href="register">Đăng ký</a></li>
				<li><a href="aboutUs">Giới thiệu</a></li>
				<li><a href="news">Tin tức</a></li>
				<li><a href="RepresentativeInfor" id="current">Hiệu thuốc
						của bạn</a>
			</ul>
		</div>
	</div>
	<div id="container">
		<div id="page_title">
			<ul>
				<li><a href="RepresentativeInfor" id="current">
						Hiệu thuốc của bạn</a></li>
				<li><img src="css/images/arrow.png" /></li>
				<li><a href="javascript:pharmacyDetail();" id="current">
						<s:property value="pharmacyName"/> </a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div>
		<!-- <div id=register> -->
			<ul id="tabmenu">
				<li onclick="makeactive(1)"><div class="tab active" id="tab1" onclick="onclickTab1();">
						Thông tin hiệu thuốc
					</div></li>
				<li onclick="makeactive(2)"><div class="tab" id="tab2" onclick="onclickTab2();">Danh
						mục thuốc</div></li>
			</ul>
			<div id="content">
				<s:hidden name="medChoosen" id="medChoosen" />
				<div>
					<div id="rootElement" class="not_advanced"
						style="border: none; width: 97%;">
						<s:form theme="simple" acceptcharset="utf-8"
							action="chinh-sua-thong-tin-hieu-thuoc" method="post" id="myForm"
							enctype="multipart/form-data">
							<s:url var="remoteurl" action="relatedSelectAction">
							</s:url>
							<s:url var="typeOfBusinessUrl" value="typeOfBusinessAction" />
							<s:url var="workingUrl" action="workingHouse" />
							<s:hidden name="page" value="/pharmacy_registerNewMedicine.jsp" />
							<s:hidden name="userGroup" id="userGroup" />
							<s:hidden name="hasvideo" id="hasvideo" />
							<s:hidden name="changeInfo" id="changeInfo" />
							<s:hidden name="pharmacyId" id="pharmacyId" />
							<s:hidden name="pharmacyName" id="pharmacyName"
								value="%{pharmacy.pharmacyName}" />
							<s:hidden name="smdEmail" value="%{#session['smdEmail']}" />
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
							<s:if test="medChoosen == false">
								<s:if test="!changeInfo">
									<div id="topBig">
										<li class="headerBig"><s:property
												value="%{pharmacy.pharmacyName}" /> <a
											href="javascript:changePharInfor();" class="editPharButton"
											title="Chỉnh sửa thông tin hiệu thuốc">Chỉnh sửa</a></li>
									</div>
									<hr class="line"/>
									<br/>
									<div id="midLeftPopup">
										<div id="top">
											<li class="header ttcb">Thông tin người đại diện</li>
										</div>
										<ul>
											<li class="detail house"><s:label value="Tên:" id="hder" />
												<s:property value="%{rep.name}" /></li>
											<li class="detail house"><s:label value="Email:"
													id="hder" /> <s:property value="%{rep.email}" /></li>
											<li class="detail house"><s:label value="Điện thoại:"
													id="hder" /> <s:property value="%{rep.phone}" /></li>
											<li class="detail house"><s:label value="Trình độ:"
													id="hder" /> <s:property value="%{rep.degreeName}" /></li>
											<li class="detail house"><s:label value="Giấy phép hành nghề:" id="hder" /> <s:property value="%{rep.licensureNo}" /></li>
										</ul>
										<div></div>
										<br /> <br />
										<div id="top">
											<li class="header ttcb">Thông tin nhà thuốc</li>
										</div>
										<div class="clear"></div>
										<div id="component_left">
											<ul>
												<li class="detail house"><b>Tên:</b> <s:property
														value="%{pharmacy.pharmacyName}" /></li>
												<li class="detail house"><b>Loại hình:</b> <s:property
														value="%{pharmacy.typeOfBusinessName}" /></li>
												<li class="detail house"><b>Địa chỉ:</b> <s:property
														value="%{pharmacy.address}" /></li>
												<li class="detail house"><b>Số điện thoại:</b><s:property value="%{pharmacy.telAreaCode}"/><s:property
														value="%{pharmacy.homePhone}" /></li>
												<li class="detail house"><b>Giấy phép kinh doanh
														số:</b> <s:property value="%{pharmacy.businessLicenseNo}" /></li>
												<li class="detail house"><b>Trực thuộc công ty:</b> <s:property
														value="%{pharmacy.pharCompany}" /></li>
												<li class="detail house"><b>GPP số:</b> <s:property
														value="%{pharmacy.gppNo}" /></li>
												<li class="detail house"><b>Bằng khen, chứng nhận:</b>
													<s:property value="%{pharmacy.notes}" /></li>
											</ul>
										</div>
									</div>
									<div id="midRightPopup">
										<div id="top">
											<li class="header map ">Bản đồ</li>
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
												<object id="videoPath">
													<video id="videoContainer" width="400px" height="400px" controls> <source
														src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
														type="video/mp4"> <source
														src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
														type="video/ogg"> <source
														src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
														type="video/webm">
													</video>
													<%-- <embed src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />" type="application/x-shockwave-flash"> --%>
												</object>
											</s:if>
											<s:else>
												<img
													src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
													style="width: 100%; height: 400px;" />
											</s:else>
										</div>
										<div id="googleMap" style="width: 100%; height: 400px;"
											class="p_map" />
									</div>
								</s:if>
								<s:else>
									<div id="register_head">Thông Tin Hiệu Thuốc</div>
									<%-- <s:if test="true">
										<div class="notice searchInvalid">
											<s:fielderror fieldName="latLngError" />
										</div>
									</s:if> --%>
									<div id="nodeElement1">
										<div id="nodeElement21">
											<div id="register_title" class="required header_small1">Tên</div>
											<div class="fieldgroup">
												<s:textfield id="nameOfPharmacy" name="nameOfPharmacy"
													cssErrorClass="errorStyle" cssClass="textbox short"
													value="%{pharmacy.pharmacyName}" maxlength="200" />
											</div>
										</div>
										<div id="nodeElement21">
											<div id="register_title" class="required header_small1">Giấy
												phép kinh doanh số</div>
											<div class="fieldgroup">
												<s:textfield id="businesslicenseNo" name="businesslicenseNo"
													cssErrorClass="errorStyle" cssClass="textbox short"
													value="%{pharmacy.businessLicenseNo}" maxlength="100" />
											</div>
										</div>
										<div id="nodeElement21">
											<div id="register_title" class="header_small1">Trực
												thuộc công ty</div>
											<s:textfield id="pharCompany" name="pharCompany"
												cssErrorClass="errorStyle" cssClass="textbox short"
												value="%{pharmacy.pharCompany}" maxlength="200"/>
										</div>
									</div>
									<div id="nodeElement1">
										<div id="nodeElement21">
											<div id="register_title" class="required header_small1">Số
												điện thoại</div>
											<div class="fieldgroup">
												<span class="spanHomePhone"> <label id="telAreaCode" style="font-size: 14px;"><s:property value="%{pharmacy.telAreaCode}"/> </label>
												</span>
												<s:textfield id="homePhone" name="homePhone"
													cssErrorClass="errorStyle" cssClass="homePhone"
													value="%{pharmacy.homePhone}" maxlength="8"/>
											</div>
										</div>
										<div id="nodeElement21">
											<div id="register_title" class="header_small1">GPP số
												(nếu có)</div>
											<s:textfield id="gppNo" name="gppNo"
												cssErrorClass="errorStyle" cssClass="textbox short"
												value="%{pharmacy.gppNo}" maxlength="45"/>
										</div>
										<div id="nodeElement21">
											<div id="register_title" class="required header_small1">Loại
												hình</div>
											<sj:select href="%{typeOfBusinessUrl}" id="typeOfBusiness"
												name="typeOfBusiness" list="typeOfBusinessList"
												listKey="typeBusinessId" listValue="typeBusinessName"
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
														list="cityList" listKey="idLatLongName"
														listValue="cityName" onChangeTopics="reloadDistrictList"
														headerKey="-1" headerValue="Tỉnh/ Thành Phố"
														onchange="cityChange();" cssClass="select" />
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
														value="%{pharmacy.street}" maxlength="100" />
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
														value="%{pharmacy.houseNo}" maxlength="50" />
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
												<s:textarea rows="5" cols="30" name="notes"
													value="%{pharmacy.notes}" />
											</div>
										</div>
										<div id="nodeElement1">
											<div id="register_title" class="header_small1">Chọn ảnh
												đại diện</div>
										</div>
										<div id="nodeElement1">
											<s:file name="avatarImage" id="avaterImageId"
												accept=".JPG,.PNG,.BMP,.JPEG" value="%{pharmacy.imgPath}" />
											<!--<input id="the_real_file_input" name="foobar" type="file"
									size="100">-->
										</div>
										<div id="nodeElement1">
											<div id="register_title" class="header_small1">Tải
												video quảng cáo</div>
										</div>
										<div id="nodeElement1">
											<s:file name="video" id="videoId" accept=".MP4,.FLV" />
										</div>

									</div>
									<div id="tu">
										<div id="nodeElementRight">
											<div id="nodeElement1">
												<div id="register_title" class="header_small1">Chọn vị
													trí trên bản đồ        <input onclick="deleteOverlays();" type=button value="Xóa đánh dấu"></div>
												<div class="register_explain explain_normal">hệ thống
													sẽ tự động xác định địa chỉ bạn cung cấp hoặc bạn có thể
													chọn một cách chính xác vị trí bạn mong muốn trên bản đồ
													dưới đây:</div>
												<div class="clear"></div>
												<div id="googleMap" style="width: 545px; height: 380px;" />
											</div>
										</div>
									</div>
									<div id="r_footer">
										<s:submit value="Lưu lại" cssClass="r_button space30 smButton"
											action="chinh-sua-thong-tin-hieu-thuoc" id="completeButton" />
										<s:submit value="Hủy bỏ" cssClass="cancel smButton"
											action="huy-bo-chinh-sua-thong-tin-hieu-thuoc" id="r_button" />
									</div>
								</s:else>
							</s:if>
						</s:form>
					</div>
				</div>
				<!-- end tab 1 -->
				<s:form action="danh-muc-thuoc" accept-charset="utf-8" method="post"
					theme="simple" name="paginationForm" cssClass="advanced">
					<s:hidden name="noResult" />
					<s:hidden name="search" />
					<s:hidden name="detail" />
					<s:hidden name="medId" />
					<s:hidden name="smdEmail" value="%{#session['smdEmail']}" />
					<%-- <s:hidden name="medId" /> --%>
					<s:hidden name="medIdDetail" id="medIdDetail" />
					<s:hidden name="success" />
					<s:hidden name="pharmacyId" id="pharmacyId" />
					<s:hidden name="typeOfPackage" id="typeOfPackage" />
					<s:hidden name="pharmacyName" id="pharmacyName" />
					<s:url var="remoteurl" action="selectTagAction" />
					<s:hidden name="page" value="/pharmacy_registerNewMedicine.jsp" />
					<div></div>
					<div></div>
					<s:if test="search == true">
						<s:if test="medicineList.size() >= 0">
							<div id="header_big" style="margin: 30px 0 10px 30px !important;">Danh mục thuốc đang bán 
							<s:a href="javascript:addMedToPhar();"
							 cssClass="editRepButton" title="Thêm thuốc vào danh mục" >Thêm mới</s:a>	
							</div>
							<div id="ds_header">
							<%@ include file="pagination.jsp"%>
							<sj:select href="%{remoteurl}" label="Loại thuốc" id="keyWordMedTypeId"
							name="keyWordMedTypeName" list="medTypes" listKey="medTypeId"
							listValue="medTypeName" headerKey="-1"
							headerValue="---Xin hãy chọn loại thuốc---"
							cssClass="selectbox smiddle danhmucthuoc marginL30" onchange="submitAction();" />
							</div>
							<s:if test="medicineList.size() > 0">
							<table id="visible" style="margin-top:10px;">
								<tr>
									<%-- <th
										<s:checkbox name="checkAll" theme="simple"
											id="checkAll" value="false" />
											class="sortable<s:if test="#attr.pagination.sortColumn.equals('MEDICINE_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
										<a href="#" onclick="fnPagination(6,'MEDICINE_NAME');">Xóa
											khỏi danh mục</a>
									</th> --%>
									<th><a title="Xóa khỏi danh mục">Xóa</a></th>
									<th
										class="sortable<s:if test="#attr.pagination.sortColumn.equals('MEDICINE_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
										<a href="#" onclick="fnPagination(6,'MEDICINE_NAME');">Tên
											thuốc</a>
									</th>
									<th
										class="sortable <s:if test="#attr.pagination.sortColumn.equals('MANUFACTURER')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
										<a href="#" onclick="fnPagination(6,'MANUFACTURER');">Nhà
											Sản Xuất</a>
									</th>
									<th
										class="sortable <s:if test="#attr.pagination.sortColumn.equals('INGREDIENTS')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
										<a href="#" onclick="fnPagination(6,'INGREDIENTS');">Thành phần thuốc</a>
									</th>
									<th
										class="sortable <s:if test="#attr.pagination.sortColumn.equals('TYPE_OF_PACKAGE_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
										<a href="#" onclick="fnPagination(6,'TYPE_OF_PACKAGE_NAME');">Loại
											thuốc</a>
									</th>
								</tr>
								<s:iterator value="#attr.medicineList" var="medicine"
									status="rowstatus">
									<s:if test="#rowstatus.even == true">
										<tr class="ac_odd">
									</s:if>
									<s:else>
										<tr>
									</s:else>
									<td>
										<%-- <s:checkbox name="checkBox" fieldValue="%{medId}"
											theme="simple" id="checkBox[%{medId}]" cssClass="checkBox" /> --%>
										<s:a name="delete"theme="simple"
											onclick="deleteMed(%{medId}); return false;" title="Xóa khỏi danh mục">Xóa</s:a>
									</td>
									<td><s:a href="javascript:medDetailInfor(%{medId});">
											<s:property value="medName" />
										</s:a></td>
									<td class="limited"><s:property value="manufacturer" /></td>
									<td><s:property value="ingredients" /></td>
									<td><s:property value="typeOfPackageName" /></td>
									</tr>
								</s:iterator>
							</table>
							</s:if>
							<s:else>
								<div class="notice searchInvalid">
									<s:label>Chưa có loại thuốc này trong danh mục thuốc đang bán</s:label>
								</div>
							</s:else>
						</s:if>
					</s:if>
					<s:if test="detail == true">
					<div id="header_big" style="margin: 30px 0 10px 30px !important;">Thông tin thuốc chi tiết 
							<s:a href="javascript:addMedToPhar();"
							 cssClass="editRepButton" title="Thêm thuốc vào danh mục" >Thêm mới</s:a>	
							</div>
						<sj:select href="%{remoteurl}" label="Loại thuốc"
							name="keyWordMedTypeName" list="medTypes" listKey="medTypeId"
							listValue="medTypeName" headerKey="-1"
							headerValue="---Xin hãy chọn loại thuốc---"
							cssClass="selectbox smiddle marginL30" onchange="submitAction();" />
							<%-- <s:submit action="them-thuoc-vao-nha-thuoc"
							value="" cssClass="addMedByPhar" title="Thêm thuốc vào danh mục" /> --%>
						<div id="header_big">Kết quả chi tiết</div>
						<div id="dynamic-list" class="bs2">
						<table>
							<tr>
								<td>
									<table id="left-of-dynamic">
										<tr>
											<td class="limited"><a class="header_small2">Tên
													thuốc: </a></td>
											<td><s:property value="%{medDetail.medName}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Nhà sản
													xuất: </a></td>
											<td><s:property value="%{medDetail.manufacturer}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Loại
													thuốc: </a></td>
											<td><s:property value="%{medDetail.typeOfPackageName}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Tên
													thuốc gốc: </a></td>
											<td><s:property value="%{medDetail.genericMedicine}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Tên
													biệt dược: </a></td>
											<td><s:property value="%{medDetail.brandName}" /></td>
										</tr>
									</table>
								</td>
								<td><img
									src="image/medicine/<s:property value="%{medDetail.imgPath}" />"
									style="height: 160px; width: 240px;" /></td>
							</tr>
						</table>
						<hr class="line" />
						<dl class="nav2">
							<dt onclick="makeDynamic(4)">
								<a class="header_small2">Thành phần</a>
								<div id="arrowDown" class="d_dt4" style="display: none;"></div>
								<div id="arrowUp" class="u_dt4" ></div>
							</dt>
							<dd id="dd4">
								<ul>
									<s:iterator value="medDetail.ingredientsList">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(5)">
								<a class="header_small2">Chỉ định/Triệu chứng/Công dụng</a>
								<div id="arrowDown" class="d_dt5"></div>
								<div id="arrowUp" class="u_dt5" style="display: none;"></div>
							</dt>
							<dd id="dd5">
								<ul>
									<s:iterator value="medDetail.indicationList">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(6)">
								<a class="header_small2">Chống chỉ định</a>
								<div id="arrowDown" class="d_dt6"></div>
								<div id="arrowUp" class="u_dt6" style="display: none;"></div>
							</dt>
							<dd id="dd6">
								<ul>
									<s:iterator value="medDetail.contraindicationList">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(7)">
								<a class="header_small2">Cách sử dụng</a>
								<div id="arrowDown" class="d_dt7"></div>
								<div id="arrowUp" class="u_dt7" style="display: none;"></div>
							</dt>
							<dd id="dd7">
								<ul>
									<s:iterator value="medDetail.dosingAndUseList">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(10)">
								<a class="header_small2">Cảnh báo</a>
								<div id="arrowDown" class="d_dt10"></div>
								<div id="arrowUp" class="u_dt10" style="display: none;"></div>
							</dt>
							<dd id="dd10">
								<ul>
									<s:iterator value="medDetail.warningList">
											<s:property />
											<br/>
										</s:iterator>
									<%-- <li><s:property value="%{medDetail.warning}" /></li> --%>

								</ul>
							</dd>
							<dt onclick="makeDynamic(11)">
								<a class="header_small2">Bảo quản</a>
								<div id="arrowDown" class="d_dt11"></div>
								<div id="arrowUp" class="u_dt11" style="display: none;"></div>
							</dt>
							<dd id="dd11">
								<ul>
									<s:iterator value="medDetail.storageList">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(12)">
								<a class="header_small2">Tương tác thuốc</a>
								<div id="arrowDown" class="d_dt12"></div>
								<div id="arrowUp" class="u_dt12" style="display: none;"></div>
							</dt>
							<dd id="dd12">
								<ul>
									<s:iterator value="medDetail.interactionList">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(13)">
								<a class="header_small2">Thuốc tương tự</a>
								<div id="arrowDown" class="d_dt13"></div>
								<div id="arrowUp" class="u_dt13" style="display: none;"></div>
							</dt>
							<dd id="dd13">
								<ul>
									<s:iterator value="medDetail.similarMedicineList">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(14)">
								<a class="header_small2">Nhà thuốc đang bán</a>
								<div id="arrowDown" class="d_dt14"></div>
								<div id="arrowUp" class="u_dt14" style="display: none;"></div>
							</dt>
							<dd id="dd14">
								<ul>
									<s:if test="medDetail.pharmacyList.size() > 0">
										<s:iterator value="medDetail.pharmacyList">
													<s:property value="pharmacyName" />
													<br/>
										</s:iterator>
									</s:if>
									<s:else>
										Chưa có thông tin
									</s:else>
								</ul>
							</dd>
						</dl>
						<s:submit name="delete" value="Xóa khỏi danh mục" theme="simple"
							cssClass="smButton redButton" action="xoa-khoi-danh-muc" />
					</div>
						<%-- <s:submit style="margin-left: 145px;"
							action="them-moi-thuoc-vao-danh-muc"
							value="Thêm mới thuốc vào danh mục"
							cssClass="smButton greenButton" /> --%>
					</s:if>
				</s:form>
			</div>
			<script type="text/javascript">
				function makeDynamic(number) {
					$("#dd" + number).slideToggle("slow");
					$(".u_dt" + number).toggle("slow");
					$(".d_dt" + number).toggle("slow");
				}
			</script>
			<script language="JavaScript" type="text/javascript">
				function makeactive(tab) {
					document.getElementById("tab1").className = "tab";
					document.getElementById("tab2").className = "tab";
					document.getElementById("tab" + tab).className = "active";
				}
			</script>
	</div>
</body>
</html>
