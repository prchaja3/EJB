package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.Book;
import persistence.LibraryPersistenceBean;

@WebServlet("/knihyServlet")
public class KnihyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	LibraryPersistenceBean obj;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		List<Object> books = obj.getBooks(); 
		
		out.println("<html><head><meta charset=\"UTF-8\"><title>Výpis knih</title></head><body>");
		out.println("<p>" + books.size() + "</p>");

		for(int i = 0; i < books.size(); i++) {
			try {
				out.println("<p>" + books.get(i).toString() + "</p>");
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
		
		out.println("<a href=localhost:8080/EjbPersistence/index.html>Zpět na úvodní stranu</a>");
		out.println("</body></html>");
		out.flush();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		Book book = new Book();
		book.setName(request.getParameter("title"));
		
		obj.addBook(book);
		
		out.println("<html><head><meta charset=\"UTF-8\"><title>Kniha vložena</title></head><body>");
		out.println("<p>Kniha byla vložena</p>");
		out.println("<a href=localhost:8080/EjbPersistence/index.html>Zpět na úvodní stranu</a>");
		out.println("</body></html>");
	}

}
