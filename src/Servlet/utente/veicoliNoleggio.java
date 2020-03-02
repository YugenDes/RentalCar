package Servlet.utente;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DAOFactory;
//meow >.< 
@WebServlet("/utente/veicoliNoleggio")
public class veicoliNoleggio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory db ;

    public veicoliNoleggio() {
        super();
        db=DAOFactory.getJpaDaoFactory();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LocalDateTime dataInizio = (LocalDateTime)session.getAttribute("dataInizio");
		LocalDateTime dataFine = (LocalDateTime)session.getAttribute("dataFine");
		int categoria = Integer.parseInt(request.getParameter("categoria"));
		String email = request.getParameter("email");
		session.setAttribute("veicoli", db.getNoleggioDAO().getMacchineTime(dataInizio,dataFine,categoria));
		response.sendRedirect(request.getContextPath()+"/utente/veicoliDisp");
	}

}
