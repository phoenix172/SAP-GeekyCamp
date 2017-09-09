package DataStructures.crcHashTable;

public class HashTableTest {
	public static void main(String[] arguments) {
		CCRHashTable<Integer, String> table = new CCRHashTable<Integer, String>();
		table.insert(1, "gosho7");
		table.insert(2, "pesho6");
		table.insert(3, "pesho5");
		table.insert(4, "pesho4");
		table.insert(5, "pesho1");
		table.insert(6, "pesho2");
		table.insert(7, "pesho3");

		for(int i =1;i<=7;i++)
			System.out.println(table.get(i));
		
		
		System.out.println(table.get(6));
		table.remove(6);
		System.out.println(table.get(6));
	}
}
