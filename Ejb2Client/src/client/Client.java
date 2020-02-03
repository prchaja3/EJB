package client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.LibrarySessionBeanRemote;

public class Client {
	BufferedReader brConsoleReader = null;
	Properties props;
	InitialContext ctx;
	{
		props = new Properties();
		//try {
		//	props.load(new FileInputStream("jndi.properties"));
		//	
		//}
		//catch (IOException ex) {
		//	ex.printStackTrace();
		//
		//}
		props.put("java.naming.factory.initial", "org.apache.openejb.client.RemoteInitialContextFactory");
		props.put("java.naming.provider.url", "http://localhost:8080/tomee/ejb/Ejb2");
		try {
			ctx = new InitialContext(props);
		}
		catch (NamingException ex) {
			ex.printStackTrace();
		}
		brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.testStatelessEjb();

	}
	
	private void showGui() {
		  System.out.println("**********************");
	      System.out.println("Vítejte v knihkupectví");
	      System.out.println("**********************");
	      System.out.print("Možnosti: \n1. Přidat knihu\n2. Ukončit \nZadejte možnost: ");
	}
	
	private void testStatelessEjb() {
		try {
			int volba = 1;
			LibrarySessionBeanRemote libraryBean = (LibrarySessionBeanRemote)ctx.lookup("LibrarySessionBean");
			while (volba != 2) {
				String bookName;
				showGui();
				String strChoice = brConsoleReader.readLine();
				volba = Integer.parseInt(strChoice);
				if (volba == 1) {
					System.out.print("Zadejte název knihy: ");
					bookName = brConsoleReader.readLine();
					libraryBean.addBook(bookName);
				}
				else if (volba == 2) {
					break;
				}
			}
			List<String> booksList = libraryBean.getBooks();
			System.out.println("Dosud přidané knihy: " + booksList.size());
			for (int i = 0; i < booksList.size(); i++) {
				System.out.println((i+1) + ". " + booksList.get(i));
			}
			LibrarySessionBeanRemote libraryBean1 = (LibrarySessionBeanRemote)ctx.lookup("LibrarySessionBean");
			List<String> booksList1 = libraryBean1.getBooks();
			System.out.println(
		            "***Druhý pohled na stateless object***");
		         System.out.println(
		            "Dosud přidané knihy: " + booksList1.size());
		         for (int i = 0; i < booksList1.size(); ++i) {
		            System.out.println((i+1)+". " + booksList1.get(i));
		         }
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		finally {
			try {
				if(brConsoleReader != null) {
					brConsoleReader.close();
				}
			}
			catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
	}

}
