<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>上传</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>

    <body>
        <form action="UploadTestServlet" enctype="multipart/form-data" id="loginform" name="loginform" method="post">
            选择图片：<input type="file" name="filename"/>
            <input id="subid" name="subid" type="submit" value="提交">
        </form>
        
        <h5>python返回结果:</h5>
         ${path}
         
  </body>
</html>