
import java.util.*;

public class OccuranceCount {
	public static void main(String[] arguments)
	{
		Scanner scanner = new Scanner(System.in);
		String[] numbers = scanner
				.nextLine().split(" ");
		scanner.close();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<numbers.length;i++)
		{
			if(numbers[i].equals(""))continue;
			int count = 1;
			for(int j=i+1;j<numbers.length;j++)
			{
				if(numbers[i].equals(numbers[j]))
				{
					count++;
					numbers[j]="";
				}
			}
			sb.append(String.format("%s %d ",numbers[i], count));
		}
		sb.delete(sb.length()-1, sb.length());
		System.out.println(sb.toString());
	}
}
