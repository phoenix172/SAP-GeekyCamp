import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Combos {
	public static void main(String[] arguments) {
		Scanner scanner = new Scanner(System.in);
		int[] set = Arrays.asList(scanner.nextLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();

		int k = scanner.nextInt();
		int[] vector = new int[k];

		Combos(set, vector, 0, 0);
	}

	private static void Combos(int[] set, int[] vector, int vectorIndex, int setIndex) {
		if (vectorIndex >= vector.length) {
			Arrays.stream(vector)
			.forEach(System.out::print);
			System.out.println();
		} else {
			for (int i = 0; i < set.length; i++) {
				vector[vectorIndex] = set[i];
				Combos(set, vector, vectorIndex + 1, i + 1);
			}
		}
	}
}
