$(document).ready(function() {
	$('#avatarImage').change(function(e) {
		var ext = $(this).val().split('.').pop().toLowerCase();
		if (this.files[0].size > 10240000) {
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
	$('#videoId').change(function(e) {
		var ext = $(this).val().split('.').pop().toLowerCase();
		if (this.files[0].size > 102400000) {
			alert('Video của bạn vượt quá dung lượng cho phép');
			$(this).val(null);
			e.preventDefault();
		}
		if ($.inArray(ext, [ 'mp4', 'ogv','webm' ]) == -1) {
			alert('Định dạng file video của bạn không được cho phép');
			e.preventDefault();
			$(this).val(null);
		}
	});
});