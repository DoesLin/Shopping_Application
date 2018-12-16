package fr.polytech.tours.jdbc.application.controller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Classe permettant la connection a la base de donnes.
 * 
 * @author Moutas Ribeiro et Lin
 *
 */
public class ConnectionDAO {

	static final String JdbcDriver = "com.mysql.cj.jdbc.Driver";
	static final String DbUrl = "jdbc:mysql://127.0.0.1:3306/TpJdbcSujet4";
	static final Properties UserInfo = new Properties();

	private static ConnectionDAO connectionFactory = null;
	/**
	 * Constructeur par default.
	 */
	public ConnectionDAO() {
		try {
			Class.forName(JdbcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// UserInfo.put("user", "does");
		// UserInfo.put("password", "dlv711");
		 UserInfo.put("user", "root");
		 UserInfo.put("password", "");
	}
	/**
	 * Ffonction permettant d'avoir l'attribut connection de type Connection.
	 * 
	 * @return conn
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(DbUrl, UserInfo);
		return conn;
	}
	/**
	 * Accesseur de la connexion specifique a notre base de donnees.
	 * @return connectionFactory
	 */
	public static ConnectionDAO getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionDAO();
		}
		return connectionFactory;
	}

}
