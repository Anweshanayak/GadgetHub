import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WearableTechList")

public class WearableTechList extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		//String name = null;
		//String CategoryName = request.getParameter("maker");
        

HashMap<String,WearableTech> allwts = new HashMap<String,WearableTech> ();


		
		try{
		     allwts = MySqlDataStoreUtilities.getWearableTechs();
		}
		catch(Exception e)
		{
			
		}
String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, WearableTech> hm = new HashMap<String, WearableTech>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.wts);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("Fitness Watches"))
		   {
			 for(Map.Entry<String,WearableTech> entry : SaxParserDataStore.wts.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Fitness Watches"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Fitness Watches";
		   }
		   if(CategoryName.equals("Smart Watches"))
		   {
			 for(Map.Entry<String,WearableTech> entry : SaxParserDataStore.wts.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Smart Watches"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Smart Watches";
		   }
		   if(CategoryName.equals("Headphones"))
		   {
			 for(Map.Entry<String,WearableTech> entry : SaxParserDataStore.wts.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Headphones"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Headphones";
		   }
		   if(CategoryName.equals("Virtual Reality"))
		   {
			 for(Map.Entry<String,WearableTech> entry : SaxParserDataStore.wts.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Virtual Reality"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Virtual Reality";
		   }
		   if(CategoryName.equals("Pet Tracker"))
		   {
			 for(Map.Entry<String,WearableTech> entry : SaxParserDataStore.wts.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Pet Tracker"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Pet Tracker";
		   }
		
		}


		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='container'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Wearable Technology</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, WearableTech> entry : hm.entrySet())
		{
			WearableTech wt = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+wt.getName()+"</h3>");
			pw.print("<strong>$"+wt.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/Wearable Technology/"+wt.getImage()+"' alt='' /></li>");
			
			pw.print("<form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wts'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form>");
pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wts'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+wt.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wts'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");

			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
