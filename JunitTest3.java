import org.junit.Test;
import static org.junit.Assert.*;

public class JunitTest3 {
	 static MenuItem mt;
	 
	
	
	@Test 
	public void ID()
	{
		mt=new MenuItem("B113","Milkshake","Beverages","Shake",20);
		assertEquals("Milkshake",mt.getItemName());
	}
	

}
