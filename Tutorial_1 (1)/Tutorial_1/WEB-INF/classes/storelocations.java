import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class storelocations implements Serializable{
	
	private String storeid;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	
	
	public storelocations (String storeid,String street,String city,String state,String zipcode){
		this.storeid=storeid;
        this.street=street;
        this.state=state;
        this.city=city;
        this.zipcode=zipcode;
	}

public String getStoreid(){
    	return storeid;
    }
    public void setStoreid(String storeid){
    	this.storeid= storeid;
    }
    public String getStreet(){
    	return street;
    }
    public void setStreet(String street){
    	this.street= street;
    }
    public String getState(){
    	return state;
    }
    public void setState(String state){
    	this.state= state;
    }
    public String getCity(){
    	return city;
    }
    public void setCity(String city){
    	this.city= city;
    }
    public String getZipcode(){
    	return zipcode;
    }
    public void setZipcode(String zipcode){
    	this.zipcode= zipcode;
    }


}
