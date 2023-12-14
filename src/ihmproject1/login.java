package ihmproject1;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class login extends JFrame implements ActionListener {
	JFrame frame;
	JLabel matricule,password,header,name,error;
	JTextField txtmat,txtname;
	JPasswordField txtpass;
	JButton btnlogin,btnsignup;
	JPanel North,Center,South;
	public login() {
		
		framebuilder();
	}
	private void framebuilder() {
		frame=new JFrame("login");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setResizable(false);
		North=new JPanel();
		Center=new JPanel();
		South=new JPanel();
		
		North.setPreferredSize(new Dimension(50,50));
		Center.setPreferredSize(new Dimension(100,100));
		South.setPreferredSize(new Dimension(50,50));
		North.setBackground(Color.WHITE);
		Center.setBackground(Color.WHITE);
		South.setBackground(Color.WHITE);

		North.setLayout(new FlowLayout(FlowLayout.CENTER));
		header=new JLabel("Login Form");
		header.setFont(new Font("Arial", Font.BOLD, 20));
		North.add(header,BorderLayout.CENTER);
		
		 Center.setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();
		 gbc.insets = new Insets(5, 5, 5, 5);
		matricule=new JLabel("matricule");
		password=new JLabel("password");
		
		txtmat=new JTextField();
		txtpass=new JPasswordField();
		btnlogin=new JButton("Login");
		btnsignup=new JButton("Signup");
		btnlogin.setBackground(Color.BLACK); 
        btnsignup.setBackground(Color.BLACK);
        btnlogin.setForeground(Color.WHITE);
        btnsignup.setForeground(Color.WHITE);
        Dimension buttonSize = new Dimension(100, 30);
		btnlogin.setPreferredSize(buttonSize);
		btnsignup.setPreferredSize(buttonSize);
		btnlogin.addActionListener(this);
		btnsignup.addActionListener(this);
		 gbc.gridx = 0;
		    gbc.gridy = 0;
		    txtmat.setColumns(15);
		    txtpass.setColumns(15);

		    Center.add(matricule, gbc);

		    gbc.gridx = 1;
		    gbc.gridy = 0;
		    Center.add(txtmat, gbc);

		    gbc.gridx = 0;
		    gbc.gridy = 1;
		    Center.add(password, gbc);

		    gbc.gridx = 1;
		    gbc.gridy = 1;
		    Center.add(txtpass, gbc);

		    JPanel buttonPanel = new JPanel(new FlowLayout());
		    buttonPanel.add(btnlogin);
		    buttonPanel.add(btnsignup);

		    gbc.gridx = 0;
		    gbc.gridy = 2;
		    gbc.gridwidth = 2; // Make the button panel span two columns
		    Center.add(buttonPanel, gbc);

		error=new JLabel("password or matricule invalid");
		error.setVisible(false);
		South.setLayout(new FlowLayout());
		South.add(error);
		frame.setLayout(new BorderLayout());
		
		frame.add(North,BorderLayout.NORTH);
		frame.add(Center,BorderLayout.CENTER);
		frame.add(South,BorderLayout.SOUTH);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Boolean can=true;
		DBoperations dbops=new DBoperations();
		String matricule=txtmat.getText();

		String password=txtpass.getText();
		if(matricule.equals("") || password.equals("")) {
			can=false;
			error.setVisible(true);
			System.out.println("inputs empty");
		}
		
			if(e.getSource()==btnsignup) {
				
				frame.dispose();
				 Signup signup = new Signup();  // Instantiate the Signup class
		            signup.setVisible(true);
		            frame.setVisible(false);
				}
			else if(e.getSource()==btnlogin && can) {
			if(dbops.checkAuth(matricule, password)) {
				frame.dispose();
				Homeui homeui=new Homeui(dbops);
				 homeui.setVisible(true);
				 DBcon.closeConnection();
		         frame.setVisible(false);
			}
			else {
				error.setVisible(true);
				System.out.println("password or matricule invalid");
			}
			}
		
		
	}

	
	
}
