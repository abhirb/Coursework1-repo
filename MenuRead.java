import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuRead 
{
	ArrayList<MenuItem> list;
	
	public MenuRead()
	{
		list=new ArrayList<MenuItem>();
	}
	
	public ArrayList<MenuItem> readMenuFile() 
	{		
		try
		{
			
			String filename="FoodMenu.txt";
			File file=new File(filename);
			Scanner scan=new Scanner(file);
			String text;
			while(scan.hasNextLine())
			{
				text=scan.nextLine();
				String[] item=text.split(",");
				
					MenuItem mi=new MenuItem(item[0],item[1],item[2],item[3],Double.parseDouble(item[4]));
					list.add(mi);
									
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
			/*try 
			{
				
			} catch (IOException e) 
			{
				System.err.println("Error in closing BufferedReader");
			}*/
		}
		
		return list;
	}
}