<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="<%=basePath%>resuorces/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resuorces/js/jquery.form.js"></script>
<script type="text/javascript">
   $(function(){
	   $("#hidden-file").change(function(){
		   $("#file-form").ajaxSubmit({
				 url:"<%=basePath%>file/upload",
				 type:"post",
				 dataType:"json",
				 success:function(data){
					 alert(data.path);
					 if(data.code==0){
					    alert("文件上传成功");
					    $("#img").attr("src","<%=basePath%>resource/upload/"+data.path);
					 }else{
						 alert(data.message);
					 }
				 }
				 
			 });
	   });
   });
	 

</script>
</head>
<body>
   <div>
      <img alt="" style="width:280px;height:100px" src="<%=basePath%>resources/upload/2017/0212/3463.jpg" id="img">
   </div>
    <form method="post" id="file-form" enctype="multipart/form-data">
       <input type="file" name="head" id="hidden-file"> 
       上传
    </form>
</body>
</html>