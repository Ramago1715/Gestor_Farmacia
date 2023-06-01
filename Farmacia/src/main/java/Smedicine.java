

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/Smedicine")
public class Smedicine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Smedicine() {
        
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Doctor doc = new Doctor();
		JSONArray JSON = new JSONArray();
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		boolean logged = doc.isLogged(mail,session);
		if(logged) {
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
		String query1 = "Select id,name From medicines";
		ResultSet rs;
		try {
			rs = st.executeQuery(query1);
			while (rs.next()) {
				try {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				JSONObject json = new JSONObject();
				json.put("idmedicamento",id);
				json.put("nombremedicamento",name);
				JSON.put(json);
				
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}

		
			con.close();
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.getWriter().append(JSON.toString());
		

		}else {
			response.setContentType("text/plain");
			response.getWriter().write("null");
		}
	}

}
