<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="javascript/pagination.js"></script>
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/login.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<script src="javascript/js-image-slider.js" type="text/javascript"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/dynamic-list.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?key=AIzaSyCt_RpfrVUrVs74uD1VwcXgR3xK64S56DM&sensor=false"></script>
<script type="text/javascript">
	var userGroup1;
	var userGroup2;
	$(document).ready(function() {
		userGroup1 = "<s:property value="#session['userGroup1'].email"/>";
		userGroup2 = "<s:property value="#session['userGroup2'].email"/>";
		if(!userGroup1 && !userGroup2) {
			document.getElementById("language").removeAttribute("style");
			document.getElementById("language").style.display = "none";
			$("#ulMenu").append('<li><a href="aboutUs">Giới thiệu</a></li>');
			$("#ulMenu").append('<li><a href="Login">Đăng nhập</a></li>');
		} else {
			document.getElementById("language").removeAttribute("style");
			document.getElementById("language").style.visibility = "visible";
			if (userGroup1) {
				$('#userLogin').text(userGroup1);
				/* $("#ulMenu").append('<li><a href="admin_medicineManagement.jsp">Quản trị</a></li>'); */
				$("#ulMenu").append('<li><a href="admin_medicineManagement.jsp">Quản lý thuốc</a>'
						+ '<ul>'
						+	'<li><a href="admin_registerNewMedicine.jsp"> Thêm mới'
						+			' thuốc</a></li>'
						+ '<li><a href="admin_medicineManagement.jsp"> Tìm kiếm'
						+ ' thuốc</a></li>'
						+ '</ul></li>'
					    + '<li><a href="admin_pharmacyManagement.jsp"> Quản lý hiệu'
						+	'thuốc</a></li>');
			} else {
				$('#userLogin').text(userGroup2);
				$("#ulMenu").append('<li><a href="aboutUs">Giới thiệu</a></li>');
				$("#ulMenu").append('<li><a href="RepresentativeInfor" id="current">Hiệu thuốc của bạn</a></li>');
			}
		}
	});

</script>
<script type="text/javascript">
	function submitAction() {
		document.paginationForm.action = "danh-muc-thuoc-action";
		document.forms["paginationForm"].submit();
	}
</script>
<title>Trang chủ</title>
</head>
<sj:head/>
<body>
	<div id="language">
		<div id="notice">
			<span>Xin chào </span> <a href="pharmacy_info.jsp"><span id="userLogin" ></span></a> | <a href="logout">Đăng
				xuất</a> | <a href="home.jsp">Đổi mật khẩu</a>
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
				<li><a href="search_medicines">Tìm kiếm thuốc</a></li>
				<li><a href="search_pharmacyInfo">Tìm nhà thuốc</a></li>
				<li><a href="search_nearestPharmacy">Nhà thuốc gần nhất</a></li>
				<li><a href="register">Đăng ký</a></li>
				<!-- <li><a href="aboutUs">Giới thiệu</a></li> -->
			</ul>
		</div>
	</div>
	<s:form action="danh-muc-thuoc" accept-charset="utf-8" method="post" theme="simple" name="paginationForm">
	<div id="container">
		<s:hidden name="noResult" />
		<s:hidden name="search" />
		<s:hidden name="detail" />
		<s:hidden name="medId" />
		<s:hidden name="success" />
		<s:hidden name="pharmacyId" id="pharmacyId" />
		<s:url var="remoteurl" action="selectTagAction" />
		<div id="page_title">
				<sj:select href="%{remoteurl}" label="Loại thuốc" name="keyWordMedTypeName"
					list="medTypes" listKey="medTypeId" listValue="medTypeName"
					headerKey="-1" headerValue="---Xin hãy chọn loại thuốc---"
					cssClass="selectbox smiddle" onchange="submitAction();" />
			</div>

		<s:if test="search == true">
			<hr class="line" />
			<s:if test="medicineList.size() > 0">
				<div id="header_big">Kết quả tìm kiếm</div>
					<%@ include file="pagination.jsp"%>
				<table id="visible">
					<tr>
						<th><s:checkbox name="checkAll" theme="simple"  id="checkAll" value="false"/> </th>
						<th
							class="sortable<s:if test="#attr.pagination.sortColumn.equals('MEDICINE_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
							<a href="#" onclick="fnPagination(6,'MEDICINE_NAME');">Tên
								thuốc</a>
						</th>
						<th
							class="sortable <s:if test="#attr.pagination.sortColumn.equals('INGREDIENTS')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
							<a href="#" onclick="fnPagination(6,'INGREDIENTS');">Hoạt
								Chất Chính </a>
						</th>
						<th
							class="sortable <s:if test="#attr.pagination.sortColumn.equals('TYPE_OF_PACKAGE_NAME')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
							<a href="#" onclick="fnPagination(6,'TYPE_OF_PACKAGE_NAME');">Loại
								thuốc</a>
						</th>
					</tr>
					<s:iterator value="#attr.medicineList" var="medicine" status="rowstatus">
						<s:if test="#rowstatus.even == true">
							<tr class="ac_odd">
						</s:if>
						<s:else>
							<tr>
						</s:else>
						<td><s:checkbox name="checkBox" fieldValue="%{medId}" theme="simple" id="checkBox[%{medId}]" cssClass="checkBox"/></td>
						<td><s:a href="javascript:submitAction(%{medId});">
								<s:property value="medName" />
							</s:a></td>
						<td><s:property value="ingredients" /></td>
						<td><s:property value="typeOfPackageName" /></td>
						</tr>
					</s:iterator>
				</table>
			</s:if>
			<s:elseif test="noResult == true">
				<s:label>Không tìm thấy kết quả tương ứng với: </s:label>
				<s:property value="inputMedName" />
			</s:elseif>
		</s:if>
		<s:if test="detail == true">
		<%-- <s:if test="detail == true"> --%>
			<hr class="line" />
			<table>
				<tr>
					<div id="header_big">Kết quả chi tiết</div>
				</tr>
				<tr>
					<div id="dynamic-list">
						<dl class="nav2">
							<dt id="dt1">
								<a class="header_small2">Tên thuốc</a>
								<div id="arrowDown" style="display: none;" class="dt1_d"></div>
								<div id="arrowUp" class="dt1_u"></div>
							</dt>
							<dd id="dd1">
								<ul>
									<li><s:property value="%{medDetail.medName}" /></li>

								</ul>
							</dd>
							<dt id="dt2">
								<a class="header_small2">Loại thuốc</a>
								<div id="arrowDown" class="dt2_d"></div>
								<div id="arrowUp" class="dt2_u" style="display: none;"></div>
							</dt>
							<dd id="dd2">
								<ul>
									<li><s:property value="%{medDetail.typeOfPackageName}" /></li>
								</ul>
							</dd>
							<dt id="dt3">
								<a class="header_small2">Hoạt Chất Chính</a>
								<div id="arrowDown" class="dt3_d"></div>
								<div id="arrowUp" class="dt3_u" style="display: none;"></div>
							</dt>
							<dd id="dd3">
								<ul>
									<li><s:property value="%{medDetail.ingredients}" /></li>

								</ul>
							</dd>
							<dt id="dt4">
								<a class="header_small2">Danh sách nhà thuốc đang bán</a>
								<div id="arrowDown" class="dt4_d"></div>
								<div id="arrowUp" class="dt4_u" style="display: none;"></div>
							</dt>
							<dd id="dd4">
								<ul>
									<li id="ten">Tên: <a href="#">Nhà thuốc 1</a></li>
									<li id="diachi">Địa chỉ: địa chỉ 1</li>
								</ul>
							</dd>
							<dt id="dt5" class="header_small2">
								<a>Kết quả gần giống</a>
								<div id="arrowDown" class="dt5_d"></div>
								<div id="arrowUp" class="dt5_u" style="display: none;"></div>
							</dt>
							<dd id="dd5">
								<ul>
									<li><a href="#">Medicine1</a></li>
									<li><a href="#">Medicine2</a></li>
								</ul>
							</dd>
						</dl>
					</div>
				</tr>
			</table>
			<s:submit name="add" value="Thêm vào danh mục thuốc" theme="simple" cssClass="smButton searchButton" action="them-vao-danh-muc-thuoc" />
		</s:if>
		<br/>
		<br/>
		<br/>
		<s:if test="success == true">
			<s:label>Bạn đã thêm vào danh mục thuốc thành công</s:label>
		</s:if>
	</div>
	</s:form>
</body>
</html>
