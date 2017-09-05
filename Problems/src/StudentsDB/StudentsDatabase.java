package StudentsDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class StudentsDatabase {
	Connection connection;
	
	private final DBSettings dbSettings;
	
	private final String DB_NAME = "students";
	private final String DEFAULT_DB_NAME = "postgres";

	public StudentsDatabase(DBSettings dbSettings) throws SQLException {
		this.dbSettings = dbSettings;
		connect(this.dbSettings.defaultDatabaseName);
		createDatabase();
		connect(this.dbSettings.studentsDatabaseName);
		CreateSchema();
	}
	
	private void connect(String databaseName) throws SQLException
	{
		String url = "jdbc:postgresql://localhost/"+databaseName;
		connection = DriverManager.getConnection(url, this.dbSettings.userName, this.dbSettings.password);
	}
	
	private void createDatabase() throws SQLException {
		ResultSet result = ExecQuery("SELECT 1 FROM pg_database WHERE datname = '" + DB_NAME + "'");
		if (!result.next()) {
			ExecNonQuery("CREATE DATABASE " + DB_NAME + ";");
		}
	}

	private void CreateSchema() throws SQLException {
		ExecNonQuery("CREATE TABLE IF NOT EXISTS FACULTIES "
				+ "(ID SERIAL PRIMARY KEY,"
				+ " NAME varchar(255) NOT NULL);");
		ExecNonQuery("CREATE TABLE IF NOT EXISTS STUDENTS "
				+ "(ID SERIAL PRIMARY KEY,"
				+ "FIRST_NAME varchar(255) NOT NULL,"
				+ "LAST_NAME varchar(255) NOT NULL,"
				+ "FACULTY_ID int REFERENCES FACULTIES (ID) ON DELETE CASCADE);");
		ExecNonQuery("CREATE TABLE IF NOT EXISTS COURSES "
				+ "(ID SERIAL PRIMARY KEY,"
				+ " NAME varchar(255) NOT NULL,"
				+ " DESCRIPTION varchar(255) NOT NULL,"
				+ " CREDITS int NOT NULL);");
		ExecNonQuery(
				"CREATE TABLE IF NOT EXISTS STUDENT_COURSES "
						+ "(STUDENT_ID int NOT NULL REFERENCES STUDENTS (ID) ON DELETE CASCADE, "
						+ "COURSE_ID int NOT NULL REFERENCES COURSES (ID) ON DELETE CASCADE);");
	}
	
	public ResultSet ExecQuery(String query) throws SQLException {
		return connection.prepareStatement(query).executeQuery();
	}

	public Boolean ExecNonQuery(String query) throws SQLException {
		return connection.prepareStatement(query).execute();
	}
}