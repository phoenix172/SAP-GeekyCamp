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
	private static Set<List<Integer>> validCombos = new HashSet<List<Integer>>();

	public static void countCombos(List<Integer> combo, int index) {
		if (index == 3) {
			validCombos.add(Arrays.asList(lock));
			return;
		}

		for (int i = -2; i <= 2; i++) {
			lock[index] = rotateLock(combo.get(index), i);
			countCombos(combo, index + 1);
		}
	}
	
	private static int rotateLock(int initial, int count) {
		if(count == 0)return initial;
		int absCount = Math.abs(count);
		int add = absCount / count;
		for(int i = 0;i < absCount; i++) {
			initial+=add;
			if(initial==0)initial=n;
			if(initial==n+1)initial=1;
		}
		return initial;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = Integer.parseInt(scanner.nextLine());
		List<Integer> combo1, combo2;
		validCombos.add(combo1 = readCombo(scanner));
		validCombos.add(combo2 = readCombo(scanner));
		scanner.close();
		countCombos(combo1, 0);
		if(combo1!=combo2)
			countCombos(combo2, 0);
		System.out.println(validCombos.size());
	}

	private static List<Integer> readCombo(Scanner scanner) {
		return Arrays.asList(scanner.nextLine().split(" ")).stream().map(Integer::parseInt)
				.collect(Collectors.toList());
	}
}
