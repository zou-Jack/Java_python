package com.zout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//参考地址:https://blog.csdn.net/Jin_Kwok/article/details/80096371

@WebServlet("/UploadTestServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /** 上传目录名*/
    private static final String UPLOAD_DIR = "uploadDir/img/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /****** 初始化部分 ******/
        //设置编码格式，前端后台统一是utf-8
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");

        /****** 文件路径部分 用的是项目发布的相对路径******/
        // Servlet上下文对象
        ServletContext servletContext = this.getServletConfig().getServletContext();
        // tomcat的项目路径，记住要加斜杠
        String fileName = "test.jpg";
        String realPath = servletContext.getRealPath(UPLOAD_DIR)+"/";//保存的路径
        String filePath = realPath+fileName;
        System.out.println("完整的文件路径:"+filePath);
        File realPathFile = new File(realPath);
        if (!realPathFile.exists()) {
            realPathFile.mkdirs();
        }

        /****** 读写部分  ******/
        FileOutputStream fos = new FileOutputStream(new File(filePath));
        //传给页面的输出流
        ServletInputStream sis = request.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;;
        while ((len=sis.read(b))!=-1) {
            fos.write(b, 0, len);
        }

        /****** 关闭资源  ******/
        fos.close();
        sis.close();

        //定义命令字符串   
        String commandStr = new String("python /home/xxy/1.py "+filePath);
        //创建线程实例并执行命令
        Process pr = Runtime.getRuntime().exec(commandStr);
        //获取通过执行上述命令生成的结果
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = null;
        String result = "";
        while ((line = in.readLine()) != null)
        {
            result += line + "\r\n";
        }
        System.out.println("Python值:"+result);
        in.close();
        int endFlag;
		try {
			endFlag = pr.waitFor();
        if (endFlag == 0)
	        {
	            System.out.println("线程正常结束.");
	    }else {
	        	System.out.println("线程异常返回值:"+endFlag);
	        }
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        /****** 把图片地址，转发回页面 ******/
        try {
            request.setAttribute("path", result);
            System.out.println("path:"+UPLOAD_DIR+fileName);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}