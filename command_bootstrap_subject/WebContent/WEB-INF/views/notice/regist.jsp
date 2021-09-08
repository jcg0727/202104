<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>공지등록</title>

<body>

	<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" >
  	 <section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>공지등록</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="#">
				        	<i class="fa fa-dashboard">공지관리</i>
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	등록
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
    			<a href="<%=request.getContextPath()%>/member/registForm.do"><b>공지 등록</b></a>
  			</div>
			<!-- form start -->
			<div class="card">				
				<div class="register-card-body">
				
					<form role="form" class="form-horizontal" action="regist.do" method="post">		
					
						<div class="form-group row">
							<label for="writer" class="col-sm-3" style="font-size:0.9em;">
								<span style="color:red;font-weight:bold;">*</span>작성자</label>
							<div class="col-sm-9 input-group-sm">				
								<input class="form-control" name="writer" type="text" class="form-control" id="writer" value="${loginUser.id }"
								/>
							</div>
							</div>
						<div class="form-group row">
							<label for="title" class="col-sm-3" style="font-size:0.9em;">
								<span style="color:red;font-weight:bold;">*</span>제목</label>
							<div class="col-sm-9 input-group-sm">								
								<input class="form-control" name="title" type="text" class="form-control" id="title"
										placeholder="제목을 입력해 주세요" />
							</div>
							
						</div>
						
						<div class="form-group row">
							<label for="content" class="col-sm-3" style="font-size:0.9em;">
								<span style="color:red;font-weight:bold;">*</span>내용</label>
							<div class="col-sm-9 input-group-sm">	
								<textarea rows="15" cols="" class="form-control" name="content" id="content" placeholder="내용을 입력해 주세요"> </textarea>							
							</div>
							
						</div>
						
						
						<div class="card-footer">
							<div class="row">								
								<div class="col-sm-6">
									<button type="button" id="registBtn" onclick="regist_go();" class="btn btn-info">등록하기</button>
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


<form id="noticeForm">
	<input type="hidden" name="writer" value="" />
	<input type="hidden" name="title" value="" />
	<input type="hidden" name="content" value="" />
</form>

<script>
function regist_go(url){
	      if(!url) url="regist.do";	      
	      var jobForm=$('#noticeForm');

	      jobForm.find("[name='writer']").val($('input[name="writer"]').val());
	      jobForm.find("[name='title']").val($('input[name="title"]').val());
	      jobForm.find("[name='content']").val($('textarea[name="content"]').val());
	      
	      jobForm.attr({
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










