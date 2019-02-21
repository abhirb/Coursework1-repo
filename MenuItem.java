public class MenuItem 
{
	private String itemId;
	private String itemName;
	private String itemCategory;
	private String itemDescription;
	private double itemPrice;
		
	public MenuItem(String itemId, String itemName, String itemCategory, String itemDescription, double itemPrice) 
	{
		this.itemId = itemId;
		this.itemName=itemName;
		this.itemCategory=itemCategory;
		this.itemDescription=itemDescription;
		this.itemPrice=itemPrice;
	}
		
	public String getItemId() 
	{
		return itemId;
	}

	public void setItemId(String itemId) 
	{
		this.itemId = itemId;
	}

	public String getItemName() 
	{
		return itemName;
	}

	public void setItemName(String itemName) 
	{
		this.itemName = itemName;
	}

	public String getItemCategory() 
	{
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) 
	{
		this.itemCategory = itemCategory;
	}
		
	public String getItemDescription() 
	{
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) 
	{
		this.itemDescription = itemDescription;
	}
		
	public double getItemPrice() 
	{
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) 
	{
		this.itemPrice = itemPrice;
	}
	
	public String toString() 
	{
		return this.getItemId()+","+this.getItemName()+","+this.getItemCategory()+","
		+this.getItemDescription()+","+this.getItemPrice();
	}
}	