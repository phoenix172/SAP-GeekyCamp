package StudentsDB.Entities;

import java.util.List;

public class Course {
	public String name;
	public String description;
	public int credits;
	public int id;
	
	@Override
	public String toString()
	{
		return String.format("id: %d, name: %s, description: %s, credits: %d",
				id, name, description, credits);
	}
}
