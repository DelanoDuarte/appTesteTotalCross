package repository;

import totalcross.sql.Connection;
import totalcross.sql.DriverManager;
import totalcross.sys.Convert;
import totalcross.sys.Settings;

/**
 * @author delan Classe com o objetivo de Criar apenas uma Conexão com o banco
 *         de dados
 */

public class ConnectionFactory {

	public ConnectionFactory() {
	}

	public Connection getConnection() throws java.sql.SQLException {
		return DriverManager.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, "projeto.db"));
	}
}