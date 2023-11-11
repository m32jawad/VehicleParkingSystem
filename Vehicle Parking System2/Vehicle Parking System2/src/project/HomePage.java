package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import javax.swing.*;

public class HomePage{

	JLabel lb_vehicleNo,lb_type,label;
	JTextField tf_vehicleNo;
	JComboBox vehicle_type;
	JButton enter_btn,exit_btn;
	String veh_type[]= {"Select Type","Car","Bus","Truck"};
	JFrame frame=new JFrame();
	JMenuBar menu_bar=new JMenuBar();
	JMenu menu,menu_details,menu_help,t_spaces,spaces_left;
	JMenuItem update,delete,logout,view,history;
	JPanel fpanel,panel1,panel;
	int car_count;
	int bus_count;
	int truck_count;
	
	HomePage(){
		
		frameSetup();
		availableSpaces();
		addActions();
	}
	public void frameSetup() {
		label=new JLabel("VEHICLE ENTRY");
		label.setFont(new Font("Algerian",Font.BOLD,25));
		label.setForeground(Color.YELLOW);
		panel1=new JPanel();
		panel1.add(label);
		panel1.setBackground(new Color(150,0,150));
		
		//Setup for menu
		menu=new JMenu("Menu");
		update=new JMenuItem("Edit");
		delete=new JMenuItem("Delete");
		logout=new JMenuItem("Log Out");
		menu.add(update);
		menu.add(delete);
		menu.add(logout);
		menu_bar.add(menu);
		
		//Setup for Details
		menu_details=new JMenu("Details");
		view=new JMenuItem("View Parked Vehicles");
		history=new JMenuItem("Search Prevoius Records");
		t_spaces=new JMenu("Total Space");
		spaces_left=new JMenu("Available Space");
		menu_details.add(view);
		menu_details.add(history);
		menu_details.add(t_spaces);
		t_spaces.add("Cars: 50");
		t_spaces.add("Busses: 30");
		t_spaces.add("Trucks: 20");
		
		menu_details.add(spaces_left);
		menu_bar.add(menu_details);
		//setup for help
		menu_help=new JMenu("Help");
		menu_bar.add(menu_help);
		
		//setup for label and text field
		lb_vehicleNo=new JLabel("Vehicle No:");
		lb_vehicleNo.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lb_vehicleNo.setForeground(Color.WHITE);
		
		tf_vehicleNo=new JTextField(15);
		tf_vehicleNo.setBackground(Color.white);
		

		//setup for Combo box 
		lb_type=new JLabel("Type Of Vehicle:");
		lb_type.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lb_type.setForeground(Color.white);
		vehicle_type=new JComboBox(veh_type);
		vehicle_type.setBackground(Color.WHITE);

		// enter and exit Button Setup
		enter_btn=new JButton("Enter");
		enter_btn.setBackground(Color.PINK);
		enter_btn.setForeground(Color.blue);
		enter_btn.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		exit_btn=new JButton("Exit");
		exit_btn.setBackground(Color.PINK);
		exit_btn.setForeground(Color.blue);
		exit_btn.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		
		// fpanel and GridBagLayout setup
		panel=new JPanel();
		panel.setBackground(new Color(150,0,150));
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		
		gbc.fill=GridBagConstraints.HORIZONTAL;

		gbc.gridx=0; gbc.gridy=0;
		panel.add(lb_vehicleNo,gbc);
		
		gbc.gridx=0; gbc.gridy=1;
		panel.add(tf_vehicleNo,gbc);

		gbc.gridx=0; gbc.gridy=2;
		panel.add(lb_type,gbc);

		gbc.gridx=0; gbc.gridy=3;
		panel.add(vehicle_type,gbc);

		gbc.gridx=1; gbc.gridy=4;
		panel.add(enter_btn,gbc);
		

		gbc.insets=new Insets(10,10,10,10);
		gbc.gridx=2; gbc.gridy=4;
		panel.add(exit_btn,gbc);
		fpanel=new JPanel();
		fpanel.add(panel1);
		fpanel.add(panel);
		fpanel.setBackground(new Color(150,0,150));
		
		//frame setup
		frame.add(menu_bar);
		frame.setSize(400, 350);
		frame.setTitle("HomePage");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(BorderLayout.NORTH,menu_bar);
		frame.getContentPane().add(fpanel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void availableSpaces() {
		try {
			Connection con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
			String sql="SELECT COUNT(*) AS count FROM ParkedVehicles WHERE TYPE LIKE 'Car'";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			rs.next();
			car_count=rs.getInt("count");
			
			sql="SELECT COUNT(*) AS count FROM ParkedVehicles WHERE Type LIKE 'Bus'";
			rs=stmt.executeQuery(sql);
			rs.next();
			bus_count=rs.getInt("count");
			
			
			sql="SELECT COUNT(*) AS count FROM ParkedVehicles WHERE Type LIKE 'Truck'";
			rs=stmt.executeQuery(sql);
			rs.next();
			truck_count=rs.getInt("count");
			
			spaces_left.removeAll();
			spaces_left.add("Car: "+(50-car_count));
			spaces_left.add("Bus: "+(30-bus_count));
			spaces_left.add("Truck: "+(20-truck_count));
			
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public boolean spaceAvailable() {
		int count;
		String type=vehicle_type.getSelectedItem().toString();
		if(type=="Car") {
			if(car_count<50)
				return true;
			else
				return false;
		}
		else if(type=="Bus") {
			if(bus_count<30)
				return true;
			else
				return false;
		}
		else if(type=="Truck") {
			if(truck_count<20)
				return true;
			else
				return false;
		}
			
		return false;
	}
	public void addActions() {
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==logout) {
					frame.dispose();
					new LoginFrame();
				}
			}
			
		});
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==update) {
					frame.dispose();
					new UpdateFrame();
				}
			}
			
		});
		history.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==history) {
					frame.dispose();
					new  SearchPrevoiusRecords();
				}
			}
			
		});
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==delete) {
					frame.dispose();
					new  SearchPrevoiusRecords();
				}
			}
			
		});
		
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ParkedVehicle();				
			}
		});
		enter_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=null;
				try{
					con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1+""+JOptionPane.ERROR_MESSAGE);
				}
				
				try {
					if(vehicle_type.getSelectedItem().equals("Select Type")) {
						JOptionPane.showMessageDialog(null, "Select Vehicle Type"+JOptionPane.ERROR_MESSAGE);
					}
					else {
						if(spaceAvailable()) {
						String sql="INSERT INTO ParkedVehicles(V_No,Type,EntryTime)"
									+ "VALUES(?,?,?)";
						PreparedStatement pstmt=con.prepareStatement(sql);

						pstmt.setString(1, tf_vehicleNo.getText());

						String t=vehicle_type.getSelectedItem()+"";
						pstmt.setString(2, t);
						pstmt.setTime(3, new java.sql.Time(System.currentTimeMillis()));
						pstmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Vehicle Parked");
						availableSpaces();
						}
						else {
							JOptionPane.showMessageDialog(null, "No Space Available");
						}
					}
				}catch(SQLException e2) {
						JOptionPane.showMessageDialog(null, "AlreadyParked"+JOptionPane.ERROR_MESSAGE);
					}					
			}
		});
		
		//EXIT
		exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
					String sql="SELECT * FROM ParkedVehicles WHERE V_No LIKE '"+tf_vehicleNo.getText()+"'";
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(sql);
					
					rs.next();
					java.sql.Time t=rs.getTime("EntryTime");
					String type=rs.getString("Type");
					
					stmt.close();
					con.clearWarnings();
					String sql2="";
					if(type.equals("Car")) {
						sql2="INSERT INTO Car(V_No,EntryTime,ExitTime,date) "
								+ "VALUES(?,?,?,?)";
					}
					else if(type.equals("Bus")) {
						sql2="INSERT INTO Bus(V_No,EntryTime,ExitTime,date) "
								+ "VALUES (?,?,?,?)";
					}else if(type.equals("Truck")) {
						sql2="INSERT INTO Truck(V_No,EntryTime,ExitTime,date) "
								+ "VALUES (?,?,?,?)";
					}
					else{
						
					}
					try{
						PreparedStatement stmt2=con.prepareStatement(sql2);
					
					Date date= new java.sql.Date(System.currentTimeMillis());
					stmt2.setString(1, tf_vehicleNo.getText());
					stmt2.setTime(2, t);
					stmt2.setTime(3, new java.sql.Time(System.currentTimeMillis()));
					stmt2.setDate(4, date);
					stmt2.executeUpdate();

					 sql="DELETE FROM ParkedVehicles WHERE V_No LIKE '"+tf_vehicleNo.getText()+"'";
					Statement stmt3=con.createStatement();
					stmt3.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Vehicle Released");
					availableSpaces();
					}catch(SQLException e3) {
						JOptionPane.showMessageDialog(null,"Vehicle Not Found");
					}

					con.clearWarnings();
					con.close();
					
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1+"");
				}
			}
		});
		menu_help.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new HelpFrame();
			}
		});
	}
}

