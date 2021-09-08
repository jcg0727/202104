<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>수정하기</title>

<body>

	<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" >
  	 <section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>수정하기</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="#">
				        	<i class="fa fa-dashboard">수정하기</i>
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	수정
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
  	</section>
	<!-- Main content -->
	<section class="content register-page">
		<div class="register-box">
			<div class="login-logo">
    			<a href="<%=request.getContextPath()%>/member/registForm.do"><b>수정 하기</b></a>
  			</div>
			<!-- form start -->
			<div class="card">				
				<div class="register-card-body">
				
					<form role="form" class="form-horizontal" action="regist.do" method="post">		
					
						<div class="form-group row">
							<label for="nno" class="col-sm-3" style="font-size:0.9em;">
								<span style="color:red;font-weight:bold;">*</span>번호</label>
							<div class="col-sm-9 input-group-sm">				
								<input class="form-control" name="nno" type="text" class="form-control" id="nno" value="${noticeVo.nno }"
								/>
							</div>
							</div>
						<div class="form-group row">
							<label for="writer" class="col-sm-3" style="font-size:0.9em;">
								<span style="color:red;font-weight:bold;">*</span>작성자</label>
							<div class="col-sm-9 input-group-sm">				
								<input class="form-control" name="writer" type="text" class="form-control" id="writer" value="${noticeVo.writer }"
								/>
							</div>
							</div>
							
						<div class="form-group row">
							<label for="title" class="col-sm-3" style="font-size:0.9em;">
								<span style="color:red;font-weight:bold;">*</span>제목</label>
							<div class="col-sm-9 input-group-sm">								
								<input class="form-control" name="title" type="text" class="form-control" id="title" value="${noticeVo.title }"
										placeholder="제목을 입력해 주세요" />
							</div>
							
						</div>
						
						<div class="form-group row">
							<label for="content" class="col-sm-3" style="font-size:0.9em;">
								<span style="color:red;font-weight:bold;">*</span>내용</label>
							<div class="col-sm-9 input-group-sm">	
								<input class="form-control" name="content" type="text" class="form-control" id="content" value="${noticeVo.content}" />						
							</div>
							
						</div>
						
						
						<div class="card-footer">
							<div class="row">								
								<div class="col-sm-6">
									<button type="button" id="registBtn" onclick="update_go();" class="btn btn-info">수정하기</button>
							 	</div>
							 	
							 	<div class="col-sm-6">
									<button type="button" id="cancelBtn" onclick="CloseWindow();"
										class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;취 &nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;</button>
								</div>
							
							</div>
						</div>
					</form>					
				</div><!-- register-card-body -->
			</div>
		</div>
	</section>		<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<form role="imageForm" action="upload/picture.do" method="post" enctype="multipart/form-data">
	<input id="inputFile" name="pictureFile" type="file" class="form-control" onchange="picture_go();"
			style="display:none;">
	<input id="oldFile" type="hidden" name="oldPicture" value="" />
	<input type="hidden" name="checkUpload" value="0" />	
</form>


<form id="updateForm">
	<input type="hidden" name="nno" value="" />
	<input type="hidden" name="title" value="" />
	<input type="hidden" name="content" value="" />
</form>

<script>
function update_go(){
	      var url="update.do";	
	      
	      var updateForm=$('#updateForm');

	      updateForm.find("[name='nno']").val($('input[name="nno"]').val());
	      updateForm.find("[name='title']").val($('input[name="title"]').val());
	      updateForm.find("[name='content']").val($('input[name="content"]').val());
	      
	      updateForm.attr({
	         action:url,
	         method:'get'
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










