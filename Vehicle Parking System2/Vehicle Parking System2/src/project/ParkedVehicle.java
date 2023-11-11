package project;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ParkedVehicle {

	JFrame frame=new JFrame("Parked Vehicles");
	JPanel rec_panel,typedate_panel,fpanel,table_panel,search_panel,btn_panel;
	JLabel rec_lbl,type_lbl,search_lbl;
	JButton back_btn,search_btn;
	JTextField tf_search;
	String veh_type[]= {"All","Car","Bus","Truck"};
	JComboBox type,vehicle_type;
	JTable table;
	DefaultTableModel model;
	public ParkedVehicle() {
		setTable("All");
	}
	public void setFrame() {
		
		frame.getContentPane().removeAll();
		frame.setVisible(false);
		//setup for records label
		rec_lbl=new JLabel("PARKED VEHICLES");
		rec_lbl.setFont(new Font("Algerian",Font.BOLD,25));
		rec_lbl.setForeground(Color.YELLOW);
		// rec_panel setup
		rec_panel=new JPanel();
		rec_panel.add(rec_lbl);
		rec_panel.setBackground(new Color(150,0,150));
		
		// setup for type date panel
		typedate_panel=new JPanel();
		typedate_panel.setBackground(new Color(150,0,150));
	
			//setup for vehicle type label and combo box
		type_lbl=new JLabel("Type: ");
		type_lbl.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		type_lbl.setForeground(Color.white);
		vehicle_type=new JComboBox(veh_type);
		vehicle_type.setBackground(Color.WHITE);

		typedate_panel.add(type_lbl);
		typedate_panel.add(vehicle_type);
		// setup for tabel
		
		search_lbl=new JLabel("Search: ");
		search_lbl.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		search_lbl.setForeground(Color.WHITE);
		
		tf_search=new JTextField(15);
		tf_search.setBackground(Color.white);
		
		search_panel=new JPanel();
		search_panel.setBackground(new Color(150,0,150));
		search_panel.add(search_lbl);
		search_panel.add(tf_search);
		
		search_btn=new JButton("Search");
		search_btn.setBackground(Color.PINK);
		search_btn.setForeground(Color.blue);
		search_btn.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		
		back_btn=new JButton("Back");
		back_btn.setBackground(Color.PINK);
		back_btn.setForeground(Color.blue);
		back_btn.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		
		btn_panel=new JPanel();
		btn_panel.setBackground(new Color(150,0,150));
		btn_panel.add(search_btn);
		btn_panel.add(back_btn);
		
		
		//setup for final panel
		fpanel=new JPanel();
		fpanel.setBackground(new Color(150,0,150));
		fpanel.setLayout(new BoxLayout(fpanel,BoxLayout.Y_AXIS));
		fpanel.add(rec_panel);
		fpanel.add(typedate_panel);
		fpanel.add(table_panel);
		fpanel.add(search_panel);
		fpanel.add(btn_panel);
		
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().add(fpanel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		addActions();
	}
	
	public void setTable(String t) {
		
		String sql="";
		Connection con=null;
		String[] heading= {"Sr#","Vehicle No","Entry Time","Type"};
		model=new DefaultTableModel(heading,0);
		table=new JTable(model);
		
		Statement stmt;
		ResultSet rs;
		if(t=="All") {
			sql="SELECT * FROM ParkedVehicles";
		}
		else if(t=="Car") {
			sql="SELECT * FROM ParkedVehicles WHERE Type Like 'Car'";
		}
		else if(t=="Bus") {

			sql="SELECT * FROM ParkedVehicles WHERE Type Like 'Bus'";
		}else if(t=="Truck") {

			sql="SELECT * FROM ParkedVehicles WHERE Type Like 'Truck'";
		}else {
			sql="";
		}
		
		try {

			con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
			int i=1;
			
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				model.addRow(new Object[] {i,rs.getString("V_No"),rs.getTime("EntryTime"),rs.getString("Type")});
				i++;
			}
			rs.close();
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table_panel=new JPanel();
		table_panel.setBackground(new Color(150,0,150));
		table_panel.removeAll();
		table_panel.add(new JScrollPane(table));
		setFrame();
		
	}
	public void searchVehicle(String t) {
		try {
			Connection con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
			String sql="SELECT * FROM ParkedVehicles WHERE V_No LIKE '"+t+"'";
			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "VEHICLE NO: "+rs.getString("V_No")+"\nEntry Time: "+rs.getTime("EntryTime")
				+"\nTYPE: "+rs.getString("Type"));
			}
			else {
				JOptionPane.showMessageDialog(null, "Vehicle Not Found");
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void addActions() {
		back_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==back_btn) {
					frame.dispose();
					new HomePage();
				}
			}
		});
		 
		vehicle_type.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String t=vehicle_type.getSelectedItem().toString();
				setTable(t);
			}
		
		});
		//Search
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tf_search.getText().isEmpty()) {
					setTable("All");
				}
				else {
					String t=tf_search.getText();
					searchVehicle(t);
				}
			}
		});
		
	}

}
