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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8; no-cache">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/pagination.js"></script>
<script type="text/javascript" src="javascript/paging-medicine.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<script type="text/javascript" src="javascript/jquery-1.3.2.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
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
							$("#ulMenu").append(
									'<li><a href="Login">Đăng nhập</a></li>');
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
								$("#ulMenu")
										.append(
												'<li><a href="RepresentativeInfor">Nhà thuốc của bạn</a></li>');
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
											medName : {
												required : true
											},
											manufacturer : {
												required : true
											},
											keyWordMedTypeName1 : {
												required : true
											},
											indications : {
												required : true
											},
											dosingAndUse : {
												required : true
											},
											ingredients : {
												required : true
											}
										},
										messages : {
											medName : "Mục này bắt buộc phải nhập",
											manufacturer : {
												required : "Mục này bắt buộc phải nhập"
											},
											keyWordMedTypeName1 : {
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
	function openPopup() {
		var medTypeLst = $("#medTypeIdList").val();
		var url = "cac-loai-thuoc?medTypeLst=" + medTypeLst;
		var w = 400;
		var h = 200;
		var left = Number((screen.width / 2) - (w / 2));
		var tops = Number((screen.height / 2) - (h / 2));
		window
				.open(
						url,
						'jav',
						'scrollbars=no,location=no,resizable=yes,width=400, height=200,top=0,left=0,top='
								+ tops + ',left=' + left);
	}
	function deleteMed(medId) {
		var result = confirm("Bạn có muốn xóa bỏ thuốc này?");
		if(result == true) {
			document.paginationForm.action = "admin_deleteMed";
			document.paginationForm.medId.value = medId;
			document.forms["paginationForm"].submit();
		} else {
			return false;
		}
	}
	
	function detailMed(medId) {
		document.paginationForm.action = "admin_changeMedInfo";
		document.paginationForm.medId.value = medId;
		document.forms["paginationForm"].submit();
	}
	
	function copyNewMed(medId) {
		document.paginationForm.action = "admin_copyNewMedicine";
		document.paginationForm.medId.value = medId;
		document.forms["paginationForm"].submit();
	}
	
	function restoreMed(medId) {
		document.paginationForm.action = "admin_restoreMedicine";
		document.paginationForm.medId.value = medId;
		document.forms["paginationForm"].submit();
	}
</script>
<title>Medicine Management</title>
</head>
<sj:head />
<script type="text/javascript"
	src="javascript/jquery.textarea-expander.js"></script>
<body>
	<s:hidden name="search" />
	<s:hidden name="detail" />
	<s:hidden name="edit" />
	<s:hidden name="copyNew" />
	<s:hidden name="delete" />
	<s:hidden name="restore" />
	<s:hidden name="actionSucces" />
	<script type="text/javascript" src="javascript/menu.js"></script>
	<div id="language">
		<div id="notice">
			<span>Xin chào </span> <s:property
					value="#session['userName']" /> | <a
				href="logout">Đăng xuất</a> | <a href="ChangePassword">Đổi mật khẩu</a>
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
				<li><a href="search_nearestPharmacy">Nhà thuốc gần nhất</a></li>
			</ul>
		</div>
	</div>
	<div id="container">
		<div id="page_title">
			<ul>
				<li><a href="admin_medicineManagement.jsp"> Quản lý thuốc</a></li>
				<li><img src="css/images/arrow.png" /></li>
				<li><a href="admin_medicineManagement.jsp"> Tìm kiếm thuốc</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div>
		<!-- <div id="searchMedicines"> -->
			<!-- <div id="header_big">Tiêu chí tìm kiếm</div> -->
			<s:form action="admin_searchMedAdvanced" theme="simple" name="paginationForm" enctype="multipart/form-data" id="paginationForm">
			<s:hidden name="medTypeIdList" id="medTypeIdList" />
			<s:hidden name="medId" value="%{medicine.medId}" />
			<s:hidden name="medTypeNameList" id="medTypeNameList" />
			<s:hidden name="page" value="admin_medicineManagement.jsp" />
			<s:hidden name="smdEmail" value="%{#session['smdEmail']}" />
				<table id="invisible">
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Tên thuốc:</s:div>
						</td>
						<td><s:textfield name="keyWordMedName"
								cssClass="textbox middle" maxlength="100" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Nhà sản xuất:</s:div>
						</td>
						<td><s:textfield name="keyWordMedManufac"
								cssClass="textbox middle" maxlength="100" /></td>
					</tr>
					<tr>
						<td><s:div id="s_text"  cssClass="header_small2">Chỉ định/Triệu chứng/Công dụng:</s:div>
						</td>
						<td><s:textfield 
								name="keyWordMedIndication" cssClass="textbox middle" maxlength="2500" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Chống chỉ định:</s:div>
						</td>
						<td><s:textfield
								name="keyWordMedContraindication" cssClass="textbox middle" maxlength="2500"/></td>
					</tr>
					<tr>

						<td><s:div id="s_text" cssClass="header_small2">Loại thuốc:</s:div>
						</td>
						<td>
						<s:url var="remoteurl" action="selectTagAction" /> <sj:select
								href="%{remoteurl}" name="keyWordMedTypeName" list="medTypes"
								listKey="medTypeId" listValue="medTypeName" headerKey="-1"
								headerValue="---Tất cả---" cssClass="selectbox smiddle" />
								</td>
						<td><s:div id="s_text" cssClass="header_small2">Thành phần:</s:div>
						</td>
						<td><s:textfield name="keyWordMedIngredients"
								cssClass="textbox middle" maxlength="2500" /></td>
					</tr>
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Đánh dấu xoá:</s:div></td>
						<td><s:checkbox name="deleteFlagBoolean"></s:checkbox></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td><s:submit action="admin_searchMedAdvanced"
								value="Tìm kiếm" cssClass="cancel smButton"></s:submit></td>
								<!-- cancel smButton-->
						<td></td>
						<td></td>
					</tr>
				</table>
				<s:if test="actionSucces == true">
					<div class="notice searchValid"><s:property value="message"/></div>
				</s:if>
			<s:if test="search">
					<s:if test="medList.size() > 0">
					<div id="header_big">Kết quả tìm kiếm</div>
					<%@ include file="pagination.jsp"%>
						<table id="visible">
							<tr>
								<th><a> Hành động</a></th>
								<th
									class="sortable <s:if test="#attr.pagination.sortColumn.equals('medName')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'MEDICINE_NAME');"> Tên</a>
								</th>
								<th
									class="sortable <s:if test="#attr.pagination.sortColumn.equals('manufacturer')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'MANUFACTURER');"> Nhà sản xuất</a>
								</th>
								<th><a>Loại</a></th>
							</tr>
							<s:iterator value="medList">
								<tr>
									<s:if test="deleteFlagBoolean">
										<td><s:a
												href="javascript:restoreMed(%{medId});">Khôi phục</s:a></td>
									</s:if>
									<s:else>
										<td><s:a href="javascript:detailMed(%{medId});">Sửa</s:a>
											| <s:a href="javascript:copyNewMed(%{medId});">Bản sao mới</s:a></td>
									</s:else>
									<td><s:property value="medName" /></td>
									<td><s:property value="manufacturer" /></td>
									<td><s:property value="typeOfPackageName" /></td>
								</tr>
							</s:iterator>
						</table>
					</s:if>
				<s:else>
					<div class="notice searchInvalid">Xin lỗi, không tìm thấy kết
						quả tìm kiếm với từ khoá bạn nhập!</div>
				</s:else>
			</s:if>
			<s:if test="edit || copyNew">
				<%-- <s:if test="Medicine.medId > 0"> --%>
				<s:if test="edit">
					<div id="header_big">Chỉnh Sửa thông tin thuốc</div>
				</s:if>
				<s:if test="copyNew">
					<div id="header_big">Thêm mới tương tự</div>
				</s:if>
					
						<table id="invisible">
							<tr>
								<td>
									<table style="width: 930px;">
									<s:if test="edit">
										<tr>
											<td></td>
											<td><img
												src="image/medicine/<s:property value="%{Medicine.imgPath}" />"
												style="height: 50px; width: 80px;" /></td>
										</tr>
									</s:if>
									<tr>
											<td><s:div id="s_text" cssClass="header_small2">Ảnh:</s:div></td>
											<td><s:file name="keyWordMedImage" /></td>
											<td><s:div id="s_text" cssClass="header_small2">Tên thuốc gốc:</s:div></td>
											<td><s:textfield type="text" name="genericMedicine"
													value="%{Medicine.genericMedicine}"
													cssClass="textbox middle" maxlength="100"/></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2 required">Tên thuốc:</s:div></td>
											<td><s:textfield type="text" name="medName"
													value="%{Medicine.medName}" cssClass="textbox middle" id="medName" maxlength="100"/></td>
											<td><s:div id="s_text" cssClass="header_small2 required">Nhà sản xuất:</s:div></td>
											<td><s:textfield type="text" name="manufacturer" id="manufacturer"
													value="%{Medicine.manufacturer}" cssClass="textbox middle" maxlength="100"/></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2 required">Loại thuốc:</s:div>
											</td>
											<td onclick="javascript:openPopup();"><s:textarea name="keyWordMedTypeName1"
													value="%{medTypeNameList}" cssClass="textbox middle medTypeButton expand26-180 tahoma"
													id="keyWordMedTypeName1" readonly="true" style="width:283px;"/></td>
											<td><s:div id="s_text" cssClass="header_small2">Tên thương mại:</s:div></td>
											<td><s:textfield type="text" id="brandName"
													value="%{Medicine.brandName}" cssClass="textbox middle"
													name="brandName" maxlength="100" /></td>
										</tr>
									</table>
							</td>
							</tr>
							<tr>
								<td>
									<table id="admin-tab">
										<tr>
											<td><s:div id="s_text" cssClass="header_small2 required">Thành phần:</s:div></td>
											<td><s:textarea value="%{medicine.ingredients}"
													name="ingredients" cols="129" id="ingredients"
													cssClass="expand40-180 tahoma" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2 required">Chỉ định/Triệu chứng/Công dụng:</s:div></td>
											<td><s:textarea value="%{medicine.indications}"
													name="indications" cols="129"
													cssClass="expand60-180 tahoma" id="indications" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Chống chỉ định:</s:div></td>
											<td><s:textarea value="%{medicine.contraindications}"
													name="contraindications" cols="129" id="contraindications"
													cssClass="expand60-180 tahoma" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2 required">Cách dùng và sử dụng:</s:div></td>
											<td><s:textarea value="%{medicine.dosingAndUse}"
													name="dosingAndUse" cols="129" id="dosingAndUse"
													cssClass="expand40-180 tahoma" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Bảo quản:</s:div></td>
											<td><s:textarea value="%{medicine.storage}"
													name="storage" cols="129" id="storage"
													cssClass="expand40-180 tahoma" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Cảnh báo:</s:div></td>
											<td><s:textarea value="%{medicine.warning}"
													name="warning" cols="129" id="warning"
													cssClass="expand40-180 tahoma" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Thuốc tương tự:</s:div></td>
											<td><s:textarea value="%{medicine.similarMedicine}"
													name="similarMedicine" cols="129" id="similarMedicine"
													cssClass="expand40-180 tahoma" maxlength="2500"></s:textarea></td>
										</tr>
										<tr>
											<td><s:div id="s_text" cssClass="header_small2">Thuốc tương tác:</s:div></td>
											<td><s:textarea value="%{medicine.interaction}"
													name="interaction" cols="129" id="interaction"
													cssClass="expand40-180 tahoma" maxlength="2500"></s:textarea></td>
										</tr>
									</table>
							</td>
							</tr>
							<tr>
								<s:if test="copyNew">
									<td>
											<s:submit style="margin-left: 105px;"
										action="admin_addNewMed" value="Lưu lại"
										cssClass="smButton greenButton" /> 
										<s:submit action="admin_medicineManagementCancel"
											value="Hủy bỏ" cssClass="cancel smButton"></s:submit>
										<%-- <s:submit
										action="admin_medicineManagementCancel" value="Huỷ bỏ"
										cssClass="smButton" id="huybo" /> --%>
										</td>
								</s:if>
								<s:else>
								<td><s:submit style="margin-left: 105px;"
										action="admin_updateMed" value="Lưu lại"
										cssClass="smButton greenButton" /> <s:submit
										onclick="deleteMed(%{medId}); return false;" value="Xoá"
										cssClass="smButton redButton" /> 
										<%-- <s:submit
										action="admin_medicineManagementCancel" value="Huỷ bỏ"
										cssClass="smButton" id="huybo" /> --%>
										<s:submit action="admin_medicineManagementCancel"
											value="Hủy bỏ" cssClass="cancel smButton"></s:submit>
										</td>
								<s:hidden name="imgPath" value="%{Medicine.imgPath}" />
								</s:else>
							</tr>
						</table>
				<%-- </s:if> --%>
			</s:if>
			</s:form>
		<!-- </div> -->
	</div>
</body>
</html>
