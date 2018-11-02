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
			response.getWriter().print("����û�е�¼����<a href='/cart/login.html'>��¼</a>");
		}else{
			response.getWriter().print("���ѵ�¼����ӭ��"+user.getUsername()+"!");
			response.getWriter().print("<a href='/cart/LogoutServlet'>�˳�</a>");
			PrintWriter out =response.getWriter();
			Collection<Book> books=BookDB.getAll();
			out.write("<br/><br/>���̵��ṩ���鼮�У�<br/>");
			for(Book book:books) {
				String url="PurchaseServlet?id="+book.getId();
				out.write(book.getName()+"<a href='"+url+"'>�������</a><br/>");
			}
			String aurl="CartServlet";
			out.write("<br/><a href='"+aurl+"'>�鿴������Ʒ</a><br/>");
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
