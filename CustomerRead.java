import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomerRead  
{
	public ArrayList<ExistingCustomersOrders> readCustomerfile() 
	{	
		ArrayList<ExistingCustomersOrders> list=new ArrayList<ExistingCustomersOrders>();
		BufferedReader br=null;
		try
		{
			br = new BufferedReader(new FileReader("Customers.txt"));
			String str=null;
			while( (str=br.readLine())!=null)
			{
				String orders[]=str.split(",");
				int i=0;
				String orderId=orders[i];
				++i;
				String customerName=orders[i];
				++i;
				String customerId=orders[i];
				if (!customerId.matches("[0-9]+") || customerId.length()!= 10) 
				{
				    try 
				    {
						throw new InvalidCustomerIdException("Invalid Customer Id "+"("+customerId+")"+" Found....Skipping this data");
					} 
				    catch (InvalidCustomerIdException e) 
				    {
						System.err.println(e.getMessage());
						continue;
					}
				}
				
				++i;
				String timeStamp=orders[i];
				++i;
				ArrayList<String> items=new ArrayList<String>();
				while(i<orders.length) 
				{
					items.add(orders[i]);
					++i;
				}
				
				ExistingCustomersOrders eco=new ExistingCustomersOrders(orderId, customerName, customerId, timeStamp ,items);
				list.add(eco);
			}
			
		}
		catch (FileNotFoundException e) 
		{
			System.err.println("File not found");
		} 
		catch (IOException e) 
		{
			System.err.println("Unable to read the file.");
		} 
		finally 
		{
			try 
			{
				br.close();
			} catch (IOException e) 
			{
				System.err.println("Error in closing BufferedReader");
			}
		}
		
		return list;
	}
}



