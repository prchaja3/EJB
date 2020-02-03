package webservice;

import java.io.IOException;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.BankRemote;



@WebServlet("/OpenAccount")
public class OpenAccount extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			Properties properties = new Properties();
			properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.core.OpenEJBInitialContextFactory");
			final Context ctx = new InitialContext(properties);
			BankRemote bank = (BankRemote)ctx.lookup("Bank");
			
			
			request.getSession().setAttribute("remote", bank);
			
			request.getRequestDispatcher("/operation.jsp").forward(request,response);
		}
		catch (NamingException | IOException | ServletException ex) {
			ex.printStackTrace();
		}
	}
	

	
}
