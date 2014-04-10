<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
<script type="text/javascript" src="javascript/footer.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
<title>Cấp lại mật khẩu</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginButton").click(function () {
			document.getElementById("wrongPassWord").style.display = "none";
		});
		$("#receiption").keyup(function () {
			document.getElementById("wrongEmail").style.display = "none";
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
					$("#myForm")
							.validate(
									{
										rules : {
											receiption : {
												required : true,
												email : true,
												notSpecialCharacter: true
											}
										},
										messages : {
											receiption : {
												required : "Mục này bắt buộc phải nhập",
												email : "Xin vui lòng nhập đúng định dạng email"
											}
											
										},
										submitHandler : function(form) {
											form.submit();
										}
									});
					$.validator.addMethod("notSpecialCharacter", function(value,
							element, arg) {
						return /^[A-Za-z\d@_ ]+/.test(value);
					}, "Email không được chưa ký tự đặc biệt");
			}
		}

		//when the dom has loaded setup form validation rules
		$(D).ready(function($) {
				JQUERY4U.UTIL.setupFormValidation();
		});

	})(jQuery, window, document);
</script>
</head>
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
	<div id="reset_password_container">
		<div id="about-us">
			<s:hidden name="success" id="success" />
			
				<form method="post" class="signin" action="resetPasswordAction" accept-charset="utf-8" name="myForm" id="myForm">
				<s:if test="!success">
					<fieldset class="textbox">
					<label class="username"><span id="passwordCss">Email</span><s:textfield id="receiption"
						name="receiption" 
						placeholder="Email của bạn" maxlength="150" />
					</label>
					<div class="error" id="wrongEmail">
						<s:fielderror fieldName="wrongEmail" theme="simple" />
					</div>
					<button class="smButton" type="submit" id="loginButton">Cấp lại mật khẩu</button>
				</fieldset>
				</s:if>
				<s:else>
					<div class="notice-rs">
					<s:label>Cấp lại mật khẩu thành công!</s:label>
					<br/>
					<s:label>Bạn vui lòng kiểm tra hộp thư đến để nhận lại mật khẩu</s:label>
					<br/>
					<s:label>Lưu ý: Bạn nên thay đổi mật khẩu sau khi đăng nhập!</s:label>
					</div>
				</s:else>
				</form>
		</div>
	</div>
</body>
</html>

