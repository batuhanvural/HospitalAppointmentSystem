package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Appointment {
	
	private int id,doctorID,hastaID;
	private String doctorName,hastaName,appDate;
	
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	public Appointment() {
		
	}
	
	public ArrayList<Appointment> getList() {

		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment");
			while (rs.next()) {

				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
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
	
	public Appointment getFetch(int id) {
		
		Connection con = conn.connDb();
		Appointment a = new Appointment();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE id ="+id);
			
			while (rs.next()) {
				
				a.setId(rs.getInt("id"));
				a.setDoctorName("doctor_name");
				break;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
		
	}
	
	
	
	public ArrayList<Appointment> getHastaList(int hasta_id) {

		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE hasta_id = "+hasta_id);
			while (rs.next()) {

				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
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
	
	public boolean updateAppointStatus(String doctor_name, String app_date) {
		
		String query = "UPDATE whour SET status = ?  WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, doctor_name);
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
	
	public boolean deleteAppointment(int id) {

		String query = "DELETE FROM appointment WHERE id = ?";
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
	
	public ArrayList<Appointment> getDoctorList(int doctor_id) {

		ArrayList<Appointment> list = new ArrayList<Appointment>();
		Appointment obj;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE doctor_id = "+doctor_id);
			while (rs.next()) {

				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
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
	
	
	
	
	public Appointment(int id, int doctorID, int hastaID, String doctorName, String hastaName, String appDate) {
		super();
		this.id = id;
		this.doctorID = doctorID;
		this.hastaID = hastaID;
		this.doctorName = doctorName;
		this.hastaName = hastaName;
		this.appDate = appDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	public int getHastaID() {
		return hastaID;
	}
	public void setHastaID(int hastaID) {
		this.hastaID = hastaID;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getHastaName() {
		return hastaName;
	}
	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

}
