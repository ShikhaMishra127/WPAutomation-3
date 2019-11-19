package utilities.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleQuery {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	private ResourceLoader environment = new ResourceLoader("qa");
	private String connection;
	private String username;
	private String password;

	public void Connect() {

		try {
			connection = environment.getValue("oracleconnection");
			username = environment.getValue("oracleusername");
			password = environment.getValue("oraclepassword");
		} catch (Exception e) {
			throw new RuntimeException("Error loading connection info", e);
		}

		try {
			con = DriverManager.getConnection(connection, username, password);

	    } catch (SQLException e) {
	        throw new RuntimeException("Error connecting to the database", e);
	    }
	}
	
	public void Close() {

			try {
				if (rs != null)  { rs.close();  }
				if (st != null)  { st.close();  }
				if (con != null) { con.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	
	public ResultSet executeQuery(String queryString) {
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(queryString);
		} catch (SQLException e) {
			throw new RuntimeException("Error executing SELECT query: "+queryString.toUpperCase(), e);
		}
		
		return rs;
	}
	
	public int executeUpdate(String updateString) {
		
		try {
			st = con.createStatement();
			int result = st.executeUpdate(updateString);
			return result;	
		} catch (SQLException e) {
			throw new RuntimeException("Error executing UPDATE query: "+updateString.toUpperCase(), e);
		}
	}
	
	public String getFirst(String key) {
		
		String result = "";
		
		try {
			if (rs.next()) {
				result = rs.getString(key);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error executing query", e);
		}
		
		return result;		
	}
}
