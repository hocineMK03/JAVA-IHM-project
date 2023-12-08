package ihmproject1;

public class MemoireData {
	private int id;
	private String title;
	private String desc;
	private int etudiant;
	private int encadreur;
	private int year;
	private String level;
	private String resume;
	private String etudiantname;
	private String encadreurname;
	
	public MemoireData(int id,int etudiant,int encadreur,int year,String title,String desc,String level,String resume) {
		this.id=id;
		this.etudiant=etudiant;
		this.encadreur=encadreur;
		this.year=year;
		this.title=title;
		this.desc=desc;
		this.level=level;
		this.resume=resume;
		setnames();
		
	}
	
	public String getTitle() {
		return title;
	}

	public String getDesc() {
		return desc;
	}

	
	public String getLevel() {
		return level;
	}

	
	public String getResume() {
		return resume;
	}

	
	
	public int getEncadreur() {
		return encadreur;
	}

	public int getYear() {
		return year;
	}
	
	public int getEtudiant() {
		return etudiant;
	}
	
	public int getId() {
		return id;
	}
	
	private void setnames() {
		DBoperations dbops=new DBoperations();
		etudiantname=dbops.getUserName(etudiant);
		encadreurname=dbops.getUserName(encadreur);
	}

	public String getEtudiantname() {
		return etudiantname;
	}

	public String getEncadreurname() {
		return encadreurname;
	}
	
	
	
	
	
	
}
