package cn.gaoxing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.gaoxing.domain.Book;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		List<Book> cart=null;
		boolean purFlag=true;
		HttpSession session=request.getSession(false);
		if(session==null){
			purFlag=false;
		}else{
			cart=(List<Book>) session.getAttribute("cart");
			if(cart==null){
				purFlag=false;
			}
		}
		if(!purFlag){
			out.write("对不起，你还没有购买任何图书信息！");
		}else{
			out.write("你购买的图书有:<br/>");
			double price=0;
			int i=0;
			for(Book book:cart){
				String deleteUrl="DeleteBookServlet?id="+i;
				i++;
				out.write("书名："+book.getName()+"\t价格是："+book.getPrice()+"元\t<a href='"+deleteUrl+"'>取消</a><br/>");
				price=price+book.getPrice();
			}
			out.write("您购买的图书总价是："+price+"\t元");
			String url="IndexServlet";
			out.write("<br/><br/><a href='"+url+"'>返回</a><br/>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
