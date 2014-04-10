<%--
    Document   : search_Medicines
    Created on : Apr 27, 2013, 10:59:49 PM
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
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/jquery-1.3.2.min.js"></script>
<title>Register New Medicine</title>
</head>
<sj:head />
<script type="text/javascript"
	src="javascript/jquery.textarea-expander.js"></script>
<body>
	<div id="language">
		<div id="notice">
			<a href="pharmacy_info.jsp">Hi: Admin</a> |4 <a href="home.jsp">Đăng
				xuất</a> | <a href="home.jsp">Đổi mật khẩu</a>
		</div>
	</div>
	<div id="banner">
		<div id="banner_container">
			<img id="logo-icon" src="css/images/my-icon.jpg" /> <img
				id="logo-text" src="css/images/my-banner-and-slogan.jpg" />
		</div>
	</div>
	<div id="menu_container">
		<div id="menu">
			<ul>
				<li><a href="home.jsp">Trang chủ</a></li>
				<li><a href="search_medicines.jsp">Tìm kiếm thuốc</a></li>
				<li><a href="search_pharmacyInfo.jsp">Tìm nhà thuốc</a></li>
				<li><a href="search_nearestPharmacy.jsp">Nhà thuốc gần nhất</a></li>
				<li><a href="register.jsp">Đăng ký</a></li>
				<li><a href="#" id="current"> Quản lý thuốc</a>
					<ul>
						<li><a href="admin_registerNewMedicine.jsp"> Thêm mới
								thuốc</a></li>
						<li><a href="admin_medicineManagement.jsp"> Tìm kiếm
								thuốc</a></li>
					</ul></li>
				<li><a href="admin_pharmacyManagement.jsp"> Quản lý hiệu
						thuốc</a></li>
			</ul>
		</div>
	</div>
	<div id="container">
		<div id="page_title">
			<ul>
				<li><a href="admin_medicineManagement.jsp" id="current">
						Quản lý thuốc</a></li>
				<li><img src="css/images/arrow.png" /></li>
				<li><a href="admin_registerNewMedicine.jsp" id="current">
						Thêm mới thuốc</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div>
		<div id="searchMedicines">
			<div id="header_big">Thêm mới thuốc</div>
			ảnh: <s:property value="{Medicine.imgPath}"/>
		</div>
	</div>
	<div id="templatemo_footer">
		Smart Medicine Dictionary Online </br> Phone number:<a href="#">
			04-3736-6491. Fax: 04-3736-6490 </a> </br> Email:<a href="#">
			info@dantri.com.vn. Website: http://www.dantri.com.vn </a> </br> or Email:<a
			href="#"> info@dantri.com.vn. Website: http://www.dantri.com.vn </a>
		<br /> Adress: <a href="#">8 Ton That Thuyet, Ha Noi, Viet Nam</a> </br>
		Copyright<a href="#"> Nhom Do An Tot Nghiep 5B</a>
	</div>

</body>
</html>
