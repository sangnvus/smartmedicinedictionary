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
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="stylesheet" type="text/css"
	href="css/AdministrationPharmacy.css">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/append_n.js"></script>
<script type="text/javascript" src="javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="javascript/dynamic-list.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
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
							$("#ulMenu").append(
									'<li><a href="Login">Đăng nhập</a></li>');
						} else {
							document.getElementById("language")
									.removeAttribute("style");
							document.getElementById("language").style.visibility = "visible";
							if (userGroup1) {
								$("#ulMenu")
								.append(
										'<li><a href="news" >Tin tức </a></li>'
										+ '<li><a href="admin_medicineManagement" id="current">Quản lý thuốc</a>'
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
											keyWordMedTypeName : {
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
											keyWordMedTypeName : {
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
						'scrollbars=no,location=no,resizable=yes,width=700, height=500,top=0,left=0,top='
								+ tops + ',left=' + left);
	}
</script>
<title>Register New Medicine</title>
</head>
<sj:head />
<script type="text/javascript"
	src="javascript/jquery.textarea-expander.js"></script>
<body>
	<div id="language">
		<div id="notice">
			<span>Xin chào </span> <s:property
					value="#session['userName']" /> | <a href="logout">Đăng
				xuất</a> | <a href="ChangePassword">Đổi mật khẩu</a>
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
				<li><a href="home.jsp">Trang chủ</a></li>
				<li><a href="search_pharmacyInfo.jsp">Tìm nhà thuốc</a></li>
				<li><a href="search_nearestPharmacy.jsp">Nhà thuốc gần nhất</a></li>
			</ul>
		</div>
	</div>
	<div id="container">
	<script type="text/javascript" src="javascript/menu.js"></script>
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
		<!-- <div id="searchMedicines">
			<div id="header_big">Thêm mới thuốc</div> -->
			<s:form theme="simple" action="admin_addNewMed"
				enctype="multipart/form-data" name="paginationForm" id="paginationForm">
				<s:hidden name="medTypeIdList" id="medTypeIdList" />
				<s:hidden name="medTypeNameList" id="medTypeNameList" />
				<s:hidden name="page" value="admin_registerNewMedicine.jsp" />
				<s:hidden name="actionSucces" />
				<s:hidden name="medId" value="%{medicine.medId}" />
				<s:hidden name="smdEmail" value="%{#session['smdEmail']}" />
				<s:if test="!actionSucces || actionSucces == false">
				<table id="invisible">
					<tr>
						<td>
							<table style="width: 930px;">
								<tr>
									<td><s:div id="s_text" cssClass="header_small2">Ảnh:</s:div></td>
									<td><s:file name="keyWordMedImage" /></td>
									<td><s:div id="s_text" cssClass="header_small2">Tên thuốc gốc:</s:div></td>
									<td><s:textfield type="text" name="genericMedicine"
											value="%{Medicine.genericMedicine}" cssClass="textbox middle" maxlength="100"/></td>
								</tr>
								<tr>
									<td><s:div id="s_text" cssClass="header_small2 required">Tên thuốc:</s:div></td>
									<td><s:textfield type="text" name="medName"
											value="%{Medicine.medName}" cssClass="textbox middle" maxlength="100" /></td>
									<td><s:div id="s_text" cssClass="header_small2 required">Nhà sản xuất:</s:div></td>
									<td><s:textfield type="text" name="manufacturer"
											value="%{Medicine.manufacturer}" cssClass="textbox middle" maxlength="100" /></td>
								</tr>
								<tr>
									<td><s:div id="s_text" cssClass="header_small2 required">Loại thuốc:</s:div>
									</td>
									<td onclick="javascript:openPopup();">
									<s:textarea type="text" name="keyWordMedTypeName"
											value="%{medTypeNameList}"
											cssClass="textbox middle medTypeButton expand26-180" id="keyWordMedTypeName1"
											style="width:283px;" readonly="true" />
									</td>
									<td><s:div id="s_text" cssClass="header_small2">Tên thương mại:</s:div></td>
									<td><s:textfield type="text" value="%{Medicine.brandName}"
											cssClass="textbox middle" name="brandName" maxlength="100" /></td>
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
											name="ingredients" cols="97" id="ingredients"
											cssClass="expand40-180" maxlength="2500"></s:textarea></td>
								</tr>
								<tr>
									<td><s:div id="s_text" cssClass="header_small2 required">Chỉ định/Triệu chứng/Công dụng:</s:div></td>
									<td><s:textarea value="%{medicine.indications}"
											name="indications" cols="97" 
											cssClass="expand60-180" id="indications" maxlength="2500"></s:textarea></td>
								</tr>
								<tr>
									<td><s:div id="s_text" cssClass="header_small2">Chống chỉ định:</s:div></td>
									<td><s:textarea value="%{medicine.contraindications}"
											name="contraindications" cols="97" id="contraindications"
											cssClass="expand60-180" maxlength="2500"></s:textarea></td>
								</tr>
								<tr>
									<td><s:div id="s_text" cssClass="header_small2 required">Cách dùng và sử dụng:</s:div></td>
									<td><s:textarea value="%{medicine.dosingAndUse}"
											name="dosingAndUse" cols="97" id="dosingAndUse"
											cssClass="expand40-180" maxlength="2500"></s:textarea></td>
								</tr>
								<tr>
									<td><s:div id="s_text" cssClass="header_small2">Bảo quản:</s:div></td>
									<td><s:textarea value="%{medicine.storage}" name="storage"
											cols="97" id="storage" cssClass="expand40-180" maxlength="2500"></s:textarea></td>
								</tr>
								<tr>
									<td><s:div id="s_text" cssClass="header_small2">Cảnh báo:</s:div></td>
									<td><s:textarea value="%{medicine.warning}" name="warning"
											cols="97" id="warning" cssClass="expand40-180" maxlength="2500"></s:textarea></td>
								</tr>
								<tr>
									<td><s:div id="s_text" cssClass="header_small2">Thuốc tương tự:</s:div></td>
									<td><s:textarea value="%{medicine.similarMedicine}"
											name="similarMedicine" cols="97" id="similarMedicine"
											cssClass="expand40-180" maxlength="2500"></s:textarea></td>
								</tr>
								<tr>
									<td><s:div id="s_text" cssClass="header_small2">Thuốc tương tác:</s:div></td>
									<td><s:textarea value="%{medicine.interaction}"
											name="interaction" cols="97" id="interaction"
											cssClass="expand40-180" maxlength="2500"></s:textarea></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td><s:submit style="margin-left: 105px;"
								action="admin_addNewMed" value="Lưu lại"
								cssClass="smButton greenButton"></s:submit></td>
					</tr>
				</table>
				</s:if>
				<s:if test="actionSucces == true">
					<div class="notice searchValid">
						<s:property value="message" />
					</div>
					<div id="dynamic-list" class="bs2">
						<table>
							<tr>
								<td>
									<table id="left-of-dynamic">
										<tr>
											<td class="limited"><a class="header_small2">Tên
													thuốc: </a></td>
											<td><s:property value="%{medicine.medName}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Nhà sản
													xuất: </a></td>
											<td><s:property value="%{medicine.manufacturer}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Loại
													thuốc: </a></td>
											<td><s:property value="%{medicine.typeOfPackageName}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Tên
													thuốc gốc: </a></td>
											<td><s:property value="%{medicine.genericMedicine}" /></td>
										</tr>
										<tr>
											<td class="limited"><a class="header_small2">Tên
													biệt dược: </a></td>
											<td><s:property value="%{medicine.brandName}" /></td>
										</tr>
									</table>
								</td>
								<td><img
									src="image/medicine/<s:property value="%{medicine.imgPath}" />"
									style="height: 160px; width: 240px;" /></td>
							</tr>
						</table>
						<hr class="line" />
						<dl class="nav2">
							<dt onclick="makeDynamic(4)">
								<a class="header_small2">Thành phần</a>
								<div id="arrowDown" class="d_dt4" style="display: none;"></div>
								<div id="arrowUp" class="u_dt4" ></div>
							</dt>
							<dd id="dd4">
								<ul>
									<s:iterator value="medicine.ingredientsList">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(5)">
								<a class="header_small2">Chỉ định/Triệu chứng/Công dụng</a>
								<div id="arrowDown" class="d_dt5"></div>
								<div id="arrowUp" class="u_dt5" style="display: none;"></div>
							</dt>
							<dd id="dd5">
								<ul>
									<s:iterator value="medicine.indicationList">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(6)">
								<a class="header_small2">Chống chỉ định</a>
								<div id="arrowDown" class="d_dt6"></div>
								<div id="arrowUp" class="u_dt6" style="display: none;"></div>
							</dt>
							<dd id="dd6">
								<ul>
									<s:iterator value="medicine.contraindicationList">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(7)">
								<a class="header_small2">Cách sử dụng</a>
								<div id="arrowDown" class="d_dt7"></div>
								<div id="arrowUp" class="u_dt7" style="display: none;"></div>
							</dt>
							<dd id="dd7">
								<ul>
									<s:iterator value="medicine.dosingAndUseList">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(10)">
								<a class="header_small2">Cảnh báo</a>
								<div id="arrowDown" class="d_dt10"></div>
								<div id="arrowUp" class="u_dt10" style="display: none;"></div>
							</dt>
							<dd id="dd10">
								<ul>
									<s:iterator value="medicine.warningList">
										<s:property />
										<br />
									</s:iterator>
									<%-- <li><s:property value="%{medDetail.warning}" /></li> --%>

								</ul>
							</dd>
							<dt onclick="makeDynamic(11)">
								<a class="header_small2">Bảo quản</a>
								<div id="arrowDown" class="d_dt11"></div>
								<div id="arrowUp" class="u_dt11" style="display: none;"></div>
							</dt>
							<dd id="dd11">
								<ul>
									<s:iterator value="medicine.storageList">
										<s:property />
										<br />
									</s:iterator>
									<%-- <li><s:property value="%{medDetail.storage}" /></li> --%>

								</ul>
							</dd>
							<dt onclick="makeDynamic(12)">
								<a class="header_small2">Tương tác thuốc</a>
								<div id="arrowDown" class="d_dt12"></div>
								<div id="arrowUp" class="u_dt12" style="display: none;"></div>
							</dt>
							<dd id="dd12">
								<ul>
									<s:iterator value="medicine.interactionList">
										<s:property />
										<br />
									</s:iterator>

								</ul>
							</dd>
							<dt onclick="makeDynamic(13)">
								<a class="header_small2">Thuốc tương tự</a>
								<div id="arrowDown" class="d_dt13"></div>
								<div id="arrowUp" class="u_dt13" style="display: none;"></div>
							</dt>
							<dd id="dd13">
								<ul>
									<s:iterator value="medicine.similarMedicineList">
										<s:property />
										<br />
									</s:iterator>
									<%-- <li><s:property value="%{medDetail.similarMedicine}" /></li> --%>

								</ul>
							</dd>
							<dt onclick="makeDynamic(14)">
								<a class="header_small2">Hiệu thuốc đang bán</a>
								<div id="arrowDown" class="d_dt14"></div>
								<div id="arrowUp" class="u_dt14" style="display: none;"></div>
							</dt>
							<dd id="dd14">
								<ul>
									Chưa có thông tin
								</ul>
							</dd>
						</dl>
						<s:submit style="margin-left: 2px;"
									action="admin_registerNewMedicine" value="Quay lại"
									cssClass="smButton greenButton" />
							<s:submit style="margin-left: 2px;" action="admin_copyNewMedicine"
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
