
<%-- 
    Document   : search_Medicines
    Created on : Apr 27, 2013, 10:59:49 PM
    Author     : W7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8; no-cache">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="stylesheet" type="text/css"
	href="css/AdministrationPharmacy.css">
<link rel="stylesheet" type="text/css" href="css/popup.css">
<link rel="stylesheet" type="text/css" href="css/tab-menu.css">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/changes-tab.js"></script>
<script type="text/javascript" src="javascript/login.js"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/pagination.js"></script>
<%-- <script type="text/javascript" src="javascript/paging-pharmacy.js"></script> --%>
<script type="text/javascript" src="javascript/dynamic-list.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?key=AIzaSyCt_RpfrVUrVs74uD1VwcXgR3xK64S56DM&sensor=false"></script>
<script type="text/javascript">
	var userGroup1;
	var userGroup2;
	$(document)
			.ready(
					function() {
						$(".itemsPhar").append("Nhà Thuốc");
						$(".itemsMed").append("Nhà Thuốc");
						$(".of").append("của");
						$(".show").append("Hiển thị");
						userGroup1 = "<s:property value="#session['userGroup1'].email"/>";
						userGroup2 = "<s:property value="#session['userGroup2'].email"/>";
						if (!userGroup1 && !userGroup2) {
							document.getElementById("language")
									.removeAttribute("style");
							document.getElementById("language").style.display = "none";
							$("#ulMenu").append(
									'<li><a href="Login">Đăng nhập</a></li>');
						} else {
							document.getElementById("language")
									.removeAttribute("style");
							document.getElementById("language").style.visibility = "visible";
							if (userGroup1) {
								$("#ulMenu")
								.append('<li><a href="news" >Tin tức </a></li>'
										+ '<li><a href="admin_medicineManagement">Quản lý thuốc</a>'
												+ '<ul>'
												+ '<li><a href="admin_registerNewMedicine"> Thêm mới'
												+ ' thuốc</a></li>'
												+ '<li><a href="admin_medicineManagement"> Tìm kiếm'
												+ ' thuốc</a></li>'
												+ '<li><a href="admin_acceptMedicine"> Kiểm duyệt'
												+ ' thuốc</a></li>'
												+ '</ul></li>'
												+ '<li><a href="admin_pharmacyManagement" id="current"> Quản lý nhà '
												+ 'thuốc</a></li>'
												+ '<li><a href="admin_news"> Quản lý tin '
												+ 'tức</a>'
												+ '<ul>'
												+ '<li><a href="admin_registerNews"> Thêm mới'
												+ ' tin tức</a></li>'
												+ '<li><a href="admin_news"> Tìm kiếm'
												+ ' tin tức</a></li>'
												+ '</ul></li>');
							} else {
								$("#ulMenu")
										.append(
												'<li><a href="RepresentativeInfor">Nhà thuốc của bạn</a></li>');
							}
						}
					});
</script>
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
											$("#videoPath").remove();
											if($("#hasvideo").val() == 'true') {
											$(".p_video")
													.append(
															'<object id="videoPath"> <video width="400px"' 
				+ 'height="400px" controls> <source src="Pharmacy_Medias/<s:property value="%{pharDetailInfor.videoPath}" />" type="video/mp4"><source src="Pharmacy_Medias/<s:property value="%{pharDetailInfor.videoPath}" />" type="video/ogg"> <source src="Pharmacy_Medias/<s:property value="%{pharDetailInfor.videoPath}" />" type="video/webm"></video> </object>');
											}
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
							$("#videoPath").remove();
							$(".p_anh").show();
							$(".p_map").hide();
							$(".p_video").hide();
							$(".anh").css("text-decoration", "underline");
							$(".video").css("text-decoration", "none");
							$(".map").css("text-decoration", "none");
						});
						$(".map").click(function() {
							$("#videoPath").remove();
							$(".p_map").show();
							$(".p_anh").hide();
							$(".p_video").hide();
							$(".map").css("text-decoration", "underline");
							$(".anh").css("text-decoration", "none");
							$(".video").css("text-decoration", "none");
						});
						$("#direct").click(function() {
							$("#videoPath").remove();
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
	function submitAction(pharmacyId) {
		document.paginationForm.action = "thong-tin-hieu-thuoc";
		document.paginationForm.pharmacyId.value = pharmacyId;
		document.forms["paginationForm"].submit();
	}
	function deletePhar(pharId) {
		var result = confirm("Bạn có muốn xóa nhà thuốc này không?");
		if(result == true) {
			document.paginationForm.action = "xoa-nha-thuoc";
			document.forms["paginationForm"].submit();
		} else {
			return false;
		}
	}
	function restorePhar(pharId) {
		document.paginationForm.action = "khoi-phuc-nha-thuoc";
		document.paginationForm.pharIdDetail.value = pharId;
		document.forms["paginationForm"].submit();
	}
	function loadDistrict() {
		var select1 = document.getElementById("selectBoxAjax1");
		var option1 = select1.options[select1.selectedIndex].value;
		$('#selectBoxAjax2')[0].options.length = 0;
		$("#selectBoxAjax2").append(
				'<option value="-1">' + 'Quận/ Huyện <\/option>');
		if (option1 != -1) {
			$.ajax({
				type : 'post',
				url : "selectBoxAjax.action?selectBoxAjax1="
						+ $("#selectBoxAjax1").val(),
				dataType : "json",
				data : "",
				success : function(data) {
					$(data.selectDistrictStr).each(
							function(index, el) {
								var split = el.split("~");
								$("#selectBoxAjax2").append(
										'<option value=' + split[1] + '>'
												+ split[0] + '<\/option>');
							});
				},
			});
		}
	}
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				var cityStr = $("#cityStr").val();
				var districtStr = $("#districtStr").val();
				var typeOfBusinessStr = $("#typeOfBusinessStr").val();
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
			});
</script>
<script type="text/javascript">
	var myCenter = new google.maps.LatLng(21.033333, 105.850000);
	var marker;
	var geocoder;
	var map;
	var mapProp;
	function initialize() {
		var lon = document.getElementById("longtitude").value;
		var lat = document.getElementById("latitude").value;
		if (lon && lat) {
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
			position : latlng,
			map : map
		});
		map.streetViewControl = false;
	}
</script>
<script type="text/javascript">
	function detailPharInfo(pharId) {
		document.paginationForm.action = "quan-ly-thong-tin-nha-thuoc-chi-tiet";
		document.paginationForm.pharIdDetail.value = pharId;
		document.forms["paginationForm"].submit();
	}
	function loadDistrict() {
		var select1 = document.getElementById("selectBoxAjax1");
		var option1 = select1.options[select1.selectedIndex].value;
		$('#selectBoxAjax2')[0].options.length = 0;
		$("#selectBoxAjax2").append(
				'<option value="-1">' + 'Quận/ Huyện <\/option>');
		if (option1 != -1) {
			$.ajax({
				type : 'post',
				url : "selectBoxAjax.action?selectBoxAjax1="
						+ $("#selectBoxAjax1").val(),
				dataType : "json",
				data : "",
				success : function(data) {
					$(data.selectDistrictStr).each(
							function(index, el) {
								var split = el.split("~");
								/* if(split[1] == 2) {
									$("#selectBoxAjax2").append('<option selected="selected" value=' + split[1] + '>' + split[0] + '<\/option>');
								} else { */
								$("#selectBoxAjax2").append(
										'<option value=' + split[1] + '>'
												+ split[0] + '<\/option>');
								/* } */
							});
				},
			});
		}
	}
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				var pharIdList = document.getElementById("pharIdList").value;
				var pharIdSplit = pharIdList.split("~");
				$("#checkAll").click(
						function() {
							var check = document.getElementById("checkAll");
							//var totalRecord = document.getElementById("paginationRecords").value;
							for ( var i = 0; i < pharIdSplit.length; i++) {
								var checkEachRow = document
										.getElementById("checkBox["
												+ pharIdSplit[i] + "]");
								if (check.checked == true) {
									checkEachRow.checked = true;
								} else {
									checkEachRow.checked = false;
								}
								//alert(checkEachRow.checked);
							}
						});
				$(".checkBox").click(
						function() {
							var check = document.getElementById("checkAll");
							for ( var i = 0; i < pharIdSplit.length; i++) {
								var checkEachRow = document
										.getElementById("checkBox["
												+ pharIdSplit[i] + "]");
								if (checkEachRow.checked == false) {
									check.checked = false;
								}
							}
						});
			});
</script>
<style>
#content{
	width:93%;
}

.smButton{
	margin:20px 18px 0 18px;
}
</style>
<title>Pharmacy Management</title>
</head>
<sj:head />
<body onload="initialize();">
	<div id="language">
		<div id="notice">
			<span>Xin chào </span> <s:property
					value="#session['userName']" /> | <a
				href="logout">Đăng xuất</a> | <a href="ChangePassword">Đổi mật khẩu</a>
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
				<li><a href="search_nearestPharmacy">Nhà thuốc gần nhất</a></li>
			</ul>
		</div>
	</div>
	<div id="container">
		<!-- <div id="page_title">
			<ul>
				<li><a href="admin_pharmacyManagement.jsp" id="current">Quản
						lý hiệu thuốc</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div> -->
		<div id="searchMedicines">
			<s:hidden name="count" />
			<s:hidden name="searched" />
			<s:hidden name="detailed" />
			<s:hidden name="checkaccept" />
			<s:hidden name="cityStr" id="cityStr" />
			<s:hidden name="hasvideo" id="hasvideo" />
			<s:hidden name="districtStr" />
			<s:hidden name="longtitude" id="longtitude" />
			<s:hidden name="latitude" id="latitude" />
			<s:hidden name="typeOfBusinessStr" />
			<s:hidden name="deleteBoolean" id="deleteBoolean" />
			<s:url var="remoteurl" action="relatedSelectAction" />
			<s:hidden name="failedAction" id="failedAction" />
			<s:hidden name="successedAction" id="successedAction" />
			<s:url var="degreeUrl" action="degreeAction" />
			<!-- <div id="header_big">Tiêu chí tìm kiếm</div> -->
			<s:form action="tra-cuu-nha-thuoc" name="paginationForm"
				accept-charset="utf-8" theme="simple" id="registerForm"
				method="post">
				<s:hidden name="smdEmail" value="%{#session['smdEmail']}" />
				<table id="invisible">
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Email:</s:div></td>
						<td><s:textfield name="email" id="email"
								cssClass="textbox middle" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Tỉnh/ Thành phố:</s:div></td>
						<td><select id="selectBoxAjax1" name="selectBoxAjax1"
							onchange="loadDistrict();" class="selectbox smiddle">
								<option value="-1">Tỉnh/ Thành phố</option>
						</select></td>
					</tr>
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Tên nhà thuốc:</s:div></td>
						<td><s:textfield name="pharmacyName" id="pharmacyName"
								cssClass="textbox middle" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Quận/ Huyện:</s:div></td>
						<td><select id="selectBoxAjax2" name="selectBoxAjax2"
							class="selectbox smiddle">
								<option value="-1">Quận/ Huyện</option>
						</select></td>
					</tr>
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Trình độ dược sĩ:</s:div></td>
						<td>
						<sj:select href="%{degreeUrl}" id="degree" name="degree"
								list="degreeList" listKey="degreeId" listValue="degreeName"
								cssClass="selectbox smiddle" headerValue="Tất cả" headerKey="0"/> <%-- <select id="degree" name="degree"
							class="selectbox smiddle">
								<option value="0">Dược sĩ trung cấp</option>
						</select> --%>
						</td>
						<td><s:div id="s_text" cssClass="header_small2">Loại hình:</s:div></td>
						<td><select id="typeOfBusiness" name="typeOfBusiness"
							class="selectbox smiddle">
								<option value="0">Tất cả</option>
						</select></td>
					</tr>
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Kiểm Duyệt:</s:div></td>
						<td><s:checkbox name="checkaccept" theme="simple" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Đánh dấu xóa:</s:div></td>
						<td><s:checkbox name="deleteFlag" theme="simple" /></td>
					</tr>
					<tr>
						<td></td>
						<td><s:submit name="search" value="Tìm Kiếm"
								cssClass="smButton searchButton"
								action="adSearchPharAdvancedAction" /></td>
						<td></td>
						<td></td>

					</tr>
				</table>
				<s:if test="successedAction == true">
					<div class="notice searchValid">
						<s:property value="message" />
					</div>
				</s:if>
				<s:if test="failedAction == true">
					<div class="notice searchInvalid">
						<s:property value="message" />
					</div>
				</s:if>
				<s:hidden name="pharIdDetail" />
				<s:hidden name="pharIdList" id="pharIdList" />
				<s:if test="searched == true">
					<s:if test="pharList.size() > 0">
						<div id="header_big">Kết quả tìm kiếm</div>
						<%-- <div class="error">
							<s:fielderror fieldName="nullCheckBox" theme="simple" />
						</div> --%>
						<%@ include file="pagination.jsp"%>
						<table id="visible">
							<tr>
								<s:if test="deleteBoolean == true">
									<th><a> Hành động</a></th>
								</s:if>
								<s:else>
								<th id="col-check-box"><s:checkbox name="checkAll" theme="simple"
										id="checkAll" /></th>
								</s:else>
								<th
									class="sortable<s:if test="#attr.pagination.sortColumn.equals('PHARMACY_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'PHARMACY_NAME');">Tên
										nhà thuốc</a>
								</th>
								<th
									class="sortable<s:if test="#attr.pagination.sortColumn.equals('CITY_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'CITY_NAME');">Tỉnh/Thành
										phố</a>
								</th>
								<th
									class="sortable<s:if test="#attr.pagination.sortColumn.equals('DISTRICT_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'DISTRICT_NAME');">Quận/Huyện</a>
								</th>
								<th
									class="sortable<s:if test="#attr.pagination.sortColumn.equals('REG_USER')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'REG_USER');">Email
										người đại diện</a>
								</th>
								<th><a>Trạng thái</a></th>	
							</tr>
							<s:iterator value="pharList">
								<tr>
									<s:if test="deleteBoolean == true">
										<td><s:a
												href="javascript:restorePhar(%{pharmacyId})">Khôi phục</s:a></td>
									</s:if>
									<s:else>
										<td id="col-check-box" class="checkBox"><s:checkbox name="checkBox"
												fieldValue="%{pharmacyId}" theme="simple"
												id="checkBox[%{pharmacyId}]" /></td>
									</s:else>
									<td><s:if test="deleteBoolean == false">
											<s:a href="javascript:detailPharInfo(%{pharmacyId});">
												<s:property value="pharmacyName" />
											</s:a>
										</s:if> <s:else>
											<s:property value="pharmacyName" />
										</s:else></td>
									<td><s:property value="cityName" /></td>
									<td><s:property value="districtName" /></td>
									<td><s:property value="regUser" /></td>
									<td><s:if test="acceptFlag">
									Đã Kiểm Duyệt
								</s:if> <s:else>
									Chưa Kiểm Duyệt
								</s:else></td>
								</tr>
							</s:iterator>
						</table>
						<s:if test="deleteBoolean == false">
							<s:if test="checkaccept">
								<s:submit name="Không chấp nhận" value="Hủy Kiểm Duyệt"
									theme="simple"
									cssClass="smButton searchButton redButton marginL20"
									action="huy-kiem-duyet-nha-thuoc" />
							</s:if>
							<s:else>
								<s:submit name="Chấp nhận" value="Kiểm Duyệt" theme="simple"
									cssClass="smButton searchButton marginL20 greenButton"
									cssStyle="text-align:center; vertical-align:middle;"
									action="kiem-duyet-nha-thuoc" />
							</s:else>
						</s:if>

					</s:if>
				</s:if>
				<s:if test="detailed == true">
					<div id=register>
					<div id="ad-header"></div>
					<span id="admin-header-big">Thông tin chi tiết</span>
						<div id="content">
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>	
							<div id="midLeftPopup">
								<div id="top">
										<li class="header ttcb">Thông tin người đại diện</li>
								</div>
								<ul>
									<li class="detail house"><s:label value="Tên:" id="hder" />
										<s:property value="%{repDetailInfor.name}" /></li>
									<li class="detail house"><s:label value="Email:" id="hder" />
										<s:property value="%{repDetailInfor.email}" /></li>
									<li class="detail house"><s:label value="Điện thoại:"
											id="hder" /> <s:property value="%{repDetailInfor.phone}" /></li>
									<li class="detail house"><s:label value="Trình độ:"
											id="hder" /> <s:property value="%{repDetailInfor.degreeName}" /></li>
									<li class="detail house"><s:label
											value="Giấy phép hành nghề:" id="hder" /> <s:property
											value="%{repDetailInfor.licensureNo}" /></li>
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
												value="%{pharDetailInfor.pharmacyName}" /></li>
										<li class="detail house"><b>Loại hình:</b> <s:property
												value="%{pharDetailInfor.typeOfBusinessName}" /></li>
										<li class="detail house"><b>Địa chỉ:</b> <s:property
												value="%{pharDetailInfor.address}" /></li>
										<li class="detail house"><b>Số điện thoại:</b><s:property value="%{pharDetailInfor.telAreaCode}"/><s:property
												value="%{pharDetailInfor.homePhone}" /></li>
										<li class="detail house"><b>Giấy phép kinh doanh số:</b>
											<s:property value="%{pharDetailInfor.businessLicenseNo}" /></li>
										<li class="detail house"><b>Trực thuộc công ty:</b> <s:property
												value="%{pharDetailInfor.pharCompany}" /></li>
										<li class="detail house"><b>GPP số:</b> <s:property
												value="%{pharDetailInfor.gppNo}" /></li>
										<li class="detail house"><b>Bằng khen, chứng nhận:</b> <s:property
												value="%{pharDetailInfor.notes}" /></li>
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
										src="Pharmacy_Images/<s:property value='%{pharDetailInfor.imgPath}' />"
										style="width: 100%; height: 400px;" />
								</div>
								<div id="nodeElement1" class="p_video">
									<s:if test="hasvideo == true">
										<object id="videoPath">
											<video width="400px" height="400px" controls> <source
												src="Pharmacy_Medias/<s:property value="%{pharDetailInfor.videoPath}" />"
												type="video/mp4"> <source
												src="Pharmacy_Medias/<s:property value="%{pharDetailInfor.videoPath}" />"
												type="video/ogg"> <source
												src="Pharmacy_Medias/<s:property value="%{pharDetailInfor.videoPath}" />"
												type="video/webm"></video>
										</object>
									</s:if>
									<s:else>
										<img
											src="Pharmacy_Medias/<s:property value="%{pharDetailInfor.videoPath}" />"
											style="width: 100%; height: 400px;" />
									</s:else>
								</div>
								<div id="googleMap" style="width: 100%; height: 400px;"
									class="p_map" />
							</div>
							
							<div id="admin-phar-but">
								<s:if test="checkaccept">
											<s:submit name="Không chấp nhận" value="Hủy Kiểm Duyệt"
												theme="simple" cssClass="smButton searchButton redButton"
												cssStyle="text-align:center; vertical-align:middle;" action="huy-kiem-duyet-nha-thuoc" />
										</s:if>
										<s:else>
											<s:submit name="Chấp nhận" value="Kiểm Duyệt" theme="simple"
												cssClass="smButton searchButton greenButton"
												cssStyle="text-align:center; vertical-align:middle;"
												action="kiem-duyet-nha-thuoc" />
										</s:else>
										<s:submit name="Quay lại" theme="simple" value="Quay lại"
											cssClass="smButton searchButton"
											cssStyle="text-align:center; vertical-align:middle;"
											action="adSearchPharAdvancedAction" />
										<s:submit name="delete" theme="simple" value="Xóa"
											cssClass="smButton searchButton redButton"
											cssStyle="text-align:center; vertical-align:middle;"
											onclick="deletePhar(); return false;" title="Xóa hiệu thuốc" />
							</div>
						</div>
					</div>
				</s:if>
			</s:form>
		</div>
	</div>
	<div id="delete-box" class="login-popup">
		<input type="button" class="close" value="X" id="close" />
		<div id="login_header">Are you sure to delete [....]?</div>
		<div id="login_content">

			<form method="post" class="signin" action="admin_pharmacy.jsp">
				<input class="button close" type="button" value="Delete" /> <input
					class="button close" type="button" value="Cancel" />

			</form>
		</div>
	</div>
	<div id="info-box" class="login-popup">
		<input type="button" class="close" value="X" id="close" />
		<div id="login_header">[....] Infomation</div>
		<div id="login_content">

			<form method="post" class="signin" action="admin_pharmacy.jsp">
				<input class="button close" type="button" value="Ok" />

			</form>
		</div>
	</div>
</body>
</html>
<script>
	$(document).ready(function() {
		$("#0").hide();
		$("#1").show();
		$(".searchButton").click(function() {

			var items = $("input.checkbox");
			for (i = 0; i < items.length; i++)
				items[i].disabled = true;
		});
	});

	
</script>