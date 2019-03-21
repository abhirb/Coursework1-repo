package connection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;

import view.ShopGUIBlock;
import model.ExistingCustomersOrders;

public class ServingStaffThread2 extends Thread
{
	Queue<ExistingCustomersOrders> que=AddOrderToQueueThread.getQueue();
	Map<String,Integer> itemT;
	public ArrayList<String> itemlist;
	String report="Server 2\n\n";
	ShopGUIBlock g;
	int t;
	public ServingStaffThread2(ShopGUIBlock g,int t)
	{
		this.g=g;
		itemT=new LinkedHashMap<String,Integer>();
		this.t=t;
	}
	public void run() 
	{
		try {
			Thread.sleep(t*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		while(!que.isEmpty()) 
		{
            ExistingCustomersOrders order1=que.peek(); 
            report+="Processing "+order1.getCustomerName()+" order.\n";
            for(ExistingCustomersOrders eco:que) 
        	    {
                boolean f1=true;
        		if(eco.getItems()!=null)
        		{
        			
        			if(eco.getCustomerName().equals(order1.getCustomerName()))
        			{
        				
        			itemlist=eco.getItems();
        			for(int i=0;i<itemlist.size();i++)
        			{
        				if(itemT.isEmpty())
        				{
        					itemT.put(itemlist.get(i), 1);
        				}
        				else
        				{					
        					for(String name1: itemT.keySet())
        					{    							
        					if(name1.equals(itemlist.get(i)))
        					{
        						f1=false;
        						itemT.replace(name1,itemT.get(name1),itemT.get(name1)+1);
        					}
        					}
        				}
        				if(f1)
        				{
        					itemT.put(itemlist.get(i),1);
        					
        				}
        			}
        			}
        		}
        		
        		
        		
        		for(String name2: itemT.keySet())
        		{
        			report+=itemT.get(name2).toString()+" "+name2;
        			report+="\n";
        		}
        		itemT=new LinkedHashMap<String,Integer>();
        	    }
    		
        	   
                //report+=order.getItems()+"\n\n";
                ExistingCustomersOrders order2=que.poll();
            report+="\n";    
            g.appendServer2(order2.getOrderId(),report);
            System.out.println(Thread.currentThread().getName()+" is serving order of "+order2.getCustomerName() ); 
    		
 
            try {
				Thread.sleep(t*2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            
		}
	}
}
