function checkOne(id) {
	var check = document.getElementById(id);
	check.checked = false;
}

function checkall(class_name, obj) {
	// Duyá»‡t qua cÃ¡c checkbox cÃ³ class = class_name (item)
	// Tráº£ vá»� cÃ¡c pháº§n tá»­ á»Ÿ dáº¡ng máº£ng, báº¯t dáº§u tá»« vá»‹ trÃ­
	// 0;
	var items = document.getElementsByClassName(class_name);
	if (obj.checked == true) // Ä�Ã£ Ä‘Æ°á»£c chá»�n
	{
		for (i = 0; i < items.length; i++)
			items[i].checked = true;
	} else { // Checkbox chÆ°a Ä‘Æ°á»£c chá»�n
		for (i = 0; i < items.length; i++)
			items[i].checked = false;
	}
}


/*
 $(".searchButton").click(function(){
		
		var items = $("input.checkbox");
		for(i=0;i<items.length;i++)
			items[i].disabled=true;
	  });
	 
*/
$(document).ready(function() {
	var medIdList = document.getElementById("medIdList").value;
	var medIdSplit = medIdList.split("~");
	$("#checkAll").click(function() {
		var check = document.getElementById("checkAll");
		//var totalRecord = document.getElementById("paginationRecords").value;
		for(var i = 0; i < medIdSplit.length; i++) {
			var checkEachRow = document.getElementById("checkBox[" + medIdSplit[i] +"]");
			if(check.checked == true) {
				checkEachRow.checked = true;
			} else {
				checkEachRow.checked = false;
			}
			//alert(checkEachRow.checked);
		}
	});
	$(".checkBox").click(function () {
		var check = document.getElementById("checkAll");
		for(var i = 0; i < medIdSplit.length; i++) {
			var checkEachRow = document.getElementById("checkBox[" + medIdSplit[i] +"]");
			if(checkEachRow.checked == false) {
				check.checked = false;
			}
		}
	});
});








