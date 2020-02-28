package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.Book;
import persistence.LibraryPersistenceBean;
import persistence.LibraryPersistenceBeanRemote;

@WebServlet("/knihyServlet")
public class KnihyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	LibraryPersistenceBean obj;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		List<Book> books = obj.getBooks(); 
		
		out.println("<html><head><meta charset=\"UTF-8\"><title>Výpis knih</title></head><body>");
		
		out.println("<p>" + books.size() + "</p>");

		out.println("<form action=\"DeleteServlet\" method=\"get\">");
		for(int i = 0; i < books.size(); i++) {
				out.println("<p>" + "<input type=\"checkbox\" name=\"delete\" value=\"" + books.get(i).getId() + "\"/>" + books.get(i).getName() + "</p>");
		}
		out.println("<input type=\"submit\" value=\"Smazat vybrané\"/>");
		out.println("</form>");
		
		out.println("<a href=localhost:8080/EjbPersistence/index.html>Zpět na úvodní stranu</a>");
		out.println("</body></html>");
		out.flush();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		Book book = new Book();
		book.setName(request.getParameter("title"));
		
		obj.addBook(book);
		obj.createTimer(60000, new Book("První automatická kniha"));
		
		
		out.println("<html><head><meta charset=\"UTF-8\"><title>Kniha vložena</title></head><body>");
		out.println("<p>Kniha byla vložena</p>");
		out.println("<p>" + book.getName() + "</p>");
		out.println("<a href=localhost:8080/EjbPersistence/index.html>Zpět na úvodní stranu</a>");
		out.println("</body></html>");
	}

}
