<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8; no-cache">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/pagination.js"></script>
<script type="text/javascript" src="javascript/NextPagination.js"></script>
<%-- <script src="javascript/autoComplete_Med.js"></script> --%>
<script src="javascript/js-image-slider.js" type="text/javascript"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/dynamic-list.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<script type="text/javascript" src="javascript/changes-tab.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel="stylesheet" type="text/css" href="css/tab-menu.css">
<link rel="stylesheet" type="text/css"
	href="css/AdministrationPharmacy.css">
<link rel="stylesheet" type="text/css" href="css/popup.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?key=AIzaSyCt_RpfrVUrVs74uD1VwcXgR3xK64S56DM&sensor=false"></script>
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

#tab1 {
	cursor: pointer;
	cursor: hand;
}

#tab2 {
	cursor: pointer;
	cursor: hand;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	var arr = [ "" ];
	var symtoms = [""];
	var contra = [""];
	$.ajax({
		type : 'post',
		url : "autoCompleteMedAction.action",
		dataType : "json",
		data : "",
		success : function(data) {
			$(data.autoString).each(function(index, el) {
				arr.push(el);
			});
		},
	});
	$.ajax({
		type : 'post',
		url : "autoCompleteSymptom.action",
		dataType : "json",
		data : "",
		success : function(data) {
			$(data.symptoms).each(function(index, el) {
				symtoms.push(el);
			});
		},
	});
	$.ajax({
		type : 'post',
		url : "autoCompleteContra.action",
		dataType : "json",
		data : "",
		success : function(data) {
			$(data.contraindications).each(function(index, el) {
				contra.push(el);
			});
		},
	});
	/*arr.sort();*/
	$("#home_text").autocomplete({
		width : 300,
		max : 10,
		delay : 100,
		minLength : 1,
		autoFocus : true,
		cacheLength : 1,
		scroll : true,
		highlight : true,
		source : function(req, responseFn) {
			if(req.term.trim() != "") {
				var re = $.ui.autocomplete.escapeRegex(req.term.trim());
				var matcher = new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + re.replace("/([\^\$\(\)\[\]\{\}\*\.\+\?\|\\])/gi", "\\$1") + ")(?![^<>]*>)(?![^&;]+;)", "i");
				var a = $.grep(arr, function(item, index) {
					return matcher.test(item.trim());
				});
				autoComplte();
				responseFn(a.slice(0, 20));
			}
		}
	});
	$("#keyWordMedIndication").autocomplete({
		width : 300,
		max : 10,
		delay : 100,
		minLength : 1,
		autoFocus : true,
		cacheLength : 1,
		scroll : true,
		highlight : true,
		source : function(req, responseFn) {
			if(req.term.trim() != "") {
				var re = $.ui.autocomplete.escapeRegex(req.term.replace(",", " ").trim());
				var matcher = new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + re.replace("/([\^\$\(\)\[\]\{\}\*\.\+\?\|\\])/gi", "\\$1") + ")(?![^<>]*>)(?![^&;]+;)", "i");
				var a = $.grep(symtoms, function(item, index) {
					return matcher.test(item.replace(",", " ").trim());
				});
				autoComplte();
				responseFn(a.slice(0, 20));
			}
		}
	});
	$("#keyWordMedContraindication").autocomplete({
		width : 300,
		max : 10,
		delay : 100,
		minLength : 1,
		autoFocus : true,
		cacheLength : 1,
		scroll : true,
		highlight : true,
		source : function(req, responseFn) {
			if(req.term.trim() != "") {
				var re = $.ui.autocomplete.escapeRegex(req.term.replace(",", " ").trim());
				var matcher = new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + re.replace("/([\^\$\(\)\[\]\{\}\*\.\+\?\|\\])/gi", "\\$1") + ")(?![^<>]*>)(?![^&;]+;)", "i");
				var a = $.grep(contra, function(item, index) {
					return matcher.test(item.replace(",", " ").trim());
				});
				autoComplte();
				responseFn(a.slice(0, 20));
			}
		}
	});
});
function autoComplte() {
	var oldFn = $.ui.autocomplete.prototype._renderItem;
	$.ui.autocomplete.prototype._renderItem = function(ul, item) {
		var re = new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + this.term.trim().replace("/([\^\$\(\)\[\]\{\}\*\.\+\?\|\\])/gi", "\\$1") + ")(?![^<>]*>)(?![^&;]+;)", "i");
		var t = item.label.replace(re,
				"<span style='font-weight:Bold;color:black;'>" + this.term.trim()
						+ "</span>");
		return $("<li></li>").data("item.autocomplete", item).append(
				"<a>" + t + "</a>").appendTo(ul);
	};
}
</script>
<script type="text/javascript">
	var userGroup1;
	var userGroup2;
	$(document)
			.ready(
					function() {
						var search = $("#search").val();
						if(search == 'true') {
								$(".itemsMed").append("Thuốc");
								$(".of").append("của");
								$(".show").append("Hiển thị");
						}
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
								$("#ulMenu")
								.append('<li><a href="news">Tin tức </a></li>'
									+'<li><a href="admin_medicineManagement">Quản lý thuốc</a>'
												+ '<ul>'
												+ '<li><a href="admin_registerNewMedicine"> Thêm mới'
												+ ' thuốc</a></li>'
												+ '<li><a href="admin_medicineManagement"> Tìm kiếm'
												+ ' thuốc</a></li>'
												+ '<li><a href="admin_acceptMedicine"> Kiểm duyệt'
												+ ' thuốc</a></li>'
												+ '</ul></li>'
												+ '<li><a href="admin_pharmacyManagement"> Quản lý nhà '
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
<script>
	$(document)
			.ready(
					function() {
						var searchAdvanced = $("#searchAdvanced").val();
						if (searchAdvanced == 'true') {
							$("#other-area").hide();
							$("#advanced-search").show();
							$("#normal-search").hide();
							$("#slogan").hide();
							$("#other-area").hide();
							$("#page_title").show();
							var medTypeStr = $("#medTypeStr1").val();
							$
									.ajax({
										type : 'post',
										url : "medTypeSelectBox.action",
										dataType : "json",
										data : "",
										success : function(data) {
											$(data.medTypeSelectBox)
													.each(
															function(index, el) {
																var split = el
																		.split("~");
																/* $("#medTypeSelect").append(
																		'<option value=' + split[1] + '>'
																				+ split[0]
																				+ '<\/option>'); */
																if (split[1] == medTypeStr) {
																	$(
																			"#medTypeSelect1")
																			.append(
																					'<option selected="selected" value=' + split[1] + '>'
																							+ split[0]
																							+ '<\/option>');
																} else {
																	$(
																			"#medTypeSelect1")
																			.append(
																					'<option value=' + split[1] + '>'
																							+ split[0]
																							+ '<\/option>');
																}
															});
										},
									});
						} else {
							$("#normal-search").show();
							$("#advanced-search").hide();
						}
						$("#start-advanced-search")
								.click(
										function() {
											$
													.ajax({
														type : 'post',
														url : "medTypeSelectBox.action",
														dataType : "json",
														data : "",
														success : function(data) {
															$(
																	data.medTypeSelectBox)
																	.each(
																			function(
																					index,
																					el) {
																				var split = el
																						.split("~");
																				$(
																						"#medTypeSelect1")
																						.append(
																								'<option value=' + split[1] + '>'
																										+ split[0]
																										+ '<\/option>');
																			});
														},
													});
											$("#other-area").hide();
											$("#advanced-search").fadeIn(400);
											$("#normal-search").slideUp(500);
											$("#slogan").slideUp(300);
											$("#result").hide();
											$("#detailedResult").hide();
											$("#pharmacyResult").hide();
											$("#tabmenu").hide();
											$("#register").hide();
											$("#page_title").show();
											$("#invalid").hide();
											$("#header_big").hide();
											$("#videoContainer")[0].pause();
										});
					});
</script>

<script type="text/javascript">
	$(document).ready(
			function() {
				$(".advanced").hide();

				$("#tab1").click(function() {
					$(".advanced").hide();
					$(".not_advanced").show();
				});

				$("#tab2").click(function() {
					$(".advanced").show();
					$(".not_advanced").hide();

				});
				if ($("#medChoosen").val() == 'true') {
					$(".advanced").show();
					$(".not_advanced").hide();
					makeactive(2);
					var medTypeStr = $("#medTypeStr").val();
					$.ajax({
						type : 'post',
						url : "medTypeSelectBox.action",
						dataType : "json",
						data : "",
						success : function(data) {
							$(data.medTypeSelectBox).each(
									function(index, el) {
										var split = el.split("~");
										if (split[1] == medTypeStr) {
											$("#medTypeSelect").append(
													'<option selected="selected" value=' + split[1] + '>'
															+ split[0]
															+ '<\/option>');
										} else {
											$("#medTypeSelect").append(
													'<option value=' + split[1] + '>'
															+ split[0]
															+ '<\/option>');
										}
									});
						},
					});
				} else {
					$(".advanced").hide();
					$(".not_advanced").show();
					initialize();
					makeactive(1);
				}
			});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".anh").click(function() {
			$("#videoContainer")[0].pause();
		});
		$(".map").click(function() {
			$("#videoContainer")[0].pause();
		});
	});
</script>
<script type="text/javascript">
	function onclickTab1() {
		document.paginationForm.action = "thong-tin-hieu-thuoc-chi-tiet";
		document.forms["paginationForm"].submit();
	}
	function onclickTab2() {
		document.myForm.action = "trang-chu-danh-muc-thuoc-dang-ban-action";
		document.myForm.typeOfPackage.value = "-1";
		document.forms["myForm"].submit();
	}
	function danhmucthuoc() {
		var select1 = document.getElementById("medTypeSelect");
		var option1 = select1.options[select1.selectedIndex].value;
		document.myForm.action = "trang-chu-danh-muc-thuoc-dang-ban-action";
		document.myForm.typeOfPackage.value = option1;
		document.forms["myForm"].submit();
	}
	function detail(medId) {
		document.detailForm.action = "detailMedAction?medId=" + medId;
		document.detailForm.submit;
	}
	function makeactive(tab) {
		document.getElementById("tab1").className = "tab";
		document.getElementById("tab2").className = "tab";
		document.getElementById("tab" + tab).className = "active";
	}
</script>
<script type="text/javascript">
	function submitAction(medId) {
		document.paginationForm.action = "thong-tin-thuoc-chi-tiet";
		document.paginationForm.medId.value = medId;
		document.forms["paginationForm"].submit();
	}
	function pharmacyDetail(pharmacyId) {
		document.paginationForm.action = "thong-tin-hieu-thuoc-chi-tiet";
		document.paginationForm.pharmacyId.value = pharmacyId;
		document.forms["paginationForm"].submit();
	}
</script>
<title>Trang chủ</title>
</head>
<%-- <sj:head /> --%>
<body>
	<div id="fixed">
		<div id="language">
			<div id="notice">
				<span>Xin chào</span>
				<s:property value="#session['userName']" />
				| <a href="logout">Đăng xuất</a> | <a href="ChangePassword">Đổi
					mật khẩu</a>
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
					<li><a href="home" id="current">Trang chủ</a></li>
					<li><a href="search_pharmacyInfo">Tìm nhà thuốc</a></li>
					<li class="inner"><a href="search_nearestPharmacy">Nhà
							thuốc gần nhất</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="container">
		<s:hidden name="longtitude" id="longtitude" />
		<s:hidden name="latitude" id="latitude" />
		<s:hidden name="medChoosen" id="medChoosen" />
		<s:hidden name="page" value="home.jsp" />
		<s:hidden name="hasvideo" id="hasvideo" />
		<s:hidden value="%{#session['userName']}" />
		<s:form action="tim-kiem-thuoc-theo-ten" accept-charset="utf-8"
			method="post" theme="simple" name="paginationForm" namespace="/page">
			<s:hidden name="noResult" />
			<s:hidden name="search" id="search"/>
			<s:hidden name="searchAdvanced" id="searchAdvanced" />
			<s:hidden name="detail" />
			<s:hidden name="medId" />
			<s:hidden name="page" value="home.jsp" />
			<s:hidden name="pharmacyId" id="pharmacyId" />
			<s:hidden name="refresh" value="true" />
			<s:hidden name="referPharmacy" id="referPharmacy" />
			<s:hidden name="medTypeStr1" id="medTypeStr1" />
			<s:hidden name="count" />
			<div id="page_title" style="display:none;">
					<ul>
						<li><a href="home" id="current">Trang chủ</a></li>
						<li><img src="css/images/arrow.png" /></li>
						<li><a href="#">Tìm kiếm nâng cao</a></li>
						<li><img src="css/images/arrow.png" /></li>
					</ul>
				</div>
			<div id="content_arena">
				<s:if test="!search && !detail && !referPharmacy">
					<div id="slogan">
						<div id="sliderFrame">
							<div id="slider">
								<a href="http://www.menucool.com/javascript-image-slider"
									target="_blank"> <img src="javascript/images/sl_1.jpg"
									alt="#cap1" />
								</a> <img src="javascript/images/sl_2.jpg"
									alt="Cơ thể yếu đuối khiến tinh thần trở nên yếu ớt." /> <img
									src="javascript/images/sl_3.jpg"
									alt="Sức khỏe tốt và trí tuệ minh mẫn là hai điều hạnh phúc nhất của cuộc đời." />
								<img src="javascript/images/sl_4.jpg"
									alt="Tài sản đầu tiên là sức khỏe." /> <img
									src="javascript/images/sl_5.jpg"
									alt="Người ta không coi trọng sức khỏe cho tới khi đau yếu." />
							</div>
							<div style="display: none;">
								<div id="cap1">
									Chào Mừng bạn đến với <a href="http://www.menucool.com/">Từ
										điển Thuốc Thông Minh</a>.
								</div>
							</div>
						</div>

					</div>
				</s:if>
				<div id="normal-search">
					<div id="home_container">
						<s:textfield name="keyWordMedName"
							placeholder="Tìm kiếm theo tên thuốc" id="home_text"
							cssClass="bs1" maxlength="100"/>
						<s:submit name="search" value="Tra cứu" id="home_submit"
							cssClass="smButton bs1" action="tim-kiem-thuoc-theo-ten-action"
							theme="simple" />
						<div
							style="width: R300px; height: 15px; margin: 15px 0 10px 735px;">
							<a id="start-advanced-search">Tìm kiếm nâng cao</a>
						</div>
					</div>
				</div>
			</div>
			<div id="advanced-search" style="display: none;">
				<!-- <div id="header_big">Tìm kiếm nâng cao</div> -->
				<table id="invisible">
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Tên thuốc:</s:div>
						</td>
						<td><s:textfield type="text" name="advancedKeyWordMedName"
								cssClass="textbox middle" maxlength="100" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Nhà sản xuất:</s:div>
						</td>
						<td><s:textfield type="text" name="keyWordMedManufac"
								cssClass="textbox middle" maxlength="100"/></td>
					</tr>
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Chỉ định/Triệu chứng/Công dụng:</s:div>
						</td>
						<td><s:textfield type="text" name="keyWordMedIndication"
								cssClass="textbox middle" maxlength="2500" id="keyWordMedIndication" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Chống chỉ định:</s:div>
						</td>
						<td><s:textfield type="text"
								name="keyWordMedContraindication" cssClass="textbox middle" maxlength="2500" id="keyWordMedContraindication"/></td>
					</tr>
					<tr>

						<td><s:div id="s_text" cssClass="header_small2">Loại thuốc:</s:div>
						</td>
						<td><select id="medTypeSelect1" name="keyWordMedTypeName"
							class="selectbox smiddle">
								<option value="-1">Tất cả các loại thuốc</option>
						</select> <%-- <s:url var="remoteurl" action="selectTagAction" /> <sj:select
								href="%{remoteurl}" name="keyWordMedTypeName" list="medTypes"
								listKey="medTypeId" listValue="medTypeName" headerKey="-1"
								headerValue="---Xin hãy chọn loại thuốc---"
								cssClass="selectbox smiddle"  /> --%></td>
						<td><s:div id="s_text" cssClass="header_small2">Thành phần:</s:div>
						</td>
						<td><s:textfield type="text" name="keyWordMedIngredients"
								cssClass="textbox middle" maxlength="2500" /></td>
					</tr>
					<tr>
						<td></td>
						<td><s:submit action="user_searchMedAdvanced"
								value="Tìm kiếm" cssClass="smButton" id="user_searchMedAdvanced"></s:submit></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
			<s:if test="search == true">
				<!-- <hr class="line" /> -->
				<s:if test="medList.size() > 0">
					<div id="result">
						<div id="header_big">Kết Quả Tìm Kiếm</div>
						<%@ include file="pagination.jsp"%>
						<table id="visible" class="bs2">
							<tr>
								<th
									class="sortable<s:if test="#attr.pagination.sortColumn.equals('MEDICINE_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'MEDICINE_NAME');">Tên
										Thuốc</a>
								</th>
								<%-- <th
									class="sortable <s:if test="#attr.pagination.sortColumn.equals('MANUFACTURER')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'MANUFACTURER');">Nhà
										Sản Xuất</a>
								</th> --%>
								<th
									class="sortable <s:if test="#attr.pagination.sortColumn.equals('INGREDIENTS')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'INGREDIENTS');">Thành
										Phần Thuốc</a>
								</th>
								<th
									class="sortable <s:if test="#attr.pagination.sortColumn.equals('INDICATIONS')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'INDICATIONS');">Chỉ
										định/Triệu chứng/Công dụng </a>
								</th>
								<th>
									<a>Nhà Thuốc Đang Bán</a>
								</th>
							</tr>
							<s:iterator value="#attr.medList" var="medicine"
								status="rowstatus">
								<s:if test="#rowstatus.even == true">
									<tr class="ac_odd">
								</s:if>
								<s:else>
									<tr>
								</s:else>
								<td class="limited"><s:a
										href="javascript:submitAction(%{medId});" target="_self">
										<s:property value="medName" />
									</s:a></td>
								<%-- <td class="limited"><s:property value="manufacturer" /></td> --%>
								<td><s:property value="ingredients" /></td>
								<td><s:property value="indications" /></td>
								<td>
								<s:if test="pharmacyList.size() > 0">
									<s:iterator value="pharmacyList" var="pharmacy">
										<s:a href="javascript:pharmacyDetail(%{pharmacyId})"><s:property value="pharmacyName"/></s:a>
										<br/>
									</s:iterator>
									</s:if>
									<s:else>
										<s:label>Chưa có thông tin</s:label>
									</s:else>
								</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</s:if>
				<s:else>
				<div id="header_big" class="marginB20">Kết Quả Tìm Kiếm</div>
						<div class="notice searchInvalid" id="invalid">Xin lỗi, không tìm thấy kết
						quả tìm kiếm với từ khoá bạn nhập!</div>
					</s:else>
			</s:if>
			<s:if test="detail == true">
				<!-- <hr class="line" /> -->
				<div id="detailedResult">
					<div id="header_big" class="marginB20">Kết Quả Chi Tiết</div>
					<div id="dynamic-list" class="bs2">
					<!-- <div>Thông tin chi tiết</div> -->
					<!-- <hr class="detail-line"/> -->
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
									src="image/medicine/<s:property value="%{medDetail.imgPath}" />"/></td>
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
									<%-- <li><s:property value="%{medDetail.ingredients}" /></li> --%>

								</ul>
							</dd>
							<dt onclick="makeDynamic(5)">
								<a class="header_small2">Chỉ định/Triệu chứng/Công dụng</a>
								<div id="arrowDown" class="d_dt5" ></div>
								<div id="arrowUp" class="u_dt5" style="display: none;"></div>
							</dt>
							<dd id="dd5">
								<ul>
									<s:iterator value="medDetail.indicationList">
											<s:property />
											<br/>
										</s:iterator>
									<%-- <li><s:property value="%{medDetail.indications}" /></li> --%>

								</ul>
								<!-- <ul>
										<li id="ten">Tên: <a href="#">Nhà thuốc 1</a></li>
										<li id="diachi">Địa chỉ: địa chỉ 1</li>
									</ul> -->
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
									<%-- <li><s:property value="%{medDetail.storage}" /></li> --%>

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
									<%-- <li><s:property value="%{medDetail.interaction}" /></li> --%>

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
									<%-- <li><s:property value="%{medDetail.similarMedicine}" /></li> --%>

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
											<s:a href="javascript:pharmacyDetail(%{pharmacyId})">
													<s:property value="pharmacyName" />
													<br/>
												</s:a>
										</s:iterator>
									</s:if>
									<s:else>
										Chưa có thông tin
									</s:else>
								</ul>
							</dd>
						</dl>

					</div>
				</div>
			</s:if>

				<s:if test="!search && !detail &&!referPharmacy">
					<div id="other-area">
				<div id="adv-header">
					<span>Dành Cho Quảng Cáo</span>
				</div>
				<s:iterator value="adLineOne">
					<div id="adv-item">
						<div id="adv-title">
							<a href="http://<s:property value="link" />" target="_blank"><s:property value="title"/> </a>
						</div>
						<div id="adv-link">
							<a href="http://<s:property value="link" />" target="_blank"><s:property value="link"/> </a>
						</div>
						<div id="adv-img">
							<a href="http://<s:property value="link" />" target="_blank"><img src="Advertise_image/<s:property value="imgPath" />" /></a>
						</div>
						<div id="adv-content">
							<a href="http://<s:property value="link" />" target="_blank"><s:property value="content"/> </a>
						</div>
					</div>
					<div id="token"></div>
				</s:iterator>
				<hr class="line1"/>
				<s:iterator value="adLineTwo">
					<div id="adv-item">
						<div id="adv-title">
							<a href="http://<s:property value="link" />" target="_blank"><s:property value="title"/> </a>
						</div>
						<div id="adv-link">
							<a href="http://<s:property value="link" />" target="_blank"><s:property value="link"/> </a>
						</div>
						<div id="adv-img">
							<a href="http://<s:property value="link" />" target="_blank"><img src="Advertise_image/<s:property value="imgPath" />" /></a>
						</div>
						<div id="adv-content">
							<a href="http://<s:property value="link" />" target="_blank"><s:property value="content"/> </a>
						</div>
					</div>
					<div id="token"></div>
				</s:iterator>
			</div>
		</s:if>
		</s:form>
		<s:if test="referPharmacy == true">
			<div id=register>
				<ul id="tabmenu">
					<li onclick="makeactive(1)"><div class="tab active" id="tab1"
							onclick="onclickTab1();">Thông tin hiệu thuốc</div></li>
					<li onclick="makeactive(2)"><div class="tab" id="tab2"
							onclick="onclickTab2();">Danh mục thuốc</div></li>
				</ul>
				<div id="content">
					<div>
						<div id="rootElement" class="not_advanced"
							style="border: none; width: 97%;">
							<div id="topBig">
								<li class="headerBig"><s:property
										value="%{pharmacy.pharmacyName}" /></li>
							</div>
							<hr class="line" />
							<div id="midLeftPopup">
								<div id="top">
									<li class="header ttcb">Thông tin người đại diện</li>
								</div>
								<ul>
									<li class="detail house"><s:label value="Tên:" id="hder" />
										<s:property value="%{rep.name}" /></li>
									<li class="detail house"><s:label value="Email:" id="hder" />
										<s:property value="%{rep.email}" /></li>
									<li class="detail house"><s:label value="Điện thoại:"
											id="hder" /> <s:property value="%{rep.phone}" /></li>
									<li class="detail house"><s:label value="Trình độ:"
											id="hder" /> <s:property value="%{rep.degreeName}" /></li>
									<li class="detail house"><s:label
											value="Giấy phép hành nghề:" id="hder" /> <s:property
											value="%{rep.licensureNo}" /></li>
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
										<li class="detail house"><b>Giấy phép kinh doanh số:</b>
											<s:property value="%{pharmacy.businessLicenseNo}" /></li>
										<li class="detail house"><b>Trực thuộc công ty:</b>
										<s:property value="%{pharmacy.pharCompany}" /></li>
										<li class="detail house"><b>GPP số:</b> <s:property
												value="%{pharmacy.gppNo}" /></li>
										<li class="detail house"><b>Bằng khen, chứng nhận:</b> <s:property
												value="%{pharmacy.notes}" /></li>
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
									<s:if test="hasvideo == true">
										<object id="videoPath">
											<video id="videoContainer" width="400px" height="400px" controls> <source
												src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
												type="video/mp4"> 
												<source
												src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
												type="video/ogg"> 
												<source
												src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
												type="video/webm">
												</video>
										</object>
									</s:if>
									<s:else>
										<img
											src="Pharmacy_Medias/<s:property value="%{pharmacy.videoPath}" />"
											style="width: 100%; height: 400px;" />
									</s:else>
								</div>
								<div id="googleMap" style="width: 100%; height: 400px;"
									class="p_map">
								</div>
							</div>
						</div>
					</div>
					<s:form action="trang-chu-danh-muc-thuoc-dang-ban" theme="simple"
						acceptcharset="utf-8" method="post" name="myForm">
						<div id="rootElement" class="advanced"
							style="border: none; width: 97%;">
							<s:hidden name="pharmacyId" />
							<s:hidden name="page" value="home.jsp" />
							<s:hidden name="typeOfPackage" id="typeOfPackage" />
							<s:hidden name="keyWordMedName" id="keyWordMedName" />
							<s:hidden name="advancedKeyWordMedName"
								id="advancedKeyWordMedName" />
							<s:hidden name="keyWordMedManufac" id="keyWordMedManufac" />
							<s:hidden name="keyWordMedIndication" id="keyWordMedIndication" />
							<s:hidden name="keyWordMedContraindication"
								id="keyWordMedContraindication" />
							<s:hidden name="keyWordMedIngredients" id="keyWordMedIngredients" />
							<s:hidden name="medIdDetail" id="medIdDetail" />
							<s:hidden name="searchAdvanced" id="searchAdvanced" />
							<s:hidden name="medTypeStr" id="medTypeStr" />
							<s:hidden name="medTypeStr1" id="medTypeStr1" />
							<s:if test="medicineList.size() >= 0">
								<div id="header_big"
									style="margin: 30px 0 10px 30px !important;">Danh mục
									thuốc đang bán</div>
								<div id="ds_header">
								<script>
								$(document).ready(function() {
									var referPharmacy = $("#referPharmacy").val();
									/* $(".itemsPhar").remove(); */
									if(referPharmacy == 'true') {
										$(".itemsPhar").append("Thuốc");
										$(".of").append("của");
										$(".show").append("Hiển thị");
									}
								});
								</script>
								
									<%@ include file="NextPagination.jsp"%>
									<select id="medTypeSelect" name="keyWordMedTypeName"
										class="selectbox smiddle" onchange="danhmucthuoc();">
										<option value="-1">Tất cả các loại thuốc</option>
									</select>
								</div>
								<s:if test="medicineList.size() > 0">
									<table id="visible" style="margin-top: 10px;">
										<tr>
											<th
												class="sortable<s:if test="#attr.pagination.sortColumn.equals('MEDICINE_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
												<a href="#" onclick="fnNextPagination(6,'MEDICINE_NAME');">Tên
													Thuốc</a>
											</th>
											<th
												class="sortable <s:if test="#attr.pagination.sortColumn.equals('MANUFACTURER')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
												<a href="#" onclick="fnNextPagination(6,'MANUFACTURER');">Nhà
													Sản Xuất</a>
											</th>
											<th
												class="sortable <s:if test="#attr.pagination.sortColumn.equals('INGREDIENTS')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
												<a href="#" onclick="fnNextPagination(6,'INGREDIENTS');">Thành
													Phần Thuốc</a>
											</th>
											<th
												class="sortable <s:if test="#attr.pagination.sortColumn.equals('INDICATIONS')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
												<a href="#" onclick="fnNextPagination(6,'INDICATIONS');">Chỉ
													Định </a>
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
											<td><s:a href="javascript:submitAction(%{medId});">
													<s:property value="medName" />
												</s:a></td>
											<td class="limited"><s:property value="manufacturer" /></td>
											<td><s:property value="ingredients" /></td>
											<td><s:property value="indications" /></td>
											</tr>
										</s:iterator>
									</table>
								</s:if>
								<s:else>
									<div class="notice searchInvalid">
										<s:label>Không có thuốc trong danh mục thuốc đang bán</s:label>
									</div>
								</s:else>
							</s:if>
						</div>
					</s:form>
				</div>
			</div>
		</s:if>
	</div>
	<script type="text/javascript">
		function makeDynamic(number) {
			$("#dd" + number).slideToggle("slow");
			$(".u_dt" + number).toggle("slow");
			$(".d_dt" + number).toggle("slow");
		}
	</script>
</body>
</html>
