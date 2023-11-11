package project;

import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class SearchPrevoiusRecords{

	boolean dlt=false;
	JFrame frame=new JFrame("Prevoius History");
	JPanel rec_panel,typedate_panel,fpanel,table_panel,search_panel,btn_panel;
	JLabel rec_lbl,date_lbl,type_lbl,search_lbl;
	JButton back_btn,search_btn,delete_btn,delete_all_btn;
	JTextField tf_search;
	String[] date_combo=new String[11];
	String veh_type[]= {"Car","Bus","Truck"};
	JComboBox vehicle_type,date;
	JTable table;
	DefaultTableModel model;
	
	MouseListener m1;
	
	public SearchPrevoiusRecords() {
		setFrame();
		setTable();
		addActions();
	}
	public void setFrame() {
		//setup for records label
		rec_lbl=new JLabel("RECORDS");
		rec_lbl.setFont(new Font("Algerian",Font.BOLD,25));
		rec_lbl.setForeground(Color.YELLOW);
		// rec_panel setup
		rec_panel=new JPanel();
		rec_panel.add(rec_lbl);
		rec_panel.setBackground(new Color(150,0,150));
		
		// setup for type date panel
		typedate_panel=new JPanel();
		typedate_panel.setBackground(new Color(150,0,150));
	
			//setup for date label and combo box
		date_lbl=new JLabel("Date:");
		date_lbl.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		date_lbl.setForeground(Color.white);
		
		
		date=new JComboBox();
		getDates();
		date.setBackground(Color.WHITE);
		typedate_panel.add(date_lbl);
		typedate_panel.add(date);
			//setup for vehicle type label and combo box
		type_lbl=new JLabel("Type: ");
		type_lbl.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		type_lbl.setForeground(Color.white);
		vehicle_type=new JComboBox(veh_type);
		vehicle_type.setBackground(Color.WHITE);

		typedate_panel.add(type_lbl);
		typedate_panel.add(vehicle_type);
		// setup for tabel
		
		String[] heading= {"Sr#","Vehicle No","Entry Time","Exit Time","Date"};
		model=new DefaultTableModel(heading,0);
		table=new JTable(model);
		
		table_panel=new JPanel();
		table_panel.setBackground(new Color(150,0,150));
		table_panel.add(new JScrollPane(table));

		//setup for search process
		
		search_lbl=new JLabel("Search By No:");
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
		
		delete_btn=new JButton("Delete");
		delete_btn.setBackground(Color.PINK);
		delete_btn.setForeground(Color.blue);
		delete_btn.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		
		delete_all_btn=new JButton("Delete All");
		delete_all_btn.setBackground(Color.PINK);
		delete_all_btn.setForeground(Color.blue);
		delete_all_btn.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		
		back_btn=new JButton("Back");
		back_btn.setBackground(Color.PINK);
		back_btn.setForeground(Color.blue);
		back_btn.setFont(new Font("Cosmic .Sans",Font.BOLD,15));
		
		btn_panel=new JPanel();
		btn_panel.setBackground(new Color(150,0,150));
		btn_panel.add(search_btn);
		btn_panel.add(delete_btn);
		btn_panel.add(delete_all_btn);
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
	}
	
	public void getDates() {
		try {
			Connection con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM Dates");
//			date_combo[0]="All";
			date.addItem("All");
			while(rs.next()) {		
				Date d=rs.getDate("Dates");
				date.addItem(d);
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e);
		}
		
	}
	
	public void setTable() {
		try {
			
		model.setRowCount(0);
		String v_type=vehicle_type.getSelectedItem().toString();
		String sql="";
		date.setSelectedItem("All");
		sql="SELECT * FROM "+v_type;
		Connection con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		int i=1;
		while(rs.next()) {
			
				model.addRow(new Object[] {i,rs.getString("V_No"),rs.getTime("EntryTime")
						,rs.getTime("ExitTime"),rs.getDate("Date")});
				i++;
			
		}
		model.fireTableStructureChanged();
		
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void addActions() {
		back_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==back_btn) {
					frame.dispose();
					new HomePage();
				}
			}
		});
		date.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				if(date.getSelectedItem()=="All")
					setTable();
				else
					updateTable();
			}
		});
		vehicle_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date.setSelectedItem("All");
				setTable();
			}
		});
		
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tf_search.getText().equals(""))
					setTable();
				else {
					searchRecords();
				}
			}
		});
		m1=new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String v_no=table.getValueAt(table.getSelectedRow(),1)+"";
				String entryTime=table.getValueAt(table.getSelectedRow(), 2)+"";
				String exitTime=table.getValueAt(table.getSelectedRow(), 3)+"";
				String v_type=vehicle_type.getSelectedItem().toString();
				String sql="DELETE FROM "+v_type+" WHERE V_No = '"+v_no+"' AND CAST(EntryTime as Time) = '"
						+entryTime+"' AND CAST(ExitTime as Time)= '"+exitTime+"'";
				try {
					Connection con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
					Statement stmt=con.createStatement();
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Deleted:  "+
					"\n"+v_no);				
				}catch(SQLException e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		};
		
		delete_btn.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				if(dlt) {
					dlt=false;
					delete_btn.setBackground(Color.pink);
					delete_btn.setForeground(Color.BLUE);
					table.removeMouseListener(m1);
				}
				
				else if(!dlt) {
					dlt=true;
					delete_btn.setBackground(Color.RED);
					delete_btn.setForeground(Color.CYAN);
					table.addMouseListener(m1);
				}
			}
		});
		
		delete_all_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice=JOptionPane.showConfirmDialog(null, "Are You sure to delete All records\nFrom Given Table");
				if(choice==0) {
					deleteAll();
				}
			}
		});
	}
	public void updateTable() {
		String[] heading= {"Sr#","Vehicle No","Entry Time","Exit Time","Date"};
		DefaultTableModel tempModel=new DefaultTableModel(heading,0);
		String d=date.getSelectedItem().toString();
		int tableRows=table.getRowCount();
		int j=0;
		for(int i=0;i< tableRows;i++) {
			String st=table.getValueAt(i, 4).toString();
			if(d.equals(st+"")) {
				tempModel.addRow(new Object[] {++j, model.getValueAt(i, 1),
						model.getValueAt(i, 2),model.getValueAt(i, 3),model.getValueAt(i, 4)});
			}
		}
		model.setRowCount(0);
		
		for(int i=0;i<tempModel.getRowCount();i++) {
			model.addRow(new Object[] {tempModel.getValueAt(i, 0), tempModel.getValueAt(i, 1),
					tempModel.getValueAt(i, 2),tempModel.getValueAt(i, 3),tempModel.getValueAt(i, 4)});
		}
		
		model.fireTableStructureChanged();
	}
	public void searchRecords() {
		try{
			Connection con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
			String v_type=vehicle_type.getSelectedItem().toString();
			String v_no=tf_search.getText();
			String sql="SELECT * FROM "+v_type+" WHERE V_No LIKE '"+v_no+"'";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			model.setRowCount(0);
			int i=0;
			while(rs.next()) {
				model.addRow(new Object[] {++i,rs.getString("V_No"),rs.getTime("EntryTime"),
						rs.getTime("ExitTime"),rs.getDate("Date")});
				
			}
			model.fireTableStructureChanged();
			tf_search.setText("");
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "RECORDS NOT FOUND");
		}
	}
	public void deleteAll() {
		try {
			Connection con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
			String v_type=vehicle_type.getSelectedItem().toString();
			for(int i=0;i<model.getRowCount();i++) {
				String v_no=model.getValueAt(i,1)+"";
				String entryTime=model.getValueAt(i, 2)+"";
				String exitTime=model.getValueAt(i, 3)+"";
				String sql="DELETE FROM "+v_type+" WHERE V_No = '"+v_no
						+"' AND CAST(EntryTime as Time) = '"+entryTime+"' AND "
						+"CAST(ExitTime as Time) = '"+exitTime+"'";
				
				Statement stmt=con.createStatement();
				stmt.executeUpdate(sql);
			}
			setTable();
			
		}catch(SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}
}
