package Algorithms;

import java.util.Arrays;
import java.util.List;

public class BinarySearch {
	public static Boolean binaryContainsRecursive(List<Integer> list, Integer number, int from, int to) //[from, to)
	{
		if(from == to-1)
			return list.get(from) == number;
		
		int mid = (from+to)/2;
		int midElement = list.get(mid);
		System.out.println(midElement);
		
		if(number<list.get(mid))
			return binaryContainsRecursive(list, number, from, mid); 
		else
			return binaryContainsRecursive(list, number, mid, to);
	}
	
	public static Boolean binaryContainsIterative(List<Integer> list, Integer number)
	{
		int from = 0;
		int to = list.size();
		
		while(to != from)
		{
			int mid = (from + to)/2;
			int midValue = list.get(mid);
			System.out.println(midValue);
			if(midValue == number)
				return true;
			if(number<midValue)
				to = mid;
			else
				from = mid;
		}
		return false;
	}
	
	public static void main(String[] args) {
		List<Integer> input = Arrays.asList(1,4,7,9,15,39,80);
		System.out.println(binaryContainsIterative(input, 7));
	}
}
