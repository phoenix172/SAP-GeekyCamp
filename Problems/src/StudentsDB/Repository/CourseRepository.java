package StudentsDB.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import StudentsDB.StudentsDatabase;
import StudentsDB.Entities.Course;

public class CourseRepository extends RepositoryBase<Course>{

	private static final String INSERT_QUERY 
		= "INSERT INTO courses(name, description, credits) VALUES('%s', '%s', %d)";
	private static final String UPDATE_QUERY
		= "UPDATE courses SET name = '%s', description = '%s', credits = %d WHERE id = %d";
	private static final String ADD_STUDENT_QUERY 
		="INSERT INTO student_courses(student_id, course_id) VALUES(%d, %d)";
	private static final String REMOVE_STUDENT_QUERY 
		="DELETE FROM student_courses WHERE student_id = %d AND course_id = %d";
	private static final String DELETE_STUDENT_RELATION_QUERY
		="DELETE FROM student_courses WHERE course_id = %d";
	
	public CourseRepository(StudentsDatabase db) {
		super(db, "courses");
	}
	
	@Override
	public void Add(Course course) throws SQLException
	{
		String query = makeInsertQuery(course.name, course.description, course.credits);
		ExecNonQuery(query);
	}
	
	@Override
	public void Update(Course course) throws SQLException
	{
		String query = makeUpdateQuery(course.id, course.name, course.description, course.credits);
		ExecNonQuery(query);
	}
	
	public void AddStudentToCourse(int studentId, int courseId) throws SQLException
	{
		String query = makeAddStudentQuery(studentId, courseId);
		ExecNonQuery(query);
	}
	
	public void RemoveStudentFromCourse(int studentId, int courseId) throws SQLException
	{
		String query = makeRemoveStudentQuery(studentId, courseId);
		ExecNonQuery(query);
	}

	@Override
	protected Course createFromResult(ResultSet result) throws SQLException {
		Course course = new Course();
		course.id = result.getInt("id");
		course.name = result.getString("name");
		course.description = result.getString("description");
		course.credits = result.getInt("credits");
		return course;
	}
	
	private String makeInsertQuery(String name, String description, int credits)
	{
		return String.format(INSERT_QUERY, name, description, credits);
	}
	
	
	private String makeUpdateQuery(int id, String name, String description, int credits)
	{
		return String.format(UPDATE_QUERY, name, description, credits, id);
	}
	
	private String makeAddStudentQuery(int studentId, int courseId)
	{
		return String.format(ADD_STUDENT_QUERY, studentId, courseId);
	}
	
	private String makeRemoveStudentQuery(int studentId, int courseId)
	{
		return String.format(REMOVE_STUDENT_QUERY, studentId, courseId);
	}
	
	@Override
	protected String makeDeleteQuery(int id)
	{
		String deleteRelationQuery = String.format(DELETE_STUDENT_RELATION_QUERY, id);
		return String.format("%s;\n%s;", deleteRelationQuery, super.makeDeleteQuery(id));
	}
}
