package repository;

import totalcross.sql.Connection;
import totalcross.sql.DriverManager;
import totalcross.sys.Convert;
import totalcross.sys.Settings;

public class ConnectionFactory {

	public ConnectionFactory() {
	}

	public Connection getConnection() throws java.sql.SQLException {
		return DriverManager.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, "projeto.db"));
	}
}