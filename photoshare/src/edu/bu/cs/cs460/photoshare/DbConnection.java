package edu.bu.cs.cs460.photoshare;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A class to manage the db connection
 *
 * @author G. Zervas <cs460tf@bu.edu>
 */
public class DbConnection {
	//private Connection connection;
	
	public static Connection getConnection() throws RuntimeException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			if (ctx == null) {
				throw new RuntimeException("No Context");
			}
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/postgres");
			if (ds == null) {
				throw new RuntimeException("Datasource not found");
			}
			conn = ds.getConnection();
			if (conn == null) {
				throw new RuntimeException("Could not get connection");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (NamingException e) {
			throw new RuntimeException(e.getMessage());
		}
		return conn;
	}
	/*
		 public DbConnection() {
		 try {
		 Context context = new InitialContext();
		 DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/postgres");
		 if (ds == null) {
		 throw new RuntimeException("Data source not found!");
		 }
		 connection = ds.getConnection();
		 } catch (NamingException e) {
		 e.printStackTrace();
		 throw new RuntimeException(e);
		 } catch (SQLException e) {
		 e.printStackTrace();
		 throw new RuntimeException(e);
		 }
		 }
	public Connection getConnection() {
		return connection;
	}
		 */
}
