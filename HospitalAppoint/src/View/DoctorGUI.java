package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Appointment;
import Model.Doctor;
import Model.Hasta;

import static org.hamcrest.CoreMatchers.nullValue;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private static Doctor doctor = new Doctor();
	private static Hasta hasta = new Hasta();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appointment appoint = new Appointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];

		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {

			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);

		}
		
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Hasta";
		colAppoint[2] = "Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];

		for (int i = 0; i < appoint.getDoctorList(doctor.getId()).size(); i++) {

			appointData[0] = appoint.getDoctorList(doctor.getId()).get(i).getId();
			appointData[1] = appoint.getDoctorList(doctor.getId()).get(i).getHastaName();
			appointData[2] = appoint.getDoctorList(doctor.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
		}

		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setBounds(100, 100, 750, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın " + doctor.getName());
		lblNewLabel.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		lblNewLabel.setBounds(6, 9, 294, 21);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		btnNewButton.setBounds(627, 6, 117, 29);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(6, 39, 738, 427);
		w_pane.add(w_tab);

		JPanel w_whour = new JPanel();
		w_whour.setBackground(Color.WHITE);
		w_tab.addTab("Çalışma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(6, 6, 130, 20);
		w_whour.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		select_time.setModel(new DefaultComboBoxModel(new String[] { "10:00", "10:30", "11:00", "11:30", "12:00",
				"12:30", "13:30", "14:00", "14:30", "15:00", "15:30" }));
		select_time.setBounds(148, 6, 92, 20);
		w_whour.add(select_time);

		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String date = "";
				try {
					date = sdf.format(select_date.getDate());

				} catch (Exception e2) {
					// TODO: handle exception
				}

				if (date.length() == 0) {

					Helper.showMsg("Lütfen geçerli bir tarih giriniz.");

				} else {
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					boolean control = doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
					if (control) {
						Helper.showMsg("success");
						updateWhourModel(doctor);

					} else {
						Helper.showMsg("error");

					}

				}
			}
		});
		btn_addWhour.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		btn_addWhour.setBounds(252, 6, 75, 20);
		w_whour.add(btn_addWhour);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(6, 38, 705, 337);
		w_whour.add(w_scrollWhour);

		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);

		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {

					String selectRow = table_whour.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					boolean control;

					try {
						control = doctor.deleteWhour(selID);
						if (control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);

						} else {
							Helper.showMsg("error");
						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}

				} else {
					Helper.showMsg("Lütfen bir tarih seçiniz.");
				}

			}
		});
		btn_deleteWhour.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		btn_deleteWhour.setBounds(636, 6, 75, 20);
		w_whour.add(btn_deleteWhour);
		
		JPanel w_appointment = new JPanel();
		w_tab.addTab("Randevularım", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(6, 38, 705, 337);
		w_appointment.add(w_scrollAppoint);
		
		table_appoint = new JTable(appointModel);
		w_scrollAppoint.setViewportView(table_appoint);
		table_appoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				Point point = e.getPoint();
				int selRow = table_appoint.rowAtPoint(point);
				table_appoint.setRowSelectionInterval(selRow, selRow);

			}
		});
		
		JButton btn_cancelAppoint = new JButton("İptal Et");
		btn_cancelAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				


				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_appoint.getValueAt(table_appoint.getSelectedRow(), 0).toString());
					String selHastaName = table_appoint.getValueAt(table_appoint.getSelectedRow(), 1).toString();
					String selDate = table_appoint.getValueAt(table_appoint.getSelectedRow(), 2).toString();
					if (appoint.deleteAppointment(selID)) {
						Helper.showMsg("success");
						updateAppointModel(doctor);
						updateWhourModel(doctor);
						hasta.updateWhourStatusPasNoName(selDate);
						
						

					} else {
						Helper.showMsg("error");

					}

				} else {

				}

			
				
				
			}
		});
		btn_cancelAppoint.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		btn_cancelAppoint.setBounds(636, 6, 75, 20);
		w_appointment.add(btn_cancelAppoint);
	}

	public void updateWhourModel(Doctor doctor) {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);

		try {
			for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {

				whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
				whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
				whourModel.addRow(whourData);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void updateAppointModel(Doctor doctor) {

		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < appoint.getDoctorList(doctor.getId()).size(); i++) {

			appointData[0] = appoint.getDoctorList(doctor.getId()).get(i).getId();
			appointData[1] = appoint.getDoctorList(doctor.getId()).get(i).getHastaName();
			appointData[2] = appoint.getDoctorList(doctor.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
	}
}
