package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fld_clinicName;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_upClinic = new JLabel("Poliklinik Adı");
		lbl_upClinic.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_upClinic.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		lbl_upClinic.setBounds(6, 6, 213, 16);
		contentPane.add(lbl_upClinic);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setHorizontalAlignment(SwingConstants.CENTER);
		fld_clinicName.setFont(new Font("Heiti SC", Font.PLAIN, 15));
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(6, 27, 213, 48);
		fld_clinicName.setText(clinic.getName());
		
		contentPane.add(fld_clinicName);
		
		JButton btn_upClinic = new JButton("Düzenle");
		btn_upClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (Helper.confirm("sure")) {
					try {
						clinic.updateClinic(clinic.getId(), fld_clinicName.getText());
						Helper.showMsg("success");
						dispose();
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				} else {

				}
				
			}
		});
		btn_upClinic.setBounds(6, 87, 213, 29);
		contentPane.add(btn_upClinic);
	}
}
