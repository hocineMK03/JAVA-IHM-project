package ihmproject1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Homeui extends JFrame implements ActionListener{
	JFrame frame;
	JLabel lencadreur,letudiant,llevel,ltitle,header;
	JTextField txtencadreur,txttitle,txtlevel,txtetudiant;
	JPasswordField txtpass;
	JButton btncreate,btnlogout,btnfilter,btndelete,btnmanage;
	JPanel Paramaiters,North,South,North_south,North_north;
	DBoperations dbops;
	JTable table;
	List<MemoireData> memoires;
	Authuser  u;
	
    
	public Homeui(DBoperations dbops) {
		this.dbops=dbops;
		  u = null;
		memoires=null;
		u=dbops.getAuthUser();
		System.out.println("u"+u.getName());
		//get the data
		frame=new JFrame("main");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		Paramaiters=new JPanel(new GridLayout(1,2,10,10));
		Paramaiters.setPreferredSize(new Dimension(100,50));
		North=new JPanel(new BorderLayout());
		North_north=new JPanel(new BorderLayout());
		North_south=new JPanel(new GridLayout(5,2,10,10));
		South=new JPanel();
		
		North.setBackground(Color.WHITE);
		North_north.setBackground(Color.WHITE);
		South.setBackground(Color.WHITE);
		North.setBackground(Color.WHITE);
		North_south.setBackground(Color.WHITE);
		Paramaiters.setBackground(Color.WHITE);
		
		South.setLayout(new BorderLayout());
		btnfilter=new JButton("filter");
		btncreate=new JButton("Create");
		btnlogout=new JButton("Logout");
		btndelete=new JButton("delete account");
		btnmanage=new JButton("manage");
		btnlogout.setBounds(10, 10, 70, 35);
		btndelete.setBounds(110, 10, 70, 35);
		header=new JLabel("memoire");
		//North.add(header,BorderLayout.CENTER);
		txtencadreur=new JTextField();
		txtetudiant=new JTextField();
		txttitle=new JTextField();
		txtlevel=new JTextField();
		lencadreur=new JLabel("encadreur");
		letudiant=new JLabel("etudiant");
		ltitle=new JLabel("title");
		llevel=new JLabel("level");
		North_north.add(header,BorderLayout.NORTH);
		
		North_south.add(lencadreur);
		North_south.add(txtencadreur);
		North_south.add(letudiant);
		North_south.add(txtetudiant);
		North_south.add(ltitle);
		North_south.add(txttitle);
		North_south.add(llevel);
		North_south.add(txtlevel);
		
		North_south.add(btnfilter);
		
		if(u.getType()==2) {
			North_south.add(btncreate);
			btncreate.addActionListener(this);
			Paramaiters.add(btndelete);
			Paramaiters.add(btnmanage);
			btnmanage.addActionListener(this);
			 btnmanage.setPreferredSize(new Dimension(100, 30));
			 btncreate.setPreferredSize(new Dimension(100, 30));
			 btndelete.setPreferredSize(new Dimension(100, 30));
			btndelete.addActionListener(this);
		}
		if(u.getType()==3) {
			Paramaiters.add(btnmanage);
			
			btnmanage.addActionListener(this);
		}
		
		
		North.add(North_north);
		North.add(North_south);
		
		Paramaiters.add(btnlogout);
		
		btncreate.setBackground(Color.BLACK); 
		btnlogout.setBackground(Color.BLACK);
	    btncreate.setForeground(Color.WHITE);
	    btnlogout.setForeground(Color.WHITE);
	    btnfilter.setBackground(Color.BLACK); 
	    btndelete.setBackground(Color.BLACK);
	    btnfilter.setForeground(Color.WHITE);
	    btndelete.setForeground(Color.WHITE);
	    
	    btnmanage.setBackground(Color.BLACK); 
	    
	    btnmanage.setForeground(Color.WHITE);
		btnfilter.addActionListener(this);
		btnlogout.addActionListener(this);
		btndelete.addActionListener(this);
		table=new JTable();
		table.setBackground(Color.WHITE);

		South.add(table.getTableHeader(), BorderLayout.NORTH);
        South.add(table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 2));

        South.add(scrollPane, BorderLayout.CENTER);
        getMemoires();
		frame.setLayout(new BorderLayout());
		frame.add(Paramaiters,BorderLayout.NORTH);
		frame.add(North,BorderLayout.CENTER);
		
		frame.add(South,BorderLayout.SOUTH);
		 frame.pack();
		frame.setVisible(true);
	}
	
	
	private void getMemoires() {
		DBoperations dbops=new DBoperations();
		 memoires=dbops.filterMemoire("","","","");
		 DefaultTableModel tableModel = createTableModel(memoires);
	      table.setModel(tableModel);

	}
	private void getMemoires(String encadreur,String etudiant,String level,String title) {
		DBoperations dbops=new DBoperations();
		 memoires=dbops.filterMemoire(encadreur,title,level,etudiant);
		 DefaultTableModel tableModel = createTableModel(memoires);
	      table.setModel(tableModel);
	}
	private DefaultTableModel createTableModel(List<MemoireData> memoires) {
        String[] columnNames = { "Title", "Description", "Etudiant", "Encadreur", "Year", "Level", "Resume"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (MemoireData memoire : memoires) {
            Object[] rowData = {
                    
                    memoire.getTitle(),
                    memoire.getDesc(),
                    memoire.getEtudiantname(),
                    memoire.getEncadreurname(),
                    memoire.getYear(),
                    memoire.getLevel(),
                    memoire.getResume()
            };
            model.addRow(rowData);
        }

        return model;
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		String encadreur,etudiant,level,title;
		encadreur="";
		etudiant="";
		level="";
		title="";
		if(e.getSource()==btnfilter) {
		encadreur=txtencadreur.getText();
		etudiant=txtetudiant.getText();
		title=txttitle.getText();
		level=txtlevel.getText();
		getMemoires(encadreur,etudiant,level,title);
		}
		else if(e.getSource()==btnlogout) {
			DBcon.closeConnection();
			Authuser.deleteInstance();
			login log=new login();
			log.setVisible(true);
			frame.setVisible(false);
			
			
			
		}
		else if(e.getSource()==btncreate) {

			DBcon.closeConnection();
            MemoireCreateUI memoirecreateui=new MemoireCreateUI(dbops);
            memoirecreateui.setVisible(true);
            frame.setVisible(false);
		}
		else if(e.getSource()==btndelete) {
			
			
			if(dbops.deleteUser()) {
				DBcon.closeConnection();
				Authuser.deleteInstance();
				login log=new login();
				log.setVisible(true);
				frame.setVisible(false);
			}
		}
		else if(e.getSource()==btnmanage) {
			DBcon.closeConnection();
			ManagerUI managerui=new ManagerUI(dbops);
			managerui.setVisible(true);
			frame.setVisible(false);
		}
	}

	
}
