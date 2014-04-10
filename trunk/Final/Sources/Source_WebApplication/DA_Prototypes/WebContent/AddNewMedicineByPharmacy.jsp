<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script src="javascript/js-image-slider.js" type="text/javascript"></script>
<script type="text/javascript" src="javascript/dynamic-list.js"></script>
<script type="text/javascript" src="javascript/pagination.js"></script>
<%-- <script type="text/javascript" src="javascript/paging-medicine.js"></script> --%>
<script type="text/javascript" src="javascript/paging-med-phar.js"></script>
<%-- <script type="text/javascript" src="javascript/autocomplete_MedForPhar.js"></script> --%>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="stylesheet" type="text/css" href="css/tab-menu.css">
<script type="text/javascript" src="javascript/append_n.js"></script>
<script type="text/javascript" src="javascript/show_hide_add_med.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript"
	src="javascript/jquery.textarea-expander.js"></script>
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
<script type="text/javascript">
$(document).ready(function() {
	var arr = [ "" ];
	var pharmacyId = "<s:property value='pharmacyId' />";
	$.ajax({
		type : 'post',
		url : "medForPharAction.action?pharmacyId=" + pharmacyId,
		dataType : "json",
		data : "",
		success : function(data) {
			$(data.medForPhar).each(function(index, el) {
				arr.push(el);
			});
		},
	});
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
											medName : {
												required : true
											},
											manufacturer : {
												required : true
											},
											keyWordMedTypeName1 : {
												required : true
											},
											indications : {
												required : true
											},
											dosingAndUse : {
												required : true
											},
											ingredients : {
												required : true
											}
										},
										messages : {
											medName : "Mục này bắt buộc phải nhập",
											manufacturer : {
												required : "Mục này bắt buộc phải nhập"
											},
											keyWordMedTypeName1 : {
												required : "Mục này bắt buộc phải nhập"
											},
											indications : {
												required : "Mục này bắt buộc phải nhập"
											},
											dosingAndUse : {
												required : "Mục này bắt buộc phải nhập"
											},
											ingredients: {
												required : "Mục này bắt buộc phải nhập"
											}
										},
										submitHandler : function(form) {
											form.submit();
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
	$(document).ready(function() {
		$(".itemsMed").append("Thuốc");
		$.ajax({
			type : 'post',
			url : "medTypeSelectBox.action",
			dataType : "json",
			data : "",
			success : function(data) {	
				$(data.medTypeSelectBox).each(
						function(index, el) {
							var split = el.split("~");
								$("#medTypeSelect").append(
										'<option value=' + split[1] + '>'
												+ split[0]
												+ '<\/option>');
						});
			},
		});
		if($("#medChoosen").val() == 'true') {
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
	var userGroup1;
	var userGroup2;
	$(document).ready(function() {
		var medIdList = document.getElementById("medIdList").value;
		var medIdSplit = medIdList.split("~");
		$("#checkAll").click(function() {
			var check = document.getElementById("checkAll");
			for(var i = 0; i < medIdSplit.length; i++) {
				var checkEachRow = document.getElementById("checkBox[" + medIdSplit[i] +"]");
				if(check.checked == true) {
					checkEachRow.checked = true;
				} else {
					checkEachRow.checked = false;
				}
			}
		});
		$(".checkBox").click(function () {
			var check = document.getElementById("checkAll");
			for(var i = 0; i < medIdSplit.length; i++) {
				var checkEachRow = document.getElementById("checkBox[" + medIdSplit[i] +"]");
				if(checkEachRow.checked == false) {
					check.checked = false;
				}
			}
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
		} else if (userGroup2) {
			user = userGroup2;
		}
		document.getElementById("userGroup").value = user;
	});
</script>
<script type="text/javascript">
	function submitAction(medId) {
		/* document.paginationForm.action = "thong-tin-thuoc-chi-tiet"; */
		document.paginationForm.action = "thong-tin-thuoc";
		//document.paginationForm.medIdDetail.value = medId;
		document.paginationForm.medId.value = medId;
		document.paginationForm.page.value = "AddNewMedicineByPharmacy.jsp";
		document.forms["paginationForm"].submit();
	}
	function pharmacyDetail() {
		document.myForm.action = "pharmacyDetail";
		document.myForm.page.value = "pharmacy_registerNewMedicine.jsp";
		document.forms["myForm"].submit();
	}
	function submitReferAddPage() {
		//document.myForm.action = "them-moi-thuoc-vao-danh-muc";
		document.myForm.action = "them-thuoc-vao-nha-thuoc";
		document.forms["myForm"].submit();
	}
	function openPopup() {
		var medTypeLst = $("#medTypeIdList").val();
		var url = "cac-loai-thuoc?medTypeLst=" + medTypeLst;
		var w = 400;
		var h = 200;
		var left = Number((screen.width / 2) - (w / 2));
		var tops = Number((screen.height / 2) - (h / 2));
		window
				.open(
						url,
						'jav',
						'scrollbars=no,location=no,resizable=yes,width=700, height=500,top=0,left=0,top='
								+ tops + ',left=' + left);
	}
	function tab1() {
		document.getElementById("addByPharSuccess").value = "false";
	}
</script>
<title>Thêm thuốc vào danh mục</title>
</head>
<body>
	<div id="language">
		<div id="notice">
			<span>Xin chào </span>
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
				<li><a href="javascript:submitReferAddPage();" id="current">
						Thêm thuốc vào danh mục</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div>
			<ul id="tabmenu">
				<li onclick="makeactive(1)"><div class="tab active" id="tab1" onclick="javascript:submitReferAddPage();">
						<a id>Thêm mới thuốc</a>
					</div></li>
				<li onclick="makeactive(2)"><div class="tab" id="tab2">Tìm kiếm có sẵn</div></li>
			</ul>
			<div id="content">
			<s:hidden name="medChoosen" id="medChoosen"/>
			<div class="not_advanced">
					<s:form theme="simple" acceptcharset="utf-8"
						action="pharmacy_addNewMed" method="post" id="myForm" name="myForm"
						enctype="multipart/form-data">
						<s:hidden name="pharmacyId" />
						<s:hidden name="pharmacyName" id="pharmacyName" />
						<s:hidden name="medTypeIdList" id="medTypeIdList"/>
						<s:hidden name="medTypeNameList" id="medTypeNameList" />
						<s:hidden name="page" value="AddNewMedicineByPharmacy.jsp" />
						<s:hidden name="actionSucces" />
						<s:hidden name="addByPharSuccess" />
						<s:hidden name="accept" value="N" />
						<s:hidden name="medId" />
						<s:hidden name="smdEmail" value="%{#session['smdEmail']}" />
						<s:if test="!addByPharSuccess">
						<table id="invisible" style="margin: 0; padding: 0; border: none;">
							<tr>
								<td>
									<table style="width: 930px;">
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Ảnh:</s:div></td>
											<td><s:file name="keyWordMedImage" /></td>
											<td><s:div id="s_text" cssClass="header_small2">Tên thuốc gốc:</s:div></td>
											<td><s:textfield type="text" name="genericMedicine"
													value="%{Medicine.genericMedicine}" id="genericMedicine"
													cssClass="textbox middle" maxlength="100"/></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2 required">Tên thuốc:</s:div></td>
											<td><s:textfield type="text" name="medName"
													value="%{Medicine.medName}" cssClass="textbox middle" id="medName" maxlength="100"/></td>
											<td><s:div id="s_text" cssClass="header_small2 required">Nhà sản xuất:</s:div></td>
											<td><s:textfield type="text" name="manufacturer" id="manufacturer"
													value="%{Medicine.manufacturer}" cssClass="textbox middle" maxlength="100" /></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2 required">Loại thuốc:</s:div>
											</td>
											<td onclick="javascript:openPopup();"><s:textarea name="keyWordMedTypeName1"
													value="%{medTypeNameList}" cssClass="textbox middle medTypeButton expand26-180"
													id="keyWordMedTypeName1" readonly="true" style="width:283px;"/></td>
											<td><s:div id="s_text" cssClass="header_small2">Tên thương mại:</s:div></td>
											<td><s:textfield type="text" id="brandName"
													value="%{Medicine.brandName}" cssClass="textbox middle"
													name="brandName" maxlength="100" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table id="admin-tab">
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Thành phần:</s:div></td>
											<td><s:textarea value="%{medicine.ingredients}"
													name="ingredients" cols="97" id="ingredients"
													cssClass="expand40-180" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2 required">Chỉ định/Triệu chứng/Công dụng:</s:div></td>
											<td><s:textarea value="%{medicine.indications}"
													name="indications" cols="97"
													cssClass="expand60-180" id="indications" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Chống chỉ định:</s:div></td>
											<td><s:textarea value="%{medicine.contraindications}"
													name="contraindications" cols="97" id="contraindications"
													cssClass="expand60-180" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2 required">Cách dùng và sử dụng:</s:div></td>
											<td><s:textarea value="%{medicine.dosingAndUse}"
													name="dosingAndUse" cols="97" id="dosingAndUse"
													cssClass="expand40-180" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Bảo quản:</s:div></td>
											<td><s:textarea value="%{medicine.storage}"
													name="storage" cols="97" id="storage"
													cssClass="expand40-180" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Cảnh báo:</s:div></td>
											<td><s:textarea value="%{medicine.warning}"
													name="warning" cols="97" id="warning"
													cssClass="expand40-180" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Thuốc tương tự:</s:div></td>
											<td><s:textarea value="%{medicine.similarMedicine}"
													name="similarMedicine" cols="97" id="similarMedicine"
													cssClass="expand40-180" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Thuốc tương tác:</s:div></td>
											<td><s:textarea value="%{medicine.interaction}"
													name="interaction" cols="97" id="interaction"
													cssClass="expand40-180" maxlength="2500"></s:textarea></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td><s:submit style="margin-left: 105px;"
										action="pharmacy_addNewMed" value="Lưu lại"
										cssClass="smButton greenButton"></s:submit></td>
							</tr>
						</table>
						</s:if>
						<s:else>
							<div class="notice searchValid">Bạn đã thêm vào danh mục thuốc thành công thành công!</div>
							<div id="dynamic-list">
							<table>
								<tr>
									<td>
										<table id="left-of-dynamic">
											<tr>
												<td class="limited"><a class="header_small2">Tên
														thuốc: </a></td>
												<td><s:property value="medName" /></td>
											</tr>
											<tr>
												<td class="limited"><a class="header_small2">Nhà
														sản xuất: </a></td>
												<td><s:property value="manufacturer" /></td>
											</tr>
											<tr>
												<td class="limited"><a class="header_small2">Loại
														thuốc: </a></td>
												<td><s:property value="keyWordMedTypeName" /></td>
											</tr>
											<tr>
												<td class="limited"><a class="header_small2">Tên
														thuốc gốc: </a></td>
												<td><s:property value="genericMedicine" /></td>
											</tr>
											<tr>
												<td class="limited"><a class="header_small2">Tên
														biệt dược: </a></td>
												<td><s:property value="brandName" /></td>
											</tr>
										</table>
									</td>
									<s:if test="imgPath != null && imgPath != ''">
									<td>
									<img
										src="image/medicine/<s:property value="%{medicine.imgPath}" />"
										style="height: 160px; width: 240px;" /></td>
										</s:if>
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
										<s:iterator value="medicine.ingredientsList">
											<s:property />
											<br/>
										</s:iterator>

									</ul>
								</dd>
								<dt onclick="makeDynamic(5)">
									<a class="header_small2">Chỉ định/Triệu chứng/Công dụng</a>
									<div id="arrowDown" class="d_dt5" ></div>
									<div id="arrowUp" class="u_dt5" style="display: none;"></div>
								</dt>
								<dd id="dd5">
									<ul>
										<s:iterator value="medicine.indicationList">
											<s:property />
											<br />
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
										<s:iterator value="medicine.contraindicationList">
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
										<s:iterator value="medicine.dosingAndUseList">
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
										<s:iterator value="medicine.warningList">
											<s:property />
											<br/>
										</s:iterator>

									</ul>
								</dd>
								<dt onclick="makeDynamic(11)">
									<a class="header_small2">Bảo quản</a>
									<div id="arrowDown" class="d_dt11"></div>
									<div id="arrowUp" class="u_dt11" style="display: none;"></div>
								</dt>
								<dd id="dd11">
									<ul>
										<s:iterator value="medicine.storageList">
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
										<s:iterator value="medicine.interactionList">
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
										<s:iterator value="medicine.similarMedicineList">
											<s:property />
											<br/>
										</s:iterator>

									</ul>
								</dd>
								<dt onclick="makeDynamic(14)">
									<a class="header_small2">Hiệu thuốc đang bán</a>
									<div id="arrowDown" class="d_dt14"></div>
									<div id="arrowUp" class="u_dt14" style="display: none;"></div>
								</dt>
								<dd id="dd14">
									<ul>
										<s:property value="pharmacyName" />
									</ul>
								</dd>
							</dl>
							</div>
							<s:if test="!medChoosen || medChoosen == false">
								<s:submit style="margin-left: 46px;"
									action="them-thuoc-vao-nha-thuoc" value="Quay lại"
									cssClass="smButton greenButton" />
							<s:submit style="margin-left: 2px;" action="pharmacy_copyNewMedicine"
									value="Bản sao mới" cssClass="smButton greenButton" />
							</s:if>
						</s:else>
					</s:form>
				</div>
		<s:form action="thuoc-trong-tu-dien" accept-charset="utf-8"
			method="post" theme="simple" name="paginationForm"
			cssClass="advanced">
				<s:hidden name="noResult" />
				<s:hidden name="pharmacyName" id="pharmacyName"/>
				<s:hidden name="search" />
				<s:hidden name="detail" />
				<s:hidden name="medIdDetail" />
				<s:hidden name="medId" />
				<s:hidden name="smdEmail" value="%{#session['smdEmail']}" />
				<s:hidden name="pharmacyId" />
				<s:hidden name="success" />
				<s:hidden name="medIdList" id="medIdList" />
				<s:hidden name="page" value="AddNewMedicineByPharmacy.jsp" />
				<s:hidden name="paginationRecords"
					value="%{pagination.page_records}" id="paginationRecords" />
				<s:hidden name="failedAction" id="failedAction" />
				<s:hidden name="successedAction" id="successedAction" />
				<s:hidden name="refresh" value="true" />
					<s:textfield name="keyWordMedName"
						placeholder="Tìm kiếm theo tên thuốc" id="home_text" maxlength="100"/>
					<s:submit name="search" value="Tra cứu" id="home_submit"
						cssClass="smButton" action="thuoc-trong-tu-dien-action" />
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
				<s:if test="search == true">
					<!-- <hr class="line" /> -->
					<s:if test="medList.size() > 0">
						<div id="header_big">Kết quả tìm kiếm</div>
						<%@ include file="pagination.jsp"%>
						<%-- <div class="error">
							<s:fielderror fieldName="nullCheckBox" theme="simple" />
						</div> --%>
						<table id="visible">
							<tr>
								<th id="col-check-box"><s:checkbox name="checkAll" theme="simple"
										id="checkAll" value="false" /></th>
								<th
									class="sortable<s:if test="#attr.pagination.sortColumn.equals('MEDICINE_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'MEDICINE_NAME');">Tên
										thuốc</a>
								</th>
								<th
									class="sortable <s:if test="#attr.pagination.sortColumn.equals('INGREDIENTS')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'INGREDIENTS');">Hoạt
										chất chính </a>
								</th>
								<th
									class="sortable <s:if test="#attr.pagination.sortColumn.equals('TYPE_OF_PACKAGE_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'TYPE_OF_PACKAGE_NAME');">Loại
										thuốc</a>
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
								<td class="checkBox" id="col-check-box"><s:checkbox name="checkBox" fieldValue="%{medId}"
										theme="simple" id="checkBox[%{medId}]" cssClass="checkBox" /></td>
								<td><s:a href="javascript:submitAction(%{medId});">
										<s:property value="medName" />
									</s:a></td>
								<td><s:property value="ingredients" /></td>
								<td><s:property value="typeOfPackageName" /></td>
								</tr>
							</s:iterator>
						</table>
						<s:submit name="add" value="Thêm vào danh mục thuốc"
							theme="simple" cssClass="smButton searchButton"
							action="them-vao-danh-muc-thuoc" />
					</s:if>
					<s:elseif test="noResult == true">
						<div class="notice searchInvalid">Xin lỗi, không tìm thấy kết
						quả tìm kiếm với từ khoá bạn nhập!</div>
					</s:elseif>
				</s:if>
				<s:if test="detail == true">
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
								<a class="header_small2">Hiệu thuốc đang bán</a>
								<div id="arrowDown" class="d_dt14"></div>
								<div id="arrowUp" class="u_dt14" style="display: none;"></div>
							</dt>
							<dd id="dd14">
								<ul>
									<s:if test="medDetail.pharmacyList.size() > 0">
										<s:iterator value="medDetail.pharmacyList">
													<s:property value="pharmacyName" />
										</s:iterator>
									</s:if>
									<s:else>
										Chưa có thông tin
									</s:else>
								</ul>
							</dd>
						</dl>
						<s:submit name="add" value="Thêm vào danh mục thuốc"
							theme="simple" cssClass="smButton searchButton"
							action="them-vao-danh-muc-thuoc" />
					</div>
				</s:if>
		</s:form>
		</div>
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
</body>
</html>
