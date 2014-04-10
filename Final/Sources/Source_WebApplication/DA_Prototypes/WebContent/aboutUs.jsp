<%--
    Document   : index
    Created on : Apr 26, 2013, 1:43:50 AM
    Author     : W7
    
    tao breadcumb = background img va` margin -
--%>

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
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<%-- <script type="text/javascript" src="javascript/check_browser_close.js"></script> --%>
<script type="text/javascript" src="javascript/login.js"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
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
													+ '<li><a href="aboutUs" id="current">Giới thiệu</a></li>'
													+ '<li><a href="news">Tin tức </a></li>'
													+ '<li><a href="Login">Đăng nhập</a></li>');
						} else {
							document.getElementById("language")
									.removeAttribute("style");
							document.getElementById("language").style.visibility = "visible";
							if (userGroup1) {
								$('.inner')
										.after(
												'<li><a href="admin_medicineManagement.jsp" class="admin">Quản lý thuốc</a>'
														+ '<ul>'
														+ '<li><a href="admin_registerNewMedicine.jsp"> Thêm mới'
														+ ' thuốc</a></li>'
														+ '<li><a href="admin_medicineManagement.jsp"> Tìm kiếm'
														+ ' thuốc</a></li>'
														+ '</ul></li>'
														+ '<li><a href="admin_pharmacyManagement.jsp" class="admin"> Quản lý hiệu thuốc</a></li>'
														+ '<li><a href="news" class="admin">Tin tức</a></li>');

							} else {
								$(".inner")
										.after(
												'<li><a href="register">Đăng ký</a></li>'
														+ '<li><a href="aboutUs" id="current">Giới thiệu</a></li>'
														+ '<li><a href="news">Tin tức </a></li>'
														+ '<li><a href="RepresentativeInfor" class="admin">Nhà thuốc của bạn</a></li>');

							}
						}
					});
</script>
<title>Giới Thiệu</title>
</head>
<body>
	<div id="language">
		<div id="notice">
			<span>Xin chào </span> <s:property
					value="#session['userName']" /> | <a href="logout">Đăng xuất</a> | <a
				href="ChangePassword">Đổi mật khẩu</a>
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
	<div id="container">

		<div id="about-us">
			<div id="title">
				<div class="header">Từ điển thuốc thông minh</div>
				<div class="explain">Được tạo bởi nhóm đồ án tốt nghiệp trường đại học FPT</div>
				<div class="explain">Ngày 15 tháng 8 năm 2013</div>
			</div>
			<div id="abu-content">
				<div>Từ điển thuốc thông minh là một website được xây dựng dựa trên ý
					tưởng về một cuốn từ điển y dược cho phép người dùng có thể
					 tra cứu thông tin về thuốc một cách nhanh chóng và thuận tiện thông
					  qua internet, không những vậy thông qua website người dùng còn có 
					  thể tìm kiếm thông tin về các cửa hàng thuốc có bán loại thuốc mà người dùng
					  quan tâm.
				<img src="css/images/aboutUs_Main.jpg"
					style="width: 37em; height: 20em; padding: 1em 0 1em 14em;" /> </br>
					</br></br>
					  Các hiệu thuốc cũng có thể đăng ký tài khoản trên website để có thể
					  đăng ký thông tin của cửa hàng lên trang web.</div>
			</div>
		</div>
	</div>
</body>
</html>

