import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;

public class Doctor extends Person {
	String pass;
	Date lastlog;
	String session;
	ArrayList<Xip> releaseList;

	public Doctor() {
		releaseList = new ArrayList<Xip>();
		// TODO Auto-generated constructor stub
	}

	public Doctor(String name, String mail, String pass, String session) {
		super(name, mail);
		setName(name);
		setMail(mail);
		setPass(pass);
		setLastlog(lastlog);
		setSession(session);
		releaseList = new ArrayList<Xip>();

	}

	public void login(String mail, String pass) {
		Connection con = null;
		

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Farmacia", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement st = null;

		try {
			st = con.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query1 = "Select mail,pass FROM doctors WHERE mail = '" + mail + "'" + "AND pass = '" + pass + "'";
		ResultSet rs;
		try {
			rs = st.executeQuery(query1);
			if (rs.next() && pass!="" && mail!="") {
				/*
				 * String mail1 = rs.getString("mail"); String passw = rs.getString("pass");
				 */
				System.out.println("Credenciales Correctas");
				String session = String.valueOf((long) (Math.random() * (9999999999L - 1000000000L + 1)) + 1000000000L);
				Statement sta = con.createStatement();
				sta.executeUpdate("UPDATE doctors set  last_log = '" + LocalDate.now() + "'" + ",session = " + session
						+ " WHERE mail = '" + mail + "'");
				System.out.println("Actualizando los datos del doctor");
				load(mail);
				System.out.println("Cargando los datos en el objeto");
			}
			else {this.setSession("null");}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean isLogged(String mail, String session) {
		boolean resultado = false;
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Farmacia", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement st = null;

		try {
			st = con.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query1 = "Select session,last_log FROM doctors WHERE mail = '" + mail + "' ";
		ResultSet rs;
		try {
			rs = st.executeQuery(query1);
			while (rs.next()) {
				if (rs.getString("session").equalsIgnoreCase(session)
						&& rs.getDate("last_log").compareTo(Date.valueOf(LocalDate.now())) == 0) {
					resultado = true;
					load(mail);
				} else {
					resultado = false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public void load(String id) {
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Farmacia", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement st = null;

		try {
			st = con.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query1 = "SELECT * FROM doctors WHERE mail = '" + id + "' ";
		ResultSet rs;

		try {
			rs = st.executeQuery(query1);
			while (rs.next()) {
				String mail = rs.getString("mail");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				Date lastlog = rs.getDate("last_log");
				String session = rs.getString("session");

				setName(name);
				setMail(mail);
				setPass(pass);
				setLastlog(lastlog);
				setSession(session);

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

	public void loadReleaseList() {
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Farmacia", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement st = null;

		try {
			st = con.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query1 = "SELECT id FROM xips WHERE doctor_mail = '" + this.mail + "'";
		ResultSet rs;

		try {
			rs = st.executeQuery(query1);
			while (rs.next()) {
				Xip xip = new Xip();
				int id = rs.getInt("id");
				xip.load(id);
				setReleaseList(xip);

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

	public String getTable() {
		String tablahtml = "<table id='" + "table'" +"> \n" + "<tr> \n" + "<th>ID</th> \n" + "<th>Id del medicamento</th> \n"
				+ "<th>Mail del paciente</th> \n" + "<th>Fecha de finalizacion</th> \n" + "</tr> \n";
		for (int i = 0; i < this.releaseList.size(); i++) {
			int si = releaseList.get(i).getDate().compareTo(Date.valueOf(LocalDate.now()));
			if (si >= 0) {
				tablahtml = tablahtml + "<tr> \n" + "<td>" + releaseList.get(i).getId() + "</td> \n" + "<td>"
						+ releaseList.get(i).getMedicine().getname() + "</td> \n" + "<td>"
						+ releaseList.get(i).getPatient().getMail() + "</td> \n" + "<td>" + releaseList.get(i).getDate()
						+ "</td> \n" + "</tr> \n";
			}

		}
		tablahtml = tablahtml + "</table>";
		/* System.out.println(tablahtml); */
		return tablahtml;
	}

	@Override
	public String toString() {
		return "Doctor [pass=" + pass + ", lastlog=" + lastlog + ", session=" + session + ", releaseList=" + releaseList
				+ "]";
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Date getLastlog() {
		return lastlog;
	}

	public void setLastlog(Date lastlog) {
		this.lastlog = lastlog;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public ArrayList<Xip> getReleaseList() {
		return releaseList;
	}

	public void setReleaseList(Xip xip) {
		this.releaseList.add(xip);
	}

}