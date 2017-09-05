import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jline.ConsoleReader;

public class CmdTest {
    public static void main(String[] args) throws Exception {
    	
        ConsoleReader reader = new ConsoleReader();
        reader.printString("goooooshooooo");
        reader.printNewline();
        reader.clearScreen();
    }

	private static void clearScreen() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	
	private static void clearScreen2()
	{
		System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}
	
	private static void clearScreen3()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        System.out.print("exep");
	    }

	}
}
