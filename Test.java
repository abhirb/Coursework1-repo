import java.text.SimpleDateFormat;
import java.util.Date;

public class Test 
{

	public static void main(String[] args) 
	{
		MenuRead mr=new MenuRead();
		//System.out.println(mr.readMenuFile());
		ShopGUI gui=new ShopGUI(mr.readMenuFile());
		//System.out.println(mr.readMenuFile());
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    //Date date = new Date();  
	    //System.out.println(formatter.format(date));
	    CustomerRead cr=new CustomerRead();
	    System.out.println(cr.readCustomerfile());
	}

}
