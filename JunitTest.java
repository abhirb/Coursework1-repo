import org.junit.Test;
import static org.junit.Assert.*;

public class JunitTest 
{
	@Test
	public void testDiscount() 
	{
		assertEquals(5.00, ShopGUI.getDiscount(100.0),0);
		
	}
}
