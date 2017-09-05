package StudentsDB;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.activity.InvalidActivityException;

import StudentsDB.CommandHandlers.*;
import StudentsDB.Entities.*;
import StudentsDB.Repository.*;

public class Main {
	public static void main(String[] arguments) throws SQLException
	{
		DBSettings dbSettings = new DBSettings();
		dbSettings.userName="postgres";
		dbSettings.password ="postgres";
		dbSettings.defaultDatabaseName = "postgres";
		
		StudentsCmd studentsCmd = new StudentsCmd(dbSettings);
		studentsCmd.Run();
	}
}
