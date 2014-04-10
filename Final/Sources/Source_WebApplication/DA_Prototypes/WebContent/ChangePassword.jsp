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
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel="stylesheet" type="text/css" href="css/about-us.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/login.js"></script>
<script src="javascript/js-image-slider.js" type="text/javascript"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/validate.file.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#saveButton").click(function () {
			document.getElementById("wrongPassWord").style.display = "none";
		});
		$("#password").keyup(function () {
			document.getElementById("wrongPassWord").style.display = "none";
		});
		$('#password').bind('cut copy paste', function(event) {
	        event.preventDefault();
	    });
		$('#retypePassword').bind('cut copy paste', function(event) {
	        event.preventDefault();
	    });
		$('#newPassword').bind('cut copy paste', function(event) {
	        event.preventDefault();
	    });
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
				$("#loginForm")
						.validate(
								{
									rules : {
										retypePassword : {
											required : true,
											customvalidation : true,
											minlength: 8,
											equalTo : "#newPassword"
										},
										newPassword : {
											required : true,
											customvalidation : true,
											minlength: 8,
											equalOldPasswrod: function() {return $("#password").val();}
										},
										password : {
											required : true,
											customvalidation : true,
											minlength: 8
										}
									},
									messages : {
										password : {
											required : "Mục này bắt buộc phải nhập",
											minlength : "Mật khẩu của bạn phải có ít nhất 8 ký tự"
										},
										newPassword : {
											required : "Mục này bắt buộc phải nhập",
											minlength : "Mật khẩu của bạn phải có ít nhất 8 ký tự"
										},
										retypePassword : {
											required : "Mục này bắt buộc phải nhập",
											minlength : "Mật khẩu của bạn phải có ít nhất 8 ký tự",
											equalTo : "Mật khẩu xác nhận không khớp"
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
				$.validator.addMethod("equalOldPasswrod", function(value,
						element, params) {
					return params != value;
				}, "Mật khẩu này phải khác mật khẩu cũ");
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
								$("#ulMenu")
								.append('<li><a href="news">Tin tức </a></li>'
									+'<li><a href="admin_medicineManagement" id="current">Quản lý thuốc</a>'
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
														+ '<li><a href="RepresentativeInfor" class="admin" id="current">Nhà thuốc của bạn</a></li>');

							}
						}
					});
</script>
<script type="text/javascript">
function pharmayInfor(pharId) {
	document.myForm.action = "pharmacyDetail";
	document.myForm.pharmacyId.value = pharId;
	document.forms["myForm"].submit();
}
</script>
<title>Thay đổi mật khẩu</title>
</head>
<sj:head/>
<body>
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
				<li><a href="home">Trang chủ</a></li>
				<li><a href="search_pharmacyInfo">Tìm nhà thuốc</a></li>
				<li class="inner"><a href="search_nearestPharmacy">Nhà
						thuốc gần nhất</a></li>
			</ul>
		</div>
	</div>
	<div id="page_title" style="margin-top: -50px;">
		<ul>
			<li><a href="ChangePassword">Thay đổi mật khẩu</a></li>
			<li><img src="css/images/arrow.png" /></li>
		</ul>
	</div>
	<div id="change_password_container">
		<!-- <div id="AdministrationPharmacy"> -->
		<div id="about-us">
			<form method="post" class="signin" action="thay-doi-mat-khau"
				theme="simple" acceptcharset="utf-8" id="loginForm" name="loginForm">
				<s:hidden name="changeFail" />
				<s:hidden name="success" />
				<s:if test="!success">
				<fieldset class="textbox">
					 <label class="password"> <span id="passwordCss"> Mật Khẩu hiện tại</span> <input
						id="password" name="password" value="" type="password"
						placeholder="Mật khẩu cũ" maxlength="24">
					</label>
					<div class="error" id="wrongPassWord">
						<s:fielderror fieldName="wrongPassword" theme="simple" />
					</div>
					<label class="password"> <span id="passwordCss"> Mật Khẩu mới </span> <input
						id="newPassword" name="newPassword" value="" type="password"
						placeholder="Mật khẩu mới" onkeypress="resetConfirmPassword();" maxlength="24">
					</label>
					<label class="password"> <span id="passwordCss"> Nhập lại mật khẩu mới </span> <input
						id="retypePassword" name="retypePassword" value="" type="password"
						placeholder="Nhập lại mật khẩu mới" maxlength="24">
					</label>
					<button class="smButton" type="submit" id="loginButton">Thay đổi mật khẩu</button>
				</fieldset>
				</s:if>
				<s:if test="success == true">
					<div class="notice searchValid"><s:label>Bạn đã thay đổi mật khẩu thành công</s:label></div>
				</s:if>
			</form>
			</div>
</body>
</html>
