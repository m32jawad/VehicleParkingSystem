package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
public class HelpFrame extends JFrame{
	
	private ImageIcon img;
	private JLabel img_lbl,help_lbl,lbl1;
	private JPanel panel,Spanel;
	private JButton back_btn;
	private JScrollPane scrollpane;
	//constructor
	public HelpFrame() {
		setFrame();
		addActions();
	}
	public void setFrame() {
		help_lbl=new JLabel("HELP");
		help_lbl.setFont(new Font("Algerian",Font.BOLD,40));
		help_lbl.setForeground(Color.YELLOW);
		panel=new JPanel();
		panel.setBackground(new Color(0,0,0));
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(help_lbl);
		
		//vehicle entry
		lbl1=new JLabel("1.Vehicle Entry");
		lbl1.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lbl1.setForeground(Color.WHITE);
		panel.add(lbl1);
		
		img=new ImageIcon("images//img4.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);
		
		//Exit help
		lbl1=new JLabel("2.Exit");
		lbl1.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lbl1.setForeground(Color.WHITE);
		panel.add(lbl1);
		
		img=new ImageIcon("images//img5.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);

		// Edit
		lbl1=new JLabel("3.Edit");
		lbl1.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lbl1.setForeground(Color.WHITE);
		panel.add(lbl1);
		
		img=new ImageIcon("images//img1.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);
		
		img=new ImageIcon("images//img2.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);
		
		// Log out
		lbl1=new JLabel("4.Log Out");
		lbl1.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lbl1.setForeground(Color.WHITE);
		panel.add(lbl1);
		
		img=new ImageIcon("images//img3.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);
		
		// total spaces
		lbl1=new JLabel("5.To View Total Spaces");
		lbl1.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lbl1.setForeground(Color.WHITE);
		panel.add(lbl1);
		
		img=new ImageIcon("images//img6.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);
		

		// available Slots
		lbl1=new JLabel("6.To View Available Slots");
		lbl1.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lbl1.setForeground(Color.WHITE);
		panel.add(lbl1);
		
		img=new ImageIcon("images//img7.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);
		
		// To View and Search Parked Vehicles
		lbl1=new JLabel("7.To View and Search Parked Vehicles");
		lbl1.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lbl1.setForeground(Color.WHITE);
		panel.add(lbl1);
		
		img=new ImageIcon("images//img8.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);
		
		// To View Previous Records
		lbl1=new JLabel("8.To View Previous Records");
		lbl1.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lbl1.setForeground(Color.WHITE);
		panel.add(lbl1);
		
		img=new ImageIcon("images//img9.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);
		
		//Previous Records
		lbl1=new JLabel("9.Previous Records");
		lbl1.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lbl1.setForeground(Color.WHITE);
		panel.add(lbl1);
		
		img=new ImageIcon("images//img10.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);
		
		//Search From Records
		lbl1=new JLabel("10.Search From Records");
		lbl1.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lbl1.setForeground(Color.WHITE);
		panel.add(lbl1);
		
		img=new ImageIcon("images//img11.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);
		
		//Delete From Records
		lbl1=new JLabel("11.Delete From Records");
		lbl1.setFont(new Font("Cosmic Sans",Font.BOLD,20));
		lbl1.setForeground(Color.WHITE);
		panel.add(lbl1);
		
		img=new ImageIcon("images//img12.png");
		img_lbl=new JLabel();
		img_lbl.setIcon(img);
		panel.add(img_lbl);
		

		back_btn=new JButton("Back");
		back_btn.setBackground(Color.LIGHT_GRAY);
		back_btn.setForeground(Color.blue);
		back_btn.setFont(new Font("Cosmic Sans",Font.BOLD,25));
		panel.add(back_btn);
		
		Spanel=new JPanel();
		setTitle("Help");
		Spanel.add(panel);
		Spanel.setBackground(new Color(0,0,0));
		getContentPane().add(new JScrollPane(Spanel));
		setSize(700,700);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void addActions() {
		back_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==back_btn) {
					dispose();
					new HomePage();
				}
			}
		});
	}
}
