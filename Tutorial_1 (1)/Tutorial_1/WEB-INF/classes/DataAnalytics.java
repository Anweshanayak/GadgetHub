import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.*;
import javax.servlet.http.HttpSession;
@WebServlet("/DataAnalytics")

public class DataAnalytics extends HttpServlet {
	static DBCollection myReviews;
	/* Trending Page Displays all the Consoles and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
				
		
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View Reviews");
			response.sendRedirect("Login");
			return;
		}
		
						
		utility.printHtml("Header.html");
		//utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='container'>");
		pw.print("<a style='font-size: 24px;'>Data Analytics on Review</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table id='bestseller'>");
		pw.print("<form method='post' action='FindReviews'>");
	
     		pw.print("<table id='bestseller'>");
			pw.print("<tr>");
			pw.print("<td> <input type='checkbox' name='queryCheckBox' value='productName'> Select </td>");
                                pw.print("<td> Product Name: </td>");
                                pw.print("<td>");
                                       pw.print("<select name='productName'>");
				       pw.print("<option value='ALL_PRODUCTS'>All Products</option>");
                                       pw.print("<option value='AppleiPhone12Mini'>AppleiPhone12Mini</option>");
                                       pw.print("<option value='AppleiPhone13Pro'>AppleiPhone13Pro</option>");
                                       pw.print("<option value='AppleiPhoneSE'>AppleiPhoneSE</option>");
                                       pw.print(" <option value='OnePlus9'>OnePlus9</option>");
                                       pw.print("<option value='OnePlus10Pro'>OnePlus10Pro</option>");
									   
							           pw.print("<option value='OnePlus10T'>OnePlus10T</option>");
										pw.print("<option value='OnePlusNordN20'>OnePlusNordN20</option>");
										pw.print("<option value='SamsungGalaxyS10'>SamsungGalaxyS10</option>");
										pw.print("<option value='SamsungGalaxyS22'>SamsungGalaxyS22</option>");
										pw.print("<option value='SamsungGalaxyZ'>SamsungGalaxyZ</option>");
										
										pw.print("<option value='AppleMacBookAir'>AppleMacBookAir</option>");
										pw.print("<option value='AppleMacBookPro'>AppleMacBookPro</option>");
										pw.print("<option value='DellInspiron15'>DellInspiron15</option>");
										pw.print("<option value='DellInspiron3510'>DellInspiron3510</option>");
										pw.print("<option value='DellLatitude7480'>DellLatitude7480</option>");
										pw.print("<option value='HP14'>HP14</option>");
										pw.print("<option value='HPPavilion15.6FHD'>HPPavilion15.6FHD</option>");
										pw.print("<option value='HPStream'>HPStream</option>");
										pw.print("<option value='Dosmarter'>Dosmarter</option>");
										pw.print("<option value='HAFURY'>HAFURY</option>");
										pw.print("<option value='MorePro'>MorePro</option>");
										pw.print("<option value='Amazfit'>Amazfit</option>");
										pw.print("<option value='Samsung'>Samsung</option>");
										pw.print("<option value='6S'>6S</option>");
										pw.print("<option value='BeatsStudio3'>BeatsStudio3</option>");
										pw.print("<option value='Sony'>Sony</option>");
										pw.print("<option value='RokidAir'>RokidAir</option>");
										pw.print("<option value='HTC VIVEPro'>HTC VIVEPro</option>");

pw.print("<option value='MetaQuest2'>MetaQuest2</option>");

pw.print("<option value='JiobitGPSlocationmonitor'>JiobitGPSlocationmonitor</option>");

pw.print("<option value='AppleAirTagPetTracker'>AppleAirTagPetTracker</option>");

pw.print("<option value='GoogleHomeMini'>GoogleHomeMini</option>");

pw.print("<option value='GoogleHome'>GoogleHome</option>");
pw.print("<option value='GoogleNest'>GoogleNest</option>");
pw.print("<option value='EchoDot(4thGen)'>EchoDot(4thGen)</option>");
pw.print("<option value='OnePlusBuds'>OnePlusBuds</option>");

pw.print("<option value='OnePlusWarpCharger'>OnePlusWarpCharger</option>");

pw.print("<option value='LaptopStand'>LaptopStand</option>");

pw.print("<option value='EchoDot(3rd Gen)'>EchoDot(3rd Gen)</option>");
           pw.print("<option value='WirelessMouse'>WirelessMouse</option>");
           pw.print("<option value='LaptopCase'>LaptopCase</option>");
                                pw.print("</td>");
			pw.print("<tr>");
			     pw.print("<td> <input type='checkbox' name='queryCheckBox' value='productPrice'> Select </td>");
                                pw.print("<td> Product Price: </td>");
                              pw.print(" <td>");
                                  pw.print("  <input type='number' name='productPrice' value = '0' size=10  /> </td>");
						pw.print("<td>");
					pw.print("<input type='radio' name='comparePrice' value='EQUALS_TO' checked> Equals <br>");
					pw.print("<input type='radio' name='comparePrice' value='GREATER_THAN'> Greater Than <br>");
					pw.print("<input type='radio' name='comparePrice' value='LESS_THAN'> Less Than");
					pw.print("</td></tr>");				
                            
  			
			pw.print("<tr><td> <input type='checkbox' name='queryCheckBox' value='reviewRating'> Select </td>");
                               pw.print(" <td> Review Rating: </td>");
                               pw.print(" <td>");
                                   pw.print(" <select name='reviewRating'>");
                                       pw.print(" <option value='1' selected>1</option>");
                                       pw.print(" <option value='2'>2</option>");
                                       pw.print(" <option value='3'>3</option>");
                                     pw.print("   <option value='4'>4</option>");
                                      pw.print("  <option value='5'>5</option>");
                                pw.print("</td>");
				pw.print("<td>");
				pw.print("<input type='radio' name='compareRating' value='EQUALS_TO' checked> Equals <br>");
				pw.print("<input type='radio' name='compareRating' value='GREATER_THAN'> Greater Than"); 
			pw.print("</td></tr>");
			
			pw.print("<tr>");
								pw.print("<td> <input type='checkbox' name='queryCheckBox' value='retailerCity'> Select </td>");
                                pw.print("<td> Retailer City: </td>");
                                pw.print("<td>");
                                pw.print("<input type='text' name='retailerCity' /> </td>");
								
                            pw.print("</tr>");
							
							 pw.print("<tr>");
								pw.print("<td> <input type='checkbox' name='queryCheckBox' value='retailerZipcode'> Select </td>");
                               pw.print(" <td> Retailer Zip code: </td>");
                               pw.print(" <td>");
                                    pw.print("<input type='text' name='retailerZipcode' /> </td>");
					        pw.print("</tr>");
				pw.print("<tr><td>");
					pw.print("<input type='checkbox' name='extraSettings' value='GROUP_BY'> Group By");
								pw.print("</td>");
								pw.print("<td>");
								pw.print("<select name='groupByDropdown'>");
                                pw.print("<option value='GROUP_BY_CITY' selected>City</option>");
                                pw.print("<option value='GROUP_BY_PRODUCT'>Product Name</option>");                                        
                                pw.print("</td><td>");
								pw.print("<input type='radio' name='dataGroupBy' value='Count' checked> Count <br>");
								pw.print("<input type='radio' name='dataGroupBy' value='Detail'> Detail <br>");
								pw.print("</td></tr>");
								
									
			
						pw.print("<tr>");
                                pw.print("<td colspan = '4'> <input type='submit' value='Find Data' class='btnbuy' /> </td>");
                            pw.print("</tr>");
							
							
		pw.print("</table>");	
		pw.print("</div></div></div>");			
		utility.printHtml("Footer.html");
	
	
	
			
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
