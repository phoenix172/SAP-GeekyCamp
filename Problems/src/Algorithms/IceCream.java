package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class IceCream {
	private static int n = 0;
	private static Integer[] lock = new Integer[3];
	private static List<Integer> combo1, combo2;
	private static Set<List<Integer>> validCombos = new HashSet<List<Integer>>();

	public static void countCombos(int index) {
		if (index == 3) {
			if(validCombos.add(Arrays.asList(lock)))
				printLock();
			return;
		}

		for (int i = -2; i <= 2; i++) {
			setRotateAndCount(combo1, index, i);
			if (combo1.get(index) != combo2.get(index)) {
				setRotateAndCount(combo2, index, i);
			}
		}
	}

	private static void printLock() {
		Arrays.stream(lock).forEach(x -> System.out.printf("%d ", x));
		System.out.println();
	}

	private static void setRotateAndCount(List<Integer> combo, int index, int rotateCount) {
		lock[index] = combo.get(index);
		rotateLock(index, rotateCount);
		countCombos(index + 1);
	}

	private static void rotateLock(int index, int count) {
		lock[index] += count;
		if (lock[index] < 1)
			lock[index] = n + lock[index];
		else if (lock[index] > n)
			lock[index] = lock[index] - n;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = Integer.parseInt(scanner.nextLine());
		validCombos.add(combo1 = readCombo(scanner));
		validCombos.add(combo2 = readCombo(scanner));
		scanner.close();
		countCombos(0);
		System.out.println(validCombos.size());
	}

	private static List<Integer> readCombo(Scanner scanner) {
		return Arrays.asList(scanner.nextLine().split(" ")).stream().map(Integer::parseInt)
				.collect(Collectors.toList());
	}
}
