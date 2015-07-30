package gg.web.mcb.EssentialsGreen.API;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLAPI extends JavaAPI {

	public static Connection OpenConection(String host, int port, String user, String password, String database) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("bdbc:mysql://" + host + ":" + port +"/" + database, user, password);
		return con;
	}
	
	public static void queryUpdate(String query, Connection con) throws SQLException{
		PreparedStatement st = null;
		st = con.prepareStatement(query);
		st.executeUpdate();
		closeResources(null, st);
	}
	
	public static void closeResources(ResultSet rs, PreparedStatement st) throws SQLException{
		if(rs != null){
			rs.close();
		}
		if(st != null){
			st.close();
		}
	}
	
	public static Connection closeConnection(Connection con) throws SQLException{
		con.close();
		return con = null;
	}
	
	public boolean hasConnection(Connection con){
		try{
			return con != null | con.isValid(1);
		}catch(SQLException e) {
			return false;
		}
	}
}