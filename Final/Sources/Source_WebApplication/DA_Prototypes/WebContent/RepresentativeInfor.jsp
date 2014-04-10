<%--
    Document   : search_Medicines
    Created on : Apr 27, 2013, 10:59:49 PM
    Author     : W7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/AdministrationPharmacy.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript" src="javascript/login.js"></script>
<script src="javascript/js-image-slider.js" type="text/javascript"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
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
											representativeName : {
												required : true
											},
											mobilePhone : {
												required : true,
												number : true,
												minlength : 9
											},
											licensureNo : {
												required : true
											},
											dosingAndUse : {
												required : true
											},
											ingredients : {
												required : true
											},
											genericMedicine : {
												required : true
											}
										},
										messages : {
											representativeName : "Mục này bắt buộc phải nhập",
											mobilePhone : {
												required : "Mục này bắt buộc phải nhập",
												number : "Sai định dạng. Vui lòng nhập số",
												minlength : "Số điện thoại phải có ít nhất 9 số"
											},
											licensureNo : {
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
											},
											genericMedicine : {
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
// Tooltip only Text
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
	function pharmayInfor(pharId) {
		document.myForm.action = "pharmacyDetail";
		document.myForm.pharmacyId.value = pharId;
		document.forms["myForm"].submit();
	}
	function changeRepInfor() {
		document.myForm.action = "chinh-sua-thong-tin-nguoi-dai-dien-action";
		document.forms["myForm"].submit();
	}
</script>
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
.editRepButton {
	width: 35px;
	height: 31px;
	border: none;
	margin-left: 6px;
}
</style>
<title>Quản lý nhà thuốc</title>
</head>
<sj:head />
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
				<li><a href="RepresentativeInfor" id="current">Nhà thuốc
						của bạn</a>
			</ul>

		</div>
	</div>
	<div id="container">
		<!-- <div id="page_title">
			<ul>
				<li><a href="RepresentativeInfor" id="current"> Hiệu thuốc
						của bạn</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div> -->
		<br/>
		<s:form id="myForm" action="chinh-sua-thong-tin-nguoi-dai-dien"
			theme="simple" acceptcharset="utf-8">
			<s:url var="degreeUrl" action="degreeAction" />
			<s:hidden name="pharmacyId" />
			<s:hidden name="repId" value="%{#session['rep'].repId}" />
			<s:hidden name="edit" id="edit" />
			<s:hidden name="degreeInput" value="%{#session['rep'].degree}" />
			<s:hidden name="editSuccess" id="editSuccess" />
			<s:hidden name="page" value="pharmacy_registerNewMedicine.jsp" />
			<s:hidden name="smdEmail" value="%{#session['smdEmail']}" />
			<s:if test="!edit">
				<div id="AdministrationPharmacy">
					<div id="pharmacist">
						<s:hidden name="userGroup" id="userGroup" />
						<li class="title pharmacist">Thông tin dược sĩ<a href="javascript:changeRepInfor();"
								class="editRepButton" title="Chỉnh sửa thông tin người đại diện">Chỉnh sửa</a> <!-- <img src="image/edit.jpg" style="margin-left:5px;margin-top:3px;width: 35px; height: 32px;"> --></li>
						<li class="detail child pharmacist"><b>Tên người đại
								diện:</b> <s:property value="#session['rep'].name" /></li>
						<li class="detail child pharmacist"><b>Email:</b> <s:property
								value="#session['rep'].email" /></li>
						<li class="detail child pharmacist"><b>Số điện thoại:</b> <s:property
								value="#session['rep'].phone" /></li>
						<li class="detail child pharmacist"><b>Giấy phép hành
								nghề:</b> <s:property value="#session['rep'].licensureNo" /></li>
						<li class="detail child pharmacist"><b>Trình độ:</b> <s:property
								value="#session['rep'].degreeName" /></li>
					</div>
					<div id="pharmacy">
						<li class="title pharmacy">Danh sách các hiệu thuốc được đại
							diện:</li>
						<s:iterator value="#session['pharmacyList']" var="pharmacy">
							<li class="info pharmacy child"><a
								href="javascript:pharmayInfor(<s:property value="pharmacyId" />);"><s:property
										value="pharmacyName" /></a></li>
						</s:iterator>
					</div>
					<%-- <s:submit name="search" value="Chỉnh sửa thông tin" id="home_submit"
						action="chinh-sua-thong-tin-nguoi-dai-dien-action" theme="simple" cssClass="smButton"/> --%>
				</div>
			</s:if>
			<%-- <s:submit value="Chỉnh sửa thông tin" action="chinh-sua-thong-tin-nguoi-dai-dien" theme="simple"/> --%>
			<s:else>
				<div id="rootElement" class="step0">
					<div id="register_head">Chỉnh Sửa Thông Tin Người Đại Diện
						(dược sỹ chính)</div>
					<div id="nodeElement1">
						<div id="nodeElement2">
							<div id="register_title" class="required header_small1">Họ
								và tên</div>
							<div class="fieldgroup">
								<s:textfield id="representativeName" name="representativeName"
									cssErrorClass="errorStyle" cssClass="textbox short"
									value="%{#session['rep'].name}" maxlength="150"/>
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
									cssErrorClass="errorStyle" cssClass="textbox short"
									value="%{#session['rep'].phone}" maxlength="10"/>
							</div>
							<!--<div class="error">
										<s:fielderror fieldName="mobilePhone" theme="simple" />
									</div>-->
						</div>
					</div>
					<div id="nodeElement1">
						<div id="nodeElement2">
							<div id="register_title" class="required header_small1">Trình
								độ</div>
							<sj:select href="%{degreeUrl}" id="degree" name="degree"
								list="degreeList" listKey="degreeId" listValue="degreeName"
								cssClass="select" />
						</div>
						<div id="nodeElement2">
							<div id="register_title" class="required header_small1">Giấy
								Phép Hành Nghề Số</div>
							<div></div>
							<div class="fieldgroup">
								<s:textfield id="licensureNo" name="licensureNo"
									cssErrorClass="errorStyle" cssClass="textbox short"
									value="%{#session['rep'].licensureNo}" maxlength="100" />
							</div>
							<!--<div class="error">
										<s:fielderror fieldName="mobilePhone" theme="simple" />
									</div>-->
						</div>
					</div>
					<div id="stepOneButton">
						<div id="nodeElement21">
							<s:submit name="search" value="Lưu lại" id="home_submit"
								action="chinh-sua-thong-tin-nguoi-dai-dien" theme="simple"
								cssClass="smButton greenButton" />
							<!-- <input type="submit" value="Lưu lại"
										class="r_button" action="chinh-sua-thong-tin-nguoi-dai-dien" /> -->
						</div>
						<div id="nodeElement21">
							<s:submit name="search" value="Hủy bỏ" id="home_submit"
								action="huy-bo" theme="simple" cssClass="cancel smButton" />
							<!-- <input type="submit" value="Hủy bỏ"
										class="r_button" action="huy-bo" /> -->
						</div>
					</div>
				</div>
			</s:else>
		</s:form>
</body>
</html>
