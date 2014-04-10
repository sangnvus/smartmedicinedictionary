<%--
    Document   : index
    Created on : Apr 26, 2013, 1:43:50 AM
    Author     : W7
    
    tao breadcumb = background img va` margin -
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/about-us.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/login.js"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/validate.file.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginButton").click(function () {
			document.getElementById("wrongPassWord").style.display = "none";
		});
		$("#password").keyup(function () {
			document.getElementById("wrongPassWord").style.display = "none";
		});
		$('#password').bind('cut copy paste', function(event) {
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
										userName : {
											required : true,
											email : true
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
										userName : {
											required : "Mục này bắt buộc phải nhập",
											email : "Xin vui lòng nhập đúng định dạng email"
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
			}
		}

		//when the dom has loaded setup form validation rules
		$(D).ready(function($) {
			JQUERY4U.UTIL.setupFormValidation();
		});

	})(jQuery, window, document);
</script>
<title>Đăng nhập</title>
</head>
<sj:head />
<script type="text/javascript" src="javascript/footer.js"></script>
<body>
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
				<li><a href="Login" id="current">Đăng nhập</a></li>

			</ul>
		</div>
	</div>
	<div id="login_container">
		<div id="about-us">
			<form method="post" class="signin" action="loginAction"
				theme="simple" acceptcharset="utf-8" id="loginForm" name="loginForm">
				<fieldset class="textbox">
					<label class="username"><span id="passwordCss">Email</span><s:textfield id="userName"
						name="userName" 
						placeholder="Email của bạn" maxlength="150"/>
					</label>
					 <label class="password"> <span id="passwordCss"> Mật Khẩu </span> <input
						id="password" name="password" type="password"
						placeholder="Mật khẩu" maxlength="24">
					</label>
					<div class="error" id="wrongPassWord">
							<s:fielderror fieldName="loginError" theme="simple" />
						</div>
					<button class="smButton" type="submit" id="loginButton">Đăng nhập</button>
					<p>
						<a class="forgot" href="ResetPassword"
							style="font-size: 12px;"> Quên mật khẩu? </a>
					</p>
				</fieldset>

			</form>
		</div>
	</div>
</body>
</html>
