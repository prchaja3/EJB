package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.LibraryPersistenceBean;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	LibraryPersistenceBean obj;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] idsDelete = request.getParameterValues("delete");
		
		for (int i = 0; i < idsDelete.length; i++) {
			obj.deleteBook(Integer.parseInt(idsDelete[i]));
		}
		
		
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset=\"UTF-8\"><title>Kniha byla smazána></title></head><body>");
		out.println("Vybrané knihy byly smazány");
		out.println("</body></html>");
	}

}
