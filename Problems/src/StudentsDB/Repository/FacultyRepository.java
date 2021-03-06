package StudentsDB.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import StudentsDB.StudentsDatabase;
import StudentsDB.Entities.Faculty;

public class FacultyRepository extends RepositoryBase<Faculty> {

	private static final String INSERT_QUERY 
		= "INSERT INTO faculties(name) VALUES('%s')";
	private static final String UPDATE_QUERY
		="UPDATE faculties SET name = '%s' WHERE id = %d";
	private static final String DELETE_STUDENT_COURSE_RELATION_QUERY
		="DELETE FROM student_courses where student_id in"
				+ "(SELECT id FROM students where faculty_id = %d)";
	
	public FacultyRepository(StudentsDatabase db) {
		super(db, "faculties");
	}

	@Override
	public void Add(Faculty entity) throws SQLException {
		String query = makeInsertQuery(entity.name);
		ExecNonQuery(query);
	}

	@Override
	public void Update(Faculty entity) throws SQLException {
		String query = makeUpdateQuery(entity.id, entity.name);
		ExecNonQuery(query);
	}
	
	private String makeInsertQuery(String name)
	{
		return String.format(INSERT_QUERY, name);
	}
	
	private String makeUpdateQuery(int id, String name)
	{
		return String.format(UPDATE_QUERY, name, id);
	}

	@Override
	protected String makeDeleteQuery(int id)
	{
		String deleteRelationQuery = String.format(DELETE_STUDENT_COURSE_RELATION_QUERY, id);
		return String.format("%s;\n%s;", deleteRelationQuery, super.makeDeleteQuery(id));
	}
	
	@Override
	protected Faculty createFromResult(ResultSet result) throws SQLException {
		Faculty faculty = new Faculty();
		faculty.name = result.getString("name");
		faculty.id = result.getInt("id");
		return faculty;
	}
}
