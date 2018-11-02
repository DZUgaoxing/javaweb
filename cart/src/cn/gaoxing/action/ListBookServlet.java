package cn.gaoxing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gaoxing.db.BookDB;
import cn.gaoxing.domain.Book;

/**
 * Servlet implementation class ListBookServlet
 */
@WebServlet("/ListBookServlet")
public class ListBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		Collection<Book> books=BookDB.getAll();
		out.write("本商店提供的书籍有：<br/>");
		for(Book book:books) {
			String url="PurchaseServlet?id="+book.getId();
			out.write(book.getName()+"<a href='"+url+"'>点击购买</a><br/>");
		}
		String aurl="CartServlet";
		out.write("<br/><a href='"+aurl+"'>查看已买商品</a><br/>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
