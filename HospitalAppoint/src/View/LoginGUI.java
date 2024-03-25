package View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_hastaPass;
	private JTextField fld_doktorTc;
	private JPasswordField fld_doktorPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setBounds(100, 100, 500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("/hospital.png")));
		lbl_logo.setBounds(208, 6, 100, 64);
		w_pane.add(lbl_logo);

		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Apple Braille", Font.BOLD, 16));
		lblNewLabel.setBounds(105, 82, 290, 16);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(6, 110, 488, 244);
		w_pane.add(w_tabpane);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		JLabel lblTcNumaranz = new JLabel("T.C. Numaranız :");
		lblTcNumaranz.setFont(new Font("Apple Braille", Font.BOLD, 16));
		lblTcNumaranz.setBounds(23, 47, 129, 16);
		w_hastaLogin.add(lblTcNumaranz);

		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setFont(new Font("Apple Braille", Font.BOLD, 16));
		lblifre.setBounds(110, 95, 42, 16);
		w_hastaLogin.add(lblifre);

		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Apple Braille", Font.BOLD, 15));
		fld_hastaTc.setBounds(164, 41, 205, 26);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);

		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setFont(new Font("Apple Braille", Font.BOLD, 15));
		fld_hastaPass.setColumns(10);
		fld_hastaPass.setBounds(164, 89, 205, 26);
		w_hastaLogin.add(fld_hastaPass);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setBounds(35, 124, 198, 40);
		w_hastaLogin.add(btn_register);

		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (fld_hastaTc.getText().isEmpty() || fld_hastaPass.getText().isEmpty()) {
					Helper.showMsg("fill");
				} else {
					try {
							Connection con = conn.connDb();
							Statement st = con.createStatement();
							ResultSet rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + fld_hastaTc.getText() + "'"); 

						if (rs.next()) {
							if (fld_hastaPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
								}
								} else {
									Helper.showMsg("Kullanıcı adı ya da şifreniz yanlış, lütfen tekrar deneyiniz.");
								}
							} else {
								Helper.showMsg("Böyle bir hasta bulunamadı, lütfen kayıt olunuz.");
							}
						rs.close();
						st.close();
						con.close();
							
							
						}
					 catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btn_hastaLogin.setBounds(245, 123, 198, 40);
		w_hastaLogin.add(btn_hastaLogin);

		JPanel w_doktorLogin = new JPanel();
		w_doktorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);

		JLabel lblTcNumaranz_1 = new JLabel("T.C. Numaranız :");
		lblTcNumaranz_1.setFont(new Font("Apple Braille", Font.BOLD, 16));
		lblTcNumaranz_1.setBounds(30, 47, 129, 16);
		w_doktorLogin.add(lblTcNumaranz_1);

		fld_doktorTc = new JTextField();
		fld_doktorTc.setFont(new Font("Apple Braille", Font.BOLD, 15));
		fld_doktorTc.setColumns(10);
		fld_doktorTc.setBounds(171, 41, 205, 26);
		w_doktorLogin.add(fld_doktorTc);

		JLabel lblifre_1 = new JLabel("Şifre:");
		lblifre_1.setFont(new Font("Apple Braille", Font.BOLD, 16));
		lblifre_1.setBounds(117, 95, 42, 16);
		w_doktorLogin.add(lblifre_1);

		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (fld_doktorTc.getText().length() == 0 || fld_doktorPass.getText().length() == 0) {

					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st
								.executeQuery("SELECT * FROM user WHERE tcno = '" + fld_doktorTc.getText() + "'");

						if (rs.next()) {
							if (fld_doktorPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword(rs.getString("password"));
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose(); // Giriş yapıldıktan sonra pencereyi kapat
								} else if (rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword(rs.getString("password"));
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose(); // Giriş yapıldıktan sonra pencereyi kapat
								}
							} else {
								Helper.showMsg("Kullanıcı adı ya da şifreniz yanlış, lütfen tekrar deneyiniz.");
							}
						} else {
							Helper.showMsg("Böyle bir doktor bulunamadı, kayıt için başhekim ile iletişime geçiniz.");
						}

						rs.close();
						st.close();
						con.close();
					} catch (SQLException e1) {
						System.out.println("Bağlantı başarısız");
						e1.printStackTrace();
					}

				}

			}
		});
		btn_doctorLogin.setBounds(25, 123, 425, 40);
		w_doktorLogin.add(btn_doctorLogin);

		fld_doktorPass = new JPasswordField();
		fld_doktorPass.setBounds(171, 89, 205, 26);
		w_doktorLogin.add(fld_doktorPass);
	}
}
