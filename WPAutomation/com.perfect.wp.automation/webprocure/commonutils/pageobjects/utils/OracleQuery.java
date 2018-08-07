package commonutils.pageobjects.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleQuery {

	private Connection con;
	private Statement st;
	private ResultSet rs;
		
	public void Connect() {
		
		String connection;
		String username;
		String password;
		
		try {
			connection = ReadConfig.getInstance().getOracleConnection();
			username = ReadConfig.getInstance().getOracleUsername();
			password = ReadConfig.getInstance().getOraclePassword();
		} catch (Exception e) {
			throw new RuntimeException("Error loading connection info", e);
		}

		try {
			con = DriverManager.getConnection(connection, username, password);
	
	    } catch (SQLException e) {
	        throw new RuntimeException("Error connecting to the database", e);
	    }
	}
	
	public void Close() throws SQLException {
		if (rs != null ) { rs.close(); }
		if (st != null ) { st.close(); }
		if (con != null ) { con.close(); }
	}
	
	public ResultSet executeQuery(String queryString) throws SQLException {
		
		st = con.createStatement();
		rs = st.executeQuery(queryString);
		
		return rs;
	}
	
	public int executeUpdate(String updateString) throws SQLException {
		
		st = con.createStatement();
		int result = st.executeUpdate(updateString);
		
		return result;
	}
	
	public String getFirst(String key) throws SQLException {
		
		String result = "";
		
		if (rs.next()) {
			result = rs.getString(key);
		}
		return result;
	}
	
}

	
		



