package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {

	private int id;
	private String name;
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Clinic() {
	}

	public ArrayList<Clinic> getList() {

		ArrayList<Clinic> list = new ArrayList<Clinic>();
		Clinic obj;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic");
			while (rs.next()) {

				obj = new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				st.close();
				rs.close();
				con.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

		}
		return list;
	}
	
	public Clinic getFetch (int id) {
		
		Connection con = conn.connDb();
		Clinic c = new Clinic();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic WHERE id ="+id);
			
			while(rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return c;
		
	}
	
	
	public boolean addClinic(String name) {

		String query = "INSERT INTO clinic" + "(name) VALUES" + "(?)";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key)
			return true;

		else
			return false;

	}
	
	public boolean deleteClinic(int id) {

		String query = "DELETE FROM clinic WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key)
			return true;

		else
			return false;

	}
	
	public boolean updateClinic(int id, String name) {
		String query = "UPDATE clinic SET name = ?  WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key)
			return true;

		else
			return false;

	}
	
	public ArrayList<User> getClinicDoctorList(int clinic_id) {

		ArrayList<User> list = new ArrayList<User>();
		User obj;
		try {
			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT u.id,u.tcno,u.type,u.name,u.password FROM worker w "
					+ "LEFT JOIN user u ON w.user_id = u.id "
					+ "WHERE clinic_id = "+clinic_id);

			while (rs.next()) {

				obj = new User(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.name"), rs.getString("u.password"),
						rs.getString("u.type"));
				list.add(obj);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
