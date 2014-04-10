
<%@page import="com.med.dic.pagination.Pagination"%>
<%
					Pagination pagination = (Pagination) request.getAttribute("pagination");
					boolean previous = false;
					if(pagination.getPage_number() > 1){
						previous = true;
					}
					boolean next = true;
					if(pagination.getTotal_pages() == pagination.getPage_number()){
						next = false;
					}
				%>


		<input type="text" style="display: none;" name="pagination.sortColumn" id="sortColumn" value="<%=pagination.getSortColumn()%>"/>
		<input type="text" style="display: none;" name="pagination.sortOrder" id="sortOrder" value="<%=pagination.getSortOrder()%>"/>
		<table id="paggingAll">
			<tr>
			  <td class="pagination-label itemsPhar" nowrap="nowrap">
			    <%=pagination.getPage_records() %> <!-- Items -->
			  </td>
			  <td>
			  	<%if(previous){%>
			  	<a href="#" onclick="fnNextPagination(4,<%=pagination.getTotal_pages()%>);">
			  		<img src="imgs/left_end.png" alt="Go to first page" width="15" height="19"/>
			  	</a>
			  	<%}else{ %>
			  		<img src="imgs/left_end_gray.png" alt="Go to first page" width="15" height="19"/>
			  	<%} %>
			  </td>
			  <td>
			  	<%if(previous){%>
			  	<a href="#" onclick="fnNextPagination(3,<%=pagination.getTotal_pages()%>);">
			  		<img src="imgs/left.png" alt="Go to first page" width="15" height="19"/>
			  	</a>
			  	<%}else{ %>
			  		<img src="imgs/left_gray.png" alt="Go to first page" width="15" height="19"/>
			  	<%} %>
			  </td>
			  <td class="pagination-label" nowrap="nowrap">Trang:</td>
			  <td>
			  	<input name="pagination.page_number" id="page_number" class="pagination-textbox" <%if(!previous && !next) {%>readonly="readonly"<%}%>
			  	style="width: 10px;" maxlen="1" value="<%=pagination.getPage_number()%>" type="text" onchange="fnNextPagination(7,<%=pagination.getTotal_pages()%>);"/></td>
			  <td class="pagination-label" nowrap="nowrap"><tu class="of"> </tu> <%=pagination.getTotal_pages()%></td>
			  
			  <td>
			  	<% if(next){ %>
			  	<a href="#" onclick="fnNextPagination(1,<%=pagination.getTotal_pages()%>);">
			  		<img src="imgs/right.png" alt="Go to next page" border="0" width="15" height="19"/>
			  	</a>
			  	<%}else{ %>
			  		<img src="imgs/right_gray.png" alt="Go to next page" border="0" width="15" height="19"/>
			  	<%} %>
			  </td>
			  <td><% if(next){ %>
			  	<a href="#" onclick="fnNextPagination(2,<%=pagination.getTotal_pages()%>);">
			  		<img src="imgs/right_end.png" alt="Go to next page" border="0" width="15" height="19"/>
			  	</a>
			  	<%}else{ %>
			  		<img src="imgs/right_end_gray.png" alt="Go to next page" border="0" width="15" height="19"/>
			  	<%} %>
			  </td>
			  <td>&nbsp;</td>
			  <td class="pagination-label show" nowrap="nowrap"><!-- Hiển thị: --></td>
			  <td class="pagination-linkoff" style="" nowrap="nowrap">
			  	<s:select onchange="fnNextPagination(5,0);" list="#{'5':'5','10':'10','50':'50','100':'100','0':'All'}" theme="simple" name="pagination.page_size" id="page_size" value="#request.pagination.page_size"/>
			  </td>
			</tr>
		</table>
