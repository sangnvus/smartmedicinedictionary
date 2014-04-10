$(document).ready(function() {
		$(".p_map").show();
		$(".map").css({"border-bottom":"4px solid #02817C", "text-decoration":"underline"});
		$(".p_video").hide();
		$(".p_anh").hide();
		$(".video").click(function() {
			/* $(".p_video").append('<object id="videoPath"> <video width="100%" height="400" controls> <source src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacyPopup.videoPath}" />" type="video/mp4"></source> </video> </object>'); */
			$(".p_video").show();
			$(".p_anh").hide();
			$(".p_map").hide();
			$(".video").css({"border-bottom":"4px solid #02817C", "text-decoration":"underline"});
			$(".anh").css({"border-bottom":"1px solid #dcdcdc", "text-decoration":"none"});
			$(".map").css({"border-bottom":"1px solid #dcdcdc", "text-decoration":"none"});
		});
		$(".anh").click(function() {
			/* $("#videoPath").remove();
			$(".p_video").append('<object id="videoPath"> <video width="100%" height="400" controls> <source src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacyPopup.videoPath}" />" type="video/mp4"></source> </video> </object>'); */
			$(".p_anh").show();
			$(".p_map").hide();
			$(".p_video").hide();
			$(".anh").css({"border-bottom":"4px solid #02817C", "text-decoration":"underline"});
			$(".video").css({"border-bottom":"1px solid #dcdcdc", "text-decoration":"none"});
			$(".map").css({"border-bottom":"1px solid #dcdcdc", "text-decoration":"none"});
		});
		$(".map").click(function() {
			/* $("#videoPath").remove();
			$("#videoContainer")[0].pause();
			$(".p_video").append('<object id="videoPath"> <video width="100%" height="400" controls> <source src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacyPopup.videoPath}" />" type="video/mp4"></source> </video> </object>'); */
			$(".p_map").show();
			$(".p_anh").hide();
			$(".p_video").hide();
			$(".map").css({"border-bottom":"4px solid #02817C", "text-decoration":"underline"});
			$(".anh").css({"border-bottom":"1px solid #dcdcdc", "text-decoration":"none"});
			$(".video").css({"border-bottom":"1px solid #dcdcdc", "text-decoration":"none"});
		});
		$("#direct").click(function() {
			/* $("#videoPath").remove();
			$(".p_video").append('<object id="videoPath"> <video width="100%" height="400" controls> <source src="http://localhost:8080/MedicineDictionaryWebApp/Pharmacy_Medias/<s:property value="%{pharmacyPopup.videoPath}" />" type="video/mp4"></source> </video> </object>'); */
			$(".p_map").show();
			$(".p_anh").hide();
			$(".p_video").hide();
			$(".map").css({"border-bottom":"4px solid #02817C", "text-decoration":"underline"});
			$(".anh").css({"border-bottom":"1px solid #dcdcdc", "text-decoration":"none"});
			$(".video").css({"border-bottom":"1px solid #dcdcdc", "text-decoration":"none"});
		});
	});