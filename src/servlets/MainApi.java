package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.XMLDao;
import models.Book;
import models.SearchRequest;

/**
 * Main Servlet which controls what the system does
 */
@WebServlet("/API")
public class MainApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainApi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equals("search")){
			SearchRequest sr = new SearchRequest(request.getParameter("book-title"), request.getParameter("book-authors"), Integer.parseInt(request.getParameter("book-year")), 
					request.getParameter("book-volume"), request.getParameter("book-journal"));
			
			XMLDao dao = new XMLDao();
			List<Book> results = dao.search(sr);
			request.setAttribute("searchResults", results);
			request.getRequestDispatcher("results.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
