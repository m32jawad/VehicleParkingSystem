package project;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class WelcomeFrame{
	JLabel backgd,label1,label,pre1_lbl,pre2_lbl,aqeel_lbl,jawad_lbl,tayyab_lbl,mam_lbl,uw_label;
	Timer timer;
	JFrame frame;
	WelcomeFrame(){
		setFrame();
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"LookAndFeel not Set!!!");
		}
	}
	public void setFrame() {

		ImageIcon image=new ImageIcon("images//background.png");
		backgd=new JLabel("",image,JLabel.CENTER);
		backgd.setBounds(0, 0, 600, 550);
		
		label1=new JLabel();
		label1.setText("WELCOME TO");
		label=new JLabel();
		label.setText("VEHICLE PARKING SYSTEM");
		label1.setBounds(170,20,600,40);
		label1.setFont(new Font("Algerian",Font.BOLD,38));
		label1.setForeground(Color.YELLOW);
		label.setBounds(45, 60, 600, 40);
		label.setFont(new Font("Algerian",Font.BOLD,38));
		label.setForeground(Color.YELLOW);
		
		pre1_lbl=new JLabel();
		pre1_lbl.setText("PREPARED BY");
		pre1_lbl.setFont(new Font("Arial Rounded MT BOLD",Font.BOLD,38));
		pre1_lbl.setBounds(160, 115, 600, 40);
		pre1_lbl.setForeground(new Color(20,150,20));
		
		aqeel_lbl=new JLabel();
		aqeel_lbl.setText("AQEEL ZAFFAR");
		aqeel_lbl.setFont(new Font("Cosmic Sans",Font.BOLD,25));
		aqeel_lbl.setBounds(190, 160, 600, 30);
		aqeel_lbl.setForeground(Color.blue);
		
		jawad_lbl=new JLabel();
		jawad_lbl.setText("MUHAMMAD JAWAD");
		jawad_lbl.setFont(new Font("Cosmic Sans",Font.BOLD,25));
		jawad_lbl.setBounds(190, 190, 600, 30);
		jawad_lbl.setForeground(Color.blue);
		
		tayyab_lbl=new JLabel();
		tayyab_lbl.setText("TAYYAB TOQEER");
		tayyab_lbl.setFont(new Font("Cosmic Sans",Font.BOLD,25));
		tayyab_lbl.setBounds(190, 220, 600, 30);
		tayyab_lbl.setForeground(Color.blue);


		pre2_lbl=new JLabel();
		pre2_lbl.setText("PREPARED FOR");
		pre2_lbl.setFont(new Font("Arial Rounded MT BOLD",Font.BOLD,38));
		pre2_lbl.setBounds(160, 260, 600, 40);
		pre2_lbl.setForeground(new Color(20,150,20));
		
		mam_lbl=new JLabel();
		mam_lbl.setText("MAM FARRHA ASLAM");
		mam_lbl.setFont(new Font("Cosmic Sans",Font.BOLD,25));
		mam_lbl.setBounds(190, 300, 600, 30);
		mam_lbl.setForeground(Color.blue);
		
		uw_label=new JLabel();
		uw_label.setText("UNIVERSITY OF WAH");
		uw_label.setBounds(70,340,600,40);
		uw_label.setFont(new Font("Algerian",Font.BOLD,44));
		uw_label.setForeground(Color.YELLOW);
		
		frame=new JFrame("WELCOME");
		frame.setResizable(false);
		frame.add(label1);
		frame.add(label);
		frame.add(pre1_lbl);
		frame.add(aqeel_lbl);
		frame.add(jawad_lbl);
		frame.add(tayyab_lbl);
		frame.add(pre2_lbl);
		frame.add(mam_lbl);
		frame.add(uw_label);
		frame.add(backgd);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 550);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
		//code for moving to next frame
		int delay=5000;
		timer=new Timer(delay,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				new LoginFrame();
				timer.stop();
			}
		});timer.start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WelcomeFrame();
		
	}

}
