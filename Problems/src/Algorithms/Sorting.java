package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Sorting {
	public static List<Integer> selectionSort(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			int minIndex = i;
			for (int j = i + 1; i < list.size(); i++) {
				if (list.get(j) < list.get(minIndex))
					minIndex = j;
			}

			Collections.swap(list, minIndex, i);
		}
		return list;
	}

	public static void mergeSort(List<Integer> list, int from, int to) { // [from, to)
		if (to - from < 2)
			return;
		//System.out.printf("from: %d, from+1: %d \n", from, from+1);
		if (to - from == 2 && list.get(from) > list.get(from+1)) {
			Collections.swap(list, from, from + 1);
			return;
		}
		int mid = (from + to) / 2;
		mergeSort(list, from, mid);
		mergeSort(list, mid, to);

		merge(list, from, mid, to);
	}

	private static void merge(List<Integer> list, int from, int mid, int to) {
		List<Integer> result = new ArrayList<Integer>();
		int pin1 = from;
		int pin2 = mid;

		while (pin1 < mid && pin2 < to) {
			if (list.get(pin1) < list.get(pin2)) {
				result.add(list.get(pin1++));
			}
			else {
				result.add(list.get(pin2++));
			}
		}
		
		while(pin1<mid)result.add(list.get(pin1++));
		while(pin2<to)result.add(list.get(pin2++));
		
		for(int i = from; i < to;i++) {
			list.set(i, result.get(i-from));
		}
	}

	public static void main(String[] args) {
		List<Integer> numbers = readNumbers();
		mergeSort(numbers, 0, numbers.size());
		print(numbers);
	}

	private static List<Integer> readNumbers() {
		Scanner scanner = new Scanner(System.in);
		List<Integer> numbers = Arrays.asList(scanner.nextLine().split(" ")).stream().map(Integer::parseInt)
				.collect(Collectors.toList());
		scanner.close();
		return numbers;
	}

	private static void print(List<Integer> numbers) {
		numbers.forEach(x -> System.out.print(x + " "));
		System.out.println();
	}
}
