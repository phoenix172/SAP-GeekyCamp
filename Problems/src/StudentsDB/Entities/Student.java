package StudentsDB.Entities;

import java.util.List;

public class Student {
	public int id;
	public String firstName;
	public String lastName;
	public int facultyId;
	
	@Override
	public String toString()
	{
		return String.format("id: %d, firstName: %s, lastName: %s, facultyId: %d",
				id, firstName, lastName, facultyId);
	}
}
