import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Medicine {
	private int id;
	private String name;
	private float Tmax;
	private float Tmin;
	
	public Medicine() {

		
	}
	
	public Medicine(int id,String name,float Tmax,float Tmin) {
		this.id = id;
		this.name = name;
		this.Tmax = Tmax;
		this.Tmin = Tmin;
	}
	
	public void load(int id) {
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
		String query1 = "Select * From medicines Where id = ";
    	String query2 = query1 + Integer.toString(id);
        ResultSet rs;
       

        try {
        	rs = st.executeQuery(query2);
			while (rs.next()) {
				int id1 = rs.getInt("id");
			    String name  = rs.getString("name");
			    float tmax = rs.getFloat("tmax");
			    float tmin = rs.getFloat("tmin");
			    
			    this.setId(id1);
			    this.setName(name);
			    this.setTmax(tmax);
			    this.setTmin(tmin);
			    
			    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
        try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getTmax() {
		return Tmax;
	}

	public void setTmax(float tmax) {
		Tmax = tmax;
	}

	public float getTmin() {
		return Tmin;
	}

	public void setTmin(float tmin) {
		Tmin = tmin;
	}

	@Override
	public String toString() {
		return "Medicine [id=" + id + ", name=" + name + ", Tmax=" + Tmax + ", Tmin=" + Tmin + "]";
	}
	
	
	
	
	
}
