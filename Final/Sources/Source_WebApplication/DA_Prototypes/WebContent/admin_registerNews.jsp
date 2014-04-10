<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/pagination.js"></script>
<script type="text/javascript" src="javascript/paging_news.js"></script>
<script type="text/javascript" src="javascript/NextPagination.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="javascript/jquery-1.3.2.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/AdministrationPharmacy.css">
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
.notice {
	margin: 20px auto -38px 25px;
	font-size: 14px;
	font-style: italic;
	height: 60px;
	padding: 22px 0 0 60px;
}
#invisible {
 width: 930px !important;
}

#imgPath{
	margin-right: 70%;
}

#imgPath .error{
	border: none !important;
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
													+ '<li><a href="news">Tin tức </a></li>'
													+ '<li><a href="Login">Đăng nhập</a></li>');
						} else {
							document.getElementById("language")
									.removeAttribute("style");
							document.getElementById("language").style.visibility = "visible";
							if (userGroup1) {
								$('.inner')
										.after('<li><a href="news" >Tin tức </a></li>'
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
														+ '<li><a href="admin_news" id="current"> Quản lý tin '
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
	/**
	 * Basic jQuery Validation Form Demo Code
	 * Copyright Sam Deering 2012
	 * Licence: http://www.jquery4u.com/license/
	 */
	(function($, W, D) {
		var JQUERY4U = {};

		JQUERY4U.UTIL = {
			setupFormValidation : function() {
					$("#paginationForm")
							.validate(
									{
										rules : {
											title : {
												required : true
											},
											subContent : {
												required : true
											},
											content : {
												required : true
											},
											author : {
												required : true
											},
											searchTitle : {
												maxLength: 10
											},
											imgPath : {
												required: true
											}
										},
										messages : {
											title : "Mục này bắt buộc phải nhập",
											subContent : {
												required : "Mục này bắt buộc phải nhập",
											},
											content : {
												required : "Mục này bắt buộc phải nhập",
											},
											author : {
												required : "Mục này bắt buộc phải nhập",
											},
											searchTitle : {
												maxLength: "Vuợt quá số lượng ký tự cho phép"
											},
											imgPath : {
												required: "Mục này bắt buộc phải nhập"
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
			$("#content").keyup(function(e) {
			if ((e.keyCode || e.which) == 13) {
				$("#content").append("\n");
			}
		});
});
</script>
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="javascript/jquery.textarea-expander.js"></script>
	<script type="text/javascript" src="javascript/menu.js"></script>
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
	<div id="container">
		<div id="page_title">
			<ul>
				<li><a href="admin_news.jsp" id="current">
						Quản lý tin tức</a></li>
				<li><img src="css/images/arrow.png" /></li>
				<li><a href="admin_registerNews.jsp" id="current">
						Thêm mới tin tức</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div>
		<!-- <div id="searchMedicines"> -->
			<!-- <div id="header_big">Thêm mới tin tức</div> -->
			<s:form theme="simple" action="them-moi-tin-tuc"
				enctype="multipart/form-data" name="paginationForm" id="paginationForm">
				<s:hidden name="actionSuccess" />
				<s:hidden name="page" value="admin_registerNews.jsp" />
				<s:hidden name="newsDetailId" id="newsDetailId" />
				<s:hidden name="smdEmail" value="%{#session['smdEmail']}" />
				<s:if test="!actionSuccess">
					<table id="invisible">
						<tr>
							<td><s:div id="s_text" cssClass="header_small2 required" >File ảnh:</s:div></td>
							<td><div id="upload">
									<s:file name="imgPath" id="imgPath" accept=".JPG,.PNG,.BMP,.JPEG"/>
								</div></div><s:textarea name="imgDesc" cssClass="expand40-180" placeholder="Mô tả về bức ảnh" maxlength="300" cols="95"/></td>
							<td></td>
						</tr>
						<tr>
							<td><s:div id="s_text" cssClass="header_small2 required">Ký giả:</s:div></td>
							<td><s:textarea value="%{newsDetail.author}" name="author"
									cols="95" id="author" cssClass="expand40-180" maxlength="150"></s:textarea></td>
							<td></td>
						</tr>
						<tr>
							<td><s:div id="s_text" cssClass="header_small2 required">Tên tiêu đề:</s:div></td>
							<td><s:textarea value="%{newsDetail.title}" name="title"
									cols="95" id="title" cssClass="expand40-180" maxlength="150"></s:textarea></td>
							<td></td>
						</tr>
						<tr>
							<td><s:div id="s_text" cssClass="header_small2 required">Trích dẫn:</s:div></td>
							<td><s:textarea value="%{newsDetail.subContent}"
									name="subContent" cols="95" id="subContent"
									cssClass="expand40-180" maxlength="500"></s:textarea></td>
							<td></td>
						</tr>
						<tr>
							<td><s:div id="s_text" cssClass="header_small2 required">Nội dung:</s:div></td>
							<td><s:textarea value="%{newsDetail.content}" name="content"
									cols="95" id="content" cssClass="expand40-180" maxlength="5000">
								</s:textarea></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td><s:submit style="margin-left: 2px;"
									action="them-moi-tin-tuc" value="Thêm mới"
									cssClass="smButton greenButton" /></td>
						</tr>
					</table>
				</s:if>
				<s:if test="actionSuccess == true">
					<div class="notice searchValid">
						<s:property value="message" />
					</div>
					<div id="dynamic-list" class="bs2">
						<table>
							<tr>
								<td>
									<table id="left-of-dynamic">
										<tr>
											<td class="limited"><a class="header_small2">Ngày tạo:</a></td>
											<td><s:property value="%{news.regDateStr}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Ngày chỉnh sửa: </a></td>
											<td><s:property value="%{news.modDateStr}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Người tạo: </a></td>
											<td><s:property value="%{news.regUser}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Người chỉnh sửa: </a></td>
											<td><s:property value="%{news.modUser}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Ký giả:</a></td>
											<td><s:property value="%{news.author}" /></td>
										</tr>
									</table>
								</td>
								<s:if test="imgPath != null">
								<td><img
									src="News_Images/<s:property value="%{news.imgPath}" />"
									style="height: 160px; width: 240px;" /></td>
								</s:if>
							</tr>
						</table>
					<hr class="line" />
					<dl class="nav2">
						<dt onclick="makeDynamic(5)">
								<a class="header_small2">Tên tiêu đề:</a>
								<div id="arrowDown" class="d_dt5" style="display: none;"></div>
								<div id="arrowUp" class="u_dt5"></div>
							</dt>
							<dd id="dd5">
								<ul>
									<li><s:property value="%{news.title}" /></li>

								</ul>
							</dd>
							<dt onclick="makeDynamic(6)">
								<a class="header_small2">Trích dẫn:</a>
								<div id="arrowDown" class="d_dt6"></div>
								<div id="arrowUp" class="u_dt6" style="display: none;"></div>
							</dt>
							<dd id="dd6">
								<ul>
									<li><s:property value="%{news.subContent}" /></li>

								</ul>
							</dd>
							<dt onclick="makeDynamic(7)">
								<a class="header_small2">Nội dung:</a>
								<div id="arrowDown" class="d_dt7"></div>
								<div id="arrowUp" class="u_dt7" style="display: none;"></div>
							</dt>
							<dd id="dd7">
								<ul>
									<li><s:iterator value="news.arrayContent">
											<s:property />
											<br/>
										</s:iterator></li>
								</ul>
							</dd>
					</dl>
					<s:submit style="margin-left: 2px;"
									action="admin_registerNews" value="Quay lại"
									cssClass="smButton greenButton" />
							<s:submit style="margin-left: 2px;" action="ban-sao-moi"
									value="Bản sao mới" cssClass="smButton greenButton" />
					</div>
				</s:if>
			</s:form>
		<!-- </div> -->
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