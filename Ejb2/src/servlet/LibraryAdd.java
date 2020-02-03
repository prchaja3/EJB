package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.LibrarySessionBean;
import ejb.LibrarySessionBeanRemote;

/**
 * Servlet implementation class LibraryAdd
 */
@WebServlet("/LibraryAdd")
public class LibraryAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	@EJB
	LibrarySessionBeanRemote obj;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		obj.addBook("Kniha číslo 1");
		obj.addBook("Kniha číslo 2");
		obj.addBook("Kniha číslo 3");
		
		List<String> knihy = new ArrayList<>();
		knihy = obj.getBooks();
		
		out.println("<html><head><title>Zkouška</title></head><body>");
		out.println("<p>" + knihy.get(0) + "</p>");
		out.println("<p>" + knihy.get(1) + "</p>");
		out.println("<p>" + knihy.get(2) + "</p>");
		out.println("</body></html>");
	}


}
