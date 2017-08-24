package servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.XMLDao;
import extractor.Extractor;
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
		if (action.equals("search")){
			request.getSession().invalidate();
			SearchRequest sr = new SearchRequest(request.getParameter("entry-agency"), request.getParameter("entry-headline"), request.getParameter("entry-date"), 
					request.getParameter("entry-date"), request.getParameter("entry-content"));
			List<List<Entry>> results = dao.search(sr);
			request.getSession().setAttribute("searchResults", results);
			
			/*
			 * build the query string for xml file
			 */
			String expression = "";
			List<String> filters = new ArrayList<String>();
			if (request.getParameter("entry-agency") != null && !request.getParameter("entry-agency").isEmpty()){
				filters.add("agency - "+ request.getParameter("entry-agency"));
			}
			if (request.getParameter("entry-headline") != null && !request.getParameter("entry-headline").isEmpty()){
				filters.add("headline - "+ request.getParameter("entry-headline"));
			}
			if (request.getParameter("entry-date") != null && !request.getParameter("entry-date").isEmpty()){
				filters.add("date - "+ request.getParameter("entry-date"));
			}
			if (request.getParameter("entry-city") != null && !request.getParameter("entry-city").isEmpty()){
				filters.add("city - "+ request.getParameter("entry-city"));
			}
			if (request.getParameter("entry-content") != null && !request.getParameter("entry-content").isEmpty() ){
				filters.add("content - "+ request.getParameter("entry-content"));
			}
			
			if (!filters.isEmpty()){
				expression = String.join(", ", filters);
			}else{
				expression = "No search string detected, showing all results";
			}
			request.getSession().setAttribute("searchStrings", expression);
			
			request.getRequestDispatcher("results.jsp").forward(request, response);
		}
		else if (action.equals("artifact")){
			String address = request.getParameter("address");
			Entry artifact = dao.searchByAddress(address);
			request.setAttribute("artifact", artifact);
			request.getRequestDispatcher("artifact.jsp").forward(request, response);
		}
		else if (action.equals("Extract People")){
			String address = request.getParameter("address");
			Entry artifact = dao.searchByAddress(address);
			try {
				Extractor.highlightPeople(artifact); //highlight the all the names of people in the content
				request.setAttribute("artifact", artifact);
				request.getRequestDispatcher("artifact.jsp").forward(request, response);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
		}
		else if (action.equals("Extract Organisations")){
			String address = request.getParameter("address");
			Entry artifact = dao.searchByAddress(address);
			try {
				Extractor.highlightOrganisations(artifact); //highlight all the organisations in the content
				request.setAttribute("artifact", artifact);
				request.getRequestDispatcher("artifact.jsp").forward(request, response);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
		}
		else if (action.equals("Extract Locations")){
			String address = request.getParameter("address");
			Entry artifact = dao.searchByAddress(address);
			try {
				Extractor.highlightLocations(artifact);
				request.setAttribute("artifact", artifact); //highlight all the locations in the content
				request.getRequestDispatcher("artifact.jsp").forward(request, response);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
		}
		else if (action.equals("Extract Keywords")){
			String address = request.getParameter("address");
			Entry artifact = dao.searchByAddress(address);
			try {
				Extractor.highlightKeywords(artifact);
				request.setAttribute("artifact", artifact); //highlight all the locations in the content
				request.getRequestDispatcher("artifact.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
