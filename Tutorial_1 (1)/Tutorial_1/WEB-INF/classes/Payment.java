import java.io.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	

		String userAddress=request.getParameter("userAddress");
		String creditCardNo=request.getParameter("creditCardNo");
		String storeid=request.getParameter("storeid");
		String street=request.getParameter("street");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String zipcode=request.getParameter("zipcode");
		int orderId=utility.getOrderPaymentSize()+1;

		System.out.print("the user address is" +userAddress);
		System.out.print(creditCardNo);
		if(!userAddress.isEmpty() && !creditCardNo.isEmpty() )
		{
			//Random rand = new Random(); 
			//int orderId = rand.nextInt(100);
			//int orderId=utility.getOrderPaymentSize()+1;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String orderDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date());
		cal.add(Calendar.DATE, 14);
		String deliveryDate = sdf.format(cal.getTime());

		cal.setTime(new Date());
		cal.add(Calendar.DATE, 9);
		String maxOrderCancellationDate = sdf.format(cal.getTime());

		cal.setTime(new Date());
		cal.add(Calendar.DATE, 14);
		String maxPickupDate = sdf.format(cal.getTime());

			//iterate through each order

			for (OrderItem oi : utility.getCustomerOrders())
			{

				//set the parameter for each column and execute the prepared statement

				utility.storePayment(orderId,oi.getName(),oi.getPrice(),userAddress,creditCardNo,street,city,state,zipcode);
			}

			//remove the order details from cart after processing
			MySqlDataStoreUtilities.storelocations(storeid,street,city,state,zipcode);
			OrdersHashMap.orders.remove(utility.username());	
			utility.printHtml("Header.html");
			//utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='container'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h2>Your Order is stored.");
			//pw.print("&nbsp&nbsp");  
			//pw.print("is stored ");
			pw.print("<br>Your Order Number is "+(orderId));
				pw.print("<br>Your Order Delivery/Pickup Date is "+(deliveryDate));
					pw.print("<br>Your Maximum Order Cancellation Date is "+(maxOrderCancellationDate));
					
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			//utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='container'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
