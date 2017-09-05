package StudentsDB.CommandHandlers;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import StudentsDB.Entities.Course;
import StudentsDB.Repository.CourseRepository;

public class CourseCommandHandler extends EntityCommandHandler<Course> {

	CourseRepository repo;

	public CourseCommandHandler(CourseRepository repo, Scanner scanner) {
		super(scanner);
		this.repo = repo;
		initActions();
	}

	private void initActions() {
		addAction("add", this::addAction);
		addAction("update", this::updateAction);
		addAction("get", this::getAction);
		addAction("delete", this::deleteAction);
		addAction("add-student", this::addStudentAction);
		addAction("remove-student", this::removeStudentAction);
	}
	
	@Override
	protected void helpAction()
	{
		String help = 
				"course add\r\n" + 
				"course update -i id\r\n" + 
				"course get\r\n" + 
				"course get -i id\r\n" + 
				"course delete -i id\r\n" +
				"course add-student -i courseid -s studentid\r\n" + 
				"course remove-student -i courseid -s studentid";
		System.out.println("Available actions: ");
		System.out.println(help);
	}
	

	private void addAction() throws SQLException {
		Course course = scanEntity(new Course());
		repo.Add(course);
	}

	private void updateAction() throws SQLException {
		int id = getIdArgument().get();
		Course course = repo.GetByID(id);
		course = scanEntity(course);
		repo.Update(course);
	}

	private void getAction() throws SQLException {
		Optional<Integer> id = getIdArgument();
		List<Course> courses;
		if (id.isPresent()) {
			courses = Arrays.asList(repo.GetByID(id.get()));
		} else {
			courses = repo.GetAll();
		}
		Print(courses);
	}
	
	private void deleteAction() throws SQLException
	{
		int id = getIdArgument().get();
		repo.Delete(id);
	}

	private void addStudentAction() throws SQLException {
		Optional<Integer> courseId = getIdArgument();
		Optional<Integer> studentId = getIntArgument("s");

		repo.AddStudentToCourse(studentId.get(), courseId.get());
	}

	private void removeStudentAction() throws SQLException {
		Optional<Integer> courseId = getIdArgument();
		Optional<Integer> studentId = getIntArgument("s");

		repo.RemoveStudentFromCourse(studentId.get(), courseId.get());
	}

	@Override
	protected Course scanEntity(Course template) {
		template.name = promptString("name", template.name);
		template.description = promptString("description", template.description);
		template.credits = promptInt("credits", template.credits);
		return template;
	}
}
