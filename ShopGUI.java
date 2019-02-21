import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ShopGUI extends JFrame implements ActionListener{
	
	private ArrayList<MenuItem> list;
	JTextField searchFood;
	JTextField searchCustomer;
	JButton add;
	JButton delete;
	JButton displayMenu;
	JScrollPane scrollTable;
	JTextArea display;
	JTable table;
	JRadioButton food;
	JRadioButton beverages;
	JRadioButton dessert;
	JRadioButton fish;
	JRadioButton chicken;
	JRadioButton fries;
	JRadioButton sandwich1;
	JRadioButton sandwich2;
	
	JRadioButton coffee;
	JRadioButton latte;
	JRadioButton tea;
	JRadioButton milkshake;
	JRadioButton juice;
	
	JRadioButton cake;
	JRadioButton croissant;
	JRadioButton souffle;
	JRadioButton icecream;
	JRadioButton brownie;
	
	DefaultTableModel dtm;
	JPanel panel;
	JPanel catPanel;
	JPanel menuPanel;
	JPanel itemPanel1;
	JPanel itemPanel2;
	JPanel itemPanel3;
	JPanel itemsPanel;
	JPanel billPanel;
	JPanel orderPanel;
	JPanel sumPanel;
	
	JButton bill;
	JButton newOrder;
	//JButton printSummary;
	ButtonGroup group1;
	ButtonGroup group2;
	ButtonGroup group3;
	int c=0;
	int a=0;
	int id=100;
	String[] cid=new String[100];
	String itemlist="";
	double total=0.0;
	
	double itemPrice=0.0;
	Map<String,Integer> itemQ;
	Map<String,Integer> itemT;
	
	
	public ShopGUI(ArrayList<MenuItem> list)
	{
		this.list=list;
		itemQ=new LinkedHashMap<String,Integer>();
		itemT=new LinkedHashMap<String,Integer>();
		
		setTitle("Shop");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener( new Close() );
		panel=new JPanel();
		panel.setSize(500,500);
		
		
		catPanel=new JPanel();
		catPanel.setLayout(new BoxLayout(catPanel, BoxLayout.PAGE_AXIS));
		
		food=new JRadioButton();
		food.setText("FOOD");
		beverages=new JRadioButton();
		beverages.setText("BEVERAGES");
		dessert=new JRadioButton();
		dessert.setText("DESSERT");
		
		ButtonGroup group=new ButtonGroup();
		group.add(food);
		group.add(beverages);
		group.add(dessert);
		JLabel label1=new JLabel("CATEGORIES");
		catPanel.add(label1);
		
		catPanel.add(food);
		catPanel.add(beverages);
		catPanel.add(dessert);
		
		menuPanel=new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
		
		add=new JButton();
		add.setText("Add Item ->");
		
		delete=new JButton();
		delete.setText("<- Remove Item");
		
		
		bill=new JButton();
		bill.setText("Generate Bill");
	
		
		JLabel label2=new JLabel("MENU ITEMS");
		menuPanel.add(label2);
		
		itemPanel1=new JPanel();
		itemPanel2=new JPanel();
		itemPanel3=new JPanel();
		itemPanel1.setLayout(new BoxLayout(itemPanel1, BoxLayout.PAGE_AXIS));
		itemPanel2.setLayout(new BoxLayout(itemPanel2, BoxLayout.PAGE_AXIS));
		itemPanel3.setLayout(new BoxLayout(itemPanel3, BoxLayout.PAGE_AXIS));
		
		fish=new JRadioButton();
		fish.setText("Fish Fingers");
		chicken=new JRadioButton();
		chicken.setText("Crispy Chicken Fingers");
		fries=new JRadioButton();
		fries.setText("French Fries");
		sandwich1=new JRadioButton();
		sandwich1.setText("Cheese Sandwich");
		sandwich2=new JRadioButton();
		sandwich2.setText("Chicken Sandwich");
		
		group1=new ButtonGroup();
		group1.add(fish);
		group1.add(chicken);
		group1.add(fries);
		group1.add(sandwich1);
		group1.add(sandwich2);
		
		coffee=new JRadioButton();
		coffee.setText("Coffee");
		latte=new JRadioButton();
		latte.setText("Latte");
		tea=new JRadioButton();
		tea.setText("Masala Tea");
		milkshake=new JRadioButton();
		milkshake.setText("Milkshake");
		juice=new JRadioButton();
		juice.setText("Fresh Juice");
		
		group2=new ButtonGroup();
		group2.add(coffee);
		group2.add(latte);
		group2.add(tea);
		group2.add(milkshake);
		group2.add(juice);
		
		
		cake=new JRadioButton();
		cake.setText("Chocolate Cake");
		croissant=new JRadioButton();
		croissant.setText("Croissant");
		souffle=new JRadioButton();
		souffle.setText("Vanilla Souffle");
		icecream=new JRadioButton();
		icecream.setText("Fried Ice-Cream");
		brownie=new JRadioButton();
		brownie.setText("Chocolate Brownie");
		
		group3=new ButtonGroup();
		group3.add(cake);
		group3.add(croissant);
		group3.add(souffle);
		group3.add(icecream);
		group3.add(brownie);
		
		
		itemPanel1.add(fish);
		itemPanel1.add(chicken);
		itemPanel1.add(fries);
		itemPanel1.add(sandwich1);
		itemPanel1.add(sandwich2);
		
		itemPanel2.add(coffee);
		itemPanel2.add(latte);
		itemPanel2.add(tea);
		itemPanel2.add(milkshake);
		itemPanel2.add(juice);
		
		itemPanel3.add(cake);
		itemPanel3.add(croissant);
		itemPanel3.add(souffle);
		itemPanel3.add(icecream);
		itemPanel3.add(brownie);
		
		food.addActionListener(this);
		beverages.addActionListener(this);
		dessert.addActionListener(this);
		
		table=new JTable();
		
		
		dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
    	
    	dtm.addColumn("#");
    	dtm.addColumn("ITEM");
    	dtm.addColumn("QUANTITY");
    	dtm.addColumn("PRICE");
		
		
		table.setModel(dtm);
		
		scrollTable = new JScrollPane();
		scrollTable.setViewportView(table);
		
		itemsPanel=new JPanel();
		itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.PAGE_AXIS));
		
		itemsPanel.add(add);
		itemsPanel.add(delete);
		
		newOrder=new JButton();
		newOrder.setText("New Order");
		
		//printSummary=new JButton();
		//printSummary.setText("Print Summary");
		
		sumPanel=new JPanel();
		sumPanel.add(newOrder);
		//sumPanel.add(printSummary);
		
		
		billPanel=new JPanel();
		billPanel.setLayout(new BoxLayout(billPanel, BoxLayout.PAGE_AXIS));
		billPanel.add(sumPanel);
		billPanel.add(scrollTable);
		billPanel.add(bill);
		
		GroupLayout layout=new GroupLayout(panel);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		panel.setLayout(layout);
		
		newOrder.addActionListener(this);
		
		add.addActionListener((ActionListener) this);
		delete.addActionListener(this);
		
		bill.addActionListener(this);
		//printSummary.addActionListener(this);
		
		layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(catPanel).addComponent(menuPanel).addComponent(itemsPanel).addComponent(billPanel));
	    layout.setVerticalGroup(layout.createSequentialGroup().addComponent(catPanel).addComponent(menuPanel).addComponent(itemsPanel).addComponent(billPanel));
			
		add(panel);
	
	    pack();
	    setVisible(true);
	    
	  
	}
	
	    public void actionPerformed(ActionEvent e)
	    {
	    	int i=table.getSelectedRow();
	    	
	    	String item=null;
	       	
	    	if(e.getSource()==add)
	    	{
	    		ArrayList<String> indexes = new ArrayList<String>(itemQ.keySet());
	    		boolean f1=true;
	    		boolean f2=true;
	    		int q=1;
	    		int l=0;
	    		c=c+1;
	    		for(Enumeration<AbstractButton> buttons = group1.getElements(); buttons.hasMoreElements();)
	    		{	 AbstractButton button = buttons.nextElement();

	    			if (button.isSelected()) 
	    			{
	    				
	    				item=button.getText();	
	                }
	    	    }
	    		for(Enumeration<AbstractButton> buttons = group2.getElements(); buttons.hasMoreElements();)
	    		{	 AbstractButton button = buttons.nextElement();

	    			if (button.isSelected()) 
	    			{
	    			 
	                 item=button.getText();	
	                }
	    	    }
	    		for(Enumeration<AbstractButton> buttons = group3.getElements(); buttons.hasMoreElements();)
	    		{	 AbstractButton button = buttons.nextElement();

	    			if (button.isSelected()) 
	    			{
	                    
	    				item=button.getText();	
	                }
	    	    }
	    		if(item!=null)
	    		{
	    			
	    				for(MenuItem items: list)
	    				{
	    				if(items.getItemName().equals(item))
	    				{
	    					if(itemT.isEmpty())
    						{
    							itemT.put(item, 1);
	    					}
    						else
    						{
    							
    							for(String name: itemT.keySet())
    							{    							
    							if(name.equals(item))
    	    					{
    								f2=false;
    								itemT.replace(name,itemT.get(name),itemT.get(name)+1);
    	    					}
    							}
    						}
	    					if(f2)
	    					{
	    						itemT.put(item,1);
	    						
	    					}
	    					if(itemQ.isEmpty())
	    					{
	    						itemQ.put(item, 1);
	    						itemPrice=items.getItemPrice();
	    						String row[]={Integer.toString(c),item,Integer.toString(q),Double.toString(itemPrice)};
	    		    			dtm.addRow(row);
	    						
	    					}
	    					else{
	    						for(String name: itemQ.keySet())
	    						{
	    							
	    							if(name.equals(item))
	    	    					{
	    							
	    								c--;
	    								f1=false;
	    								itemQ.replace(name,itemQ.get(name),itemQ.get(name)+1);
	    								q=itemQ.get(name);
	    	    						itemPrice=items.getItemPrice()*q;
	    	    											
	    	    						l=indexes.indexOf(name);
	    	    						dtm.setValueAt(Integer.toString(q),l, 2);
	    	    						dtm.setValueAt(Double.toString(itemPrice),l, 3);
	    	    						l=0;
	    	    					}
	    						}
	    					if(f1)
	    					{
	    						itemQ.put(item,1);
	    						itemPrice=items.getItemPrice();
	    						String row[]={Integer.toString(c),item,Integer.toString(q),Double.toString(itemPrice)};
	    		    			dtm.addRow(row);
	    					}
	    					}
	    					}
	    					
	    				}
	    			}
	    		else
	    		{
	    			c--;
	    			JOptionPane.showMessageDialog(this,"Select an item to add");
	    		}
	    			
	    	}
	    		
	    	
	    	if(e.getSource()==delete)
	    	{
	    		
	    		if(i>=0)
	    		{
	    			c=c-1;
	    		if(i!=table.getRowCount())
	    		{
	    			if(table.getValueAt(i, 2).equals("1"))
	    			{
	    			dtm.removeRow(i);
	    			for(int k=i;k<table.getRowCount();k++)
	    		   {
	    				
	    			int v=Integer.parseInt((String) (table.getValueAt(k, 0)))-1;
	    			table.setValueAt(Integer.toString(v),k,0);
	    		   }
	    			}
	    		else
	    		{
	    			int v=Integer.parseInt((String) (table.getValueAt(i, 2)))-1;
	    			double p1=Double.parseDouble((String) (table.getValueAt(i, 3)));
	    			double p2=p1-(p1/(v+1));
	    			table.setValueAt(Integer.toString(v), i, 2);
	    			table.setValueAt(Double.toString(p2), i, 3);
	    		}
	    		
	    		}
	    		}
	    		else{
	    			JOptionPane.showMessageDialog(this,"Select an item to remove");
	    		}
	    	}
	    	if(e.getSource()==newOrder)
	    	{
	    		c=0;
	    		dtm.setRowCount(0);
	    		itemQ.clear();
	    		
	    	}
	    	if(e.getSource()==food)
			{
				menuPanel.add(itemPanel1);
				itemPanel1.setVisible(true);
				itemPanel2.setVisible(false);
				itemPanel3.setVisible(false);
				group2.clearSelection();
				group3.clearSelection();
				
			}
	    	else if(e.getSource()==beverages)
			{
				menuPanel.add(itemPanel2);
				itemPanel2.setVisible(true);
				itemPanel1.setVisible(false);
				itemPanel3.setVisible(false);
				group1.clearSelection();
				group3.clearSelection();
				
				
			}
	    	else if(e.getSource()==dessert)
			{
	    		
				menuPanel.add(itemPanel3);
				itemPanel3.setVisible(true);
				itemPanel1.setVisible(false);
				itemPanel2.setVisible(false);
				group2.clearSelection();
				group1.clearSelection();
				
				
			}
	    	if(e.getSource()==bill)	    		
	    	{
	    		String d="CID"+id;
	    		cid[a]=d;
	    	
	    		double dis=0.0;
	    		for(int j=0;j<table.getRowCount();j++)
    			{
    				total+=Double.parseDouble((String) table.getModel().getValueAt(j, 3));
    			}
	    		String billreport="";
	    		try {
	    			
	    			BufferedWriter writer = new BufferedWriter(new FileWriter("Bill.txt"));
	    			billreport+="Customer ID: "+cid[a]+"\r\n\r\n";
	    			billreport+=String.format("%-2s","#");
	    			billreport+=String.format("%-25s","ITEM");
	    			billreport+=String.format("%-10s","QUANTITY");
	    			billreport+="PRICE";
	    			billreport+="\r\n--------------------------------------------------\r\n";
	    			String[] s={"2","25","10","10"};
	    			int g=0;
	    			writer.write(billreport);			
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
	    			if(total>=50)
	    			{
	    				dis=0.05*total;
	    				total-=dis;
	    				writer.write(String.format("%-37s","DISCOUNT"));
	    				writer.write(String.format("%.2f",(dis))+"\r\n");
	    			}
	    			writer.write(String.format("%-37s","TOTAL"));
	    			writer.write(Double.toString(total));
	    			writer.close();
	    			a++;
	    			id++;
	    			
	    		}
	    		catch(FileNotFoundException ex) {
	    			System.out.println("The file to write the report to is not found");
	    		}
	    		catch (IOException ex) {
	    			
	    			ex.printStackTrace();
	    		}
	    		
	    		JOptionPane.showMessageDialog(this,"Bill has been generated");
	    		}
	       	}
	    private class Close extends WindowAdapter {  
	     public void windowClosing(WindowEvent e)
	    {
	    	for(String name: itemT.keySet())
			{
	    		int k=itemT.get(name);
	    		itemlist+="The item "+name+" has been ordered ";
				if(k==1)
				{
				   itemlist+=k+" time."; 	
				}
				else
				{
					itemlist+=k+" times.";
				}
				itemlist+="\n";
			}
	    	itemlist+="The total income: "+total+"\r\n";
	    	itemlist+="Do you want to exit?";
	    	JTextArea displayList = new JTextArea(15,20);
	        displayList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
	        displayList.setEditable(false);
	       // JScrollPane scrollList = new JScrollPane(displayList);
	        //displayList.add(scrollList,BorderLayout.CENTER);
	        displayList.setText(itemlist);
	        int option = JOptionPane.showOptionDialog(ShopGUI.this,displayList,"Exit Dialog",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, null, null,null );  
            if( option == JOptionPane.YES_OPTION ) {  
                System.exit( 0 );  
            }  
            if( option == JOptionPane.NO_OPTION ) {
            	displayList.setText("");
                
            }  
			
	    }
	    }
	    public static double getDiscount(Double total) 
	    {
	    	return 0.05*total;	
	    }
}
	    


