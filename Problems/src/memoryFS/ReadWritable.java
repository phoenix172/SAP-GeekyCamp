package memoryFS;

import java.io.IOException;

public interface ReadWritable extends Readable{
	void write(String newContent) throws IOException;
}
