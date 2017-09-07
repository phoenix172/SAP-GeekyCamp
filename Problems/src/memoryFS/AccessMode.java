package memoryFS;

public enum AccessMode {
	ReadWrite(2),Read(1), Closed(0);

	private int accessLevel;

	AccessMode(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public int level() {
		return accessLevel;
	}
}
