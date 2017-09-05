package StudentsDB.CommandHandlers;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import StudentsDB.Entities.Student;
import StudentsDB.Repository.StudentRepository;

public class StudentCommandHandler extends EntityCommandHandler<Student> {
	StudentRepository repo;

	public StudentCommandHandler(StudentRepository repo, Scanner scanner) {
		super(scanner);
		this.repo = repo;
		initActions();
	}

	private void initActions() {
		addAction("add", this::addAction);
		addAction("update", this::updateAction);
		addAction("delete", this::deleteAction);
		addAction("get", this::getAction);
	}
	
	@Override
	protected void helpAction()
	{
		String help = "student add\r\n" + 
		"student update -i id\r\n" + 
		"student delete -i id\r\n" + 
		"course get\r\n" + 
		"student get -i id\r\n" + 
		"student get -f facultyId\r\n" + 
		"student get -c courseid";
		System.out.println("Available actions: ");
		System.out.println(help);
	}

	private void addAction() throws SQLException {
		Student student = scanEntity(new Student());
		repo.Add(student);
	}

	private void updateAction() throws SQLException {
		int id = getIdArgument().get();
		Student student = repo.GetByID(id);
		student = scanEntity(student);
		repo.Update(student);
	}

	private void deleteAction() throws SQLException {
		int id = getIdArgument().get();
		repo.Delete(id);
	}

	private void getAction() throws SQLException {
		Optional<Integer> id = getIdArgument();
		Optional<Integer> facultyId = getIntArgument("f");
		Optional<Integer> courseId = getIntArgument("c");

		List<Student> students;

		if (id.isPresent()) {
			students = Arrays.asList(repo.GetByID(id.get()));
		} else if (facultyId.isPresent()) {
			students = repo.GetByFaculty(facultyId.get());
		} else if (courseId.isPresent()) {
			students = repo.GetByCourse(courseId.get());
		} else {
			students = repo.GetAll();
		}

		Print(students);
	}

	@Override
	protected Student scanEntity(Student template) {
		template.firstName = promptString("firstName", template.firstName);
		template.lastName = promptString("lastName", template.lastName);
		template.facultyId = promptInt("facultyId", template.facultyId);
		return template;
	}
}
