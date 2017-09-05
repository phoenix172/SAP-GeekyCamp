package StudentsDB;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
	public static void main(String[] arguments) throws SQLException, IOException
	{
		try
		{
			DBSettings dbSettings = Configuration.LoadDBSettings("dbSettings.json");
				
			StudentsCmd studentsCmd = new StudentsCmd(dbSettings);
			studentsCmd.Run();
		}
		catch(SQLException ex)
		{
			System.out.println("Error connecting to database. Please check configuration.");
		}
		catch(IOException ex)
		{
			System.out.println("Error reading settings.");
		}
	}
}
