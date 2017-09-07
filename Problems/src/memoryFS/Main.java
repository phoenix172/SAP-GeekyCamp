package memoryFS;

import java.io.IOException;

public class Main {
	public static void main(String[] arguments) throws IOException {
		FileSystem fileSystem = new FileSystem();
		Directory directory = fileSystem.createDirectory("gosho");
		File pesho = fileSystem.createFile("pesho");
		File neshto = directory.createFile("neshto");
		
		try
		{
			directory.delete();
		}
		catch(IOException ex)
		{
			System.out.println("it blew delete directory with content");
		}
		
		ReadWritable handle = neshto.open();
		handle.write("nqkuf content");
		handle.close();
		
		ReadWritable writable = pesho.open();
		try
		{
			pesho.open();
		}
		catch(IOException ex)
		{
			System.out.println("it blew open when file already open");
		}
		writable.write("gosho e");
		writable.close();
		
		try
		{
			System.out.println(writable.read());
		}
		catch(IOException ex)
		{
			System.out.println("it blew read while file closed");
		}
		
		
		Readable handler = neshto.openReadOnly();
		System.out.println(handler.read());
				
		printChildren(fileSystem);
		
		System.out.println("delete gosho");
		directory.deleteRecursive();
		
		printChildren(directory);
		
		printChildren(fileSystem);
	}
	
	public static void printChildren(Directory dir)
	{
		System.out.println();
		System.out.printf("children of %s\n",dir.name());
		dir.directories().forEach(System.out::println);
		dir.files().forEach(System.out::println);
	}
}
