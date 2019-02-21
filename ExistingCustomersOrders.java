
import  java.sql.Timestamp;
import java.util.*;
public class ExistingCustomersOrders 
{
	private String orderId;
	private String customerName;
	private String customerId;
	private String timeStamp;
	private ArrayList<String> items;

	public ExistingCustomersOrders()
	{}
	public ExistingCustomersOrders(String orderId, String customerName, String customerId, String timeStamp,ArrayList<String> items) 
	{
		this.orderId = orderId;
		this.customerName=customerName;
		this.customerId=customerId;
		this.timeStamp=timeStamp;
		this.items=items;
	}
		

	public String getOrderId() 
	{
		return orderId;
	}
	
	public void setOrderId(String orderId) 
	{
		this.orderId = orderId;
	}
	
	public String getCustomerId() 
	{
		return customerId;
	}
	
	public void setCustomerId(String customerId) 
	{
		this.customerId = customerId;
	}
	
	public String getCustomerName() 
	{
		return customerName;
	}
	
	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}
	
	public String getTimeStamp() 
	{
		return timeStamp;
	}
	
	public void setTimeStamp(String timeStamp) 
	{
		this.timeStamp = timeStamp;
	}
	
	public ArrayList<String> getItems() 
	{
		return items;
	}
	
	public void setItems(ArrayList<String> items) 
	{
		this.items = items;
	}
	
	public String toString() 
	{
		return this.getOrderId()+","+this.getCustomerName()+","+this.getCustomerId()+","+
		this.getTimeStamp()+","+this.getItems();
	}
	
}	






