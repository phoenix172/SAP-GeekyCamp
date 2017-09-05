package StudentsDB.CommandHandlers;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import StudentsDB.Entities.Faculty;
import StudentsDB.Repository.FacultyRepository;

public class FacultyCommandHandler extends EntityCommandHandler<Faculty> {

	FacultyRepository repo;

	public FacultyCommandHandler(FacultyRepository repo, Scanner scanner) {
		super(scanner);
		this.repo = repo;
		initActions();
	}

	private void initActions() {
		addAction("add", this::addAction);
		addAction("delete", this::deleteAction);
		addAction("get", this::getAction);
		addAction("update", this::updateAction);
	}

	@Override
	protected void helpAction()
	{
		String help = 
				"faculty add\r\n" + 
				"faculty update -i id\r\n" + 
				"student delete -i id\r\n" + 
				"course get\r\n" + 
				"faculty get -i id";
		System.out.println("Available actions: ");
		System.out.println(help);
	}
	
	private void addAction() throws SQLException {
		Faculty faculty = scanEntity(new Faculty());
		repo.Add(faculty);
	}

	private void updateAction() throws SQLException {
		int id = getIdArgument().get();
		Faculty faculty;
		faculty = repo.GetByID(id);
		faculty = scanEntity(faculty);
		repo.Update(faculty);
	}

	private void deleteAction() throws SQLException {
		int id = getIdArgument().get();
		repo.Delete(id);
	}

	private void getAction() throws SQLException {
		Optional<Integer> id = getIdArgument();
		List<Faculty> faculties;
		if (id.isPresent()) {
			faculties = Arrays.asList(repo.GetByID(id.get()));
		} else {
			faculties = repo.GetAll();
		}
		Print(faculties);
	}

	@Override
	protected Faculty scanEntity(Faculty template) {
		template.name = promptString("name", template.name);
		return template;
	}
}
