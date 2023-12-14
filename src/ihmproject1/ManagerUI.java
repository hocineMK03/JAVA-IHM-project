package ihmproject1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ManagerUI extends JFrame implements ActionListener {
	JFrame frame;
	JLabel lid,header,error;
	JTextField txtidinput;
	
	JButton btngoback,btndelete,btnupdate;
	JPanel North,South,Center,SouthPanel;
	DBoperations dbops;
	JTable table;
	List<MemoireData> memoires;
	Authuser  u;
	
	
	public ManagerUI(DBoperations dbops) {
        this.dbops = dbops;
        frame = new JFrame("manager");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        u = dbops.getAuthUser();
        North = new JPanel(new BorderLayout());
        Center = new JPanel();
        South = new JPanel();
        SouthPanel = new JPanel(); // New panel for "ID," text field, and buttons

        table = new JTable();
        
        btngoback = new JButton("Go Back");
        btndelete = new JButton("Delete");
        txtidinput = new JTextField();
        lid = new JLabel("ID");
        error=new JLabel("error");
        error.setVisible(false);
        if (u.getType() == 2) {
            btnupdate = new JButton("Update");
            getMemoires(u.getName());
            btnupdate.addActionListener(this);
            SouthPanel.add(lid);
            SouthPanel.add(txtidinput);
            SouthPanel.add(btndelete);
            SouthPanel.add(btnupdate);
        } else if (u.getType() == 3) {
            getMemoires();
            SouthPanel.add(lid);
            SouthPanel.add(txtidinput);
            SouthPanel.add(btndelete);
            
        }
        SouthPanel.add(error);
        North.add(table.getTableHeader(), BorderLayout.NORTH);
        North.add(table, BorderLayout.CENTER);
       
        Center.add(btngoback);

        frame.setLayout(new BorderLayout());
        frame.add(North, BorderLayout.NORTH);
        frame.add(Center, BorderLayout.CENTER);

        // Add SouthPanel to South
        South.add(SouthPanel);

        frame.add(South, BorderLayout.SOUTH);

        btngoback.addActionListener(this);
        btndelete.addActionListener(this);

        txtidinput.setPreferredSize(new Dimension(150, 25));
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
	private void getMemoires(String name) {
		DBoperations dbops=new DBoperations();
		 memoires=dbops.filterMemoire(name,"","","");
		 DefaultTableModel tableModel = createTableModel(memoires);
	      table.setModel(tableModel);

	}
	
	
	private DefaultTableModel createTableModel(List<MemoireData> memoires) {
        String[] columnNames = {"ID", "Title", "Description", "Etudiant", "Encadreur", "Year", "Level", "Resume"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);

        for (MemoireData memoire : memoires) {
            Object[] rowData = {
                    memoire.getId(),
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

	
	public int checkinput() {
		try {
			String inputtxt=txtidinput.getText();
			int memoireid = Integer.parseInt(inputtxt);
			return memoireid;
		}
		
		catch (NumberFormatException e1) {
		    // Handle the case where the input is not a valid integer
		    System.out.println("Invalid input. Please enter a valid integer.");
		    error.setVisible(true);
		    error.setText("Please enter a valid integer");
		    return -1;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String inputtxt=txtidinput.getText();
		int memoireid;
		if(e.getSource()==btngoback) {
			
			
			Homeui homeui=new Homeui(dbops);
			homeui.setVisible(true);
			frame.setVisible(false);
		}
		
		if(e.getSource()==btndelete) {
			
			memoireid=checkinput();
			if(u.getType()==2 && memoireid!=-1) {
				if(dbops.checkAccessiblity(memoireid)) {
					if(dbops.deleteMemoire(memoireid)) {
						DBcon.closeConnection();
						ManagerUI managerui=new ManagerUI(dbops);
						managerui.setVisible(true);
						frame.setVisible(false);
					}
				}
				else {
					DBcon.closeConnection();
					error.setVisible(true);
				    error.setText("you don't have the rights to do so");
				}
				//check if he can
			}
			else if(u.getType()==3 && memoireid!=-1) {
				if(dbops.deleteMemoire(memoireid)) {
					DBcon.closeConnection();
					ManagerUI managerui=new ManagerUI(dbops);
					managerui.setVisible(true);
					frame.setVisible(false);
				}
				else {
					DBcon.closeConnection();
					error.setVisible(true);
				    error.setText("couldnt find the memoire");
				}
			}
		}
		if(e.getSource()==btnupdate) {
			memoireid=checkinput();
			Authuser u=dbops.getAuthUser();
			if(memoireid!=-1) {
				if(dbops.checkAccessiblity(memoireid)) {
					MemoireData memoiredata;
					memoiredata=dbops.filterMemoire(memoireid);
					MemoireData singlememoire=new MemoireData(memoireid,memoiredata.getEtudiant(),memoiredata.getEncadreur(),memoiredata.getYear(),memoiredata.getTitle(),memoiredata.getDesc(),memoiredata.getLevel(),memoiredata.getResume());
					MemoireCreateUI memoirecreateui=new MemoireCreateUI(dbops,singlememoire);
				}
				else {
					DBcon.closeConnection();
					error.setVisible(true);
				    error.setText("you don't have the rights to do so");
				}
			}
			
		}
	}

}
