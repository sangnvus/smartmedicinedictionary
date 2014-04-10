
<%-- 
    Document   : search_Medicines
    Created on : Apr 27, 2013, 10:59:49 PM
    Author     : W7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8; no-cache">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link rel="stylesheet" type="text/css"
	href="css/AdministrationPharmacy.css">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/pagination.js"></script>
<script type="text/javascript" src="javascript/paging-medicine.js"></script>
<script type="text/javascript" src="javascript/dynamic-list.js"></script>
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
								.append( '<li><a href="news" >Tin tức </a></li>'
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
	function detailMedInfo(medId) {
		document.paginationForm.action = "thong-tin-chi-tiet-thuoc-kiem-duyet";
		document.paginationForm.medIdDetail.value = medId;
		document.forms["paginationForm"].submit();
	}
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				var medIdList = document.getElementById("medIdList").value;
				var medIdSplit = medIdList.split("~");
				$("#checkAll").click(
						function() {
							var check = document.getElementById("checkAll");
							//var totalRecord = document.getElementById("paginationRecords").value;
							for ( var i = 0; i < medIdSplit.length; i++) {
								var checkEachRow = document
										.getElementById("checkBox["
												+ medIdSplit[i] + "]");
								if (check.checked == true) {
									checkEachRow.checked = true;
								} else {
									checkEachRow.checked = false;
								}
								//alert(checkEachRow.checked);
							}
						});
				$(".checkBox").click(
						function() {
							var check = document.getElementById("checkAll");
							for ( var i = 0; i < medIdSplit.length; i++) {
								var checkEachRow = document
										.getElementById("checkBox["
												+ medIdSplit[i] + "]");
								if (checkEachRow.checked == false) {
									check.checked = false;
								}
							}
						});
			});
</script>
<title>Pharmacy Management</title>
</head>
<sj:head />
<body>
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
	<script type="text/javascript" src="javascript/menu.js"></script>
		<div id="page_title">
			<ul>
				<li><a href="admin_medicineManagement.jsp"> Quản lý thuốc</a></li>
				<li><img src="css/images/arrow.png" /></li>
				<li><a href="admin_pharmacyManagement.jsp" id="current">Kiểm duyệt thuốc</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div>
		<!-- <div id="searchMedicines"> -->
			<s:hidden name="searched" />
			<s:hidden name="detailed" />
			<s:hidden name="checkaccept" />
			<s:hidden name="medIdList" id="medIdList" />
			<s:hidden name="acceptSuccess" />
			<s:hidden name="medIdList" id="medIdList" />
			<s:hidden name="failedAction" id="failedAction" />
			<s:hidden name="successedAction" id="successedAction" />
			<!-- <div id="header_big">Tiêu chí tìm kiếm</div> -->
			<s:form action="tra-cuu-thuoc-kiem-duyet" name="paginationForm"
				accept-charset="utf-8" theme="simple" id="registerForm"
				method="post">
				<s:hidden name="medIdDetail" id="medIdDetail" />
				<s:hidden name="smdEmail" value="%{#session['smdEmail']}" />
				<table id="invisible">
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Tên thuốc:</s:div>
						</td>
						<td><s:textfield type="text" name="keyWordMedName"
								cssClass="textbox middle" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Nhà sản xuất:</s:div>
						</td>
						<td><s:textfield type="text" name="keyWordMedManufac"
								cssClass="textbox middle" /></td>
					</tr>
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Chỉ định:</s:div>
						</td>
						<td><s:textfield type="text"
								name="keyWordMedIndication" cssClass="textbox middle" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Chống chỉ định:</s:div>
						</td>
						<td><s:textfield type="text"
								name="keyWordMedContraindication" cssClass="textbox middle" /></td>
					</tr>
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Loại thuốc:</s:div>
						</td>
						<td><s:url var="remoteurl" action="selectTagAction" /> <sj:select
								href="%{remoteurl}" name="keyWordMedTypeName" list="medTypes"
								listKey="medTypeId" listValue="medTypeName" headerKey="-1"
								headerValue="---Tất cả---" cssClass="selectbox smiddle" /></td>
						<td><s:div id="s_text" cssClass="header_small2">Thành phần:</s:div>
						</td>
						<td><s:textfield type="text" name="keyWordMedIngredients"
								cssClass="textbox middle" /></td>
					</tr>
					<tr>
						<td><s:div id="s_text" cssClass="header_small2">Kiểm duyệt:</s:div></td>
						<td><s:checkbox name="checkaccept" theme="simple" /></td>
					</tr>
					<tr>
						<td></td>
						<td><s:submit action="tra-cuu-thuoc-kiem-duyet-action"
								value="Tìm kiếm" cssClass="smButton"></s:submit></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<s:if test="successedAction == true">
					<div class="notice searchValid">
						<s:property value="message" />
					</div>
				</s:if>
				<s:if test="failedAction == true">
					<div class="notice searchInvalid">
						<s:property value="message" />
					</div>
				</s:if>
				<s:if test="searched == true">
					<s:if test="medicineList.size() > 0">
						<div id="header_big">Kết quả tìm kiếm</div>
						<div class="error">
							<s:fielderror fieldName="nullCheckBox" theme="simple" />
						</div>
						<%@ include file="pagination.jsp"%>
						<table id="visible">
							<tr>
								<th id="col-check-box"><s:checkbox name="checkAll" theme="simple"
										id="checkAll" /></th>
								<th
									class="sortable <s:if test="#attr.pagination.sortColumn.equals('medName')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'MEDICINE_NAME');"> Tên</a>
								</th>
								<th
									class="sortable <s:if test="#attr.pagination.sortColumn.equals('manufacturer')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'MANUFACTURER');"> Nhà sản xuất</a>
								</th>
								<th
									class="sortable <s:if test="#attr.pagination.sortColumn.equals('typeOfPackageName')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'INGREDIENTS');">Thành phần</a>
								</th>
								<th><a href="#">Trạng thái</a></th>
								<%-- <th
									class="sortable <s:if test="#attr.pagination.sortColumn.equals('typeOfPackageName')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									<a href="#" onclick="fnPagination(6,'INDICATIONS');">Chỉ định</a>
								</th> --%>
							</tr>
							<s:iterator value="medicineList">
								<tr>
									<td class="checkBox"><s:checkbox name="checkBox"
											fieldValue="%{medId}" theme="simple"
											id="checkBox[%{medId}]" /></td>
									<td><s:a href="javascript:detailMedInfo(%{medId});">
											<s:property value="medName" />
										</s:a></td>
									<td><s:property value="ingredients" /></td>
									<td><s:property value="indications" /></td>
									<td><s:if test="acceptFlag">
									Đã Kiểm Duyệt
								</s:if> <s:else>
									Chưa Kiểm Duyệt
								</s:else></td>
								</tr>
							</s:iterator>
						</table>
						<s:if test="checkaccept">
							<s:submit name="Không chấp nhận" value="Hủy Kiểm Duyệt"
								theme="simple"
								cssClass="smButton searchButton redButton marginL20"
								action="huy-kiem-duyet-thuoc" />
						</s:if>
						<s:else>
							<s:submit name="Chấp nhận" value="Kiểm Duyệt" theme="simple"
								cssClass="smButton searchButton marginL20 greenButton"
								cssStyle="text-align:center; vertical-align:middle;"
								action="kiem-duyet-thuoc" />
						</s:else>

					</s:if>
				</s:if>
				<%-- <s:if test="acceptSuccess == true">
					<s:if test="checkaccept == true">
						<div class="notice searchValid">
							<s:label>Hủy kiếm duyệt thành công</s:label>
						</div>
					</s:if>
					<s:else>
						<div class="notice searchValid">
							<s:label>Kiểm duyệt thành công</s:label>
						</div>
					</s:else>
				</s:if> --%>
				<s:if test="detailed == true">
					<div id="header_big">Kết quả chi tiết</div>
					<div id="dynamic-list" class="bs2">
						<table>
						<tr>
							<td>
								<table id="left-of-dynamic">
									<tr>
										<td class="limited"><a class="header_small2">Tên
												thuốc: </a></td>
										<td><s:property value="%{medDetail.medName}" /></td>
									</tr>
									<tr>
										<td class="limited"><a class="header_small2">Nhà sản
												xuất: </a></td>
										<td><s:property value="%{medDetail.manufacturer}" /></td>
									</tr>
									<tr>
										<td class="limited"><a class="header_small2">Loại
												thuốc: </a></td>
										<td><s:property value="%{medDetail.typeOfPackageName}" /></td>
									</tr>
									<tr>
										<td class="limited"><a class="header_small2">Tên
												thuốc gốc: </a></td>
										<td><s:property value="%{medDetail.genericMedicine}" /></td>
									</tr>
									<tr>
										<td class="limited"><a class="header_small2">Tên biệt
												dược: </a></td>
										<td><s:property value="%{medDetail.brandName}" /></td>
									</tr>
									<tr>
										<td class="limited">
											<a class="header_small2">Nhà thuốc đăng ký:</a>
										</td>
										<td><s:property value="%{medDetail.pharmacyName}" /></td>
									</tr>
								</table>
							</td>
							<td><img
								src="image/medicine/<s:property value="%{medDetail.imgPath}" />"
								style="height: 160px; width: 240px;" /></td>
						</tr>
					</table>
					<hr class="line" />
					<dl class="nav2">
					<dt onclick="makeDynamic(4)">
								<a class="header_small2">Thành phần</a>
								<div id="arrowDown" class="d_dt4"></div>
								<div id="arrowUp" class="u_dt4" style="display: none;"></div>
							</dt>
							<dd id="dd4">
								<ul>
									<s:iterator value="%{medDetail.ingredientsList}">
											<s:property />
											<br/>
										</s:iterator>

								</ul>
							</dd>
							<dt onclick="makeDynamic(5)">
								<a class="header_small2">Chỉ định</a>
								<div id="arrowDown" class="d_dt5" style="display: none;"></div>
								<div id="arrowUp" class="u_dt5"></div>
							</dt>
							<dd id="dd5">
								<ul>
									<s:iterator value="%{medDetail.indicationList}">
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
									<s:iterator value="%{medDetail.contraindicationList}">
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
									<s:iterator value="%{medDetail.dosingAndUseList}">
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
									<s:iterator value="%{medDetail.warningList}">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(11)">
								<a class="header_small2">Bảo quản</a>
								<div id="arrowDown" class="d_dt11"></div>
								<div id="arrowUp" class="u_dt11" style="display: none;"></div>
							</dt>
							<dd id="dd11">
								<ul>
									<s:iterator value="%{medDetail.storageList}">
											<s:property />
											<br/>
										</s:iterator>
								</ul>
							</dd>
							<dt onclick="makeDynamic(12)">
								<a class="header_small2">Tương tác thuốc</a>
								<div id="arrowDown" class="d_dt12"></div>
								<div id="arrowUp" class="u_dt12" style="display: none;"></div>
							</dt>
							<dd id="dd12">
								<ul>
									<s:iterator value="%{medDetail.interactionList}">
											<s:property />
											<br/>
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
									<s:iterator value="%{medDetail.similarMedicineList}">
											<s:property />
											<br/>
										</s:iterator>

								</ul>
							</dd>
							<dt onclick="makeDynamic(14)">
								<a class="header_small2">Nhà thuốc đang bán</a>
								<div id="arrowDown" class="d_dt14"></div>
								<div id="arrowUp" class="u_dt14" style="display: none;"></div>
							</dt>
							<dd id="dd14">
								<ul>
									<s:if test="medDetail.pharmacyList.size() > 0">
										<s:iterator value="medDetail.pharmacyList">
											<s:property value="pharmacyName" />
											<br/>
										</s:iterator>
									</s:if>
									<s:else>
										Chưa có thông tin
									</s:else>
								</ul>
							</dd>
						</dl>
					</div>
					<s:if test="checkaccept">
						<s:submit name="Không chấp nhận" value="Hủy Kiểm Duyệt"
							theme="simple"
							cssClass="smButton searchButton redButton marginL20"
							action="huy-kiem-duyet-thuoc" />
					</s:if>
					<s:else>
						<s:submit name="Chấp nhận" value="Kiểm Duyệt" theme="simple"
							cssClass="smButton searchButton marginL20 greenButton"
							cssStyle="text-align:center; vertical-align:middle;"
							action="kiem-duyet-thuoc" />
					</s:else>
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
<script type="text/javascript">
	function makeDynamic(number) {
		$("#dd" + number).slideToggle("slow");
		$(".u_dt" + number).toggle("slow");
		$(".d_dt" + number).toggle("slow");
	}
</script>