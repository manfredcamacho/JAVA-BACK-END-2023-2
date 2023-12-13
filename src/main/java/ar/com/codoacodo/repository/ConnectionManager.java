package ar.com.codoacodo.repository;

import ar.com.codoacodo.utils.PropertiesFileReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	public static Connection getConnection( ) {

		try {

			String host = PropertiesFileReader.getValue("db.host");
			String username = PropertiesFileReader.getValue("db.username");
			String password = PropertiesFileReader.getValue("db.password");
			String port = PropertiesFileReader.getValue("db.port");
			String dbName = PropertiesFileReader.getValue("db.name");
			String dbUrl = "jdbc:mysql://"+host+":"+port+"/"+dbName+"?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String driver = "com.mysql.cj.jdbc.Driver";

			Class.forName(driver);

			return DriverManager.getConnection(dbUrl, username, password);

		}catch (IOException e) {
			throw new RuntimeException("Unable to read the configuration file.", e);
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Failed to connect to the database.", e);
		}
		
	}

}

