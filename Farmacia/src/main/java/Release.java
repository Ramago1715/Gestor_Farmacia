

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;


@WebServlet("/Release")
public class Release extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		String medicamento = request.getParameter("medicamento");
		String paciente = request.getParameter("paciente");
		String fechalimite = request.getParameter("fechalimite");
		Date.valueOf(fechalimite);
		String idxips = request.getParameter("idxips");
		Doctor doc = new Doctor();
		boolean logged = doc.isLogged(mail, session);
		if(logged) {
			Connection con = null;
			

			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Farmacia", "root", "");
			
			Statement st = null;
				st = con.createStatement();
			String query1 = "INSERT INTO xips VALUES ( "+idxips+", '" + mail +"', " + medicamento +", '"+ paciente + "', '" + fechalimite + "')";
				
			st.executeUpdate(query1);
	
			} catch (SQLException e) {
				e.printStackTrace();
			}

		
		} else {
			response.getWriter().write("null");
		}
	}	

}
