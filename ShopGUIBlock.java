package view;


import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.sql.Timestamp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.ExistingCustomersOrders;
import connection.AddOrderToQueueThread;
import connection.MenuRead;
import connection.ServingStaffThread1;
import connection.ServingStaffThread2;
import connection.TestMain;

public class ShopGUIBlock extends JFrame implements ActionListener {
	
	JPanel newPanel;
	JPanel timePanel;
	JPanel panel1;
	JPanel panel2;
	JPanel mainPanel;
	JPanel statusPanel;
	JPanel orderPanel;
	JPanel server1Panel;
	JPanel server2Panel;
	JPanel tablePanel;
	JLabel orderstatus;
	JLabel serverinfo;
	JLabel timer;
	JLabel timep;
	JButton apply;
	JButton newOrder;
	JButton genreport;
	JTextArea displayList;
	JTextArea server1;
	JTextArea server2;
	JScrollPane scroll1;
	JScrollPane scroll2;
	JScrollPane scroll3;
	String itemreport="";
	
	Choice c1;
	public ArrayList<String> itemlist;
	Map<String,Integer> itemT;

	JScrollPane scrollTable;
	
	JTable table;
	DefaultTableModel dtm;
	
	private static Queue<ExistingCustomersOrders> que;
	
	AddOrderToQueueThread ao;	
	ServingStaffThread1 st1;	
	ServingStaffThread2 st2;
	
	
	public ShopGUIBlock() throws InterruptedException
	{
		clock();
		que=new LinkedList<>();
		timer=new JLabel();
		
		panel2=new JPanel();
		timePanel=new JPanel();
		timep=new JLabel("Preparation Time: ");
		c1=new Choice();
		c1.add("1 second");
		for(int i=2;i<=10;i++)
		{
			c1.add(i+" seconds");
		}
		apply=new JButton("Apply");
		panel2.add(timep);
		panel2.add(c1);
		panel2.add(apply);
		
		
		
		
		
	
		
		itemT=new LinkedHashMap<String,Integer>();
		newOrder=new JButton();
		newOrder.setText("New Order");
		genreport=new JButton();
		genreport.setText("Generate Report");
		
		orderstatus=new JLabel("Order Status");
		serverinfo=new JLabel("Server Info");
		mainPanel=new JPanel();
		tablePanel=new JPanel();
		statusPanel=new JPanel();
		orderPanel=new JPanel();
		server1Panel=new JPanel();
		server2Panel=new JPanel();
		newPanel=new JPanel();
		panel1=new JPanel();
		
		displayList=new JTextArea(10,50);
		server1=new JTextArea(10,50);
		server2=new JTextArea(10,50);
		
		scroll1=new JScrollPane(displayList);
		scroll2=new JScrollPane(server1);
		scroll3=new JScrollPane(server2);
		table=new JTable();
		dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
    	
    	dtm.addColumn("ORDER ID");
    	dtm.addColumn("ITEMS");
    	dtm.addColumn("STATUS");
		
		
		table.setModel(dtm);
		
		scrollTable = new JScrollPane();
		scrollTable.setViewportView(table);

		
		
		
		
		timePanel.add(timer);
		
		
		orderPanel.add(scroll1);
		server1Panel.add(scroll2);
		server2Panel.add(scroll3);
		
		
		
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.PAGE_AXIS));
		statusPanel.add(serverinfo);
		statusPanel.add(orderPanel);
		statusPanel.add(server1Panel);
		statusPanel.add(server2Panel);
		
			
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS));
		tablePanel.add(panel2);
		tablePanel.add(orderstatus);
		tablePanel.add(scrollTable);
		
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(tablePanel);
		mainPanel.add(statusPanel);
		
		panel1.setLayout(new FlowLayout());
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.PAGE_AXIS));
		newPanel.add(timePanel);
		panel1.add(newOrder);
		panel1.add(genreport);
		mainPanel.add(panel1);
		newPanel.add(mainPanel);
		
		this.add(newPanel);
		
		newOrder.addActionListener(this);
		genreport.addActionListener(this);
		apply.addActionListener(this);
		
		setTitle("ORDERS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    pack();
	    setVisible(true);
	    
	    ao=new AddOrderToQueueThread(this);
		
	    ao.start();
		ao.join();
		
		que=AddOrderToQueueThread.getQueue();
		
	
		itemreport+="There are currently "+que.size()+" waiting in the queue:\n";
		
		/*for(ExistingCustomersOrders eco:que) 
	    {
			
			itemreport+=String.format("%-20s",eco.getCustomerName());
			itemreport+=eco.getItems().size()+" items";
			itemreport+="\n";
			displayList.setText(itemreport);
			String row[]={eco.getOrderId(),Integer.toString(eco.getItems().size()),"Preparing"};
			dtm.addRow(row);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
					
		}*/
	
		
	}
	public void appendQUE(ExistingCustomersOrders eco)
	{
		itemreport+=String.format("%-20s",eco.getCustomerName());
		itemreport+=eco.getItems().size()+" items";
		itemreport+="\n";
		displayList.setText(itemreport);
		String row[]={eco.getOrderId(),Integer.toString(eco.getItems().size()),"Preparing"};
		dtm.addRow(row);
		
		
	}
	public void appendQUEUE(ArrayList<String> items,String cid,String name)
	{
		itemreport+=String.format("%-20s",name);
		itemreport+=items.size()+" items";
		itemreport+="\n";
		displayList.setText(itemreport);
		String row[]={cid,Integer.toString(items.size()),"Preparing"};
		dtm.addRow(row);
	}
	public void appendServer1(String orderID,String report)
	{
		for(int k=0;k<table.getRowCount();k++)
		   {
				
			String id=(String) table.getValueAt(k, 0);
			if(id.equals(orderID))
			{
				table.setValueAt("Delivered",k,2);
			}
			
		   }
		if(que.isEmpty())
		{
		   JOptionPane.showMessageDialog(this,"Log file has been created");
		   PrintStream console=System.out;
		   System.setOut(console);
		 }
		
		server1.setText(report);
	}
	public void appendServer2(String orderID,String report)
	{

		for(int k=0;k<table.getRowCount();k++)
		   {
				
			String id=(String) table.getValueAt(k, 0);
			if(id.equals(orderID))
			{
				table.setValueAt("Delivered",k,2);
			}
			
		   }
		if(que.isEmpty())
		{
			
		     JOptionPane.showMessageDialog(this,"Log file has been created");
		     PrintStream console=System.out;
		     System.setOut(console);
		  
		}
		
		server2.setText(report);
	}
	public void clock()
	{
		Thread clock=new Thread()
		{
			public void run()
			{
				try{
					for(;;){
				Calendar cal=new GregorianCalendar();
				int day=cal.get(Calendar.DAY_OF_MONTH);
				int month=cal.get(Calendar.MONTH);
				int year=cal.get(Calendar.YEAR);
				
				int second=cal.get(Calendar.SECOND);
				int minute=cal.get(Calendar.MINUTE);
				int hour=cal.get(Calendar.HOUR);
						
				timer.setText(hour+":"+minute+":"+second+"      "+day+"/"+month+"/"+year);
						
						Thread.sleep(1000);
					}
				}catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			}
		};
		clock.start();
	}
	  public void actionPerformed(ActionEvent e)
	    {
		  if(e.getSource()==apply)
		  {
			  int t=Integer.parseInt(c1.getItem(c1.getSelectedIndex()).substring(0,1));
			  st1=new ServingStaffThread1(this,t);
			  st2=new ServingStaffThread2(this,t);
			  st1.setName("Serving Staff Member 1");
			  st1.start();
				
			  st2.setName("Serving Staff Member 2");
			  st2.start();
		  }
		  if(e.getSource()==newOrder)
		  {
			  
			  MenuRead mr=new MenuRead();
			  ShopGUI gui=new ShopGUI(mr.readMenuFile(),this);
		  }
		  if(e.getSource()==genreport)
		  {
			  String report="";
	    		try {
	    			
	    			BufferedWriter writer = new BufferedWriter(new FileWriter("Report.txt"));
	    			
	    			report+=String.format("%-10s","ORDER ID");
	    			report+=String.format("%-10s","ITEMS");
	    			report+=String.format("%-15s","STATUS");
	    			
	    			report+="\r\n--------------------------------------------------\r\n";
	    			String[] s={"10","10","15"};
	    			int g=0;
	    			writer.write(report);
	    			
	    			for(int j=0;j<table.getRowCount();j++)
	    			{
	    				g=0;
	    				for(int k=0;k<table.getColumnCount();k++)
	    				{
	    					writer.write(String.format("%-"+s[g]+"s",table.getModel().getValueAt(j, k)));
	    					g++;
	    				}
	    				writer.write("\r\n      \r\n");
	    				
	    			}
	    			writer.write("\r\n--------------------------------------------------\r\n");
	    			/*if(total>=50)
	    			{
	    				dis=0.05*total;
	    				total-=dis;
	    				writer.write(String.format("%-37s","DISCOUNT"));
	    				writer.write(String.format("%.2f",(dis))+"\r\n");
	    			}
	    			writer.write(String.format("%-37s","TOTAL"));
	    			writer.write(Double.toString(total));
	    			
	    			a++;
	    			id++;*/
	    			writer.close();
	    			
	    			
	    		}
	    		catch(FileNotFoundException ex) {
	    			System.out.println("The file to write the report to is not found");
	    		}
	    		catch (IOException ex) {
	    			
	    			ex.printStackTrace();
	    		}
	    		
	    		JOptionPane.showMessageDialog(this,"Report has been generated");
	    		System.exit(0);
	    		}
		  }
	    

}
