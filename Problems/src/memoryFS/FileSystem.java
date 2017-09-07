package memoryFS;

import java.io.IOException;

public class FileSystem extends Directory {

	public FileSystem() throws IOException {
		super(null, "~");
	}

	@Override
	public void delete() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteRecursive() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void rename(String name) {
		throw new UnsupportedOperationException();
	}
}
