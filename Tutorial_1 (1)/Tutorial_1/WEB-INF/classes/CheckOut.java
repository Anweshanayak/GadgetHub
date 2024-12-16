import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;

import java.sql.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
        HttpSession session=request.getSession(); 

		//get the order product details	on clicking submit the form will be passed to submitorder page	
		
	    String userName = session.getAttribute("username").toString();
        String orderTotal = request.getParameter("orderTotal");
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='CheckOut' action='Payment' method='post'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
		pw.print(userName);
		pw.print("</td></tr>");
		// for each order iterate and display the order name price
		for (OrderItem oi : utility.getCustomerOrders()) 
		{
			pw.print("<tr><td> Product Purchased:</td><td>");
			pw.print(oi.getName()+"</td></tr><tr><td>");
			pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
			pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
			pw.print("Product Price:</td><td>"+ oi.getPrice());
			pw.print("</td></tr>");
		}
		pw.print("<tr><td>");
        pw.print("Total Order Cost</td><td>"+orderTotal);
		pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
		pw.print("</td></tr></table><table><tr></tr><tr></tr>");
		pw.print("<tr><td>");
     	pw.print("User ID:</td>");
		pw.print("<td><input type='text' name='userid'>");
		pw.print("</td></tr>");	


			pw.print("<tr><td>");
     	pw.print("Customer Name:</td>");
		pw.print("<td><input type='text' name='customername'>");
		pw.print("</td></tr>");	

int orderId=utility.getOrderPaymentSize()+1;
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String orderDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date());
		cal.add(Calendar.DATE, 14);
		String deliveryDate = sdf.format(cal.getTime());
		pw.print("<tr><td>");
     	pw.print("Credit Card Number:</td>");
		pw.print("<td><input type='text' name='creditCardNo'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
	    pw.print("Customer Address: </td>");
		pw.print("<td><input type='text' name='userAddress'>");
        pw.print("</td></tr>");


			pw.print("<tr><td>");
     	pw.print("Order id :</td>");
		pw.print("<td>"+orderId+"");
		pw.print("</td></tr>");	


			pw.print("<tr><td>");
     	pw.print("Order Date:</td>");
		pw.print("<td>"+orderDate);
		pw.print("</td></tr>");	

			pw.print("<tr><td>");
     	pw.print("Delivery Date:</td>");
		pw.print("<td>"+deliveryDate);
		pw.print("</td></tr>");	

pw.print("<tr><td>");
		pw.print("<form action='/action_page.php'>");
		pw.print("<label for='delivery'>Choose your preferance:</label></td>");
		pw.print("<td><select name='delivery' id='delivery'>"+
		"<option value='pickup'>Pick Up from Store</option>"+
		"<option value='athome'>Home Delivery</option>"+
		"</select>");
		pw.print("<br><br>");
		pw.print("</td></tr>");

		
pw.print("<tr><td>");


pw.print("<tr><td>");
	    pw.print("Quantity: </td>");
		pw.print("<td>"+utility.CartCount());
        pw.print("</td></tr>");


pw.print("<tr><td>");
	    pw.print("Shipping cost for home delivery: </td>");
		pw.print("<td>$5.00");
        pw.print("</td></tr>");

pw.print("<tr><td>");
	    pw.print("Discount: </td>");
		pw.print("<td>5%");
        pw.print("</td></tr>");
        pw.print("<tr><td>");
	    pw.print("Total Sales: </td><td>"+utility.CartCount());
		pw.print("</td></tr>");

    pw.print("<tr><td>");
		pw.print("<form action='/action_page.php'>");
		pw.print("<label for='storeid'>Store Id:</label></td>");
		pw.print("<td><select name='storeid' id='storeid'>"+
		"<option value=''> </option>"+
		"<option value='2901 S King Dr' id='storeid'>2901 S King Dr</option>"+
		"<option value='Elk Grove Village' id='storeid'>Elk Grove Village</option>"+
		"<option value='Des Plaines' id='storeid'>Des Plaines</option>"+
		"<option value='1251 S Halsted St' id='storeid'>1251 S Halsted St</option>"+
		"<option value='500 W Madison St' id='storeid'>500 W Madison St</option>"+
		"<option value='Burling Street, South' id='storeid'''>Burling Street, South</option>"+
		"<option value='50 N Western Ave' id='storeid'>50 N Western Ave</option>"+
		"<option value='Broadwat Street, North' id='storeid'>Broadwat Street, North</option>"+
		"<option value='S Eastern Ave' id='storeid'>5 S Eastern Ave</option>"+
		"<option value='N Eastern Ave' id='storeid'>5 N Eastern Ave</option>"+
		"</select>");
		pw.print("<br><br>");
		pw.print("</td></tr>");



        pw.print("<tr><td>");
     	pw.print("Street No: </td>");
		pw.print("<td><input type='text' name='street'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
     	pw.print("City:  </td>");
		pw.print("<td><input type='text' name='city'>");
		pw.print("</td></tr>");
pw.print("<tr><td>");
     	pw.print("State: </td>");
		pw.print("<td><input type='text' name='state'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
     	pw.print("Zip-Code: </td>");
		pw.print("<td><input type='text' name='zipcode'>");
		pw.print("</td></tr>");
        pw.print("</td></tr>");

        
		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy'>");
        pw.print("</td></tr></table></form>");
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	    }
        catch(Exception e)
		{
         System.out.println(e.getMessage());
		}  			
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    }
}
