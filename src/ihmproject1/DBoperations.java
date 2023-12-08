package ihmproject1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBoperations {
	DBcon dbcon;
	Authuser u;
	public DBoperations() {
		 dbcon=new DBcon();
	}
	public Boolean checkAuth(String matricule,String password ) {
		
		Authuser.deleteInstance();
		String name = "";
		boolean success = false;
		int type=0;
		try {
            
			String query = "SELECT name,type FROM users WHERE matricule = ? AND password = ?";
			PreparedStatement pstmt = DBcon.con.prepareStatement(query);

			    pstmt.setString(1, matricule);
			    pstmt.setString(2, password);

			    // Execute the query and process the results
			    try (ResultSet resultSet = pstmt.executeQuery()) {
			        while (resultSet.next()) {
			            // Process the result set
			             name = resultSet.getString("name");
			             type=resultSet.getInt("type");
			            // Do something with the 'name'
			        }
			        
			    }
			    if(!name.isEmpty() && type!=0) {
			    	success = true;
			    	u= new Authuser(name,success,type);
			    	System.out.println("user");
			    	return true;
			    	 
			    }
			    System.out.println("user1");
			     return false;
			     
            
        } catch (SQLException e) {
            //e.printStackTrace();
            System.err.println("Failed to create statement.");
            return false;
        }
        
	}
	public Boolean  createAccount(String matricule,String name,String prenom,String password,int type ) {
		
		if(!checkAuth(matricule,password)) {
			if(u==null) {
				System.out.println("yesyes");
				
			}
			else {
				if(u.isSuccess()) {
					
					//already exist
					return false;
				}
				
			}
			try {
	            
				 String query = "INSERT INTO users (matricule, name,prenom, password, type) VALUES (?, ?, ?, ?,?)";
	               PreparedStatement pstmt = DBcon.con.prepareStatement(query);
	               
				    pstmt.setString(1, matricule);
				    pstmt.setString(2, name);
				    pstmt.setString(3, prenom);
				    pstmt.setString(4, password);
				    pstmt.setInt(5, type);
				    int rowsAffected = pstmt.executeUpdate();
				    // Execute the query and process the results
				    if (rowsAffected > 0) {
	                    System.out.println("Account created successfully!");
	                    u= new Authuser(name,true,type);
	                    return true;
	                } else {
	                    System.out.println("Failed to create account.");
	                    return false;
	                }
				    
				    
				     
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.err.println("Failed to create statement.");
	            return false;
	        }
		}
		return false;
		
	}
	
	public Boolean createMemoire(String title,String desc,int year,String resume,String encadreur, String etudiant ,String level) {
		int idetudiant,idencadreur;
		try {
			idetudiant=getId(etudiant);
			idencadreur=getId(encadreur);
		}
		catch (Exception e) { // Replace ExceptionType with the actual exception type you are catching
		    // Handle the exception or log it if needed
		    e.printStackTrace(); // or logger.error("An error occurred: " + e.getMessage(), e);
		    return false; // Return false in case of an exception
		}
		try {
		    String query = "INSERT INTO memoires (title, `desc`, idetudiant, idencadreur, year, resume, level) VALUES (?, ?, ?, ?, ?, ?, ?)";
		    PreparedStatement pstmt = DBcon.con.prepareStatement(query);
		    pstmt.setString(1, title);
		    pstmt.setString(2, desc);
		    pstmt.setInt(3, idetudiant);
		    pstmt.setInt(4, idencadreur);
		    pstmt.setInt(5, year);
		    pstmt.setString(6, resume);
		    pstmt.setString(7, level);

		    int rowsAffected = pstmt.executeUpdate();

		    // Execute the query and process the results
		    if (rowsAffected > 0) {
		        System.out.println("Memoire created successfully!");
		        return true;
		    } else {
		        System.out.println("Failed to create Memoire.");
		        return false;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    System.err.println("Failed to create statement.");
		    return false;
		}

		
		
	}
	//not finished yet
	public MemoireData filterMemoire(int id) {
		String query="SELECT * FROM memoires WHERE idmemoires=? ";
		MemoireData mdata=null;
			int id1,etudiant1,encadreur1,year1;
			String title1,desc1,level1,resume1;
		try {
			PreparedStatement pstmt = DBcon.con.prepareStatement(query);
			pstmt.setInt(1, id);
			try (ResultSet resultSet = pstmt.executeQuery()) {
		        while (resultSet.next()) {
		        
		           //put data in a  table or a list
		        	 id1=resultSet.getInt("idmemoires");
		        	 title1=resultSet.getString("title");
		        	 desc1=resultSet.getString("desc");
		        	 etudiant1=resultSet.getInt("idetudiant");
		        	 encadreur1=resultSet.getInt("idencadreur");
		        	 year1=resultSet.getInt("year");
		        	 level1=resultSet.getString("level");
		        	  resume1=resultSet.getString("resume");
		        	
		        	 mdata=new MemoireData(id1,etudiant1,encadreur1,year1,title1,desc1,level1,resume1);
		        	 return mdata; 
		        		        }
		       
		    }
			
			
		}
		catch (SQLException e) {
	           e.printStackTrace();
	           System.err.println("Failed to create statement.");
	           
	       }
		return null;
		
	}

	public List<MemoireData> filterMemoire(String encadreurname,String title,String level,String etudiantname ) {
		
		int id1,etudiant1,encadreur1,year1;
		String title1,desc1,level1,resume1;
		String query="SELECT m.*, u.name as etudiantname, u2.name as encadreurname " +
                "FROM memoires m " +
                "JOIN users u ON m.idetudiant = u.idUSERS " +
                "JOIN users u2 ON m.idencadreur = u2.idUSERS " +
                "WHERE 1 = 1 ";
	
                if (!title.isEmpty()) {
                	title+="%'";
    		    	query+="AND title  LIKE '"+title;
    		    	
    		    	
    		    }
                
    		
    		    if (!level.isEmpty()) {
    		    	level+="%'";
    		    	query+="AND level  LIKE '"+level;
    		    	
    		    }
    		    
    		    if (!etudiantname.isEmpty()) {
    		    	etudiantname+="%'";
    		    	query+="AND u.name  LIKE '"+etudiantname;
    		    	
    		    }
    		    
    		    if (!encadreurname.isEmpty()) {
    		    	encadreurname+="%'";
    		    	query+="AND u2.name  LIKE '"+encadreurname;
    		    	
    		    }
    		    
		List<MemoireData> memoires = new ArrayList<>();
		//to fix
		try {
			PreparedStatement pstmt = DBcon.con.prepareStatement(query);
			
			
		    
			query+=" ;";
			System.out.println("query="+query);
		    try (ResultSet resultSet = pstmt.executeQuery()) {
		        while (resultSet.next()) {
		        
		           //put data in a  table or a list
		        	 id1=resultSet.getInt("idmemoires");
		        	 title1=resultSet.getString("title");
		        	 desc1=resultSet.getString("desc");
		        	 etudiant1=resultSet.getInt("idetudiant");
		        	 encadreur1=resultSet.getInt("idencadreur");
		        	 year1=resultSet.getInt("year");
		        	 level1=resultSet.getString("level");
		        	  resume1=resultSet.getString("resume");
		        	
		        	MemoireData mdata=new MemoireData(id1,etudiant1,encadreur1,year1,title1,desc1,level1,resume1);
		        	memoires.add(mdata);		        }
		        
		    }
           
       } catch (SQLException e) {
           e.printStackTrace();
           System.err.println("Failed to create statement.");
           
       }
		return memoires;
	}
	

	
	
	
	
	public Authuser getAuthUser() {
	    return u;
	}
	public int getId(String name, int type) {
		
		int id=-1;
		try {
			
			String query = "SELECT idUSERS FROM users WHERE type = ? AND name = ?";
			PreparedStatement pstmt = DBcon.con.prepareStatement(query);

			    pstmt.setInt(1, type);
			    pstmt.setString(2, name);

			   
			    try (ResultSet resultSet = pstmt.executeQuery()) {
			        while (resultSet.next()) {
			           
			             id=resultSet.getInt("idUSERS");
			           
			        }
			        
			    }
			
			    return id;
        
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Failed to create statement.");
        return -1;
    }
	}
public int getId(String matricule) {
		
		int id=-1;
		try {
			
			String query = "SELECT idUSERS FROM users WHERE matricule = ?";
			PreparedStatement pstmt = DBcon.con.prepareStatement(query);

			    pstmt.setString(1, matricule);
			    

			   
			    try (ResultSet resultSet = pstmt.executeQuery()) {
			        while (resultSet.next()) {
			           
			             id=resultSet.getInt("idUSERS");
			           
			        }
			        
			    }
			
			    return id;
        
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Failed to create statement.");
        return -1;
    }
	}
	
	
	
	public String getUserName(int id) {
		String name="";
		
try {
			
			String query = "SELECT name FROM users WHERE idUSERS = ?";
			PreparedStatement pstmt = DBcon.con.prepareStatement(query);

			    pstmt.setInt(1, id);
			    

			   
			    try (ResultSet resultSet = pstmt.executeQuery()) {
			        while (resultSet.next()) {
			           
			             name=resultSet.getString("name");
			           
			        }
			        
			    }
			
			    
        
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Failed to create statement.");
        
    }
		return name;
	}
	
	
	public int checkmatricule(String matricule) {
		int type=-1;
		
try {
			
			String query = "SELECT type FROM users WHERE matricule = ?";
			PreparedStatement pstmt = DBcon.con.prepareStatement(query);

			    pstmt.setString(1, matricule);
			    

			   
			    try (ResultSet resultSet = pstmt.executeQuery()) {
			        while (resultSet.next()) {
			           
			             type=resultSet.getInt("type");
			           
			        }
			        
			    }
			
			    
        
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Failed to create statement.");
        
    }
System.out.println("type"+type);
return type;
	}

	
	public Boolean deleteUser() {
		Authuser u= getAuthUser();
		int id=u.getId();
		System.out.println("deleted");
		//delete
		String query="DELETE from users WHERE 1=1 AND idUSERS= ?";
		try{
			PreparedStatement pstmt = DBcon.con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			 System.out.println("Row with ID " + id + " deleted successfully.");
			return true;
	           
	        } catch (SQLException e) {
	        	
	            System.err.println("Error deleting row with ID " + id + ": " + e.getMessage());
	            return false;
	        }
		
	}
	public Boolean checkAccessiblity(int memoireid) {
		Authuser u= getAuthUser();
		int id=u.getId();
try {
			
			String query = "SELECT * FROM memoires WHERE idencadreur= ? AND idmemoires=?";
			PreparedStatement pstmt = DBcon.con.prepareStatement(query);

			    pstmt.setInt(1, id);
			    pstmt.setInt(2, memoireid);

			   
			    try (ResultSet resultSet = pstmt.executeQuery()) {
			        while (resultSet.next()) {
			           
			             
			           
			        }
			        
			    }
			
			    return true;
        
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Failed to create statement.");
        return false;
    }

	}
	public Boolean deleteMemoire(int id) {
		
		
		
		String query="DELETE from memoires WHERE 1=1 AND idmemoires= ?";
		try{
			PreparedStatement pstmt = DBcon.con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			 System.out.println("Row with ID " + id + " deleted successfully.");
			return true;
	           
	        } catch (SQLException e) {
	        	
	            System.err.println("Error deleting row with ID " + id + ": " + e.getMessage());
	            return false;
	        }
	}
	public Boolean updateMemoire(int id, int year, String title, String level,String desc,String resume) {
	    String query = "UPDATE memoires SET";

	    if (!title.isEmpty()) {
	        query += " title = '" + title + "',";
	    }
	    if (!level.isEmpty()) {
	        query += " level = '" + level + "',";
	    }
	    if (!desc.isEmpty()) {
	        query += " `desc` = '" + desc + "',";
	    }
	    if (!resume.isEmpty()) {
	        query += " resume = '" + resume + "',";
	    }
	    if (year != -1) {
	        query += " year = " + year + ",";
	    }

	    // Remove the trailing comma if any
	    if (query.endsWith(",")) {
	        query = query.substring(0, query.length() - 1);
	    }

	    query += " WHERE idmemoires = " + id;
	    System.out.println("Executing SQL query: " + query);

	    try {
	        PreparedStatement pstmt = DBcon.con.prepareStatement(query);

	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Memoire updated successfully.");
	            return true;
	        } else {
	            System.out.println("No memoire found with the given ID.");
	            return false;
	        }

	    } catch (SQLException e) {
	        System.err.println("Error updating memoire with ID " + id + ": " + e.getMessage());
	        return false;
	    }
	}


}