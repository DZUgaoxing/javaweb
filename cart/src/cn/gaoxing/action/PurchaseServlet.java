package cn.gaoxing.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.gaoxing.db.BookDB;
import cn.gaoxing.domain.Book;

/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String id=request.getParameter("id");
		if(id==null){
			String url="IndexServlet";
			response.sendRedirect(url);
			return;
		}
		Book book=BookDB.getBook(id);
		HttpSession session=request.getSession();
		@SuppressWarnings("unchecked")
		List<Book> cart=(List<Book>)session.getAttribute("cart");
		if(cart==null){
			cart =new ArrayList<Book>();
			session.setAttribute("cart", cart);
		}
		cart.add(book);
		Cookie cookie=new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*30);
		cookie.setPath("/cart");
		response.addCookie(cookie);
		String url="CartServlet";
		response.sendRedirect(url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
