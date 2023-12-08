package ihmproject1;

public class Authuser {
	private static Authuser instance;
	 private String name;
	    private boolean success=false;
	    private int type;
	    private int id;
	    public Authuser(String name, boolean success,int type) {
	        this.name = name;
	        this.success = success;
	        this.type=type;
	        DBoperations dbops=new DBoperations();
	        id=dbops.getId(name, type);
	    }

	    public String getName() {
	        return name;
	    }

	    public boolean isSuccess() {
	        return success;
	    }
	    public int getType() {
			return type;
		}
	    public static Authuser getInstance(String name, boolean success, int type) {
	        if (instance == null) {
	            instance = new Authuser(name, success, type);
	        }
	        return instance;
	    }
	    public static void deleteInstance() {
	        instance = null;
	    }

		public int getId() {
			return id;
		}
		
	    
}
