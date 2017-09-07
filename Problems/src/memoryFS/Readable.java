package memoryFS;

import java.io.IOException;

public interface Readable {
	String read() throws IOException;
	void close() throws IOException;
}
