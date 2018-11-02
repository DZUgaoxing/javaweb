package cn.gaoxing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.gaoxing.db.BookDB;
import cn.gaoxing.domain.Book;
import cn.gaoxing.domain.User;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		if(user==null){
			response.getWriter().print("您还没有登录，请<a href='/cart/login.html'>登录</a>");
		}else{
			response.getWriter().print("你已登录，欢迎你"+user.getUsername()+"!");
			response.getWriter().print("<a href='/cart/LogoutServlet'>退出</a>");
			PrintWriter out =response.getWriter();
			Collection<Book> books=BookDB.getAll();
			out.write("<br/><br/>本商店提供的书籍有：<br/>");
			for(Book book:books) {
				String url="PurchaseServlet?id="+book.getId();
				out.write(book.getName()+"<a href='"+url+"'>点击购买</a><br/>");
			}
			String aurl="CartServlet";
			out.write("<br/><a href='"+aurl+"'>查看已买商品</a><br/>");
			Cookie cookie=new Cookie("JESSIONID", session.getId());
			cookie.setMaxAge(30*60);
			cookie.setPath("/cart");
			response.addCookie(cookie);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
