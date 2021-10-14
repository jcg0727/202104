<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js"></script>
</head>

<body>
<div id="content">
  <h1 style="color:red">안녕하세요</h1>
  <p>PDF 문서의 내용입니다</p>
</div>

<img alt="" src="">

<script language = "javascript">
let doc = new jsPDF('p','pt','a4');
 
 
doc.addHTML(document.body,function() {
    doc.save('html.pdf');
});
 
</script>





</body>
</html>