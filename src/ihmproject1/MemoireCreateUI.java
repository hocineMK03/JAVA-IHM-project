package ihmproject1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MemoireCreateUI extends JFrame implements ActionListener {
	DBoperations dbops;
	JFrame frame,showframe;
	JLabel lencadreur,letudiant,llevel,ltitle,header,error,ldesc,lresume,lyear;
	JTextField txtencadreur,txttitle,txtlevel,txtetudiant,txtdesc,txtyear;
	JTextArea txtresume;
	JPasswordField txtpass;
	JButton btnsubmit,btngoback;
	JPanel North,Center,South;
	JRadioButton Prof,Etudiant;
	Boolean whichone;
	ButtonGroup group=new ButtonGroup();
	MemoireData singlememoire;
	//need two constructors one for new create and second for update
	public MemoireCreateUI(DBoperations dbops) {
		whichone=true;
		this.dbops=dbops;
		framebuilder();
		etudiantsframe();
	}
	public MemoireCreateUI(DBoperations dbops, MemoireData singlememoire) {
		whichone=false;
		this.singlememoire=singlememoire;
		this.dbops=dbops;
		framebuilder();
		
		Object encadreurObject = singlememoire.getEncadreur();
		String mattostr = encadreurObject.toString();
		Object etudiantObject = singlememoire.getEtudiant();
		String mattostr2 = etudiantObject.toString();
		Object yearobject = singlememoire.getYear();
		String mattostr3 = yearobject.toString();
		txtencadreur.setText(mattostr);
		txttitle.setText(singlememoire.getTitle());
		txtlevel.setText(singlememoire.getLevel());
		txtetudiant.setText(mattostr2);
		txtdesc.setText(singlememoire.getDesc());
		txtresume.setText(singlememoire.getResume());
		txtyear.setText(mattostr3);
		
		btnsubmit.setText("Update");
	}
	private void framebuilder() {
		
		frame=new JFrame("Create");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(500, 550);
		North=new JPanel();
		Center=new JPanel();
		South=new JPanel();
		Authuser u=dbops.getAuthUser();
		North.setPreferredSize(new Dimension(100,100));
		Center.setPreferredSize(new Dimension(100,150));
		South.setPreferredSize(new Dimension(50,50));
		
		North.setLayout(new BorderLayout());
		header=new JLabel("MemoireCreate Form");
		 header.setFont(new Font("Arial", Font.BOLD, 20));
		North.add(header,BorderLayout.CENTER);
		
		Center.setLayout(new GridLayout(8, 2, 5, 5));
		error=new JLabel("");
		error.setVisible(false);
		txtencadreur=new JTextField();
		txtetudiant=new JTextField();
		txttitle=new JTextField();
		txtlevel=new JTextField();
		txtdesc=new JTextField();
		txtresume=new JTextArea();
		txtyear=new JTextField();
		txtencadreur.setText(u.getName());
		txtencadreur.setEditable(false);
		lencadreur=new JLabel("encadreur mat");
		letudiant=new JLabel("etudiant mat");
		ltitle=new JLabel("title");
		llevel=new JLabel("level");
		ldesc=new JLabel("desc");
		lresume=new JLabel("resume");
		lyear=new JLabel("year");
		btnsubmit = new JButton("Submit");
	    btngoback = new JButton("Go Back");
	    
	    btnsubmit.addActionListener(this);
	    btngoback.addActionListener(this);
		Center.add(lencadreur);
		Center.add(txtencadreur);
		Center.add(letudiant);
		Center.add(txtetudiant);
		Center.add(ltitle);
		Center.add(txttitle);
		Center.add(llevel);
		Center.add(txtlevel);
		Center.add(ldesc);
		Center.add(txtdesc);
		Center.add(lresume);
		Center.add(txtresume);
		Center.add(lyear);
		Center.add(txtyear);
		Center.add(btnsubmit);
		Center.add(btngoback);
		
		
		South.add(error);
		frame.setLayout(new BorderLayout());
		
		frame.add(North,BorderLayout.NORTH);
		frame.add(Center,BorderLayout.CENTER);
		frame.add(South,BorderLayout.SOUTH);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
public void etudiantsframe() {
	 showframe=new JFrame("etudiants infos");
	dbops.DBreconnect();
	List<StudentsInfo> studentsList=dbops.getStudents();
	String[] columnNames = {"Matricule", "Name", "Prenom"};
	DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    // Populate the table model with data from the studentsList
    for (StudentsInfo student : studentsList) {
        Object[] rowData = {student.getMatricule(), student.getName(), student.getPrenom()};
        tableModel.addRow(rowData);
    }

   
    JTable table = new JTable(tableModel);

    
    JScrollPane scrollPane = new JScrollPane(table);

 
    JPanel detailspanel = new JPanel(new BorderLayout());
    detailspanel.add(scrollPane, BorderLayout.CENTER);

   
    showframe.add(detailspanel);

    // Set frame properties
   
    showframe.setSize(600, 400);
    showframe.setLocationRelativeTo(null); // Center the frame on the screen
    showframe.setVisible(true);

}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		String etudiantmatricule=txtetudiant.getText();
	String encadreurmatricule=txtencadreur.getText();
		String title=txttitle.getText();
		String level=txtlevel.getText();
		String desc=txtdesc.getText();
		String resume=txtresume.getText();
		String textyear=txtyear.getText();
		int year=0;
		System.out.println("whichone"+whichone);
		if(e.getSource()==btnsubmit && whichone) {
			dbops.DBreconnect();
			Authuser u=dbops.getAuthUser();
			int etudiantresponce=dbops.checkmatricule(etudiantmatricule);
			int encadreurresponce=u.getId();
			if(encadreurresponce!=-1 && etudiantresponce!=-1) {
				if(encadreurresponce==1 ||etudiantresponce==2 ) {
					error.setText("matricule isn't the same type as the required type");
					error.setVisible(true);
				}
				else {
					try {
			            year = Integer.parseInt(textyear);
			            if(whichone) {
			            	if(dbops.createMemoire(title, desc, year, resume, encadreurmatricule, etudiantmatricule, level)) {
			            		DBcon.closeConnection();
				            	System.out.println("here");
				            	Homeui homeui=new Homeui(dbops);
				            	homeui.setVisible(true);
				            	frame.setVisible(false);
				            	showframe.setVisible(false);
								showframe.dispose();
				            }
				            else {
				            	System.out.println("soemthing happenned");
				            	error.setText("something happenned");
								error.setVisible(true);
				            }
			            }
			            else {
			            	//update
			            	
			            }
			            
			        } catch (NumberFormatException e1) {
			        	System.out.println("int error");
			            error.setText(e1.getMessage());
			        }
				}
			}
			else if(encadreurresponce==-1 && etudiantresponce!=-1){
				error.setText("encadreur matricule invalid");
				error.setVisible(true);
			}
			else if(encadreurresponce!=-1 && etudiantresponce==-1) {
				 error.setText("etudiant matricule invalid");
				error.setVisible(true);
			}
			else {
				error.setText("both matricules invalid");
				error.setVisible(true);
			}
		}
		else if(e.getSource()==btnsubmit && !whichone) {
			try {
				year = Integer.parseInt(textyear);
				if(textyear.isEmpty() || title.isEmpty() || level.isEmpty() || desc.isEmpty() || resume.isEmpty()) {
					error.setText("can't do empty");
					error.setVisible(true);
				}
				else {
					if(dbops.updateMemoire(singlememoire.getId(),year, title, level,desc,resume)) {
						//updated
						frame.dispose();
						DBcon.closeConnection();
						ManagerUI managerui=new ManagerUI(dbops);
						managerui.setVisible(true);
						frame.setVisible(false);
						showframe.setVisible(false);
						showframe.dispose();
					}
					else {
						error.setText("can't update");
						error.setVisible(true);
					}
				}
			}
			catch (NumberFormatException e1) {
	        	System.out.println("int error");
	            error.setText(e1.getMessage());
	            error.setVisible(true);
	        }
		
		}
		
		if(e.getSource()==btngoback) {
			
			DBcon.closeConnection();
			Homeui homeui=new Homeui(dbops);
			homeui.setVisible(true);
			frame.setVisible(false);
			showframe.setVisible(false);
			showframe.dispose();
			frame.dispose();
		}
	}
}
