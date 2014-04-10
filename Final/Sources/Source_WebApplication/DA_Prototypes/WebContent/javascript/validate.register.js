(function($, W, D) {
	var JQUERY4U = {};

	JQUERY4U.UTIL = {
		setupFormValidation : function() {
			//form validation rules
			$("#registerForm").validate({
				rules : {
					representativeName : "required",
					email : {
						required : true,
						email : true
					},
					password : {
						required : true,
						customvalidation : true,
						minlength : 8
					},
					mobilePhone : {
						required : true,
						number : true,
						minlength : 10
					},
					licensureNo : "required",
					retypePassword : {
						required : true,
						equalTo : "#password",
						customvalidation : true
					},
					pharmacyName : {
						required : true
					},
					businesslicenseNo : {
						required : true
					},
					homePhone : {
						required : true,
						number : true,
						minlength : 8
					},
					street : {
						required : true
					},
					houseNo : {
						required : true
					},
					city : {
						valueNotEquals : "-1"
					},
					district : {
						valueNotEquals : "-1"
					}
				},
				messages : {
					representativeName : "Mục này bắt buộc phải nhập",
					password : {
						required : "Mục này bắt buộc phải nhập",
						minlength : "Mật khẩu của bạn phải có ít nhất 8 ký tự"
					},
					email : {
						required : "Mục này bắt buộc phải nhập",
						email : "Xin vui lòng nhập đúng định dạng email"
					},
					retypePassword : {
						required : "Mục này bắt buộc phải nhập",
						equalTo : "Mật khẩu xác nhận không khớp"
					},
					mobilePhone : {
						required : "Mục này bắt buộc phải nhập",
						number : "Sai định dạng. Vui lòng nhập số",
						minlength : "Số điện thoại phải có ít nhất 10 số"
					},
					licensureNo : {
						required : "Mục này bắt buộc phải nhập"
					},
					pharmacyName : {
						required : "Mục này bắt buộc phải nhập"
					},
					businesslicenseNo : {
						required : "Mục này bắt buộc phải nhập"
					},
					homePhone : {
						required : "Mục này bắt buộc phải nhập",
						number : "Sai định dạng. Vui lòng nhập số",
						minlength : "Số điện thoại phải có ít nhất 8 số"
					},
					street : {
						required : "Mục này bắt buộc phải nhập"
					},
					houseNo : {
						required : "Mục này bắt buộc phải nhập"
					}
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
			$.validator.addMethod("customvalidation", function(value, element) {
				return /^[A-Za-z\d=#$%@_ -]+$/.test(value);
			}, "Mật khẩu không được chứa ký tự đặc biệt");
			$.validator.addMethod("valueNotEquals", function(value, element,
					arg) {
				return arg != value;
			}, "Mục này không được bỏ trống");
		}
	}

	//when the dom has loaded setup form validation rules
	$(D).ready(function($) {
		JQUERY4U.UTIL.setupFormValidation();
	});

})(jQuery, window, document);
