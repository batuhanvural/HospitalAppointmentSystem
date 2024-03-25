package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Bashekim;
import Model.Clinic;
import Model.Hasta;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Bashekim bashekim = new Bashekim();
	Clinic clinic = new Clinic();
	Appointment appointment = new Appointment();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTc;
	private JTextField fld_dPass;
	private JTextField fld_dKid;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;
	private JTable table_appoint;
	private DefaultTableModel appointModel = null;
	private Object[] appointData = null;
	private JPopupMenu appointMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) {

		/* Doctor Model */

		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];

		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

		/* Worker Model */

		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		/* Clinic Model */

		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Poliklinik Adı";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];

		for (int i = 0; i < clinic.getList().size(); i++) {

			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		
		/*	Appoint Model */
		
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[4];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doktor Adı";
		colAppoint[2] = "Hasta Adı";
		colAppoint[3] = "Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[4];
		
		for (int i = 0; i < appointment.getList().size(); i++) {
			
			appointData[0] = appointment.getList().get(i).getId();
			appointData[1] = appointment.getList().get(i).getDoctorName();
			appointData[2] = appointment.getList().get(i).getHastaName();
			appointData[3] = appointment.getList().get(i).getAppDate();
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

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın " + bashekim.getName());
		lblNewLabel.setBounds(6, 6, 294, 21);
		lblNewLabel.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(627, 3, 117, 29);
		btnNewButton.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		w_pane.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 39, 738, 427);
		w_pane.add(tabbedPane);

		JPanel w_doctor = new JPanel();
		tabbedPane.addTab("Doktor Yönetimi", null, w_doctor, null);
		w_doctor.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(562, 23, 149, 16);
		w_doctor.add(lblNewLabel_1);

		fld_dName = new JTextField();
		fld_dName.setHorizontalAlignment(SwingConstants.CENTER);
		fld_dName.setFont(new Font("Heiti SC", Font.PLAIN, 15));
		fld_dName.setColumns(10);
		fld_dName.setBounds(562, 40, 149, 37);
		w_doctor.add(fld_dName);

		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setFont(new Font("Heiti SC", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(562, 94, 149, 16);
		w_doctor.add(lblNewLabel_1_1);

		fld_dTc = new JTextField();
		fld_dTc.setHorizontalAlignment(SwingConstants.CENTER);
		fld_dTc.setColumns(10);
		fld_dTc.setBounds(562, 111, 149, 37);
		w_doctor.add(fld_dTc);

		JLabel lblNewLabel_1_2 = new JLabel("Şifre");
		lblNewLabel_1_2.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(562, 160, 149, 16);
		w_doctor.add(lblNewLabel_1_2);

		fld_dPass = new JTextField();
		fld_dPass.setHorizontalAlignment(SwingConstants.CENTER);
		fld_dPass.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(562, 177, 149, 37);
		w_doctor.add(fld_dPass);

		JButton btn_dAdd = new JButton("Ekle");
		btn_dAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0
						|| fld_dTc.getText().length() == 0) {

					Helper.showMsg("fill");
				} else {
					boolean control = bashekim.addDoctor(fld_dTc.getText(), fld_dPass.getText(), fld_dName.getText());
					if (control) {
						Helper.showMsg("success");
						fld_dName.setText(null);
						fld_dTc.setText(null);
						fld_dPass.setText(null);
						updateDoctorModel();

					} else {

					}
				}

			}
		});
		btn_dAdd.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		btn_dAdd.setBounds(562, 226, 149, 29);
		w_doctor.add(btn_dAdd);

		JLabel lblNewLabel_1_2_1 = new JLabel("Kullanıcı ID");
		lblNewLabel_1_2_1.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		lblNewLabel_1_2_1.setBounds(562, 263, 149, 16);
		w_doctor.add(lblNewLabel_1_2_1);

		fld_dKid = new JTextField();
		fld_dKid.setHorizontalAlignment(SwingConstants.CENTER);
		fld_dKid.setEnabled(false);
		fld_dKid.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		fld_dKid.setEditable(false);
		fld_dKid.setColumns(10);
		fld_dKid.setBounds(562, 280, 149, 37);
		w_doctor.add(fld_dKid);

		JButton btn_dRmv = new JButton("Sil");
		btn_dRmv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dKid.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz !");

				} else {

					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_dKid.getText());
						boolean control = bashekim.deleteDoctor(selectID);
						if (control) {
							Helper.showMsg("success");
							fld_dKid.setText(null);
							updateDoctorModel();
						} else {
						}
					}

				}
			}
		});
		btn_dRmv.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		btn_dRmv.setBounds(562, 329, 149, 29);
		w_doctor.add(btn_dRmv);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(6, 6, 543, 369);
		w_doctor.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					fld_dKid.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());

				} catch (Exception e2) {

					e2.printStackTrace();

				}

			}
		});

		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				if (e.getType() == TableModelEvent.UPDATE) {

					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();

					boolean control = bashekim.updateDoctor(selectID, selectTcno, selectPass, selectName);

				} else {

				}

			}
		});

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(6, 6, 271, 369);
		w_clinic.add(w_scrollClinic);

		/* Sağ click ile pop-up menüsü açtırma */

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		clinicMenu.add(updateMenu);
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);

				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						updateClinicModel();
					}
				});

			}
		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					if (clinic.deleteClinic(selID)) {
						Helper.showMsg("success");
						updateClinicModel();

					} else {
						Helper.showMsg("error");

					}

				} else {

				}

			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);

		/*
		 * mouse un hangi koordinatta sağ click yaptığını anlayıp o satırı baz alma kodu
		 */

		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});

		w_scrollClinic.setViewportView(table_clinic);

		JLabel lblNewLabel_1_3 = new JLabel("Poliklinik Adı");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(289, 6, 149, 16);
		w_clinic.add(lblNewLabel_1_3);

		fld_clinicName = new JTextField();
		fld_clinicName.setHorizontalAlignment(SwingConstants.CENTER);
		fld_clinicName.setFont(new Font("Heiti SC", Font.PLAIN, 15));
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(289, 23, 149, 37);
		w_clinic.add(fld_clinicName);

		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_clinicName.getText().length() == 0) {

					Helper.showMsg("fill");

				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();

						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			}
		});
		btn_addClinic.setBounds(289, 72, 149, 29);
		w_clinic.add(btn_addClinic);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(450, 6, 261, 369);
		w_clinic.add(w_scrollWorker);

		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);

		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(289, 280, 149, 51);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {

			select_doctor.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));

		}
		select_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());

		});
		w_clinic.add(select_doctor);

		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {

					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();
					boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
					if (control) {
						Helper.showMsg("success");

					} else {
						Helper.showMsg("available");

					}

				} else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz.");
				}
			}
		});
		btn_addWorker.setBounds(289, 329, 149, 29);
		w_clinic.add(btn_addWorker);

		JLabel lblNewLabel_1_3_1 = new JLabel("Poliklinik Adı");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		lblNewLabel_1_3_1.setBounds(289, 134, 149, 16);
		w_clinic.add(lblNewLabel_1_3_1);

		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {

					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);

					for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {

						workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
						workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
						workerModel.addRow(workerData);

					}
					table_worker.setModel(workerModel);

				} else {

					Helper.showMsg("Lütfen bir poliklinik seçiniz !");

				}

			}
		});
		btn_workerSelect.setBounds(289, 163, 149, 29);
		w_clinic.add(btn_workerSelect);
		
		JPanel panel_appoint = new JPanel();
		panel_appoint.setBackground(Color.WHITE);
		tabbedPane.addTab("Randevular", null, panel_appoint, null);
		panel_appoint.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(6, 6, 705, 369);
		panel_appoint.add(w_scrollAppoint);
		
		table_appoint = new JTable(appointModel);
		table_appoint.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		w_scrollAppoint.setViewportView(table_appoint);

	}

	public void updateDoctorModel() {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);

		try {
			for (int i = 0; i < bashekim.getDoctorList().size(); i++) {

				doctorData[0] = bashekim.getDoctorList().get(i).getId();
				doctorData[1] = bashekim.getDoctorList().get(i).getName();
				doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
				doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
				doctorModel.addRow(doctorData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateClinicModel() {

		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);

		try {
			for (int i = 0; i < clinic.getList().size(); i++) {

				clinicData[0] = clinic.getList().get(i).getId();
				clinicData[1] = clinic.getList().get(i).getName();
				clinicModel.addRow(clinicData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void updateAppointmentModel() {

		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);

		try {
			
			for (int i = 0; i < appointment.getList().size(); i++) {
				
				appointData[0] = appointment.getList().get(i).getId();
				appointData[1] = appointment.getList().get(i).getDoctorName();
				appointData[2] = appointment.getList().get(i).getHastaName();
				appointData[3] = appointment.getList().get(i).getAppDate();
				appointModel.addRow(appointData);			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
