package connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import view.ShopGUIBlock;
import model.ExistingCustomersOrders;

public class AddOrderToQueueThread extends Thread
{
	private static Queue<ExistingCustomersOrders> que=new LinkedList<>();
	ShopGUIBlock g;
	public AddOrderToQueueThread(ShopGUIBlock g)
	{
		this.g=g;
	}
	
	public void run() 
	{
		 try
	        {
	            PrintStream fileOut = new PrintStream(new File("./out.txt"));
	            System.setOut(fileOut);
	             
	     
		 
		System.out.println("Adding Customer Orders To Queue............");
		CustomerRead cr=new CustomerRead();
		ArrayList<ExistingCustomersOrders> al=cr.readCustomerfile();
		
		for(ExistingCustomersOrders eco:al) 
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			que.add(eco);
			g.appendQUE(eco);
			 
			System.out.println("Adding "+eco.getCustomerName()+" Order To Queue");
		}
		
		System.out.println("All Orders Added To Queue Successfully");
	
         
        }

	 catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
	}
	
	public static Queue<ExistingCustomersOrders> getQueue() 
	{
		return que;
	}
}
