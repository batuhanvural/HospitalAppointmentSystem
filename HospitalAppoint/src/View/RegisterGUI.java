package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tc;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setBounds(100, 100, 300, 330);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(6, 6, 288, 16);
		w_pane.add(lblNewLabel_1);

		fld_name = new JTextField();
		fld_name.setHorizontalAlignment(SwingConstants.CENTER);
		fld_name.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		fld_name.setColumns(10);
		fld_name.setBounds(6, 23, 288, 37);
		w_pane.add(fld_name);

		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Heiti SC", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(6, 77, 288, 16);
		w_pane.add(lblNewLabel_1_1);

		fld_tc = new JTextField();
		fld_tc.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		fld_tc.setHorizontalAlignment(SwingConstants.CENTER);
		fld_tc.setColumns(10);
		fld_tc.setBounds(6, 94, 288, 37);
		w_pane.add(fld_tc);

		JLabel lblNewLabel_1_2 = new JLabel("Şifre");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(6, 143, 288, 16);
		w_pane.add(lblNewLabel_1_2);

		fld_pass = new JPasswordField();
		fld_pass.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		fld_pass.setHorizontalAlignment(SwingConstants.CENTER);
		fld_pass.setBounds(6, 171, 288, 37);
		w_pane.add(fld_pass);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_tc.getText().length() == 0 || fld_pass.getText().length() == 0
						|| fld_name.getText().length() == 0) {

					Helper.showMsg("fill");

				} else {

					boolean control = hasta.register(fld_tc.getText(), fld_pass.getText(), fld_name.getText());
					if (control) {
						Helper.showMsg("success");
						LoginGUI login = new LoginGUI();
						login.setVisible(true);
						dispose();

					} else {
						Helper.showMsg("available");

					}

				}
			}
		});
		btn_register.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		btn_register.setBounds(74, 220, 149, 29);
		w_pane.add(btn_register);

		JButton btn_backTo = new JButton("Geri Dön");
		btn_backTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backTo.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		btn_backTo.setBounds(74, 261, 149, 29);
		w_pane.add(btn_backTo);
	}
}
