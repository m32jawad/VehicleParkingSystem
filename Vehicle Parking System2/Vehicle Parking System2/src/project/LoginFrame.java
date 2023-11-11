package project;

import java.awt.Button;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;

public class LoginFrame extends JFrame{

	JLabel l_name,l_password,label,login_label,fgt_label;
	JButton login_btn,cancel_btn;
	JTextField tf_name;
	JPasswordField pf_password;
	JPanel panel,panel1,fpanel;
	LoginFrame(){
		login();
		addActions();
	}
	public void login() {
		login_label=new JLabel("HI LOGIN HERE");
		login_label.setFont(new Font("Algerian",Font.BOLD,25));
		login_label.setForeground(Color.YELLOW);
		panel1=new JPanel();
		panel1.setBackground(new Color(150,0,150));
		panel1.add(login_label);
		
		l_name=new JLabel("User name:");
		l_name.setFont(new Font("Cosmic Sans",Font.BOLD,18));
		l_name.setForeground(Color.white);
		tf_name=new JTextField(15);
		tf_name.setBackground(Color.white);
		
		l_password=new JLabel("Password:");
		l_password.setFont(new Font("Cosmic Sans",Font.BOLD,18));
		l_password.setForeground(Color.white);
		pf_password= new JPasswordField(15);
		pf_password.setBackground(Color.white);

		login_btn=new JButton("Log In");
		login_btn.setBackground(Color.PINK);
		login_btn.setForeground(Color.blue);
		login_btn.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		cancel_btn=new JButton("Cancel");
		cancel_btn.setBackground(Color.PINK);
		cancel_btn.setForeground(Color.blue);
		cancel_btn.setFont(new Font("Cosmic Sans",Font.BOLD,15));
		
		fgt_label=new JLabel("Forget Password?");
		fgt_label.setFont(new Font("Cosmic Sans",Font.BOLD,13));
		fgt_label.setForeground(Color.white);
		
		label=new JLabel("");
		label.setFont(new Font("Cosmic Sans",Font.BOLD,13));
		label.setForeground(Color.white);
		
		panel=new JPanel();
		panel.setBackground(new Color(150,0,150));
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.HORIZONTAL;
		
		
		gbc.gridx=0;gbc.gridy=0;
		panel.add(l_name,gbc);
		
		gbc.gridx=0;gbc.gridy=1;
		panel.add(tf_name,gbc);
		
		gbc.gridx=0;gbc.gridy=2;
		panel.add(l_password,gbc);
		
		gbc.gridx=0;gbc.gridy=3;
		panel.add(pf_password,gbc);
		
		gbc.gridx=1;gbc.gridy=4;
		panel.add(login_btn,gbc);
		

		gbc.insets=new Insets(10,10,10,10);
		gbc.gridx=2;gbc.gridy=4;
		panel.add(cancel_btn,gbc);
	
		gbc.gridx=1;gbc.gridy=5;
		panel.add(fgt_label,gbc);
		
		gbc.gridx=1;gbc.gridy=6;
		panel.add(label,gbc);
		
		fpanel=new JPanel();
		fpanel.add(panel1);
		fpanel.add(panel);
		fpanel.setBackground(new Color(150,0,150));
		this.setTitle("Log In");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(fpanel);
		this.setSize(500, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	public void addActions() {
		login_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user=tf_name.getText().toLowerCase();
				String password = pf_password.getText().toLowerCase();
				if(user.equals("admin")&&password.equals("abc123"))
				{
					dispose();
					setDate();
					new HomePage();
				}
				else
				{
					label.setText("Sorry! Try Again");
					label.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							dispose();
							new LoginFrame();
						}
					});
				}
			}
		});
		cancel_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e1) {
				// TODO Auto-generated method stub
				if(e1.getSource()==cancel_btn) {
					dispose();
				}
			}
			
		});
		fgt_label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				fgt_label.setText("Try to contact with owner");
			}
		});
	}
	
	public void setDate() {
		try {
			Date d=new java.sql.Date(System.currentTimeMillis());
			Connection con=DriverManager.getConnection("jdbc:ucanaccess://ParkingRecords.accdb");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("INSERT INTO Dates(Dates) VALUES '"+d+"'");
		}catch(SQLException e) {
			
		}
	}
}

