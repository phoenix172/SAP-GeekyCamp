package StudentsDB;

import java.sql.SQLException;
import java.util.Scanner;

import StudentsDB.CommandHandlers.CommandHandler;
import StudentsDB.CommandHandlers.CourseCommandHandler;
import StudentsDB.CommandHandlers.FacultyCommandHandler;
import StudentsDB.CommandHandlers.RootCommandHandler;
import StudentsDB.CommandHandlers.StudentCommandHandler;
import StudentsDB.Repository.CourseRepository;
import StudentsDB.Repository.FacultyRepository;
import StudentsDB.Repository.StudentRepository;

public class StudentsCmd {
	
	Scanner scanner;
	StudentsDatabase database;
	RootCommandHandler handler;
	
	public StudentsCmd(DBSettings dbSettings) throws SQLException
	{
		scanner = new Scanner(System.in);
		database = new StudentsDatabase(dbSettings);
		handler = createRootCommandHandler();
	}
	
	public void Run()
	{
		System.out.println("StudentCMD 1.0");
		System.out.println("Type help to get started...");
		while(true)
		{
			readCommand();
		}
	}
	
	private void readCommand() {
		String line = scanner.nextLine().trim();
		if(!line.isEmpty())
		{
			this.handler.handleCommand(line);
		}
	}

	private RootCommandHandler createRootCommandHandler() {
		CourseRepository courseRepository = new CourseRepository(database);
		FacultyRepository facultyRepository = new FacultyRepository(database);
		StudentRepository studentRepository = new StudentRepository(database);
		
		RootCommandHandler rootHandler = new RootCommandHandler();
		rootHandler.AddHandler("student", new StudentCommandHandler(studentRepository, scanner));
		rootHandler.AddHandler("faculty", new FacultyCommandHandler(facultyRepository, scanner));
		rootHandler.AddHandler("course", new CourseCommandHandler(courseRepository, scanner));
		return rootHandler;
	}
}
