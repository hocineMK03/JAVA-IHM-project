package ihmproject1;

public class StudentsInfo {

	private String name;
    private String prenom;
    private String matricule;

    public StudentsInfo(String name, String prenom, String matricule) {
        this.name = name;
        this.prenom = prenom;
        this.matricule = matricule;
    }

    public String getName() {
        return name;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMatricule() {
        return matricule;
    }
}
