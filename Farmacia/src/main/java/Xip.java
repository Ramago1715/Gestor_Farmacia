import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Xip {
	private int id;
	private Medicine medicine;
	private Patient patient;
	private Date date;
	
	public Xip() {
		
	}
	public Xip(int id,Medicine medicine,Patient patient,Date date) {
		setId(id);
		setMedicine(medicine);
		setPatient(patient);
		setDate(date);
		
	}

	public void load(int id)  {
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
		String query1 = "Select * From xips Where id = ";
    	String query2 = query1 + Integer.toString(id);
        ResultSet rs;
       

        try {
        	rs = st.executeQuery(query2);
			while (rs.next()) {
				int id1 = rs.getInt("id");
			    int id_medicine  = rs.getInt("id_medicine");
			    String mail_patient = rs.getString("id_patient");
			    Date date = rs.getDate("date");
			    
			    Medicine medicine = new Medicine();
			    Patient patient = new Patient();
			    
			    medicine.load(id_medicine);
			    patient.load(mail_patient);
			    
			    setId(id1);
			    setMedicine(medicine);
			    setPatient(patient);
			    setDate(date);
			    
			    
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
    	/*objeto medicine y patient vacio, usar sus metodos load*/
    	
    	
    	
    	
		
	}
	
	
	@Override
	public String toString() {
		return "Xip [id=" + id + ", medicine=" + medicine + ", patient=" + patient + ", date=" + date + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
		
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	
	
}
