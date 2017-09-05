import java.util.*;
import java.sql.*;

public class DbTask1
{
	public static void main(String[] args) throws SQLException
	{
		String url = "jdbc:postgresql://localhost/gosho";
		Properties props = new Properties();
		props.setProperty("user", "postgres");
		props.setProperty("password", "postgres");
		Connection conn = DriverManager.getConnection(url, props);
		PreparedStatement prepareStatement = null;
		
		try
		{
			ResultSet executeQuery  = conn.prepareStatement("SELECT * FROM goshos").executeQuery();
			while(executeQuery.next())
			{
				System.out.println(executeQuery.getInt(1) + " | " + executeQuery.getString(2));
			}
		}
		finally
		{
			prepareStatement.close();
			conn.close();
		}
	}
}
