
<%-- 
    Document   : search_Medicines
    Created on : Apr 27, 2013, 10:59:49 PM
    Author     : W7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8; no-cache">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/pagination.js"></script>
<script type="text/javascript" src="javascript/paging_news.js"></script>
<script type="text/javascript" src="javascript/NextPagination.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<script type="text/javascript" src="javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="javascript/calendar.js"></script>
<link rel="stylesheet" type="text/css" href="css/calendar.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
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
										.after( '<li><a href="news" >Tin tức </a></li>'
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
												required : true
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
function submitAction(newsId) {
	document.paginationForm.action = "chi-tiet-ve-tin-tuc";
	document.paginationForm.newsDetailId.value = newsId;
	document.forms["paginationForm"].submit();
}
function deleteNews(newsId) {
	var result = confirm("Bạn có muốn xóa bỏ tin tức này?");
	if(result == true) {
		document.paginationForm.action = "xoa-tin-tuc";
		document.paginationForm.newsDetailId.value = newsId;
		document.forms["paginationForm"].submit();
	} else {
		return false;
	}
}
function copyNews(newsId) {
	document.paginationForm.action = "ban-sao-moi";
	document.paginationForm.newsDetailId.value = newsId;
	document.forms["paginationForm"].submit();
}
function restore(newsId) {
	document.paginationForm.action = "khoi-phuc-tin-tuc";
	document.paginationForm.newsDetailId.value = newsId;
	document.forms["paginationForm"].submit();
}
</script>
<script type="text/javascript">
	$(document).ready(function() {
				$("#txtInput1").keyup(function(e) {
				if ((e.keyCode || e.which) == 13) {
					$("#txtInput1").append("\n");
					//document.getElementById("txtInput1").value = $("#txtInput1").val() + '\n';
				}
			});
				$('#avatarImage').change(function(e) {
					var ext = $(this).val().split('.').pop().toLowerCase();
					if (this.files[0].size > 1000000) {
						alert('Ảnh của bạn vượt quá dung lượng cho phép');
						$(this).val(null);
						e.preventDefault();
					}
					if ($.inArray(ext, [ 'bmp', 'jpeg', 'jpg', 'png' ]) == -1) {
						alert('Định dạng file ảnh của bạn không được cho phép');
						e.preventDefault();
						$(this).val(null);
					}
				});
	});
</script>
<title>Quản lý tin tức</title>
</head>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript"
	src="javascript/jquery.textarea-expander.js"></script>
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
		<!-- <div id="searchMedicines"> -->
		<div id="page_title">
			<ul>
				<li><a href="admin_news.jsp"> Quản lý tin tức</a></li>
				<li><img src="css/images/arrow.png" /></li>
				<li><a href="admin_news.jsp"> Tìm kiếm tin tức</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div>
			<s:hidden name="searched" />
			<s:hidden name="detailed" />
			<s:url var="remoteurl" action="relatedSelectAction" />
			<s:form theme="simple" action="tim-kiem-tin-tuc"
				enctype="multipart/form-data" name="paginationForm" id="paginationForm">
				<s:hidden name="page" value="admin_news.jsp" />
				<s:hidden name="newsDetailId" id="newsDetailId" />
				<s:hidden name="actionSuccess" />
				<s:hidden name="copyNews" />
				<s:hidden name="countPage" />
				<s:hidden name="smdEmail" value="%{#session['smdEmail']}" />
				<!-- <div id="header_big">Tiêu chí tìm kiếm</div> -->
				<table id="invisible">
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Tiêu đề:</s:div>
						</td>
						<td><s:textfield type="text" name="searchTitle"
								cssClass="textbox middle" maxlength="150" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Nội dung:</s:div></td>
						<td><s:textfield type="text" name="searchContent"
								cssClass="textbox middle" /></td>
					</tr>
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Ký giả:</s:div></td>
						<td><s:textfield type="text" name="searchAuthor"
								cssClass="textbox middle" maxlength="150" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Ngày tạo:</s:div>
						</td>
						<td><s:textfield type="text" name="searchRegDate"
								cssClass="textbox middle" placeholder="ngày/tháng/năm hoặc ngày-tháng-năm" maxlength="10"/>
						</td>
					</tr>
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Đánh dấu xoá:</s:div></td>
						<td><s:checkbox name="deleteFlagBoolean"></s:checkbox></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td><s:submit name="search" value="Tìm kiếm" id="home_submit"
								cssClass="smButton searchButton"
								action="tim-kiem-tin-tuc-action" theme="simple" /></td>
					</tr>
				</table>
				<div>
				</div>
				<s:if test="actionSuccess == true">
					<div class="notice searchValid"><s:property value="message"/></div>
				</s:if>
				<s:if test="searched == true">
					<s:if test="newsList.size() > 0">
						<div id="header_big">Kết quả tìm kiếm</div>
						<%@ include file="pagination.jsp"%>
						<table id="visible">
								<tr>
									<th><a> Hành động</a></th>
									<th
										class="sortable<s:if test="#attr.pagination.sortColumn.equals('TITLE')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
										<a href="#" onclick="fnPagination(6,'TITLE');">Tên tiêu đề</a>
									</th>
									<th
										class="sortable <s:if test="#attr.pagination.sortColumn.equals('AUTHOR')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
										<a href="#" onclick="fnPagination(6,'AUTHOR');">Ký giả</a>
									</th>
									<th><a>Ngày tạo</a></th>
									<%-- <th
										class="sortable <s:if test="#attr.pagination.sortColumn.equals('REG_DATE')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
										<a href="#" onclick="fnPagination(6,'REG_DATE');">Ngày tạo</a>
									</th> --%>
									<th
										class="sortable <s:if test="#attr.pagination.sortColumn.equals('SUB_CONTENT')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
										<a href="#" onclick="fnPagination(6,'SUB_CONTENT');">Trích dẫn</a>
									</th>
								</tr>
								<s:iterator value="#attr.newsList" var="news"
									status="rowstatus">
									<s:if test="#rowstatus.even == true">
										<tr class="ac_odd">
									</s:if>
									<s:else>
										<tr>
									</s:else>
									<s:if test="!deleteFlagBoolean">
										<td class="limited"><s:a href="javascript:submitAction(%{id});">Sửa</s:a> | 
										<s:a href="javascript:copyNews(%{id})">Bản sao mới</s:a>
										</td>
									</s:if>
									<s:if test="deleteFlagBoolean == true">
										<td><s:a href="javascript:restore(%{id});">Khôi phục</s:a></td>
									</s:if>
								<td><s:property value="title" /></td>
								<td><s:property value="author" /></td>
									<td><s:property value="searchRedate" /></td>
									<td style="min-width:370px;"><s:property value="subContent" /></td>
									</tr>
								</s:iterator>
							</table>
					</s:if>
					<s:else>
					<div class="notice searchInvalid">Xin lỗi, không tìm thấy kết
						quả tìm kiếm với từ khoá bạn nhập!</div>
				</s:else>
				</s:if>
				<s:if test="detailed == true || copyNews == true">
					<div id="header_big">Chỉnh sửa tin tức</div>
					<table id="invisible">
						<s:if test="detailed == true">
							<tr>
								<td><s:div id="s_text" cssClass="header_small2">Ngày tạo:</s:div></td>
								<td><s:property value="%{newsDetail.regDateStr}" /></td>
								<td></td>
							</tr>
							<tr>
								<td><s:div id="s_text" cssClass="header_small2">Ngày chỉnh sửa:</s:div></td>
								<td><s:property value="%{newsDetail.modDateStr}" /></td>
								<td></td>
							</tr>
							<tr>
								<td><s:div id="s_text" cssClass="header_small2">Người tạo:</s:div></td>
								<td><s:property value="%{newsDetail.regUser}" /></td>
								<td></td>
							</tr>
							<tr>
								<td><s:div id="s_text" cssClass="header_small2">Người chỉnh sửa:</s:div></td>
								<td><s:property value="%{newsDetail.modUser}" /></td>
								<td></td>
							</tr>
						</s:if>
						<tr>
							<td><s:div id="s_text" cssClass="header_small2 required">File ảnh:</s:div></td>
							<td><div id="upload"><s:file name="imgPath" id="imgPath" /></div>
							<s:textarea name="imgDesc" cssClass="expand40-180" placeholder="Mô tả về bức ảnh" maxlength="300" cols="95" value="%{newsDetail.imgDesc}"/></td>
							<td></td>
						</tr>
						<tr>
							<td><s:div id="s_text" cssClass="header_small2 required">Ký giả:</s:div></td>
							<td><s:textarea value="%{newsDetail.author}"
									name="author" cols="95" id="txtInput"
									cssClass="expand40-180" maxlength="200"></s:textarea></td>
							<td></td>
						</tr>
						<tr>
							<td><s:div id="s_text" cssClass="header_small2 required">Tên tiêu đề:</s:div></td>
							<td><s:textarea value="%{newsDetail.title}"
									name="title" cols="95" id="txtInput"
									cssClass="expand40-180" maxlength="150"></s:textarea></td>
							<td></td>
						</tr>
						<tr>
							<td><s:div id="s_text" cssClass="header_small2 required">Trích dẫn:</s:div></td>
							<td><s:textarea value="%{newsDetail.subContent}"
									name="subContent" cols="95" id="txtInput"
									cssClass="expand40-180" maxlength="500"></s:textarea></td>
							<td></td>
						</tr>
						<tr>
							<td><s:div id="s_text" cssClass="header_small2 required">Nội dung:</s:div></td>
							<td><s:textarea value="%{newsDetail.content}" name="content"
									cols="95" id="txtInput1" cssClass="expand40-180" maxlength="5000">
									</s:textarea></td>
							<td></td>
						</tr>
						<s:if test="detailed == true">
						<tr>
							<td></td>
							<td><s:submit style="margin-left: 2px;"
										action="chinh-sua-tin-tuc" value="Lưu lại"
										cssClass="smButton greenButton" /><s:submit
										onclick="deleteNews(%{newsDetailId}); return false;" value="Xoá"
										cssClass="smButton redButton" /><s:submit
										action="huy-bo-chinh-sua" value="Huỷ bỏ"
										cssClass="smButton" /></td>
										<td></td>
						</tr> 
						</s:if>
						<s:else>
							<tr>
								<td></td>
								<td><s:submit style="margin-left: 2px;"
										action="them-moi-tin-tuc" value="Lưu lại"
										cssClass="smButton greenButton" />
								<s:submit action="admin_registerNews" value="Huỷ bỏ"
										cssClass="smButton" /></td>
								<td></td>
							</tr>
						</s:else>
					</table>
				</s:if>
			</s:form>
		<!-- </div> -->
	</div>
	<div id="delete-box" class="login-popup">
		<input type="button" class="close" value="X" id="close" />
		<div id="login_header">Are you sure to delete [....]?</div>
		<div id="login_content">

			<form method="post" class="signin" action="admin_pharmacy.jsp">
				<input class="button close" type="button" value="Delete" /> <input
					class="button close" type="button" value="Cancel" />

			</form>
		</div>
	</div>
	<div id="info-box" class="login-popup">
		<input type="button" class="close" value="X" id="close" />
		<div id="login_header">[....] Infomation</div>
		<div id="login_content">

			<form method="post" class="signin" action="admin_pharmacy.jsp">
				<input class="button close" type="button" value="Ok" />

			</form>
		</div>
	</div>
</body>
</html>
<script>
	$(document).ready(function() {
		$("#0").hide();
		$("#1").show();
		$(".searchButton").click(function() {

			var items = $("input.checkbox");
			for (i = 0; i < items.length; i++)
				items[i].disabled = true;
		});
	});
</script>