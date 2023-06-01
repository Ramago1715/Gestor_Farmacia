import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Patient extends Person {
	
	public Patient() {
		
	}
	public Patient(String name,String mail) {
		super(name,mail);
		this.name = name;
		this.mail = mail;
	}
	
	public void load(String id) {

		/*El id equivale al correo del paciente*/
Connection con = null;
		
        try {con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Farmacia",
                "root", "");
	} catch (SQLException e) {
		e.printStackTrace();
	}
		Statement st = null;
		
        try{ st = con.createStatement();
        
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		String query1 = "Select * From patients Where mail = '" +id +"'";
        ResultSet rs;
       

        try {
        	rs = st.executeQuery(query1);
			while (rs.next()) {
				String mail = rs.getString("mail");
			    String name  = rs.getString("name");
			    
			    this.setName(name);
			    this.setMail(mail);
			   	    
										}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
        
        
        
        
        try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "Patient [name=" + name + ", mail=" + mail + "]";
	}
	
}

