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
<link rel="stylesheet" type="text/css" href="css/home.css">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/login.js"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<style type="text/css">
#highlights_post {
	width: 335px !important;
}
</style>
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
													+ '<li><a href="news" id="current">Tin tức </a></li>'
													+ '<li><a href="Login">Đăng nhập</a></li>');
						} else {
							document.getElementById("language")
									.removeAttribute("style");
							document.getElementById("language").style.visibility = "visible";
							if (userGroup1) {
								$('.inner')
										.after(
												'<li><a href="news" id="current">Tin tức </a></li>'
														+ '<li><a href="admin_medicineManagement" class="admin">Quản lý thuốc</a>'
														+ '<ul>'
														+ '<li><a href="admin_registerNewMedicine"> Thêm mới'
														+ ' thuốc</a></li>'
														+ '<li><a href="admin_medicineManagement"> Tìm kiếm'
														+ ' thuốc</a></li>'
														+ '<li><a href="admin_acceptMedicine"> Kiểm duyệt'
														+ ' thuốc</a></li>'
														+ '</ul></li>'
														+ '<li><a href="admin_pharmacyManagement" class="admin"> Quản lý nhà thuốc</a></li>'
														+ '<li><a href="admin_news.jsp"> Quản lý tin '
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
														+ '<li><a href="news" id="current">Tin tức </a></li>'
														+ '<li><a href="RepresentativeInfor" class="admin">Nhà thuốc của bạn</a></li>');

							}
						}
					});
</script>
<script type="text/javascript">
	function detailNews(newsId) {
		document.myForm.action = "tin-tuc-chi-tiet";
		document.myForm.newsId.value = newsId;
		document.forms["myForm"].submit();
	}
</script>
<title>Tin tức</title>
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
			<ul id="ulMenu">
				<li><a href="home">Trang chủ</a></li>
				<li><a href="search_pharmacyInfo">Tìm nhà thuốc</a></li>
				<li class="inner"><a href="search_nearestPharmacy">Nhà
						thuốc gần nhất</a></li>
			</ul>
		</div>
	</div>
	<s:form action="tin-tuc-chi-tiet" name="myForm">
	<s:hidden name="newsId" />
	<div id="container" style="min-height: 1150px;">
		<div id="detail-news">
			<div id="posted-date">
				Được cập nhật vào <s:property value="%{news.dayName}"/> <s:property value="%{news.regDateStr}" />
			</div>
			<div class="news-title1">
				<s:property value="%{news.title}" />
			</div>
			<div id="detail-news-header">
				 <s:property value="%{news.subContent}" />
			</div>
			<div id="details-img">
				<img src="News_Images/<s:property value="%{news.imgPath}" />" />
			</div>
			<div id="details-des">
				<s:property value="%{news.imgDesc}" />
			</div>
			<span class="details-cont"> <s:iterator value="news.arrayContent">
					<s:property />
				</s:iterator>
			</span>
			<div id="detail-author">
				<s:property value="%{news.author}" />
			</div>
		</div>
		<div id="detail-QC">
			<div id="highlights_post">
				<s:iterator  value="otherNewsList">
				<li><s:a href="javascript:detailNews(%{id});"><s:property value="title"/></s:a> </li>
				</s:iterator>
			</div>
			<s:iterator value="adLineTwo">
					<div id="adv-item">
						<div id="adv-title">
							<a href="http://<s:property value="link" />" target="_blank"><s:property
									value="title" /> </a>
						</div>
						<div id="adv-link">
							<a href="http://<s:property value="link" />" target="_blank"><s:property
									value="link" /> </a>
						</div>
						<div id="adv-img">
							<a href="http://<s:property value="link" />" target="_blank"><img
								src="Advertise_image/<s:property value="imgPath" />" /></a>
						</div>
						<div id="adv-content">
							<a href="http://<s:property value="link" />" target="_blank"><s:property
									value="content" /> </a>
						</div>
					</div>
					<div id="token"></div>
				</s:iterator>
		</div>
	</div>
	</s:form>
</body>
</html>

