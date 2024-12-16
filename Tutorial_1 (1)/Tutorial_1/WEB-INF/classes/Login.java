import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")

public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		HashMap<String, User> hm=new HashMap<String, User>();
		//String TOMCAT_HOME = System.getProperty("catalina.home");
		
		try
		{		
    //       FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\UserDetails.txt"));
    //       ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
		  // hm = (HashMap)objectInputStream.readObject();
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
				
		}
		User user = hm.get(username);
		if(user!=null)
		{
		 String user_password = user.getPassword();
		 if (password.equals(user_password)) 
			{
			HttpSession session = request.getSession(true);
			session.setAttribute("username", user.getName());
			session.setAttribute("usertype", user.getUsertype());
			response.sendRedirect("Home");
			return;
			}
		}
		displayLogin(request, response, pw, true);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}


	
	protected void displayLogin(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		//pw.print("<style='background-color: #550403'> <h1 align='center'><a href='' align='center' style='color:black'>Smart <span><style='color:red'>Portables</span></a></h1>");
		pw.print("<div style='background-color:#FFFFFF;' class='post' style='float: none; width: 100%'>");
		pw.print("<h2><a style='font-size: 24px;text-align:center margin>Sign In</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:100%; margin:25px; margin-left: 350px;margin-right: auto;'>");
		if (error)
			pw.print("<h4 style='color:white; font-weight:bold;'>The username, password or user type you entered is incorrect. Please check again!</h4>");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("login_msg")!=null){			
			pw.print("<h4 style='color:red'>"+session.getAttribute("login_msg")+"</h4>");
			session.removeAttribute("login_msg");
		}
		pw.print("<form method='post' action='Login' style='background-color: #FFFFFFF;'>"
				+ "<table style='width:100%; background-color:	#800000; table-border='black' float='center';'><tr text-align='center'><td>"
				+ "<h3 style='color:white'>Username:</h3></td></tr><tr><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3 style='color:white'>Password:</h3></td></tr><tr><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3 style='color:white'>User Type:</h3></td></tr><tr><td><select name='usertype' class='input'><option value='customer' selected>Customer</option><option value='retailer'>Store Manager</option><option value='manager'>Salesman</option></select>"
				+ "</td></tr><tr><td></td></tr><tr><td>"
				+ "<strong><a class='' href='Registration' style='float: right;height: 20px; margin: 20px; text-size:10px; color:white;'>New to Smart Portables? Register here!</a></strong>"
				+ "</td><td>"
				+ "<input type='submit' class='btnbuy' value='SIGN IN' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</td></tr></table>"
				+ "</form>" + "</div></div></div>");
		utility.printHtml("Footer.html");
	}

}
