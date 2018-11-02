package cn.gaoxing.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gaoxing.domain.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String checkcode=request.getParameter("check_code");
		String savedCode=(String)request.getSession().getAttribute("check_code");
		PrintWriter out=response.getWriter();
		if(username.equals("gao")&&password.equals("123")&&checkcode.equals(savedCode)){
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/cart/IndexServlet");
		}else if(checkcode.equals(savedCode)){
			out.write("ÓÃ»§Ãû»òÃÜÂë´íÎó£¬µÇÂ¼Ê§°Ü");
			out.write("<br/><a href='/cart/login.html'>·µ»ØµÇÂ¼</a>");
		}else{
			out.write("ÑéÖ¤Âë´íÎó");
			out.write("<br/><a href='/cart/login.html'>·µ»ØµÇÂ¼</a>");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
