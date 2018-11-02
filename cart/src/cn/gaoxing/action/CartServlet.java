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
			out.write("�Բ����㻹û�й����κ�ͼ����Ϣ��");
		}else{
			out.write("�㹺���ͼ����:<br/>");
			double price=0;
			int i=0;
			for(Book book:cart){
				String deleteUrl="DeleteBookServlet?id="+i;
				i++;
				out.write("������"+book.getName()+"\t�۸��ǣ�"+book.getPrice()+"Ԫ\t<a href='"+deleteUrl+"'>ȡ��</a><br/>");
				price=price+book.getPrice();
			}
			out.write("�������ͼ���ܼ��ǣ�"+price+"\tԪ");
			String url="IndexServlet";
			out.write("<br/><br/><a href='"+url+"'>����</a><br/>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
