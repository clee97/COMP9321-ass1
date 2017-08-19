package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.XMLDao;
import models.Entry;
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
		XMLDao dao = new XMLDao();
		if (action.equals("home")){
			Integer count = request.getParameter("count") != null ? Integer.parseInt(request.getParameter("count")) : 10;
			List<Entry> randomEntries = dao.randomise(count);
			request.setAttribute("randomEntries", randomEntries);
		}
		else if (action.equals("search")){
			SearchRequest sr = new SearchRequest(request.getParameter("entry-agency"), request.getParameter("entry-headline"), request.getParameter("entry-date"), 
					request.getParameter("entry-date"), request.getParameter("entry-content"));
			
			List<Entry> results = dao.search(sr);
			request.setAttribute("searchResults", results);
			request.getRequestDispatcher("results.jsp").forward(request, response);
		}
		else if (action.equals("artifactPage")){
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
