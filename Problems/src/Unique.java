

import java.util.*;

public class Unique {
	public static void main(String[] arguments)
	{
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();
		
		System.out.println(isUnique(input));
	}
	
	private static Boolean isUnique(String input)
	{
		for(int i =0;i<input.length();i++)
		{
			for(int j = i+1;j<input.length();j++)
			{
				if(input.charAt(i) == input.charAt(j))
					return false;
			}
		}
		return true;
	}
}
