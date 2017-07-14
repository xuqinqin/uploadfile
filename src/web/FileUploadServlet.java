package web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
//实现web向服务器传送文件，保存在服务器中
public class FileUploadServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//只对post请求有效,post请求发生乱码问题解决比较方便，只需要写这句话就好
		response.setContentType("text/html;charset=utf-8"); 
		//step1,创建一个DiskFileItemFactory对象，
		//该对象为解析器提供解析时的缺省值
		DiskFileItemFactory diff=
				new DiskFileItemFactory();
		//step2,创建解析器
		ServletFileUpload sfu=
				new ServletFileUpload(diff);
		//step3,使用解析器来解析，解析器会调用request.getInputStream
		//流，然后分析这个流，并且将分析的结果封装到FileItem对象里面
		//一个FileItem对象封装了一个表单域中的所有数据
		try {
			List<FileItem> items=
					sfu.parseRequest(request);
			//只需要遍历items集合就可以访问表单中每一个表单域的数据
			for(int i=0;i<items.size();i++){
				FileItem  curr=items.get(i);
				if(curr.isFormField()){
					//普通表单域
					//name属性
					String fieldname=curr.getFieldName();
					System.out.println("filename:"+fieldname);
					//输入的input值
					String username=new String(curr.getString().getBytes("ISO-8859-1"),"UTF-8");
					System.out.println("username:"+username);
				}else {
					//上传文件域
					//依据逻辑路径(path)获取实际部署时的物理路径
					//拒绝硬编码
					ServletContext sctx=getServletContext();
					String path=sctx.getRealPath("upload");
					System.out.println("path:"+path);
					//获取文件名
					String filename=curr.getName();
					File file=new File(path+"\\"+filename);
					try {
						curr.write(file);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
				
	}

}
