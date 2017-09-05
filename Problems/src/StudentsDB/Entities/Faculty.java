package StudentsDB.Entities;

import java.util.List;

public class Faculty {
	public int id;
	public String name;
	
	@Override
	public String toString()
	{
		return String.format("id: %d, name: %s", id, name);
	}
}
