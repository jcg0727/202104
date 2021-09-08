<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body>
<div class="card-body" style="text-align:center;">
    		  <div class="row">
	             <div class="col-sm-12">	
	             
		    		<table class="table table-bordered">
		    			<tr>
		                	<th>번호</th>
		               		<td id="nno" name="nno">${noticeVo.nno}</td>
		               	</tr>
		                <tr>	
		                	<th>제목</th>
		               		<td>${noticeVo.title}</td>
		                </tr>	
		                <tr>
		                	<th>작성자</th>
		               		<td>${noticeVo.writer}</td>
		               	</tr>
		                <tr>	
		                	<th>내용</th>
		               		<td>${noticeVo.content}</td>
		               	</tr>
		               	<tr>
		                	<th>등록날짜</th> <!-- yyyy-MM-dd  -->
		               		<td><fmt:formatDate value="${noticeVo.regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		               	</tr>
		              
		            </table>
		            <div class="card-footer">
							<div class="row">								
								<div class="col-sm-4">
									<button type="button" id="registBtn" onclick="update_go('${noticeVo.nno}');" class="btn btn-info">수정하기</button>
							 	</div>
								<div class="col-sm-4">
									<button type="button" id="registBtn" onclick="delete_go('${noticeVo.nno}');" class="btn btn-info">삭제하기</button>
							 	</div>
							 	
							 	<div class="col-sm-4">
									<button type="button" id="cancelBtn" onclick="CloseWindow();"
										class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;취 &nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;</button>
								</div>
							
							</div>
						</div>
		            
    		     </div> <!-- col-sm-12 -->
    	       </div> <!-- row -->
    		</div> <!-- card-body -->
    	
    	
    		
	<form id="deleteForm">
		<input type="hidden" name="nno" value="" />
	</form>
	<form id="updateForm">
		<input type="hidden" name="nno" value="" />
	</form>
	
    	<script>
    	
    		function update_go(nno){
    			var url = "updateForm.do";
    			
    			var updateForm=$('#updateForm');
    			
    			updateForm.find("[name='nno']").val(nno);
    			
    			updateForm.attr({
    				action : url,
    				method : 'get'
    			}).submit();
    		}
    		
    		function delete_go(nno){
    			var url = "delete.do";
    			
    			var deleteForm=$('#deleteForm');
    			
    			deleteForm.find("[name='nno']").val(nno);
    			
    			deleteForm.attr({
    				action : url,
    				method : 'get'
    			}).submit();
    		}
    		
    		 function CloseWindow(parentURL){
    			   if(parentURL){
    				   window.opener.parent.location.href=parentURL;
    			   }else{
    				   window.opener.parent.location.reload(true);
    			   }
    			   window.close();
    		   }
    	</script>
    		
 </body>   		