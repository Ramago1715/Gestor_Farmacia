

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/ServeXips")
public class ServeXips extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeXips() {
        
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String respuesta = "null";
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		Doctor doc = new Doctor();
		boolean logged = doc.isLogged(mail,session);

		if(logged) {
			doc.load(mail);

			doc.loadReleaseList();
			respuesta = doc.getTable();		
			
		}
			

		response.getWriter().write(respuesta);
		
	}

}
