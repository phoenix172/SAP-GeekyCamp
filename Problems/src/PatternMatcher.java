import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatternMatcher {
	public static void main(String[] arguments) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();
		String[] values = input.split(" ");
		if (values.length != 2) {
			System.out.println(true);
			return;
		}

		String text = values[0];
		String pattern = values[1];

		System.out.println(isMatch(text, pattern));
	}

	private static void printIndexesOfToken(String text, String token) {
		Integer[] indexes = indexesOfToken(text, token);
		for (Integer index : indexes)
			System.out.println(index);
	}

	private static Boolean isMatch(String text, String pattern) {
		String[] tokens = pattern.split("\\*");
		return isMatch(text, tokens, 0);
	}

	private static Boolean isMatch(String text, String[] tokens, int firstToken) {
		if(firstToken == tokens.length)
		{
			return true;
		}
		
		String currentToken = tokens[firstToken];
//		System.out.println("trying to match " + text + " with {" + arrayToString(tokens, firstToken)
	//			+ "} current token: " + currentToken);
		
		Integer[] indexes = indexesOfToken(text, currentToken);

		for (Integer index : indexes) {
			String substring = text.substring(index + currentToken.length());
			if (isMatch(substring, tokens, firstToken + 1)) {
			//	System.out.println("result true");
				return true;
			}

		}
		//System.out.println("bottom result false");
		return false;
	}

	private static String arrayToString(String[] array, int firstIndex) {
		StringBuilder builder = new StringBuilder();
		for (int i = firstIndex; i < array.length; i++) {
			builder.append(array[i]);
			builder.append(" ");
		}
		return builder.toString();
	}

	private static Integer[] indexesOfToken(String input, String token) {
		List<Integer> indexes = new ArrayList<Integer>();
		int index = 0;
		while (true) {
			index = firstIndexOfToken(input, token, index);
			if (index != -1) {
				indexes.add(index);
				index += token.length();
			} else
				break;
		}
		return indexes.toArray(new Integer[0]);
	}

	private static int firstIndexOfToken(String input, String token, int startIndex) {
		for (int i = startIndex; i < input.length() - token.length() + 1; i++) {
			String substring = input.substring(i, i + token.length());
			if (equalsToken(substring, token))
				return i;
		}
		return -1;
	}

	private static Boolean equalsToken(String text, String token) {
		if (text.length() == token.length()) {
			for (int i = 0; i < text.length(); i++) {
				char currentTokenChar = token.charAt(i);
				if (text.charAt(i) != currentTokenChar && currentTokenChar != '?')
					return false;
			}
			return true;
		}
		return false;
	}
}
