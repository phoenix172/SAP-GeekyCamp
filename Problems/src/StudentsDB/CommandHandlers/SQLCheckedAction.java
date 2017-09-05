package StudentsDB.CommandHandlers;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLCheckedAction {
	void run() throws SQLException;
}
