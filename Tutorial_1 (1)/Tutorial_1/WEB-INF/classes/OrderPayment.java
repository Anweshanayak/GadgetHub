import java.io.IOException;
import java.io.*;


public class OrderPayment implements Serializable{
	private int orderId;
	private String userName;
	private String orderName;
	private double orderPrice;
	private String userAddress;
	private String creditCardNo;
	private String street;
	private String state;
	private String city;
	private String zipcode;
	
	public OrderPayment(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo,String street,String city,String state, String zipcode){
		this.orderId=orderId;
		this.userName=userName;
		this.orderName=orderName;
	 	this.orderPrice=orderPrice;
		this.userAddress=userAddress;
	 	this.creditCardNo=creditCardNo;
	 		this.street=street;
	 			this.city=city;
	 				this.state=state;
	 					this.zipcode=zipcode;
	 
		}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street= street;
	}
public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
public String getZip() {
		return zipcode;
	}

	public void setZip(String zipcode) {
		this.zipcode = zipcode;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	

}
