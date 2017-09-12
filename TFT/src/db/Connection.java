package db;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
	private static final String DRIVER_CLASS = "org.postgresql.Driver";
	private static final String HOST = System.getenv("DB_HOST");
	private static final int PORT = Integer.parseInt(System.getenv("DB_PORT"));
	private static final String USER = System.getenv("DB_USER");
	private static final String PASSWORD = System.getenv("DB_PASSWORD");
	private static final String DB_NAME = System.getenv("DB_NAME");
	private static final String DB_URL = 
			String.format("jdbc:postgresql://%s:%d/%s", HOST, PORT, DB_NAME);
	
	public static final EntityManagerFactory factory = buildFactory();
	
	private static EntityManagerFactory buildFactory() {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.driver", DRIVER_CLASS);
		properties.put("javax.persistence.jdbc.url", DB_URL);
		properties.put("javax.persistence.jdbc.user", USER);
		properties.put("javax.persistence.jdbc.password", PASSWORD);
		return Persistence.createEntityManagerFactory("Notes", properties);
	}
}