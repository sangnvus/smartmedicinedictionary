<%--
    Document   : search_Medicines
    Created on : Apr 27, 2013, 10:59:49 PM
    Author     : W7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
	boolean search = false;
	boolean register = false;
	String check = null;
	String delFlag = null;
	String ingredients = " ";
	String contraindications = " ";
	String indications = " ";
	String manufacturer = " ";
	String name = " ";
	check = request.getParameter("button");
	if (null != check) {
		search = true;
		register = false;
		delFlag = request.getParameter("delFlag");
		String ingredients1 = request.getParameter("ingredients");
		String contraindications1 = request
				.getParameter("contraindications");
		String indications1 = request.getParameter("indications");
		String manufacturer1 = request.getParameter("manufacturer");
		String name1 = request.getParameter("name");
		if (null != ingredients1) {
			ingredients = ingredients1;
		}
		if (null != contraindications1) {
			contraindications = contraindications1;
		}
		if (null != indications1) {
			indications = indications1;
		}
		if (null != manufacturer1) {
			manufacturer = manufacturer1;
		}
		if (null != name1) {
			name = name1;
		}
	} else {
		if ("New Register".equals(check)) {
			register = true;
			search = false;
		} else {
			register = false;
			search = false;
		}
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/search.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<script src="javascript/1.8.3_jquery.min.js"></script>
<script src="javascript/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="javascript/login.js"></script>
<script src="javascript/js-image-slider.js" type="text/javascript"></script>
<script type="text/javascript" src="javascript/menu.js"></script>
<script type="text/javascript" src="javascript/footer.js"></script>
<title>Medicine Management</title>
</head>
<body>
	<div id="language">
		<div id="notice">
			<a href="#">Hi: Admin</a> | <a href="home.jsp">Logout</a>
		</div>
	</div>
	<div id="banner">
		<div id="banner_container">
			<img id="logo-text" src="css/images/my-banner-and-slogan-demo1.png" />
		</div>
	</div>
	<div id="menu_container">
		<div id="menu">
			<ul>
				<li><a href="home.jsp">Trang chủ</a></li>
				<li><a href="#">Tìm kiếm</a>
					<ul>
						<li><a href="search_Medicines.jsp">Tìm kiếm nâng cao</a></li>
						<li><a href="search_NearestLocationResult.jsp">Tìm cửa
								hàng gần nhất</a></li>
						<li><a href="search_location.jsp">Tìm kiếm địa chỉ nhà
								thuốc</a></li>
					</ul></li>
				<li><a href="#">Hiệu thuốc</a>
					<ul>
						<li><a href="register.jsp">Đăng ký</a></li>
					</ul></li>
				<li style="display: none;"><a href="aboutUs.jsp">Giới thiệu</a></li>
				<li><a href="#" id="current">Quản trị</a>
					<ul>
						<li><a href="pharmacy_registerNewMedicine.jsp"> Thêm mới
								thuốc</a></li>
						<li><a href="pharmacy_info.jsp">Thông tin hiệu thuốc</a></li>
					</ul></li>

			</ul>

		</div>
	</div>
	<div id="container">
	<br/>
		<div id="page_title">
			<ul>
			<li><a href="pharmacy_info.jsp" id="current">
						Thông tin hiệu thuốc</a></li>
				<li><img src="css/images/arrow.png" /></li>
				<li><a href="pharmacy_changeInfo.jsp" id="current">
						Quản lý thuốc</a></li>
				<li><img src="css/images/arrow.png" /></li>
			</ul>
		</div>
		<br/>
		<br/>
		<br/>
		<div id=register>
			<div id="rootElement" class="step0">
				<div id="register_head"> 1: Thông Tin Người Đại Diện (dược
					sỹ chính)</div>
				<div id="nodeElement1">
					<div id="nodeElement2">
						<div id="register_title" class="required">Email</div>
						<input type="text" id="textbox" />
					</div>
					<div id="nodeElement2">
						<div id="register_title" class="required">Mật Khẩu</div>
						<div class="explain">Nhiều hơn 9 ký tự.</div>
						<input type="text" id="textbox" />
					</div>
					<div id="nodeElement2">
						<div id="register_title" class="required">Xác Nhận Mật Khẩu</div>
						<div class="explain">Nhập lại mật khẩu.</div>
						<input type="text" id="textbox" />
					</div>
				</div>
				<div id="nodeElement1">
					<div id="nodeElement21">
						<div id="register_title" class="required">Họ và tên</div>
						<input type="text" id="textbox" />
					</div>
					<div id="nodeElement21">
						<div id="register_title" class="required">Số Điện Thoại Di
							Động</div>
						<input type="text" id="textbox" />
					</div>
				</div>
				<div id="nodeElement1">
					<div id="nodeElement21">
						<div id="register_title" class="required">Trình độ</div>
						<select style="width: 258px; height: 30px;">
							<option>Trung cấp dược</option>
							<option>Đại học dược</option>
							<option>Thạc sỹ dược</option>
							<option>Khác</option>
						</select>
					</div>
					<div id="nodeElement21">
						<div id="register_title" class="required">Giấy Phép Hành
							Nghề Số</div>
						<input type="text" id="textbox" />
					</div>
				</div>
			</div>
			<div id="rootElement" class="step1" >
				<div id="register_head">2: Thông Tin Hiệu Thuốc</div>
				<div id="nodeElement1">
					<div id="nodeElement21">
						<div id="register_title" class="required">Tên</div>
						<input type="text" id="textbox" />
					</div>
					<div id="nodeElement21">
						<div id="register_title" class="required">Giấy phép kinh
							doanh số</div>
						<input type="text" id="textbox" />
					</div>
					<div id="nodeElement21">
						<div id="register_title">Trực thuộc công ty</div>
						<input type="text" id="textbox" />
					</div>
				</div>
				<div id="nodeElement1">
					<div id="nodeElement21">
						<div id="register_title" class="required">Số điện thoại</div>
						<input type="text" id="textbox" />
					</div>
					<div id="nodeElement21">
						<div id="register_title">GPP số (nếu có)</div>
						<input type="text" id="textbox" />
					</div>
					<div id="nodeElement21">
						<div id="register_title" class="required">Loại hình</div>
						<select style="width: 258px; height: 30px;">
							<option>Doanh nghiệp</option>
							<option>Nhà thuốc</option>
							<option>Hiệu thuốc</option>
						</select>
					</div>
				</div>
				<div id="nodeElementLeft">
					<div id="nodeElement1">
						<div id="nodeElement21">
							<div id="register_title" class="required">Tỉnh, thành phố</div>
							<select style="width: 258px; height: 30px;">
								<option value="hanoi">Hà Nội</option>
								<option value="hungyen">Hưng Yên</option>
								<option value="halong">Hạ Long</option>
							</select>
						</div>
					</div>
					<div id="nodeElement1">
						<div id="nodeElement21">
							<div id="register_title" class="required">Quận, huyện</div>
							<select style="width: 258px; height: 30px;">
								<option value="sontay">Sơn Tây</option>
								<option value="phonoi">Phố Nối</option>
								<option value="halong">Hạ Long</option>
							</select>
						</div>
					</div>
					<div id="nodeElement1">
						<div id="nodeElement21">
							<div id="register_title" class="required">Đường/thôn, xóm</div>
							<input type="text" id="textbox" />
						</div>
					</div>
					<div id="nodeElement1">
						<div id="nodeElement21">
							<div id="register_title" class="required">Số nhà, ngõ</div>
							<input type="text" id="textbox" />
						</div>
					</div>
					<div id="nodeElement1">
						<div id="nodeElement21">
							<div id="register_title">Bằng khen, chứng nhận (nếu có)</div>
							<textarea rows="5" cols="35"></textarea>
						</div>
					</div>
					<div id="nodeElement1">
						<div id="register_title">Chọn ảnh đại diện</div>
					</div>
					<div id="nodeElement1">
						<div id="button_icon">
							<button class="smButton">Chọn ảnh...</button>
						</div>
						<div id="filename">
							<label>Chưa có tệp tin nào được chọn...</label>
						</div>
						<form>
							<input id="the_real_file_input" name="foobar" type="file"
								style="display: none;" size="100">
						</form>
						<script>
							$(document)
									.ready(
											function() {
												$('#button_icon')
														.click(
																function() {
																	$(
																			'#the_real_file_input')
																			.click();

																	$(
																			'input[type=file]')
																			.bind(
																					'change',
																					function() {
																						var str = "";
																						str = $(
																								this)
																								.val();
																						if (str.length < 1) {
																							$(
																									"#filename")
																									.text(
																											"Chưa có tệp tin nào được chọn...");
																						} else
																							$(
																									"#filename")
																									.text(
																											str);
																					})
																			.change();

																});
											});
						</script>
					</div>

				</div>
				<div id="nodeElementRight">
					<div id="nodeElement1">
						<div id="register_title">Chọn vị trí trên bản đồ</div>
						<div class="register_explain">Hệ thống sẽ tự động xác định
							địa chỉ bạn cung cấp. Bạn có thể chọn một cách chính xác vị trí
							bạn mong muốn trên bản đồ dưới đây:</div>
						<img src="css/images/choose_map.png" style="margin-bottom: 10px;" />
					</div>
				</div>
				<div id="nodeElement1">
					<div id="nodeElement21">
						<input type="button" value="Lưu lại" class="r_button" />
						<input id="bt" type="submit" value="Huỷ bỏ"
							class="r_button" />
					</div>
				</div>

			</div>
		</div>
		</div>
	</div>
</body>
</html>
