<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
<!-- 表单提交必须用upload方法 -->
	<form action="upload" method="post"
	enctype="multipart/form-data">
		<fieldset>
			<legend>上传文件</legend>
			用户名：<input name="username"><br/>
			照片：<input type="file" name="file1"/>
			<input type="submit" value="确定"/>
		</fieldset>
	</form>
</body>
</html>