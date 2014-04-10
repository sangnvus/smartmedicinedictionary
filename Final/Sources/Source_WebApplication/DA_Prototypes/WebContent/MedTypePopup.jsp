<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="css/AdministrationPharmacy.css">
<title>Insert title here</title>
<script type="text/javascript">
	function returnParent(){
		var size = document.getElementById("size").value;
		var medTypeLst = document.getElementById("medTypeListStr").value;
		var medTypeName = "";
		var medTypeId = "";
		var medTypeStrSplit = medTypeLst.split("~");
		for ( var i = 0; i < size; i++) {
			var checkEachRow = document
					.getElementById("checkBox["
							+ medTypeStrSplit[i] + "]");
			if (checkEachRow.checked == true) {
				medTypeName += checkEachRow.value + ",";
				medTypeId += medTypeStrSplit[i] + ",";
			}
		}
		if(medTypeName.length > 0) {
			medTypeName = medTypeName.substring(0, medTypeName.length-1);
			medTypeId = medTypeId.substring(0, medTypeId.length - 1 );
		}
		window.opener.$("#keyWordMedTypeName1").val(medTypeName);
		window.opener.$("#medTypeIdList").val(medTypeId);
		window.opener.$("#medTypeNameList").val(medTypeName);
	    window.close();
	}
</script>
<script type="text/javascript">
	function addMedType() {
		var medTypeLst= document.getElementById("medTypeLst").value;
		var medTypeLstSplit = medTypeLst.split(",");
		for ( var i = 0; i < medTypeLstSplit.length; i++) {
			var checkEachRow1 = document
			.getElementById("checkBox["
			                          + medTypeLstSplit[i] + "]");
			checkEachRow1.checked = true;
		}
		
	} 
</script>
</head>
<body onload="addMedType();">
	<s:hidden name="size" id="size" />
	<s:hidden name="mama" id="mama" />
	<s:hidden name="medTypeListStr" id="medTypeListStr" />
	<s:hidden name="medTypeLst" id="medTypeLst" />
	<s:if test="medTypeList.size() > 0">
		<div id="medtype-checkbox">
			<s:iterator value="medTypeList">
				<s:div id="pmt-checkbox" name="tu" onclick="check(%{medTypeId});">
					<s:checkbox name="checkBox" fieldValue="%{medTypeName}"
						id="checkBox[%{medTypeId}]" onclick="check(%{medTypeId});"/>
						<s:label value="%{medTypeName}"/>
				</s:div>
			</s:iterator>
			<div id="pop-footer">
			<s:submit value="OK" onclick="returnParent();" cssClass="smButton greenButton"/>
			</div>
		</div>
	</s:if>
	<script type="text/javascript">
			function check(id) {
				 var checkboxCheck = document.getElementById("checkBox["+id+"]").checked;
				if (!checkboxCheck) {
					document.getElementById("checkBox["+id+"]").checked = true;
					$("#checkBox["+id+"]").addClass("enabled");
					/* $("#check["+id+"]").css("background-color","green"); */
				} else {
					
					document.getElementById("checkBox["+id+"]").checked = false;
					/* $("#check["+id+"]").css("background-color","blue"); */
				} 
			}
		
			
		</script>
</body>
</html>