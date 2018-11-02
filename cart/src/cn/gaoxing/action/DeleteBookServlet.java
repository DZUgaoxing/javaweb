package cn.gaoxing.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.gaoxing.domain.Book;

@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session=request.getSession();
		@SuppressWarnings("unchecked")
		List<Book> cart=(List<Book>)session.getAttribute("cart");
        for(int i=0;i<cart.size();i++){
        	if(i==id){
        		cart.remove(cart.get(i));
        		System.out.println(id);
        	}
        }
		response.sendRedirect("CartServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
