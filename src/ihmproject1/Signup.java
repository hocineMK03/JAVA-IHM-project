package ihmproject1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Signup extends JFrame implements ActionListener{

	
	
	JFrame frame;
	JLabel matricule,password,header,name,prenom;
	JTextField txtmat,txtname,txtprenom;
	JPasswordField txtpass;
	JButton btnlogin,btnsignup;
	JPanel North,Center,South;
	JRadioButton Prof,Etudiant;
	ButtonGroup group=new ButtonGroup();
	public Signup() {
		framebuilder();
		
	}
	private void framebuilder() {
		frame=new JFrame("signup");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(450, 450);
		North=new JPanel();
		Center=new JPanel();
		South=new JPanel();
		North.setBackground(Color.WHITE);
		Center.setBackground(Color.WHITE);
		South.setBackground(Color.WHITE);

		North.setPreferredSize(new Dimension(100,100));
		Center.setPreferredSize(new Dimension(10,50));
		South.setPreferredSize(new Dimension(50,50));
		
		North.setLayout(new FlowLayout(FlowLayout.CENTER));
		header=new JLabel("SingnUp Form");
		 header.setFont(new Font("Arial", Font.BOLD, 20));
		North.add(header,BorderLayout.CENTER);
		
		Center.setLayout(new GridLayout(6, 2, 5, 5));
		matricule=new JLabel("matricule");
		password=new JLabel("password");
		txtname=new JTextField();
		name=new JLabel("name");
		txtprenom=new JTextField();
		prenom=new JLabel("prenom");
		txtmat=new JTextField();
		txtpass=new JPasswordField();
		btnlogin=new JButton("Login");
		btnsignup=new JButton("Signup");
		Prof=new JRadioButton("Prof");
		Etudiant=new JRadioButton("Etudiant");
		Etudiant.setSelected(true);
		group.add(Prof);
		group.add(Etudiant);
		btnlogin.addActionListener(this);
		btnsignup.addActionListener(this);
		btnlogin.setBackground(Color.BLACK); 
        btnsignup.setBackground(Color.BLACK);
        btnlogin.setForeground(Color.WHITE);
        btnsignup.setForeground(Color.WHITE);
		Center.add(matricule);
		Center.add(txtmat);
		Dimension buttonSize = new Dimension(100, 30);
		btnlogin.setPreferredSize(buttonSize);
		btnsignup.setPreferredSize(buttonSize);
		Center.add(name);
		Center.add(txtname);
		Center.add(prenom);
		Center.add(txtprenom);
		Center.add(Prof);
		Center.add(Etudiant);
		Center.add(password);
		Center.add(txtpass);
		Center.add(btnsignup);
		Center.add(btnlogin);
		
		frame.setLayout(new BorderLayout());
		
		frame.add(North,BorderLayout.NORTH);
		frame.add(Center,BorderLayout.CENTER);
		frame.add(South,BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		DBoperations dbops=new DBoperations();
		String matricule=txtmat.getText();
		String name=txtname.getText();
		String prenom=txtprenom.getText();
		String password=txtpass.getText();
		Boolean can=true;
		if(matricule.equals("") || password.equals("") || name.equals("") || prenom.equals("")) {
			can=false;
		}
		int type=0;
		if(e.getSource()==btnsignup && can) {
			if (Prof.isSelected()) {
				type=2;
			}
			else {
				type=1;
			}
			if(dbops.createAccount(matricule, name,prenom, password,type)) {
				DBcon.closeConnection();
				frame.dispose();
				Homeui homeui=new Homeui(dbops);
				 homeui.setVisible(true);
		         frame.setVisible(false);
			}
			else {
				
			}
			
			}
		else if(e.getSource()==btnlogin) {
			
			frame.dispose();
            login log=new login();
            log.setVisible(true);
            frame.setVisible(false);
			}
		
	}

}
