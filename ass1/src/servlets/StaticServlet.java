package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StaticServlet
 */
@WebServlet("/login")
public class StaticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaticServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<BODY>");
		out.println("<HEAD>");
		out.println("<TITLE>Static Servlet</TITLE>");
		out.println("</HEAD>");
		out.println("<body>");
		out.println("<CENTER><H1>Hello, World</H1></CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		
		login(response, user, pass);
	}
	
	private void login(HttpServletResponse response, String user, String pass) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<BODY>");
		out.println("<HEAD>");
		out.println("<TITLE>Logged In!</TITLE>");
		out.println("</HEAD>");
		out.println("<body>");
		out.println("<CENTER><H1>Hello, " + user + "</H1></CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");
	}
}
