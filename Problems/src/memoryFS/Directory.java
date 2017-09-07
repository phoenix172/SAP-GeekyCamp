package memoryFS;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Directory extends FileSystemObject {

	private Map<String, File> files;
	private Map<String, Directory> directories;

	protected Directory(Directory parentDirectory, String name) throws IOException {
		super(parentDirectory, name);
		this.files = new HashMap<String, File>();
		this.directories = new HashMap<String, Directory>();
	}

	public boolean containsChild(String name) {
		return files.containsKey(name) || directories.containsKey(name);
	}

	public File createFile(String name) throws IOException {
		File f = new File(this, name);
		files.put(name, f);
		return f;
	}

	public Directory createDirectory(String name) throws IOException {
		Directory d = new Directory(this, name);
		directories.put(name, d);
		return d;
	}

	public Collection<Directory> directories() {
		return this.<Directory>sortObjects(directories.values());
	}

	public Collection<File> files() {
		return this.<File>sortObjects(files.values());
	}

	private <T extends FileSystemObject> Collection<T> sortObjects(Collection<T> objects) {
		return objects.stream().sorted((a, b) -> a.toString().compareTo(b.toString())).collect(Collectors.toList());
	}

	public Directory getDirectory(String name) {
		if (!directories.containsKey(name))
			return null;
		return directories.get(name);
	}

	public File getFile(String name) {
		if (!files.containsKey(name))
			return null;
		return files.get(name);
	}

	@Override
	public void delete() throws IOException {
		if (!directories.isEmpty() || !files.isEmpty())
			throw new IOException("Unable to delete a directory that has children. Use deleteRecursive instead.");
		super.delete();
	}

	public void deleteRecursive() throws IOException {
		for (Directory directory : directories.values()) {
			directory.deleteRecursive();
		}
		for (File file : files.values()) {
			file.forceDelete();
		}
		delete();
	}

	public void deleteChild(FileSystemObject object) {
		files.remove(object.name());
		directories.remove(object.name());
	}

	@Override
	public String toString() {
		return String.format("dir: %s", super.toString());
	}
}
