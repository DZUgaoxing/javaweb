package cn.gaoxing.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LastTimeServlet
 */
@WebServlet("/LastTimeServlet")
public class LastTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String lastTime=null;
		Cookie[] cookies=request.getCookies();
		for (int i = 0; cookies!=null&&i < cookies.length; i++) {
			if("LastAccess".equals(cookies[i].getName())){
				lastTime=cookies[i].getValue();
				break;
			}
		}
		if(lastTime==null){
			response.getWriter().print("你是首次访问本站！");
		}else{
			response.getWriter().print("你上次的访问时间是："+lastTime);
		}
		String currentTime=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
		Cookie cookie=new Cookie("LastAccess", currentTime);
		response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
