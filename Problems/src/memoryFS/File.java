package memoryFS;

import java.io.IOException;

import javax.imageio.IIOException;

public class File extends FileSystemObject{
	private class ReadOnlyFileHandle implements Readable{
		@Override
		public String read() throws IOException {
			return getContent();
		}
		
		@Override
		public void close() throws IOException {
			setClosed();
		}
	}
	
	private class FileHandle extends ReadOnlyFileHandle implements ReadWritable
	{
		@Override
		public void write(String newContent) throws IOException {
			setContent(newContent);			
		}
	}
	
	private String fileContent;

	private AccessMode accessMode = AccessMode.Closed;

	protected File(Directory parentDirectory, String name) throws IOException {
		super(parentDirectory, name);
	}
	
	@Override
	public void rename(String newName) throws IOException
	{
		throwIfOpen();
		super.rename(newName);
	}
	
	@Override
	public void delete() throws IOException
	{
		throwIfOpen();
		super.delete();
	}
	
	public void forceDelete() throws IOException
	{
		setClosed();
		delete();
	}

	public Readable openReadOnly() throws IOException {
		throwIfOpen();
		accessMode = AccessMode.Read;
		return new ReadOnlyFileHandle();
	}

	public ReadWritable open() throws IOException {
		throwIfOpen();
		accessMode = AccessMode.ReadWrite;
		return new FileHandle();
	}
	
	private String getContent() throws IOException
	{
		throwIfNoAccess(AccessMode.Read);
		return fileContent;
	}
	
	private void setContent(String newContent) throws IOException
	{
		throwIfNoAccess(AccessMode.ReadWrite);
		fileContent = newContent;
	}

	private void setClosed() throws IOException
	{
		throwIfNoAccess(AccessMode.Read);
		accessMode = AccessMode.Closed;
	}
	
	private void throwIfNoAccess(AccessMode required) throws IOException
	{
		if(accessMode.level() < required.level())
			 throw new IOException("Access denied");
	}
	
	private void throwIfOpen() throws IOException {
		if (!accessMode.equals(AccessMode.Closed))
			throw new IOException("File is open");
	}
	
	@Override
	public String toString()
	{
		return String.format("file: %s", super.toString());
	}
}
