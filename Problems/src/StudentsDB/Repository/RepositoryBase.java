package StudentsDB.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import StudentsDB.StudentsDatabase;
import StudentsDB.Entities.Course;
import StudentsDB.Entities.Student;

public abstract class RepositoryBase<T> {
	
	private static final String DELETE_QUERY = "DELETE FROM %s where id = %d";
	private static final String GET_ID_QUERY = "SELECT * FROM %s WHERE id = %d";
	private static final String GET_QUERY = "SELECT * FROM %s";
	
	private StudentsDatabase db;
	private String tableName;
	
	protected RepositoryBase(StudentsDatabase db, String tableName)
	{
		this.tableName = tableName;
		this.db = db;
	}
	
	protected ResultSet ExecQuery(String query) throws SQLException
	{
		return db.ExecQuery(query);
	}
	
	protected Boolean ExecNonQuery(String query) throws SQLException
	{
		return db.ExecNonQuery(query);
	}
	
	public abstract void Add(T entity) throws SQLException;
	
	public abstract void Update(T entity) throws SQLException;
	
	public void Delete(int id) throws SQLException
	{
		String query = makeDeleteQuery(id);
		ExecNonQuery(query);
	}
	
	public T GetByID(int id) throws SQLException
	{
		String query = makeGetByIDQuery(id);
		ResultSet result = ExecQuery(query);
		return mapResult(result).get(0);
	}
	
	public List<T> GetAll() throws SQLException
	{
		String query = makeGetQuery();
		ResultSet result = ExecQuery(query);
		return mapResult(result);
	}
	
	protected List<T> mapResult(ResultSet result) throws SQLException
	{
		List<T> resultObjects = new ArrayList<T>();
		while(result.next())
		{
			T course = createFromResult(result);
			resultObjects.add(course);
		}
		return resultObjects;
	}
	protected abstract T createFromResult(ResultSet result) throws SQLException;
	
	
	protected String makeDeleteQuery(int id)
	{
		return String.format(DELETE_QUERY, tableName, id);
	}
	
	private String makeGetByIDQuery(int id)
	{
		return String.format(GET_ID_QUERY, tableName, id);
	}
	
	private String makeGetQuery()
	{
		return String.format(GET_QUERY, tableName);
	}
}
