package project;

import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.*;

public class UpdateFrame {

	JFrame frame;
	JLabel label,O_veh_no,N_veh_no;
	JTextField tf_veh_no_old;
	JPanel panel1,panel,fpanel;
	JTextField tf_Old_no,tf_new_no;
	JButton ok_btn,back_btn;
	public UpdateFrame() {
		setFrame();
	}
	public  void setFrame() {
		label=new JLabel("UPDATING VEHICLE RECORDS");
		label.setFont(new Font("Algerian",Font.BOLD,25));
		label.setForeground(Color.YELLOW);
		panel1=new JPanel();
		panel1.add(label);
		panel1.setBackground(new Color(150,0,150));
		
		O_veh_no=new JLabel("Old Vehicle No:");
		O_veh_no.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		O_veh_no.setForeground(Color.white);
		tf_veh_no_old=new JTextField(15);
		tf_veh_no_old.setBackground(Color.white);
		
		N_veh_no=new JLabel("New Vehicle No:");
		N_veh_no.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		N_veh_no.setForeground(Color.white);
		tf_new_no= new JTextField(15);
		tf_new_no.setBackground(Color.white);
				
		
		ok_btn=new JButton("OK");
		ok_btn.setBackground(Color.PINK);
		ok_btn.setForeground(Color.blue);
		ok_btn.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		back_btn=new JButton("Back");
		back_btn.setBackground(Color.PINK);
		back_btn.setForeground(Color.blue);
		back_btn.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		
		panel=new JPanel();
		panel.setBackground(new Color(150,0,150));
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		
		gbc.fill=GridBagConstraints.HORIZONTAL;

		gbc.gridx=0; gbc.gridy=0;
		panel.add(O_veh_no,gbc);
		
		gbc.gridx=0; gbc.gridy=1;
		panel.add(tf_veh_no_old,gbc);

		gbc.gridx=0; gbc.gridy=2;
		panel.add(N_veh_no,gbc);

		gbc.gridx=0; gbc.gridy=3;
		panel.add(tf_new_no,gbc);

		gbc.gridx=1; gbc.gridy=4;
		panel.add(ok_btn,gbc);
		

		gbc.insets=new Insets(10,10,10,10);
		gbc.gridx=2; gbc.gridy=4;
		panel.add(back_btn,gbc);
		fpanel=new JPanel();
		fpanel.add(panel1);
		fpanel.add(panel);
		fpanel.setBackground(new Color(150,0,150));
		
		
		frame=new JFrame("Update Record");
		frame.setSize(400, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().add(fpanel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		addActions();
	}
	public void addActions() {
		back_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==back_btn) {
					frame.dispose();
					new HomePage();
				}
			}
			public void mouseEntered(MouseEvent e) {
				back_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		
			public void mouseExited(MouseEvent e) {
				back_btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		});
		ok_btn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {				
				String newNo=tf_new_no.getText();
				String oldNo=tf_veh_no_old.getText();
					String sql="UPDATE ParkedVehicles SET V_No = '"+newNo+"' WHERE V_No = '"
							+oldNo+"'";
					try {
						Connection con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
						Statement stmt=con.createStatement();
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Vehicle Number Updated Successfully");
					}catch(SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Vehicle of That number Not Found");
					}				
			}
			public void mouseEntered(MouseEvent e) {
				ok_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		
			public void mouseExited(MouseEvent e) {
				ok_btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
	}

}
