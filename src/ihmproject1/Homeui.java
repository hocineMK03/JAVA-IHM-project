package ihmproject1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Homeui extends JFrame implements ActionListener{
	JFrame frame;
	JLabel lencadreur,letudiant,llevel,ltitle,header;
	JTextField txtencadreur,txttitle,txtlevel,txtetudiant;
	JPasswordField txtpass;
	JButton btncreate,btnlogout,btnfilter,btndelete,btnmanage,btndetail;
	JPanel Paramaiters,North,South,North_south,North_north,leftpanel,rightpanel;
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
		frame = new JFrame("main");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,600);
		frame.setResizable(false);
		leftpanel = new JPanel();
		leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));
		leftpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
		rightpanel = new JPanel(new FlowLayout());
		
		North = new JPanel();
		North.setLayout(new BoxLayout(North, BoxLayout.Y_AXIS));
		North_north = new JPanel(new FlowLayout());
		North_south = new JPanel(new GridLayout(0, 2, 10, 10));
		leftpanel.setBackground(Color.WHITE);
		rightpanel.setBackground(Color.WHITE);
		North_north.setBackground(Color.WHITE);
		North_south.setBackground(Color.WHITE);
		North.setBackground(Color.WHITE);
		rightpanel.setBackground(Color.WHITE);
		btnfilter = new JButton("Filter");
		btncreate = new JButton("Create");
		btnlogout = new JButton("Logout");
		btndelete = new JButton("Delete Account");
		btnmanage = new JButton("Manage");
		btndetail = new JButton("Details");

		txtencadreur = new JTextField();
		txtetudiant = new JTextField();
		txttitle = new JTextField();
		txtlevel = new JTextField();

		lencadreur = new JLabel("Encadreur");
		letudiant = new JLabel("Etudiant");
		ltitle = new JLabel("Title");
		llevel = new JLabel("Level");
		
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
	    btndetail.setForeground(Color.WHITE);
	    btndetail.setBackground(Color.BLACK);
		North_south.add(lencadreur);
		North_south.add(txtencadreur);

		North_south.add(letudiant);
		North_south.add(txtetudiant);

		North_south.add(ltitle);
		North_south.add(txttitle);

		North_south.add(llevel);
		North_south.add(txtlevel);

		North.add(North_north);
		North.add(North_south);
		North_south.add(btnfilter);
		North_north.add(btnlogout);
		btnfilter.addActionListener(this);
		btnlogout.addActionListener(this);
		if (u.getType() == 2) {
		    North_south.add(btncreate);
		    btncreate.addActionListener(this);

		    North_north.add(btnmanage);
		    btnmanage.addActionListener(this);

		    North_north.add(btndelete);
		    btndelete.addActionListener(this);
		}

		if (u.getType() == 3) {
		    North_north.add(btnmanage);
		    btnmanage.addActionListener(this);
		}
		table=new JTable();
		table.setOpaque(true);
		table.getTableHeader().setOpaque(true);
		table.setBackground(Color.WHITE);

		rightpanel.add(table.getTableHeader(), BorderLayout.NORTH);
		rightpanel.add(table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 2));

        rightpanel.add(scrollPane, BorderLayout.CENTER);
      
        getMemoires();
		North_south.add(btndetail);
		btndetail.addActionListener(this);

		leftpanel.add(North);
		frame.add(leftpanel, BorderLayout.WEST);
		frame.add(rightpanel, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
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
        String[] columnNames = { "Tableindex","Title", "Description", "Etudiant", "Encadreur", "Year", "Level", "Resume"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        int i=0;
        for (MemoireData memoire : memoires) {
            Object[] rowData = {
                    i,
                    memoire.getTitle(),
                    memoire.getDesc(),
                    memoire.getEtudiantname(),
                    memoire.getEncadreurname(),
                    memoire.getYear(),
                    memoire.getLevel(),
                    memoire.getResume()
            };
            i++;
            model.addRow(rowData);
        }

        return model;
    }
	private void showDetails() {
	    int selectedRow = table.getSelectedRow();

	    if (selectedRow != -1) {
	        // Get data from the selected row
	    	int tablenum=(int)table.getValueAt(selectedRow, 0);
	        String title = (String) table.getValueAt(selectedRow, 1);
	        String desc = (String) table.getValueAt(selectedRow, 2);
	        String etudiant = (String) table.getValueAt(selectedRow, 3);
	        String encadreur = (String) table.getValueAt(selectedRow, 4);
	        int year = (int) table.getValueAt(selectedRow, 5);
	        String level = (String) table.getValueAt(selectedRow, 6);
	        String resume = (String) table.getValueAt(selectedRow, 7);
	        MemoireData selectedMemoire = memoires.get(tablenum);
	        int etudiantmat=selectedMemoire.getEtudiant();
	        int encadreurmat=selectedMemoire.getEncadreur();
	        // Display the details in a new frame
	        showdetailframe(title, desc, etudiant, encadreur, year, level, resume,etudiantmat,encadreurmat);
	    } else {
	        JOptionPane.showMessageDialog(frame, "Please select a row for details.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
	    }
	}


public void showdetailframe(String title, String desc, String etudiant, String encadreur, int year, String level, String resume,int etudiantmat,int encadreurmat) {
	JFrame detailsFrame = new JFrame("Details");
    detailsFrame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel detailsPanel = new JPanel(new GridLayout(9, 2));
    detailsFrame.setResizable(false);
    detailsPanel.add(new JLabel("Title:"));
    detailsPanel.add(new JLabel(title));

    detailsPanel.add(new JLabel("Description:"));
    JTextArea descTextArea = new JTextArea(desc);
    descTextArea.setLineWrap(true);
    descTextArea.setWrapStyleWord(true);
    descTextArea.setEditable(false);
    JScrollPane descScrollPane = new JScrollPane(descTextArea);
    detailsPanel.add(descScrollPane);

    detailsPanel.add(new JLabel("Etudiant:"));
    detailsPanel.add(new JLabel(etudiant));

    detailsPanel.add(new JLabel("Etudiant Matricule:"));
    detailsPanel.add(new JLabel(String.valueOf(etudiantmat)));
    
    detailsPanel.add(new JLabel("Encadreur:"));
    detailsPanel.add(new JLabel(encadreur));

    detailsPanel.add(new JLabel("Encadreur Matricule:"));
    detailsPanel.add(new JLabel(String.valueOf(encadreurmat)));
    
    detailsPanel.add(new JLabel("Year:"));
    detailsPanel.add(new JLabel(String.valueOf(year)));


    detailsPanel.add(new JLabel("Level:"));
    detailsPanel.add(new JLabel(level));

    detailsPanel.add(new JLabel("Resume:"));
    JTextArea resumeTextArea = new JTextArea(resume);
    resumeTextArea.setLineWrap(true);
    resumeTextArea.setWrapStyleWord(true);
    resumeTextArea.setEditable(false);
    JScrollPane resumeScrollPane = new JScrollPane(resumeTextArea);
    detailsPanel.add(resumeScrollPane);

    detailsFrame.add(detailsPanel);
    detailsFrame.setVisible(true);
    detailsFrame.setLocationRelativeTo(null);
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
		if(e.getSource()==btndetail) {
			showDetails();
			
		}
	}

	
}
