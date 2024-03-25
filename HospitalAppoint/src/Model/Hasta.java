package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Hasta extends User {

	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Hasta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		// TODO Auto-generated constructor stub
	}

	public boolean register(String tcno, String password, String name) {

		int key = 0;
		boolean duplicate = false;

		String query = "INSERT INTO user " + "(tcno,password,name,type) VALUES " + "(?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "'");

			while (rs.next()) {
				duplicate = true;

				break;
			}
			if (!duplicate) {

				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");

				preparedStatement.executeUpdate();
				key = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}
	
	public boolean deleteAppointment(int doctor_id) {

		String query = "DELETE FROM appointment WHERE doctor_id = ?";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
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

	public boolean addAppointment(int doctor_id, int hasta_id, String doctor_name, String hasta_name, String appDate) {

		int key = 0;

		String query = "INSERT INTO appointment " + "(doctor_id,doctor_name,hasta_id,hasta_name,app_date) VALUES "
				+ "(?,?,?,?,?)";

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setInt(3, hasta_id);
			preparedStatement.setString(4, hasta_name);
			preparedStatement.setString(5, appDate);
			preparedStatement.executeUpdate();
			key = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}
	
	public boolean updateWhourStatusA(int doctor_id, String wdate) {

		int key = 0;

		String query = "UPDATE whour SET status = ? WHERE doctor_id = ? AND wdate = ?";

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
			key = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}
	public boolean updateWhourStatusP(int doctor_id, String wdate) {

		int key = 0;

		String query = "UPDATE whour SET status = ? WHERE doctor_id = ? AND wdate = ?";

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "a");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
			key = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}
	
	public boolean updateWhourStatusPas(String doctor_name, String wdate) {

		int key = 0;

		String query = "UPDATE whour SET status = ? WHERE doctor_name = ? AND wdate = ?";

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "a");
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
			key = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}
	public boolean updateWhourStatusPasNoName(String wdate) {

		int key = 0;

		String query = "UPDATE whour SET status = ? WHERE wdate = ?";

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "a");
			preparedStatement.setString(2, wdate);
			preparedStatement.executeUpdate();
			key = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}

}
