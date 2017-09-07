package memoryFS;

import java.io.IOException;

public abstract class FileSystemObject {
	private String name;
	private Directory parentDirectory;
	
	protected FileSystemObject(Directory parentDirectory, String name) throws IOException
	{
		this.parentDirectory = parentDirectory;
		throwIfExists(name);
		this.name = name;
	}

	private void throwIfExists(String newName) throws IOException {
		if(parentDirectory == null)return;
		if(parentDirectory.containsChild(newName)) 
			throw new IOException("A file with the same name already exists");
	}
	

	public Directory parent() {
		return parentDirectory;
	}

	public String fullPath() {
		return String.format("%s\\%s", parentDirectory.fullPath(), name);
	}

	public void rename(String newName) throws IOException {
		throwIfExists(newName);
		name = newName;
	}

	public void delete() throws IOException {
		parentDirectory.deleteChild(this);
	}

	public String name() {
		return name;
	}

	@Override 
	public String toString()
	{
		return name;
	}
}
