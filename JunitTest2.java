import org.junit.Test;
import static org.junit.Assert.*;

public class JunitTest2 {
	 static MenuItem mt;
	 static String order = "Fish Fingers";
	
	
	@Test 
	public void ID()
	{
		mt=new MenuItem("F110","Fish Fingers","Food","Fish",30);
		assertEquals("F110",mt.getItemId());
	}
	

}
