package StudentsDB.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import StudentsDB.StudentsDatabase;
import StudentsDB.Entities.Student;

public class StudentRepository extends RepositoryBase<Student>{	
	private static final String INSERT_QUERY 
		= "INSERT INTO students(first_name, last_name, faculty_id) VALUES('%s', '%s', %d)";
	private static final String UPDATE_QUERY
		= "UPDATE students SET first_name = '%s', last_name = '%s', faculty_id = %d WHERE id = %d";
	private static final String GET_BY_FACULTY_QUERY
		= "SELECT * FROM students WHERE faculty_id = %d";
	private static final String GET_BY_COURSE_QUERY
		= "SELECT s.* FROM students s "
				+ "INNER JOIN student_courses sc on sc.student_id = s.id "
				+ "INNER JOIN courses c on c.id = sc.course_id "
				+ "WHERE c.id = %d ";
	private static final String DELETE_COURSE_RELATION_QUERY
		= "DELETE FROM student_courses sc WHERE sc.student_id = %d";
	
	public StudentRepository(StudentsDatabase db)
	{
		super(db, "students");
	}
	
	@Override
	public void Add(Student student) throws SQLException
	{
		String query = makeInsertQuery(student.firstName, student.lastName, student.facultyId);
		ExecNonQuery(query);
	}
	
	@Override
	public void Update(Student student) throws SQLException
	{
		String query = makeUpdateQuery(student.id, student.firstName, student.lastName, student.facultyId);
		ExecNonQuery(query);
	}
	
	public List<Student> GetByCourse(int courseId) throws SQLException
	{
		String query = makeGetByCourseQuery(courseId);
		ResultSet result = ExecQuery(query);
		return mapResult(result);
	}
	
	public List<Student> GetByFaculty(int facultyId) throws SQLException
	{
		String query = makeGetByFacultyQuery(facultyId);
		ResultSet result =ExecQuery(query);
		return mapResult(result);
	}
	
	private String makeGetByFacultyQuery(int facultyId) {
		return String.format(GET_BY_FACULTY_QUERY, facultyId);
	}

	private String makeGetByCourseQuery(int courseId)
	{
		return String.format(GET_BY_COURSE_QUERY, courseId);
	}
	
	private String makeInsertQuery(String firstName, String lastName, int facultyId)
	{
		return String.format(INSERT_QUERY, firstName, lastName, facultyId);
	}
	
	private String makeUpdateQuery(int id, String firstName, String lastName, int facultyId)
	{
		return String.format(UPDATE_QUERY, firstName, lastName, facultyId, id);
	}
	
	@Override
	protected String makeDeleteQuery(int id)
	{
		String deleteRelationQuery = String.format(DELETE_COURSE_RELATION_QUERY, id);
		return String.format("%s;\n%s;", deleteRelationQuery, super.makeDeleteQuery(id));
	}
	
	@Override
	protected Student createFromResult(ResultSet result) throws SQLException {
		Student student = new Student();
		student.firstName = result.getString("first_name");
		student.lastName = result.getString("last_name");
		student.facultyId = result.getInt("faculty_id");
		student.id = result.getInt("id");
		return student;
	}
}
